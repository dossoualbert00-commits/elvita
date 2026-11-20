package com.example.data.model

internal val anamnesisSkillsPart1: List<AnamnesisSkill> = listOf(
  AnamnesisSkill(
    id = 1,
    title = "1. Accueillir correctement un patient",
    subtitle = "Posture, politesse, cadre d'intimité et mise à l'aise",
    explanation = "L'accueil pose les fondations de l'alliance thérapeutique. Il doit garantir la confidentialité, le respect de la dignité et réduire l'anxiété inhérente au milieu hospitalier.",
    professionalPhrasings = listOf(
      "« Bonjour Monsieur/Madame [Nom]. Je vous en prie, installez-vous confortablement. »",
      "« Avez-vous besoin d'aide pour vous asseoir ou relever le dossier du lit ? »",
      "« Nous allons prendre un moment ensemble au calme pour échanger sur ce qui vous amène. »",
      "« Permettez-moi de fermer la porte et de tirer le paravent afin que notre échange reste strictement confidentiel. »",
      "« Prenez votre temps pour vous installer, je m'assois face à vous pour que nous puissions discuter tranquillement. »"
    ),
    errorsToAvoid = listOf(
      "Appeler le patient uniquement par son numéro de chambre ou son organe (« le foie du 12 »).",
      "Laisser la porte de la chambre ouverte ou les rideaux tirés pendant l'entretien.",
      "Commencer à écrire sur le dossier ou l'ordinateur sans regarder le patient dans les yeux.",
      "Rester debout au pied du lit les bras croisés, créant une distance hiérarchique intimidante."
    ),
    patientResponsesExamples = listOf(
      "« Je suis un peu angoissé d'être ici, il y a trop de monde qui passe. »" to "« C'est parfaitement compréhensible. Je ferme la porte pour que nous puissions discuter en toute tranquillité. »",
      "« J'ai très froid dans cette chambre. »" to "« Je vous apporte immédiatement une couverture supplémentaire avant que nous commencions. »",
      "« Ma fille attend dans le couloir, elle peut entrer ? »" to "« C'est entièrement votre choix : souhaitez-vous qu'elle assiste à l'entretien ou préférez-vous d'abord échanger seul avec moi ? »",
      "« Désolé pour le désordre autour du lit... »" to "« Ne vous excusez surtout pas, l'important est votre confort et que vous puissiez vous reposer. »",
      "« Je suis mal entendant de l'oreille gauche. »" to "« Merci de me le signaler ! Je vais me placer à votre droite et parler distinctement face à vous. »"
    ),
    followUpQuestions = listOf(
      "« Êtes-vous bien installé pour que nous puissions discuter ? »",
      "« Souhaitez-vous que vos proches restent avec nous ou préfèrent-ils patienter quelques minutes ? »",
      "« La lumière ou le bruit du couloir ne vous gênent-ils pas trop en ce moment ? »",
      "« Avez-vous besoin d'un verre d'eau avant que nous commencions ? »",
      "« Vous sentez-vous suffisamment à l'aise et sans douleur aiguë immédiate pour échanger quelques minutes ? »"
    ),
    practicalExerciseQuestion = "Quel est le comportement d'accueil le plus approprié lors de l'entrée dans la chambre d'un patient hospitalisé ?",
    practicalExerciseChoices = listOf(
      "Entrer en regardant l'écran de la tablette et dire : « Bonjour, je viens pour l'observation clinique. »",
      "Frapper, entrer, saluer le patient par son nom, se positionner à sa hauteur, fermer la porte pour préserver l'intimité.",
      "Demander d'emblée : « Pourquoi êtes-vous hospitalisé ? » sans décliner son identité.",
      "Parler fort depuis le couloir pour vérifier si le patient est réveillé."
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Frapper, saluer par son nom, se placer à sa hauteur et préserver l'intimité sont les composantes essentielles d'un accueil respectueux et rassurant."
  ),

  AnamnesisSkill(
    id = 2,
    title = "2. Se présenter et établir un climat de confiance",
    subtitle = "Statut clair, fonction dans l'équipe et objectif de l'entretien",
    explanation = "L'étudiant en médecine doit expliciter clairement son statut d'externe/étudiant tout en rassurant le patient sur le travail coordonné avec le médecin senior ou le chef de clinique.",
    professionalPhrasings = listOf(
      "« Bonjour, je m'appelle [Prénom Nom], je suis étudiant en médecine dans le service. »",
      "« Je viens réaliser votre première observation clinique afin de recueillir l'ensemble de votre histoire. »",
      "« Je ferai ensuite le point complet avec le Dr [Nom du médecin référent] pour votre prise en charge. »",
      "« Tout ce que vous me confierez est couvert par le secret médical et servira uniquement à mieux vous soigner. »",
      "« Mon objectif est de prendre le temps nécessaire pour écouter tous les détails que vous jugez importants. »"
    ),
    errorsToAvoid = listOf(
      "Se faire passer pour le médecin titulaire ou entretenir l'ambiguïté sur son statut d'étudiant.",
      "Dire : « Je suis juste un stagiaire, ça ne sert à rien ce que je fais. » (décrédibilise le soin).",
      "Promettre des résultats d'examens ou une heure de sortie sans accord médical préalable.",
      "Utiliser d'emblée un jargon médical inaccessible pour impressionner le patient."
    ),
    patientResponsesExamples = listOf(
      "« Mais vous êtes médecin ou vous apprenez sur moi ? »" to "« Je suis étudiant en médecine en fin de cursus. Mon rôle est de préparer minutieusement votre dossier avec vous avant que le médecin responsable vienne valider l'examen. »",
      "« J'ai déjà tout dit aux urgences hier soir ! »" to "« Je comprends que cela soit répétitif et fatigant. Cela nous permet de vérifier chaque détail et de nous assurer que rien n'a évolué depuis votre admission. »",
      "« Est-ce que mon patron saura ce que je vous dis ? »" to "« Non, le secret médical s'applique strictement entre vous et l'équipe soignante. Aucune information médicale ne peut être transmise à votre employeur. »",
      "« Vous avez l'air bien jeune pour me soigner... »" to "« Je suis effectivement en formation avancée et je travaille toujours en binôme étroit avec le médecin senior du service qui supervise chaque décision. »",
      "« Est-ce que le grand professeur passera me voir ensuite ? »" to "« Oui, le médecin référent passera lors de la visite, et grâce à notre entretien, il disposera déjà d'un dossier très complet pour vous répondre précisément. »"
    ),
    followUpQuestions = listOf(
      "« Est-ce que cela vous convient que nous prenions 20 minutes ensemble ? »",
      "« Avez-vous des questions sur le déroulement de notre entretien ? »",
      "« Y a-t-il un sujet particulier que vous aimeriez aborder en priorité dès maintenant ? »",
      "« Préférez-vous que je vous explique chaque étape au fur et à mesure ? »",
      "« Avez-vous déjà eu un entretien médical détaillé de ce type auparavant ? »"
    ),
    practicalExerciseQuestion = "Face à un patient réticent disant « J'ai déjà tout raconté aux urgences », que doit répondre l'étudiant ?",
    practicalExerciseChoices = listOf(
      "« C'est le protocole, vous devez recommencer avec moi. »",
      "« Valider sa fatigue, expliquer avec empathie l'importance de vérifier l'évolution des symptômes et la complémentarité de l'observation d'étage. »",
      "« Très bien, je recopierai juste la feuille des urgences sans vous examiner. »",
      "« Si vous refusez, vous ne verrez pas le médecin senior aujourd'hui. »"
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Faire preuve d'empathie face à la lassitude du patient tout en expliquant la valeur ajoutée d'un réexamen d'étage permet de désamorcer les tensions et restaurer l'adhésion."
  ),

  AnamnesisSkill(
    id = 3,
    title = "3. Identifier le motif de consultation",
    subtitle = "Le symptôme cardinal qui a déclenché le recours aux soins",
    explanation = "Le motif de consultation est le maître-symptôme ou l'événement déclencheur qui a poussé le patient à consulter. Il doit être formulé en termes médicaux précis sans porter de diagnostic prématuré.",
    professionalPhrasings = listOf(
      "« Qu'est-ce qui vous a amené à consulter ou à appeler les secours aujourd'hui ? »",
      "« Quel est le problème ou la gêne qui vous préoccupe le plus en ce moment ? »",
      "« Qu'est-ce qui a changé dans votre état pour vous faire venir à l'hôpital ? »",
      "« Parmi les différents troubles que vous ressentez, lequel a été le véritable déclencheur de votre venue ? »",
      "« Si votre médecin traitant vous a orienté ici, quel symptôme précis l'a le plus inquiété ? »"
    ),
    errorsToAvoid = listOf(
      "Inscrire un diagnostic au lieu du motif (ex: noter « Infarctus du myocarde » au lieu de « Douleur thoracique rétro-sternale »).",
      "Se noyer dans les 10 plaintes chroniques sans identifier l'événement aigu déclencheur.",
      "Prendre pour motif la prescription d'un confrère sans interroger le symptôme réel.",
      "Écrire « Bilan » ou « Hospitalisation » comme motif clinique sans préciser la plainte."
    ),
    patientResponsesExamples = listOf(
      "« C'est mon médecin traitant qui m'a dit de venir d'urgence ! »" to "« D'accord, et pour quelle raison précise vous a-t-il examiné ? Quel symptôme lui avez-vous décrit ? »",
      "« J'ai mal partout, je n'en peux plus depuis des mois. »" to "« Je comprends votre souffrance globale. Mais qu'est-ce qui s'est aggravé particulièrement ces dernières 24 heures pour nécessiter votre hospitalisation ? »",
      "« On m'a dit que j'avais de l'eau dans les poumons. »" to "« D'accord, cela correspond probablement au compte-rendu des urgences. Vous personnellement, ressentiez-vous un essoufflement ou une gêne pour respirer ? »",
      "« J'ai juste fait un bilan sanguin qui n'était pas bon. »" to "« Quelle anomalie vous a-t-on signalée sur cette prise de sang, et aviez-vous des symptômes comme une grande fatigue ou de la fièvre ? »",
      "« Ma femme a insisté pour que je vienne. »" to "« Qu'a remarqué votre épouse dans votre comportement ou votre état physique qui l'a inquiétée aujourd'hui ? »"
    ),
    followUpQuestions = listOf(
      "« Si vous deviez retenir la gêne principale qui a tout déclenché, quelle serait-elle ? »",
      "« Était-ce une douleur, un essoufflement, une fièvre ou autre chose ? »",
      "« À partir de quel moment précis avez-vous décidé qu'il fallait consulter ? »",
      "« Est-ce ce symptôme-là qui vous fait le plus souffrir en ce moment ? »",
      "« Y avait-il un autre signe urgent au moment où vous avez appelé les secours ? »"
    ),
    practicalExerciseQuestion = "Lequel des éléments suivants constitue un motif de consultation correctement rédigé ?",
    practicalExerciseChoices = listOf(
      "« Pneumopathie franche lobaire aiguë droite bactérienne »",
      "« Dyspnée aiguë fébrile avec toux purulente évoluant depuis 48 heures »",
      "« Patient envoyé par le Dr Martin pour bilan »",
      "« Syndrome coronarien aigu avec sus-décalage de ST »"
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Le motif doit décrire le maître-symptôme sémiologique (dyspnée aiguë fébrile) et sa temporalité, et non un diagnostic nosologique présumé."
  ),

  AnamnesisSkill(
    id = 4,
    title = "4. Poser des questions ouvertes",
    subtitle = "Laisser le patient raconter librement sa plainte initiale",
    explanation = "Les questions ouvertes permettent au patient de décrire avec ses propres mots la survenue du problème sans être influencé. Elles fournissent souvent 80% des informations diagnostiques utiles.",
    professionalPhrasings = listOf(
      "« Racontez-moi comment tout cela a commencé... »",
      "« Que s'est-il passé depuis le début de votre douleur ? »",
      "« Comment décririez-vous ce que vous ressentez exactement ? »",
      "« Pouvez-vous me décrire le film de votre journée depuis l'apparition du premier malaise ? »",
      "« Quelles autres manifestations avez-vous remarquées depuis que ce trouble a débuté ? »"
    ),
    errorsToAvoid = listOf(
      "Interrompre le patient dès les 15 premières secondes de parole.",
      "Commencer directement par un interrogatoire policier de questions fermées (« oui/non »).",
      "Orienter d'emblée la réponse (« Vous avez mal dans le bras gauche, n'est-ce pas ? »).",
      "Terminer les phrases du patient à sa place par impatience."
    ),
    patientResponsesExamples = listOf(
      "« Ça a commencé mardi soir devant la télé, j'ai senti comme une chape de plomb sur la poitrine... »" to "« Continuez, je vous écoute... Que s'est-il passé ensuite ? »",
      "« J'ai eu une drôle de sensation dans le ventre, puis j'ai vomi. »" to "« Décrivez-moi un peu plus cette sensation dans le ventre avec vos propres mots. »",
      "« Je ne sais pas trop par où commencer, tout est confus. »" to "« Ne vous inquiétez pas. Partez simplement du tout premier jour où vous ne vous êtes plus senti comme d'habitude. »",
      "« Ça me fait comme un poids qui descend. »" to "« Je vois. Que ressentez-vous d'autre lorsque ce poids apparaît ? »",
      "« Depuis quelques jours je ne suis vraiment plus moi-même. »" to "« Qu'est-ce qui a changé concrètement dans votre quotidien ou vos sensations pour vous faire dire cela ? »"
    ),
    followUpQuestions = listOf(
      "« Et ensuite, comment cela a-t-il évolué ? »",
      "« Y a-t-il d'autres choses que vous avez remarquées à ce moment-là ? »",
      "« Pouvez-vous m'en dire davantage sur ce moment précis ? »",
      "« Comment avez-vous réagi lorsque cela est survenu ? »",
      "« À quoi ressemblait la gêne quand elle était à son maximum ? »"
    ),
    practicalExerciseQuestion = "Quelle formulation représente une question ouverte idéale en début d'histoire de la maladie ?",
    practicalExerciseChoices = listOf(
      "« Avez-vous eu mal au thorax vers 18 heures mardi ? »",
      "« Racontez-moi comment sont apparus vos symptômes et comment la journée s'est déroulée. »",
      "« C'est une douleur constrictive qui serre ou qui pique ? »",
      "« Avez-vous de la fièvre à 39°C depuis ce matin ? »"
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Une question ouverte ne suggère aucune réponse et invite le patient à dérouler la chronologie spontanée de son expérience."
  ),

  AnamnesisSkill(
    id = 5,
    title = "5. Poser des questions fermées",
    subtitle = "Préciser des éléments sémiologiques factuels et exhaustifs",
    explanation = "Les questions fermées (auxquelles on répond par oui, non, une valeur ou un choix binaire) interviennent dans un second temps pour verrouiller des critères diagnostiques précis et des signes d'alerte.",
    professionalPhrasings = listOf(
      "« La douleur irradie-t-elle dans votre mâchoire ou votre bras gauche ? (Oui / Non) »",
      "« Avez-vous mesuré votre température avec un thermomètre ? Si oui, combien affichait-il ? »",
      "« Avez-vous vomi du sang ? (Oui / Non) »",
      "« Cette douleur thoracique augmente-t-elle lorsque vous prenez une grande inspiration ? »",
      "« Êtes-vous réveillé la nuit par cette gêne respiratoire au point de devoir vous asseoir ? »"
    ),
    errorsToAvoid = listOf(
      "Utiliser des questions fermées dès l'introduction (bloque le récit spontané du patient).",
      "Poser plusieurs questions fermées enchaînées d'une traite sans laisser le temps de répondre (« Avez-vous de la fièvre, des frissons, des vomissements et de la diarrhée ? »).",
      "Oublier de vérifier les signes négatifs pertinents (« Pas de fièvre ? Pas d'essoufflement ? »).",
      "Poser une question fermée contenant une double négation déroutante."
    ),
    patientResponsesExamples = listOf(
      "« Oui, ça me tirait jusque dans les dents. »" to "« C'est une information très importante. Est-ce que cette douleur dans les dents a duré plus de 20 minutes ? »",
      "« Non, aucune fièvre, j'étais plutôt frissonnant. »" to "« Avez-vous eu des claquements de dents intenses pendant ces frissons ? »",
      "« Je ne sais pas si j'avais du sang dans les selles, c'était juste très noir. »" to "« Est-ce que vos selles avaient une odeur nauséabonde et une consistance collante comme du goudron (méléna) ? »",
      "« Parfois j'ai le cœur qui tape. »" to "« Ces palpitations sont-elles apparues au repos ou uniquement pendant un effort physique ? »",
      "« J'ai souvent soif ces derniers temps. »" to "« Vous levez-vous plus de deux fois par nuit pour uriner (nycturie) ? »"
    ),
    followUpQuestions = listOf(
      "« Est-ce que la douleur augmente lorsque vous appuyez sur votre ventre ? »",
      "« Avez-vous pris de l'aspirine ou un anti-inflammatoire ce matin ? »",
      "« Avez-vous constaté une baisse nette du volume de vos urines depuis hier ? »",
      "« La douleur cède-t-elle en moins de 5 minutes lorsque vous arrêtez l'effort ? »",
      "« Avez-vous des picotements ou une perte de force dans un membre à ce moment-là ? »"
    ),
    practicalExerciseQuestion = "Quel est le rôle principal des questions fermées dans l'interrogatoire ?",
    practicalExerciseChoices = listOf(
      "Établir le premier contact et rassurer le patient sur le plan émotionnel.",
      "Valider des critères sémiologiques précis, chiffrer des paramètres et vérifier les signes négatifs essentiels.",
      "Remplacer complètement les questions ouvertes pour aller plus vite en garde.",
      "Demander au patient de poser son propre diagnostic."
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Les questions fermées ciblent des points spécifiques, mesurables et éliminent méthodiquement les diagnostics différentiels."
  ),

  AnamnesisSkill(
    id = 6,
    title = "6. Utiliser des questions de précision et de relance",
    subtitle = "Clarifier les termes flous et approfondir les caractéristiques",
    explanation = "Les patients emploient couramment des termes imprécis (« vertige », « malaise », « crise de foie », « brûlure »). Le soignant doit clarifier la réalité clinique sous-jacente sans brusquer.",
    professionalPhrasings = listOf(
      "« Quand vous dites 'vertige', est-ce que la pièce tournait autour de vous, ou s'agissait-il d'une sensation de voile noir ? »",
      "« Vous me dites que la douleur est 'forte'. Si vous deviez lui donner une note de 0 à 10, 10 étant insupportable, à combien l'estimeriez-vous ? »",
      "« Vous mentionnez 'souvent' : est-ce plusieurs fois par jour, par semaine ou par mois ? »",
      "« Pouvez-vous me montrer avec un seul doigt l'endroit exact où la douleur est la plus intense ? »",
      "« Quand vous parlez d'étouffement, est-ce que vous manquez d'air pour parler ou avez-vous simplement l'impression de soupirer ? »"
    ),
    errorsToAvoid = listOf(
      "Accepter un terme du langage populaire (« crise de foie », « rhumatismes ») sans demander ce que le patient ressent physiquement.",
      "Laisser subsister un flou sur la durée exacte d'un malaise ou d'une perte de connaissance.",
      "Ne pas faire préciser le siège exact de la douleur avec le doigt ou la main.",
      "Écrire « douleur intense » dans le dossier sans échelle numérique EVA/EN."
    ),
    patientResponsesExamples = listOf(
      "« J'ai fait une crise de foie après le repas de dimanche. »" to "« Qu'avez-vous ressenti exactement : une douleur sous les côtes à droite, des nausées, des brûlures d'estomac ? Montrez-moi avec votre main. »",
      "« Je suis tombé dans les pommes au marché. »" to "« Vous souvenez-vous d'avoir vu le sol arriver, ou vous êtes-vous réveillé par terre sans vous souvenir de la chute ? »",
      "« J'ai craché du sang ce matin. »" to "« Ce sang est-il arrivé lors d'un effort de toux (hémoptysie) ou lors d'un vomissement (hématémèse) ? »",
      "« J'ai mal au rein gauche depuis trois jours. »" to "« Montrez-moi où vous avez mal : est-ce dans le bas du dos au milieu ou sur le flanc sous les côtes, descendant vers l'aine ? »",
      "« J'ai eu de la diarrhée toute la semaine. »" to "« Combien de selles liquides par 24 heures exactement, et y avait-il du sang ou des glaires ? »"
    ),
    followUpQuestions = listOf(
      "« Pouvez-vous me montrer du doigt l'endroit exact où la douleur a commencé ? »",
      "« Combien de temps cela a-t-il duré : quelques secondes, plusieurs minutes ou plusieurs heures ? »",
      "« Sur une échelle de 0 à 10, comment cotez-vous cette douleur au pic de la crise ? »",
      "« À quelle distance de marche ou à quel étage d'escalier cet essoufflement apparaît-il ? »",
      "« Y avait-il un témoin présent lors de votre malaise pour décrire si vous avez convulsé ou pâli ? »"
    ),
    practicalExerciseQuestion = "Un patient déclare : « Docteur, j'ai tout le temps des vertiges le matin ». Quelle est la relance la plus rigoureuse ?",
    practicalExerciseChoices = listOf(
      "« C'est sûrement un problème de cristaux dans l'oreille interne. »",
      "« Quand vous avez cette sensation, est-ce que les murs tournent comme sur un manège, ou est-ce une tête vide quand vous vous levez du lit ? »",
      "« Avez-vous pris votre bétahistine ce matin ? »",
      "« Les vertiges ne sont pas graves, décrivez-moi plutôt votre toux. »"
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Distinguer un vrai vertige rotatoire vestibulaire d'une hypotension orthostatique ou d'un malaise lipothymique est la première étape indispensable."
  ),

  AnamnesisSkill(
    id = 7,
    title = "7. Obtenir des réponses précises sans influencer le patient",
    subtitle = "Neutralité bienveillante, questions non suggestives",
    explanation = "Poser des questions suggestives fausse le diagnostic en incitant le patient à confirmer ce que le médecin s'attend à entendre. L'interrogatoire doit demeurer strictement neutre.",
    professionalPhrasings = listOf(
      "« Comment qualifieriez-vous le type de douleur : comme un serrement, une brûlure, un coup de poignard, ou autre chose ? »",
      "« À quel moment de la journée la toux se manifeste-t-elle principalement ? »",
      "« Qu'est-ce qui augmente ou au contraire soulage cette douleur ? »",
      "« Avez-vous remarqué un lien quelconque entre vos repas et l'apparition de cette gêne ? »",
      "« Si cette douleur se déplace, vers quelle région du corps la ressentez-vous partir ? »"
    ),
    errorsToAvoid = listOf(
      "Demander : « La douleur vous serre la poitrine, n'est-ce pas ? » (question hautement suggestive).",
      "Suggérer : « Vous n'avez pas de sang dans les selles, je suppose ? » (pousse le patient à répondre 'non').",
      "Hocher la tête de manière approbatrice ou alarmée avant même la fin de la réponse.",
      "Donner une liste fermée à deux choix quand d'autres mécanismes existent sans dire « ou autre chose »."
    ),
    patientResponsesExamples = listOf(
      "« Ça ne me brûle pas vraiment... c'est bizarre comme sensation. »" to "« Prenez votre temps, utilisez vos propres mots ou une image pour m'expliquer ce que vous ressentez. »",
      "« Vous pensez que c'est le cœur ? »" to "« Nous allons explorer toutes les pistes avec rigueur sans préjuger. Pour le moment, dites-moi si cette douleur se modifie lorsque vous respirez profondément. »",
      "« Je dis oui pour la fièvre parce que je pense que ça va avec... »" to "« Ne cherchez surtout pas la bonne réponse ! Dites-moi uniquement ce que vous avez réellement constaté ou mesuré. »",
      "« Si vous me le demandez, peut-être que ça tire un peu vers l'épaule. »" to "« Sans chercher à me faire plaisir, ressentez-vous vraiment une douleur dans l'épaule en dehors de la poitrine ? »",
      "« Tout le monde me dit que c'est un ulcère. »" to "« Mettons de côté ce qu'on vous a dit et regardons objectivement comment votre douleur réagit quand vous mangez ou à jeun. »"
    ),
    followUpQuestions = listOf(
      "« Y a-t-il une position dans laquelle vous vous sentez plus soulagé ? »",
      "« Est-ce continu ou cela vient-il par vagues successives ? »",
      "« Est-ce que l'alimentation aggrave, calme ou ne change rien à votre douleur ? »",
      "« Dans quelles circonstances précises la gêne apparaît-elle ou disparaît-elle ? »",
      "« Avez-vous remarqué quelque chose d'inhabituel dans votre corps en dehors de ce que je viens d'évoquer ? »"
    ),
    practicalExerciseQuestion = "Laquelle de ces questions est la plus neutre et non suggestive ?",
    practicalExerciseChoices = listOf(
      "« Vous n'avez pas fait de fièvre hier, rassurez-moi ? »",
      "« La douleur irradie bien dans le bras gauche comme pour un infarctus ? »",
      "« Vers quel endroit du corps cette douleur se propage-t-elle, si elle se déplace ? »",
      "« Vous avez certainement mal quand vous mangez gras ? »"
    ),
    correctChoiceIndex = 2,
    practicalExerciseCorrection = "Demander vers quel endroit la douleur se propage sans citer d'organe laisse le patient libre de décrire son irradiation réelle."
  )
)
