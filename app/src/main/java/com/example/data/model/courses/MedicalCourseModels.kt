package com.example.data.model.courses

import com.example.data.model.SemiologyCategory

data class CourseSummaryTable(
  val title: String,
  val headers: List<String>,
  val rows: List<List<String>>
)

data class MedicalCourse(
  val id: String,
  val title: String,
  val specialty: SemiologyCategory,
  val academicLevel: String = "Externat / DFASM (3e–6e année)",
  val subtitle: String,
  // 14 rubriques universitaires obligatoires
  val definition: String,                      // 1. Définition
  val epidemiology: String,                    // 2. Épidémiologie
  val etiologiesAndRiskFactors: List<String>,  // 3. Étiologies / Facteurs de risque
  val physiopathology: String,                 // 4. Physiopathologie
  val classificationAndPathology: String,      // 5. Anatomopathologie / Classification
  val clinicalSigns: String,                   // 6. Clinique (signes fonctionnels et physiques)
  val paraclinicalExams: List<String>,         // 7. Examens paracliniques
  val diagnosticCriteria: List<String>,        // 8. Critères diagnostiques
  val clinicalForms: List<String>,             // 9. Formes cliniques
  val evolutionAndPrognosis: String,           // 10. Évolution, complications et pronostic
  val managementPrinciples: List<String>,      // 11. Prise en charge (principes généraux)
  val differentialDiagnoses: List<String>,     // 12. Diagnostics différentiels
  val clinicalCaseTitle: String,               // 13. Cas clinique pédagogique (titre)
  val clinicalCaseScenario: String,            // 13. Cas clinique pédagogique (énoncé fictif)
  val clinicalCaseCorrection: String,          // 13. Cas clinique pédagogique (analyse & correction)
  val keyPoints: List<String>,                 // 14. Points clés à retenir
  val summaryTables: List<CourseSummaryTable> = emptyList()
)
