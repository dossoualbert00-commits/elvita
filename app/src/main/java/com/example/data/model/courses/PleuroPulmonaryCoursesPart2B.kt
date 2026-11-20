package com.example.data.model.courses

import com.example.data.model.SemiologyCategory

object PleuroPulmonaryCoursesPart2B {
  val courses: List<MedicalCourse> = listOf(
    MedicalCourse(
      id = "course_resp_16_cancer_bronchique",
      title = "16. Cancer Bronchopulmonaire (CBP)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Première cause de mortalité par cancer — Opposition CBNPC vs CBPC et pièges révélateurs",
      definition = "Tumeur maligne primitive développée aux dépens de l'épithélium bronchique ou bronchiolo-alvéolaire, regroupée en deux grandes entités de comportement et de traitement opposés : Cancers Bronchiques Non à Petites Cellules (CBNPC, 85%) et Cancers à Petites Cellules (CBPC, 15%).",
      epidemiology = "1ère cause de décès par cancer chez l'homme et en forte progression chez la femme (~50 000 nouveaux cas/an en France). Pic de fréquence entre 55 et 75 ans ; mortalité élevée car diagnostiqué à un stade métastatique dans > 60% des cas.",
      etiologiesAndRiskFactors = listOf(
        "TABAGISME actif ou passif (> 85–90% des cas — risque multiplié par 10 à 20 selon la durée surtout > quantité/jour !)",
        "Expositions professionnelles reconnues en maladie professionnelle : AMIANTE (synergie multiplicative × 50 avec le tabac !!), radon, silice, chrome, arsenic, hydrocarbures polycycliques",
        "Formes du non-fumeur (10–15%, souvent femmes jeunes) : adénocarcinomes porteurs d'altérations moléculaires ciblables (EGFR, ALK, ROS1, RET... !)"
      ),
      physiopathology = "L'exposition répétée aux cancérogènes tabagiques accumule des mutations somatiques bronchiques (TP53, KRAS) conduisant à la dysplasie puis au carcinome invasif. Extension locale (compression cave supérieure, nerf récurrent, plexus brachial C8-D1 = Pancoast-Tobias), ganglionnaire médiastinale et métastatique (cerveau, os, surrénales, foie).",
      classificationAndPathology = "1) CBNPC (85%) : Adénocarcinome (le plus fréquent aujourd'hui, périphérique, souvent chez femme/non-fumeur avec cibles moléculaires !), Carcinome Épidermoïde (proximal central hilaire, gros fumeur, volontiers nécrosé/hypercalcémiant par PTHrP !), Carcinome à grandes cellules. 2) CBPC (15%) : tumeur neuroendocrine très agressive, centrale médiastino-hilaire massive, chimiosensible mais rechute fréquente, nombreux syndromes paranéoplasiques (SIADH, Lambert-Eaton, Cushing).",
      clinicalSigns = "SOUVENT ASYMPTOMATIQUE LONGTEMPS. Signes locaux d'alerte chez un fumeur > 40–45 ans : modification d'une toux chronique de fumeur, HÉMOPTYSIE (même minime !), pneumonie récidivante au MÊME ENDROIT, dyspnée/wheezing localisé. Signes d'extension locorégionale : DYSPHONIE (paralysie récurrentielle gauche !), SYNDROME DE CLAUDE BERNARD-HORNER + névralgie C8-D1 du membre supérieur (syndrome de Pancoast-Tobias sur tumeur de l'apex !), SYNDROME CAVE SUPÉRIEUR (œdème en pèlerine + turgescence jugulaire + circulation collatérale thoracique -> urgence !). Altération de l'état général (amaigrissement) et métastases (douleurs osseuses, céphalées/crise d'épilepsie).",
      paraclinicalExams = listOf(
        "DIAGNOSTIC POSITIF = HISTOLOGIE OBLIGATOIRE avant tout traitement : Scanner thoraco-abdominal avec coupes surrénaliennes/hépatiques injecté -> Fibroscopie bronchique avec biopsies (tumeur proximale) OU Ponction-biopsie transthoracique sous scanner (nodule périphérique)",
        "BIOMARQUEURS MOLÉCULAIRES + PD-L1 SYSTÉMATIQUES dans tout CBNPC non épidermoïde (et non-fumeur) : EGFR, ALK, ROS1, BRAF, KRAS, MET, RET, NTRK -> ouvre l'accès aux thérapies ciblées orales et à l'immunothérapie !",
        "BILAN D'EXTENSION COMPLET : TEP-Scanner au 18-FDG + IRM CÉRÉBRALE SYSTÉMATIQUE (même asymptomatique !) + Échoendoscopie bronchique (EBUS) si adénopathies médiastinales suspectes (stade TNM 8e/9e édition !)",
        "Bilan d'opérabilité avant chirurgie éventuelle (stades localisés I–IIIA) : EFR (VEMS, DLCO), scintigraphie/VO2max, bilan cardiaque"
      ),
      diagnosticCriteria = listOf(
        "Preuve anatomopathologique obligatoire (CBNPC vs CBPC + sous-type + PD-L1/statut moléculaire) + Stadification TNM complète par TEP-TDM et IRM cérébrale"
      ),
      clinicalForms = listOf(
        "Syndrome de Pancoast-Tobias (tumeur de l'apex détruisant 1ère côte + plexus C8-D1 + ganglion stellaire = Claude Bernard-Horner)",
        "Syndromes paranéoplasiques classiques : Hyponatrémie par SIADH (Schwarz-Bartter dans le CBPC !), Hypercalcémie maligne (PTHrP dans l'épidermoïde !), Ostéoarthropathie hypertrophiante de Pierre-Marie (hippocratisme digital douloureux)"
      ),
      evolutionAndPrognosis = "Pronostic transformé pour les formes localisées opérables (survie à 5 ans ~60–80% au stade I) et les métastatiques avec addiction oncogénique (EGFR/ALK) ou forte réponse à l'immunothérapie, mais globalement sombre (~20% à 5 ans tous stades confondus). Sevrage tabagique améliore le pronostic même après diagnostic !",
      managementPrinciples = listOf(
        "CBNPC localisé (stades I–IIIA opérables) : CHIRURGIE d'exérèse (lobectomie + curage ganglionnaire) +/- chimiothérapie/immunothérapie néoadjuvante ou adjuvante (seule chance de guérison définitive !)",
        "CBNPC localement avancé non résécable (IIIB/IIIC) : Radio-chimiothérapie concomitante + consolidation par Immunothérapie (Durvalumab)",
        "CBNPC métastatique (stade IV) : 1) Si altération moléculaire ciblable (EGFR, ALK...) -> Thérapie ciblée TKI orale ; 2) Sinon -> Immunothérapie (anti-PD1/PD-L1) seule si PD-L1 >= 50%, ou Chimio-Immunothérapie",
        "CBPC (quasi toujours non chirurgical !) : Chimiothérapie (Sels de platine + Étoposide) + Immunothérapie (stades étendus) ou + Radiothérapie thoracique précoce et irradiation prophylactique crânienne (stades localisés)"
      ),
      differentialDiagnoses = listOf(
        "Métastase pulmonaire unique d'un autre cancer (côlon, rein, sein, mélanome), Tuberculome / mycose pulmonaire, Hamartochondrome ou carcinoïde bronchique typique, Sarcoïdose médiastinale"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Syndrome de Pancoast-Tobias de l'apex droit",
      clinicalCaseScenario = "M. R., 61 ans (cas fictif pédagogique), fumeur 40 PA, consulte pour une douleur intense de l'épaule droite irradiant vers le bord interne du bras jusqu'aux 4e et 5e doigts droits, persistante la nuit depuis 2 mois. L'examen découvre à droite un myosis, un ptosis modéré de la paupière supérieure et une énophtalmie, avec amyotrophie de l'éminence hypothénar droite. Radio thorax : opacité dense du sommet pulmonaire droit lysant l'arc postérieur de la 1ère côte.",
      clinicalCaseCorrection = "1) SYNDROME DE PANCOAST-TOBIAS révélant un cancer bronchopulmonaire de l'apex pulmonaire droit (envahissement du plexus brachial inférieur C8-D1 + chaîne sympathique cervicale donnant un syndrome de Claude Bernard-Horner homolatéral + lyse costale).\n" +
        "2) Bilan urgent : Scanner TAP + Biopsie pour preuve histologique + IRM cérébrale + IRM du défilé cervicothoracique/plexus brachial + TEP-TDM + recherche biomarqueurs moléculaires (PD-L1, EGFR, ALK...).\n" +
        "3) Discussion en RCP spécialisée (souvent radio-chimiothérapie néoadjuvante puis chirurgie d'exérèse si résécable après bilan complet) + antalgie neuropathique et sevrage tabagique.",
      keyPoints = listOf(
        "Fumeur > 40 ans + hémoptysie OU modification de toux OU pneumonie récidivante au même site OU dysphonie = SCANNER THORACIQUE + FIBROSCOPIE !",
        "CBPC (15%) = central, chimiosensible, SIADH, jamais/rarement opéré ; CBNPC (85%) = adénocarcinome/épidermoïde -> chercher EGFR/ALK/PD-L1 !",
        "Toujours faire IRM cérébrale et TEP-TDM dans le bilan d'extension d'un CBNPC avant traitement curatif"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Comparaison schématique CBNPC vs CBPC",
          headers = listOf("Critère", "CBNPC (85%)", "CBPC (15%)"),
          rows = listOf(
            listOf("Sous-types / Siège", "Adénocarcinome (périphérique), Épidermoïde (central)", "Neuroendocrine agressif (médiastino-hilaire massif)"),
            listOf("Biologie / Paranéoplasique", "Hypercalcémie PTHrP (épidermoïde), EGFR/ALK (adénoK)", "Hyponatrémie SIADH, Cushing, Lambert-Eaton"),
            listOf("Chirurgie (stade précoce)", "Traitement de référence curatif (lobectomie)", "Quasi JAMAIS chirurgical (chimio-radiothérapie / immuno)")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_17_htap",
      title = "17. Hypertension Pulmonaire (HTP / HTAP)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "PAP moyenne > 20 mmHg au cathétérisme droit — Dyspnée isolée et cœur pulmonaire droit",
      definition = "Élévation pathologique de la pression artérielle pulmonaire moyenne (PAPm) > 20 mmHg au repos, mesurée avec certitude par cathétérisme cardiaque droit (seuil ESC 2022 abaissé de 25 à > 20 mmHg !).",
      epidemiology = "L'hypertension pulmonaire dans son ensemble est fréquente (surtout secondaire aux cardiopathies gauches et aux maladies respiratoires/BPCO). L'HTAP vraie du Groupe 1 est une maladie rare sévère (~15–50 cas/million), touchant volontiers la femme jeune dans les formes idiopathiques/associées.",
      etiologiesAndRiskFactors = listOf(
        "5 GROUPES CLINIQUES OMS : Groupe 1 = HTAP précapillaire vraie (idiopathique, génétique BMPR2, anorexigènes/dasatinib, connectivites Sclérodermie !!, VIH, cardiopathies congénitales, hypertension portale)",
        "Groupe 2 (le PLUS FRÉQUENT !) = HTP des CARDIOPATHIES GAUCHES (post-capillaire : IC à FEVG réduite ou préservée, valvulopathie mitrale/aortique !)",
        "Groupe 3 = HTP des MALADIES RESPIRATOIRES / HYPOXIE CHRONIQUE (BPCO, fibrose pulmonaire, SAOS sévère)",
        "Groupe 4 = HTP THROMBOEMBOLIQUE CHRONIQUE (HTP-TEC post-EP — SEULE FORME CURABLE CHIRURGICALEMENT PAR ENDARTÉRIECTOMIE !!)",
        "Groupe 5 = Mécanismes multifactoriels/inconnus (sarcoïdose, drépanocytose, myéloprolifératifs)"
      ),
      physiopathology = "Remodelage artériolaire pulmonaire (prolifération endothéliale et musculaire lisse, microthromboses in situ -> lésions plexiformes) augmentant les résistances vasculaires pulmonaires (RVP > 2 UW) -> surcharge systolique chronique du VD -> hypertrophie puis dilatation et défaillance cardiaque droite fatale.",
      classificationAndPathology = "Distinction hémodynamique fondamentale au cathétérisme droit : HTP PRÉCAPILLAIRE (PAPm > 20 mmHg ET Pression Artérielle Pulmonaire d'Occlusion PAPO <= 15 mmHg ET RVP > 2 UW -> Groupes 1, 3, 4, 5) vs HTP POST-CAPILLAIRE (PAPm > 20 mmHg ET PAPO > 15 mmHg -> Groupe 2 Cardiopathie gauche !).",
      clinicalSigns = "DYSPNÉE D'EFFORT PROGRESSIVE ISOLÉE (signe révélateur constant mais souvent tardif avec errance diagnostique de 1–2 ans car radio et EFR de base normales !!), asthénie extrême à l'effort, LIPOTHYMIES OU SYNCOPES D'EFFORT (signe de gravité majeure = bas débit fixe !), douleurs angineuses d'effort (ischémie VD), dysphonie rare (compression récurrent gauche = syndrome d'Ortner). AUSCULTATION : ÉCLAT DE B2 AU FOYER PULMONAIRE ++, souffle systolique d'insuffisance tricuspide (Signe de Carvallo). Signes d'IVD tardifs : turgescence jugulaire, reflux hépato-jugulaire, hépatomégalie, OMI, ascite.",
      paraclinicalExams = listOf(
        "DÉPISTAGE DE 1ÈRE INTENTION : Échocardiographie Transthoracique (ETT) estimant la Pression Artérielle Pulmonaire Systolique (PAPS) via le flux d'IT et mesurant le VD (dilatation VD/VG > 1, septum paradoxal, TAPSE)",
        "DIAGNOSTIC DE CERTITUDE OBLIGATOIRE : CATHÉTÉRISME CARDIAQUE DROIT (mesure PAPm > 20 mmHg, PAPO <= 15 vs > 15, Débit cardiaque, RVP > 2 UW, + test de vasoréactivité au NO dans le Groupe 1 !)",
        "SCINTIGRAPHIE PULMONAIRE DE VENTILATION/PERFUSION (V/Q) SYSTÉMATIQUE devant toute HTP précapillaire inexpliquée pour ne JAMAIS rater une HTP-TEC du Groupe 4 (meilleure sensibilité que l'angioscanner pour les obstructions distales !!)",
        "Bilan étiologique complet : EFR + DLCO (chute isolée de la DLCO très évocatrice dans la sclérodermie !), TDM thoracique, sérologies VIH/VHB/VHC, bilan hépatique, auto-anticorps (FAN, anti-centromères/Scl-70 !), Test de marche de 6 min (TM6) + BNP"
      ),
      diagnosticCriteria = listOf(
        "Preuve hémodynamique obligatoire au Cathétérisme Cardiaque Droit : PAPm > 20 mmHg au repos (Précapillaire si PAPO <= 15 mmHg et RVP > 2 UW)"
      ),
      clinicalForms = listOf(
        "HTAP associée à la sclérodermie systémique limitée (syndrome CREST -> dépistage annuel systématique par ETT + DLCO !)",
        "HTP post-embolique chronique (HTP-TEC -> traitement chirurgical curatif possible ou angioplastie par ballonnets !)"
      ),
      evolutionAndPrognosis = "Maladie sévère évoluant vers l'insuffisance cardiaque droite réfractaire ; survie nettement améliorée par les bi/trithérapies vasodilatatrices ciblées précoces en centre expert (ou endartériectomie si Groupe 4).",
      managementPrinciples = listOf(
        "Adresser IMPÉRATIVEMENT vers un CENTRE DE RÉFÉRENCE / COMPÉTENCE HTP avant de prescrire tout vasodilatateur pulmonaire spécifique !",
        "Mesures générales : limitation efforts intenses/syncopes, contraception efficace chez femme jeune (GROSSESSE FORMELLEMENT CONTRE-INDIQUÉE = mortalité maternelle 25–50% !!), oxygénothérapie si hypoxie, diurétiques si surcharge droite",
        "Groupe 1 (HTAP) : Inhibiteurs calciques forte dose uniquement si test de vasoréactivité aigu positif (< 10% des patients !) ; sinon bithérapie ou trithérapie ciblée d'emblée (Antagonistes récepteurs endothéline + IPDE5 + analogues prostacycline/Sotatercept)",
        "Groupe 4 (HTP-TEC) : ANTICOAGULATION CURATIVE À VIE + ENDARTÉRIECTOMIE PULMONAIRE CHIRURGICALE (ou angioplastie/Riociguat si inopérable)",
        "Groupes 2 et 3 : Traiter l'insuffisance cardiaque gauche ou la maladie respiratoire (O2) — les vasodilatateurs de l'HTAP sont généralement contre-indiqués ou délétères dans le Groupe 2 !"
      ),
      differentialDiagnoses = listOf(
        "Asthme / Déconditionnement / Anémie (causes fréquentes d'errance devant une dyspnée isolée à poumons normaux !)",
        "Embolie pulmonaire aiguë, Insuffisance cardiaque gauche à FEVG préservée (PAPO > 15 au KT droit), Péricardite constrictive"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Syncopes d'effort et sclérodactylie",
      clinicalCaseScenario = "Mme N., 44 ans (cas fictif pédagogique), suivie pour un phénomène de Raynaud sévère avec sclérodactylie débutante, consulte pour une dyspnée d'effort d'aggravation progressive depuis 1 an et deux lipothymies récentes en montant les escaliers. Auscultation pulmonaire normale, mais éclat net de B2 au foyer pulmonaire. EFR : volumes normaux, DLCO abaissée à 42% ! ETT : VD dilaté, PAPS estimée à 64 mmHg, FEVG 65% normale.",
      clinicalCaseCorrection = "1) Forte suspicion d'HTAP PRÉCAPILLAIRE (Groupe 1) compliquant une SCLÉRODERMIE SYSTÉMIQUE LIMITÉE (Raynaud + sclérodactylie + chute isolée de la DLCO + éclat de B2 + syncopes d'effort = critère de haute gravité !).\n" +
        "2) Examens indispensables en centre expert : CATHÉTÉRISME CARDIAQUE DROIT (pour confirmer PAPm > 20, PAPO <= 15, RVP > 2 UW), Scintigraphie pulmonaire V/Q (éliminer Groupe 4 HTP-TEC), auto-anticorps anti-centromères, BNP, TM6.\n" +
        "3) Contre-indication absolue à la grossesse et aux efforts intenses, mise en route d'une bithérapie vasodilatatrice spécifique (ex: ARE + IPDE5).",
      keyPoints = listOf(
        "Dyspnée isolée à poumons clairs + éclat de B2 pulmonaire + syncopes d'effort = Penser HTAP -> ETT puis Cathétérisme Cardiaque Droit (PAPm > 20 mmHg)",
        "TOUJOURS faire une Scintigraphie pulmonaire V/Q devant toute HTP précapillaire (ne pas rater une HTP-TEC du Groupe 4 curable par chirurgie !)",
        "Contre-indication absolue de la grossesse dans l'HTAP ; ne pas donner de traitements spécifiques Groupe 1 dans une HTP du Groupe 2 (cardiopathie gauche) !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Les 5 Groupes cliniques OMS d'Hypertension Pulmonaire",
          headers = listOf("Groupe", "Dénomination", "Profil KT droit", "Traitement clé"),
          rows = listOf(
            listOf("Groupe 1", "HTAP vraie (idiopathique, sclérodermie, VIH)", "Précapillaire (PAPO <= 15)", "Vasodilatateurs spécifiques (ARE, IPDE5, prostacyclines)"),
            listOf("Groupe 2", "Cardiopathies gauches (IC, valves) — n°1 !", "Post-capillaire (PAPO > 15)", "Traitement de l'IC gauche (PAS de vasodilatateurs HTAP !)"),
            listOf("Groupe 3", "Maladies respiratoires hypoxiantes (BPCO, fibrose)", "Précapillaire", "Oxygénothérapie + traitement pneumopathie"),
            listOf("Groupe 4", "HTP thromboembolique chronique (HTP-TEC)", "Précapillaire", "Anticoagulation à vie + Endartériectomie chirurgicale")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_18_atelectasie",
      title = "18. Atélectasie Pulmonaire",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Collapsus alvéolaire avec perte de volume — Signe radiologique clé d'attraction du médiastin et recherche d'obstacle",
      definition = "Affaissement (collapsus) et perte de volume d'un segment, d'un lobe ou d'un poumon entier par résorption de l'air alvéolaire secondaire à une obstruction bronchique complète (atélectasie obstructive) ou à une compression extrinsèque.",
      epidemiology = "Très fréquente en post-opératoire de chirurgie thoracique ou abdominale haute (bouchon muqueux !), en réanimation, chez le fumeur (cancer bronchique !), et chez le petit enfant (corps étranger inhalé !).",
      etiologiesAndRiskFactors = listOf(
        "OBSTRUCTION BRONCHIQUE INTRALUMINALE : Cancer bronchopulmonaire obstructif (cause majeure à éliminer chez l'adulte fumeur !!), Bouchon muqueux épais (post-opératoire, asthmatique, BPCO, encombrement), Corps étranger inhalé (enfant 1–4 ans ou trouble de déglutition !), Tumeur carcinoïde, Sténose tuberculeuse",
        "COMPRESSION EXTRINSÈQUE / PASSIVE : Épanchement pleural abondant ou pneumothorax comprimant le parenchyme adjacent, adénopathie hilaire compressive",
        "RÉTRACTILE / ADHÉSIVE : Fibrose post-radique ou séquelle tuberculeuse, déficit en surfactant"
      ),
      physiopathology = "En aval d'une bronche totalement obstruée, l'air piégé est progressivement réabsorbé par le sang capillaire pulmonaire tandis que la pression négative pleurale attire les structures mobiles voisines (coupole, scissures, médiastin, trachée) VERS le territoire collapsé. Le sang continuant de perfuser la zone non ventilée crée un effet shunt hypoxémiant.",
      classificationAndPathology = "Atélectasie linéaire/en bande (sous-segmentaire fréquente post-opératoire ou embolie), segmentaire, lobaire (lobe supérieur droit donnant le classique « S inversé de Golden » si masse hilaire !), ou pulmonaire totale (« poumon blanc rétractile »).",
      clinicalSigns = "Dépend de la brutalité et de l'étendue : parfois asymptomatique si segmentaire ; dyspnée brutale, douleur thoracique et fièvre si lobaire/totale aiguë post-opératoire. EXAMEN PHYSIQUE (SYNDROME DE CONDENSATION RÉTRACTILE) : diminution d'ampliation et rétraction de l'hémithorax atteint, MATITÉ franche, ABOLITION DES VIBRATIONS VOCALES ET DU MURMURE VÉSICULAIRE (car bronche bouchée ne transmettant ni la voix ni le souffle tubaire !!), et déviation du choc de pointe/trachée VERS le côté malade.",
      paraclinicalExams = listOf(
        "Radiographie thoracique (face + profil) : OPACITÉ DENSE HOMOGÈNE TRIANGULAIRE À SOMMET HILAIRE SANS BRONCHOGRAMME AÉRIQUE, accompagnée de SIGNES DIRECTS (déplacement des scissures vers l'opacité) ET INDIRECTS DE PERTE DE VOLUME (ATTRACTION DE LA TRACHÉE ET DU MÉDIASTIN VERS LE CÔTÉ MALADE, surélévation de la coupole diaphragmatique homolatérale, pincement des espaces intercostaux !)",
        "Scanner thoracique : précise le siège exact de l'obstruction endobronchique et recherche une masse tumorale hilaire",
        "FIBROSCOPIE BRONCHIQUE SOUPLE = EXAMEN CLÉ DOUBLE (diagnostique ET thérapeutique) : aspire un bouchon muqueux ou visualise/biopsie un cancer bronchique ou retire un corps étranger !"
      ),
      diagnosticCriteria = listOf(
        "Opacité systématisée sans bronchogramme aérique + Signes nets de perte de volume (attraction des scissures, de la coupole et du médiastin VERS le côté opaque)"
      ),
      clinicalForms = listOf(
        "« Poumon blanc » par atélectasie complète d'un poumon (obstruction bronche souche) : hémithorax tout blanc AVEC médiastin attiré du côté blanc (vs pleurésie massive = médiastin refoulé du côté sain !!)",
        "Syndrome du lobe moyen (atélectasie chronique récidivante du lobe moyen par adénopathies ou dilatation bronchique)"
      ),
      evolutionAndPrognosis = "Réexpansion immédiate après aspiration d'un bouchon muqueux ou kinésithérapie précoce. Si l'obstacle persiste : pneumonie rétrosténotique récidivante, abcédation ou bronchectasies séquellaires.",
      managementPrinciples = listOf(
        "ATÉLECTASIE AIGUË POST-OPÉRATOIRE (bouchon muqueux) : Kinésithérapie respiratoire active + aspiration bronchique par fibroscopie en urgence si lobe/poumon entier hypoxémiant !",
        "ATÉLECTASIE CHEZ LE FUMEUR / NON POST-OPÉRATOIRE : FIBROSCOPIE BRONCHIQUE + BIOPSIES SYSTÉMATIQUES pour éliminer un cancer bronchique !",
        "CORPS ÉTRANGER INHALÉ : Bronchoscopie d'extraction (rigide ou souple spécialisée)"
      ),
      differentialDiagnoses = listOf(
        "Pleurésie abondante (« poumon blanc » MAIS médiastin REFOULÉ vers le côté sain opposé !)",
        "Pneumonie franche lobaire (pas de perte de volume, scissures en place, BRONCHOGRAMME AÉRIQUE visible, vibrations vocales augmentées + souffle tubaire !)",
        "Aplasie/agénésie pulmonaire ou séquelle de pneumonectomie (interrogatoire !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Poumon blanc attractif après chirurgie abdominale",
      clinicalCaseScenario = "M. O., 64 ans (cas fictif pédagogique), fumeur 30 PA, est à J2 d'une colectomie sous anesthésie générale. Il devient dyspnéique avec SpO2 87% AA et T° 38.4°C. Auscultation : silence complet de tout l'hémithorax gauche avec matité. Radio thoracique au lit : opacité totale de l'hémithorax gauche avec DÉVIATION NETTE DE LA TRACHÉE ET DU CŒUR VERS LA GAUCHE.",
      clinicalCaseCorrection = "1) ATÉLECTASIE PULMONAIRE TOTALE GAUCHE (« poumon blanc attractif » par obstruction complète de la bronche souche gauche !).\n" +
        "2) Ce n'est PAS une pleurésie massive car la trachée et le médiastin sont ATTIRÉS vers le côté opaque (et non refoulés vers la droite).\n" +
        "3) Cause immédiate la plus probable à J2 post-opératoire : gros BOUCHON MUQUEUX obstructif -> FIBRO-ASPIRATION BRONCHIQUE EN URGENCE (et profiter de la fibroscopie chez ce fumeur à 30 PA pour inspecter la muqueuse et exclure toute lésion tumorale sous-jacente !) + kinésithérapie respiratoire.",
      keyPoints = listOf(
        "Opposition radio fondamentale d'un poumon blanc : Atélectasie ATTIRE le médiastin ; Pleurésie REFOULE le médiastin !",
        "Opacité triangulaire rétractile sans bronchogramme + vibration vocale et MV abolis = Atélectasie",
        "Devant toute atélectasie non évidemment liée à un simple bouchon post-opératoire résolutif : FIBROSCOPIE BRONCHIQUE obligatoire (cancer / corps étranger)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Distinction radioclinique : Atélectasie vs Pneumonie vs Pleurésie",
          headers = listOf("Critère", "Atélectasie obstructive", "Pneumonie alvéolaire", "Pleurésie abondante"),
          rows = listOf(
            listOf("Volume du territoire", "DIMINUÉ (rétractile)", "Normal (non rétractile)", "AUGMENTÉ (expansif)"),
            listOf("Position médiastin/trachée", "ATTIRÉS vers l'opacité", "En place (médian)", "REFOULÉS du côté sain"),
            listOf("Bronchogramme aérique", "Absent", "PRÉSENT", "Absent"),
            listOf("Vibrations vocales / Auscultation", "Abolies / Silence", "Augmentées / Souffle tubaire + crépitants", "Abolies / Silence")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_19_syndrome_pleural",
      title = "19. Synthèse : Le Syndrome Pleural (Liquidien, Gazeux & Mixte)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Maîtrise complète de la sémiologie pleurale au lit du malade — Palpation, Percussion, Auscultation & Imagerie",
      definition = "Ensemble des manifestations cliniques et radiologiques traduisant l'interposition anormale de liquide (pleurésie, hémothorax, pyothorax), d'air (pneumothorax) ou des deux (hydropneumothorax) dans l'espace pleural, ou un épaississement pleural séquellaire.",
      epidemiology = "Compétence transversale quotidienne indispensable en médecine interne, urgences, pneumologie et chirurgie thoracique : savoir reconnaître un syndrome pleural au lit du patient évite erreurs diagnostiques et gestes dangereux.",
      etiologiesAndRiskFactors = listOf(
        "Syndrome pleural LIQUIDIEN : Transsudats (Insuffisance cardiaque, Cirrhose, Néphrotique) vs Exsudats (Cancer/Mésothéliome, Infection parapneumonique/Empyème, Tuberculose, EP) vs Sang (Hémothorax)",
        "Syndrome pleural GAZEUX : Pneumothorax spontané primaire (blebs chez sujet jeune mince fumeur), Secondaire (BPCO, emphysème, asthme), Traumatique ou Iatrogène",
        "Syndrome pleural MIXTE : Hydropneumothorax / Pyopneumothorax / Hémopneumothorax (trauma, rupture abcès pulmonaire ou fistule œsophagienne/bronchopleurale, ponction compliquée !)"
      ),
      physiopathology = "La plèvre normale maintient une pression négative (-3 à -5 cmH2O) maintenant le poumon collé à la paroi. Toute interposition d'air ou de liquide supprime la transmission des vibrations vocales et des bruits respiratoires vers le stéthoscope, tout en créant un écran soit mat (liquide) soit tympanique (air) à la percussion.",
      classificationAndPathology = "Trois tableaux physiques purs + un tableau mixte : Liquidien pur, Gazeux pur, Mixte hydro-aérique, et Pachypleurite (épaississement pleural fibreux séquellaire fixe sans liquide libre).",
      clinicalSigns = "Douleur thoracique de type PLEURAL (latérale basale en point de côté, augmentée à l'inspiration profonde, à la toux et parfois irradiant vers l'épaule si atteinte diaphragmatique), dyspnée variable, toux sèche quinteuse aux changements de position. INSPECTER l'immobilité/distension relative d'un hémithorax. PALPER : ABOLITION DES VIBRATIONS VOCALES dans TOUS les syndromes pleuraux ! PERCUTER : différence décisive = MATITÉ franche hydrique déclive (liquide) vs TYMPANISME sonore (air), ou matité basale surmontée de tympanisme (mixte !). AUSCULTATION : ABOLITION DU MURMURE VÉSICULAIRE (parfois frottement pleural sec rugueux « en cuir neuf » au tout début ou décours d'une pleurésie sèche !).",
      paraclinicalExams = listOf(
        "Radiographie thoracique debout (face) : Liquidien = opacité basale concave en haut (Damoiseau) ; Gazeux = hyperclarté périphérique totalement avasculaire + ligne pleurale viscérale ; Mixte = NIVEAU HYDRO-AÉRIQUE STRICTEMENT HORIZONTAL traversant tout l'hémithorax (signature de la présence simultanée d'air ET de liquide en plèvre libre !)",
        "Échographie pleurale au lit (ultra-rapide et sensible : épanchement anéchogène/échogène, cloisonnements, ou « signe du code-barres » sans glissement dans le pneumothorax)",
        "Ponction pleurale exploratrice (si liquide) + Scanner thoracique si complexe ou doute avec bulle d'emphysème/abcès"
      ),
      diagnosticCriteria = listOf(
        "Abolition des vibrations vocales + Abolition du murmure vésiculaire + Percussion (Matité = liquide / Tympanisme = air) confirmées par radio/échographie"
      ),
      clinicalForms = listOf(
        "Pleurésie sèche (frottement pleural rude aux deux temps respiratoires disparaissant en apnée — à la différence du frottement péricardique qui persiste en apnée !!)",
        "Épanchement cloisonné / scissural (« pseudo-tumeur fantôme » dans la petite scissure lors d'une poussée d'insuffisance cardiaque, disparaissant sous diurétiques !)"
      ),
      evolutionAndPrognosis = "Directement lié à la cause et à la tolérance aiguë (choc obstructif si pneumothorax compressif ou épanchement massif ; choc hémorragique si hémothorax ; fibrose pleurale séquellaire / fibrothorax si empyème ou sang non drainé).",
      managementPrinciples = listOf(
        "Toujours évaluer d'abord la TOLÉRANCE (hémodynamique, turgescence jugulaire, hypoxémie) avant toute attente d'imagerie",
        "Ne JAMAIS ponctionner/drainer à l'aveugle sans repérage clinique + échographique idéal, et ne jamais évacuer > 1 à 1.5 L d'un coup (risque d'OAP a vacuo)",
        "Respecter la séparation stricte transsudat (traitement médical) vs exsudat (bilan étiologique) vs empyème/hémothorax/pneumothorax suffocant (drainage urgent)"
      ),
      differentialDiagnoses = listOf(
        "Condensation pulmonaire (vibrations vocales AUGMENTÉES + crépitants/souffle tubaire -> jamais d'abolition des VV !)",
        "Frottement péricardique (persiste quand le patient bloque sa respiration en apnée, contrairement au frottement pleural qui s'arrête en apnée !)",
        "Abcès pulmonaire (niveau hydro-aérique arrondi intraparenchymateux vs niveau hydro-aérique pleural étendu de l'hydropneumothorax)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Piège de l'apnée et du niveau horizontal thoracique",
      clinicalCaseScenario = "M. F., 58 ans (cas fictif pédagogique), suivi pour cirrhose, a subi une ponction pleurale droite en ville deux heures avant son arrivée aux urgences pour aggravation brutale de sa dyspnée. À l'examen de l'hémithorax droit : abolition des vibrations vocales et du murmure vésiculaire sur toute la hauteur, avec un TYMPANISME sous la clavicule et une MATITÉ franche sous l'omoplate. Radio debout : opacité basale droite surmontée d'une grande hyperclarté périphérique séparées par une ligne STRICTEMENT HORIZONTALE.",
      clinicalCaseCorrection = "1) HYDROPNEUMOTHORAX DROIT IATROGÈNE compliquant la ponction pleurale (introduction accidentelle d'air lors de la ponction d'un hydrothorax cirrhotique).\n" +
        "2) Sémiologie typique mixte : vibrations vocales et MV abolis + tympanisme en haut (air) + matité en bas (liquide) + ligne strictement horizontale sur le cliché debout (disparition de la courbe parabolique de Damoiseau !).\n" +
        "3) Évaluer tolérance (SpO2, PA, FC) : si bien toléré et minime -> surveillance + O2 ; si grand décollement mal toléré -> exsufflation ou drainage pleural aspiratif.",
      keyPoints = listOf(
        "Point commun absolu de TOUT syndrome pleural : ABOLITION des vibrations vocales et du murmure vésiculaire",
        "Percussion & Radio font le tri : Matité + Damoiseau = Liquide ; Tympanisme + hyperclarté avasculaire = Air ; Ligne strictement horizontale = Mixte !",
        "Frottement pleural s'arrête en apnée (vs Frottement péricardique qui persiste en apnée !)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Tableau synthétique complet des syndromes pleuraux",
          headers = listOf("Type", "Inspection", "Palpation (VV)", "Percussion", "Auscultation", "Image radio clé"),
          rows = listOf(
            listOf("Liquidien (Pleurésie)", "Immobilité basale", "Abolies", "Matité hydrique déclive", "MV aboli (+ souffle doux haut)", "Opacité déclive courbe de Damoiseau"),
            listOf("Gazeux (Pneumothorax)", "Distension / immobilité", "Abolies", "Tympanisme", "Silence (MV aboli)", "Hyperclarté avasculaire + bord viscéral"),
            listOf("Mixte (Hydropneumothorax)", "Immobilité", "Abolies", "Tympanisme haut + Matité bas", "MV aboli", "Niveau hydro-aérique STRICTEMENT HORIZONTAL"),
            listOf("Pleurésie sèche (début)", "Normale", "Normales/diminuées", "Normale", "Frottement « cuir neuf » (disparaît en apnée)", "Normale ou comblement minime")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_20_syndrome_condensation",
      title = "20. Synthèse : Le Syndrome de Condensation Pulmonaire",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Remplacement de l'air alvéolaire — Distinction capitale Non Rétractile (Pneumonie) vs Rétractile (Atélectasie)",
      definition = "Ensemble des signes physiques et radiologiques résultant du comblement des alvéoles pulmonaires (habituellement remplies d'air) par un liquide inflammatoire (pus, œdème, sang), des cellules tumorales ou par collapsus alvéolaire.",
      epidemiology = "Syndrome sémiologique le plus fréquent en pathologie pulmonaire hospitalière : son identification précise permet d'orienter dès le lit du patient entre pneumonie infectieuse, infarctus pulmonaire, œdème ou cancer/atélectasie.",
      etiologiesAndRiskFactors = listOf(
        "CONDENSATION ALVÉOLAIRE NON RÉTRACTILE (à bronche PERMÉABLE !) : Pneumopathie infectieuse bactérienne (PFLA à Pneumocoque ++), Infarctus pulmonaire (Embolie pulmonaire), Œdème pulmonaire localisé ou diffus, Hémorragie alvéolaire, Adénocarcinome lépidique",
        "CONDENSATION RÉTRACTILE (à bronche OBSTRUÉE !) : Atélectasie par bouchon muqueux, cancer bronchique proximal, corps étranger",
        "CONDENSATION AVEC EXCAVATION (nécrose centrale communiquant avec une bronche) : Abcès pulmonaire, Caverne tuberculeuse, Cancer épidermoïde nécrosé"
      ),
      physiopathology = "Un parenchyme pulmonaire normal aéré filtre et amortit les sons laryngés ; lorsqu'il devient solide/liquide tout en gardant ses bronches ouvertes (condensation non rétractile), il devient un EXCELLENT CONDUCTEUR acoustique des vibrations vocales et des bruits trachéo-bronchiques (= augmentation des VV, souffle tubaire, bronchophonie) ! Si la bronche est bouchée (atélectasie), la conduction cesse (= abolition VV et MV !).",
      classificationAndPathology = "Trois sous-syndromes sémiologiques précis au lit du patient : 1) Condensation non rétractile (bronche perméable) ; 2) Condensation rétractile (bronche bouchée) ; 3) Syndrome cavitaire (cavité aérée dans parenchyme condensé).",
      clinicalSigns = "CONDENSATION NON RÉTRACTILE (PFLA) : 1) Palpation : AUGMENTATION nette des vibrations vocales (« dites 33 ») en regard du foyer ; 2) Percussion : MATITÉ OU SUBMATITÉ NON DÉCLIVE (ne change pas avec la position, limite scissurale nette !) ; 3) Auscultation : diminution du murmure vésiculaire normal REMPLACÉ PAR UN SOUFFLE TUBAIRE (rude, à prédominance inspiratoire, comparable au bruit en soufflant dans un tube creux) ET UNE COURONNE DE RÂLES CRÉPITANTS FINS EN FOYER en fin d'inspiration + bronchophonie/pectoriloquie ! SYNDROME CAVITAIRE (rare) : souffle caverneux + gargouillements.",
      paraclinicalExams = listOf(
        "Radiographie thoracique (face + profil) : opacité dense alvéolaire systématisée (respectant les limites lobaires/segmentaires sans attraction des scissures ni du médiastin) traversée par un BRONCHOGRAMME AÉRIQUE (clartés bronchiques tubulaires branchées visibles au sein du poumon opaque = signature de condensation à bronche perméable !!)",
        "Scanner thoracique si doute diagnostique (atypie, suspicion EP, abcès débutant, résistance aux antibiotiques à 72h)",
        "Bilan étiologique ciblé selon contexte : NFS/CRP/Hémocultures/Antigénuries (si fièvre), Angioscanner/D-Dimères (si suspicion d'infarctus pulmonaire sur EP !), Fibroscopie bronchique (si atélectasie ou fumeur à risque néoplasique)"
      ),
      diagnosticCriteria = listOf(
        "Condensation non rétractile : Augmentation VV + Matité fixe + Crépitants en foyer / Souffle tubaire + Opacité alvéolaire avec bronchogramme aérique à la radio"
      ),
      clinicalForms = listOf(
        "Infarctus pulmonaire d'Embolie Pulmonaire (syndrome de condensation fébrile tardive à J2–J4 avec hémoptysie et opacité triangulaire périphérique à base pleurale sans grand bronchogramme = bosse de Hampton -> ne pas confondre avec une simple pneumonie !!)",
        "Condensation bilatérale diffuse (OAP cardiogénique ou SDRA)"
      ),
      evolutionAndPrognosis = "Résolution complète clinique en quelques jours et radiologique en 4 à 6 semaines pour une pneumonie banale (attention au retard radiologique chez le sujet âgé !). Toute condensation qui ne disparaît pas au contrôle à 6 semaines chez un fumeur impose de rechercher un cancer bronchique.",
      managementPrinciples = listOf(
        "Ne JAMAIS présumer « condensation = forcément pneumonie » : vérifier toujours les diagnostics d'infarctus pulmonaire (EP), de néoplasie sous-jacente et d'atélectasie !",
        "Traiter la cause : antibiothérapie probabiliste précoce si PFLA, fibro-aspiration si atélectasie, anticoagulation si infarctus d'EP",
        "Contrôle radiologique à distance (4–8 semaines) obligatoire chez tout patient > 50 ans ou tabagique après une pneumopathie"
      ),
      differentialDiagnoses = listOf(
        "Épanchement pleural liquidien (VV ABOLIES et matité DÉCLIVE avec courbe de Damoiseau — parfois associés en épanchement parapneumonique !)",
        "Atélectasie rétractile (VV ABOLIES + médiastin attiré + pas de bronchogramme)",
        "Fibrose pulmonaire diffuse (râles crépitants secs bilatéraux des bases « velcro » sans syndrome infectieux ni opacité lobaire franche)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Pourquoi l'acoustique pulmonaire distingue tout",
      clinicalCaseScenario = "Deux patients fébriles à 38.8°C ont une matité de la base droite. Patient A : vibrations vocales NETTEMENT AUGMENTÉES à la paume de la main droite, crépitants secs en fin d'inspiration et souffle rude central ; radio avec opacité triangulaire systématisée contenant un bronchogramme aérique net. Patient B : vibrations vocales TOTALEMENT ABOLIES à droite, silence auscultatoire complet, matité hydrique déclive remontant vers l'aisselle ; radio avec opacité homogène effaçant le cul-de-sac droit à limite concave en haut.",
      clinicalCaseCorrection = "1) Patient A : SYNDROME DE CONDENSATION ALVÉOLAIRE NON RÉTRACTILE (bronche ouverte transmettant les vibrations vocales et donnant un bronchogramme aérique) -> PFLA probable.\n" +
        "2) Patient B : SYNDROME D'ÉPANCHEMENT PLEURAL LIQUIDIEN (le liquide entre poumon et paroi bloque totalement les vibrations vocales et le murmure vésiculaire) -> Pleurésie nécessitant une échographie + ponction pleurale exploratrice.\n" +
        "3) Ce cas illustre la valeur décisive de la palpation des vibrations vocales (« 33 ») et du bronchogramme aérique radiologique au lit du patient.",
      keyPoints = listOf(
        "Condensation NON rétractile (bronche ouverte) = VV AUGMENTÉES + Matité fixe + Souffle tubaire + Crépitants + Bronchogramme aérique",
        "Condensation RÉTRACTILE (Atélectasie, bronche bouchée) = VV ABOLIES + Matité + Silence + Médiastin ATTIRÉ",
        "Toute condensation qui traîne > 6 semaines chez un fumeur = Radio/TDM contrôle + Fibroscopie (éliminer cancer bronchique !)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Comparaison acoustique complète des syndromes parenchymateux et pleuraux",
          headers = listOf("Syndrome", "Bronche", "Vibrations vocales", "Bruits surajoutés / Souffle", "Médiastin / Radio"),
          rows = listOf(
            listOf("Condensation non rétractile (PFLA)", "Ouverte", "AUGMENTÉES", "Crépitants + Souffle tubaire", "Médian + Bronchogramme aérique"),
            listOf("Condensation rétractile (Atélectasie)", "Bouchée", "ABOLIES", "Aucun (silence)", "ATTIRÉ vers le foyer, pas de bronchogramme"),
            listOf("Épanchement pleural liquidien", "Normale (comprimée)", "ABOLIES", "Souffle pleurétique doux au sommet (rare)", "REFOULÉ si massif, courbe de Damoiseau"),
            listOf("Syndrome cavitaire (Caverne/Abcès)", "Ouverte dans cavité", "Augmentées", "Souffle caverneux + gargouillements", "Image cavitaire (+/- niveau hydro-aérique)")
          )
        )
      )
    )
  )
}
