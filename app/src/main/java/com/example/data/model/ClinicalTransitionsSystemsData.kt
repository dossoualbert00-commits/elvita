package com.example.data.model

internal val transitionTenSystemsData: List<TransitionSystemGroup> = listOf(
  TransitionSystemGroup(
    systemName = "1. Appareil Cardiovasculaire",
    transitionPhrases = listOf(
      "« Passons maintenant au fonctionnement de votre cœur et de votre circulation sanguine. »",
      "« En dehors de votre plainte principale, j'aimerais vérifier comment réagit votre cœur à l'effort et au repos. »",
      "« Regardons ensemble s'il existe des signes au niveau cardiaque ou des vaisseaux des jambes. »",
      "« Vérifions brièvement quelques points sur votre rythme cardiaque et votre circulation. »",
      "« Pour compléter cet aspect vasculaire, posons quelques questions simples sur vos jambes et votre souffle. »"
    ),
    targetedQuestions = listOf(
      "Avez-vous ressenti des douleurs, serrements ou brûlures dans la poitrine à l'effort ou au repos ?",
      "Avez-vous l'impression que votre cœur bat trop vite, trop fort ou de façon irrégulière (palpitations) ?",
      "Êtes-vous essoufflé quand vous marchez vite, montez un étage ou quand vous êtes allongé à plat ?",
      "Avez-vous remarqué un gonflement des chevilles en fin de journée ou des crampes dans les mollets à la marche (claudication) ?",
      "Avez-vous déjà eu un voile noir devant les yeux ou une perte de connaissance brève (syncope) ?"
    ),
    mistakesToAvoid = listOf(
      "Oublier de demander le nombre d'oreillers nécessaires pour dormir (orthopnée).",
      "Confondre douleur pleurale et douleur angineuse sans demander le lien avec l'effort et la trinitrine."
    )
  ),
  TransitionSystemGroup(
    systemName = "2. Appareil Respiratoire",
    transitionPhrases = listOf(
      "« Intéressons-nous maintenant à vos poumons et à votre respiration au quotidien. »",
      "« Après le cœur, faisons le point sur votre souffle, la toux et d'éventuelles sécrétions bronchiques. »",
      "« Regardons s'il existe la moindre gêne respiratoire associée à votre état actuel. »",
      "« Abordons maintenant l'appareil respiratoire, même si vous ne toussez pas aujourd'hui. »",
      "« J'aimerais vous poser quelques questions précises sur votre manière de respirer la nuit et le jour. »"
    ),
    targetedQuestions = listOf(
      "Toussez-vous actuellement ? Si oui, est-ce une toux sèche ou grasse avec des crachats ?",
      "De quelle couleur sont vos crachats (clairs, jaunes, verts, rouillés) et avez-vous déjà vu du sang dedans (hémoptysie) ?",
      "Avez-vous ressenti un sifflement dans la poitrine (wheezing) ou un point de côté qui augmente quand vous inspirez fort ?",
      "Vous sentez-vous essoufflé au repos, à la parole ou au moindre effort de la vie courante ?",
      "Ronflez-vous fortement avec des pauses respiratoires nocturnes et une somnolence le jour (SAOS) ?"
    ),
    mistakesToAvoid = listOf(
      "Oublier de faire la distinction entre crachat sanglant venant de la toux (hémoptysie) et saignement nasal ou digestif.",
      "Ne pas chiffrer l'intensité de la dyspnée (périmètre de marche ou échelle mMRC)."
    )
  ),
  TransitionSystemGroup(
    systemName = "3. Appareil Digestif",
    transitionPhrases = listOf(
      "« Passons maintenant au système digestif, de la déglutition jusqu'au transit intestinal. »",
      "« Parlons de votre appétit, de votre estomac et de votre transit ces derniers jours. »",
      "« Vérifions s'il existe des troubles digestifs liés soit à votre maladie, soit à vos médicaments. »",
      "« Abordons votre ventre et votre digestion de façon simple et méthodique. »",
      "« Faisons un point rapide sur ce que vous mangez, vos nausées éventuelles et vos selles. »"
    ),
    targetedQuestions = listOf(
      "Avez-vous des difficultés ou une sensation de blocage quand vous avalez les aliments solides ou liquides (dysphagie) ?",
      "Avez-vous des brûlures d'estomac, des remontées acides, des nausées ou des vomissements ?",
      "Votre transit s'est-il modifié récemment : constipation nouvelle, arrêt des gaz ou diarrhée ?",
      "Avez-vous remarqué du sang rouge dans les selles (rectorragies) ou des selles très noires et collantes comme du goudron (méléna) ?",
      "Avez-vous noté une douleur après les repas ou un jaunissement des yeux ou de la peau (ictère) ?"
    ),
    mistakesToAvoid = listOf(
      "Oublier de vérifier l'arrêt des matières ET des gaz devant une douleur abdominale (occlusion).",
      "Ne pas poser directement la question sur la couleur noire des selles (méléna)."
    )
  ),
  TransitionSystemGroup(
    systemName = "4. Appareil Neurologique",
    transitionPhrases = listOf(
      "« Continuons par le système nerveux, l'équilibre, la vision et la sensibilité. »",
      "« Je vais vous poser quelques questions sur d'éventuels maux de tête, vertiges ou troubles de la force. »",
      "« Vérifions ensemble si vous avez remarqué un changement dans votre parole, votre mémoire ou vos gestes fins. »",
      "« Passons brièvement au volet neurologique et sensoriel pour être complet. »",
      "« Abordons maintenant votre sommeil, votre équilibre et vos sensations dans les bras et les jambes. »"
    ),
    targetedQuestions = listOf(
      "Souffrez-vous de maux de tête inhabituels, brutaux ou qui vous réveillent la nuit (céphalées) ?",
      "Avez-vous ressenti une faiblesse, un engourdissement ou des fourmillements dans un bras, une jambe ou le visage ?",
      "Avez-vous eu des difficultés passagères à trouver vos mots, à articuler, ou une vision double (diplopie) ?",
      "Avez-vous des troubles de l'équilibre, des chutes inexpliquées ou l'impression que la pièce tourne ?",
      "Avez-vous déjà perdu connaissance avec des secousses musculaires ou une morsure de langue ?"
    ),
    mistakesToAvoid = listOf(
      "Banalsier un déficit neurologique ayant duré 10 minutes puis récupéré (AIT = urgence absolue).",
      "Confondre vertige rotatoire vrai et simple étourdissement orthostatique."
    )
  ),
  TransitionSystemGroup(
    systemName = "5. Appareil Urinaire & Néphrologique",
    transitionPhrases = listOf(
      "« Passons maintenant au fonctionnement de vos reins et de votre vessie. »",
      "« Parlons brièvement de vos urines : fréquence, couleur et confort lors de la miction. »",
      "« Même si ce n'est pas votre plainte principale, vérifions qu'il n'y a pas de gêne urinaire associée. »",
      "« Faisons un point simple sur votre façon d'uriner le jour et la nuit. »",
      "« Vérifions s'il existe le moindre signe rénal ou vésical à prendre en compte. »"
    ),
    targetedQuestions = listOf(
      "Ressentez-vous des brûlures, des douleurs ou une gêne lorsque vous urinez (dysurie / brûlures mictionnelles) ?",
      "Urinez-vous beaucoup plus souvent que d'habitude, notamment la nuit (pollakiurie / nycturie) ?",
      "Vos urines ont-elles changé de couleur ou d'aspect : rouges (hématurie), foncées comme du thé ou très troubles ?",
      "Avez-vous l'impression d'uriner en quantité nettement diminuée ou au contraire beaucoup plus abondante (oligurie vs polyurie) ?",
      "Ressentez-vous une douleur intense dans le bas du dos sur un côté descendant vers le bas-ventre (colique néphrétique) ?"
    ),
    mistakesToAvoid = listOf(
      "Oublier la rétention aiguë d'urines comme cause fréquente d'agitation aiguë chez le sujet âgé.",
      "Confondre urines hématuriques rouges et urines foncées bilirubinuriques."
    )
  ),
  TransitionSystemGroup(
    systemName = "6. Système Endocrinien & Métabolique",
    transitionPhrases = listOf(
      "« Abordons maintenant l'équilibre hormonal, le poids et votre ressenti face à la température. »",
      "« Posons quelques questions générales sur votre appétit, votre soif et votre énergie. »",
      "« Vérifions s'il existe des signes évocateurs d'un dérèglement thyroïdien ou glycémique. »",
      "« Passons au plan métabolique : variations de poids, fatigue globale et tolérance au chaud ou au froid. »",
      "« Faisons le point rapidement sur votre métabolisme général ces derniers mois. »"
    ),
    targetedQuestions = listOf(
      "Avez-vous pris ou perdu plusieurs kilos involontairement ces derniers mois ?",
      "Ressentez-vous une soif excessive inhabituelle vous poussant à boire plusieurs litres par jour (polydipsie) ?",
      "Êtes-vous devenu particulièrement sensible au froid (frilosité) ou au contraire à la chaleur avec sueurs ?",
      "Avez-vous noté une fatigue chronique majeure, des tremblements fins des mains ou une nervosité nouvelle ?",
      "Avez-vous remarqué un gonflement à la base du cou (goitre) ou des crampes musculaires fréquentes ?"
    ),
    mistakesToAvoid = listOf(
      "Négliger une perte de poids chiffrée (calculer le pourcentage de perte par rapport au poids de forme).",
      "Omettre le syndrome polyuro-polydipsique devant une fatigue ou des douleurs abdominales."
    )
  ),
  TransitionSystemGroup(
    systemName = "7. Appareil Locomoteur & Rhumatologique",
    transitionPhrases = listOf(
      "« Passons à vos os, vos muscles et vos articulations dans la vie de tous les jours. »",
      "« Parlons maintenant de votre mobilité : dos, épaules, hanches, genoux et mains. »",
      "« Vérifions si certaines articulations sont douloureuses, gonflées ou raides au réveil. »",
      "« Abordons le volet musculo-squelettique pour évaluer votre confort et votre marche. »",
      "« Faisons un rapide bilan de vos douleurs articulaires ou musculaires récentes. »"
    ),
    targetedQuestions = listOf(
      "Avez-vous des douleurs dans une ou plusieurs articulations ? Sont-elles rouges, chaudes ou gonflées ?",
      "Vos articulations sont-elles raides le matin au réveil pendant plus de 30 minutes (horaire inflammatoire) ou uniquement le soir après l'effort (horaire mécanique) ?",
      "Souffrez-vous du bas du dos avec une douleur qui descend dans la fesse et la jambe (sciatalgie) ?",
      "Ressentez-vous des douleurs musculaires diffuses (myalgies) ou une faiblesse pour vous lever d'une chaise ?",
      "Avez-vous subi un traumatisme, une chute ou une entorse récente ces dernières semaines ?"
    ),
    mistakesToAvoid = listOf(
      "Oublier de distinguer rythme inflammatoire (réveil nocturne + dérouillage > 30 min) et rythme mécanique.",
      "Négliger une monoarthrite aiguë fébrile (urgence : arthrite septique)."
    )
  ),
  TransitionSystemGroup(
    systemName = "8. Système Dermatologique & Téguments",
    transitionPhrases = listOf(
      "« Intéressons-nous brièvement à votre peau, vos ongles, vos cheveux et vos muqueuses. »",
      "« Vérifions si vous avez remarqué des boutons, plaques ou taches inhabituelles sur le corps. »",
      "« Passons à l'aspect cutané : démangeaisons, plaies ou changement de couleur récent. »",
      "« Posons deux ou trois questions sur votre peau, souvent très révélatrice de la santé générale. »",
      "« Avant de terminer, j'aimerais savoir s'il y a eu la moindre réaction cutanée récente. »"
    ),
    targetedQuestions = listOf(
      "Avez-vous remarqué l'apparition récente de plaques rouges, de boutons, de cloques ou de bleus spontanés ?",
      "Souffrez-vous de démangeaisons intenses (prurit), notamment le soir ou après la douche ?",
      "Avez-vous une plaie qui cicatrise mal sur les jambes, les pieds ou ailleurs ?",
      "Avez-vous vu un grain de beauté changer rapidement de taille, de couleur ou saigner ?",
      "Avez-vous des aphtes fréquents dans la bouche ou une perte anormale de cheveux ?"
    ),
    mistakesToAvoid = listOf(
      "Omettre de rechercher un purpura sur la peau lors de tout syndrome infectieux fébrile.",
      "Ne pas examiner les pieds chez un patient diabétique présentant de la fièvre."
    )
  ),
  TransitionSystemGroup(
    systemName = "9. Système Hématologique & Immunitaire",
    transitionPhrases = listOf(
      "« Vérifions rapidement quelques points concernant le sang, les saignements et vos défenses. »",
      "« Passons au plan hématologique : tendance aux bleus, saignements ou ganglions. »",
      "« J'aimerais savoir si vous saignez facilement ou si vous avez remarqué des boules sous la peau. »",
      "« Abordons maintenant le système sanguin et ganglionnaire de manière simple. »",
      "« Terminons par quelques questions sur la coagulation et les signes généraux (fièvre, sueurs). »"
    ),
    targetedQuestions = listOf(
      "Faites-vous facilement des bleus (ecchymoses) sans choc, ou saignez-vous souvent du nez ou des gencives ?",
      "Avez-vous remarqué des petites boules indolores ou sensibles dans le cou, sous les bras ou à l'aine (adénopathies) ?",
      "Avez-vous présenté des sueurs nocturnes trempant les draps ou une fièvre prolongée inexpliquée ?",
      "Avez-vous des infections ORL ou pulmonaires qui se répètent anormalement ces derniers mois ?",
      "Avez-vous déjà eu besoin d'une perfusion de fer ou d'une transfusion sanguine par le passé ?"
    ),
    mistakesToAvoid = listOf(
      "Négliger les gingivorragies et épistaxis spontanées chez un patient sous anticoagulant ou thrombopénique.",
      "Oublier les signes B (fièvre > 38°C, sueurs nocturnes, perte de poids > 10%)."
    )
  ),
  TransitionSystemGroup(
    systemName = "10. Gynéco-Obstétrique, ORL & Ophtalmologie",
    transitionPhrases = listOf(
      "« Pour être tout à fait complet, vérifions la sphère ORL, les yeux et, chez les patientes, l'aspect gynécologique. »",
      "« Regardons rapidement la vue, l'audition et la gorge, souvent négligées lors d'un entretien rapide. »",
      "« Posons quelques questions rapides et respectueuses sur le cycle menstruel ou la sphère pelvienne. »",
      "« Terminons la revue des systèmes par vos yeux, vos oreilles et votre sphère intime. »",
      "« Ces dernières questions concernent vos organes des sens et votre santé hormonale/reproductive. »"
    ),
    targetedQuestions = listOf(
      "[Ophtalmo] Avez-vous une baisse récente de la vue, un œil rouge douloureux ou des mouches volantes ?",
      "[ORL] Avez-vous mal à l'oreille, une baisse d'audition, le nez bouché d'un seul côté ou la voix enrouée depuis plus de 3 semaines ?",
      "[Gynéco] Quelle est la date exacte de vos dernières règles et existe-t-il une possibilité de grossesse actuelle ?",
      "[Gynéco] Avez-vous des saignements vaginaux inhabituels (métrorragies), des pertes anormales ou des douleurs pelviennes ?",
      "[Uro-andro] Pour les hommes : avez-vous noté un jet urinaire plus faible, une boule testiculaire ou une douleur scrotale aiguë ?"
    ),
    mistakesToAvoid = listOf(
      "Oublier la date des dernières règles devant toute douleur abdominale chez la femme en âge de procréer (risque de GEU).",
      "Négliger une dysphonie chronique chez un patient tabagique (cancer ORL)."
    )
  )
)
