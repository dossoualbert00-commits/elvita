package com.example.data.model

internal val transitionSectionsData: List<TransitionSection> = listOf(
  TransitionSection(
    id = "5.1",
    title = "5.1 Débuter l'entretien et poser le cadre",
    subtitle = "De l'entrée dans la chambre au motif de consultation",
    objective = "Instaurer un climat serein, expliciter le déroulement de l'entretien et inviter le patient à s'exprimer librement.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Bonjour Monsieur/Madame [Nom], je suis [Prénom], étudiant en médecine dans le service. Je viens prendre le temps d'échanger avec vous pour préparer votre dossier médical avec le Dr [Nom]. »",
        "Ouverture standardisée respectueuse et transparente sur le statut."
      ),
      TransitionPhraseItem(
        "« Avant que nous commencions, êtes-vous confortablement installé et la température ou la douleur actuelle vous permettent-elles de discuter une vingtaine de minutes ? »",
        "Vérification du confort et de l'absence de détresse aiguë."
      ),
      TransitionPhraseItem(
        "« Pour que nous soyons au calme, je ferme la porte. Tout ce que nous dirons reste couvert par le secret médical. »",
        "Garantie immédiate de confidentialité et d'intimité."
      ),
      TransitionPhraseItem(
        "« Nous allons d'abord parler ensemble de ce qui vous amène aujourd'hui, puis je vous poserai quelques questions sur vos antécédents et vos traitements avant de vous examiner. »",
        "Annonce claire du plan de la consultation (réduit l'anxiété)."
      ),
      TransitionPhraseItem(
        "« Je vous écoute : racontez-moi avec vos propres mots ce qui vous a amené à consulter aujourd'hui. »",
        "Transition fluide vers la question ouverte inaugurale."
      )
    ),
    phrasesToAvoid = listOf(
      "« Bonjour, c'est quoi votre problème ? » (brutal et impersonnel).",
      "« Bon, j'ai pas beaucoup de temps, dites-moi vite pourquoi vous êtes là. »",
      "« Je suis le stagiaire, on m'a envoyé poser des questions. » (décrédibilise la démarche)."
    )
  ),

  TransitionSection(
    id = "5.2",
    title = "5.2 Passer aux questions ciblées et de précision",
    subtitle = "Du récit spontané libre à la caractérisation sémiologique rigoureuse",
    objective = "Montrer au patient qu'on a entendu son récit libre, puis annoncer des questions plus précises pour caractériser le symptôme.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Merci pour ces explications très claires. Pour bien comprendre ce que vous avez ressenti, je vais maintenant vous poser quelques questions plus précises sur cette douleur. »",
        "Transition classique après l'écoute ouverte de 1 à 2 minutes."
      ),
      TransitionPhraseItem(
        "« Si je résume bien ce début d'histoire, tout a commencé brutalement mardi matin. Revenons ensemble sur les caractéristiques exactes de ce malaise. »",
        "Reformulation de validation avant de cibler."
      ),
      TransitionPhraseItem(
        "« Vous avez utilisé le mot 'étouffement' : pouvez-vous m'aider à préciser ce détail avec des questions simples auxquelles vous pouvez répondre par oui ou non ? »",
        "Clarification d'un terme flou en annonçant des questions fermées."
      ),
      TransitionPhraseItem(
        "« Pour que nous ne passions à côté d'aucun élément important, je vais vérifier avec vous une liste de signes qui accompagnent parfois ce type de gêne. »",
        "Justification bienveillante d'une série de questions fermées."
      ),
      TransitionPhraseItem(
        "« Reprenons maintenant la chronologie heure par heure : juste avant cet épisode, que faisiez-vous exactement ? »",
        "Recentrer un patient confus sur l'axe temporel."
      )
    ),
    phrasesToAvoid = listOf(
      "Couper la parole sans prévenir pour lancer « Avez-vous de la fièvre ? Oui ou non ? ».",
      "« Oui bon d'accord, mais vous saignez ou pas ? »",
      "Enchaîner 4 questions fermées dans la même phrase sans laisser répondre."
    )
  ),

  TransitionSection(
    id = "5.3",
    title = "5.3 Passer aux antécédents personnels et familiaux",
    subtitle = "De l'épisode aigu actuel au terrain médical antérieur",
    objective = "Faire comprendre au patient pourquoi on l'interroge sur son passé médical ou sa famille alors qu'il souffre aujourd'hui.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« J'ai bien compris l'histoire de votre problème actuel. Pour adapter au mieux la prise en charge, j'ai maintenant besoin de mieux connaître votre santé habituelle. »",
        "Pivot naturel entre histoire de la maladie et antécédents."
      ),
      TransitionPhraseItem(
        "« Avant cet épisode récent, étiez-vous suivi régulièrement par un médecin pour une maladie particulière, même bien équilibrée ? »",
        "Formulation accessible remplaçant le mot jargon 'antécédents'."
      ),
      TransitionPhraseItem(
        "« Parfois, des interventions ou des hospitalisations anciennes éclairent la situation actuelle : avez-vous déjà été opéré ou hospitalisé au cours de votre vie ? »",
        "Exploration des antécédents chirurgicaux expliquée au patient."
      ),
      TransitionPhraseItem(
        "« Certaines pathologies ont une composante héréditaire : dans votre famille proche, y a-t-il des problèmes de cœur, de tension, de diabète ou d'autres maladies connues ? »",
        "Introduction rassurante aux antécédents familiaux."
      ),
      TransitionPhraseItem(
        "« Pour compléter votre dossier en toute précision, notamment chez les patientes, avez-vous eu des grossesses ou des particularités gynécologiques par le passé ? »",
        "Transition respectueuse vers les antécédents gynéco-obstétricaux."
      )
    ),
    phrasesToAvoid = listOf(
      "« Vous avez des antécédents ? » (beaucoup de patients ignorent le sens exact du terme).",
      "« Bon, vos parents sont morts de quoi ? » (brutal et anxiogène).",
      "Passer sans aucune phrase de liaison des douleurs du jour à « Vous avez été opéré quand ? »."
    )
  ),

  TransitionSection(
    id = "5.4",
    title = "5.4 Passer aux traitements et allergies",
    subtitle = "De l'anamnèse pathologique au bilan thérapeutique et sécuritaire",
    objective = "Recueillir l'ordonnance réelle, l'automédication souvent oubliée et caractériser toute allergie vraie.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Maintenant que nous avons fait le point sur votre passé médical, passons en revue les médicaments que vous prenez chaque jour afin d'éviter toute interaction. »",
        "Introduction sécuritaire de l'inventaire médicamenteux."
      ),
      TransitionPhraseItem(
        "« En plus de votre ordonnance habituelle, avez-vous pris récemment des cachets achetés sans ordonnance, de l'aspirine, des anti-inflammatoires ou des plantes ? »",
        "Dépistage bienveillant de l'automédication sans jugement."
      ),
      TransitionPhraseItem(
        "« Vous est-il arrivé ces derniers jours d'oublier ou d'arrêter un traitement parce qu'il vous gênait ou que la boîte était vide ? »",
        "Évaluation déculpabilisante de l'observance thérapeutique."
      ),
      TransitionPhraseItem(
        "« Pour votre sécurité avant toute prescription : êtes-vous allergique à un médicament, un produit de contraste ou un aliment ? »",
        "Transition claire vers les allergies."
      ),
      TransitionPhraseItem(
        "« Quand vous me mentionnez cette allergie passée, que s'est-il passé exactement sur votre peau ou votre respiration après la prise ? »",
        "Distinction essentielle entre intolérance digestive et choc anaphylactique."
      )
    ),
    phrasesToAvoid = listOf(
      "« Vous avez pensé à prendre vos médicaments au moins ? » (culpabilisant).",
      "« Pas d'allergie, hein ? » (suggestif).",
      "« Des remèdes de grand-mère inutiles ? » (moqueur, bloque la confidence)."
    )
  ),

  TransitionSection(
    id = "5.5",
    title = "5.5 Passer aux facteurs de risque et mode de vie",
    subtitle = "Aborder tabac, alcool, profession, voyages et habitudes sans jugement",
    objective = "Recueillir des informations parfois sensibles (addictions, exposition professionnelle, contexte social) avec neutralité bienveillante.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Pour comprendre pleinement votre état de santé général, je pose systématiquement à tous nos patients quelques questions sur leur mode de vie et leurs habitudes. »",
        "Normalisation efficace avant d'aborder tabac/alcool."
      ),
      TransitionPhraseItem(
        "« Quel métier exercez-vous ou avez-vous exercé, et êtes-vous amené à être exposé à des poussières, des produits chimiques ou de gros efforts ? »",
        "Exploration des facteurs environnementaux et professionnels."
      ),
      TransitionPhraseItem(
        "« Concernant le tabac ou l'alcool : fumez-vous actuellement ou avez-vous fumé dans le passé, et quelle est votre consommation habituelle ? »",
        "Question neutre permettant de quantifier paquets-années et grammes/j."
      ),
      TransitionPhraseItem(
        "« Avez-vous effectué un voyage hors de la région ou pris l'avion récemment au cours des trois derniers mois ? »",
        "Dépistage indispensable du paludisme d'importation et du risque thromboembolique."
      ),
      TransitionPhraseItem(
        "« Sur le plan quotidien, vivez-vous seul ou accompagné, et comment vous déplacez-vous à domicile (escaliers, courses) ? »",
        "Évaluation de l'autonomie sociale pour préparer la sortie."
      )
    ),
    phrasesToAvoid = listOf(
      "« Vous buvez combien de litres par jour ? » (stigmatisant).",
      "« C'est à cause de la cigarette que vous êtes ici ! » (moralisateur).",
      "Omettre le mode de vie en pensant que c'est indiscret."
    )
  ),

  TransitionSection(
    id = "5.6",
    title = "5.6 Passer à l'interrogatoire par appareil (10 appareils)",
    subtitle = "Revue systématique complète pour ne manquer aucun symptôme satellite",
    objective = "Annoncer la revue des 10 appareils de manière fluide sans donner l'impression d'un questionnaire robotique.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Nous avons bien cerné votre plainte principale. Je vais maintenant faire avec vous un tour d'horizon rapide des différents systèmes de votre organisme pour être sûr de ne rien laisser passer. »",
        "Phrase chapeau ouvrant l'interrogatoire par appareil."
      ),
      TransitionPhraseItem(
        "« Commençons par le cœur et la respiration, puis nous verrons rapidement la digestion et les autres systèmes. »",
        "Annonce structurée qui rassure sur la logique de l'enchaînement."
      ),
      TransitionPhraseItem(
        "« Du côté cardiaque maintenant : avez-vous ressenti... »",
        "Transition brève et claire d'un système à l'autre."
      ),
      TransitionPhraseItem(
        "« Même si votre douleur est plutôt au ventre, vérifions brièvement s'il n'y a pas de gêne au niveau urinaire ou gynécologique. »",
        "Justification clinique d'une question sur un appareil voisin."
      ),
      TransitionPhraseItem(
        "« Terminons ce tour d'horizon par la peau, les articulations et d'éventuelles glandes ou ganglions. »",
        "Clôture structurée de la revue des systèmes."
      )
    ),
    phrasesToAvoid = listOf(
      "Poser des questions d'appareil urologique ou dermatologique au milieu d'une histoire cardiaque sans prévenir.",
      "Lire mécaniquement une grille les yeux rivés sur la fiche sans regarder le patient.",
      "Utiliser du jargon brut (« Avez-vous une hématurie, une dysurie ou un prurit ? »)."
    ),
    systems = transitionTenSystemsData
  ),

  TransitionSection(
    id = "5.7",
    title = "5.7 Annoncer l'examen physique",
    subtitle = "De la fin de l'interrogatoire au geste clinique respectueux",
    objective = "Préparer psychologiquement et physiquement le patient, vérifier le consentement, garantir pudeur et hygiène des mains.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Merci beaucoup pour toutes ces précisions. Si vous en êtes d'accord, nous allons maintenant passer à l'examen physique afin de vérifier ces éléments directement. »",
        "Demande de consentement claire avant de toucher le patient."
      ),
      TransitionPhraseItem(
        "« Je me désinfecte les mains avec la solution hydro-alcoolique et je vous explique au fur et à mesure tout ce que je vais examiner. »",
        "Règle d'hygiène visible et pédagogie immédiate."
      ),
      TransitionPhraseItem(
        "« Pour que je puisse écouter votre cœur et vos poumons correctement, pourriez-vous découvrir le haut de votre corps ? Je vous redonnerai le drap dès que c'est fait. »",
        "Respect absolu de la pudeur et du confort thermique."
      ),
      TransitionPhraseItem(
        "« Dites-moi immédiatement si une position vous fatigue ou si le moindre geste vous déclenche une douleur. »",
        "Alliance de sécurité avant de manipuler un patient algique."
      ),
      TransitionPhraseItem(
        "« Nous allons d'abord prendre vos constantes (tension, oxygène, pouls, température), puis j'écouterai cœur et poumons avant d'examiner votre ventre allongé. »",
        "Annonce simple de la séquence de l'examen."
      )
    ),
    phrasesToAvoid = listOf(
      "Déshabiller ou soulever le t-shirt du patient sans prévenir ni demander l'accord.",
      "Poser un stéthoscope glacé sur le thorax sans l'avoir réchauffé ni prévenu.",
      "Examiner le patient avec des mains non désinfectées."
    )
  ),

  TransitionSection(
    id = "5.8",
    title = "5.8 Transitions PENDANT l'examen physique",
    subtitle = "Guider chaque étape (auscultation, abdomen, neuro, zones intimes)",
    objective = "Maintenir la coopération et le relâchement du patient pendant les manœuvres cliniques parfois inconfortables.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« [Auscultation cardio-pulmonaire] Je vais écouter vos poumons dans le dos : respirez un peu plus fort par la bouche ouverte quand je pose le stéthoscope, et dites-moi si la tête vous tourne. »",
        "Consigne précise évitant l'hyperventilation inconfortable."
      ),
      TransitionPhraseItem(
        "« [Palpation abdominale] Pour examiner votre ventre en douceur, allongez-vous à plat et pliez légèrement les genoux ; prévenez-moi d'abord où vous avez le plus mal pour que je commence loin de cette zone. »",
        "Mise en relâchement de la paroi et approche non traumatique."
      ),
      TransitionPhraseItem(
        "« [Examen neurologique] Nous allons tester vos réflexes et la force de vos bras et jambes avec de petits gestes simples, comme serrer mes deux doigts le plus fort possible. »",
        "Instruction ludique et précise garantissant une cotation fiable."
      ),
      TransitionPhraseItem(
        "« [Membres inférieurs & pouls] Je vais maintenant regarder vos jambes et vos chevilles pour vérifier s'il n'y a pas de gonflement, puis sentir le pouls au niveau de vos pieds. »",
        "Annonce claire expliquant le lien avec la circulation."
      ),
      TransitionPhraseItem(
        "« [Examen intime / périnéal / toucher] Cet examen nécessite d'observer une zone plus intime ; il est important pour votre diagnostic et ne durera que quelques secondes. Êtes-vous d'accord pour qu'on le réalise maintenant, ou souhaitez-vous la présence d'un autre soignant ? »",
        "Protocolisation stricte du consentement et de la dignité (chaperon si souhaité)."
      ),
      TransitionPhraseItem(
        "« [Fin de l'examen] C'est terminé pour l'examen physique, vous pouvez vous rhabiller tranquillement et vous réinstaller comme vous êtes le mieux. »",
        "Conclusion polie permettant au patient de retrouver sa pudeur avant la synthèse."
      )
    ),
    phrasesToAvoid = listOf(
      "Palper brutalement une fosse iliaque douloureuse en premier sans prévenir.",
      "Commenter « Oh là là, quel souffle ! » ou froncer les sourcils en silence (génère une panique immédiate).",
      "Réaliser un toucher pelvien ou examen mammaire sans consentement explicite préalable."
    )
  ),

  TransitionSection(
    id = "5.9",
    title = "5.9 Clôturer l'entretien avec le patient",
    subtitle = "Reformuler, annoncer la suite concrète et laisser la porte ouverte",
    objective = "Ne jamais partir brusquement : résumer les points clés, expliquer les prochaines étapes et vérifier la compréhension.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Nous arrivons au terme de notre entretien et de l'examen. Merci pour votre patience et la précision de vos réponses. »",
        "Reconnaissance bienveillante de la collaboration du patient."
      ),
      TransitionPhraseItem(
        "« Si je résume pour être sûr de ne rien omettre lors de mon passage devant le médecin : vous êtes venu pour [motif], vous prenez [traitement], et nous avons relevé [constante/signe]. Est-ce fidèle à votre vécu ? »",
        "Synthèse de validation participative."
      ),
      TransitionPhraseItem(
        "« Je vais maintenant présenter l'ensemble de votre dossier au Dr [Nom] qui passera vous voir lors de la visite pour décider avec vous des examens et du traitement. »",
        "Projection temporelle claire (évite l'attente dans le flou)."
      ),
      TransitionPhraseItem(
        "« Avant que je ne sorte, avez-vous une question urgente ou quelque chose d'important que nous n'aurions pas encore évoqué ? »",
        "Question filet de sécurité permettant souvent de recueillir une inquiétude majeure cachée."
      ),
      TransitionPhraseItem(
        "« Voici la sonnette d'appel à portée de main : si votre douleur augmente ou si vous vous sentez mal d'ici là, appelez l'équipe immédiatement sans attendre. »",
        "Consigne de sécurité avant de quitter la chambre."
      )
    ),
    phrasesToAvoid = listOf(
      "« Bon allez, j'ai fini, bonne journée ! » sans rien expliquer de la suite.",
      "« Vous avez sûrement une tumeur, on va faire un scanner. » (annonce diagnostique sauvage interdite).",
      "Partir en laissant la barrière du lit ou la sonnette hors d'atteinte."
    )
  ),

  TransitionSection(
    id = "5.10",
    title = "5.10 Phrases pour rassurer le patient et gérer l'émotion",
    subtitle = "Empathie clinique, désamorçage de la colère et accueil de la peur",
    objective = "Savoir contenir la détresse psychologique, valider l'émotion sans faire de fausses promesses et maintenir le lien thérapeutique.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« Je vois que cette situation vous inquiète beaucoup, et c'est tout à fait légitime. Vous êtes désormais entouré et surveillé en sécurité ici. »",
        "Validation émotionnelle (nommer et légitimer l'émotion)."
      ),
      TransitionPhraseItem(
        "« Prenez votre temps pour respirer, ne vous excusez surtout pas de pleurer : nous pouvons faire une pause quelques instants si vous le souhaitez. »",
        "Accueil empathique des pleurs sans fuir le silence."
      ),
      TransitionPhraseItem(
        "« Je comprends votre agacement face à cette attente prolongée aux urgences. Je suis là maintenant et je m'occupe pleinement de votre dossier pour faire avancer les choses. »",
        "Désamorçage d'un patient irrité sans justification défensive."
      ),
      TransitionPhraseItem(
        "« Je préfère rester totalement honnête avec vous : à ce stade nous avons besoin des résultats de la prise de sang et de l'imagerie avant d'affirmer un diagnostic, mais nous avançons étape par étape. »",
        "Réponse honnête à la question 'Est-ce grave / est-ce un cancer ?' sans mensonge ni panique."
      ),
      TransitionPhraseItem(
        "« Dès que le médecin aura analysé vos résultats, il viendra vous les expliquer en termes clairs ; si vous voulez, nous pouvons aussi appeler votre proche de confiance. »",
        "Inclusion rassurante de l'entourage."
      ),
      TransitionPhraseItem(
        "« Votre douleur actuelle est notre priorité : je préviens tout de suite l'infirmière et le médecin pour vérifier ce que nous pouvons vous donner pour vous soulager rapidement. »",
        "Action immédiate sur une souffrance aiguë (restaure instantanément la confiance)."
      )
    ),
    phrasesToAvoid = listOf(
      "« Ne vous inquiétez pas, ce n'est rien du tout ! » (banalisation dangereuse et souvent fausse).",
      "« Calmez-vous s'il vous plaît, vous n'êtes pas le seul malade ici ! » (escalade conflictuelle).",
      "« Je ne sais pas, demandez au médecin » (abandon froid)."
    )
  ),

  TransitionSection(
    id = "5.11",
    title = "5.11 Introduire et structurer la présentation clinique au médecin",
    subtitle = "Transitions orales professionnelles lors du tour de salle ou du staff",
    objective = "Enchaîner avec assurance les blocs de la synthèse orale devant le médecin senior en moins de 90 secondes.",
    recommendedPhrases = listOf(
      TransitionPhraseItem(
        "« [Accroche] Docteur, je vous présente Monsieur/Madame [Initiales/Nom], [Âge] ans, hospitalisé en chambre [X] pour [Motif sémiologique], sur un terrain de [ATCD majeurs]. »",
        "Phrase d'ouverture donnant d'emblée le terrain et le motif."
      ),
      TransitionPhraseItem(
        "« [Transition Antécédents → HDM] Sur ce terrain à risque, l'histoire de la maladie actuelle a débuté il y a [Durée] de façon [Brutale/Progressive]... »",
        "Articulation logique montrant comment le terrain éclaire l'épisode aigu."
      ),
      TransitionPhraseItem(
        "« [Transition Anamnèse → Examen] À l'admission, le patient est [Conscient/Glasgow], avec des constantes montrant [Constantes clés positives et négatives]. »",
        "Passage net du subjectif vers l'objectif mesuré."
      ),
      TransitionPhraseItem(
        "« [Transition Examen physique] L'examen physique retrouve principalement [Syndrome physique], tandis que le reste de l'examen somatique est sans particularité. »",
        "Mise en relief des signes pertinents sans noyer l'auditeur."
      ),
      TransitionPhraseItem(
        "« [Synthèse & Hypothèses] En synthèse, ce tableau évoque en priorité [Hypothèse 1], mais nous devons formellement éliminer [Urgence vitale / Différentiel]. »",
        "Formulation clinique mature hiérarchisant les priorités."
      ),
      TransitionPhraseItem(
        "« [Ouverture sur la CAT] Sur le plan immédiat, j'ai pensé à proposer [Examens de 1ère intention] et une surveillance [Modalité]. Qu'en pensez-vous pour la suite ? »",
        "Clôture proactive montrant une réflexion médicale construite."
      )
    ),
    phrasesToAvoid = listOf(
      "« Alors voilà, c'est le patient de la chambre 4, il avait déjà eu des trucs avant... »",
      "Lire ses 3 pages de brouillon dans le désordre sans annoncer le motif au début.",
      "Donner la conclusion (« C'est une embolie pulmonaire ») avant même d'avoir donné l'âge ou les signes cliniques."
    )
  )
)
