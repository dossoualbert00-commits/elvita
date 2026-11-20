package com.example.data.model

internal val oralEvaluationCriteriaData: List<OralEvaluationCriterion> = listOf(
  OralEvaluationCriterion(
    title = "1. Accroche immédiate (Identité + Terrain + Motif)",
    description = "Donner dès la première phrase l'âge, le sexe, les 2-3 comorbidités clés et le motif sémiologique d'admission (en 10 secondes)."
  ),
  OralEvaluationCriterion(
    title = "2. Chronologie claire de l'Histoire de la Maladie",
    description = "Raconter la cinétique temporelle exacte (début brutal/progressif, évolution, traitements essayés) sans digressions."
  ),
  OralEvaluationCriterion(
    title = "3. Signes positifs ET négatifs pertinents",
    description = "Citer autant les symptômes présents que les signes d'alarme ou différentiels activement écartés (ex: pas de fièvre, pas d'hémoptysie)."
  ),
  OralEvaluationCriterion(
    title = "4. Constantes vitales chiffrées et état général",
    description = "Donner PA, FC, FR, SpO2 (+ air ambiant ou O2), T°, Glasgow et tolérance hémodynamique/respiratoire."
  ),
  OralEvaluationCriterion(
    title = "5. Examen physique synthétisé en syndromes",
    description = "Regrouper l'examen par appareil en mettant en exergue le syndrome physique principal puis la normalité du reste."
  ),
  OralEvaluationCriterion(
    title = "6. Hypothèses hiérarchisées + Urgence à éliminer",
    description = "Formuler clairement le diagnostic le plus probable ET les urgences vitales différentielles à exclure."
  ),
  OralEvaluationCriterion(
    title = "7. Plan d'action concret (Examens + Surveillance + CAT)",
    description = "Proposer les examens complémentaires de 1ère intention justifiés et les mesures immédiates."
  ),
  OralEvaluationCriterion(
    title = "8. Maîtrise du temps (60 à 90 secondes)",
    description = "Parler distinctement, sans lire mot à mot un texte, en gardant un contact visuel professionnel avec le senior."
  )
)

internal val oralPresentationCasesData: List<OralPresentationCase> = listOf(
  OralPresentationCase(
    id = "oral_cardio_sca",
    title = "Cas 1 (cas fictif pédagogique) : Douleur thoracique aiguë aux Urgences",
    specialty = "Cardiologie",
    durationTarget = "75 secondes",
    patientProfile = "M. Bernard K. (cas fictif pédagogique), 59 ans — Tabagique 30 PA, HTA traitée.",
    badVersion = "« Alors bonjour Docteur, c'est le monsieur du box 3, M. Bernard. Il a eu mal dans la poitrine ce matin en prenant son petit-déjeuner, vers 7h ou 8h je crois. Avant il était suivi pour de l'hypertension et du cholestérol, ah et son père a fait un infarctus. Il prend des cachets tous les matins mais je ne sais plus les noms exacts. Je l'ai examiné, il a 14/9 de tension et son cœur bat normalement. Peut-être qu'il fait un infarctus ou alors c'est son estomac, on va faire une prise de sang pour voir. »",
    referenceScript = "« Docteur, je vous présente Monsieur B. K. (cas fictif pédagogique), 59 ans, hypertendu traité par Ramipril 5 mg et tabagique actif à 30 paquets-années avec hérédité coronarienne précoce, admis aux urgences pour une douleur thoracique aiguë.\n\n" +
      "L'histoire a débuté brutalement à 07h30 au repos par une douleur rétro-sternale en barre constrictive, cotée à 8/10, irradiant dans la mâchoire et le bras gauche, persistante depuis maintenant 90 minutes et accompagnée de sueurs froides et d'une nausée sans dyspnée ni fièvre.\n\n" +
      "À l'examen d'admission, le patient est conscient (Glasgow 15), algique mais calme, avec une PA à 148/88 mmHg symétrique aux deux bras, FC 94 bpm régulière, FR 18/min, SpO2 97% en air ambiant et T° 36.9°C. L'auscultation cardio-pulmonaire est normale sans souffle ni crépitant, les pouls périphériques sont tous perçus et l'abdomen est souple.\n\n" +
      "En synthèse, devant ce syndrome douloureux thoracique angineux typique et prolongé sur terrain à haut risque cardiovasculaire, mon hypothèse principale est un Syndrome Coronarien Aigu (SCA), tout en éliminant formellement une dissection aortique et une embolie pulmonaire.\n\n" +
      "En conduite à tenir immédiate, j'ai installé le patient sous scope continu et je propose de réaliser sans délai un ECG 12 dérivations dans les 10 minutes, un prélèvement de troponine ultrasensible, NFS/ionogramme/hémostase, et de préparer la prise en charge anti-ischémique selon le tracé ECG. »",
    keyStrengths = listOf(
      "Terrain chiffré dès la 1ère phrase (59 ans, HTA sous Ramipril, 30 PA, hérédité).",
      "Douleur caractérisée selon tous ses critères (siège, type, intensité, irradiation, durée 90 min, signes associés).",
      "Signes négatifs essentiels donnés (symétrie des PA aux deux bras contre dissection, pas de crépitant contre OAP).",
      "Délai ECG < 10 min mis en avant conformément aux recommandations."
    ),
    seniorQuestions = listOf(
      "« Pourquoi avez-vous précisé que la tension était symétrique aux deux bras ? »" to "« Pour rechercher une anisotension (> 20 mmHg entre les deux bras), signe classique de dissection aortique aiguë devant toute douleur thoracique intense. »",
      "« Si l'ECG montre un sus-décalage persistant de ST dans les dérivations antérieures, que fait-on ? »" to "« On déclenche immédiatement la filière de reperfusion coronaire en urgence (coronarographie primaire < 90-120 min, ou fibrinolyse si délai impossible) avec double anti-agrégation plaquettaire et anticoagulation. »"
    )
  ),

  OralPresentationCase(
    id = "oral_pneumo_ep",
    title = "Cas 2 (cas fictif pédagogique) : Dyspnée aiguë post-voyage",
    specialty = "Pneumologie",
    durationTarget = "75 secondes",
    patientProfile = "Mme Laura P. (cas fictif pédagogique), 34 ans — Contraception œstroprogestative, vol long-courrier.",
    badVersion = "« C'est une jeune femme de 34 ans qui est arrivée parce qu'elle respire mal depuis hier soir et elle a un point dans le dos. Elle n'a aucune maladie grave d'habitude, elle prend juste la pilule. Elle revient du Mexique il y a 3 jours. Elle a 115 de pouls et 92% d'oxygène. Aux poumons on n'entend presque rien de spécial. C'est peut-être un asthme ou une embolie pulmonaire, on fait un scanner ? »",
    referenceScript = "« Docteur, voici Madame L. P. (cas fictif pédagogique), 34 ans, sans antécédent médico-chirurgical notable mais sous contraception œstroprogestative et de retour il y a 4 jours d'un vol long-courrier de 11 heures, admise pour une dyspnée aiguë avec douleur basi-thoracique.\n\n" +
      "L'épisode a commencé hier soir à 21h00 de manière brutale par un point de côté basi-thoracique droit augmentant à l'inspiration profonde, associé à une dyspnée d'effort puis de repos, une lipothymie transitoire et une douleur du mollet gauche depuis 48 heures, sans fièvre ni expectoration purulente.\n\n" +
      "À l'examen, la patiente est consciente, anxieuse, hémodynamiquement stable (PA 114/72 mmHg), mais tachycarde à 116 bpm régulière, polypnéique à 25/min avec SpO2 91% en air ambiant (corrigée à 97% sous 3 L/min d'O2) et apyrétique à 37.3°C. L'auscultation pulmonaire est claire et symétrique ; en revanche, le mollet gauche est augmenté de volume (+3 cm), chaud et douloureux à la dorsiflexion.\n\n" +
      "En synthèse, cette dyspnée aiguë hypoxémiante avec tachycardie, douleur pleurale à auscultation normale et signes de thrombose veineuse profonde sur terrain œstroprogestatif post-vol réalise un tableau hautement suspect d'Embolie Pulmonaire avec probable TVP surale gauche (probabilité clinique élevée au score de Wells). Différentiels à écarter : pneumothorax spontané et péricardite.\n\n" +
      "Conduite à tenir proposée : mise sous O2, calcul direct du score de probabilité clinique justifiant un angioscanner thoracique d'emblée (sans attendre des D-Dimères), ECG, troponine/BNP pour évaluer le retentissement ventriculaire droit, et anticoagulation curative immédiate en l'absence de contre-indication. »",
    keyStrengths = listOf(
      "Mise en évidence immédiate des deux facteurs de risque thromboemboliques (pilule + vol 11h).",
      "Examen comparatif ciblé des mollets réalisé et chiffré (+3 cm).",
      "Raisonnement bayésien juste : probabilité élevée → angioscanner direct sans D-Dimères inutiles."
    ),
    seniorQuestions = listOf(
      "« Pourquoi ne dosez-vous pas les D-Dimères avant l'angioscanner chez cette patiente ? »" to "« Parce que sa probabilité clinique pré-test (TVP clinique + tachycardie + immobilisation prolongée + absence de diagnostic alternatif plus probable) est élevée : des D-Dimères même normaux n'élimineraient pas suffisamment le risque et retarderaient l'imagerie diagnostique. »",
      "« Quels critères définiraient une embolie pulmonaire à haut risque (grave) ? »" to "« Un état de choc obstructif ou une hypotension artérielle persistante (PAS < 90 mmHg ou chute >= 40 mmHg pendant > 15 min) imposant la fibrinolyse en urgence. »"
    )
  ),

  OralPresentationCase(
    id = "oral_neuro_avc",
    title = "Cas 3 (cas fictif pédagogique) : Déficit neurologique brutal (Filière AVC)",
    specialty = "Neurologie",
    durationTarget = "70 secondes",
    patientProfile = "M. André T. (cas fictif pédagogique), 72 ans — FA connue arrêtée, HTA.",
    badVersion = "« Docteur, M. T. a fait un AVC ce matin ! Il ne parle plus bien et son bras droit ne bouge plus. Sa femme dit que ça a commencé vers 9h mais elle n'est pas sûre. Il a beaucoup de tension à 18/10. Il prend plein de médicaments pour le cœur qu'il aurait oubliés depuis quelques jours. Il faut vite l'envoyer en IRM. »",
    referenceScript = "« Docteur, alerte neurovasculaire : je vous présente Monsieur A. T. (cas fictif pédagogique), 72 ans, hypertendu connu et porteur d'une fibrillation atriale non valvaire dont il aurait interrompu l'anticoagulant oral depuis une semaine, amené pour un déficit neurologique focal brutal.\n\n" +
      "Selon son épouse présente sur place, le patient était strictement asymptomatique à 09h15 et a présenté brutalement à 09h25 (heure précise de début il y a exactement 55 minutes) une chute sans perte de connaissance suivie d'une impossibilité à bouger le côté droit et d'un trouble du langage.\n\n" +
      "À l'examen, le patient est vigile mais confus, avec PA 182/96 mmHg, FC irrégulière à 98 bpm, SpO2 97% AA, apyrétique à 36.8°C et une glycémie capillaire contrôlée immédiatement normale à 1.12 g/L. L'examen retrouve une hémiparésie droite proportionnelle cotée 1/5 au membre supérieur et 2/5 au membre inférieur, une paralysie faciale centrale droite et une aphasie non fluente de Broca (score NIHSS estimé à 14).\n\n" +
      "En synthèse, il s'agit d'un syndrome déficitaire sylvien gauche brutal évoluant depuis moins de 4h30 sur probable embolie cardiaque par rupture d'anticoagulation, évoquant en priorité un AVC ischémique sylvien gauche, devant lequel il faut impérativement éliminer une hémorragie intracérébrale.\n\n" +
      "Conduite à tenir en extrême urgence : respect de l'hypertension permissive tant que PA < 220/120, déclenchement immédiat de l'IRM cérébrale (ou scanner sans injection) avec bilan d'hémostase pour évaluer l'éligibilité à la thrombolyse intraveineuse et/ou à la thrombectomie mécanique. »",
    keyStrengths = listOf(
      "L'heure exacte du dernier moment vu normal (09h15) et du début (09h25) est donnée d'emblée.",
      "La glycémie capillaire est vérifiée en urgence (élimine l'hypoglycémie pseudo-AVC).",
      "La règle de respect tensionnel initial (< 220/120 avant décision de thrombolyse) est maîtrisée."
    ),
    seniorQuestions = listOf(
      "« Quel est le premier examen biologique au lit du patient à faire devant tout déficit focal brutal ? »" to "« La glycémie capillaire, car une hypoglycémie sévère peut mimer parfaitement un AVC et se corrige immédiatement par resucrage IV. »",
      "« À quel seuil abaisse-t-on prudemment la PA si une thrombolyse IV est décidée ? »" to "« Il faut obtenir une PA < 185/110 mmHg avant d'injecter le fibrinolytique pour limiter le risque de transformation hémorragique. »"
    )
  ),

  OralPresentationCase(
    id = "oral_gastro_occl",
    title = "Cas 4 (cas fictif pédagogique) : Vomissements et arrêt du transit",
    specialty = "Gastroentérologie / Chirurgie",
    durationTarget = "75 secondes",
    patientProfile = "Mme Monique R. (cas fictif pédagogique), 66 ans — Antécédent de chirurgie colique en 2016.",
    badVersion = "« Voici Mme R., 66 ans, qui vomit depuis hier et qui a très mal au ventre. Elle n'est pas allée à la selle depuis 3 jours. Elle a déjà été opérée du ventre il y a quelques années. Son ventre est tout gonflé et on entend des bruits bizarres ou parfois rien. Elle est un peu déshydratée et sa tension est à 10/6. Je pense que c'est une gastro-entérite compliquée ou une occlusion. »",
    referenceScript = "« Docteur, je vous présente Madame M. R. (cas fictif pédagogique), 66 ans, ayant pour principal antécédent une hystérectomie par laparotomie et une chirurgie colique pour sigmoïdite en 2016, admise pour douleurs abdominales aiguës et vomissements incoercibles.\n\n" +
      "Le tableau a débuté il y a 36 heures par des douleurs abdominales diffuses à type de coliques paroxystiques, suivies dès la 12e heure par des vomissements alimentaires devenus bilieux précoces, et un arrêt complet des matières ET des gaz depuis maintenant 24 heures, sans fièvre ni diarrhée.\n\n" +
      "À l'examen général, la patiente présente des signes de déshydratation extracellulaire (plis cutanés, langue sèche), PA 102/64 mmHg, FC 108 bpm, T° 37.4°C, FR 20/min. L'abdomen est météorisé de manière asymétrique, siège d'une cicatrice médiane sous-ombilicale, sensible dans son ensemble sans contracture généralisée ni défense franche, tympanique à la percussion avec silence auscultatoire. Les orifices herniaires inguinaux et cruraux sont libres et le toucher rectal montre une ampoule rectale vide.\n\n" +
      "En synthèse, l'association douleurs en coliques + vomissements bilieux précoces + arrêt des matières et des gaz + météorisme sur abdomen cicatriciel constitue un syndrome occlusif aigu probable du grêle sur bride post-opératoire, dont l'urgence vitale à redouter est l'ischémie/strangulation ou la perforation diastatique.\n\n" +
      "CAT immédiate : laisser strictement à jeun, pose de sonde nasogastrique en aspiration douce, réhydratation IV hydro-électrolytique, bilan complet (NFS, ionogramme, créatinine, lactates, CRP) et scanner abdomino-pelvien injecté en urgence avec avis chirurgical viscéral. »",
    keyStrengths = listOf(
      "Examen systématique des orifices herniaires et du toucher rectal explicitement rapporté.",
      "Argumentation claire entre mécanisme fonctionnel et mécanique sur bride (cicatrice médiane).",
      "Mesures conservatrices d'urgence (à jeun, SNG, correction volémique, lactates pour souffrance digestive)."
    ),
    seniorQuestions = listOf(
      "« Quels signes cliniques ou biologiques vous feraient craindre une souffrance ischémique (strangulation) imposant le bloc opératoire immédiat ? »" to "« L'apparition d'une fièvre, d'une défense ou contracture abdominale, d'un état de choc, d'une hyperleucocytose franche ou d'une élévation des lactates plasmatiques. »"
    )
  ),

  OralPresentationCase(
    id = "oral_infect_sepsis",
    title = "Cas 5 (cas fictif pédagogique) : Pyélonéphrite avec signes de sepsis",
    specialty = "Infectiologie / Néphrologie",
    durationTarget = "75 secondes",
    patientProfile = "Mme Nadia S. (cas fictif pédagogique), 49 ans — Diabète de type 2 sous Metformine.",
    badVersion = "« Docteur, c'est Mme S., 49 ans, diabétique. Elle a de la fièvre à 39.8°C depuis hier soir avec mal dans le dos à droite et quand elle urine ça lui brûle depuis 4 jours. Ce matin elle était très faible avec 85/50 de tension et elle respire vite. Sa bandelette montre plein de leucocytes et des nitrites positifs. Il faut lui donner des antibiotiques en comprimés contre l'infection urinaire ? »",
    referenceScript = "« Docteur, situation septique grave : je vous présente Madame N. S. (cas fictif pédagogique), 49 ans, diabétique de type 2 traitée par Metformine, admise pour fièvre élevée avec douleur lombaire droite.\n\n" +
      "L'histoire a débuté il y a 4 jours par des brûlures mictionnelles et une pollakiurie négligées, compliquées depuis hier soir d'une fièvre à 39.8°C avec frissons solennels, d'une douleur lombaire droite intense irradiant vers le flanc et d'une asthénie majeure depuis ce matin sans dyspnée ni diarrhée.\n\n" +
      "À l'admission, on note des signes de gravité hémodynamique et septique : patiente ralentie (Glasgow 14/15), marbrures débutantes des genoux, PA 86/52 mmHg (PAM 63 mmHg), FC 126 bpm, FR 26/min, SpO2 95% AA, T° 39.6°C (qSOFA = 3/3). La fosse lombaire droite est très sensible à l'ébranlement et la bandelette urinaire est fortement positive aux leucocytes et nitrites.\n\n" +
      "En synthèse, ce tableau associe une pyélonéphrite aiguë droite sur terrain diabétique à un sepsis grave débutant (qSOFA 3, marbrures, hypotension), devant lequel il faut impérativement éliminer en urgence un obstacle urologique (pyélonéphrite aiguë obstructive = urgence chirurgicale).\n\n" +
      "Conduite à tenir dans l'heure : arrêt temporaire de la Metformine (risque d'acidose lactique), remplissage vasculaire par cristalloïdes, hémocultures et ECBU immédiats avant antibiothérapie probabiliste parentérale (C3G + aminoside), bilan rénal/lactates et échographie rénale au lit ou scanner en urgence pour vérifier l'absence de dilatation des cavités pyélocalicielles. »",
    keyStrengths = listOf(
      "Identification immédiate du score qSOFA = 3/3 et des marbrures.",
      "Réflexe thérapeutique de suspension de la Metformine devant sepsis/hypoperfusion rénale.",
      "Recherche obligatoire de l'obstacle urologique par imagerie en urgence (ne pas prescrire juste des comprimés !)."
    ),
    seniorQuestions = listOf(
      "« Pourquoi est-il interdit de se contenter d'antibiotiques seuls si l'échographie montre une dilatation des cavités rénales droites sur calcul ? »" to "« Une pyélonéphrite aiguë obstructive avec sepsis est une urgence urologique absolue : sans drainage mécanique en urgence (sonde JJ ou néphrostomie), les antibiotiques ne pénètrent pas dans les urines sous pression et la patiente évolue vers le choc septique réfractaire. »"
    )
  )
)
