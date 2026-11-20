package com.example

import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppDatabase
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.GlasgowEvaluationEntity
import com.example.data.local.entities.LabResultEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import com.example.data.medical.HepaticInput
import com.example.data.medical.InflamCoagInput
import com.example.data.medical.MedicalInterpreter
import com.example.data.medical.NfsInput
import com.example.data.medical.UeCreatInput
import com.example.data.medical.UrineDipstickInput
import com.example.data.model.ClinicalTrainingDataProvider
import com.example.data.model.ClinicalTransitionsProvider
import com.example.data.model.PreventionDataProvider
import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologyDataProvider
import com.example.data.model.courses.MedicalCoursesProvider
import com.example.data.repository.MedicalRepository
import com.example.data.storage.AndroidSharedPrefsStorage
import com.example.ui.clinical.ClinicalViewModel
import com.example.ui.home.HomeViewModel
import com.example.ui.labs.LabsViewModel
import com.example.ui.profile.ProfileViewModel
import com.example.ui.semiology.SemiologyViewModel
import com.example.ui.vitals.VitalsViewModel
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ElVitaUpgradeRobolectricTest {

  private lateinit var context: Context
  private lateinit var db: AppDatabase
  private lateinit var repository: MedicalRepository
  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
    context = ApplicationProvider.getApplicationContext()
    db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    repository = MedicalRepository(db)
  }

  @After
  fun tearDown() {
    db.close()
    Dispatchers.resetMain()
  }

  // Test A : Persistance locale de la photo de profil étudiant
  @Test
  fun testA_profilePhotoPersistenceAndRemoval() {
    val storage = AndroidSharedPrefsStorage(context)
    val photoFile = File(context.filesDir, "test_student_avatar.jpg").apply {
      writeBytes(byteArrayOf(0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte(), 0xE0.toByte()))
    }

    val vm1 = ProfileViewModel(storage, repository, db)
    var savedCallback = false
    vm1.savePhotoFromFilePath(photoFile.absolutePath) { savedCallback = it }
    assertTrue("Photo save callback must succeed", savedCallback)
    assertEquals(photoFile.absolutePath, vm1.profile.value.photoPath)

    // Recréer un nouveau ViewModel (simulation fermeture/réouverture)
    val vm2 = ProfileViewModel(storage, repository, db)
    assertEquals("Photo path must survive app restart", photoFile.absolutePath, vm2.profile.value.photoPath)

    // Suppression
    vm2.removePhoto()
    assertNull("Photo path must be null after removal", vm2.profile.value.photoPath)
    assertFalse("Photo file must be deleted on removal", photoFile.exists())
  }

  // Test B : Room MIGRATION_1_2 non-destructive & isolation stricte par dossier patient
  @Test
  fun testB_roomMigration1To2AndStrictPatientIsolation() = runBlocking {
    // 1. Vérifier que MIGRATION_1_2 crée glasgow_evaluations sans détruire les données v1
    val dbName = "test_migration_1_2.db"
    context.deleteDatabase(dbName)
    val config = SupportSQLiteOpenHelper.Configuration.builder(context)
      .name(dbName)
      .callback(object : SupportSQLiteOpenHelper.Callback(1) {
        override fun onCreate(db: SupportSQLiteDatabase) {
          db.execSQL(
            "CREATE TABLE IF NOT EXISTS `patients` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fullNameOrAlias` TEXT NOT NULL, `age` INTEGER NOT NULL, `gender` TEXT NOT NULL, `admissionDate` TEXT NOT NULL, `department` TEXT NOT NULL, `roomNumber` TEXT NOT NULL, `primaryCondition` TEXT NOT NULL, `createdAt` INTEGER NOT NULL)"
          )
          db.execSQL(
            "INSERT INTO `patients` (`id`, `fullNameOrAlias`, `age`, `gender`, `admissionDate`, `department`, `roomNumber`, `primaryCondition`, `createdAt`) VALUES (1, 'Patient Migré', 45, 'Homme', '2026-11-01', 'Médecine', '101', 'Fièvre', 1000)"
          )
        }

        override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {}
      })
      .build()

    val helper = FrameworkSQLiteOpenHelperFactory().create(config)
    val rawDb = helper.writableDatabase
    AppDatabase.MIGRATION_1_2.migrate(rawDb)

    val cursorPatients = rawDb.query("SELECT fullNameOrAlias FROM patients WHERE id = 1")
    assertTrue("Pre-existing v1 patient row must survive migration", cursorPatients.moveToFirst())
    assertEquals("Patient Migré", cursorPatients.getString(0))
    cursorPatients.close()

    val cursorGlasgowTable = rawDb.query("SELECT name FROM sqlite_master WHERE type='table' AND name='glasgow_evaluations'")
    assertTrue("glasgow_evaluations table must exist after MIGRATION_1_2", cursorGlasgowTable.moveToFirst())
    cursorGlasgowTable.close()
    rawDb.close()
    context.deleteDatabase(dbName)

    // 2. Isolation stricte entre 2 patients distincts dans Room
    val p1Id = repository.insertPatient(
      PatientEntity(fullNameOrAlias = "Patient A", age = 60, gender = "Homme", admissionDate = "01/11/2026", department = "Cardio", roomNumber = "1", primaryCondition = "HTA")
    )
    val p2Id = repository.insertPatient(
      PatientEntity(fullNameOrAlias = "Patient B", age = 32, gender = "Femme", admissionDate = "01/11/2026", department = "Pneumo", roomNumber = "2", primaryCondition = "Asthme")
    )

    repository.insertVital(VitalEntity(patientId = p1Id, temperature = 38.9f, formattedDateTime = "01/11 10:00"))
    repository.insertVital(VitalEntity(patientId = p2Id, temperature = 36.7f, formattedDateTime = "01/11 10:05"))

    repository.insertLabResult(LabResultEntity(patientId = p1Id, testType = "NFS", date = "01/11", summaryTitle = "NFS A", valuesMapJson = "{}", interpretationText = "Anémie A"))
    repository.insertLabResult(LabResultEntity(patientId = p2Id, testType = "HEPATIC", date = "01/11", summaryTitle = "Bilan B", valuesMapJson = "{}", interpretationText = "Normal B"))

    repository.insertGlasgow(GlasgowEvaluationEntity(patientId = p1Id, eyeOpening = 3, verbalResponse = 4, motorResponse = 5, totalScore = 12, alertLevel = "MODERATE", interpretationText = "GCS 12", pedagogicalNote = "", timestamp = 1000L, formattedDateTime = "01/11"))

    val p1Vitals = repository.getVitalsForPatient(p1Id).first()
    val p2Vitals = repository.getVitalsForPatient(p2Id).first()
    assertEquals(1, p1Vitals.size)
    assertEquals(38.9f, p1Vitals[0].temperature!!, 0.01f)
    assertEquals(1, p2Vitals.size)
    assertEquals(36.7f, p2Vitals[0].temperature!!, 0.01f)

    val p1Labs = repository.getLabResultsForPatientOnce(p1Id)
    val p2Labs = repository.getLabResultsForPatientOnce(p2Id)
    assertEquals(1, p1Labs.size)
    assertEquals("NFS", p1Labs[0].testType)
    assertEquals(1, p2Labs.size)
    assertEquals("HEPATIC", p2Labs[0].testType)

    assertNotNull(repository.getLatestGlasgowForPatient(p1Id))
    assertNull(repository.getLatestGlasgowForPatient(p2Id))
  }

  // Test C : Score de Glasgow déplacé dans Mesures avec obligation de patient sélectionné
  @Test
  fun testC_glasgowInVitalsGatedOnPatientAndAbsentFromLabs() = runBlocking {
    val vitalsVm = VitalsViewModel(repository)
    var requiredTriggered = false
    var savedTriggered = false

    // Sans patient sélectionné -> doit bloquer et appeler onPatientRequired
    vitalsVm.setGlasgowEye(3)
    vitalsVm.setGlasgowVerbal(4)
    vitalsVm.setGlasgowMotor(5)
    vitalsVm.saveGlasgowEvaluation(
      onSuccess = { savedTriggered = true },
      onPatientRequired = { requiredTriggered = true }
    )
    assertTrue("Glasgow save without selected patient must require patient", requiredTriggered)
    assertFalse("Glasgow save must not succeed without patient", savedTriggered)

    // Avec patient sélectionné -> doit enregistrer dans Room
    val pid = repository.insertPatient(
      PatientEntity(fullNameOrAlias = "Patient Neuro", age = 50, gender = "Homme", admissionDate = "02/11/2026", department = "Neuro", roomNumber = "12", primaryCondition = "Trauma crânien")
    )
    vitalsVm.onGlasgowPatientSelected(pid)
    requiredTriggered = false
    savedTriggered = false
    vitalsVm.saveGlasgowEvaluation(
      onSuccess = { savedTriggered = true },
      onPatientRequired = { requiredTriggered = true }
    )
    withTimeout(3000) {
      while (!savedTriggered) {
        delay(25)
      }
    }
    assertTrue("Glasgow save with selected patient must succeed", savedTriggered)
    val latestGcs = repository.getLatestGlasgowForPatient(pid)
    assertNotNull(latestGcs)
    assertEquals(12, latestGcs!!.totalScore)

    // Vérifier qu'aucune méthode Glasgow n'existe sur LabsViewModel (retrait confirmé)
    val labsMethods = LabsViewModel::class.java.declaredMethods.map { it.name }
    assertFalse(
      "LabsViewModel should no longer expose glasgow methods",
      labsMethods.any { it.contains("glasgow", ignoreCase = true) }
    )
  }

  // Test D : Analyses biologiques — 6 volets NFS + edge cases + conclusions des 5 bilans
  @Test
  fun testD_allFiveLabExamsHaveStructuredConclusionsAndNfsSixCases() {
    val nfsNormal = MedicalInterpreter.interpretNfs(NfsInput(hb = 14.2f, ht = 42.5f, gr = 4.8f, gb = 6.5f, pnn = 3.8f, plaquettes = 250f))
    val nfsHyperGb = MedicalInterpreter.interpretNfs(NfsInput(hb = 13.5f, ht = 41f, gr = 4.6f, gb = 16.2f, pnn = 12.8f, plaquettes = 260f))
    val nfsLeuko = MedicalInterpreter.interpretNfs(NfsInput(hb = 13.5f, ht = 41f, gr = 4.6f, gb = 2.5f, pnn = 0.9f, plaquettes = 240f))
    val nfsAnemia = MedicalInterpreter.interpretNfs(NfsInput(hb = 8.4f, ht = 25f, gr = 3.1f, gb = 6.5f, pnn = 3.9f, plaquettes = 240f))
    val nfsThrombo = MedicalInterpreter.interpretNfs(NfsInput(hb = 13.5f, ht = 41f, gr = 4.6f, gb = 6.5f, pnn = 3.9f, plaquettes = 38f))
    val nfsPancyto = MedicalInterpreter.interpretNfs(NfsInput(hb = 7.1f, ht = 22f, gr = 2.4f, gb = 1.9f, pnn = 0.4f, plaquettes = 15f))

    listOf(nfsNormal, nfsHyperGb, nfsLeuko, nfsAnemia, nfsThrombo, nfsPancyto).forEach { res ->
      assertTrue("NFS generalConclusion must not be blank", res.generalConclusion.isNotBlank())
      assertTrue("NFS professionalMedicalConclusion must not be blank", res.professionalMedicalConclusion.isNotBlank())
    }

    val ue = MedicalInterpreter.interpretUeCreat(UeCreatInput(urea = 14.5f, creat = 210f, na = 131f, k = 5.9f, cl = 98f, hco3 = 18f, age = 70, weightKg = 70f, isFemale = false))
    val hep = MedicalInterpreter.interpretHepatic(HepaticInput(alat = 220f, asat = 190f, pal = 110f, ggt = 95f, biliTotal = 42f, biliConj = 28f, lipase = 280f))
    val inflam = MedicalInterpreter.interpretInflamCoag(InflamCoagInput(crp = 150f, vs = 55f, pct = 2.8f, tp = 48f, inr = 2.1f, tcaRatio = 1.4f, fibrinogene = 1.5f, dDimeres = 2400f))
    val urine = MedicalInterpreter.interpretUrine(UrineDipstickInput(leucocytes = "3+", nitrites = "Positif", proteinurie = "1+ (0.3 g/L)", hematurie = "Trace", cetones = "Négatif", glucose = "Négatif"))

    listOf(ue, hep, inflam, urine).forEach { interp ->
      assertTrue("summary must be non-blank for ${interp.title}", interp.summary.isNotBlank())
      assertTrue("generalConclusion must be non-blank for ${interp.title}", interp.generalConclusion.isNotBlank())
      assertTrue("clinicalContextCaveat must be non-blank for ${interp.title}", interp.clinicalContextCaveat.isNotBlank())
    }
  }

  // Test E : Observation clinique — auto-sync non destructif depuis le dossier patient
  @Test
  fun testE_observationAutoSyncNonDestructiveFromPatientDossier() = runBlocking {
    val patient = PatientEntity(
      fullNameOrAlias = "M. Sync Test",
      age = 64,
      gender = "Homme",
      admissionDate = "04/11/2026",
      department = "Médecine Interne",
      roomNumber = "208",
      primaryCondition = "Fièvre prolongée"
    )
    val pid = repository.insertPatient(patient)
    val insertedPatient = repository.getPatientById(pid)!!

    repository.insertVital(VitalEntity(patientId = pid, temperature = 39.1f, systolicBp = 110, diastolicBp = 70, heartRate = 102, respiratoryRate = 22, oxygenSaturation = 94, formattedDateTime = "04/11/2026 08:00"))
    repository.insertGlasgow(GlasgowEvaluationEntity(patientId = pid, eyeOpening = 4, verbalResponse = 4, motorResponse = 6, totalScore = 14, alertLevel = "MODERATE", interpretationText = "Confusion légère", pedagogicalNote = "", timestamp = 2000L, formattedDateTime = "04/11/2026 08:05"))
    repository.insertLabResult(LabResultEntity(patientId = pid, testType = "NFS", date = "04/11/2026", summaryTitle = "Hyperleucocytose", valuesMapJson = "gb=15.4", interpretationText = "Hyperleucocytose à PNN"))

    val clinicalVm = ClinicalViewModel(repository)
    clinicalVm.syncObservationFromPatientDossier(insertedPatient)

    val form = clinicalVm.obsForm.value
    assertTrue("Has synced vitals", form.hasSyncedVitals)
    assertTrue("Has synced Glasgow", form.hasSyncedGlasgow)
    assertEquals(1, form.syncedLabsCount)
    assertTrue("GeneralExam must mention Glasgow 14/15", form.generalExam.contains("14/15"))
    assertTrue("GeneralExam must mention T° 39.1", form.generalExam.contains("39.1"))
    assertTrue("ComplementaryExams must include NFS result", form.complementaryExams.contains("Hyperleucocytose à PNN"))

    // Modifier l'observation localement ne doit jamais altérer les entités sources dans Room
    clinicalVm.updateObsFields(generalExam = "Texte modifié manuellement dans l'observation")
    assertEquals("Texte modifié manuellement dans l'observation", clinicalVm.obsForm.value.generalExam)
    assertEquals(14, repository.getLatestGlasgowForPatient(pid)!!.totalScore)
    assertEquals(39.1f, repository.getLatestVitalForPatient(pid)!!.temperature!!, 0.01f)
    assertEquals("Hyperleucocytose à PNN", repository.getLabResultsForPatientOnce(pid)[0].interpretationText)
  }

  // Test F : Prévention — 23 fiches repères complètes & compatibilité Accueil
  @Test
  fun testF_preventionHas23CompleteTopicsAndHomeCompatibility() {
    val topics = PreventionDataProvider.topics
    assertEquals("Prevention library must contain exactly 23 topics", 23, topics.size)
    assertEquals("Topic IDs must be unique", topics.size, topics.map { it.id }.toSet().size)

    topics.forEach { t ->
      assertTrue("${t.id} title missing", t.title.isNotBlank())
      assertTrue("${t.id} definition missing", t.definition.isNotBlank())
      assertTrue("${t.id} riskFactors missing", t.riskFactors.isNotEmpty())
      assertTrue("${t.id} targetPopulation missing", t.targetPopulation.isNotBlank())
      assertTrue("${t.id} preventionMeasures missing", t.preventionMeasures.isNotEmpty())
      assertTrue("${t.id} screeningRecommendations missing", t.screeningRecommendations.isNotBlank())
      assertTrue("${t.id} practicalAdvice missing", t.practicalAdvice.isNotEmpty())
      assertTrue("${t.id} warningSigns missing", t.warningSigns.isNotEmpty())
    }

    val homeVm = HomeViewModel(repository)
    assertEquals(23, homeVm.preventionTopics.size)
  }

  // Test G : Sémiologie — >= 20 fiches dans chacune des 13 spécialités + 14 rubriques complètes
  @Test
  fun testG_all13SpecialtiesHaveAtLeast20CompleteSheets() {
    val sheets = SemiologyDataProvider.sheets
    assertEquals("Sheet IDs must be unique", sheets.size, sheets.map { it.id }.toSet().size)

    val specialtyCategories = SemiologyCategory.entries.filter { it.isSpecialty }
    assertEquals("There must be 13 clinical specialties", 13, specialtyCategories.size)

    specialtyCategories.forEach { category ->
      val catSheets = sheets.filter { it.category == category }
      assertTrue(
        "Category ${category.label} must have >= 20 sheets, found ${catSheets.size}",
        catSheets.size >= 20
      )
    }

    sheets.forEach { s ->
      assertTrue("${s.id} definition", s.definition.isNotBlank())
      assertTrue("${s.id} physiopathology", s.physiopathology.isNotBlank())
      assertTrue("${s.id} etiologies", s.etiologies.isNotEmpty())
      assertTrue("${s.id} circumstances", s.circumstances.isNotBlank())
      assertTrue("${s.id} anamnesis", s.anamnesis.isNotBlank())
      assertTrue("${s.id} searchMethod", s.searchMethod.isNotBlank())
      assertTrue("${s.id} physicalExam", s.physicalExam.isNotBlank())
      assertTrue("${s.id} associatedSigns", s.associatedSigns.isNotEmpty())
      assertTrue("${s.id} clinicalSignificance", s.clinicalSignificance.isNotBlank())
      assertTrue("${s.id} differentialDiagnoses", s.differentialDiagnoses.isNotEmpty())
      assertTrue("${s.id} complementaryExams", s.complementaryExams.isNotEmpty())
      assertTrue("${s.id} gravitySigns", s.gravitySigns.isNotEmpty())
      assertTrue("${s.id} pitfallsAndLimits", s.pitfallsAndLimits.isNotBlank())
      assertTrue("${s.id} keyPoints", s.keyPoints.isNotEmpty())
    }
  }

  // Test H : Cours Médicaux (20 pleuropulmonaires + spécialités clés, 14 rubriques + tableaux) & SemiologyViewModel
  @Test
  fun testH_medicalCoursesCompleteAndFilterableInViewModel() {
    val courses = MedicalCoursesProvider.courses
    assertEquals("Course IDs must be unique", courses.size, courses.map { it.id }.toSet().size)

    val respCourses = courses.filter { it.specialty == SemiologyCategory.RESPIRATORY }
    assertTrue("Respiratory specialty must have >= 20 courses, got ${respCourses.size}", respCourses.size >= 20)
    assertTrue("Total courses must be >= 31, got ${courses.size}", courses.size >= 31)

    courses.forEach { c ->
      assertTrue("${c.id} title", c.title.isNotBlank())
      assertTrue("${c.id} definition", c.definition.isNotBlank())
      assertTrue("${c.id} epidemiology", c.epidemiology.isNotBlank())
      assertTrue("${c.id} etiologiesAndRiskFactors", c.etiologiesAndRiskFactors.isNotEmpty())
      assertTrue("${c.id} physiopathology", c.physiopathology.isNotBlank())
      assertTrue("${c.id} classificationAndPathology", c.classificationAndPathology.isNotBlank())
      assertTrue("${c.id} clinicalSigns", c.clinicalSigns.isNotBlank())
      assertTrue("${c.id} paraclinicalExams", c.paraclinicalExams.isNotEmpty())
      assertTrue("${c.id} diagnosticCriteria", c.diagnosticCriteria.isNotEmpty())
      assertTrue("${c.id} clinicalForms", c.clinicalForms.isNotEmpty())
      assertTrue("${c.id} evolutionAndPrognosis", c.evolutionAndPrognosis.isNotBlank())
      assertTrue("${c.id} managementPrinciples", c.managementPrinciples.isNotEmpty())
      assertTrue("${c.id} differentialDiagnoses", c.differentialDiagnoses.isNotEmpty())
      assertTrue("${c.id} clinicalCaseTitle", c.clinicalCaseTitle.isNotBlank())
      assertTrue("${c.id} clinicalCaseScenario", c.clinicalCaseScenario.isNotBlank())
      assertTrue("${c.id} clinicalCaseCorrection", c.clinicalCaseCorrection.isNotBlank())
      assertTrue("${c.id} keyPoints", c.keyPoints.isNotEmpty())
      assertTrue("${c.id} summaryTables", c.summaryTables.isNotEmpty())
      assertTrue(
        "${c.id} must mark case as cas fictif pédagogique",
        c.clinicalCaseTitle.contains("cas fictif pédagogique", ignoreCase = true) ||
          c.clinicalCaseScenario.contains("cas fictif pédagogique", ignoreCase = true)
      )
    }

    val vm = SemiologyViewModel()
    vm.selectCourseSpecialty(SemiologyCategory.RESPIRATORY)
    assertTrue(vm.getFilteredCourses().size >= 20)
    vm.updateCourseSearchQuery("Embolie")
    assertTrue(vm.getFilteredCourses().any { it.title.contains("Embolie", ignoreCase = true) })
  }

  // Test I : Formation Clinique Pédagogique — enrichissements (>= 5 exemples/relances)
  @Test
  fun testI_clinicalTrainingEnrichmentInvariants() {
    val skills = ClinicalTrainingDataProvider.anamnesisSkills
    assertEquals(14, skills.size)
    skills.forEach { skill ->
      assertTrue("Skill ${skill.id} phrasings >= 5", skill.professionalPhrasings.size >= 5)
      assertTrue("Skill ${skill.id} errors >= 3", skill.errorsToAvoid.size >= 3)
      assertTrue("Skill ${skill.id} patientResponses >= 5", skill.patientResponsesExamples.size >= 5)
      assertTrue("Skill ${skill.id} followUpQuestions >= 5", skill.followUpQuestions.size >= 5)
      assertEquals("Skill ${skill.id} 4 choices", 4, skill.practicalExerciseChoices.size)
    }

    val guides = ClinicalTrainingDataProvider.infoCategoriesGuide
    assertEquals(9, guides.size)
    guides.forEach { g ->
      assertTrue("Guide ${g.categoryName} examples >= 5", g.examples.size >= 5)
    }

    val obsSkills = ClinicalTrainingDataProvider.observationSkills
    assertEquals(4, obsSkills.size)
    obsSkills.forEach { os ->
      assertTrue("ObsSkill ${os.title} additionalExamples >= 5", os.additionalExamples.size >= 5)
    }

    val synthesis = ClinicalTrainingDataProvider.synthesisCases
    assertTrue("Synthesis cases >= 5", synthesis.size >= 5)
    synthesis.forEach { c ->
      assertTrue(
        "Case ${c.id} must be explicitly labeled cas fictif pédagogique",
        c.title.contains("cas fictif pédagogique", ignoreCase = true) ||
          c.patientProfile.contains("cas fictif pédagogique", ignoreCase = true)
      )
    }
  }

  // Test J : Sections 5 (Transitions 5.1-5.11 + 10 appareils) et 6 (Présentation Orale)
  @Test
  fun testJ_transitionsAndOralPresentationDataCompleteness() {
    val sections = ClinicalTransitionsProvider.transitionSections
    assertEquals("Must have 11 transition sections (5.1 to 5.11)", 11, sections.size)

    sections.forEach { sec ->
      assertTrue("Section ${sec.id} recommendedPhrases >= 5", sec.recommendedPhrases.size >= 5)
      assertTrue("Section ${sec.id} phrasesToAvoid non-empty", sec.phrasesToAvoid.isNotEmpty())
    }

    val section56 = sections.firstOrNull { it.id == "5.6" }
    assertNotNull("Section 5.6 must exist", section56)
    assertEquals("Section 5.6 must cover 10 systems", 10, section56!!.systems.size)
    section56.systems.forEach { sys ->
      assertTrue("${sys.systemName} transitionPhrases >= 5", sys.transitionPhrases.size >= 5)
      assertTrue("${sys.systemName} targetedQuestions >= 5", sys.targetedQuestions.size >= 5)
    }

    val oralCases = ClinicalTransitionsProvider.oralPresentationCases
    assertTrue("Oral presentation cases >= 5", oralCases.size >= 5)
    oralCases.forEach { oc ->
      assertTrue("${oc.id} badVersion non-blank", oc.badVersion.isNotBlank())
      assertTrue("${oc.id} referenceScript non-blank", oc.referenceScript.isNotBlank())
      assertTrue("${oc.id} keyStrengths non-empty", oc.keyStrengths.isNotEmpty())
      assertTrue("${oc.id} seniorQuestions non-empty", oc.seniorQuestions.isNotEmpty())
      assertTrue("${oc.id} labeled cas fictif pédagogique", oc.title.contains("cas fictif pédagogique", ignoreCase = true))
    }

    assertEquals("Oral evaluation grid has 8 criteria", 8, ClinicalTransitionsProvider.oralEvaluationCriteria.size)
  }
}
