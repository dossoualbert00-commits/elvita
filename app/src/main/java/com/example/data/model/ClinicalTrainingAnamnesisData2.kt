package com.example.data.model

internal val anamnesisSkillsPart2: List<AnamnesisSkill> = listOf(
  AnamnesisSkill(
    id = 8,
    title = "8. Explorer les antécédents médicaux et chirurgicaux",
    subtitle = "Pathologies chroniques, interventions, hospitalisations et terrain",
    explanation = "Les antécédents éclairent le terrain de survenue de la maladie actuelle. Il faut distinguer les antécédents médicaux (HTA, diabète, asthme), chirurgicaux (opérations, anesthésies), gynéco-obstétricaux et familiaux.",
    professionalPhrasings = listOf(
      "« Avez-vous des maladies pour lesquelles vous êtes suivi régulièrement ou prenez un traitement au long cours ? »",
      "« Avez-vous déjà été opéré au cours de votre vie ? Même dans votre enfance ou sous anesthésie locale ? »",
      "« Avez-vous déjà été hospitalisé auparavant ? Si oui, en quelle année et pour quel motif précis ? »",
      "« Quelqu'un dans votre famille proche (parents, frères, sœurs, enfants) souffre-t-il de maladies cardiaques, d'AVC, de diabète ou de cancers ? »",
      "« Pour les patientes : combien de grossesses avez-vous eues, avez-vous fait des fausses couches ou des complications (hypertension gravidique, diabète) ? »"
    ),
    errorsToAvoid = listOf(
      "Se contenter de demander « Avez-vous des antécédents ? » (le patient ne comprend souvent pas le terme).",
      "Oublier de quantifier les intoxications (tabac en paquets-années, alcool en verres/jour).",
      "Négliger les antécédents familiaux de décès précoce (mort subite avant 55 ans chez l'homme / 65 ans chez la femme).",
      "Ne pas croiser les dires du patient avec les médicaments de son ordonnance."
    ),
    patientResponsesExamples = listOf(
      "« Non, je n'ai aucune maladie, je prends juste ma petite pilule pour la tension et mon insuline. »" to "« D'accord, donc vous êtes traité pour de l'hypertension et du diabète. Depuis combien d'années exactement et à quel dosage ? »",
      "« J'ai été opéré de l'estomac il y a 30 ans mais ce n'est rien. »" to "« C'est au contraire une information précieuse ! S'agissait-il d'un ulcère, d'une vésicule ou d'une chirurgie bariatrique ? Avez-vous gardé des comptes-rendus ? »",
      "« Mon père est parti brutalement à 48 ans, mais on n'a jamais su pourquoi. »" to "« Une mort subite précoce avant 55 ans dans la famille directe est un élément capital pour évaluer votre risque cardiovasculaire génétique. »",
      "« J'ai fait de l'asthme quand j'étais petit mais c'est fini. »" to "« À quel âge avez-vous eu votre dernière crise et avez-vous encore parfois des sifflements ou un inhalateur de secours ? »",
      "« J'ai eu une transfusion lors d'un accouchement en 1988. »" to "« Merci de le préciser : avez-vous eu des bilans de dépistage viraux (hépatites, VIH) par la suite ? »"
    ),
    followUpQuestions = listOf(
      "« Quelqu'un dans votre famille proche (parents, frères, sœurs) a-t-il eu des problèmes cardiaques jeunes ? »",
      "« Avez-vous déjà fait une phlébite ou une embolie pulmonaire par le passé ? »",
      "« Vos vaccinations (tétanos, grippe, pneumocoque, hépatite B) sont-elles à jour ? »",
      "« Avez-vous déjà eu un séjour prolongé en zone tropicale ou un antécédent de tuberculose ? »",
      "« Quelle est la date de vos dernières règles ou de votre ménopause ? »"
    ),
    practicalExerciseQuestion = "Face à un patient qui répond « Non, je ne suis jamais malade, je prends juste du Kardegic et du Tahor », que doit noter l'étudiant ?",
    practicalExerciseChoices = listOf(
      "Aucun antécédent médical.",
      "Noter la présence d'une maladie cardiovasculaire sous-jacente ou d'une dyslipidémie/antécédent ischémique justifiant ces molécules.",
      "Supprimer les traitements car le patient dit qu'il n'est pas malade.",
      "Attendre la visite du médecin pour poser la question."
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Les patients sous-estiment souvent leurs pathologies chroniques quand elles sont stabilisées par un traitement préventif."
  ),

  AnamnesisSkill(
    id = 9,
    title = "9. Rechercher les traitements, allergies et facteurs de risque",
    subtitle = "Ordonnance réelle, automédication, terrain allergique et FDRCV",
    explanation = "Recueillir l'ordonnance exacte, l'automédication récente (AINS, paracétamol, phytothérapie), les allergies vraies (anaphylaxie vs intolérance) et les facteurs de risque cardiovasculaires ou infectieux.",
    professionalPhrasings = listOf(
      "« Avez-vous sur vous votre ordonnance ou la liste exacte de vos médicaments avec leurs dosages ? »",
      "« Avez-vous pris des médicaments sans ordonnance ces derniers jours : aspirine, ibuprofène, paracétamol, plantes ou remèdes traditionnels ? »",
      "« Êtes-vous allergique à des médicaments, aliments ou au latex ? Que s'est-il passé quand vous y avez été exposé (gonflement, urticaire, malaise) ? »",
      "« Prenez-vous régulièrement tous vos comprimés ou vous arrive-t-il parfois d'en oublier ou d'arrêter certains traitements ? »",
      "« Concernant votre mode de vie : fumez-vous, consommez-vous de l'alcool ou d'autres substances, et quelle est votre profession actuelle ou passée ? »"
    ),
    errorsToAvoid = listOf(
      "Noter « Allergie pénicilline » sans vérifier s'il s'agissait d'une diarrhée bénigne ou d'un œdème de Quincke.",
      "Oublier de demander la consommation de contraceptifs oraux (risque thromboembolique veineux).",
      "Ne pas rechercher la prise récente d'AINS devant un tableau d'insuffisance rénale aiguë, d'ulcère ou d'infection sévère.",
      "Négliger l'arrêt brutal d'un traitement chronique (bêtabloquant, anticoagulant, corticoïde, antiépileptique)."
    ),
    patientResponsesExamples = listOf(
      "« Je suis allergique à l'Augmentin, ça m'a donné mal au ventre. »" to "« C'est une intolérance digestive fréquente liée à l'acide clavulanique, et non une allergie immunologique vraie avec urticaire ou choc. Nous allons le consigner précisément. »",
      "« J'ai pris de l'Advil pour calmer ma rage de dents avant d'avoir mal au ventre. »" to "« C'est un anti-inflammatoire majeur qui peut agresser la muqueuse gastrique et les reins. Combien de comprimés avez-vous pris par jour et pendant combien de jours ? »",
      "« J'ai arrêté mon anticoagulant il y a cinq jours parce que j'avais saigné du nez. »" to "« Information majeure ! Nous devons immédiatement vérifier l'indication de votre anticoagulant et signaler cet arrêt au médecin. »",
      "« Je prends juste des tisanes amaigrissantes achetées sur internet. »" to "« Certains compléments ou plantes non contrôlés peuvent être toxiques pour le foie ou interagir avec les médicaments. Avez-vous le nom ou la boîte ? »",
      "« Je fumais un paquet par jour pendant 30 ans, j'ai arrêté il y a deux ans. »" to "« Cela représente 30 paquets-années (1 paquet/j × 30 ans) : c'est un facteur de risque cardiovasculaire et respiratoire qui reste important malgré le sevrage récent. »"
    ),
    followUpQuestions = listOf(
      "« Fumez-vous ou avez-vous fumé ? Combien de cigarettes par jour et pendant combien d'années ? »",
      "« Avez-vous du diabète, du cholestérol ou de l'hypertension dans votre entourage direct ? »",
      "« Avez-vous utilisé des corticoïdes, des antibiotiques ou des antalgiques dans les 15 derniers jours ? »",
      "« Quelle est votre profession et avez-vous été exposé à l'amiante, aux poussières, aux solvants ou à des animaux ? »",
      "« Comment votre médecin vous a-t-il expliqué le rôle de ce traitement et à quelle heure le prenez-vous ? »"
    ),
    practicalExerciseQuestion = "Pourquoi est-il crucial de caractériser la sévérité d'une allergie médicamenteuse rapportée ?",
    practicalExerciseChoices = listOf(
      "Pour savoir si le patient aime ou non le goût du médicament.",
      "Pour discriminer une intolérance digestive mineure d'une réaction anaphylactique IgE-médiée engageant le pronostic vital et contre-indiquant formellement la classe.",
      "Pour réduire le coût de l'hospitalisation.",
      "C'est une obligation purement administrative sans impact médical."
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Distinguer une allergie vraie d'une simple intolérance évite de priver inutilement un patient d'antibiotiques majeurs tout en protégeant d'un choc anaphylactique récidivant."
  ),

  AnamnesisSkill(
    id = 10,
    title = "10. Explorer les symptômes associés",
    subtitle = "Recherche méthodique des signes d'accompagnement sémiologiques",
    explanation = "Un symptôme isolé n'a que peu de valeur ; ce sont les signes associés (fièvre, sueurs, nausées, palpitations, anomalies du transit ou de la diurèse) qui construisent le syndrome clinique.",
    professionalPhrasings = listOf(
      "« En plus de cette douleur thoracique, avez-vous ressenti un essoufflement, des sueurs ou des palpitations ? »",
      "« Cette fièvre s'est-elle accompagnée de frissons avec tremblements, de sueurs nocturnes ou de courbatures ? »",
      "« Avez-vous remarqué des modifications de vos urines (couleur, brûlures, fréquence) ou de vos selles depuis le début des troubles ? »",
      "« Sur le plan général, avez-vous perdu du poids sans faire de régime, ou perdu l'appétit récemment ? »",
      "« Avez-vous ressenti au même moment des maux de tête, des troubles de la vue, des nausées ou des vertiges ? »"
    ),
    errorsToAvoid = listOf(
      "Se focaliser uniquement sur l'organe douloureux et oublier d'interroger les appareils voisins.",
      "Ne pas poser la question des signes digestifs (nausées, vomissements) devant une douleur thoracique inférieure.",
      "Oublier de vérifier l'existence de céphalées, raideur de nuque ou photophobie devant une fièvre.",
      "Négliger les signes généraux d'altération de l'état général (AEG : asthénie, anorexie, amaigrissement)."
    ),
    patientResponsesExamples = listOf(
      "« Maintenant que vous le dites, j'étais trempé de sueur quand la douleur est venue. »" to "« C'est un signe d'accompagnement neurovégétatif très significatif. Avez-vous eu des nausées, un malaise ou une sensation d'angoisse ? »",
      "« Mes urines sont devenues très foncées comme du thé. »" to "« Avez-vous remarqué si la peau ou le blanc des yeux a jauni (ictère), si vous avez des démangeaisons ou des selles décolorées ? »",
      "« J'ai aussi mal au mollet droit depuis que je suis revenu en avion. »" to "« Ce signe associé oriente immédiatement vers une thrombose veineuse profonde compliquée d'embolie pulmonaire. Le mollet est-il gonflé et chaud ? »",
      "« Je perds mes vêtements, j'ai perdu 7 kg en deux mois sans comprendre pourquoi. »" to "« C'est un amaigrissement significatif (> 5%). Avez-vous des sueurs nocturnes abondantes ou des ganglions perceptibles ? »",
      "« J'ai une petite toux avec ma brûlure d'estomac la nuit. »" to "« Avez-vous des remontées acides dans la bouche en vous penchant ou en position allongée (pyrosis/RGO) ? »"
    ),
    followUpQuestions = listOf(
      "« Avez-vous remarqué une perte de poids involontaire ces dernières semaines ? »",
      "« Toussez-vous ? Si oui, crachez-vous du sang ou des sécrétions purulentes ? »",
      "« Avez-vous eu des nausées, des vomissements ou un arrêt des gaz et des matières ? »",
      "« Avez-vous remarqué un gonflement des chevilles, du visage ou des ganglions dans le cou ? »",
      "« Vos yeux supportent-ils la lumière sans douleur et avez-vous des bourdonnements d'oreille ? »"
    ),
    practicalExerciseQuestion = "Devant une douleur de la fosse iliaque droite chez une femme jeune, quel symptôme associé doit être systématiquement exploré ?",
    practicalExerciseChoices = listOf(
      "La présence de crampes dans les mollets.",
      "La date des dernières règles, l'existence de métrorragies ou de pertes vaginales anormales (GEU, salpingite).",
      "L'existence d'une allergie aux pollens de graminées.",
      "Le port de lunettes de vue."
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Chez toute femme en âge de procréer, une douleur pelvienne impose l'interrogatoire gynécologique strict pour éliminer une grossesse extra-utérine en urgence vitale."
  ),

  AnamnesisSkill(
    id = 11,
    title = "11. Reconstituer chronologiquement l'histoire de la maladie",
    subtitle = "Cinétique temporelle : début, évolution, épisodes antérieurs",
    explanation = "L'histoire de la maladie doit se lire comme un récit chronologique précis : date de début, mode d'installation (brutal, progressif), cinétique d'aggravation, efficacité des traitements et état actuel.",
    professionalPhrasings = listOf(
      "« Replaçons les événements dans l'ordre : à quelle date et heure précise est apparu le tout premier signe ? »",
      "« Était-ce brutal comme un coup de tonnerre en quelques secondes ou progressif sur plusieurs heures/jours ? »",
      "« Entre le début et aujourd'hui, est-ce que cela n'a fait qu'empirer ou y a-t-il eu des moments d'accalmie ? »",
      "« Quels traitements ou gestes avez-vous déjà essayés chez vous, et quel effet ont-ils eu sur la durée ? »",
      "« Aviez-vous déjà ressenti un épisode similaire ces dernières semaines ou par le passé ? »"
    ),
    errorsToAvoid = listOf(
      "Écrire une histoire dans le désordre temporel (mélanger ce qui s'est passé il y a 3 semaines et ce matin).",
      "Oublier de dater les examens complémentaires déjà réalisés en ville avant l'hospitalisation.",
      "Ne pas préciser si le patient a déjà présenté un épisode strictement identique par le passé.",
      "Confondre l'ancienneté de la maladie chronique (ex: BPCO depuis 10 ans) avec le début de l'exacerbation aiguë (depuis 3 jours)."
    ),
    patientResponsesExamples = listOf(
      "« Ça a commencé il y a un mois par une petite toux, puis avant-hier gros frissons, et ce matin je crache du sang. »" to "« Très bien, c'est très clair. Notons J-30 toux sèche traînante, J-2 frissons avec fièvre aiguë, et ce matin hémoptysie inaugurale. »",
      "« J'ai déjà eu ça l'année dernière, on m'avait donné des antibiotiques. »" to "« Vous souvenez-vous du nom du médicament, de la durée et si vos symptômes avaient totalement disparu entre deux ? »",
      "« Je crois que c'était vendredi... non, peut-être mercredi après le mariage. »" to "« Utilisons un repère sûr : étiez-vous déjà gêné pour marcher lors du mariage, ou cela a-t-il commencé le lendemain matin ? »",
      "« Depuis ce matin ça va un peu mieux que cette nuit. »" to "« À quelle heure la douleur a-t-elle atteint son pic, et qu'avez-vous fait pour qu'elle diminue depuis ce matin ? »",
      "« J'ai fait une radio mardi chez le radiologue en ville. »" to "« Avez-vous le compte-rendu ou les clichés avec vous pour que nous comparions l'évolution sur 72 heures ? »"
    ),
    followUpQuestions = listOf(
      "« Combien de temps exactement a duré le pic douloureux le plus intense ? »",
      "« Avez-vous pu dormir la nuit dernière ou la douleur vous a-t-elle réveillé ? »",
      "« L'intensité des symptômes aujourd'hui est-elle plus forte, identique ou moindre qu'à J1 ? »",
      "« Y a-t-il eu un événement particulier juste avant le début (effort, repas, voyage, chute, infection ORL) ? »",
      "« Le traitement pris aux urgences vous a-t-il soulagé durablement ou la plainte revient-elle déjà ? »"
    ),
    practicalExerciseQuestion = "Quelle formule d'introduction d'histoire de la maladie respecte le mieux les standards médicaux ?",
    practicalExerciseChoices = listOf(
      "« Le patient est malade depuis longtemps et vient pour des examens divers. »",
      "« L'histoire de la maladie remonte à 72 heures, marquée par l'installation brutale d'une douleur basi-thoracique droite pleurale, complétée à H+24 par une fièvre à 39.2°C et une toux productive purulente. »",
      "« Le patient a pris du paracétamol mais sans succès donc il est venu. »",
      "« Histoire compliquée sans début bien clair selon la famille. »"
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "Préciser la temporalité exacte (72h, H+24), le mode d'installation et la qualification sémiologique donne une structure chronologique irréprochable."
  ),

  AnamnesisSkill(
    id = 12,
    title = "12. Identifier les signes d'alarme (« Red Flags »)",
    subtitle = "Reconnaître sans délai les situations d'urgence vitale",
    explanation = "L'étudiant doit rechercher activement les signes de gravité immédiate : détresse respiratoire, instabilité hémodynamique, troubles de la conscience, déficit neurologique aigu, purpura fulminans ou choc.",
    professionalPhrasings = listOf(
      "« Avez-vous eu des vertiges intenses, l'impression de partir ou une véritable perte de connaissance ? »",
      "« Avez-vous des difficultés soudaines à parler, avaler, voir, ou une faiblesse dans la moitié du visage ou d'un membre ? »",
      "« Avez-vous remarqué des taches rouges ou violettes apparues rapidement sur la peau ? »",
      "« Avez-vous des difficultés à terminer vos phrases sans reprendre votre souffle ? »",
      "« La douleur thoracique ou abdominale s'accompagne-t-elle de sueurs froides, de palpitations rapides ou d'une pâleur extrême ? »"
    ),
    errorsToAvoid = listOf(
      "Poursuivre un interrogatoire de 30 minutes chez un patient en détresse respiratoire ou marbré (urgence vitale prioritaire).",
      "Négliger une céphalée brutale « en coup de tonnerre » (suspicion d'hémorragie sous-arachnoïdienne).",
      "Sous-estimer une douleur thoracique constrictive prolongée de plus de 20 minutes ou un déficit neurologique transitoire.",
      "Oublier de contrôler les constantes vitales (SpO2, PA, FC, FR, Glycémie) dès l'apparition d'un malaise."
    ),
    patientResponsesExamples = listOf(
      "« C'est la pire douleur de tête de toute ma vie, arrivée en une seconde comme une explosion. »" to "« ALERTE : C'est une céphalée explosive en coup de tonnerre. Appel immédiat du médecin senior pour imagerie cérébrale en urgence (éliminer hémorragie méningée). »",
      "« J'ai les lèvres et le bout des doigts tout bleus et je n'arrive plus à reprendre mon souffle. »" to "« ALERTE : Cyanose et détresse respiratoire aiguë. Mise sous oxygène immédiate, appel de l'équipe médicale, monitoring SpO2 et constantes. »",
      "« Ce matin mon bras droit ne répondait plus et je bafouillais pendant 10 minutes, mais c'est revenu. »" to "« ALERTE : Accident ischémique transitoire (AIT) — risque majeur d'AVC constitué imminent. Noter l'heure exacte du début et alerter immédiatement pour filière neurovasculaire. »",
      "« J'ai vomi deux grands bols de sang rouge vif tout à l'heure. »" to "« ALERTE : Hématémèse abondante. Risque de choc hémorragique : prendre PA/FC immédiatement, poser deux VVP de gros calibre et prévenir le réanimateur/gastroentérologue. »",
      "« Mon enfant a 40°C de fièvre et des petites taches violettes sur le ventre qui grandissent vite. »" to "« ALERTE ABSOLUE : Suspicion de purpura fulminans. Tester la vitropression, alerter le médecin senior dans la seconde pour antibiothérapie parentérale immédiate. »"
    ),
    followUpQuestions = listOf(
      "« Pouvez-vous coller votre menton contre votre poitrine sans que cela ne déclenche une vive douleur ? »",
      "« Avez-vous des douleurs abdominales d'une intensité brutale et syncopale ? »",
      "« Avez-vous uriné au cours des 12 dernières heures ? »",
      "« Ressentez-vous une soif intense, une confusion ou une sensation de froid aux extrémités ? »",
      "« Ces taches cutanées disparaissent-elles quand on appuie dessus avec un verre transparent ? »"
    ),
    practicalExerciseQuestion = "Quel signe d'alarme impose d'interrompre l'interrogatoire pour appeler le médecin et débuter les gestes d'urgence ?",
    practicalExerciseChoices = listOf(
      "Une rhinite claire avec éternuements sans fièvre.",
      "Une céphalée explosive en coup de tonnerre avec raideur de nuque et vomissements en jet.",
      "Une lombalgie mécanique évoluant depuis 6 mois.",
      "Une fatigue après une journée de travail."
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "La céphalée explosive fébrile ou afébrile est une urgence absolue (hémorragie méningée ou méningite) nécessitant une alerte immédiate."
  ),

  AnamnesisSkill(
    id = 13,
    title = "13. Organiser les réponses du patient",
    subtitle = "Trier, structurer et hiérarchiser les données brutes",
    explanation = "Le patient s'exprime souvent de manière décousue, en mélangeant ressentis subjectifs, rumeurs familiales et faits médicaux. L'étudiant doit trier ces informations et les ranger dans les bonnes cases sémiologiques.",
    professionalPhrasings = listOf(
      "« Si vous me le permettez, reprenons point par point : parlons d'abord de votre toux, puis nous aborderons votre problème de genou. »",
      "« Je note bien ce que vous me dites pour votre sommeil ; focalisons-nous d'abord sur la douleur dans la poitrine qui vous a fait venir. »",
      "« Pour résumer ce premier point : la douleur a débuté hier matin et a duré 2 heures, c'est bien cela ? »",
      "« Vous m'avez donné beaucoup d'éléments utiles ; classons ensemble vos anciens antécédents d'un côté et ce qui est nouveau depuis cette semaine de l'autre. »",
      "« Revenons un instant sur ce médicament que vous venez de citer avant de poursuivre sur vos autres symptômes. »"
    ),
    errorsToAvoid = listOf(
      "Recopier mot à mot le flux de conscience du patient sans aucun filtre sémiologique.",
      "Se laisser déborder par des détails anecdotiques sans rapport avec l'affection aiguë.",
      "Oublier de reformuler pour valider que le tri effectué correspond fidèlement à la réalité vécue.",
      "Interrompre sèchement un patient âgé anxieux au lieu d'utiliser une transition empathique de recentrage."
    ),
    patientResponsesExamples = listOf(
      "« Alors ma belle-sœur m'a dit que c'était sûrement la vésicule parce que son cousin a eu la même chose après avoir mangé du poulet et ensuite le voisin m'a donné un cachet... »" to "« D'accord. Retenons les faits cliniques objectifs : vous avez ressenti une douleur abdominale après le repas et vous avez ingéré un comprimé inconnu. Quel était son nom ou son effet ? »",
      "« J'ai mal au dos, et puis mes yeux piquent, et puis mon pied gauche me lance depuis l'an dernier... »" to "« Classons ces plaintes par priorité : quel est le problème aigu qui vous empêche de rester chez vous aujourd'hui ? »",
      "« En 1995 j'ai eu une chute, et puis en 2018 un voyage, ah et hier j'ai vomi du sang... »" to "« Arrêtons-nous immédiatement sur le fait majeur d'hier : vous avez vomi du sang. Décrivez-moi exactement cet épisode avant qu'on revienne sur votre historique. »",
      "« Je ne prends presque rien : juste un peu de tension, un peu de sucre et un cachet pour le cœur. »" to "« Récapitulons méthodiquement : HTA traitée, diabète probable et cardioprotection. Je vérifierai votre ordonnance exacte juste après. »",
      "« Tout est lié à mon stress au travail, vous savez mon chef... »" to "« Je comprends que ce contexte professionnel vous pèse lourdement. Notons-le dans votre mode de vie, et vérifions maintenant les signes physiques actuels pour ne rien manquer d'organique. »"
    ),
    followUpQuestions = listOf(
      "« Est-ce que cette synthèse résume fidèlement ce que vous avez ressenti ? »",
      "« Y a-t-il un élément important que nous n'avons pas encore abordé concernant ce problème précis ? »",
      "« Parmi ces trois symptômes, lequel est apparu en premier ? »",
      "« Cet épisode de 2018 avait-il nécessité une hospitalisation ou s'est-il résolu spontanément ? »",
      "« Souhaitez-vous ajouter une précision avant que nous passions à l'examen physique ? »"
    ),
    practicalExerciseQuestion = "Face à un patient très prolixe qui raconte des anecdotes familiales sans fin, quelle est la bonne attitude pédagogique ?",
    practicalExerciseChoices = listOf(
      "Lui couper la parole sèchement en disant : « Ça ne m'intéresse pas. »",
      "Le laisser parler pendant 45 minutes sans intervenir quitte à ne pas avoir le temps de l'examiner.",
      "Recadrer avec bienveillance en reformulant le point clinique clé et en le ramenant sur le fil conducteur de la plainte principale.",
      "Quitter la chambre et envoyer une infirmière à sa place."
    ),
    correctChoiceIndex = 2,
    practicalExerciseCorrection = "Le recadrage bienveillant par reformulation permet de maintenir le cap méthodologique sans froisser le patient."
  ),

  AnamnesisSkill(
    id = 14,
    title = "14. Produire une synthèse claire et structurée",
    subtitle = "La minute de synthèse orale au médecin senior et la trace écrite",
    explanation = "L'aboutissement de l'interrogatoire est la synthèse clinique : résumer en 60 secondes l'âge, le terrain, le motif d'admission, la chronologie des faits et les hypothèses soulevées.",
    professionalPhrasings = listOf(
      "« En synthèse, il s'agit de M. [Nom], [Âge] ans, aux antécédents de [FDR/ATCD], admis pour [Motif]. L'histoire est marquée par [Chronologie]. L'examen retrouve [Signes clés]. Nous discutons en priorité [Hypothèses]. »",
      "« Monsieur/Madame, je vais vous résumer ce que j'ai compris de votre situation pour être certain de n'avoir rien manqué. »",
      "« Le tableau associe un syndrome [ex: infectieux / algique / de condensation] sur un terrain à risque de [comorbidité], devant lequel la priorité immédiate est d'éliminer [urgence vitale]. »",
      "« Pour la prise en charge immédiate, je propose de réaliser en première intention [ECG / NFS / Radiographie] sous surveillance scopique. »",
      "« Docteur, avez-vous besoin que je précise un point particulier de l'anamnèse ou de l'examen physique de ce patient ? »"
    ),
    errorsToAvoid = listOf(
      "Faire une synthèse fleuve de 10 minutes qui noie l'auditeur dans les détails sans hiérarchie.",
      "Ne pas mentionner les antécédents majeurs qui changent complètement la probabilité diagnostique.",
      "Omettre de formuler clairement les 2 ou 3 hypothèses diagnostiques hiérarchisées et l'urgence vitale à éliminer.",
      "Lire ses notes mot à mot la tête baissée au lieu de présenter les faits structurés de manière fluide."
    ),
    patientResponsesExamples = listOf(
      "« Vous avez tout compris docteur, c'est exactement ça ! »" to "« Parfait. Je vais maintenant passer à votre examen physique complet (cœur, poumons, ventre, réflexes) avant d'aller échanger avec le médecin senior. »",
      "« Non, vous avez oublié de dire que j'avais aussi eu des frissons terribles jeudi soir ! »" to "« Merci infiniment de cette précision cruciale : je la rajoute immédiatement à la chronologie car elle oriente vers une origine bactérienne. »",
      "« Est-ce que ça veut dire que je vais être opéré aujourd'hui ? »" to "« À ce stade, nous posons d'abord les hypothèses et les examens urgents pour confirmer le diagnostic ; le médecin senior discutera directement des options thérapeutiques avec vous. »",
      "« Vous allez vraiment tout retenir ? »" to "« Oui, j'ai tout consigné dans votre dossier sécurisé selon une structure rigoureuse pour qu'aucune information ne soit perdue entre équipes. »",
      "« J'espère que je ne vous ai pas fait perdre votre temps. »" to "« Au contraire, votre récit précis nous fait gagner un temps précieux pour cibler tout de suite les bons examens. »"
    ),
    followUpQuestions = listOf(
      "« Validez-vous ce résumé avant que je présente votre dossier au médecin senior ? »",
      "« Est-ce que l'ordre chronologique des événements que je viens de relire correspond bien à votre vécu ? »",
      "« Avez-vous une dernière inquiétude ou question que vous souhaitez que je transmette au médecin responsable ? »",
      "« Souhaitez-vous que j'informe votre personne de confiance après la validation du plan de soins ? »",
      "« Êtes-vous prêt pour l'examen physique maintenant que notre entretien est structuré ? »"
    ),
    practicalExerciseQuestion = "Quelle structure orale de synthèse d'observation est la référence recommandée lors du staff médical ?",
    practicalExerciseChoices = listOf(
      "Donner directement le résultat de la prise de sang sans présenter le patient.",
      "Identité + Terrain/Antécédents clés → Motif d'admission → Chronologie de l'histoire → Données saillantes de l'examen → Hypothèses et plan.",
      "Lire l'intégralité du carnet de santé depuis la naissance.",
      "Donner uniquement son avis personnel sur le traitement à donner."
    ),
    correctChoiceIndex = 1,
    practicalExerciseCorrection = "La structure standardisée (Terrain → Motif → HDM → Examen → Hypothèses) est le langage universel de la transmission médicale rigoureuse."
  )
)
