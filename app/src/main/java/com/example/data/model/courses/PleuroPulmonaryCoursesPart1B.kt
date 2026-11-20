package com.example.data.model.courses

import com.example.data.model.SemiologyCategory

object PleuroPulmonaryCoursesPart1B {
  val courses: List<MedicalCourse> = listOf(
    MedicalCourse(
      id = "course_resp_06_asthme",
      title = "6. Asthme Bronchique & Asthme Aigu Grave (AAG)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Inflammation bronchique chronique réversible — Reconnaître le silence auscultatoire de l'AAG",
      definition = "Maladie inflammatoire chronique des voies aériennes caractérisée par des épisodes paroxystiques et réversibles de bronchospasme (dyspnée sifflante, oppression thoracique, toux) associés à une hyperréactivité bronchique.",
      epidemiology = "Touche ~4 millions de personnes en France (~6–10% des adultes, 10% des enfants). Encore responsable d'environ 800 à 900 décès/an évitables par sous-estimation de la gravité ou défaut de corticothérapie inhalée de fond.",
      etiologiesAndRiskFactors = listOf(
        "Asthme allergique extrinsèque (atopique — sujet jeune, rhinite allergique/eczéma, acariens, pollens, phanères d'animaux)",
        "Asthme non allergique tardif ou professionnel (boulangers, coiffeurs, isocyanates)",
        "Facteurs déclenchants de crise : allergènes, infections virales (rhinovirus), effort/air froid, tabac, pollution, médicaments (BÊTABLOQUANTS même en collyre !, Aspirine/AINS dans le syndrome de Widal avec polypose naso-sinusienne !)"
      ),
      physiopathology = "Inflammation chronique à éosinophiles/Th2 (ou neutrophilique) de la muqueuse bronchique entraînant hyperréactivité musculaire lisse (bronchoconstriction), œdème muqueux et hypersécrétion de mucus épais -> freinage expiratoire variable réversible sous bronchodilatateurs.",
      classificationAndPathology = "Contrôle GINA (contrôlé / partiellement contrôlé / non contrôlé sur les 4 dernières semaines : symptômes diurnes > 2/semaine, réveils nocturnes, besoin de BDCA > 2/semaine, limitation d'activité). Exacerbation simple vs Asthme Aigu Grave (AAG).",
      clinicalSigns = "Crise typique : dyspnée expiratoire sifflante paroxystique volontiers NOCTURNE ou au petit matin, toux sèche quinteuse (parfois seul équivalent d'asthme !), oppression thoracique, sibilants expiratoires bilatéraux diffus à l'auscultation, réversible rapidement sous bêta-2 mimétique de courte durée d'action (BDCA). SIGNES DE GRAVITÉ DE L'ASTHME AIGU GRAVE (AAG) : difficulté à parler ou tousser, orthopnée (« assis penché en avant »), sueurs, cyanose, FR > 30/min, FC > 120/min, pouls paradoxal, DEP < 150 L/min (< 50% théorique) et ALERTE EXTRÊME = SILENCE AUSCULTATOIRE + NORMOCAPNIE/HYPERCAPNIE + TROUBLES DE CONSCIENCE !",
      paraclinicalExams = listOf(
        "SPIROMÉTRIE / EFR à distance d'une crise : Syndrome ventilatoire obstructif (VEMS/CVF < 0.70 ou < LIN) RÉVERSIBLE après bêta-2 mimétique (gain de VEMS >= +12% ET >= +200 mL !). Si EFR de base normale : test de provocation à la méthacholine (hyperréactivité bronchique)",
        "Débit Expiratoire de Pointe (DEP / Peak Flow) au lit du malade pour surveiller la crise",
        "Dans l'AAG : Gaz du sang artériels (hypoxémie + normocapnie ou hypercapnie = ÉPUISEMENT IMMINENT !!), Radio thorax (éliminer pneumothorax ou foyer infectieux déclenchant !)",
        "Bilan allergologique (Prick-tests / IgE spécifiques orientés), NFS (éosinophilie)"
      ),
      diagnosticCriteria = listOf(
        "Symptômes respiratoires variables paroxystiques + Preuve objective d'une limitation variable et réversible des débits expiratoires (EFR avec réversibilité >= +12% et >= +200 mL ou variabilité du DEP > 10–20%)"
      ),
      clinicalForms = listOf(
        "Asthme d'effort / induit par l'exercice ; Toux chronique isolée équivalent d'asthme",
        "Syndrome de Widal (Triade : Asthme sévère + Polypose naso-sinusienne + Intolérance à l'aspirine/AINS)",
        "Asthme aigu grave (AAG) suraigu asphyxique ou résistant au salbutamol"
      ),
      evolutionAndPrognosis = "Excellent pronostic sous traitement de fond régulier par corticostéroïdes inhalés (CSI). En l'absence de CSI : remodelage bronchique irréversible (trouble obstructif fixé) et risque d'AAG mortel.",
      managementPrinciples = listOf(
        "CRISE / AAG : Nébulisations répétées de Salbutamol + Ipratropium sous O2 (6–8 L/min pour SpO2 >= 94%) + CORTICOTHÉRAPIE SYSTÉMIQUE PRÉCOCE (Prednisolone 1 mg/kg PO ou Méthylprednisolone IV) + Sulfate de Magnésium IV si échec initial",
        "TRAITEMENT DE FOND (Recommandations GINA) : NE JAMAIS LAISSER UN ASTHMATIQUE SOUS BDCA SEUL ! Tout asthmatique doit recevoir des Corticoïdes Inhalés (CSI — ex: CSI-Formotérol à la demande dès le palier 1, puis quotidien continu selon paliers) ; éducation à la technique d'inhalation + plan d'action écrit + éviction des allergènes/tabac !",
        "Asthme sévère non contrôlé sous forte dose CSI+LABA : phénotypage et biothérapies (anti-IgE, anti-IL5/IL5R, anti-IL4R)"
      ),
      differentialDiagnoses = listOf(
        "BPCO (fumeur > 40 ans, dyspnée continue peu/non réversible), Insuffisance cardiaque gauche (« pseudo-asthme cardiaque » avec crépitants des bases chez le sujet âgé !), Corps étranger trachéo-bronchique (sibilant unilatéral fixe !), Dysfonction des cordes vocales, Embolie pulmonaire"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Crise d'asthme sévère avec piège gazométrique",
      clinicalCaseScenario = "Mlle R., 24 ans (cas fictif pédagogique), asthmatique utilisant uniquement de la Ventoline (Salbutamol) 4 fois/jour sans corticoïde inhalé, est amenée aux urgences pour une crise ne cédant pas depuis 6 heures. Elle est assise penchée en avant, sue abondamment, prononce difficilement 2 mots, FR 34/min, FC 132/min, SpO2 89% AA. À l'auscultation : thorax distendu quasi SILENCIEUX (rares sibilants faibles). Gaz du sang AA : pH 7.36, PaO2 56 mmHg, PaCO2 43 mmHg !",
      clinicalCaseCorrection = "1) ASTHME AIGU GRAVE (AAG) avec signes de défaillance respiratoire imminente (incapacité de parler, sueurs, tachypnée > 30, tachycardie > 120, SILENCE AUSCULTATOIRE, et surtout NORMOCAPNIE à 43 mmHg chez un patient qui devrait hyperventiler et avoir une hypocapnie = ÉPUISEMENT VENTILATOIRE IMMINENT !!).\n" +
        "2) Urgence de réanimation/SAUV : O2 haut débit, nébulisations continues/répétées Salbutamol + Bromure d'ipratropium, Méthylprednisolone IV 60–80 mg + Sulfate de Magnésium 2g IV sur 20 min, préparation intubation si aggravation.\n" +
        "3) À la sortie : éducation thérapeutique stricte et instauration impérative d'un traitement de fond par CSI-Formotérol (jamais de Salbutamol isolé !).",
      keyPoints = listOf(
        "Diagnostic positif d'asthme = EFR avec syndrome obstructif RÉVERSIBLE (>= +12% et >= +200 mL après BDCA)",
        "Drapeau rouge ultime de l'AAG : SILENCE AUSCULTATOIRE + NORMOCAPNIE/HYPERCAPNIE aux gaz du sang = épuisement respiratoire et arrêt imminent !",
        "Règle GINA : JAMAIS de BDCA seul sans corticoïde inhalé associé ! Attention aux bêtabloquants (même collyres) et à l'aspirine/AINS (Widal)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Signes d'alerte évolutifs d'une crise d'asthme vers l'AAG",
          headers = listOf("Paramètre", "Crise légère/modérée", "Asthme Aigu Grave (AAG)", "Arrêt respiratoire imminent"),
          rows = listOf(
            listOf("Parole", "Phrases complètes", "Mots entrecoupés", "Impossible"),
            listOf("Fréquence resp.", "Augmentée < 25–30/min", "> 30/min", "Bradypnée / pauses"),
            listOf("Auscultation", "Sibilants expiratoires nets", "Sibilants forts ou diminués", "SILENCE auscultatoire"),
            listOf("PaCO2 (GDS)", "< 35 mmHg (hypocapnie)", "35–42 mmHg (normalisation piège !)", "> 45 mmHg (acidose resp.)")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_07_bpco",
      title = "7. Bronchopneumopathie Chronique Obstructive (BPCO)",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Limitation ventilatoire chronique NON complètement réversible — Rôle absolu de l'EFR et du sevrage tabagique",
      definition = "Maladie respiratoire chronique fréquente, évitable et traitable, caractérisée par une obstruction bronchique chronique persistante et NON complètement réversible définie par la spirométrie (VEMS/CVF < 0.70 après bronchodilatateur).",
      epidemiology = "Touche ~7.5% des adultes > 40 ans (~3.5 millions de personnes en France, très sous-diagnostiquée dans > 70% des cas !). 3e cause de mortalité mondiale.",
      etiologiesAndRiskFactors = listOf(
        "TABAGISME actif ou passif (> 85% des cas en France — généralement >= 15–20 PA !)",
        "Expositions professionnelles/domestiques : poussières minérales (silice, charbon), gaz toxiques, fumées de biomasse (cuisine au feu de bois)",
        "Déficit génétique en Alpha-1-antitrypsine (à rechercher systématiquement si BPCO précoce < 45 ans ou sujet peu/non fumeur avec emphysème panlobulaire des bases !)"
      ),
      physiopathology = "L'agression chronique par la fumée provoque : 1) Inflammation et fibrose des petites bronches (bronchiolite oblitérante) ; 2) Destruction des parois alvéolaires avec perte du rappel élastique (EMPHYSÈME centrolobulaire des sommets) -> collapsus expiratoire précoce des petites voies aériennes, distension thoracique dynamique (piégeage d'air) et hyperinflation, puis hypoxémie/hypercapnie et cœur pulmonaire chronique.",
      classificationAndPathology = "Stades spirométriques de gravité GOLD (sur le VEMS post-BD en % théorique, une fois VEMS/CVF < 0.70) : GOLD 1 léger >= 80% ; GOLD 2 modéré 50–79% ; GOLD 3 sévère 30–49% ; GOLD 4 très sévère < 30%. Évaluation clinique combinée GOLD ABE (symptômes mMRC/CAT + nombre d'exacerbations).",
      clinicalSigns = "Bronchite chronique clinique (toux + expectoration muqueuse >= 3 mois/an pendant >= 2 ans consécutifs — mais ne suffit pas sans EFR !), DYSPNÉE D'EFFORT PROGRESSIVE (quantifiée par l'échelle mMRC de 0 à 4), tolérance tardivement très réduite. Examen souvent normal au début ; puis thorax en tonneau (distension), diminution du murmure vésiculaire, sibilants/ronchi, expiration lèvres pincées, signe de Hoover, signe de Campbell ; au stade évolué : cyanose et signes d'insuffisance cardiaque droite (OMI, TJ).",
      paraclinicalExams = listOf(
        "SPIROMÉTRIE / EFR AVEC TEST DE RÉVERSIBILITÉ = EXAMEN INDISPENSABLE AU DIAGNOSTIC : VEMS/CVF < 0.70 APRÈS BRONCHODILATATEUR (syndrome obstructif persistant non complètement réversible !), mesure du Volume Résiduel (VR > 120% = distension) et DLCO (abaissée si emphysème)",
        "Radiographie thoracique (distension : coupoles diaphragmatiques aplaties, hyperclarté, espaces intercostaux élargis, cœur « en goutte » ; et recherche cancer bronchique !)",
        "Gaz du sang artériels (dès VEMS < 50% ou SpO2 < 92% -> vérifier indication O2 longue durée !)",
        "Dosage de l'Alpha-1-antitrypsine (si < 45 ans ou non-fumeur ou ATCD familial), NFS (polyglobulie réactionnelle à l'hypoxie ?), ETT (HTAP ?)"
      ),
      diagnosticCriteria = listOf(
        "Diagnostic EXCLUSIVEMENT SPIROMÉTRIQUE : VEMS / CVF < 0.70 après inhalation de bronchodilatateur chez un sujet symptomatique ou exposé (tabac > 40 ans)"
      ),
      clinicalForms = listOf(
        "Phénotype « Pink Puffer » (emphysémateux pur : maigre, très dyspnéique, peu hypoxémique au repos, thorax très distendu)",
        "Phénotype « Blue Bloater » (bronchitique chronique hypoxique-hypercapnique, cyanose, polyglobulie, cœur pulmonaire droit)",
        "Exacerbation aiguë de BPCO (EABPCO : majoration aiguë de la dyspnée, de la toux et/ou du volume/purulence des crachats — causes : infection virale/bactérienne, pollution, EP !!)"
      ),
      evolutionAndPrognosis = "Déclin accéléré du VEMS si poursuite du tabac (seul le sevrage tabagique freine le déclin du VEMS !). Exacerbations fréquentes accélérant la mortalité ; risque majeur de cancer bronchopulmonaire (terrain tabagique identique !).",
      managementPrinciples = listOf(
        "1) SEVRAGE TABAGIQUE COMPLET ET DÉFINITIF (mesure n°1 la plus efficace !) + Vaccinations (Grippe, Pneumocoque, COVID, VRS, Coqueluche) + Réhabilitation respiratoire",
        "2) Bronchodilatateurs inhalés de longue durée d'action (LAMA et/ou LABA selon groupe GOLD ABE) ; Corticoïdes inhalés UNIQUEMENT ajoutés si exacerbations répétées avec éosinophiles >= 300/mm³ (jamais de CSI seuls en monothérapie dans la BPCO !!)",
        "3) Oxygénothérapie de longue durée (OLD >= 15h/jour) si PaO2 <= 55 mmHg (ou <= 60 mmHg avec polyglobulie/HTAP/cœur droit) à distance d'une exacerbation",
        "4) EXACERBATION AIGUË : Bronchodilatateurs BDCA, O2 TITRÉ PRUDENT (cible SpO2 88–92% pour ne pas aggraver l'hypercapnie !!), Corticoïdes systémiques courts 5 jours (40 mg/j), Antibiothérapie (Amoxicilline ou Amox-Ac clavulanique) uniquement si crachats franchement PURULENTS ou forme grave, VNI immédiate si acidose respiratoire pH < 7.35 avec hypercapnie !"
      ),
      differentialDiagnoses = listOf(
        "Asthme chronique (réversibilité complète ou très marquée à l'EFR), Insuffisance cardiaque chronique, Bronchectasies diffuses, Mucoviscidose, Cancer bronchique"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Exacerbation aiguë acidosique de BPCO",
      clinicalCaseScenario = "M. P., 67 ans (cas fictif pédagogique), fumeur à 45 PA, BPCO GOLD 3 connue, est amené par les pompiers pour aggravation de sa dyspnée depuis 3 jours avec expectorations verdâtres abondantes. Il a reçu 12 L/min d'O2 au masque haute concentration dans le camion. À l'arrivée : somnolent, astérixis (flapping tremor) des mains, sueurs, SpO2 98%. GDS : pH 7.26, PaO2 110 mmHg, PaCO2 78 mmHg, HCO3- 34 mmol/L.",
      clinicalCaseCorrection = "1) Acidose respiratoire aiguë décompensée hypercapnique sur Exacerbation de BPCO (surinfection bronchique purulente) AGGRAVÉE PAR L'HYPEROXIE NON TITRÉE (O2 à 12 L/min levant l'effet Haldane et l'hypoventilation hypoxique !).\n" +
        "2) Urgence absolue : Baisser immédiatement le débit d'O2 pour viser une cible SpO2 88–92% (jamais 98–100% !) + Mise en place de VENTILATION NON INVASIVE (VNI) au masque en urgence (pH < 7.35 + hypercapnie !).\n" +
        "3) Associer nébulisations BDCA, Prednisolone 40 mg/j x 5j, Amoxicilline-Acide clavulanique (car crachats purulents + gravité).",
      keyPoints = listOf(
        "Diagnostic de BPCO = SPIROMÉTRIE OBLIGATOIRE (VEMS/CVF < 0.70 post-BD)",
        "Cible d'oxygénothérapie chez le BPCO hypercapnique = SpO2 88–92% (JAMAIS d'O2 à haut débit incontrôlé !)",
        "Acidose respiratoire aiguë (pH < 7.35 + PaCO2 élevée) sur EABPCO = Indication formelle de VNI urgente !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Classification spirométrique GOLD de la BPCO (si VEMS/CVF < 0.70 post-BD)",
          headers = listOf("Stade GOLD", "Gravité", "VEMS post-BD (% valeur théorique)"),
          rows = listOf(
            listOf("GOLD 1", "Légère", ">= 80 %"),
            listOf("GOLD 2", "Modérée", "50 % <= VEMS < 80 %"),
            listOf("GOLD 3", "Sévère", "30 % <= VEMS < 50 %"),
            listOf("GOLD 4", "Très sévère", "< 30 %")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_08_pleuresie",
      title = "8. Pleurésie / Épanchement Pleural Liquidien",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Trépied liquide pleural + Critères de Light (Transsudat vs Exsudat)",
      definition = "Présence anormale de liquide dans la cavité pleurale (entre feuillet viscéral et feuillet pariétal), distinguée en transsudat (déséquilibre mécanique hydrostatique/oncotique sur plèvre saine) et exsudat (lésion inflammatoire, infectieuse ou tumorale de la plèvre).",
      epidemiology = "Motif très fréquent de consultation pneumologique et d'hospitalisation. Chez le sujet âgé : Insuffisance cardiaque (transsudat n°1) et Cancers/Mésothéliome (exsudats majeurs) ; chez le sujet jeune : infection parapneumonique et Tuberculose.",
      etiologiesAndRiskFactors = listOf(
        "TRANSSUDATS (plèvre saine — traitement de la cause générale !) : Insuffisance cardiaque gauche/globale (~80% !), Cirrhose hépatique (hydrothorax hépatique droit ++), Syndrome néphrotique, Embolie pulmonaire parfois, Atélectasie",
        "EXSUDATS (plèvre malade — exploration étiologique impérative !) : Cancers (métastases pleurales sein/poumon ++, mésothéliome sur amiante !), Infections (pleurésie parapneumonique / empyème purulent, Tuberculose pleurale !), Embolie pulmonaire (infarctus), Maladies de système (Lupus, PR), Pancréatite aiguë / perforation œsophage (gauche riche en amylase !)"
      ),
      physiopathology = "La cavité pleurale contient normalement < 15 mL de liquide lubrifiant. Une élévation de la pression hydrostatique capillaire (ICG) ou chute de la pression oncotique (cirrhose/néphrotique) filtre un liquide pauvre en protéines (transsudat). Une perméabilité augmentée ou obstruction lymphatique laisse passer protéines et cellules (exsudat).",
      classificationAndPathology = "Critères biochimiques de LIGHT (Exsudat si AU MOINS UN des 3 critères suivants) : 1) Protides pleuraux / Protides sériques > 0.5 (ou protides pleuraux > 30 g/L en pratique simple) ; 2) LDH pleuraux / LDH sériques > 0.6 ; 3) LDH pleuraux > 2/3 de la limite supérieure des LDH sériques.",
      clinicalSigns = "Dyspnée proportionnelle à l'abondance, douleur thoracique latérale « en point de côté » augmentée à l'inspiration et à la toux (disparaît souvent quand le liquide devient très abondant !), toux sèche déclenchée par les changements de position. EXAMEN PHYSIQUE = TRÉPIED D'ÉPANCHEMENT PLEURAL LIQUIDIEN : 1) ABOLITION des vibrations vocales à la palpation ; 2) MATITÉ franche hydrique, déclive, à limite supérieure concave en haut et en dedans (courbe de Damoiseau) ; 3) ABOLITION du murmure vésiculaire à l'auscultation (+/- souffle pleurétique doux au bord supérieur).",
      paraclinicalExams = listOf(
        "Radiographie thoracique : opacité dense homogène déclive effaçant la coupole et le cul-de-sac costo-diaphragmatique à limite concave en haut (courbe de Damoiseau) ; si massif : hémithorax blanc opaque AVEC REFOULEMENT du médiastin vers le côté opposé !",
        "Échographie pleurale : CONFIRME le liquide (anechoïque/cloisonné), quantifie et REPÈRE le point de ponction en sécurité",
        "PONCTION PLEURALE EXPLORATRICE (obligatoire devant toute pleurésie non étiquetée ou unilatérale > 1 cm, sauf insuffisance cardiaque bilatérale typique répondant aux diurétiques !) : Aspect macroscopique + Biochimie (Protides, LDH selon Light, Glucose, pH si suspicion d'empyème !) + Cytologie + Bactériologie (direct, culture, BK si lymphocyte !) ; Biopsies pleurales / Thoracoscopie si exsudat inexpliqué suspect de néoplasie/TB"
      ),
      diagnosticCriteria = listOf(
        "Trépied clinique (matité + abolition VV + abolition MV) + Imagerie (écho/radio) + Analyse ponction pleurale selon critères de Light"
      ),
      clinicalForms = listOf(
        "Pleurésie purulente / Empyème (pus franc, pH < 7.20, glucose < 2.2 mmol/L -> DRAINAGE THORACIQUE EN URGENCE !)",
        "Pleurésie hémorragique (hématocrite pleural < 50% sang -> cancer/EP ; >= 50% -> Hémothorax !)",
        "Pleurésie chyleuse (aspect laiteux riche en triglycérides > 1.1 g/L -> rupture canal thoracique/lymphome)"
      ),
      evolutionAndPrognosis = "Transsudats résolutifs sous traitement diurétique/étiologique. Exsudats purulents exposant à la pachypleurite rétractile s'ils ne sont pas drainés tôt ; pleurésies néoplasiques souvent récidivantes (talcage / pleurodèse / cathéter tunnelisé).",
      managementPrinciples = listOf(
        "Ponction évacuatrice si mauvaise tolérance respiratoire (sans dépasser 1 à 1.5 L par séance pour éviter l'œdème de réexpansion a vacuo !)",
        "Transsudat : traiter l'organe malade (diurétiques si IC, ponction/régime si cirrhose)",
        "Pleurésie purulente / pH < 7.20 : DRAINAGE THORACIQUE D'URGENCE + lavage + antibiothérapie IV adaptée",
        "Pleurésie néoplasique récidivante : symphyse pleurale (talcage par thoracoscopie)"
      ),
      differentialDiagnoses = listOf(
        "Atélectasie massive (donne matité + abolition MV mais ATTRACTIVE : médiastin et trachée déviés VERS le côté opaque !), Pneumonie (vibrations vocales AUGMENTÉES + bronchogramme), Épaississement pleural séquellaire (échographie tranche immédiatement !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Pleurésie fébrile post-pneumonie",
      clinicalCaseScenario = "M. H., 46 ans (cas fictif pédagogique), traité en ville depuis 6 jours par Amoxicilline pour une pneumopathie droite, consulte aux urgences pour persistance de la fièvre à 39.2°C et dyspnée croissante. Base droite : matité franche, abolition des vibrations vocales et du murmure vésiculaire. Radio : opacité droite effaçant la coupole avec ligne bordante de Damoiseau. L'échographie retrouve un épanchement abondant finement cloisonné. Ponction : liquide trouble jaunâtre, Protides 48 g/L (sérique 68), pH pleural 7.08, Glucose 1.1 mmol/L, 92% PNN altérés.",
      clinicalCaseCorrection = "1) Pleurésie EXSUDATIVE (Protides pl/s = 0.70 > 0.5) PURULENTE / COMPLIQUÉE PARAPNEUMONIQUE (pH < 7.20 + glucose effondré + PNN altérés + aspect cloisonné).\n" +
        "2) Ce n'est PAS une simple pleurésie réactionnelle bénigne : les antibiotiques seuls ne suffiront PAS !\n" +
        "3) Conduite d'urgence : DRAINAGE THORACIQUE IMMÉDIAT + élargissement antibiothérapie IV (ex: Amoxicilline-Acide clavulanique ou C3G + Métronidazole) + fibrinolytiques intrapleuraux si cloisonnements persistants.",
      keyPoints = listOf(
        "Trépied liquidien : Abolition vibrations vocales + Matité hydrique déclive + Abolition murmure vésiculaire",
        "Critères de Light (Protides P/S > 0.5 ou LDH P/S > 0.6) séparent Exsudat (plèvre malade) vs Transsudat (IC, cirrhose)",
        "Liquide trouble/purulent OU pH pleural < 7.20 = Pleurésie purulente compliquée -> DRAINAGE THORACIQUE EN URGENCE !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Orientation diagnostique du liquide pleural (Ponction exploratrice)",
          headers = listOf("Aspect / Analyse", "Orientation principale", "Geste / Bilan clé"),
          rows = listOf(
            listOf("Citrin Transsudat (Light -)", "Insuffisance cardiaque, Cirrhose, Néphrotique", "Traiter cause (diurétiques) — pas de biopsie"),
            listOf("Citrin Exsudat Lymphocytaire", "Tuberculose, Cancer, Lymphome", "Culture BK + Cytologie + Biopsie pleurale/TDM"),
            listOf("Trouble ou pH < 7.20 / PNN", "Pleurésie purulente / Empyème", "DRAINAGE THORACIQUE URGENT + ATB IV"),
            listOf("Hémorragique (non traumatique)", "Cancer métastatique / Mésothéliome / EP", "Cytologie + Angioscanner + Thoracoscopie")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_09_pneumothorax",
      title = "9. Pneumothorax Spontané & Compressif",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Trépied gazeux (Tympanisme + Silence) — Exsufflation urgente si forme suffocante",
      definition = "Irruption d'air dans la cavité pleurale virtuelle entre les feuillets viscéral et pariétal, entraînant un décollement et un collapsus partiel ou total du poumon sous-jacent.",
      epidemiology = "Pneumothorax spontané primaire : classique chez l'homme jeune (20–35 ans), grand, mince, FUMEUR (90% des cas !). Pneumothorax secondaire sur poumon pathologique (BPCO, emphysème, asthme, fibrose) souvent plus grave.",
      etiologiesAndRiskFactors = listOf(
        "Spontané PRIMAIRE (poumon apparemment sain) : rupture de petites bulles sous-pleurales apicales (« blebs ») favorisée massivement par le TABAC et le cannabis",
        "Spontané SECONDAIRE : BPCO/emphysème bulleux (++), asthme sévère, mucoviscidose, fibrose, cancer, pneumocystose, endométriose thoracique (pneumothorax cataménial droit pendant les règles !)",
        "Traumatique (plaie thoracique, fracture de côtes) ou Iatrogène (pose VVC sous-clavière, biopsie transthoracique, ponction pleurale)"
      ),
      physiopathology = "L'entrée d'air annule la pression négative pleurale : le poumon se rétracte vers le hile et la cage thoracique se distend. Dans le PNEUMOTHORAX COMPRESSIF (mécanisme à soupape unidirectionnelle), la pression pleurale devient fortement positive -> refoule le médiastin vers le côté sain -> plicature des veines caves et effondrement du retour veineux = CHOC OBSTRUCTIF MORTEL !",
      classificationAndPathology = "Petit vs Grand pneumothorax (selon critère SPLF/BTS : décollement >= 2 cm au hile ou >= 3 cm à l'apex en bande claire avasculaire complète). Surtout distinction vitale : Bien toléré vs Compressif/Mal toléré.",
      clinicalSigns = "Douleur thoracique unilatérale BRUTALE « en coup de poignard », bloquant l'inspiration profonde, suivie d'une dyspnée variable et d'une toux sèche irritative. EXAMEN PHYSIQUE = TRÉPIED GAZEUX UNILATÉRAL : 1) Abolition des vibrations vocales ; 2) TYMPANISME sonore à la percussion ; 3) Abolition du murmure vésiculaire (« silence auscultatoire »). SIGNES DE GRAVITÉ (PNEUMOTHORAX COMPRESSIF / SUFFOCANT) : polypnée intense, cyanose, hypotension/choc, turgescence jugulaire, déviation trachéale vers le côté SAIN, emphysème sous-cutané !",
      paraclinicalExams = listOf(
        "AUCUN EXAMEN D'IMAGERIE NE DOIT RETARDER LE GESTE DE DÉCOMPRESSION EN CAS DE PNEUMOTHORAX COMPRESSIF CLINIQUE !",
        "Radiographie thoracique de face en inspiration (plus de cliché systématique en expiration forcée dangereux si mal toléré !) : hyperclarté périphérique totalement AVASCULAIRE séparée du parenchyme pulmonaire rétracté par une fine ligne bordante pleurale viscérale !",
        "Échographie pleurale (absence de glissement pleural + signe du code-barres) ; Scanner thoracique utile uniquement si doute diagnostique (grande bulle d'emphysème géante !) ou bilan secondaire"
      ),
      diagnosticCriteria = listOf(
        "Triade tympanisme + abolition VV/MV + Hyperclarté périphérique avasculaire bordée par la plèvre viscérale à la radio thorax"
      ),
      clinicalForms = listOf(
        "Pneumothorax compressif suffocant (choc obstructif -> décompression immédiate à l'aiguille !)",
        "Pneumothorax bilatéral synchrone (urgence absolue !)",
        "Hémopneumothorax (niveau hydro-aérique horizontal à la base -> risque choc hémorragique -> drainage gros calibre !)"
      ),
      evolutionAndPrognosis = "Récidive homolatérale dans ~30% après un 1er épisode spontané primaire (50–70% après un 2e) surtout si poursuite du tabac ! Contre-indication temporaire à l'avion (~2–4 semaines après guérison) et définitive à la plongée sous-marine en bouteille sans talcage/pleurectomie chirurgicale.",
      managementPrinciples = listOf(
        "PNEUMOTHORAX COMPRESSIF (choc/asphyxie) : DÉCOMPRESSION IMMÉDIATE À L'AIGUILLE / CATHLON (2e espace intercostal ligne médio-claviculaire ou 4e–5e EIC ligne axillaire antérieure) avant même la radio, suivie d'un drain thoracique !",
        "Grand pneumothorax bien toléré : EXSUFFLATION à l'aiguille/cathéter ou DRAINAGE THORACIQUE aspiratif doux ; Abstention + surveillance + O2 envisageable si petit pneumothorax primaire bien toléré",
        "Toujours associer SEVRAGE TABAGIQUE STRICT ! Symphyse pleurale (talcage/abrasion vidéo-thoracoscopique) dès la 1ère récidive ou pneumothorax bilatéral ou métier à risque"
      ),
      differentialDiagnoses = listOf(
        "Bulle d'emphysème géante chez le BPCO (piège majeur : drainer une bulle d'emphysème crée une fistule bronchopleurale grave ! En cas de doute chez un BPCO -> SCANNER THORACIQUE avant drainage si patient stable !)",
        "Embolie pulmonaire, SCA, Dissection aortique, Péricardite"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Détresse aiguë chez un grand fumeur mince",
      clinicalCaseScenario = "M. V., 23 ans (cas fictif pédagogique), grand mince (1m88, 63 kg), fumeur 1 paquet/jour, ressent soudainement au repos un violent coup de poignard thoracique droit. En 30 minutes aux urgences, il s'aggrave brutalement : agitation, cyanose, PA 78/48 mmHg, FC 138/min, FR 36/min, SpO2 81% AA, turgescence des veines jugulaires, hémithorax droit immobile, tympanique et totalement silencieux à l'auscultation.",
      clinicalCaseCorrection = "1) PNEUMOTHORAX DROIT COMPRESSIF / SUFFOCANT avec état de choc obstructif (tamponnade gazeuse comprimant les veines caves -> hypotension + TJ + silence tympanique droit).\n" +
        "2) INTERDICTION ABSOLUE d'attendre la radiographie thoracique ! Le patient risque l'arrêt cardiaque en quelques minutes.\n" +
        "3) Geste salvateur immédiat : EXSUFFLATION / DÉCOMPRESSION D'URGENCE à l'aiguille/cathéter de gros calibre à droite (2e EIC médio-claviculaire ou 4e–5e EIC axillaire antérieur) donnant un « pschitt » d'air sous pression et restaurant immédiatement la PA -> puis pose d'un drain thoracique + sevrage tabagique.",
      keyPoints = listOf(
        "Trépied gazeux : Tympanisme + Abolition VV + Silence auscultatoire (opposé à la matité de la pleurésie !)",
        "Pneumothorax compressif (choc + TJ + asphyxie) = Diagnostic CLINIQUE -> Décompression immédiate AVANT la radio !",
        "Chez un BPCO stable, toujours éliminer une bulle d'emphysème géante par scanner avant de poser un drain ; plongée en bouteille contre-indiquée !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Opposition sémiologique des grands syndromes pleuraux et parenchymateux",
          headers = listOf("Syndrome", "Vibrations vocales", "Percussion", "Auscultation"),
          rows = listOf(
            listOf("Pneumothorax (air pleural)", "Abolies", "TYMPANISME", "Silence (MV aboli)"),
            listOf("Pleurésie (liquide pleural)", "Abolies", "MATITÉ franche déclive", "Silence (MV aboli)"),
            listOf("Condensation (Pneumonie)", "AUGMENTÉES", "Matité non déclive", "Crépitants + Souffle tubaire"),
            listOf("Atélectasie", "Abolies / diminuées", "Matité rétractile", "MV aboli (médiastin attiré)")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_resp_10_hemothorax",
      title = "10. Hémothorax",
      specialty = SemiologyCategory.RESPIRATORY,
      subtitle = "Épanchement de sang pur dans la plèvre — Double menace respiratoire et hémorragique",
      definition = "Accumulation de sang pur dans la cavité pleurale (définie biologiquement par un hématocrite du liquide pleural >= 50% de l'hématocrite sanguin périphérique), d'origine le plus souvent traumatique mais parfois iatrogène ou spontanée.",
      epidemiology = "Présent dans près de 50–60% des traumatismes thoraciques fermés graves (AVP, chutes) ou pénétrants (arme blanche/balistique). Chaque hémithorax peut contenir jusqu'à 2,5 à 3 litres de sang (plus de la moitié de la volémie !).",
      etiologiesAndRiskFactors = listOf(
        "TRAUMATISMES THORACIQUES (cause > 80%) : fractures de côtes lésant une artère intercostale ou mammaire interne, plaie pulmonaire, rupture isthmique de l'aorte thoracique ou des gros vaisseaux médiastinaux",
        "IATROGÈNES : pose de voie veineuse centrale (sous-clavière/jugulaire), ponction/biopsie pleurale ou pulmonaire, chirurgie thoracique",
        "NON TRAUMATIQUES / SPONTANÉS : rupture de bride pleurale lors d'un hémopneumothorax spontané, tumeur pleurale/pulmonaire hypervascularisée, dissection aortique rompue en plèvre gauche, trouble majeur de l'hémostase / surdosage anticoagulant"
      ),
      physiopathology = "L'irruption de sang provoque une double défaillance aiguë : 1) HYPOTENSION / CHOC HÉMORRAGIQUE (séquestration possible de > 2 litres dans une seule plèvre !) ; 2) COMPRESSION PULMONAIRE ET MÉDIASTINALE (hypoventilation, hypoxie). Si le sang reste non drainé : caillotage puis fibrose rétractile (fibrothorax) et surinfection (empyème).",
      classificationAndPathology = "Petit (< 300–500 mL) vs Abondant (> 500–1500 mL) vs HÉMOTHORAX MASSIF (> 1500 mL d'emblée au drainage ou débit continu > 200 mL/heure pendant 2–4 heures -> indication chirurgicale d'hémostase immédiate !).",
      clinicalSigns = "Association d'un SYNDROME D'ÉPANCHEMENT PLEURAL LIQUIDIEN (dyspnée, douleur thoracique, matité basale, abolition du murmure vésiculaire et des vibrations vocales ; ou tableau mixte hydro-aérique si hémopneumothorax associé !) ET D'UN ÉTAT DE CHOC HÉMORRAGIQUE (pâleur intense, soif, tachycardie, hypotension artérielle, marbrures, veines du cou PLATES — à la différence du pneumothorax compressif ou de la tamponnade qui ont des veines jugulaires turgescentes !!).",
      paraclinicalExams = listOf(
        "Échographie pleurale d'urgence au lit du patient (e-FAST du polytraumatisé) : épanchement échogène hétérogène dans les culs-de-sac pleuraux",
        "Radiographie thoracique (en position couchée chez polytraumatisé : voile diffus d'un hémithorax ! En position assise : opacité basale ou niveau hydro-aérique horizontal si hémopneumothorax !)",
        "Angioscanner thoracique (si patient stabilisé : recherche fuite active artérielle intercostale/mammaire interne, lésion aortique ou rupture diaphragmatique !)",
        "Biologie : Hémoglobine/Hématocrite (chute parfois retardée par hémoconcentration initiale !), Groupe/Rhésus/RAI en urgence, bilan hémostase (TP/TCA/Fibrinogène -> coagulopathie !), Lactates"
      ),
      diagnosticCriteria = listOf(
        "Contexte traumatique/iatrogène/anticoagulant + Épanchement liquidien pleural + Ponction/Drainage ramenant du sang pur avec hématocrite pleural >= 50% de l'hématocrite sanguin"
      ),
      clinicalForms = listOf(
        "Hémothorax massif post-traumatique avec choc hémorragique (urgence de déchoquage + thoracotomie d'hémostase)",
        "Hémothorax retardé post-fracture costale (pouvant survenir 48–72h après un choc thoracique apparemment simple !)",
        "Hémothorax cailloté (mal drainé -> nécessite fibrinolyse pleurale ou vidéothoracoscopie de décaillotage)"
      ),
      evolutionAndPrognosis = "Urgence vitale immédiate si lésion artérielle/vasculaire active. Bien drainé précocement avec contrôle du saignement, évolution favorable ; sinon risque majeur d'hémothorax cailloté surinfecté (empyème) et de séquelle restrictive (fibrothorax).",
      managementPrinciples = listOf(
        "Réanimation hémorragique simultanée : 2 grosses VVP, remplissage cristalloïdes + transfusion culots/plasma (objectif hémostase et Hb) + Acide tranexamique (Exacyl) en traumatologie précoce (< 3h !)",
        "DRAINAGE THORACIQUE DE GROS CALIBRE (28 à 32 French pour éviter l'obstruction par les caillots !) en 4e–5e espace intercostal ligne axillaire moyenne -> mesure immédiate du volume et du débit horaire",
        "CRITÈRES DE THORACOTOMIE D'HÉMOSTASE EN URGENCE (ou embolisation artérielle si fuite intercostale ciblée stable) : > 1500 mL d'emblée à la pose du drain OU débit > 200 mL/heure pendant >= 2–4 heures OU instabilité hémodynamique persistante !",
        "Ne jamais oublier l'antibioprophylaxie ciblée éventuelle lors du drainage en trauma pénétrant et le suivi radiologique pour éviter un caillot résiduel"
      ),
      differentialDiagnoses = listOf(
        "Tamponnade péricardique ou Pneumothorax compressif (différence sémiologique clé : veines jugulaires TURGESCENTES dans tamponnade/pneumothorax compressif vs veines jugulaires PLATES et collapsées dans l'hémothorax hémorragique !!)",
        "Pleurésie séro-hémorragique néoplasique (hématocrite pleural < 50% du sang), Rupture diaphragmatique (bruits digestifs audibles dans le thorax !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Polytraumatisme thoracique gauche",
      clinicalCaseScenario = "M. G., 31 ans (cas fictif pédagogique), victime d'un accident de moto avec choc latéral gauche, arrive agité et très pâle aux urgences. PA 75/45 mmHg, FC 134/min, SpO2 88% sous masque O2, veines jugulaires PLATES. Hémithorax gauche douloureux avec matité et abolition du murmure vésiculaire. L'e-FAST montre un volumineux épanchement pleural gauche ; pas d'épanchement péricardique ni péritonéal. La pose d'un drain thoracique gauche 32F ramène immédiatement 1700 mL de sang rouge chaud, puis 300 mL supplémentaires en 30 minutes.",
      clinicalCaseCorrection = "1) HÉMOTHORAX MASSIF TRAUMATIQUE GAUCHE EN CHOC HÉMORRAGIQUE (noter les veines jugulaires plates signant l'hypovolémie hémorragique et non la tamponnade).\n" +
        "2) Volume initial > 1500 mL (1700 mL d'emblée !) + débit actif 300 mL/30 min = INDICATION FORMELLE DE THORACOTOMIE D'HÉMOSTASE EN EXTRÊME URGENCE !\n" +
        "3) Simultanément : transfusion massive (culots + plasma 1:1 + fibrinogène si besoin), acide tranexamique IV, attention à ne pas clamper un drain qui saigne activement si risque de compression médiastinale, transfert immédiat au bloc de chirurgie thoracique.",
      keyPoints = listOf(
        "Choc + matité/silence d'un hémithorax + Veines jugulaires PLATES = HÉMOTHORAX MASSIF (vs TJ dans tamponnade/pneumothorax compressif !)",
        "Drainage par drain de GROS CALIBRE (28–32F) pour évacuer les caillots et quantifier le saignement",
        "Règle d'or chirurgicale : > 1500 mL à la pose du drain OU > 200 mL/heure durant 2–4h = Thoracotomie d'hémostase urgente !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Orientation vitale devant un choc traumatique thoracique (Triade veines jugulaires / thorax)",
          headers = listOf("Diagnostic", "Veines jugulaires", "Percussion thoracique", "Geste d'urgence immédiat"),
          rows = listOf(
            listOf("Hémothorax massif", "PLATES (hypovolémie)", "Matité unilatérale", "Transfusion + Gros drain + Thoracotomie si > 1500 mL"),
            listOf("Pneumothorax compressif", "TURGESCENTES (obstructif)", "Tympanisme unilatéral", "Décompression immédiate aiguille puis drain"),
            listOf("Tamponnade cardiaque", "TURGESCENTES (obstructif)", "Normale (bruits assourdis)", "Péricardiocentèse / Fenêtre chirurgicale")
          )
        )
      )
    )
  )
}
