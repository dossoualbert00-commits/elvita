package com.example.data.model.courses

import com.example.data.model.SemiologyCategory

object PleuroPulmonaryCoursesPart1A {
  val courses: List<MedicalCourse> = listOf(
    MedicalCourse(
      id = "course_resp_01_ep",
      title = "1. Embolie Pulmonaire (EP)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Oblitération aiguë du lit artériel pulmonaire — Urgence cardio-respiratoire",
      definition = "Obstruction brutale, partielle ou totale, du tronc ou d'une branche de l'artère pulmonaire par un embole, le plus souvent fibrino-cruorique issu d'une thrombose veineuse profonde (TVP) des membres inférieurs ou pelviennes.",
      epidemiology = "3e cause de mortalité cardiovasculaire après l'infarctus et l'AVC. Incidence annuelle ~60 à 100/100 000 habitants, augmentant fortement après 60 ans, en milieu chirurgical, oncologique et obstétrical.",
      etiologiesAndRiskFactors = listOf(
        "Triade de Virchow : Stase veineuse + Lésion endothéliale + Hypercoagulabilité",
        "Facteurs majeurs (OR > 10) : Chirurgie orthopédique majeure/hanche-genou, polytraumatisme, immobilisation prolongée, cancer actif, antécédent personnel de MTEV",
        "Facteurs modérés/faibles : Contraception œstroprogestative, grossesse et post-partum, voyage long courrier, obésité, thrombophilie (Facteur V Leiden, SAPL, déficit AT/PC/PS)"
      ),
      physiopathology = "L'obstruction artérielle pulmonaire augmente brutalement la post-charge du ventricule droit (HTAP aiguë) -> dilatation et dysfonction VD -> bombement septal vers le VG réduisant son remplissage -> baisse du débit cardiaque et choc obstructif. Simultanément : effet espace mort (zones ventilées non perfusées), hypoxémie par effet shunt et hypocapnie réflexe.",
      classificationAndPathology = "Stratification du risque pronostique ESC (mortalité à 30 jours) : 1) EP à HAUT RISQUE (état de choc / PAS < 90 mmHg — 5% des cas) ; 2) EP à RISQUE INTERMÉDIAIRE (sPESI >= 1 : intermédiaire-haut si dysfonction VD à l'écho/TDM ET troponine élevée ; intermédiaire-bas si un seul ou aucun des deux) ; 3) EP à BAS RISQUE (sPESI = 0 sans dysfonction VD ni élévation de troponine).",
      clinicalSigns = "Dyspnée brutale inexpliquée (80%), douleur thoracique basi-thoracique de type pleural (« point de côté », 50%), tachypnée > 20/min, tachycardie (> 100/min), hémoptysie tardive (infarctus pulmonaire), fièvre modérée (38°C), syncope ou malaise lipothymique inauguraux (forme grave !). Signes de TVP associés dans 30–50% des cas (gros mollet rouge chaud douloureux). L'auscultation pulmonaire est classiquement NORMALE au début !",
      paraclinicalExams = listOf(
        "Scores de probabilité clinique PRÉ-TEST OBLIGATOIRES avant tout examen : Score de Genève révisé ou Score de Wells",
        "D-Dimères (ELISA haute sensibilité) : UNIQUEMENT si probabilité faible ou intermédiaire (VPN quasi 100% si négatif < 500 µg/L ou < Âge × 10 après 50 ans ! Ne PAS doser si probabilité forte !)",
        "Angioscanner thoracique spiralé (examen de référence : lacune endoluminale dans les artères pulmonaires + rapport VD/VG) ; ou Scintigraphie pulmonaire V/Q si insuffisance rénale sévère/allergie grave à l'iode/grossesse",
        "ECG (tachycardie sinusale ++, aspect S1Q3, BBD récent, ondes T négatives de V1 à V4), Gaz du sang (effet shunt : hypoxémie + hypocapnie), Écho-Doppler veineux des membres inférieurs, Échocardiographie (cœur pulmonaire aigu), Troponine & BNP/NT-proBNP"
      ),
      diagnosticCriteria = listOf(
        "Probabilité clinique faible/intermédiaire + D-Dimères négatifs = EP ÉLIMINÉE",
        "Probabilité forte OU D-Dimères positifs -> Angioscanner thoracique positif (défaut d'opacification artériel pulmonaire) = EP CONFIRMÉE",
        "TVP proximale confirmée à l'écho-Doppler MI chez un patient dyspnéique suspect d'EP suffit parfois à poser l'indication d'anticoagulation"
      ),
      clinicalForms = listOf(
        "EP massive à haut risque (choc obstructif, syncope, ACR sans autre cause -> thrombolyse systémique !)",
        "Infarctus pulmonaire (douleur pleurale + hémoptysie + fébricule + opacité triangulaire périphérique de Hampton)",
        "EP de la femme enceinte (D-Dimères souvent faussement positifs -> écho-Doppler MI d'abord, puis scintigraphie perfusion ou angioscanner basse dose)"
      ),
      evolutionAndPrognosis = "Mortalité < 2% si traitée précocement à bas risque, > 15–30% dans les formes avec choc ou dysfonction VD sévère. Complications à long terme : récidive thromboembolique, hypertension pulmonaire thromboembolique chronique (HTP-TEC, ~2–4%).",
      managementPrinciples = listOf(
        "Anticoagulation curative IMMÉDIATE dès la suspicion clinique si probabilité forte/intermédiaire sans attendre l'imagerie (sauf contre-indication hémorragique majeure)",
        "EP à haut risque (choc) : Réanimation + Héparine non fractionnée IVSE + Thrombolyse systémique (Altéplase)",
        "EP sans choc : AOD en 1ère intention (Rivaroxaban, Apixaban) ou HBPM/Fondaparinux relayés par AVK ; durée >= 3 mois (prolongée si facteur non provoqué ou cancer actif)",
        "Ambulatoire envisageable si EP à bas risque (sPESI = 0 ou critères Hestia négatifs) et contexte social adapté"
      ),
      differentialDiagnoses = listOf(
        "Syndrome coronarien aigu et Dissection aortique (doivent être éliminés par ECG/angioscanner)",
        "Pneumopathie aiguë infectieuse, Péricardite, Pneumothorax, Décompensation de BPCO"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Dyspnée aiguë post-opératoire",
      clinicalCaseScenario = "Mme L., 62 ans (cas fictif pédagogique), sans antécédent cardiaque, consulte aux urgences 10 jours après une pose de prothèse totale de hanche (arrêt prématuré de son HBPM préventive) pour une dyspnée d'apparition brutale accompagnée d'un point de côté basal droit. Constantes : PA 108/70 mmHg, FC 118/min, FR 26/min, SpO2 89% AA, T° 37.7°C. Auscultation pulmonaire libre. Mollet gauche tendu, augmenté de 3 cm. ECG : tachycardie sinusale à 118/min et ondes T négatives en V1–V3.",
      clinicalCaseCorrection = "1) Probabilité clinique FORTE (chirurgie hanche récente, tachycardie > 95, signes de TVP) -> NE PAS faire de D-Dimères, réaliser directement un ANGIOSCANNER THORACIQUE en urgence.\n" +
        "2) Anticoagulation curative immédiate sans attendre le scanner.\n" +
        "3) Angioscanner montre une EP bilatérale proximale avec VD/VG > 1 + Troponine augmentée -> EP à risque INTERMÉDIAIRE-HAUT (sPESI >= 1 + dysfonction VD + cytolyse myocardique) imposant une surveillance hospitalière rapprochée (USIC/USC).",
      keyPoints = listOf(
        "D-Dimères = UNIQUEMENT en probabilité faible ou intermédiaire (jamais si probabilité forte !)",
        "Examen physique clé : auscultation pulmonaire souvent NORMALE + rechercher TVP + syncope = signe de gravité",
        "Anticoagulation curative immédiate dès probabilité forte/intermédiaire ; Thrombolyse réservée aux formes avec état de choc"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Stratification pronostique ESC de l'Embolie Pulmonaire",
          headers = listOf("Niveau de risque", "Choc / Hypotension", "sPESI >= 1", "Dysfonction VD (Écho/TDM)", "Troponine"),
          rows = listOf(
            listOf("Haut risque", "OUI", "OUI", "OUI", "+"),
            listOf("Intermédiaire-haut", "Non", "OUI", "OUI (les 2)", "+ (les 2)"),
            listOf("Intermédiaire-bas", "Non", "OUI", "1 seul ou aucun", "1 seul ou aucun"),
            listOf("Bas risque", "Non", "Non (0)", "Non", "Négative")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_02_pneumonie_bact",
      title = "2. Pneumonies Bactériennes (PFLA & Pneumopathies Atypiques)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Infection aiguë du parenchyme alvéolaire — Distinction Pneumocoque vs Germes atypiques",
      definition = "Infection aiguë du parenchyme pulmonaire acquise en milieu extrahospitalier (Pneumonie Aiguë Communautaire — PAC) ou en milieu de soins, le plus souvent bactérienne, touchant les espaces alvéolaires et/ou l'interstitium.",
      epidemiology = "1ère cause de mortalité infectieuse dans les pays développés (~400 000 à 600 000 cas/an en France). Streptococcus pneumoniae reste le germe le plus fréquent et le plus grave à tout âge ; Mycoplasma pneumoniae prédomine chez l'adulte jeune.",
      etiologiesAndRiskFactors = listOf(
        "Cocci Gram+ : Streptococcus pneumoniae (Pneumocoque — cause n°1 et la plus sévère !)",
        "Germes atypiques intracellulaires : Mycoplasma pneumoniae, Chlamydophila pneumoniae, Legionella pneumophila (eaux chaudes contaminées, climatisation, sujets fragiles/tabagiques)",
        "Autres bacilles : Haemophilus influenzae (BPCO ++), Staphylococcus aureus (post-grippe, nécrosant PVL+), anaérobies (troubles de déglutition -> pneumopathie d'inhalation !), Klebsiella pneumoniae (alcoolisme chronique)",
        "Facteurs de risque : Âge > 65 ans, BPCO, tabac, éthylisme chronique, diabète, splénectomie/drépanocytose (pneumocoque grave !), immunodépression"
      ),
      physiopathology = "Colonisation microaspirationnelle des voies aériennes supérieures -> dépassement des défenses mucociliaires et macrophages alvéolaires -> exsudat fibrino-leucocytaire remplissant les alvéoles (hépatisation pulmonaire -> syndrome de condensation alvéolaire sans perte de volume).",
      classificationAndPathology = "4 stades anatomopathologiques de la PFLA : engouement (œdème), hépatisation rouge (fibrine + hématies), hépatisation grise (PNN massifs) et résolution. Cliniquement : tableau aigu systématisé (Pneumocoque) vs tableau subaigu interstitiel/extrapulmonaire (atypiques/Légionelle).",
      clinicalSigns = "PFLA (Pneumocoque) : début BRUTAL à la minute, frissons solennels intenses, fièvre élevée >= 39°C d'emblée, point de côté thoracique, toux puis crachats rouillés purulents, herpès labial fréquent ; à l'examen : SYNDROME DE CONDENSATION ALVÉOLAIRE (vibrations vocales augmentées + matité + râles crépitants en foyer + souffle tubaire). Légionellose : fièvre > 39.5°C avec pouls dissocié, signes digestifs (diarrhée) et neurologiques (confusion), hyponatrémie ! Mycoplasma : début progressif, fièvre modérée, toux sèche quinteuse, contexte épidémique familial/collectivité, myringite.",
      paraclinicalExams = listOf(
        "Radiographie thoracique (face + profil) OBLIGATOIRE pour confirmer le diagnostic : opacité alvéolaire dense systématisée avec bronchogramme aérique (PFLA) ou opacités interstitielles/multifocales",
        "Score de gravité CRB-65 en ville (Confusion, FR >= 30, PAS < 90 ou PAD <= 60, Âge >= 65 : 0 = ambulatoire ; >= 1 = discuter hospitalisation)",
        "Si patient hospitalisé : NFS (hyperleucocytose PNN), CRP/PCT, ionogramme (hyponatrémie Légionelle ?), urée/créat, gaz du sang si hypoxie, 2 HÉMOCULTURES + Antigénurie Légionelle (si forme grave/contexte) et Antigénurie Pneumocoque, ECBC si crachat valide",
        "NE PAS attendre la radio ou la biologie pour débuter l'antibiothérapie en urgence !"
      ),
      diagnosticCriteria = listOf(
        "Critères cliniques (fièvre + toux/expectoration + dyspnée/crépitants en foyer) + Confirmation radiologique par opacité parenchymateuse récente"
      ),
      clinicalForms = listOf(
        "Pneumopathie d'inhalation (siège déclive : lobe inférieur droit / segment apical inférieur, haleine fétide, anaérobies -> Amoxicilline-Acide clavulanique !)",
        "Pneumonie à Staphylococcus aureus post-grippale (nécrosante bulleuse grave)",
        "Pneumonie nosocomiale (>= 48h après admission -> Pyocyanique, Entérobactéries, SARM)"
      ),
      evolutionAndPrognosis = "Amélioration rapide de la fièvre (apyrexie en 48–72h) sous antibiothérapie adaptée. Si persistance de fièvre > 72h : rechercher épanchement pleural parapneumonique / empyème (ponction !), abcédation, erreur de germe (atypique ? BK ?), ou obstacle tumoral sous-jacent (scanner à distance chez fumeur > 50 ans !).",
      managementPrinciples = listOf(
        "PFLA adulte sain sans gravité : Amoxicilline 1g x 3/j pendant 5 à 7 jours (cible absolue = Pneumocoque !)",
        "Atypiques (Mycoplasma/Chlamydia) : Macrolide (Azithromycine ou Clarithromycine) ou Doxycycline",
        "PAC avec comorbidités / âgé / grave : Amoxicilline-Acide clavulanique ou C3G (Ceftriaxone) + Macrolide (ou Lévofloxacine) en hospitalisation + O2 + hydratation",
        "Réévaluation clinique IMPÉRATIVE à 48–72h et radiographie thoracique de contrôle à 1–2 mois chez fumeur > 50 ans (éliminer cancer bronchique sous-jacent !)"
      ),
      differentialDiagnoses = listOf(
        "Embolie pulmonaire (infarctus pulmonaire fébrile), Œdème aigu du poumon, Tuberculose pulmonaire (évolution subaiguë, sueurs nocturnes, sommets !), Cancer bronchique surinfecté"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Pneumonie sévère communautaire",
      clinicalCaseScenario = "M. D., 71 ans (cas fictif pédagogique), BPCO légère, présente brutalement hier soir un grand frisson unique suivi d'une fièvre à 39.6°C, d'une douleur basi-thoracique gauche et ce matin d'une confusion modérée. PA 100/65 mmHg, FC 114/min, FR 32/min, SpO2 88% AA. Base gauche : matité, crépitants en foyer et souffle tubaire. Score CRB-65 = 3 (Âge >= 65, FR >= 30, Confusion).",
      clinicalCaseCorrection = "1) Tableau typique de Pneumonie Franche Lobaire Aiguë (PFLA) grave présumée à Pneumocoque (CRB-65 = 3 + hypoxémie sévère).\n" +
        "2) Hospitalisation urgente (évaluation réanimation selon gaz du sang/lactates), O2 pour SpO2 >= 94%, hémocultures + antigénuries + radio thorax.\n" +
        "3) Antibiothérapie IV probabiliste immédiate associant C3G (Ceftriaxone IV) + Macrolide (Spiramycine/Azithromycine) ou Lévofloxacine (couvrant Pneumocoque + Légionelle dans toute PAC grave !).",
      keyPoints = listOf(
        "CRB-65 évalue instantanément le lieu de prise en charge (C: Confusion, R: FR >= 30, B: PAS < 90/PAD <= 60, 65: Âge >= 65 ans)",
        "Pneumocoque = début brutal + crachats rouillés + condensation localisée -> Amoxicilline en 1ère ligne ambulatoire",
        "Si fièvre persiste à 72h sous Amoxicilline : rechercher épanchement pleural (ponction), abcès ou germe atypique ; radio de contrôle à distance chez fumeur !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Comparaison PFLA à Pneumocoque vs Pneumopathie Atypique vs Légionellose",
          headers = listOf("Critères", "Pneumocoque (PFLA)", "Mycoplasma (Atypique)", "Legionella pneumophila"),
          rows = listOf(
            listOf("Début", "Brutal à la minute", "Progressif (3–5 jours)", "Rapide (24–48h)"),
            listOf("Signes clés", "Frisson unique + point de côté + crachat rouillé", "Toux sèche quinteuse + contexte familial", "Fièvre > 39.5°C + diarrhée + confusion + hyponatrémie"),
            listOf("Radio thorax", "Opacité alvéolaire systématisée + bronchogramme", "Opacités interstitielles bilatérales", "Opacités alvéolaires souvent bilatérales"),
            listOf("1er choix ATB", "Amoxicilline", "Macrolide", "Macrolide ou Fluoroquinolone")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_03_pneumonie_virale",
      title = "3. Pneumonies Virales (Grippe, SARS-CoV-2, VRS)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Atteinte interstitielle diffuse — Risque d'orage cytokinique et de surinfection bactérienne",
      definition = "Atteinte inflammatoire aiguë des voies respiratoires distales et du parenchyme pulmonaire (interstitium et alvéoles) causée par un virus respiratoire (Influenza, SARS-CoV-2, VRS, adénovirus, métapneumovirus).",
      epidemiology = "Saisonnalité hivernale marquée pour Grippe et VRS ; endémo-épidémique pour le SARS-CoV-2. Grave chez les sujets âgés, obèses, femmes enceintes, insuffisants cardiaques/respiratoires et immunodéprimés.",
      etiologiesAndRiskFactors = listOf(
        "Virus Influenza A et B (grippe saisonnière)",
        "SARS-CoV-2 (COVID-19) et VRS (Virus Respiratoire Syncytial : bronchiolite du nourrisson et pneumonie du sujet âgé)",
        "Terrain à risque : Âge > 65 ans, grossesse (3e trimestre), obésité morbide, immunodépression, BPCO, cardiopathie chronique"
      ),
      physiopathology = "Lésion directe de l'épithélium bronchique et des pneumocytes de type I/II avec œdème interstitiel (opacités en « verre dépoli »), microthromboses capillaires pulmonaires (fréquentes dans la COVID-19) et possible emballement inflammatoire alvéolaire (SDRA) vers J7–J10. La destruction ciliaire favorise la surinfection bactérienne secondaire (Pneumocoque, Staphylocoque doré !).",
      classificationAndPathology = "Atteinte diffuse alvéolo-interstitielle (dommage alvéolaire diffus dans les formes sévères avec membranes hyalines). Pas de suppuration pure sauf surinfection.",
      clinicalSigns = "Prodromes viraux : fièvre, courbatures myalgiques intenses, céphalées, asthénie, toux sèche persistante, anosmie/agueusie (évocatrice COVID-19), rhinopharyngite. Dans les formes graves : aggravation respiratoire retardée vers J7–J10 (« deuxième vague » !), polypnée, hypoxémie parfois « heureuse » (désaturation profonde au saturomètre sans grande sensation subjective d'étouffement au début !), crépitants fins bilatéraux ou auscultation pauvre.",
      paraclinicalExams = listOf(
        "RT-PCR nasopharyngée multiplex (Grippe, SARS-CoV-2, VRS)",
        "Oxymétrie de pouls (SpO2) et Gaz du sang artériel si SpO2 < 94%",
        "Scanner thoracique (dans COVID-19/formes graves : plages bilatérales périphériques en « verre dépoli » +/- condensations ; angioscanner si suspicion d'EP associée !)",
        "Biologie : lymphopénie fréquente, CRP élevée (modérée initialement puis élevée en phase inflammatoire), D-Dimères, Procalcitonine (basse dans les formes virales pures ; élevée si surinfection bactérienne !)"
      ),
      diagnosticCriteria = listOf(
        "Syndrome respiratoire aigu + Détection moléculaire virale positive (RT-PCR) + Anomalies radiologiques/scanographiques (verre dépoli bilatéral / syndrome interstitiel)"
      ),
      clinicalForms = listOf(
        "Pneumonie grippale maligne primaire (détresse respiratoire aiguë en 48h) vs Surinfection bactérienne post-grippale tardive à S. aureus ou Pneumocoque (« rechute fébrile après amélioration transitoire = V grippal » !)",
        "Pneumopathie hypoxémiante à SARS-CoV-2 avec maladie thromboembolique associée"
      ),
      evolutionAndPrognosis = "Résolution spontanée fréquente des formes légères en 7–10 jours ; 5–15% des patients à risque basculent vers une insuffisance respiratoire aiguë (SDRA) nécessitant réanimation. Risque de fibrose séquellaire après SDRA prolongé.",
      managementPrinciples = listOf(
        "Oxygénothérapie adaptée (lunettes -> masque -> O2 haut débit nasal Optiflow -> ventilation mécanique si SDRA)",
        "Pas d'antibiothérapie systématique devant une pneumonie virale pure prouvée sans foyer bactérien ni aggravation secondaire !",
        "Corticothérapie (Dexaméthasone 6 mg/j pendant 10 jours) INDIQUÉE dans la pneumonie COVID-19 oxygéno-requérante (CONTRE-INDIQUÉE dans la grippe grave !!), prévention thromboembolique systématique (HBPM), antiviraux précoces chez sujets à risque (Oseltamivir grippe, Nirmatrelvir/Ritonavir COVID-19)",
        "Prévention majeure : VACCINATION antigrippale annuelle, anti-COVID et anti-VRS chez les populations cibles + isolement gouttelettes"
      ),
      differentialDiagnoses = listOf(
        "Pneumopathie bactérienne atypique (Mycoplasma, Légionelle), Pneumocystose (sujet immunodéprimé VIH/corticothérapie !), OAP cardiogénique, Embolie pulmonaire"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Aggravation secondaire à J8",
      clinicalCaseScenario = "M. K., 58 ans (cas fictif pédagogique), obèse (IMC 33), testé positif par PCR au SARS-CoV-2 il y a 8 jours avec fièvre et toux sèche initiales. Après une légère amélioration, il consulte à J8 pour une réascension fébrile à 38.7°C et une sensation de souffle court à la moindre parole. SpO2 87% AA, FR 28/min, FC 102/min. CRP 135 mg/L, Procalcitonine 0.12 ng/mL, Lymphocytes 0.5 G/L.",
      clinicalCaseCorrection = "1) Orage inflammatoire pulmonaire à J8 de la COVID-19 (hypoxémie sévère + CRP haute + lymphopénie + PCT basse rendant la surinfection bactérienne peu probable mais à vérifier au scanner).\n" +
        "2) Réaliser un Angioscanner thoracique (quantifier l'étendue du verre dépoli ET éliminer une embolie pulmonaire fréquemment associée !).\n" +
        "3) Hospitalisation : Oxygénothérapie pour SpO2 >= 94%, DEXAMÉTHASONE 6 mg/j IV/PO, prophylaxie/traitement HBPM, PAS d'antibiotiques d'emblée sauf preuve de foyer bactérien.",
      keyPoints = listOf(
        "« V grippal » (amélioration puis rechute fébrile purulente à J5–J7) = Surinfection bactérienne à Pneumocoque ou Staphylocoque doré !",
        "Scanner COVID-19 typique = Verre dépoli bilatéral périphérique +/- embolie pulmonaire fréquente",
        "Dexaméthasone salvatrice dans la COVID-19 hypoxémiante (mais à proscrire dans la grippe !) ; éviter les antibiotiques inutiles sans preuve bactérienne"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Orientation devant une pneumonie : Virale pure vs Surinfection bactérienne",
          headers = listOf("Paramètre", "Pneumonie Virale Pure", "Surinfection Bactérienne Post-Virale"),
          rows = listOf(
            listOf("Chronologie", "Continue ou aggravation inflammatoire J7–J10", "Évolution biphasique (« V grippal »)"),
            listOf("Expectoration", "Toux sèche irritative", "Expectoration purulente nouvelle"),
            listOf("Imagerie", "Verre dépoli bilatéral / interstitiel diffus", "Condensation alvéolaire localisée"),
            listOf("Procalcitonine (PCT)", "Normale / Basse (< 0.25 ng/mL)", "Franchement élevée (> 0.5 ng/mL)")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_04_pneumonie_fongique",
      title = "4. Pneumonies Fongiques (Pneumocystose & Aspergillose)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Infections opportunistes du sujet immunodéprimé — Réflexe LBA et Bêta-D-Glucane",
      definition = "Infections pulmonaires graves causées par des champignons opportunistes (Pneumocystis jirovecii, Aspergillus fumigatus, Cryptococcus) survenant quasi exclusivement sur un terrain d'immunodépression cellulaire, neutropénie prolongée ou poumon cavitaire préexistant.",
      epidemiology = "La pneumocystose reste une infection révélatrice fréquente du VIH non dépisté (CD4 < 200/mm³) et des hémopathies/corticothérapies prolongées. L'aspergillose invasive menace les patients en aplasie fébrile prolongée (LAM, allogreffe).",
      etiologiesAndRiskFactors = listOf(
        "Pneumocystis jirovecii : VIH avec CD4 < 200/mm³, corticothérapie prolongée (> 20 mg/j > 1 mois sans prophylaxie), chimiothérapie / anti-TNF / greffe",
        "Aspergillus fumigatus : 1) Aspergillose Pulmonaire Invasive (neutropénie profonde prolongée < 500 PNN/mm³, greffe, réanimation grippe/COVID sévère) ; 2) Aspergillome intracavitaire (greffe sur ancienne caverne tuberculeuse !) ; 3) ABPA (aspergillose bronchopulmonaire allergique chez asthmatique/mucoviscidose)"
      ),
      physiopathology = "Pneumocystose : prolifération trophozoïtaire alvéolaire empêchant les échanges gazeux (alvéolite spumeuse hypoxémiante majeure). Aspergillose invasive : angio-invasion des artérioles pulmonaires par les filaments mycéliens -> thrombose, nécrose et infarctus hémorragiques pulmonaires (« signe du halo » puis « croissant gazeux »).",
      classificationAndPathology = "Trois grands tableaux aspergillaires distincts selon l'immunité de l'hôte : hypersensibilité (ABPA), colonisation simple de cavité (aspergillome) et destruction invasive angio-invasive (immunodéprimé profond).",
      clinicalSigns = "PNEUMOCYSTOSE : triade subaiguë progressive sur 2–4 semaines chez un immunodéprimé sans prophylaxie = DYSPNÉE D'EFFORT PUIS DE REPOS + TOUX SÈCHE + FIÈVRE MODÉRÉE, contrastant avec une AUSCULTATION PULMONAIRE QUASI NORMALE et une HYPOXÉMIE SÉVÈRE ! ASPERGILLOSE INVASIVE : fièvre réfractaire aux antibiotiques chez un patient en neutropénie prolongée, douleur thoracique pleurale, hémoptysie parfois cataclysmique. ASPERGILLOME : hémoptysies récidivantes chez un ancien tuberculeux.",
      paraclinicalExams = listOf(
        "Scanner thoracique haute résolution : verre dépoli bilatéral diffus péri-hilaire épargnant parfois la périphérie (Pneumocystose) ; nodules cerclés d'un « SIGNE DU HALO » en verre dépoli puis « croissant gazeux » ou image « en grelot » déclive mobile (Aspergillome !)",
        "Fibroscopie bronchique avec Lavage Broncho-Alvéolaire (LBA — examen clé) : PCR et coloration directe (Gomori-Grocott / MGG) pour Pneumocystis ; culture mycologique + Antigène Galactomannane pour Aspergillus",
        "Biologie sang : LDH très élevés et Bêta-D-Glucane (sensible dans pneumocystose), Antigénémie aspergillaire (Galactomannane), Sérologie VIH systématique devant toute pneumocystose !"
      ),
      diagnosticCriteria = listOf(
        "Terrain d'immunodépression compatible + Image TDM évocatrice + Confirmation microbiologique par LBA (PCR/coloration Pneumocystis ou galactomannane/filaments d'Aspergillus) et/ou biomarqueurs fongiques"
      ),
      clinicalForms = listOf(
        "Pneumocystose révélant une séropositivité VIH méconnue (toujours proposer un test VIH !)",
        "Aspergillome simple sur caverne tuberculeuse ancienne (risque d'hémoptysie massive par érosion des artères bronchiques !)"
      ),
      evolutionAndPrognosis = "Mortalité élevée (15–30% dans la pneumocystose grave, > 40–60% dans l'aspergillose invasive) si le traitement spécifique est retardé ; excellente prévention possible par prophylaxie ciblée.",
      managementPrinciples = listOf(
        "Pneumocystose : Cotrimoxazole (Sulfaméthoxazole-Triméthoprime) à forte dose pendant 21 jours + CORTICOTHÉRAPIE ADJUVANTE SI PaO2 < 70 mmHg (indispensable !!) + Prophylaxie secondaire ensuite jusqu'à remontée durable des CD4 > 200",
        "Aspergillose invasive : Voriconazole ou Isavuconazole IV en 1ère intention dès la forte suspicion clinique/TDM",
        "Aspergillome symptomatique (hémoptysies) : Embolisation artérielle bronchique en urgence puis résection chirurgicale si opérable"
      ),
      differentialDiagnoses = listOf(
        "Pneumonie virale (CMV, COVID-19), OAP cardiogénique, Pneumopathie médicamenteuse (Méthotrexate, Bléomycine), Nocardiose ou Tuberculose chez l'immunodéprimé"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Dyspnée subaiguë sur terrain immunodéprimé",
      clinicalCaseScenario = "M. B., 39 ans (cas fictif pédagogique), traité par corticoïdes à 40 mg/j depuis 3 mois pour une vascularite sans prophylaxie, consulte pour une dyspnée s'aggravant progressivement depuis 3 semaines avec toux sèche et fièvre à 38.2°C. Auscultation normale ! SpO2 85% AA (PaO2 52 mmHg). Radio : infiltrat réticulo-nodulaire bilatéral péri-hilaire. LDH à 680 UI/L.",
      clinicalCaseCorrection = "1) Tableau typique de PNEUMOCYSTOSE PULMONAIRE (terrain corticothérapie prolongée sans Cotrimoxazole prophylactique + triade subaiguë + auscultation pauvre avec hypoxie sévère + LDH hauts).\n" +
        "2) Réaliser Scanner thoracique (verre dépoli bilatéral), LBA (PCR + coloration Pneumocystis jirovecii) ET Sérologie VIH par principe.\n" +
        "3) Débuter EN URGENCE : Cotrimoxazole forte dose IV + CORTICOTHÉRAPIE systémique (car PaO2 < 70 mmHg !) + Oxygénothérapie.",
      keyPoints = listOf(
        "Immunodéprimé (CD4 < 200 ou corticoïdes longs sans prophylaxie) + Dyspnée subaiguë + Toux sèche + Auscultation normale + Hypoxie sévère = PNEUMOCYSTOSE !",
        "Si PaO2 < 70 mmHg dans une pneumocystose : ajouter impérativement des CORTICOÏDES au Cotrimoxazole",
        "Neutropénie longue + fièvre réfractaire aux ATB + « signe du halo » au TDM = Aspergillose invasive -> Voriconazole"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Comparaison Pneumocystose vs Aspergillose Invasive vs Aspergillome",
          headers = listOf("Caractéristique", "Pneumocystose", "Aspergillose Invasive", "Aspergillome"),
          rows = listOf(
            listOf("Terrain type", "VIH (CD4 < 200), corticoïdes", "Neutropénie profonde prolongée", "Ancienne caverne (TB)"),
            listOf("Signe TDM clé", "Verre dépoli bilatéral diffus", "Nodule avec « signe du halo »", "Boule en « grelot » mobile"),
            listOf("Diagnostic", "LBA (PCR / Gomori) + BDG", "LBA + Galactomannane sang/LBA", "TDM + Sérologie Aspergillus"),
            listOf("Traitement", "Cotrimoxazole (+ Cortico si hypoxie)", "Voriconazole / Isavuconazole", "Embolisation + Chirurgie si hémoptysie")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_05_tuberculose",
      title = "5. Tuberculose Pulmonaire (TB)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Infection chronique à Mycobacterium tuberculosis — Contagiosité et traitement quadri-antibiotique",
      definition = "Maladie infectieuse transmissible par voie aérienne due au complexe Mycobacterium tuberculosis (Bacille de Koch — BK), affectant principalement le poumon (80% des formes) sous forme de tuberculose-maladie active.",
      epidemiology = "Problème majeur de santé publique mondial (> 10 millions de cas/an, ~1,3 million de décès/an). En France : ~4 500–5 000 cas/an à déclaration obligatoire, concentrés chez les sujets précaires, migrants de zones d'endémie, sujets âgés et immunodéprimés (VIH).",
      etiologiesAndRiskFactors = listOf(
        "Agent : Mycobacterium tuberculosis (bacille aérobie strict, acido-alcoolo-résistant — BAAR, croissance lente 3–6 semaines sur milieu de Löwenstein-Jensen)",
        "Transmission : exclusivement interhumaine par voie AÉRIENNE (aérosols de toux émis par un patient bacillifère)",
        "Facteurs de risque : VIH, précarité/promiscuité, origine de zone de forte endémie, diabète, silicose, dénutrition, anti-TNF alpha (dépistage obligatoire avant initiation !)"
      ),
      physiopathology = "Primo-infection tuberculeuse (inhalation de BK -> chancre d'inoculation + adénopathie médiastinale -> granulome épithélioïde et gigantocellulaire avec nécrose caséeuse). Dans 90% des cas : infection tuberculeuse latente (ITL, patient non malade et non contagieux). Dans 10% : réactivation vers la tuberculose-maladie avec fonte du caséum créant des CAVERNES riches en bacilles (extrêmement contagieuses).",
      classificationAndPathology = "Lésion anatomopathologique caractéristique : GRANULOME ÉPITHÉLIOÏDE ET GIGANTOCELLULAIRE AVEC NÉCROSE CASÉEUSE. Distinguer ITL (asymptomatique, radio normale, IGRA/IDR+) vs Tuberculose-maladie (symptômes/anomalies radio + culture positive).",
      clinicalSigns = "Évolution SUBAIGUË OU CHRONIQUE (> 2–3 semaines) : toux traînante persistante +/- expectoration hémoptoïque ou HÉMOPTYSIE franche, FÉBRICULE VESPERAL (le soir !), SUEURS NOCTURNES PROFUSES (« mouillant les draps »), AMAIGRISSEMENT marqué et asthénie/anorexie. L'examen clinique pulmonaire est souvent pauvre ou révèle des râles aux sommets. Rechercher adénopathies périphériques, atteinte méningée ou vertébrale (mal de Pott).",
      paraclinicalExams = listOf(
        "Radiographie et/ou Scanner thoracique : lésions préférentielles des SOMMETS / LOBES SUPÉRIEURS (nodules, infiltrats excavés et CAVERNES à paroi épaisse avec bronche de drainage !), ou MILIAIRE (petits micronodules diffus partout)",
        "Diagnostic microbiologique OBLIGATOIRE avant traitement (3 crachats 3 matins de suite ou tubages gastriques le matin à jeun, ou fibroscopie bronchique si crachats négatifs) : Examen direct BAAR (coloration Ziehl-Neelsen -> évalue la CONTAGIOSITÉ immédiate !), PCR (GeneXpert -> diagnostic rapide en 2h + détection résistance à la RIFAMPICINE !), et Culture Löwenstein-Jensen (antibiogramme indispensable !)",
        "Bilan pré-thérapeutique OBLIGATOIRE : Sérologie VIH, VHB/VHC, Bilan hépatique (ASAT/ALAT/Bilirubine), Créatinine + Acide urique, Examen ophtalmologique avec VISION DES COULEURS (avant Éthambutol !), test grossesse ; Déclaration Obligatoire (DO) et enquête autour du cas"
      ),
      diagnosticCriteria = listOf(
        "Preuve bactériologique formelle par isolement de Mycobacterium tuberculosis (PCR/Culture) sur prélèvements respiratoires (+ granulome caséeux à l'histologie en forme extrapulmonaire)"
      ),
      clinicalForms = listOf(
        "Miliaire tuberculeuse (dissémination hématogène gravissime -> fond d'œil + PL + hémocultures sur milieu spécifique !)",
        "Tuberculose extrapulmonaire (~20% : ganglionnaire, pleurale, méningée/neuroméningée = urgence avec corticoïdes !, osseuse/mal de Pott, péricardique, uro-génitale)"
      ),
      evolutionAndPrognosis = "Guérison > 95% si quadrithérapie bien prise pendant 6 mois. Risque d'émergence de tuberculose multirésistante (MDR/XDR) en cas d'inobservance ou d'arrêt prématuré du traitement. Séquelles cavitaires exposant à la greffe aspergillaire (aspergillome !).",
      managementPrinciples = listOf(
        "ISOLEMENT RESPIRATOIRE « AIR » IMMÉDIAT en chambre seule (masque FFP2 pour les soignants, masque chirurgical pour le patient hors chambre !) dès la suspicion et jusqu'à négativation des BAAR (environ 2–3 semaines de traitement !)",
        "Traitement standard de 6 mois (forme sensible pulmonaire) en 1 prise à jeun le matin : 2 mois de QUADRITHÉRAPIE (Isoniazide + Rifampicine + Pyrazinamide + Éthambutol) puis 4 mois de BITHÉRAPIE (Isoniazide + Rifampicine)",
        "Surveillance hépatique rapprochée (Isoniazide, Rifampicine, Pyrazinamide tous hépatotoxiques ! -> arrêt si transaminases > 3N symptomatiques ou > 6N !), névrite optique rétrobulbaire sous Éthambutol, coloration orangée des urines/larmes et interaction pilule/AVK/ARV sous Rifampicine (inducteur enzymatique majeur !)",
        "Déclaration Obligatoire (ARS) + Prise en charge à 100% + Dépistage des sujets contacts (CLAT)"
      ),
      differentialDiagnoses = listOf(
        "Cancer bronchopulmonaire nécrosé (aspect de caverne épaisse chez le fumeur !), Abcès pulmonaire à pyogènes, Mycobactérie atypique, Sarcoïdose (granulome SANS nécrose caséeuse !), Aspergillose"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Toux chronique et sueurs nocturnes",
      clinicalCaseScenario = "M. S., 34 ans (cas fictif pédagogique), arrivé récemment d'une zone de forte endémie, consulte pour une toux productive avec filets de sang évoluant depuis 5 semaines, accompagnée de sueurs nocturnes abondantes, d'un fébricule le soir à 38°C et d'un amaigrissement de 7 kg. Radio thorax : infiltration nodulaire et image cavitaire de 3 cm au sommet droit. L'examen direct des crachats (Ziehl) retrouve de nombreux BAAR (+++).",
      clinicalCaseCorrection = "1) Tuberculose pulmonaire commune bacillifère hautement contagieuse (BAAR+++ au direct).\n" +
        "2) Isolement respiratoire « AIR » immédiat (masque FFP2 pour soignants), 3 prélèvements + GeneXpert (vérifier rpoB / sensibilité Rifampicine) + Culture + Bilan pré-thérapeutique (VIH, ASAT/ALAT, Créat, Vision des couleurs).\n" +
        "3) Débuter quadrithérapie (Rifampicine + Isoniazide + Pyrazinamide + Éthambutol) 2 mois puis Rifampicine + Isoniazide 4 mois + Déclaration Obligatoire et dépistage des cas contacts.",
      keyPoints = listOf(
        "Toux > 2–3 semaines + Amaigrissement + Sueurs nocturnes + Lésion des sommets = Tuberculose -> Isolement AIR immédiat !",
        "Examen direct (BAAR) donne la contagiosité en urgence ; PCR (GeneXpert) détecte résistance Rifampicine ; Culture affirme diagnostic et antibiogramme complet",
        "Quadrithérapie 2 mois (RIPE) puis Bithérapie 4 mois (RI) ; surveiller Foie (R,I,P), Œil/couleurs (Éthambutol) et interactions médicamenteuses (Rifampicine)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Effets indésirables et surveillance des 4 antituberculeux majeurs",
          headers = listOf("Molécule", "Toxicité majeure", "Surveillance clé", "Particularité clinique"),
          rows = listOf(
            listOf("Rifampicine (R)", "Hépatite, allergie", "Transaminases, bilirubine", "Urines/larmes oranges ; inducteur enzymatique majeur"),
            listOf("Isoniazide (I/H)", "Hépatite, neuropathie périphérique", "Transaminases", "Associer Vitamine B6 (Pyridoxine) si dénutri/grossesse/VIH"),
            listOf("Pyrazinamide (Z)", "Hépatite sévère, hyperuricémie", "Transaminases, acide urique", "Arrêter à la fin des 2 premiers mois"),
            listOf("Éthambutol (E)", "Névrite optique rétrobulbaire (NORB)", "Vision des couleurs + acuité", "Arrêter dès baisse vision/couleurs (cécité irréversible si tardif !)")
          )
        )
      )
    )
  )
}
