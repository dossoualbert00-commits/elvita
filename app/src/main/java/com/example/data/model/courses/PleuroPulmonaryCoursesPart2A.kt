package com.example.data.model.courses

import com.example.data.model.SemiologyCategory

object PleuroPulmonaryCoursesPart2A {
  val courses: List<MedicalCourse> = listOf(
    MedicalCourse(
      id = "course_resp_11_abces",
      title = "11. Abcès Pulmonaire",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Suppuration nécrotique collectée du parenchyme — Image hydro-aérique et vomique purulente",
      definition = "Collection suppurée néo-formée au sein d'une cavité nécrotique creusée dans le parenchyme pulmonaire par une infection bactérienne nécrosante non tuberculeuse.",
      epidemiology = "Devient plus rare grâce aux antibiotiques mais touche surtout l'homme d'âge moyen alcoolo-tabagique, les patients présentant des troubles de la déglutition/épilepsie/AVC, ou porteurs d'une néoplasie bronchique sténosante.",
      etiologiesAndRiskFactors = listOf(
        "Infection par INHALATION ORALE / ANAÉROBIES (> 60% : Peptostreptococcus, Prevotella, Fusobacterium — terrain : mauvaise hygiène bucco-dentaire, coma, ivresse aiguë, AVC, RGO !)",
        "Germes aérobies nécrosants : Staphylococcus aureus (PVL+), Klebsiella pneumoniae (fumeur alcoolique — lobe supérieur bombant !), Pseudomonas aeruginosa, Nocardia (immunodéprimé)",
        "Obstacle bronchique sous-jacent (cancer bronchopulmonaire nécrosé ++ chez tout fumeur > 45–50 ans !)"
      ),
      physiopathology = "Micro-inhalation septique de salive/contenu gastrique dans les zones déclives (segment postérieur lobe supérieur ou segment apical du lobe inférieur droit) -> pneumonie nécrosante -> liquéfaction purulente centrale -> fistulisation dans une bronche créant une VOMIQUE purulente fétide et une cavité hydro-aérique.",
      classificationAndPathology = "Abcès primaire (poumon antérieurement sain sur inhalation) vs Abcès secondaire (sur cancer bronchique obstructif, embole septique d'endocardite droite, immunodépression).",
      clinicalSigns = "Phase fermée (avant ouverture bronchique) : fièvre persistante élevée oscillante avec frissons, altération de l'état général rapide, toux douloureuse. Phase ouverte (fistulisation bronchique) : VOMIQUE PURULENTE BRUTALE (rejet massif par la bouche d'une grande quantité de pus d'ODEUR FÉTIDE PUTRIDE si anaérobies !!), suivie d'une chute transitoire de la fièvre et de crachats purulents abondants. Rechercher hippocratisme digital si évolution subaiguë et mauvais état bucco-dentaire !",
      paraclinicalExams = listOf(
        "Radiographie et SCANNER THORACIQUE (examen de référence) : CAVITÉ À PAROI ÉPAISSE ET IRRÉGULIÈRE contenant un NIVEAU HYDRO-AÉRIQUE HORIZONTAL (air en haut, liquide dense en bas) ; le scanner permet d'éliminer un empyème pleural avec fistule bronchopleurale et de chercher une tumeur bronchique !",
        "Bactériologie : Hémocultures aéro-anaérobies + ECBC +/- Fibroscopie bronchique avec prélèvement protégé (double intérêt : prélèvement microbiologique ET élimination d'un cancer bronchique ou corps étranger !!)",
        "Biologie : hyperleucocytose à PNN majeure, CRP très élevée, recherche VIH selon terrain"
      ),
      diagnosticCriteria = listOf(
        "Tableau infectieux suppuratif (+/- vomique fétide) + Image cavitaire parenchymateuse avec niveau hydro-aérique au scanner thoracique"
      ),
      clinicalForms = listOf(
        "Abcès sur cancer bronchique nécrosé (toujours contrôler par fibroscopie/TDM !)",
        "Abcès multiples par embols septiques (toxicomanie IV -> endocardite tricuspide à S. aureus !)"
      ),
      evolutionAndPrognosis = "Guérison habituelle sous antibiothérapie prolongée (3 à 6 semaines) sans chirurgie dans > 85% des cas. Complications : hémoptysie grave, rupture en plèvre (pyopneumothorax !!), choc septique, chronicité.",
      managementPrinciples = listOf(
        "Antibiothérapie probabiliste précoce ciblant ANAÉROBIES + S. aureus/entérobactéries : Amoxicilline-Acide clavulanique (ou Clindamycine, ou Pipéracilline-Tazobactam si nosocomial/grave) prolongée 3 à 6 semaines jusqu'à fermeture/netteté radio",
        "Kinésithérapie de drainage postural (après exclusion d'hémoptysie !)",
        "Soins dentaires impératifs + Fibroscopie bronchique systématique chez le fumeur > 40–45 ans pour ne pas méconnaître un cancer ! Drainage percutané/chirurgie uniquement si échec médical ou rupture pleurale"
      ),
      differentialDiagnoses = listOf(
        "Caverne tuberculeuse (sommets, évolution subaiguë/chronique, BAAR+, rarement niveau hydro-aérique important)",
        "Cancer bronchique excavé nécrosé (paroi épaisse mamelonnée irrégulière -> biopsie par fibroscopie !)",
        "Pyopneumothorax (épanchement pleural mixte longeant la paroi à angles obtus, bien distingué de l'abcès intraparenchymateux rond au scanner !), Bulle d'emphysème surinfectée, Aspergillome"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Vomique fétide chez un patient alcoolo-tabagique",
      clinicalCaseScenario = "M. T., 54 ans (cas fictif pédagogique), fumeur à 35 PA avec grave délabrement dentaire et alcoolisation chronique, présente depuis 12 jours une fièvre à 39.3°C, une altération de l'état général et une haleine fétide. Ce matin, lors d'une quinte de toux, il rejette brutalement un demi-verre de pus nauséabond. Scanner thoracique : cavité de 5 cm à paroi épaisse dans le segment apical du lobe inférieur droit contenant un niveau hydro-aérique horizontal.",
      clinicalCaseCorrection = "1) ABCÈS PULMONAIRE À ANAÉROBIES probablement secondaire à une inhalation salivaire septique (terrain alcoolo-tabagique + mauvais état dentaire + siège déclive typique + vomique fétide + niveau hydro-aérique).\n" +
        "2) Antibiothérapie immédiate active sur anaérobies (Amoxicilline-Acide clavulanique IV puis relais PO prolongé 4 à 6 semaines) + hémocultures.\n" +
        "3) Réaliser impérativement une FIBROSCOPIE BRONCHIQUE (rechercher un cancer bronchopulmonaire obstructif sous-jacent vu le tabagisme à 35 PA !) et bilan dentaire complet.",
      keyPoints = listOf(
        "Triade évocatrice : Terrain d'inhalation/tabac + Vomique purulente fétide + Niveau hydro-aérique parenchymateux au scanner",
        "Toujours penser aux ANAÉROBIES (haleine/pus fétide -> Amoxicilline-Acide clavulanique 3 à 6 semaines !)",
        "Chez tout fumeur > 45 ans : FIBROSCOPIE BRONCHIQUE OBLIGATOIRE pour éliminer un cancer bronchique excavé ou sténosant"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Orientation devant une image cavitaire pulmonaire",
          headers = listOf("Étiologie", "Terrain & Clinique", "Aspect radiologique / TDM clé", "Examen décisif"),
          rows = listOf(
            listOf("Abcès à pyogènes / anaérobies", "Fièvre aiguë + vomique fétide", "Paroi épaisse + Niveau hydro-aérique net", "TDM + Fibroscopie + ECBC"),
            listOf("Tuberculose cavitaire", "Sueurs nocturnes, amaigrissement", "Caverne des sommets + nodules satellites", "3 crachats BAAR / GeneXpert"),
            listOf("Cancer bronchique excavé", "Fumeur > 45 ans, peu/pas de fièvre", "Paroi épaisse mamelonnée irrégulière", "Fibroscopie + Biopsies"),
            listOf("Aspergillome", "Ancienne caverne + hémoptysie", "Masse ronde mobile déclive (« grelot »)", "TDM + Sérologie Aspergillus")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_12_bronchectasies",
      title = "12. Bronchectasies (Dilatation des Bronches — DDB)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Dilatation permanente et irréversible du calibre bronchique — Bronchorrhée chronique et hémoptysies",
      definition = "Dilatation permanente et irréversible du calibre des bronches moyennes et distales (> 2 mm) par destruction de leur armature fibro-cartilagineuse et musculaire, s'accompagnant d'une rétention de sécrétions purulentes et d'infections récidivantes.",
      epidemiology = "Prévalence sous-estimée (~50–100/100 000 adultes), plus fréquente chez la femme âgée pour les formes idiopathiques, et chez l'enfant/adulte jeune pour la mucoviscidose ou les dyskinésies ciliaires.",
      etiologiesAndRiskFactors = listOf(
        "FORMES LOCALISÉES : Obstacle endobronchique (corps étranger oublié, tumeur bénigne/maligne), séquelle d'infection focale grave (tuberculose des sommets, pneumonie grave de l'enfance, coqueluche)",
        "FORMES DIFFUSES : Idiopathiques (~40%), MUCOVISCIDOSE (recherche systématique chez l'adulte jeune !!), Dyskinésie ciliaire primitive (syndrome de Kartagener : DDB + sinusite chronique + situs inversus !), Déficits immunitaires humoraux (hypogammaglobulinémie / DICV), ABPA (aspergillose bronchopulmonaire allergique : DDB centrales proximales !), BPCO/Polyarthrite rhumatoïde"
      ),
      physiopathology = "« Cercle vicieux de Cole » : une agression initiale altère la clairance mucociliaire -> stagnation de mucus -> colonisation bactérienne chronique (Haemophilus, puis Pseudomonas aeruginosa !) -> inflammation neutrophilique chronique destructrice (élastase) dilatant encore plus les bronches -> néovascularisation artérielle bronchique fragile exposant aux hémoptysies !",
      classificationAndPathology = "Classification morphologique de Reid au scanner : DDB cylindriques (tubulaires, modérées), variqueuses (en chapelet) et kystiques/sacciformes (les plus graves, très sécrétantes).",
      clinicalSigns = "BRONCHORRHÉE CHRONIQUE QUOTIDIENNE ABONDANTE (> 30 mL/jour — expectoration purulente surtout le matin au réveil = « toilette bronchique matinale », sédimentant en 3–4 couches dans un verre !), infections bronchopulmonaires récidivantes toujours dans les mêmes territoires, HÉMOPTYSIES fréquentes parfois abondantes (hypervascularisation systémique bronchique !). Auscultation : râles sous-crépitants / ronflants fixes localisés ou diffus. Hippocratisme digital dans les formes anciennes diffuses.",
      paraclinicalExams = listOf(
        "SCANNER THORACIQUE EN COUPES FINES SANS INJECTION = EXAMEN CLÉ DU DIAGNOSTIC POSITIF : absence de rétrécissement progressif des bronches vers la périphérie, calibre bronchique > artère pulmonaire satellite réalisant l'aspect pathognomonique en « BAGUE À CHATON » ou en « RAILS DE TRAMWAY » !",
        "ECBC régulier : identifier les germes colonisants (Haemophilus influenzae, S. aureus, PSEUDOMONAS AERUGINOSA facteur majeur de gravité !, mycobactéries atypiques)",
        "Bilan étiologique des DDB diffuses : Électrophorèse des protéines + dosage pondéral IgG/IgA/IgM, test à la sueur / CFTR (mucoviscidose), recherche d'ABPA (IgE totales > 1000 UI/mL, IgE/prick Aspergillus), EFR (trouble obstructif fréquent), Fibroscopie bronchique OBLIGATOIRE si DDB LOCALISÉES (chercher tumeur ou corps étranger !)"
      ),
      diagnosticCriteria = listOf(
        "Tableau clinique compatible (bronchorrhée chronique/infections répétées/hémoptysie) + Critères TDM haute résolution (diamètre bronchique interne > artère satellite = signe de la bague à chaton + non-réduction de calibre vers la périphérie)"
      ),
      clinicalForms = listOf(
        "DDB sèches hémoptoïques (sans grande bronchorrhée, souvent séquelles tuberculeuses apicales se révélant par des hémoptysies)",
        "Syndrome de Kartagener (DDB diffuses + sinusite/polypose + situs inversus + infertilité masculine)",
        "DDB diffuses colonisées à Pseudomonas aeruginosa (exacerbations fréquentes, déclin respiratoire)"
      ),
      evolutionAndPrognosis = "Évolution chronique émaillée d'exacerbations infectieuses, d'hémoptysies (parfois graves -> embolisation artérielle bronchique !), et vers l'insuffisance respiratoire chronique et l'amylose AA rarement.",
      managementPrinciples = listOf(
        "PIERRE ANGULAIRE DU TRAITEMENT : KINÉSITHÉRAPIE RESPIRATOIRE QUOTIDIENNE DE DÉSENCOMBREMENT BRONCHIQUE + Hydratation + Éviction absolue du tabac !",
        "Traitement des exacerbations : Antibiothérapie adaptée à l'ECBC (ciblant Pseudomonas aeruginosa si colonisé -> Ciprofloxacine ou Bêta-lactamine anti-pyocyanique IV !), durant 14 jours habituellement",
        "Éradication précoce de toute primo-colonisation à Pseudomonas ; Macrolides au long cours (Azithromycine) si >= 3 exacerbations/an après exclusion mycobactérie atypique",
        "Hémoptysie sévère : artério-embolisation bronchique ; Chirurgie d'exérèse exceptionnelle si DDB strictement localisée très symptomatique"
      ),
      differentialDiagnoses = listOf(
        "Bronchite chronique simple / BPCO sans dilatation bronchique au scanner, Tuberculose active, Abcès pulmonaire, Mucoviscidose non encore diagnostiquée"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Bronchorrhée matinale et image en bague à chaton",
      clinicalCaseScenario = "Mme C., 59 ans (cas fictif pédagogique), non fumeuse, consulte pour une toux productive quotidienne ramenant chaque matin au réveil un demi-verre d'expectorations mucopurulentes depuis 8 ans, associée à 4 épisodes de surinfection/an et deux petites hémoptysies récentes. Auscultation : gros râles bulleux persistants aux deux bases. Scanner thoracique haute résolution : dilatations bronchiques kystiques et cylindriques basales bilatérales avec aspect en « bague à chaton ».",
      clinicalCaseCorrection = "1) DILATATION DES BRONCHES (BRONCHECTASIES) DIFFUSES BILATÉRALES confirmée par le signe scannographique de la « bague à chaton ».\n" +
        "2) Bilan complémentaire indispensable : ECBC (recherche Pseudomonas aeruginosa / mycobactéries), électrophorèse + dosage pondéral des immunoglobulines (DICV ?), IgE/prick Aspergillus (ABPA ?), EFR et +/- test sueur.\n" +
        "3) Traitement de fond : kinésithérapie respiratoire quotidienne de drainage bronchique + vaccinations (grippe, pneumocoque) + antibiothérapie guidée par ECBC lors des exacerbations.",
      keyPoints = listOf(
        "Clinique cardinale des DDB = Bronchorrhée purulente matinale abondante + Infections bronchiques répétées + Hémoptysies",
        "Diagnostic de certitude = SCANNER THORACIQUE HAUTE RÉSOLUTION (signe de la « bague à chaton » et « rails de tramway »)",
        "Toujours faire une fibroscopie si DDB LOCALISÉES (éliminer obstacle/cancer) et un ECBC régulier (dépister Pseudomonas aeruginosa)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Orientation étiologique devant des bronchectasies au scanner",
          headers = listOf("Topographie TDM", "Causes principales à évoquer", "Examen clé à prescrire"),
          rows = listOf(
            listOf("DDB localisées (1 lobe/segment)", "Tumeur bronchique, corps étranger, séquelle TB", "Fibroscopie bronchique obligatoire"),
            listOf("DDB diffuses basales / moyennes", "Idiopathique, DICV, Kartagener, post-infectieux", "IgG/IgA/IgM, ECBC, bilan ciliaire"),
            listOf("DDB prédominant aux sommets", "Mucoviscidose, séquelles tuberculeuses", "Test à la sueur / gène CFTR"),
            listOf("DDB proximales centrales", "ABPA (aspergillose bronchopulmonaire allergique)", "IgE totales > 1000 + IgE/IgG Aspergillus")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_13_ira",
      title = "13. Insuffisance Respiratoire Aiguë (IRA)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Défaillance aiguë de l'hématose — Reconnaître les signes de gravité vitale et distinguer Hypoxémie vs Hypercapnie",
      definition = "Incapacité brutale de l'appareil respiratoire à assurer des échanges gazeux normaux (oxygénation et/ou élimination du CO2), définie gazométriquement par une PaO2 < 60 mmHg (hypoxémie sévère, SpO2 < 90% AA) et/ou une PaCO2 > 45 mmHg avec acidose respiratoire (pH < 7.35).",
      epidemiology = "Motif majeur de recours aux urgences et d'admission en réanimation. Mortalité hospitalière élevée (10–30%) corrélée à la rapidité d'identification de la cause et des signes d'épuisement ventilatoire.",
      etiologiesAndRiskFactors = listOf(
        "IRA HYPOXÉMIQUE PURE (Effet Shunt — PaCO2 normale ou basse) : Pneumopathie aiguë grave, OAP cardiogénique, Embolie pulmonaire, SDRA, Atélectasie, Asthme initial",
        "IRA HYPERCAPNIQUE / HYPOVENTILATION ALVÉOLAIRE (PaCO2 > 45 mmHg + acidose) : Décompensation de BPCO (cause n°1 !), Asthme aigu grave épuisé, Intoxication aux dépresseurs centraux (morphiniques/benzodiazépines), Maladies neuromusculaires (Guillain-Barré, myasthénie), Obstruction des voies aériennes supérieures (œdème de Quincke, corps étranger, épiglottite)"
      ),
      physiopathology = "Quatre mécanismes d'hypoxémie : 1) Effet shunt (zones perfusées non ventilées : pneumonie, OAP, atélectasie) ; 2) Effet espace mort (zones ventilées non perfusées : EP) ; 3) Hypoventilation alvéolaire (hypercapnie associée) ; 4) Trouble de diffusion alvéolo-capillaire. La différence alvéolo-artérielle en O2 [P(A-a)O2] est NORMALE dans l'hypoventilation pure (ex: intoxication morphinique) et AUGMENTÉE dans les atteintes parenchymateuses/vasculaires.",
      classificationAndPathology = "IRA de Type I (hypoxémique non hypercapnique : PaO2 < 60, PaCO2 <= 45) vs IRA de Type II (hypercapnique : PaCO2 > 45 + acidose respiratoire aiguë pH < 7.35 si non compensée ou décompensée sur IRC).",
      clinicalSigns = "SIGNES D'HYPOXÉMIE : polypnée (FR > 25–30/min), cyanose des lèvres et extrémités (hémoglobine réduite > 5 g/dL), tachycardie, agitation/confusion. SIGNES D'HYPERCAPNIE : SUEURS PROFUSES (vasodilatation), HTA/tachycardie, ASTÉRIXIS (flapping tremor des mains !), céphalées, somnolence évoluant vers le coma hypercapnique (« narcose au CO2 »). SIGNES DE LUTTE ET D'ÉPUISEMENT RESPIRATOIRE (URGENCE INTUBATION/VNI !!) : tirage sus-sternal/intercostal, mise en jeu des muscles sterno-cléido-mastoïdiens, BALANCEMENT THORACO-ABDOMINAL (respiration abdominale paradoxale !), bradypnée secondaire/pauses respiratoires, incapacité de parler ou tousser, état de choc.",
      paraclinicalExams = listOf(
        "Oxymétrie de pouls (SpO2) immédiate puis GAZ DU SANG ARTÉRIELS (PaO2, PaCO2, pH, HCO3-, Lactates — noter si sous air ambiant ou sous O2 !)",
        "Radiographie thoracique au lit du patient (OAP, pneumonie, pneumothorax, épanchement, atélectasie)",
        "ECG (SCA, tachycardie, S1Q3), Bilan biologique (NFS, CRP/PCT, ionogramme, urée/créat, troponine, BNP/NT-proBNP, D-Dimères si suspicion EP sans autre cause évidente)",
        "Échographie pleuro-pulmonaire et cardiaque au lit du patient (orientent en < 5 minutes entre OAP, pneumonie, pneumothorax, tamponnade, EP !)"
      ),
      diagnosticCriteria = listOf(
        "Détresse respiratoire clinique + Gaz du sang objectivant PaO2 < 60 mmHg (ou SpO2 < 90% AA) et/ou PaCO2 > 45 mmHg avec acidose pH < 7.35"
      ),
      clinicalForms = listOf(
        "IRA sur insuffisance respiratoire chronique (BPCO, cyphoscoliose -> bicarbonates déjà élevés > 30 mmol/L traduisant la compensation métabolique chronique !)",
        "IRA de novo sur poumon antérieurement sain (OAP, pneumonie sévère, EP, SDRA)"
      ),
      evolutionAndPrognosis = "Risque imminent d'arrêt cardio-respiratoire hypoxique ou hypercapnique en l'absence de correction rapide de l'oxygénation et/ou d'assistance ventilatoire (VNI ou intubation).",
      managementPrinciples = listOf(
        "Oxygénothérapie immédiate adaptée : haut débit/masque haute concentration si poumon sain hypoxémique (cible >= 94–95%) ; O2 TITRÉ PRUDENT (cible 88–92%) chez le BPCO hypercapnique connu !",
        "Libération des voies aériennes supérieures, position demi-assise (sauf choc), VVP, monitorage continu (SpO2, scope ECG, PA, FR)",
        "Assistance ventilatoire si échec ou critères : VNI (indication formelle dans EABPCO avec pH < 7.35 et OAP cardiogénique sévère !) ; Intubation oro-trachéale et ventilation mécanique si coma, choc, épuisement extrême ou échec VNI",
        "Traitement étiologique urgent simultané (diurétiques/nitrés si OAP, bronchodilatateurs si asthme/BPCO, antibiotiques si pneumonie, drainage si pneumothorax, anticoagulation si EP, Naloxone si surdosage morphinique !)"
      ),
      differentialDiagnoses = listOf(
        "Acidose métabolique sévère (acidocétose, choc lactique -> dyspnée ample de Kussmaul avec poumons parfaitement clairs et PaCO2 très BASSE par hyperventilation compensatrice — ce n'est pas une défaillance respiratoire !)",
        "Crise d'angoisse / attaque de panique (diagnostic d'élimination strict après SpO2/auscultation/ECG normaux !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Tri d'une détresse respiratoire avec respiration paradoxale",
      clinicalCaseScenario = "Mme Z., 72 ans (cas fictif pédagogique), myasthénique ayant arrêté son traitement depuis 4 jours à la suite d'une infection bronchique, est admise en détresse respiratoire : voix éteinte, impossibilité de déglutir, sueurs profuses, FR 34/min puis 14/min avec balancement thoraco-abdominal net (rentrée inspiratoire de l'abdomen !). SpO2 84% AA. GDS : pH 7.21, PaO2 51 mmHg, PaCO2 68 mmHg, HCO3- 26 mmol/L.",
      clinicalCaseCorrection = "1) IRA HYPERCAPNIQUE AIGUË SÉVÈRE (Type II) sur crise myasthénique avec épuisement diaphragmatique IMMINENT : balancement thoraco-abdominal + bradypnée secondaire + troubles de déglutition + acidose respiratoire aiguë non compensée (bicarbonates normaux à 26 -> processus très aigu !).\n" +
        "2) La VNI seule est insuffisante et risquée devant les troubles de déglutition et l'épuisement neuromusculaire avancé.\n" +
        "3) CAT d'extrême urgence : appel réanimation, pré-oxygénation et INTUBATION ORO-TRACHÉALE + ventilation mécanique + prise en charge de la crise myasthénique (immunoglobulines IV ou échanges plasmatiques + traitement de l'infection déclenchante).",
      keyPoints = listOf(
        "Signes d'hypercapnie = Sueurs + Astérixis + HTA + Somnolence/Coma ; Signe d'épuisement diaphragmatique = Balancement thoraco-abdominal !",
        "Bicarbonates normaux + PaCO2 haute + pH bas = Hypercapnie aiguë de novo ; Bicarbonates très hauts (> 30) = Décompensation aiguë sur hypercapnie chronique (BPCO)",
        "OAP et BPCO acidosique -> VNI en 1ère intention ; Troubles de conscience / coma / épuisement / choc -> Intubation immédiate"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Interprétation rapide des Gaz du Sang Artériels dans l'IRA",
          headers = listOf("Profil GDS", "PaO2", "PaCO2", "pH", "HCO3-", "Exemples typiques"),
          rows = listOf(
            listOf("Effet Shunt pur", "< 60", "< 35", "> 7.45", "Normal", "EP, Pneumonie débutante, OAP léger"),
            listOf("Hypoventilation aiguë", "< 60", "> 45", "< 7.35", "22–26", "Crise myasthénique, AAG épuisé, Opioïdes"),
            listOf("IRC hypercapnique décompensée", "< 60", ">> 50", "< 7.35", "> 30", "Exacerbation aiguë de BPCO grave"),
            listOf("Acidose métabolique compensée", "> 80", "< 30", "< 7.35", "< 18", "Acidocétose diabétique (Kussmaul)")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_14_oap",
      title = "14. Œdème Aigu du Poumon (OAP Cardiogénique)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Inondation alvéolaire aiguë par élévation des pressions de remplissage gauche — Orthopnée et crépitants bilatéraux",
      definition = "Invasion brutale des alvéoles et de l'interstitium pulmonaires par un liquide plasmatique transsudatif secondaire à une élévation aiguë de la pression capillaire pulmonaire (> 25 mmHg) liée à une défaillance cardiaque gauche (OAP hémodynamique/cardiogénique).",
      epidemiology = "Première cause d'insuffisance respiratoire aiguë aux urgences chez le sujet âgé (> 70 ans). Pronostic grave en phase aiguë (mortalité hospitalière 5–10%, > 30% si choc cardiogénique associé !).",
      etiologiesAndRiskFactors = listOf(
        "Causes d'OAP sur cœur antérieurement malade (IC connue) ou de novo : Poussée HYPERTENSIVE sévère (cause très fréquente chez le sujet âgé à FEVG préservée !), SYNDROME CORONARIEN AIGU (infarctus/ischémie gâchette à éliminer SYSTÉMATIQUEMENT !), Passage en FIBRILLATION AURICULAIRE rapide ou trouble du rythme, Écart de régime hyposodé / arrêt diurétiques, Infection bronchopulmonaire ou anémie aiguë",
        "Insuffisance valvulaire aiguë (rupture de cordage mitral, endocardite infectieuse aortique/mitrale) ou sténose mitrale serrée"
      ),
      physiopathology = "Augmentation brutale de la pression télédiastolique du VG -> hypertension veineuse et capillaire pulmonaire -> dépassement de la pression oncotique et des capacités de drainage lymphatique -> œdème interstitiel puis inondation alvéolaire massive -> effondrement de la compliance pulmonaire et effet shunt hypoxémiant majeur.",
      classificationAndPathology = "Deux profils cliniques hémodynamiques clés guidant le traitement d'urgence : 1) OAP HYPERTENSIF / NORMOTENSIF « chaud et humide » (PAS >= 110–140 mmHg : VASODILATATEURS nitrés IV + diurétiques + VNI) ; 2) OAP HYPOTENSIF / CHOC CARDIOGÉNIQUE « froid et humide » (PAS < 90 mmHg, marbrures : PAS de nitrés !! -> inotropes Dobutamine/Noradrénaline + revascularisation si SCA !).",
      clinicalSigns = "Dyspnée aiguë dramatique souvent NOCTURNE obligeant le patient à S'ASSEOIR AU BORD DU LIT (ORTHOPNÉE EXTRÊME, angoisse de mort imminente !), toux quinteuse ramenant parfois une EXPECTORATION MOUSSEUSE ROSÉE « SAUMONÉE », sueurs profuses, polypnée, tirage. AUSCULTATION PULMONAIRE : RÂLES CRÉPITANTS FINS BILATÉRAUX SYMÉTRIQUES ASCENDANTS « EN MARÉE MONTANTE » (des bases vers les sommets !), parfois associés à des sibilants (« pseudo-asthme cardiaque » chez le sujet âgé !). Rechercher galop B3, souffle valvulaire, HTA ou signes d'IVD associés.",
      paraclinicalExams = listOf(
        "ECG 12 DÉRIVATIONS EN EXTRÊME URGENCE (rechercher un SCA ST+ ou ST-, une fibrillation auriculaire rapide ou un bloc de branche gauche !)",
        "Radiographie thoracique (au lit assis) : OPACITÉS ALVÉOLAIRES FLOCONNEUSES BILATÉRALES SYMÉTRIQUES PÉRI-HILAIRES « EN AILES DE PAPILLON », cardiomégalie, lignes B de Kerley, redistribution vasculaire vers les sommets, comblement pleural fréquent",
        "Échographie pulmonaire (nombreuses lignes B bilatérales diffuses = « fusées pleurales ») + Échocardiographie transthoracique (FEVG, pressions de remplissage élevées, valvulopathie aiguë, cinétique segmentaire !)",
        "Biologie : BNP ou NT-proBNP très élevés (confirme l'origine cardiaque avec forte sensibilité !), Troponine (infarctus gâchette ?), Gaz du sang artériels, Ionogramme/Créatinine, NFS, CRP"
      ),
      diagnosticCriteria = listOf(
        "Orthopnée aiguë + Crépitants bilatéraux « en marée montante » + Opacités alvéolaires péri-hilaires bilatérales / lignes B + BNP/NT-proBNP élevés et/ou élévation des pressions de remplissage à l'ETT"
      ),
      clinicalForms = listOf(
        "OAP « flash » hypertensif (installation explosive en quelques minutes sur HTA maligne ou sténose bilatérale des artères rénales -> cède spectaculairement sous dérivés nitrés IV + VNI !)",
        "« Pseudo-asthme cardiaque » (crépitants masqués par des sibilants d'œdème péribronchique -> piège chez le sujet âgé : ne pas donner de bêta-2 mimétiques qui aggravent la tachycardie sans vérifier BNP/radio/ECG !)"
      ),
      evolutionAndPrognosis = "Amélioration spectaculaire en 30–60 minutes sous traitement vasodilatateur/diurétique/VNI adapté, sauf si choc cardiogénique ou infarctus massif sous-jacent nécessitant une prise en charge interventionnelle.",
      managementPrinciples = listOf(
        "Position ASSISE stricte jambes pendantes au bord du lit (diminue immédiatement le retour veineux !)",
        "Oxygénothérapie immédiate + VENTILATION NON INVASIVE (VNI en mode CPAP ou VS-AI-PEP — réduit considérablement la mortalité et le recours à l'intubation dans l'OAP !!)",
        "DÉRIVÉS NITRÉS IV (Isosorbide dinitrate / Trinitrine en bolus/IVSE) EN 1ÈRE LIGNE SI PAS >= 100–110 mmHg (vasodilatation veineuse et artériolaire rapide -> baisse immédiate de la précharge/postcharge !)",
        "DIURÉTIQUE DE L'ANSE IV (Furosémide 40 à 80 mg IVD) + Traitement de la cause déclenchante (coronarographie si SCA, ralentir/cardioverter une FA rapide, anti-HTA, ATB si pneumonie déclenchante !)"
      ),
      differentialDiagnoses = listOf(
        "OAP lésionnel / SDRA (cœur normal, pression capillaire normale, contexte infectieux/pancréatite/polytrauma — BNP bas et absence d'orthopnée au profit d'une hypoxémie réfractaire !)",
        "Asthme aigu ou Exacerbation de BPCO (antécédent connu, sibilants purs sans crépitants, BNP normal)",
        "Embolie pulmonaire massive, Pneumopathie bilatérale sévère"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Détresse nocturne hypertensive avec expectoration mousseuse",
      clinicalCaseScenario = "M. V., 76 ans (cas fictif pédagogique), hypertendu mal suivi, est réveillé à 02h00 du matin par une sensation d'étouffement brutal l'obligeant à s'asseoir au bord du lit, avec une toux ramenant des crachats mousseux rosés. PA 210/115 mmHg, FC 112/min régulière, FR 32/min, SpO2 83% AA. Auscultation : crépitants fins bilatéraux montant jusqu'aux omoplates. ECG : HVG électrique sans sus-décalage ST. Radio au lit : opacités floconneuses péri-hilaires bilatérales en « ailes de papillon ».",
      clinicalCaseCorrection = "1) ŒDÈME AIGU DU POUMON (OAP) CARDIOGÉNIQUE HYPERTENSIF typique (« flash »).\n" +
        "2) Prise en charge immédiate en urgence : laisser ASSIS jambes pendantes, mise sous VNI (CPAP/BiPAP) + O2 pour SpO2 >= 94%, DÉRIVÉS NITRÉS IV en première intention (PAS très élevée à 210 mmHg !) + FUROSÉMIDE 40–80 mg IVD.\n" +
        "3) Dosages ECG/Troponine cinétique + BNP + Ionogramme/Créatinine + ETT dès stabilisation (rechercher sténose des artères rénales si OAP flash récidivants !).",
      keyPoints = listOf(
        "Triade OAP cardiogénique : Orthopnée nocturne + Crépitants bilatéraux en marée montante + Expectoration mousseuse rosée",
        "Radio : Opacités péri-hilaires bilatérales en « ailes de papillon » ; Toujours faire ECG + Troponine (chercher SCA gâchette !)",
        "Traitement clé immédiat si PAS >= 110 : Position assise + VNI (CPAP) + Dérivés nitrés IV + Furosémide IV"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Distinction OAP Cardiogénique vs OAP Lésionnel (SDRA)",
          headers = listOf("Critère", "OAP Cardiogénique", "OAP Lésionnel (SDRA)"),
          rows = listOf(
            listOf("Mécanisme", "Hyper-pression capillaire gauche (> 25 mmHg)", "Lésion membrane alvéolo-capillaire (perméabilité)"),
            listOf("Clinique / Cause", "Cardiopathie, HTA, SCA, orthopnée", "Sepsis, pneumonie grave, pancréatite, polytrauma"),
            listOf("BNP / NT-proBNP", "Très élevé", "Normal ou faiblement élevé"),
            listOf("Échocardiographie", "Pressions de remplissage élevées / FEVG altérée", "Pressions de remplissage normales"),
            listOf("Traitement", "VNI + Nitrés IV + Diurétiques IV", "Ventilation protectrice + Décubitus ventral + Traitement cause")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_15_sdra",
      title = "15. Syndrome de Détresse Respiratoire Aiguë (SDRA)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "OAP lésionnel inflammatoire — Critères de Berlin et ventilation protectrice en réanimation",
      definition = "Œdème pulmonaire lésionnel aigu non cardiogénique caractérisé par une inflammation diffuse de la membrane alvéolo-capillaire entraînant une hypoxémie sévère réfractaire à l'oxygène et des opacités pulmonaires bilatérales diffuses.",
      epidemiology = "Représente ~10% des admissions en réanimation ; mortalité toujours lourde (30 à 45% selon la sévérité de l'hypoxémie et les défaillances d'organes associées).",
      etiologiesAndRiskFactors = listOf(
        "Causes PULMONAIRES DIRECTES : Pneumonies infectieuses graves (bactériennes, grippe, COVID-19 — cause n°1 !), Inhalation gastrique acide (syndrome de Mendelson !), Contusion pulmonaire, Noyade, Inhalation toxique",
        "Causes EXTRAPULMONAIRES INDIRECTES : CHOC SEPTIQUE de toute origine (péritonite, pyélonéphrite...), Pancréatite aiguë nécrosante sévère, Polytraumatisme / Polytransfusion (TRALI !), Embolie graisseuse, Brûlures étendues"
      ),
      physiopathology = "Orage inflammatoire libérant cytokines et polynucléaires neutrophiles activés -> destruction des pneumocytes I/II et de l'endothélium capillaire -> fuite d'un exsudat riche en protéines et fibrine (membranes hyalines) + perte du surfactant -> collapsus alvéolaire massif (« baby lung » = poumon réduit à un petit volume aéré rigide) et shunt intrapulmonaire vrai (insensible à la simple hausse de FiO2 !).",
      classificationAndPathology = "Critères diagnostiques de BERLIN (2012) — 4 CRITÈRES OBLIGATOIRES : 1) Délai AIGU (< 7 jours après agression connue) ; 2) Imagerie : opacités alvéolo-interstitielles BILATÉRALES non expliquées par épanchement/atélectasie ; 3) Origine : non expliquée entièrement par insuffisance cardiaque ou surcharge hydrique (vérifier par ETT !) ; 4) Hypoxémie sous PEP >= 5 cmH2O : Léger (200 < PaO2/FiO2 <= 300), Modéré (100 < PaO2/FiO2 <= 200), SÉVÈRE (PaO2/FiO2 <= 100 mmHg).",
      clinicalSigns = "Installation rapide (< 72h–7 jours après facteur déclenchant) d'une détresse respiratoire majeure : polypnée extrême, tirage intense, cyanose ne se corrigeant PAS malgré une oxygénothérapie à haut débit (hypoxémie réfractaire !), crépitants diffus aux deux champs sans signe d'insuffisance cardiaque (pas d'orthopnée nocturne ancienne, pas de turgescence jugulaire ni OMI de surcharge). Évolution très rapide vers l'épuisement.",
      paraclinicalExams = listOf(
        "Gaz du sang artériels sous oxygène connu (calcul impératif du rapport PaO2 / FiO2 <= 300 mmHg !)",
        "Radiographie et Scanner thoracique : opacités alvéolaires et en verre dépoli bilatérales diffuses hétérogènes prédominant dans les zones déclives postérieures, sans cardiomégalie ni redistribution apicale",
        "Échocardiographie transthoracique (ETT) INDISPENSABLE pour éliminer une composante cardiogénique (OAP hémodynamique) et rechercher un cœur pulmonaire aigu sur HTAP",
        "Bilan étiologique complet selon contexte : prélèvements microbiologiques respiratoires/hémocultures, lipasémie, scanner abdominal si sepsis occulte"
      ),
      diagnosticCriteria = listOf(
        "Les 4 critères de Berlin réunis : Aigu (< 7 jours) + Opacités bilatérales + Absence d'OAP hydrostatique pur + PaO2/FiO2 <= 300 mmHg avec PEP >= 5 cmH2O"
      ),
      clinicalForms = listOf(
        "SDRA léger (PaO2/FiO2 201–300), Modéré (101–200), Sévère (<= 100 mmHg)",
        "SDRA post-transfusionnel (TRALI survenant < 6h après transfusion de plasma/culots !)"
      ),
      evolutionAndPrognosis = "3 phases anatomocliniques : Exsudative (J1–J7, membranes hyalines), Proliférative (J7–J21) et Fibrose séquellaire éventuelle. Pronostic lié à l'étiologie et à l'absence de lésions induites par la ventilation mécanique (barotraumatisme/volutraumatisme).",
      managementPrinciples = listOf(
        "Admission IMMÉDIATE en RÉANIMATION",
        "VENTILATION MÉCANIQUE PROTECTRICE (pierre angulaire prouvée diminuant la mortalité !) : PETIT VOLUME COURANT (Vt = 6 mL/kg de poids idéal théorique, calculé sur la TAILLE et non le poids réel !), pression de plateau <= 30 cmH2O, PEP élevée adaptée, hypercapnie permissive modérée tolérée",
        "Si SDRA modéré à sévère (PaO2/FiO2 < 150 mmHg) : SÉANCES DE DÉCUBITUS VENTRAL (16h/jour — améliore nettement la survie par recrutement alvéolaire postérieur !!) + Curarisation précoce brève (48h) + Recours à l'ECMO veino-veineuse si hypoxémie réfractaire ultime",
        "Traitement agressif de la CAUSE (antibiothérapie précoce si sepsis, drainage foyer), gestion restrictive des apports hydriques après phase de choc initiale"
      ),
      differentialDiagnoses = listOf(
        "OAP cardiogénique (critères ETT + BNP -> s'améliore vite sous diurétiques/nitrés/VNI !)",
        "Hémorragie intra-alvéolaire diffuse (vascularites ANCA/Goodpasture -> LBA hémorragique avec sidérophages !)",
        "Pneumopathie organisée cryptogénique ou pneumopathie aiguë à éosinophiles (corticosensible -> intérêt du LBA si cause infectieuse douteuse !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Hypoxémie réfractaire post-pancréatite aiguë",
      clinicalCaseScenario = "M. E., 49 ans (cas fictif pédagogique), hospitalisé depuis 48h en soins continus pour une pancréatite aiguë sévère biliaire, développe rapidement une polypnée à 36/min et une cyanose. Malgré un masque haute concentration (FiO2 estimée 0.8), SpO2 à 82%. GDS : PaO2 48 mmHg (PaO2/FiO2 = 60 mmHg !). Radio thorax au lit : infiltrats alvéolaires cotonneux bilatéraux diffus épargnant les culs-de-sac. ETT au lit : FEVG à 65%, pressions de remplissage VG basses/normales.",
      clinicalCaseCorrection = "1) SDRA SÉVÈRE (Critères de Berlin : < 7j après agression connue = pancréatite aiguë ; opacités bilatérales diffuses ; ETT excluant OAP cardiogénique ; PaO2/FiO2 = 60 <= 100 mmHg).\n" +
        "2) Transfert immédiat en RÉANIMATION pour intubation et ventilation mécanique PROTECTRICE (Vt 6 mL/kg de poids idéal théorique basé sur la taille, pression plateau <= 30 cmH2O, PEP optimisée).\n" +
        "3) Devant PaO2/FiO2 < 150 mmHg : curarisation initiale + DÉCUBITUS VENTRAL >= 16h/jour (et discussion ECMO-VV si échec) + poursuite prise en charge pancréatite.",
      keyPoints = listOf(
        "4 critères de Berlin du SDRA : Délai < 7j + Opacités bilatérales + Non cardiogénique (ETT !) + PaO2/FiO2 <= 300 sous PEP >= 5",
        "Ventilation protectrice OBLIGATOIRE = Petit volume courant (6 mL/kg de poids idéal théorique) + Pplateau <= 30 cmH2O",
        "SDRA modéré/sévère (PaO2/FiO2 < 150) = Décubitus ventral >= 16h/jour + Curarisation précoce"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Classification de gravité de Berlin du SDRA (avec PEP/CPAP >= 5 cmH2O)",
          headers = listOf("Stade de gravité", "Rapport PaO2 / FiO2", "Mortalité approximative", "Mesure spécifique associée"),
          rows = listOf(
            listOf("Léger", "200 < PaO2/FiO2 <= 300", "~27 %", "Ventilation protectrice (Vt 6 mL/kg)"),
            listOf("Modéré", "100 < PaO2/FiO2 <= 200", "~32 %", "PEP optimisée + Décubitus ventral si < 150"),
            listOf("Sévère", "PaO2 / FiO2 <= 100 mmHg", "~45 %", "Décubitus ventral >= 16h/j + Curare +/- ECMO")
          )
        )
      )
    )
  )
}
