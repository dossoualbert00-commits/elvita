package com.example.data.model

internal val infoCategoriesGuideList: List<InfoCategoryGuide> = listOf(
  InfoCategoryGuide(
    categoryName = "1. Identité du patient",
    description = "Données d'identification et administratives indispensables.",
    whatToInclude = listOf("Nom, prénom ou pseudonyme/initiales", "Âge et date de naissance", "Sexe biologique", "Profession habituelle", "Mode d'entrée et personne de confiance"),
    whatToExclude = "Ne pas inclure les symptômes ni les antécédents médicaux.",
    hospitalExample = "Mme Sophie M., 54 ans, comptable, admise via les urgences le 04/11/2026.",
    examples = listOf(
      "M. Jean-Luc D., 67 ans, menuisier retraité, né le 12/03/1959, entré par le SAU pour dyspnée, épouse désignée personne de confiance.",
      "Mme Aïssatou K., 29 ans, enseignante, adressée par son médecin traitant en hôpital de jour le 14/05/2026.",
      "M. Thomas R., 19 ans, étudiant en droit, amené par les sapeurs-pompiers à 03h15 suite à un malaise sur la voie publique.",
      "Mme Claire B. (cas fictif pédagogique), 72 ans, ancienne couturière, résidant en autonomie à domicile, mutuelle à jour.",
      "Enfant Léo V., 7 ans, scolarisé en CE1, accompagné de sa mère Mme Sarah V., titulaire de l'autorité parentale."
    )
  ),
  InfoCategoryGuide(
    categoryName = "2. Motif de consultation",
    description = "Le problème ou symptôme cardinal qui motive la venue.",
    whatToInclude = listOf("Plainte principale formulée en termes sémiologiques précis", "Durée d'évolution globale"),
    whatToExclude = "Ne pas inscrire une hypothèse diagnostique présumée.",
    hospitalExample = "Douleur thoracique rétro-sternale constrictive apparue depuis 2 heures.",
    examples = listOf(
      "Dyspnée aiguë fébrile avec toux productive purulente évoluant depuis 48 heures.",
      "Douleur abdominale aiguë de la fosse iliaque droite accompagnée de vomissements depuis 12 heures.",
      "Déficit moteur brutal de l'hémicorps droit et aphasie constatés au réveil à 07h00.",
      "Altération fébrile de la conscience après retour de zone d'endémie palustre il y a 9 jours.",
      "Œdèmes des membres inférieurs d'aggravation progressive avec prise de poids de 5 kg en 10 jours."
    )
  ),
  InfoCategoryGuide(
    categoryName = "3. Histoire de la maladie",
    description = "Le récit chronologique de l'épisode actuel.",
    whatToInclude = listOf("Date et heure de début précises", "Mode d'installation (brutal / progressif)", "Facteurs déclenchants", "Caractéristiques sémiologiques détaillées", "Évolution dans le temps", "Traitements pris et efficacité"),
    whatToExclude = "Ne pas mélanger les pathologies anciennes sans rapport direct.",
    hospitalExample = "Installation brutale à 06h00 au repos d'un serrement thoracique irradiant dans la mâchoire, sans soulagement par le paracétamol.",
    examples = listOf(
      "Début à J-3 par une sensation de malaise général et fièvre à 38.8°C, suivi à J-1 de frissons intenses, toux sèche puis crachats rouillés sans amélioration sous paracétamol 3g/j.",
      "Apparition brutale à 14h20 lors d'un effort de jardinage d'une douleur épigastrique en barre irradiant dans le dos, cotée à 9/10, non calmée par le repos.",
      "Installation progressive sur 15 jours d'une polyurie, polydipsie intense (4 L/j) et amaigrissement de 4 kg, compliquée depuis ce matin de nausées et douleurs abdominales diffuses.",
      "Chute mécanique de sa hauteur à domicile à 10h00 suivie d'une douleur immédiate de hanche droite avec impotence fonctionnelle totale et impossibilité de se relever.",
      "Survenue à H-4 d'une céphalée frontale rapidement progressive avec photophobie, deux vomissements en jet et fièvre à 39.5°C mesurée à domicile."
    )
  ),
  InfoCategoryGuide(
    categoryName = "4. Antécédents",
    description = "Le terrain pathologique antérieur du patient.",
    whatToInclude = listOf("Antécédents médicaux (HTA, diabète, asthme...)", "Antécédents chirurgicaux datés", "Antécédents gynéco-obstétricaux", "Antécédents familiaux significatifs"),
    whatToExclude = "Ne pas inscrire les symptômes de la maladie aiguë actuelle.",
    hospitalExample = "HTA depuis 10 ans, cholécystectomie en 2018, père décédé d'un infarctus à 52 ans.",
    examples = listOf(
      "Médicaux : Diabète de type 2 découvert en 2016 (HbA1c 7.4%), HTA essentielle traitée, dyslipidémie.",
      "Chirurgicaux : Appendicectomie à l'âge de 14 ans, prothèse totale de hanche gauche en 2021 sans complication thromboembolique.",
      "Gynéco-obstétricaux : G3P3, trois accouchements par voie basse à terme, hystérectomie pour fibrome en 2019, ménopausée à 51 ans.",
      "Familiaux : Mère atteinte d'un cancer du côlon diagnostiqué à 58 ans ; frère hypertendu ; pas de mort subite connue.",
      "Allergiques & Transfusionnels : Aucune transfusion sanguine ; œdème de Quincke documenté après prise d'amoxicilline en 2015."
    )
  ),
  InfoCategoryGuide(
    categoryName = "5. Signes fonctionnels",
    description = "Les plaintes et sensations subjectives ressenties par le patient.",
    whatToInclude = listOf("Douleur, nausées, vertiges, essoufflement, palpitations, asthénie, prurit"),
    whatToExclude = "Les signes physiques objectivés par l'auscultation ou les appareils de mesure.",
    hospitalExample = "Sensation d'oppression thoracique, dyspnée au moindre effort, angoisse d'étouffement.",
    examples = listOf(
      "Orthopnée nécessitant 3 oreillers la nuit et dyspnée stade III de la NYHA.",
      "Douleur lombaire gauche à type de torsion, paroxystique, irradiant vers la fosse iliaque et les organes génitaux externes, avec agitation.",
      "Brûlures mictionnelles intenses associées à une pollakiurie nocturne (6 levers) et des impériosités.",
      "Asthénie profonde invalidante, vertiges rotatoires déclenchés aux changements de position et acouphènes bilatéraux.",
      "Prurit cutané généralisé à recrudescence nocturne depuis 3 semaines."
    )
  ),
  InfoCategoryGuide(
    categoryName = "6. Symptômes associés",
    description = "Les signes surajoutés accompagnant la plainte principale.",
    whatToInclude = listOf("Sueurs profuses, nausées, vomissements, céphalées, frissons, troubles du transit"),
    whatToExclude = "Les antécédents anciens guéris.",
    hospitalExample = "Sueurs froides d'accompagnement et deux épisodes de vomissements alimentaires.",
    examples = listOf(
      "Fièvre subjective avec frissons solennels et sueurs nocturnes obligeant à changer de chemise.",
      "Nausées précoces, anorexie complète et arrêt des gaz depuis 24 heures.",
      "Photophobie, phonophobie et cervicalgies diffuses accompagnant les céphalées.",
      "Urines foncées couleur thé et selles mastic décolorées accompagnant la douleur de l'hypochondre droit.",
      "Dysphonie récente et amaigrissement involontaire de 6 kg en 2 mois accompagnant la toux chronique."
    )
  ),
  InfoCategoryGuide(
    categoryName = "7. Facteurs de risque",
    description = "Éléments augmentant la probabilité de survenue d'une pathologie.",
    whatToInclude = listOf("FDRCV : Tabagisme (chiffré en PA), HTA, diabète, dyslipidémie, obésité, sédentarité", "FDR thromboemboliques, infectieux, toxiques ou professionnels"),
    whatToExclude = "Les traitements médicamenteux prescrits.",
    hospitalExample = "Tabagisme actif à 25 paquets-années, sédentarité, IMC à 31 kg/m².",
    examples = listOf(
      "Cardiovasculaires : Tabagisme actif 30 PA, HTA non traitée, hérédité coronarienne précoce (père IDM à 49 ans).",
      "Thromboemboliques : Vol long-courrier de 11 heures il y a 4 jours, contraception œstroprogestative, alitement récent.",
      "Hépatiques & Toxiques : Consommation chronique d'alcool estimée à 60 g/jour depuis 15 ans, partage ancien de seringues.",
      "Respiratoires professionnels : Exposition professionnelle à l'amiante durant 22 ans en chantier naval.",
      "Infectieux : Retour du Sénégal sans chimioprophylaxie antipaludique, vaccination antiamarile non à jour."
    )
  ),
  InfoCategoryGuide(
    categoryName = "8. Traitements en cours",
    description = "Liste exhaustive de l'ordonnance habituelle et des prises récentes.",
    whatToInclude = listOf("DCI des molécules", "Posologies et horaires de prise", "Automédication récente (AINS, antalgiques)", "Allergies médicamenteuses avérées"),
    whatToExclude = "Les traitements arrêtés il y a plusieurs années.",
    hospitalExample = "Ramipril 5mg 1 cp le matin, Atorvastatine 20mg le soir. Pas d'allergie connue.",
    examples = listOf(
      "Ordonnance chronique : Bisoprolol 5 mg le matin, Apixaban 5 mg matin et soir, Furosémide 40 mg à 08h.",
      "Antidiabétiques : Metformine 1000 mg matin et soir, Empagliflozine 10 mg le matin (observance jugée bonne).",
      "Automédication récente : Ibuprofène 400 mg x 3/j pris pendant 6 jours pour lombalgie jusqu'à hier soir.",
      "Rupture thérapeutique : Arrêt spontané de son traitement antiépileptique (Lévétiracétam) il y a 72 heures par manque de renouvellement.",
      "Allergie médicamenteuse : Urticaire généralisée après sulfamides (Cotrimoxazole) en 2020 ; tolérance digestive simple signalée sous amoxicilline."
    )
  ),
  InfoCategoryGuide(
    categoryName = "9. Synthèse de l'interrogatoire",
    description = "Résumé articulé reliant le terrain, l'histoire et les hypothèses.",
    whatToInclude = listOf("Patient de X ans avec terrain Y", "Présentant un tableau de Z évoluant depuis...", "Évoquant en première intention..."),
    whatToExclude = "Le compte-rendu exhaustif mot à mot.",
    hospitalExample = "Homme de 54 ans tabagique et hypertendu, présentant une douleur angineuse prolongée typique avec sueurs, évocatrice en urgence d'un syndrome coronarien aigu.",
    examples = listOf(
      "Femme de 38 ans sous œstroprogestatifs, au retour d'un vol de 10 heures, présentant une douleur basithoracique droite brutale avec dyspnée et tachycardie évoquant en priorité une embolie pulmonaire.",
      "Homme de 64 ans éthylo-tabagique présentant une dysphagie progressive aux solides puis liquides avec amaigrissement de 8 kg en 3 mois, orientant vers une néoplasie œsophagienne.",
      "Patient de 24 ans sans antécédent présentant depuis 18h un syndrome infectieux sévère avec céphalées intenses, photophobie et vomissements imposant d'éliminer en urgence une méningite bactérienne.",
      "Patiente de 76 ans diabétique et insuffisante rénale chronique présentant après 5 jours d'AINS une oligurie et des œdèmes faisant suspecter une insuffisance rénale aiguë iatrogène.",
      "Patient de 42 ans de retour de zone d'endémie sans prophylaxie présentant une fièvre à 40°C avec frissons et céphalées imposant d'éliminer immédiatement un paludisme grave à Plasmodium falciparum."
    )
  )
)

internal val classificationExercisesList: List<ClassificationExercise> = listOf(
  ClassificationExercise(
    rawPatientQuote = "« J'ai été opéré de l'appendicite en 2012 et mon père est mort d'une crise cardiaque à 50 ans. »",
    options = listOf("Motif de consultation", "Histoire de la maladie", "Antécédents", "Signes fonctionnels"),
    correctCategory = "Antécédents",
    explanation = "Ces éléments renseignent le passé chirurgical personnel et le risque cardiovasculaire familial génétique."
  ),
  ClassificationExercise(
    rawPatientQuote = "« Vers 14h, alors que je montais les escaliers, j'ai soudainement manqué d'air et senti un point de côté aigu. »",
    options = listOf("Histoire de la maladie", "Traitements en cours", "Facteurs de risque", "Identité du patient"),
    correctCategory = "Histoire de la maladie",
    explanation = "Ce récit décrit la chronologie précise, le facteur déclenchant (effort) et le mode d'installation des symptômes de l'épisode aigu."
  ),
  ClassificationExercise(
    rawPatientQuote = "« J'ai une douleur qui serre très fort sous le sternum et qui m'empêche de respirer à fond. »",
    options = listOf("Signes fonctionnels", "Antécédents", "Traitements en cours", "Facteurs de risque"),
    correctCategory = "Signes fonctionnels",
    explanation = "Il s'agit de la description directe de la plainte subjective ressentie par le patient (douleur constrictive et gêne respiratoire)."
  ),
  ClassificationExercise(
    rawPatientQuote = "« Je fume un paquet par jour depuis mes 20 ans et je fais de la tension non soignée. »",
    options = listOf("Facteurs de risque", "Motif de consultation", "Synthèse de l'interrogatoire", "Histoire de la maladie"),
    correctCategory = "Facteurs de risque",
    explanation = "Le tabagisme quantifié (paquet-année) et l'hypertension artérielle non contrôlée sont des facteurs majeurs de risque cardiovasculaire."
  ),
  ClassificationExercise(
    rawPatientQuote = "« Je prends du Furosémide 40 mg tous les matins, mais j'ai aussi avalé trois Ibuprofène depuis avant-hier pour mon genou. »",
    options = listOf("Traitements en cours", "Symptômes associés", "Motif de consultation", "Facteurs de risque"),
    correctCategory = "Traitements en cours",
    explanation = "Il faut consigner ensemble le traitement de fond en DCI et l'automédication récente (ici AINS néphrotoxique)."
  ),
  ClassificationExercise(
    rawPatientQuote = "« En même temps que ma fièvre, j'ai eu des sueurs abondantes toute la nuit et de violents frissons avec claquements de dents. »",
    options = listOf("Symptômes associés", "Antécédents", "Identité du patient", "Traitements en cours"),
    correctCategory = "Symptômes associés",
    explanation = "Les frissons solennels et sueurs nocturnes accompagnent le symptôme principal (fièvre) et orientent vers un syndrome bactériémique."
  )
)

internal val observationSkillsList: List<ObservationMethodSkill> = listOf(
  ObservationMethodSkill(
    title = "1. Organiser une observation médicale complète",
    objective = "Maîtriser le plan canonique en 7 étapes d'une observation hospitalière.",
    detailedGuide = "L'observation médicale hospitalière suit une architecture immuable :\n" +
      "1. Identification du patient et données administratives\n" +
      "2. Motif d'admission précis\n" +
      "3. Histoire de la maladie détaillée et chronologique\n" +
      "4. Antécédents médicaux, chirurgicaux, familiaux et ordonnance\n" +
      "5. Examen clinique général et constantes vitales\n" +
      "6. Examen physique méthodique appareil par appareil\n" +
      "7. Synthèse clinique, hypothèses hiérarchisées et stratégie de prise en charge.",
    keyPoints = listOf("Toujours respecter l'ordre standardisé", "Noter les signes négatifs pertinents", "Ne jamais mélanger interrogatoire et examen physique"),
    badExample = "« Le patient a un infarctus. Il a mal et son cœur bat vite. Il prend de l'aspirine. »",
    goodExample = "Observation structurée avec titres de paragraphes clairs, constantes chiffrées et auscultation documentée avec signes positifs et négatifs.",
    additionalExamples = listOf(
      "« Histoire de la maladie : le patient a 14/8 de tension et des crépitants. »" to "« Histoire de la maladie : dyspnée d'effort aggravée sur 72h. Examen physique : PA 140/80 mmHg, râles crépitants bilatéraux des bases. »",
      "« Antécédents : opéré il y a longtemps, ne sait plus quoi. »" to "« Antécédents chirurgicaux : cicatrice médiane sus-ombilicale constatée ; cholécystectomie rapportée vers 2012 (compte-rendu à récupérer). »",
      "« Poumons : RAS. »" to "« Examen pleuro-pulmonaire : murmure vésiculaire bilatéral et symétrique, absence de râles surajoutés ni de matité déclive, pas de tirage. »",
      "« Traitement : prend ses cachets pour le diabète. »" to "« Traitements habituels : Metformine 850 mg x 2/j, Sitagliptine 100 mg/j (observance régulière, dernière prise ce matin à 08h). »",
      "« Conclusion : à surveiller en salle. »" to "« Synthèse & CAT : Syndrome coronarien aigu probable devant douleur angineuse > 20 min chez patient plurifactoriel. ECG + troponine immédiats, scope continu, avis cardio. »"
    ),
    exercisePrompt = "Quel est le défaut majeur si un étudiant place l'auscultation cardiaque dans le paragraphe de l'histoire de la maladie ?",
    exerciseCorrection = "L'histoire de la maladie relève de l'anamnèse (ce que raconte le patient), tandis que l'auscultation est un examen physique objectif réalisé par le soignant. Les mélanger nuit gravement à la clarté et à la fiabilité médico-légale."
  ),

  ObservationMethodSkill(
    title = "2. Décrire l'état général et les constantes vitales",
    objective = "Quantifier la tolérance clinique globale et l'autonomie.",
    detailedGuide = "L'état général s'évalue selon plusieurs composantes clés :\n" +
      "• Le score OMS / Performance Status ECOG (0 = activité normale sans restriction, à 4 = alité en permanence)\n" +
      "• L'état nutritionnel et les téguments : poids, taille, IMC, fonte musculaire, déshydratation (pli cutané, sécheresse muqueuse)\n" +
      "• L'aspect du faciès et la conscience : score de Glasgow (E+V+M), tirage, cyanose, marbrures, sueurs, ictère\n" +
      "• Les 8 constantes vitales chiffrées : PA aux deux bras, FC, FR (sur 1 min entière), SpO2 + débit d'O2, T°, Glycémie, EVA douleur, Diurèse.",
    keyPoints = listOf("La FR est la constante la plus sensible de dégradation", "Toujours noter l'OMS pour évaluer le terrain", "Vérifier la SpO2 en précisant 'air ambiant' ou le débit d'O2"),
    badExample = "« Patient en forme moyenne avec tension normale. »",
    goodExample = "« Patient conscient, orienté, état général conservé (OMS 1). Apyrétique (37.2°C). PA 132/82 mmHg aux deux bras, FC 72 bpm régulière, FR 16/min sans tirage, SpO2 98% en air ambiant. Poids 74 kg, taille 1m75 (IMC 24.2 kg/m²). Muqueuses bien hydratées, conjonctives normocolorées. »",
    additionalExamples = listOf(
      "« SpO2 95% normale. »" to "« SpO2 95% sous 4 L/min d'oxygène aux lunettes nasales (désaturation à 87% en air ambiant lors de la marche). »",
      "« Patient conscient et stable. »" to "« Conscience : Score de Glasgow 15/15 (E4 V5 M6), orienté dans le temps et l'espace, OMS 2, apyrétique à 37.1°C, pas de marbrures des genoux. »",
      "« Poids normal, boit bien. »" to "« Poids 52 kg pour 1m70 (IMC 18.0 kg/m² — dénutrition modérée), perte de 6 kg en 3 mois, muqueuses sèches, pas de pli cutané persistant. »",
      "« Petite fièvre et essoufflé. »" to "« Température tympanique 38.9°C avec frissons, polypnée à 28 cycles/min avec battement des ailes du nez et mise en jeu des sterno-cléido-mastoïdiens. »",
      "« Glycémie ok, tension haute. »" to "« Glycémie capillaire à 1.18 g/L à jeun ; PA mesurée à 174/98 mmHg au bras droit et 170/96 mmHg au bras gauche après 10 min de repos. »"
    ),
    exercisePrompt = "Pourquoi est-il insuffisant de noter simplement 'SpO2 96%' sans préciser l'environnement respiratoire ?",
    exerciseCorrection = "Une SpO2 à 96% en air ambiant est physiologique, alors qu'une SpO2 à 96% sous 15 litres d'oxygène au masque à haute concentration traduit une détresse respiratoire aiguë gravissime."
  ),

  ObservationMethodSkill(
    title = "3. Distinguer rigoureusement signes subjectifs et signes objectifs",
    objective = "Savoir séparer ce que ressent le patient de ce que constate le clinicien.",
    detailedGuide = "• Signes subjectifs (symptômes) : sensations rapportées par le patient que le médecin ne peut pas directement mesurer (douleur, nausées, vertiges, fatigue, angoisse, palpitations ressenties).\n" +
      "• Signes objectifs (signes physiques et mesures) : anomalies constatées et vérifiées par le clinicien lors de l'examen physique ou instrumental (râles crépitants auscultatoires, souffle systolique, hépatomégalie mesurée en cm, fièvre au thermomètre, œdèmes prenant le godet, tachycardie à l'ECG).",
    keyPoints = listOf("Les signes subjectifs guident l'orientation", "Les signes objectifs confirment et étayent le syndrome", "Ne jamais transformer une plainte en signe physique sans examen"),
    badExample = "« Examen physique : le patient a très mal au ventre et a envie de vomir. » (Ce sont des symptômes subjectifs !)",
    goodExample = "« Examen physique abdominal : Abdomen souple, dépressible, douleur provoquée à la palpation du point de McBurney avec signe de Blumberg positif. Bruits hydro-aériques présents. Orifices herniaires libres. »",
    additionalExamples = listOf(
      "« Examen physique : patient très fatigué et qui a des vertiges. »" to "« Anamnèse : asthénie intense et vertiges positionnels. Examen physique : TA couché 130/75 → TA debout à 1 min 98/62 mmHg (hypotension orthostatique objective). »",
      "« Auscultation cardiaque : le patient sent des palpitations. »" to "« Signe fonctionnel : palpitations paroxystiques décrites. Examen physique : bruits du cœur rapides et irréguliers à 132 bpm avec pouls radial déficitaire. »",
      "« Examen respiratoire : sensation d'étouffement. »" to "« Signe fonctionnel : dyspnée de repos. Examen physique : FR 30/min, cyanose péribuccale, sibilants expiratoires diffus aux deux champs. »",
      "« Examen physique : le patient dit qu'il a le foie gonflé. »" to "« Examen abdominal : hépatomégalie régulière, à bord inférieur mousse, mesurée à 15 cm sur la ligne médio-claviculaire, sensible à la palpation. »",
      "« Examen neuro : dit qu'il voit flou et ne sent pas son bras. »" to "« Examen neurologique : hémianopsie latérale homonyme droite au doigt, déficit moteur coté 2/5 au membre supérieur droit et hypoesthésie tactile objective. »"
    ),
    exercisePrompt = "Classez les éléments suivants : Céphalée, Souffle carotidien, Nausée, SpO2 88%, Prurit, Ictère cutanéo-muqueux.",
    exerciseCorrection = "• Subjectifs : Céphalée, Nausée, Prurit.\n• Objectifs : Souffle carotidien (ausculté), SpO2 88% (oxymètre), Ictère cutanéo-muqueux (constaté visuellement)."
  ),

  ObservationMethodSkill(
    title = "4. Rédiger une conclusion clinique structurée",
    objective = "Terminer l'observation par une synthèse claire et un plan d'action hiérarchisé.",
    detailedGuide = "La conclusion d'observation répond à 4 questions fondamentales :\n" +
      "1. Qui est le patient et quel est son terrain ? (Âge, sexe, comorbidités majeures)\n" +
      "2. Quel est le syndrome clinique principal ? (Regroupement des signes positifs)\n" +
      "3. Quelles sont les hypothèses diagnostiques hiérarchisées ? (Diagnostic le plus probable + Urgences vitales à éliminer formellement)\n" +
      "4. Quelle est la conduite à tenir immédiate ? (Examens complémentaires ciblés de première intention, thérapeutique d'urgence et surveillance).",
    keyPoints = listOf("Toujours citer l'urgence vitale à éliminer", "Justifier les examens prescrits", "Indiquer le niveau de surveillance requis"),
    badExample = "« En conclusion, c'est peut-être une embolie ou une pneumonie. On verra avec les prises de sang. »",
    goodExample = "« En conclusion, patient de 62 ans coronarien et hypertendu, présentant un syndrome douloureux thoracique constrictif aigu sans sus-décalage de ST à l'ECG per-critique. Diagnostic le plus probable : Syndrome Coronarien Aigu sans sus-ST (NSTEMI) à troponine en attente. Diagnostics différentiels à éliminer : Embolie pulmonaire, Dissection aortique. CAT : Pose de VVP, monitoring scopique continu, double anti-agrégation, anticoagulation curative, dosage cinétique des troponines et avis cardiologique en urgence. »",
    additionalExamples = listOf(
      "« Conclusion : mal au ventre fébrile, peut-être appendicite. »" to "« Jeune homme de 21 ans sans antécédent présentant un syndrome douloureux fébrile de fosse iliaque droite avec défense localisée. Hypothèse principale : appendicite aiguë non compliquée. À éliminer : perforation/péritonite, torsion testiculaire. CAT : à jeun, VVP, antalgie IV, bilan pré-opératoire, échographie abdominale et avis chirurgical. »",
      "« Conclusion : diabète déséquilibré, on fait des perfusions. »" to "« Patiente de 26 ans diabétique de type 1 en rupture d'insuline présentant un syndrome polyuro-polydipsique avec dyspnée de Kussmaul et cétonurie +++ : acidocétose diabétique sévère. À éliminer : sepsis déclencheur. CAT : réhydratation NaCl 0,9%, insulinothérapie IVSE après kaliémie, gazométrie et surveillance horaire. »",
      "« Conclusion : grosse fièvre après voyage. »" to "« Patient de 34 ans de retour de zone d'endémie palustre sans prophylaxie, présentant une fièvre à 39.8°C avec céphalées et thrombopénie probable. Urgence absolue : accès palustre grave à P. falciparum. CAT : frottis-goutte épaisse en urgence absolue, recherche de signes de gravité OMS, bilan d'organes. »",
      "« Conclusion : AVC droit probable. »" to "« Patiente de 71 ans hypertendue en FA non anticoagulée présentant une hémiplégie gauche proportionnelle brutale vue à H+1h15 (NIHSS à coter). Hypothèse : AVC ischémique sylvien droit. À éliminer impérativement : hémorragie cérébrale (contre-indiquant la thrombolyse). CAT : alerte filière AVC, glycémie capillaire, IRM/Scanner cérébral sans injection immédiat. »",
      "« Conclusion : essoufflement chronique aggravé. »" to "« Patient de 69 ans tabagique à 45 PA atteint de BPCO Gold 3 présentant une décompensation respiratoire aiguë purulente avec hypercapnie suspectée (somnolence). À éliminer : embolie pulmonaire, pneumothorax, OAP. CAT : gaz du sang artériel, RP, O2 titré prudent (objectif SpO2 88-92%), bronchodilatateurs, avis réanimation/VNI. »"
    ),
    exercisePrompt = "Quelles sont les 3 urgences vitales thoraciques qui doivent toujours être discutées dans la conclusion devant une douleur thoracique aiguë ?",
    exerciseCorrection = "Le Syndrome Coronarien Aigu (SCA), l'Embolie Pulmonaire (EP), et la Dissection Aortique (auxquelles s'ajoutent le pneumothorax compressif et la rupture œsophagienne)."
  )
)

internal val synthesisCasesList: List<SynthesisCaseTraining> = listOf(
  SynthesisCaseTraining(
    id = "case_pneumo",
    title = "Cas 1 (cas fictif pédagogique) : De la toux fébrile au syndrome de condensation alvéolaire",
    patientProfile = "Homme de 68 ans (cas fictif pédagogique), fumeur sevré (15 PA), autonome (OMS 0), pas d'autre antécédent.",
    symptomsCollected = listOf(
      "Frissons solennels intenses avec sensation de fièvre depuis 48 heures",
      "Point de côté basi-thoracique droit d'apparition brutale",
      "Toux initialement sèche devenue productive avec crachats rouillés couleur 'jus de prune'",
      "Asthénie aiguë et anorexie"
    ),
    physicalSignsFound = listOf(
      "Température : 39.4°C, PA 125/75 mmHg, FC 102 bpm, FR 26/min (polypnée), SpO2 93% en air ambiant",
      "Palpation : Augmentation des vibrations vocales à la base pulmonaire droite",
      "Percussion : Matité franche basi-thoracique droite non déclive",
      "Auscultation : Râles crépitants en foyer en fin d'inspiration avec souffle tubaire central bien audible à la base droite"
    ),
    clinicalReasoningPath = "1. Regroupement syndromique : Association (Augmentation des vibrations vocales + Matité + Souffle tubaire + Crépitants en foyer) = SYNDROME DE CONDENSATION PULMONAIRE ALVÉOLAIRE TYPIQUE.\n" +
      "2. Contexte fébrile aigu avec frissons solennels et crachats rouillés = Étiologie infectieuse bactérienne typique (Pneumocoque / Streptococcus pneumoniae).\n" +
      "3. Évaluation de la gravité (Score CRB-65) : Âge >= 65 ans (1 point), FR >= 30 (0), PA < 90/60 (0), Confusion (0) -> Score CRB-65 = 1 (Hospitalisation recommandée pour surveillance et antibiothérapie).",
    primaryHypothesis = "Pneumopathie Franche Lobaire Aiguë (PFLA) droite bactérienne présumée à pneumocoque.",
    emergencyToRuleOut = "Décompensation respiratoire aiguë avec choc septique, ou épanchement pleural parapneumonique purulent.",
    immediateActionPlan = listOf(
      "Radiographie thoracique de face en urgence (rechercher opacité alvéolaire systématisée)",
      "Bilan biologique : NFS (hyperleucocytose à PNN), CRP, hémocultures avant toute antibiothérapie",
      "Antibiothérapie probabiliste précoce dirigée contre le pneumocoque (Amoxicilline 1g x 3/jour)",
      "Oxygénothérapie adaptée pour maintenir SpO2 >= 95%, surveillance des constantes toutes les 4h."
    )
  ),

  SynthesisCaseTraining(
    id = "case_cardio",
    title = "Cas 2 (cas fictif pédagogique) : De la dyspnée nocturne au syndrome d'insuffisance cardiaque",
    patientProfile = "Femme de 74 ans (cas fictif pédagogique), antécédent d'hypertension artérielle mal équilibrée et d'infarctus antérieur il y a 8 ans.",
    symptomsCollected = listOf(
      "Essoufflement progressif à la marche puis au repos depuis 5 jours",
      "Orthopnée majeure obligeant la patiente à dormir assise sur 3 oreillers",
      "Réveils nocturnes en sursaut avec sensation d'étouffement (dyspnée paroxystique nocturne)",
      "Prise de poids rapide de 4 kg en une semaine avec gonflement des chevilles"
    ),
    physicalSignsFound = listOf(
      "Constantes : PA 165/95 mmHg, FC 110 bpm régulière, FR 28/min, SpO2 91% en air ambiant, apyrétique",
      "Inspection : Turgescence jugulaire spontanée bilatérale à 45° et œdèmes des membres inférieurs blancs, mous, indolores, prenant le godet jusqu'à mi-jambes",
      "Palpation : Reflux hépato-jugulaire net et sensible",
      "Auscultation cardiaque : Galop protodiastolique B3 bien perçu à la pointe",
      "Auscultation pulmonaire : Râles crépitants bilatéraux symétriques ascendant jusqu'aux mi-champs pulmonaires ('en marée montante')"
    ),
    clinicalReasoningPath = "1. Regroupement syndromique gauche : Orthopnée + Crépitants bilatéraux en marée montante + Bruit de galop B3 = SYNDROME D'INSUFFISANCE CARDIAQUE GAUCHE (Œdème Aigu du Poumon hémodynamique).\n" +
      "2. Regroupement syndromique droit : Turgescence jugulaire + Reflux hépato-jugulaire + OMI bilatéraux prenant le godet + Prise de poids rapide = SYNDROME D'INSUFFISANCE CARDIAQUE DROITE.\n" +
      "3. Synthèse globale : Décompensation cardiaque globale à prédominance gauche (OAP), favorisée par une poussée hypertensive sur cardiopathie ischémique préexistante.",
    primaryHypothesis = "Insuffisance cardiaque globale décompensée avec Œdème Aigu du Poumon (OAP) hémodynamique.",
    emergencyToRuleOut = "Infarctus du myocarde récidivant (SCA gâchette), trouble du rythme ventriculaire ou tamponnade.",
    immediateActionPlan = listOf(
      "Position assise stricte jambes pendantes, oxygénothérapie pour SpO2 >= 95%",
      "Diurétique de l'anse intraveineux rapide (Furosémide 40 à 80 mg IVD)",
      "Dérivés nitrés intraveineux si PAS > 110 mmHg pour réduire la post-charge et la précharge",
      "ECG 12 dérivations immédiat, dosage Troponine, NT-proBNP, Ionogramme/Créatinine, et Échocardiographie transthoracique (ETT) dès stabilisation."
    )
  ),

  SynthesisCaseTraining(
    id = "case_abdo",
    title = "Cas 3 (cas fictif pédagogique) : De la douleur épigastrique brutale au syndrome chirurgical abdominal",
    patientProfile = "Homme de 51 ans (cas fictif pédagogique), tabagique 20 PA, lombalgies traitées par kétoprofène depuis 10 jours sans protecteur gastrique.",
    symptomsCollected = listOf(
      "Douleur épigastrique survenue brutalement à 19h15 en coup de poignard, immédiatement maximale (EVA 9/10)",
      "Diffusion rapide en 2 heures à tout l'abdomen avec immobilité antalgique absolue",
      "Deux vomissements alimentaires réflexes sans amélioration de la douleur",
      "Absence de diarrhée ni de brûlures mictionnelles"
    ),
    physicalSignsFound = listOf(
      "Constantes : T° 38.1°C, PA 100/62 mmHg, FC 116 bpm, FR 24/min, SpO2 97% AA, faciès crispé et immobile",
      "Inspection : Abdomen plat ne respirant pas lors des inspirations profondes",
      "Palpation : Contracture abdominale généralisée permanente et irréductible ('ventre de bois') avec douleur exquise au toucher rectal",
      "Percussion : Disparition de la matité pré-hépatique remplacée par un tympanisme (pneumopéritoine clinique)"
    ),
    clinicalReasoningPath = "1. Regroupement syndromique : Douleur brutale en coup de poignard + Contracture abdominale généralisée ('ventre de bois') + Disparition de la matité pré-hépatique = SYNDROME PÉRITONÉAL AIGU PAR PERFORATION D'ORGANE CREUX.\n" +
      "2. Terrain favorisant : Prise prolongée d'AINS (kétoprofène) sans IPP et tabagisme = perforation d'ulcère gastroduodénal en premier lieu.\n" +
      "3. Signes de retentissement : Tachycardie à 116 bpm et PA limite indiquant un début de choc hypovolémique et septique.",
    primaryHypothesis = "Péritonite aiguë généralisée par perforation d'ulcère gastroduodénal favorisée par les AINS.",
    emergencyToRuleOut = "Choc septique imminent, pancréatite aiguë nécrosante, infarctus mésentérique ou infarctus myocardique inférieur pseudo-chirurgical.",
    immediateActionPlan = listOf(
      "Patient laissé strictement à jeun, pose de deux VVP, remplissage vasculaire par cristalloïdes et antalgie IV adaptée",
      "Avis chirurgical digestif et anesthésique en extrême urgence pour bloc opératoire",
      "Bilan urgent : Scanner abdomino-pelvien injecté (ou ASP/RP debout montrant croissant gazeux sous-diaphragmatique), NFS, ionogramme, créatinine, lipasémie, ECG systématique",
      "Antibiothérapie probabiliste intraveineuse couvrant entérobactéries et anaérobies après hémocultures."
    )
  ),

  SynthesisCaseTraining(
    id = "case_neuro",
    title = "Cas 4 (cas fictif pédagogique) : De la céphalée fébrile au syndrome méningé grave",
    patientProfile = "Étudiante de 22 ans (cas fictif pédagogique), sans antécédent médical, vivant en résidence universitaire.",
    symptomsCollected = listOf(
      "Céphalées fronto-occipitales intenses apparues en 6 heures, résistantes au paracétamol",
      "Photophobie intense (refuse la lumière de la chambre) et phonophobie",
      "Trois vomissements en jet faciles sans nausée préalable",
      "Fièvre d'apparition rapide avec frissons et somnolence progressive"
    ),
    physicalSignsFound = listOf(
      "Constantes : T° 39.7°C, PA 105/65 mmHg, FC 120 bpm, FR 22/min, SpO2 98% AA, Glasgow 14/15 (E3 V5 M6)",
      "Inspection : Attitude couchée en chien de fusil tournée face au mur ; découverte de 3 éléments purpuriques ne s'effaçant pas à la vitropression sur les jambes",
      "Examen méningé : Raideur de nuque franche et douloureuse, signes de Kernig et Brudzinski positifs",
      "Examen neurologique : Pas de déficit moteur focalisé ni d'asymétrie des pupilles"
    ),
    clinicalReasoningPath = "1. Regroupement syndromique : Céphalées + Photophobie + Vomissements en jet + Raideur de nuque + Kernig/Brudzinski = SYNDROME MÉNINGÉ FRANC.\n" +
      "2. Association à une fièvre à 39.7°C + altération de vigilance + purpura non effaçable = MÉNINGITE BACTÉRIENNE GRAVE À MÉNINGOCOQUE AVEC SUSPICION DE PURPURA FULMINANS.\n" +
      "3. Règle d'or absolue : Devant un purpura fébrile extensif ou nécrotique, aucune imagerie ni ponction lombaire ne doit retarder la première dose d'antibiotique intraveineux.",
    primaryHypothesis = "Méningite aiguë bactérienne présumée à Neisseria meningitidis avec syndrome septique grave (purpura).",
    emergencyToRuleOut = "Purpura fulminans avec choc septique foudroyant et engagement cérébral.",
    immediateActionPlan = listOf(
      "Antibiothérapie intraveineuse IMMÉDIATE (Ceftriaxone 2g IV dès la suspicion devant purpura), avant même la ponction lombaire si risque vital",
      "Corticothérapie associée (Dexaméthasone) selon protocole de méningite bactérienne et remplissage vasculaire",
      "Isolement gouttelettes immédiat et déclaration/antibioprophylaxie des sujets contacts",
      "Hémocultures, bilan complet (lactates, hémostase/CIVD, ionogramme), puis imagerie et ponction lombaire dès stabilisation hémodynamique."
    )
  ),

  SynthesisCaseTraining(
    id = "case_infect",
    title = "Cas 5 (cas fictif pédagogique) : De la fièvre au retour de voyage à l'urgence infectieuse",
    patientProfile = "Homme de 36 ans (cas fictif pédagogique), sans antécédent notable, revenu il y a 11 jours d'un séjour de 3 semaines en Afrique de l'Ouest sans chimioprophylaxie.",
    symptomsCollected = listOf(
      "Fièvre élevée à 40.1°C évoluant par pics avec sueurs profuses et courbatures intenses depuis 3 jours",
      "Céphalées frontales battantes et troubles digestifs (nausées, selles molles)",
      "Asthénie majeure l'empêchant de se lever ce matin et urines très foncées",
      "Prise de paracétamol sans effet durable"
    ),
    physicalSignsFound = listOf(
      "Constantes : T° 39.8°C, PA 94/58 mmHg, FC 124 bpm, FR 26/min, SpO2 94% AA, Glasgow 13/15 (ralentissement idéomoteur et confusion)",
      "Inspection : Subictère conjonctival franc, pâleur muqueuse modérée, pas d'éruption cutanée",
      "Palpation abdominale : Splénomégalie modérée débordant de 2 travers de doigt sous le rebord costal gauche",
      "Auscultation : Tachycardie régulière sans souffle, champs pulmonaires libres"
    ),
    clinicalReasoningPath = "1. Règle sémiologique incontournable : Toute fièvre au retour d'une zone d'endémie palustre (dans les 3 mois) est un PALUDISME jusqu'à preuve parasitologique du contraire.\n" +
      "2. Critères de gravité OMS déjà présents : Troubles de la conscience (confusion, Glasgow 13), ictère clinique, hypotension limite et polypnée.\n" +
      "3. Synthèse : Accès palustre grave à Plasmodium falciparum mettant en jeu le pronostic vital à très court terme.",
    primaryHypothesis = "Paludisme grave d'importation à Plasmodium falciparum (neuropaludisme débutant).",
    emergencyToRuleOut = "Choc septique sur fièvre typhoïde, hépatite virale fulminante ou fièvre hémorragique virale selon le contexte épidémiologique.",
    immediateActionPlan = listOf(
      "Frottis sanguin mince + Goutte épaisse + TDR en URGENCE ABSOLUE (résultat attendu < 2 heures) avec calcul de la parasitémie",
      "Bilan de gravité immédiat : Glycémie veineuse (risque d'hypoglycémie), Gaz du sang / lactates, NFS (thrombopénie/anémie), créatininémie, bilan hépatique et hémostase",
      "Hospitalisation en réanimation / soins continus et mise en route immédiate d'Artésunate intraveineux dès confirmation (ou sans délai si suspicion majeure et retard diagnostique)",
      "Surveillance horaire de la conscience, de la glycémie, de la diurèse et de l'hémodynamique."
    )
  )
)
