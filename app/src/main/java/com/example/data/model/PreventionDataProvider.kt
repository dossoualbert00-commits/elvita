package com.example.data.model

data class PreventionTopic(
  val id: String,
  val title: String,
  val subtitle: String,
  val keyPoints: List<String>,
  val clinicalMessage: String,
  val tag: String,
  // 7 rubriques structurées obligatoires (adaptées selon âge, sexe, risque et contexte)
  val definition: String,
  val riskFactors: List<String>,
  val targetPopulation: String,
  val preventionMeasures: List<String>,
  val screeningRecommendations: String,
  val practicalAdvice: List<String>,
  val warningSigns: List<String>
)

object PreventionDataProvider {

  val topics: List<PreventionTopic> = listOf(
    // 1. Paludisme (conservé et enrichi)
    PreventionTopic(
      id = "malaria",
      title = "Paludisme : Urgence & Prévention",
      subtitle = "Goutte épaisse, TDR, chimioprophylaxie et gravité OMS",
      tag = "Infectiologie",
      keyPoints = listOf(
        "Toute fièvre au retour de zone d'endémie est un paludisme jusqu'à preuve du contraire.",
        "Rechercher immédiatement les signes de gravité : coma, détresse respiratoire, convulsions, hypoglycémie, ictère.",
        "Diagnostic parasitologique en urgence (Frottis-Goutte Épaisse et TDR) avant traitement."
      ),
      clinicalMessage = "Traitement du neuropaludisme / accès grave : Artésunate intraveineux en extrême urgence.",
      definition = "Parasitose potentiellement mortelle due à Plasmodium (P. falciparum surtout), transmise par la piqûre nocturne de l'anophèle femelle.",
      riskFactors = listOf(
        "Séjour ou résidence en zone d'endémie intertropicale (Afrique subsaharienne, Amazonie, Asie du Sud-Est).",
        "Absence ou mauvaise observance de chimioprophylaxie et de protection antivectorielle.",
        "Terrains vulnérables : jeunes enfants < 5 ans, femmes enceintes, sujets non immuns, splénectomisés."
      ),
      targetPopulation = "Voyageurs en zone d'endémie, habitants des zones endémiques, femmes enceintes et enfants < 5 ans (risque maximal d'accès grave).",
      preventionMeasures = listOf(
        "Protection mécanique nocturne : moustiquaire imprégnée d'insecticide à longue durée d'action (MILDA), vêtements longs le soir.",
        "Répulsifs cutanés adaptés (DEET, Icaridine) selon l'âge et la grossesse.",
        "Chimioprophylaxie antipaludique adaptée au pays, à l'âge et à la fonction rénale/hépatique."
      ),
      screeningRecommendations = "TDR (Test de Diagnostic Rapide) + Frottis sanguin et Goutte Épaisse en extrême urgence devant TOUTE fièvre ou syndrome grippal dans les 3 mois suivant le retour.",
      practicalAdvice = listOf(
        "Ne jamais attendre les résultats au-delà de 2 heures devant des signes de gravité clinique.",
        "Chez la femme enceinte : prévention par traitement préventif intermittent (TPI) en zone d'endémie."
      ),
      warningSigns = listOf(
        "Troubles de conscience / obnubilation / convulsions (neuropaludisme)",
        "Détresse respiratoire, état de choc, ictère franc, hémoglobinurie (urines 'porto')",
        "Hypoglycémie < 0.40 g/L ou acidose métabolique / hyperlactatémie"
      )
    ),

    // 2. Poliomyélite (conservé et enrichi)
    PreventionTopic(
      id = "polio",
      title = "Poliomyélite & Paralysie Flasque",
      subtitle = "Surveillance mondiale, PFA et couverture vaccinale",
      tag = "Santé Publique",
      keyPoints = listOf(
        "Transmission féco-orale interhumaine par les poliovirus sauvages ou dérivés de souches vaccinales.",
        "Tableau clinique : paralysie flasque aiguë asymétrique avec abolition des ROT, sans atteinte sensitive.",
        "Prévention absolue par la vaccination systématique (VPI injectable et VPO)."
      ),
      clinicalMessage = "Toute paralysie flasque aiguë (PFA) chez l'enfant impose une déclaration et un prélèvement de selles.",
      definition = "Infection virale aiguë à entérovirus (poliovirus) atteignant les motoneurones de la corne antérieure de la moelle épinière, responsable de paralysies flasques irréversibles.",
      riskFactors = listOf(
        "Défaut de couverture vaccinale infantile.",
        "Précarité hydrique et défaut d'assainissement favorisant la transmission féco-orale."
      ),
      targetPopulation = "Nourrissons et enfants < 15 ans (cible prioritaire du PEV), voyageurs vers des pays à circulation résiduelle.",
      preventionMeasures = listOf(
        "Vaccination universelle complète (VPI / VPO) dès les premiers mois de vie avec rappels.",
        "Hygiène des mains, accès à l'eau potable et assainissement."
      ),
      screeningRecommendations = "Surveillance épidémiologique de tout cas de Paralysie Flasque Aiguë (PFA) chez le sujet < 15 ans : 2 prélèvements de selles à 24-48h d'intervalle dans les 14 jours.",
      practicalAdvice = listOf(
        "Vérifier systématiquement le carnet vaccinal de tout enfant hospitalisé.",
        "Notifier sans délai toute PFA aux autorités sanitaires."
      ),
      warningSigns = listOf(
        "Installation aiguë d'une faiblesse asymétrique d'un membre avec hypotonie et abolition des ROT",
        "Atteinte bulbaire : troubles de déglutition, dysphonie, paralysie diaphragmatique"
      )
    ),

    // 3. Hygiène des mains (conservé et enrichi)
    PreventionTopic(
      id = "hygiene",
      title = "Hygiène des Mains & Précautions Standard",
      subtitle = "Les 5 indications OMS au lit du patient",
      tag = "Sécurité du soin",
      keyPoints = listOf(
        "Avant de toucher un patient.",
        "Avant un geste aseptique (pose de cathéter, pansement).",
        "Après un risque d'exposition à un liquide biologique, après contact patient et environnement."
      ),
      clinicalMessage = "La friction hydro-alcoolique (FHA) sur mains sèches et non souillées est la référence absolue.",
      definition = "Ensemble des mesures barrières de base appliquées à tout patient quel que soit son statut infectieux pour prévenir la transmission croisée manuportée.",
      riskFactors = listOf(
        "Port de bijoux, montre, bague, vernis à ongles ou manches longues.",
        "Charge de travail élevée et oubli de la friction entre deux patients."
      ),
      targetPopulation = "Tout personnel soignant, étudiants en médecine/soins infirmiers et visiteurs en milieu hospitalier.",
      preventionMeasures = listOf(
        "Règle zéro bijou, ongles courts sans vernis, avant-bras dégagés.",
        "Friction hydro-alcoolique 20-30 secondes (7 étapes) ou lavage au savon doux si mains visiblement souillées/talquées ou Clostridioides difficile."
      ),
      screeningRecommendations = "Audits périodiques d'observance de la FHA et consommation de solution hydro-alcoolique par lit et par jour.",
      practicalAdvice = listOf(
        "Toujours effectuer une FHA avant de saisir le stéthoscope puis désinfecter la membrane après examen.",
        "Porter des gants uniquement en cas de risque de contact avec sang/liquides biologiques et les retirer immédiatement après le geste."
      ),
      warningSigns = listOf(
        "Cas groupés d'infections nosocomiales dans une unité",
        "Portage ou épidémie à bactérie hautement résistante (BHRe) dans le service"
      )
    ),

    // 4. Vaccination & Calendrier vaccinal (conservé et élargi population générale + soignants)
    PreventionTopic(
      id = "vaccines",
      title = "Vaccination & Calendrier Vaccinal",
      subtitle = "Couverture du nourrisson, adulte, senior et soignant",
      tag = "Immunologie",
      keyPoints = listOf(
        "Nourrisson/enfant : BCG, DTP, Coqueluche, Hib, Hépatite B, Pneumocoque, Méningocoque, ROR, Rotavirus.",
        "Adolescent/Adulte : rappels dTPca à 25, 45, 65 ans puis tous les 10 ans ; vaccination HPV filles et garçons (11-14 ans).",
        "Soignants : Hépatite B obligatoire avec contrôle anti-HBs (> 100 UI/L = immunité durable), grippe annuelle."
      ),
      clinicalMessage = "Vérifier le statut vaccinal à chaque consultation et chez la femme enceinte (coqueluche, grippe, VRS).",
      definition = "Immunisation active préventive permettant de protéger l'individu contre les maladies infectieuses graves et de créer une immunité collective protectrice pour les plus fragiles.",
      riskFactors = listOf(
        "Retard ou rupture de calendrier vaccinal.",
        "Terrains à risque : âges extrêmes (< 1 an, >= 65 ans), grossesse, immunodépression, asplénie, pathologies chroniques."
      ),
      targetPopulation = "Nourrissons, enfants, adolescents (HPV), femmes enceintes, seniors >= 65 ans (Grippe, Pneumocoque, Zona, VRS) et professionnels exposés.",
      preventionMeasures = listOf(
        "Tenue rigoureuse du carnet de vaccination.",
        "Stratégie du cocooning autour du nouveau-né (coqueluche chez la mère enceinte entre 20-36 SA et entourage).",
        "Vaccination antigrippale annuelle et antipneumococcique chez les patients BPCO, insuffisants cardiaques, diabétiques ou immunodéprimés."
      ),
      screeningRecommendations = "Vérification systématique à chaque hospitalisation, bilan pré-thérapeutique (avant immunosuppresseurs) et contrôle sérologique anti-HBs chez les soignants.",
      practicalAdvice = listOf(
        "Un retard vaccinal ne nécessite jamais de reprendre le schéma à zéro : il suffit de reprendre là où il s'est arrêté.",
        "Les vaccins vivants atténués (ROR, Fièvre jaune, Varicelle) sont contre-indiqués chez la femme enceinte et l'immunodéprimé sévère."
      ),
      warningSigns = listOf(
        "Réaction anaphylactique immédiate post-injection (surveillance 15 minutes recommandée)",
        "Fièvre ou syndrome méningé chez un patient asplénique non vacciné contre pneumocoque/méningocoque"
      )
    ),

    // 5. Maladies cardiovasculaires (conservé et enrichi)
    PreventionTopic(
      id = "cardio_prev",
      title = "Prévention des Maladies Cardiovasculaires",
      subtitle = "Risque cardiovasculaire global et athérosclérose",
      tag = "Cardiologie",
      keyPoints = listOf(
        "Évaluer le risque global (SCORE2 / SCORE2-OP) intégrant âge, sexe, PA, tabac et cholestérol non-HDL.",
        "Sevrage tabagique complet : bénéfice vasculaire précoce et réduction majeure du risque d'infarctus et d'AVC.",
        "Objectif LDL-c adapté au niveau de risque (< 0.55 g/L si très haut risque, < 0.70 g/L si haut risque)."
      ),
      clinicalMessage = "La prévention cardiovasculaire repose sur la maîtrise conjointe de TOUS les facteurs de risque modifiables.",
      definition = "Ensemble des mesures visant à prévenir la survenue des événements athérothrombotiques (syndrome coronarien aigu, AVC ischémique, artériopathie oblitérante des membres inférieurs).",
      riskFactors = listOf(
        "Non modifiables : âge (> 50 ans homme, > 60 ans femme), sexe masculin, hérédité coronaire précoce.",
        "Modifiables : tabagisme actif, HTA, diabète, dyslipidémie (LDL élevé), obésité abdominale, sédentarité, stress chronique."
      ),
      targetPopulation = "Adultes à partir de 40 ans (plus tôt si antécédents familiaux ou diabète), sujets porteurs de facteurs de risque ou en prévention secondaire après événement vasculaire.",
      preventionMeasures = listOf(
        "Arrêt total du tabac avec accompagnement (substituts nicotiniques, suivi).",
        "Alimentation de type méditerranéen (fruits, légumes, poisson, huile d'olive, réduction sel/graisses saturées).",
        "Activité physique régulière : >= 150 min/semaine d'endurance modérée."
      ),
      screeningRecommendations = "Bilan lipidique (EAL) + glycémie à jeun + mesure PA dès 40 ans chez l'homme et 50 ans/ménopause chez la femme (plus tôt si facteurs de risque) ; ECG si symptômes ou haut risque.",
      practicalAdvice = listOf(
        "Éduquer le patient sur les signaux d'alerte d'infarctus (douleur thoracique prolongée > 20 min) et d'AVC (FAST).",
        "Vérifier l'observance des statines et antihypertenseurs sans banaliser les douleurs musculaires inexpliquées."
      ),
      warningSigns = listOf(
        "Douleur thoracique constrictive rétro-sternale irradiant bras gauche/mâchoire avec sueurs",
        "Déficit neurologique brutal (paralysie faciale, faiblesse d'un membre, trouble de la parole = appel 15 immédiat)",
        "Claudication intermittente des membres inférieurs ou douleur de repos du pied"
      )
    ),

    // 6. Infections nosocomiales (conservé et enrichi)
    PreventionTopic(
      id = "nosocomial",
      title = "Infections Associées aux Soins (IAS)",
      subtitle = "Bon usage des antibiotiques et dispositifs invasifs",
      tag = "Hygiène",
      keyPoints = listOf(
        "Retirer tout dispositif invasif (cathéter veineux, sonde urinaire) dès qu'il n'est plus indispensable.",
        "Réévaluer toute antibiothérapie probabiliste à 48-72h avec les résultats bactériologiques.",
        "Respect strict des précautions complémentaires : Contact, Gouttelettes, Air."
      ),
      clinicalMessage = "La sonde vésicale est la première cause d'infection nosocomiale : pas de sonde de confort !",
      definition = "Infection survenant au cours ou au décours d'une prise en charge hospitalière, n'étant ni présente ni en incubation à l'admission (délai usuel >= 48h après admission).",
      riskFactors = listOf(
        "Sondage urinaire à demeure prolongé, cathéters centraux ou périphériques, ventilation mécanique.",
        "Âges extrêmes, immunodépression, antibiothérapie large spectre favorisant les BMR."
      ),
      targetPopulation = "Tout patient hospitalisé (en particulier réanimation, chirurgie, gériatrie, hématologie) et personnel soignant.",
      preventionMeasures = listOf(
        "Pose aseptique stricte des cathéters et ablation précoce ; surveillance quotidienne des points de ponction.",
        "Système clos permanent pour toute sonde urinaire ; préférence aux étuis péniens ou sondages intermittents si adaptés.",
        "Préparation cutanée pré-opératoire et antibioprophylaxie ciblée."
      ),
      screeningRecommendations = "Surveillance clinique quotidienne des sites d'insertion (rougeur, induration, pus), température et réévaluation de la pertinence des dispositifs à chaque visite.",
      practicalAdvice = listOf(
        "Ne pas faire d'ECBU sur simple aspect trouble/malodorant chez un patient sondé asymptomatique.",
        "Appliquer strictement les fiches d'isolement affichées sur la porte de chambre."
      ),
      warningSigns = listOf(
        "Fièvre nouvelle >= 38.3°C après 48h d'hospitalisation chez un patient porteur de cathéter ou sonde",
        "Signes locaux de veinite (cordon rouge chaud douloureux sur voie veineuse)",
        "Diarrhée glairo-sanglante ou aqueuse sous antibiotiques (penser à Clostridioides difficile)"
      )
    ),

    // 7. Cancer du sein
    PreventionTopic(
      id = "breast_cancer",
      title = "Cancer du Sein : Prévention & Dépistage",
      subtitle = "Autopalpation, examen clinique annuel et mammographie",
      tag = "Oncologie",
      keyPoints = listOf(
        "Premier cancer féminin : un dépistage précoce permet une guérison dans plus de 85-90% des cas.",
        "Examen clinique mammaire annuel dès 25 ans ; mammographie bilatérale tous les 2 ans de 50 à 74 ans (risque moyen).",
        "Adapté dès 30-40 ans par IRM/mammographie annuelle en cas de haut risque génétique (BRCA1/2)."
      ),
      clinicalMessage = "Tout nodule mammaire apparu récemment chez une femme, quel que soit son âge, doit être exploré par imagerie et biopsie.",
      definition = "Prolifération maligne développée aux dépens des cellules épithéliales de la glande mammaire (carcinomes canalaires ou lobulaires le plus souvent).",
      riskFactors = listOf(
        "Âge (> 50 ans), sexe féminin, antécédent personnel ou familial au 1er degré (mutations BRCA1/2, PALB2).",
        "Exposition œstrogénique prolongée (puberté précoce, ménopause tardive, THM prolongé), obésité post-ménopausique, alcool, tabac, sédentarité."
      ),
      targetPopulation = "Femmes >= 25 ans (examen clinique annuel), femmes de 50 à 74 ans (dépistage organisé), et femmes de tout âge à haut risque familial/génétique.",
      preventionMeasures = listOf(
        "Activité physique régulière, maintien d'un poids normal après la ménopause.",
        "Limitation de la consommation d'alcool et sevrage tabagique.",
        "Allaitement maternel (effet protecteur démontré) et réévaluation régulière des traitements hormonaux."
      ),
      screeningRecommendations = "Risque moyen : examen clinique annuel dès 25 ans + mammographie/échographie tous les 2 ans de 50 à 74 ans. Haut risque familial/BRCA : suivi oncogénétique et IRM annuelle dès 30 ans.",
      practicalAdvice = listOf(
        "Réaliser l'examen des deux seins en position assise puis couchée, bras levés, sans oublier les aires ganglionnaires axillaires et sus-claviculaires.",
        "Ne pas rassurer à tort sur une masse dure indolore chez une femme jeune sans échographie mammaire."
      ),
      warningSigns = listOf(
        "Nodule mammaire dur, indolore, mal limité ou fixé",
        "Rétraction cutanée ou mamelonnaire récente, aspect en 'peau d'orange'",
        "Écoulement mamelonnaire unilatéral sanglant ou unipore, adénopathie axillaire dure"
      )
    ),

    // 8. Cancer du col de l'utérus
    PreventionTopic(
      id = "cervical_cancer",
      title = "Cancer du Col de l'Utérus",
      subtitle = "Vaccination anti-HPV, frottis (FCU) et test HPV-HR",
      tag = "Gynécologie",
      keyPoints = listOf(
        "Dû dans quasi 100% des cas à une infection persistante par Papillomavirus Humains oncogènes (HPV 16, 18...).",
        "Prévention primaire : vaccination HPV recommandée chez les filles ET les garçons (11-14 ans, rattrapage jusqu'à 19-26 ans).",
        "Dépistage secondaire organisé : cytologie (FCU) entre 25 et 29 ans, puis test HPV-HR tous les 5 ans entre 30 et 65 ans."
      ),
      clinicalMessage = "La combinaison vaccination HPV + dépistage régulier permet d'éviter plus de 90% des cancers du col.",
      definition = "Tumeur maligne du col utérin (épidermoïde dans 85% des cas), précédée pendant 10-15 ans de lésions précancéreuses (CIN) curables si dépistées.",
      riskFactors = listOf(
        "Infection persistante à HPV à haut risque oncogène (transmission sexuelle).",
        "Tabagisme actif (cofacteur majeur de persistance virale), partenaires multiples, rapports précoces, immunodépression (VIH)."
      ),
      targetPopulation = "Pré-adolescents/adolescents (vaccination), femmes de 25 à 65 ans (dépistage même vaccinées !), fréquence accrue chez les femmes vivant avec le VIH.",
      preventionMeasures = listOf(
        "Vaccination anti-HPV nonavalente avant le début de la vie sexuelle.",
        "Sevrage tabagique et utilisation du préservatif (réduction partielle du risque).",
        "Traitement rapide des lésions précancéreuses de haut grade (conisation)."
      ),
      screeningRecommendations = "25-29 ans : 2 frottis cytologiques à 1 an d'intervalle puis à 3 ans. 30-65 ans : test HPV-HR tous les 5 ans (si négatif). Même chez les femmes vaccinées !",
      practicalAdvice = listOf(
        "Toujours interroger sur la date du dernier frottis / test HPV lors d'une consultation féminine.",
        "Rassurer : une infection HPV transitoire avant 30 ans guérit spontanément dans la majorité des cas."
      ),
      warningSigns = listOf(
        "Métrorragies provoquées (saignements après les rapports sexuels) ou spontanées post-ménopausiques",
        "Leucorrhées fétides chroniques striées de sang",
        "Douleurs pelviennes ou lombaires tardives (compression urétérale)"
      )
    ),

    // 9. Cancer de la prostate
    PreventionTopic(
      id = "prostate_cancer",
      title = "Cancer de la Prostate : Information & Dépistage",
      subtitle = "Toucher rectal, PSA et décision partagée dès 50 ans",
      tag = "Urologie",
      keyPoints = listOf(
        "Cancer le plus fréquent chez l'homme > 50 ans, le plus souvent d'évolution lente.",
        "Dépistage individuel reposant sur l'association Toucher Rectal (TR) + dosage PSA après information éclairée.",
        "Démarche proposée de 50 à 75 ans (dès 45 ans si origine afro-caribéenne ou antécédents familiaux >= 2 parents)."
      ),
      clinicalMessage = "Pas de dépistage de masse aveugle : le dosage du PSA doit s'accompagner d'un toucher rectal et d'une décision médicale partagée.",
      definition = "Adénocarcinome développé aux dépens de la zone périphérique de la glande prostatique, hormono-dépendant (androgènes).",
      riskFactors = listOf(
        "Âge (> 50 ans), antécédents familiaux de cancer de la prostate ou du sein (BRCA2).",
        "Origine ethnique afro-caribéenne (risque accru et survenue plus précoce)."
      ),
      targetPopulation = "Hommes de 50 à 75 ans ayant une espérance de vie > 10 ans ; dès 45 ans si haut risque génétique/familial ou origine afro-antillaise.",
      preventionMeasures = listOf(
        "Information éclairée sur les bénéfices (détection précoce) et risques (surdiagnostic, biopsies inutiles) avant prescription du PSA.",
        "Activité physique régulière et limitation du surpoids."
      ),
      screeningRecommendations = "Démarche individuelle : Toucher Rectal + PSA total à 50 ans (45 ans si haut risque). Éliminer d'abord prostatite ou sondage récent avant d'interpréter un PSA élevé.",
      practicalAdvice = listOf(
        "Devant un PSA > 4 ng/mL ou un TR dur nodulaire : l'IRM multiparamétrique prostatique précède aujourd'hui les biopsies ciblées.",
        "Ne jamais prescrire de PSA pendant une infection urinaire aiguë (fausse élévation majeure)."
      ),
      warningSigns = listOf(
        "Nodule dur pierreux asymétrique ou induration au toucher rectal",
        "Douleurs osseuses rachidiennes ou pelviennes d'horaire inflammatoire chez un homme âgé",
        "Hématurie, troubles mictionnels d'apparition récente et rapide, altération de l'état général"
      )
    ),

    // 10. Cancer colorectal
    PreventionTopic(
      id = "colorectal_cancer",
      title = "Cancer Colorectal : Dépistage & Prévention",
      subtitle = "Test immunologique (FIT) et coloscopie ciblée",
      tag = "Gastro-entérologie",
      keyPoints = listOf(
        "Se développe le plus souvent à partir d'un polype adénomateux bénin sur 5 à 10 ans.",
        "Dépistage organisé par test immunologique fécal (recherche de sang occulte) tous les 2 ans de 50 à 74 ans.",
        "Haut risque familial (antécédent 1er degré < 65 ans) ou symptômes = coloscopie directe d'emblée (pas de test fécal !)."
      ),
      clinicalMessage = "Un test fécal positif ou des rectorragies imposent une coloscopie totale — jamais de répétition du test.",
      definition = "Adénocarcinome du côlon ou du rectum, 2e cause de mortalité par cancer mais largement évitable par exérèse endoscopique des polypes.",
      riskFactors = listOf(
        "Âge > 50 ans, polypes adénomateux antérieurs, antécédents familiaux (syndrome de Lynch, polypose adénomateuse familiale).",
        "Viande rouge et charcuteries en excès, alimentation pauvre en fibres, tabac, alcool, sédentarité, obésité, MICI ancienne (RCH/Crohn)."
      ),
      targetPopulation = "Hommes et femmes de 50 à 74 ans (risque moyen) ; plus tôt (40-45 ans ou -5 ans avant le cas index) si haut risque familial ou MICI.",
      preventionMeasures = listOf(
        "Alimentation riche en fibres (légumes, fruits, céréales complètes), réduction viandes transformées et alcool.",
        "Activité physique régulière et sevrage tabagique.",
        "Polypectomie endoscopique des adénomes."
      ),
      screeningRecommendations = "Risque moyen (50-74 ans) : test immunologique dans les selles (FIT) tous les 2 ans. Haut risque familial / MICI / symptômes : coloscopie directe.",
      practicalAdvice = listOf(
        "Tout saignement anal (même chez un patient hémorroïdaire connu > 45 ans) ou anémie ferriprive inexpliquée justifie d'explorer le côlon.",
        "Expliquer la simplicité du test immunologique (1 seul prélèvement indolore à domicile)."
      ),
      warningSigns = listOf(
        "Rectorragies ou méléna, anémie ferriprive sans cause évidente chez l'adulte > 45 ans",
        "Modification récente et durable du transit (alternance diarrhée/constipation)",
        "Douleurs abdominales chroniques, syndrome rectal, amaigrissement inexpliqué"
      )
    ),

    // 11. Hypertension artérielle (HTA)
    PreventionTopic(
      id = "hypertension",
      title = "Hypertension Artérielle (HTA) : Contrôle & Risques",
      subtitle = "Automesure tensionnelle, règles hygiéno-diététiques et cibles",
      tag = "Cardiologie",
      keyPoints = listOf(
        "Définie au cabinet par PAS >= 140 mmHg et/ou PAD >= 90 mmHg confirmée sur plusieurs mesures (ou MAPA/AMT >= 135/85).",
        "Maladie silencieuse ('tueur silencieux') causant AVC, insuffisance cardiaque, infarctus et insuffisance rénale.",
        "Mesures hygiéno-diététiques systématiques : réduction du sel (< 5-6 g/j), activité physique, perte de poids."
      ),
      clinicalMessage = "Ne jamais porter un diagnostic d'HTA sur une seule mesure : confirmer par Automesure Tensionnelle (règle des 3) ou MAPA.",
      definition = "Élévation chronique pathologique de la pression artérielle systémique, facteur de risque majeur d'AVC, cardiopathie hypertensive et néphroangiosclérose.",
      riskFactors = listOf(
        "Âge, hérédité, origine africaine/antillaise (HTA souvent plus précoce et sévère).",
        "Apport sodé excessif, surpoids/obésité, sédentarité, alcool, syndrome d'apnées du sommeil (SAOS), AINS/corticoïdes/réglisse."
      ),
      targetPopulation = "Tout adulte (dépistage annuel au moins, et à chaque consultation), surveillance renforcée chez le diabétique, la femme enceinte (pré-éclampsie) et le sujet âgé.",
      preventionMeasures = listOf(
        "Réduction sodée (< 5-6 g NaCl/j), alimentation riche en potassium (fruits/légumes) sauf insuffisance rénale.",
        "Activité aérobie 30 min/j, limitation alcool, gestion du poids.",
        "Observance stricte du traitement antihypertenseur (bithérapie précoce si besoin)."
      ),
      screeningRecommendations = "Mesure de la PA aux deux bras au repos (5 min assis, brassard adapté) au minimum chaque année. Confirmation par automesure (3 mesures matin et soir pendant 3 jours) ou MAPA.",
      practicalAdvice = listOf(
        "Devant une HTA chez un sujet jeune (< 35 ans) ou résistante à 3 traitements : rechercher une HTA secondaire (sténose artère rénale, hyperaldostéronisme, phéochromocytome, SAOS).",
        "Rechercher systématiquement l'atteinte des organes cibles : ECG, créatininémie, bandelette urinaire (protéinurie)."
      ),
      warningSigns = listOf(
        "PAS >= 180 ou PAD >= 110 mmHg avec douleur thoracique, dyspnée (OAP), déficit neurologique (AVC) ou céphalées sévères (urgence hypertensive)",
        "Chez la femme enceinte >= 20 SA : PA >= 140/90 avec protéinurie, céphalées, phosphènes ou barre épigastrique (pré-éclampsie !)"
      )
    ),

    // 12. Diabète sucré
    PreventionTopic(
      id = "diabetes",
      title = "Diabète : Dépistage, Éducation & Complications",
      subtitle = "Prévention du DT2, pied diabétique et signes de décompensation",
      tag = "Endocrinologie",
      keyPoints = listOf(
        "Diagnostic : glycémie à jeun >= 1.26 g/L (7.0 mmol/L) à 2 reprises, ou glycémie aléatoire >= 2.0 g/L avec symptômes, ou HbA1c >= 6.5%.",
        "Chez le patient à risque (surpoids >= 45 ans, ATCD familial/diabète gestationnel) : dépistage ciblé annuel.",
        "Prévention des complications micro- et macrovasculaires : HbA1c cible (~7%), PA, LDL, examen des pieds et fond d'œil annuel."
      ),
      clinicalMessage = "Devant une polyuro-polydipsie + nausées/douleurs abdominales/dyspnée : rechercher d'urgence une acétonurie (acidocétose !).",
      definition = "Maladie métabolique chronique caractérisée par une hyperglycémie durable liée à un défaut de sécrétion d'insuline (DT1) et/ou une insulinorésistance (DT2).",
      riskFactors = listOf(
        "Surpoids / obésité abdominale, sédentarité, âge > 45 ans, alimentation riche en sucres rapides et graisses saturées.",
        "Antécédent familial de diabète de type 2, antécédent de diabète gestationnel ou de macrosomie fœtale (> 4 kg), HTA, dyslipidémie."
      ),
      targetPopulation = "Adultes >= 45 ans en surpoids ou avec >= 1 facteur de risque, femmes ayant eu un diabète gestationnel, patients sous corticoïdes ou antipsychotiques.",
      preventionMeasures = listOf(
        "Activité physique régulière (améliore immédiatement la sensibilité à l'insuline) et réduction pondérale de 5-7% en cas de prédiabète.",
        "Alimentation équilibrée à index glycémique bas, arrêt des boissons sucrées.",
        "Éducation du patient diabétique : auto-surveillance, soins quotidiens des pieds, chaussage adapté."
      ),
      screeningRecommendations = "Glycémie veineuse à jeun (ou HbA1c) tous les 1 à 3 ans chez l'adulte à risque ; dépistage systématique au 1er et 2e trimestre (HGPO 24-28 SA) chez la femme enceinte à risque.",
      practicalAdvice = listOf(
        "Examiner les pieds à chaque visite (monofilament, pouls pédieux, mycoses interdigitales, cors).",
        "Bilan annuel du diabétique : HbA1c/3-6 mois, créatinine + microalbuminurie, bilan lipidique, fond d'œil, ECG."
      ),
      warningSigns = listOf(
        "Syndrome polyuro-polydipsique intense, amaigrissement rapide, dyspnée de Kussmaul, haleine cétonique (acidocétose)",
        "Déshydratation majeure avec trouble de vigilance du sujet âgé (coma hyperosmolaire)",
        "Sueurs, tremblements, confusion (hypoglycémie sous insuline/sulfamide) ou plaie du pied infectée"
      )
    ),

    // 13. Infections sexuellement transmissibles (IST)
    PreventionTopic(
      id = "sti",
      title = "Infections Sexuellement Transmissibles (IST)",
      subtitle = "Chlamydia, Gonocoque, Syphilis : dépistage et partenaires",
      tag = "Infectiologie",
      keyPoints = listOf(
        "Très fréquemment asymptomatiques (notamment Chlamydia trachomatis), exposant à stérilité tubaire et grossesse extra-utérine.",
        "Usage du préservatif (externe/interne) : seul moyen barrière protégeant simultanément contre la majorité des IST.",
        "Tout diagnostic d'IST impose le bilan complet des autres IST (VIH, VHB, Syphilis) et le traitement simultané des partenaires."
      ),
      clinicalMessage = "Une IST en cache souvent une autre : prescrire un dépistage combiné + informer et traiter les partenaires !",
      definition = "Infections bactériennes, virales ou parasitaires transmises lors de rapports sexuels non protégés (Chlamydia, Neisseria gonorrhoeae, Treponema pallidum, Trichomonas, HSV).",
      riskFactors = listOf(
        "Rapports sexuels non protégés, multipartenariat, changement récent de partenaire.",
        "Jeunes adultes < 25 ans, antécédent récent d'IST, chemsex, absence de dépistage régulier."
      ),
      targetPopulation = "Jeunes femmes et hommes sexuellement actifs (< 25 ans : dépistage Chlamydia recommandé), personnes multipartenaires ou après prise de risque.",
      preventionMeasures = listOf(
        "Préservatifs + lubrifiants à base d'eau ; vaccination HPV et Hépatite B.",
        "Dépistage régulier après changement de partenaire ou prise de risque.",
        "Abstinence ou rapports protégés jusqu'à guérison complète du patient et du/des partenaire(s)."
      ),
      screeningRecommendations = "PCR Chlamydia/Gonocoque (premier jet urinaire chez l'homme, auto-prélèvement vaginal chez la femme) + sérologies Syphilis (TPHA/VDRL), VIH, VHB.",
      practicalAdvice = listOf(
        "Adopter une posture médicale bienveillante, confidentielle et strictement sans jugement lors de l'anamnèse sexuelle.",
        "Vérifier l'absence de signes d'atteinte pelvienne haute (salpingite) chez la femme."
      ),
      warningSigns = listOf(
        "Douleur pelvienne fébrile avec leucorrhées purulentes (salpingite — risque de stérilité)",
        "Écoulement urétral purulent ou brûlures mictionnelles aiguës",
        "Ulcération génitale/orale indolore indurée (chancre syphilitique) ou éruption palmo-plantaire"
      )
    ),

    // 14. VIH / SIDA
    PreventionTopic(
      id = "hiv",
      title = "VIH : Dépistage, TPE, PrEP & I=I",
      subtitle = "Prévention combinée et urgence de l'exposition (< 48h)",
      tag = "Infectiologie",
      keyPoints = listOf(
        "Un patient sous traitement antirétroviral avec charge virale indétectable ne transmet PAS le VIH (Indétectable = Intransmissible).",
        "Traitement Post-Exposition (TPE) : urgence à débuter idéalement dans les 4h et au maximum dans les 48h après un AES ou rapport à risque.",
        "Prophylaxie Pré-Exposition (PrEP) et dépistage sans ordonnance / CeGIDD."
      ),
      clinicalMessage = "Accident d'Exposition au Sang (AES) ou sexuel : consulter en urgence (< 4h, max 48h) pour évaluer l'indication d'un TPE.",
      definition = "Infection chronique par le Virus de l'Immunodéficience Humaine ciblant les lymphocytes T CD4+, évoluant sans traitement vers l'immunodépression sévère (SIDA).",
      riskFactors = listOf(
        "Rapports sexuels non protégés (anaux ou vaginaux), partage de matériel d'injection.",
        "Accident d'Exposition au Sang (piqûre creuse profonde), transmission mère-enfant en l'absence de traitement."
      ),
      targetPopulation = "Toute personne au moins une fois dans la vie (15-70 ans) ; dépistage annuel ou trimestriel chez les populations exposées ; bilan systématique en début de grossesse.",
      preventionMeasures = listOf(
        "Prévention diversifiée : préservatif, PrEP chez les personnes très exposées, traitement précoce universel (TasP : I=I).",
        "Chez les soignants : ne jamais recapuchonner les aiguilles usagées, conteneur OPCT à portée de main, port de gants."
      ),
      screeningRecommendations = "Sérologie VIH ELISA de 4e génération (Ag p24 + Ac anti-VIH1/2) fiable dès 6 semaines après la prise de risque (ou TROD dès 3 mois).",
      practicalAdvice = listOf(
        "Conduite immédiate en cas d'AES : ne pas faire saigner, laver eau + savon, rincer, antisepsie (Dakin ou alcool 70° pdt 5 min), déclarer et consulter dans les 4 heures.",
        "Penser au dépistage VIH devant toute pneumocystose, zona multi-métamérique, candidose œsophagienne ou tuberculose."
      ),
      warningSigns = listOf(
        "Syndrome mononucléosique fébrile avec rash cutané 2-4 semaines après prise de risque (primo-infection VIH)",
        "Fièvre prolongée, amaigrissement majeur, candidose buccale, dyspnée subaiguë (infections opportunistes)"
      )
    ),

    // 15. Hépatites virales (B et C)
    PreventionTopic(
      id = "viral_hepatitis",
      title = "Hépatites Virales B & C : Dépistage & Vaccination",
      subtitle = "Prévenir la cirrhose et le carcinome hépatocellulaire",
      tag = "Hépatologie",
      keyPoints = listOf(
        "Souvent silencieuses pendant des décennies avant d'évoluer vers cirrhose ou cancer du foie.",
        "Hépatite B : prévention quasi absolue par la vaccination ; dépistage par triade Ag HBs + Ac anti-HBs + Ac anti-HBc.",
        "Hépatite C : pas de vaccin, mais guérison dans > 95% des cas en 8-12 semaines par antiviraux à action directe."
      ),
      clinicalMessage = "Dépister VHB et VHC une fois chez tout adulte et systématiquement avant chimiothérapie/immunosuppression (réactivation VHB mortelle).",
      definition = "Infections virales chroniques du foie (VHB virus à ADN ; VHC virus à ARN) responsables d'une inflammation chronique conduisant à la fibrose puis à la cirrhose.",
      riskFactors = listOf(
        "Exposition sanguine (partage de seringues, tatouage/piercing non stérile, transfusion avant 1992, soins invasifs sans stérilisation).",
        "Transmission sexuelle et périnatale mère-enfant (surtout VHB)."
      ),
      targetPopulation = "Tout adulte (au moins 1 fois), femmes enceintes (Ag HBs obligatoire), soignants (vaccination VHB obligatoire), patients originaires de zones de forte endémie.",
      preventionMeasures = listOf(
        "Vaccination universelle contre l'Hépatite B + sérovaccination immédiate du nouveau-né de mère Ag HBs+ à la naissance.",
        "Matériel à usage unique (soins, tatouage, réduction des risques), rapports protégés."
      ),
      screeningRecommendations = "Sérologie VHB (Ag HBs, Ac anti-HBs, Ac anti-HBc) + Sérologie VHC (Ac anti-VHC, suivie d'ARN VHC si positive).",
      practicalAdvice = listOf(
        "Devant toute élévation même modérée des transaminases : prescrire sérologies VHB et VHC.",
        "Patient porteur chronique VHB/VHC : éviction complète de l'alcool et prudence avec les médicaments hépatotoxiques."
      ),
      warningSigns = listOf(
        "Ictère cutanéo-muqueux fébrile ou asthénie majeure avec cytolyse (hépatite aiguë)",
        "Ascite, hémorragie digestive (varices œsophagiennes), encéphalopathie hépatique (cirrhose décompensée)"
      )
    ),

    // 16. Tuberculose
    PreventionTopic(
      id = "tuberculosis",
      title = "Tuberculose : Repérage, Isolement & Dépistage",
      subtitle = "Toux > 3 semaines, isolement Air et enquête autour d'un cas",
      tag = "Pneumologie",
      keyPoints = listOf(
        "Toute toux chronique >= 3 semaines, surtout avec fièvre vespérale, sueurs nocturnes et amaigrissement, doit faire évoquer une tuberculose pulmonaire.",
        "Patient bacillifère suspect = isolement respiratoire 'AIR' immédiat (chambre individuelle porte fermée, masque FFP2 pour soignants, chirurgical pour patient).",
        "Diagnostic : Radiographie thoracique + 3 prélèvements de crachats (ECBC-BK : examen direct BAAR + culture + PCR)."
      ),
      clinicalMessage = "Toute suspicion de tuberculose pulmonaire impose un isolement AIR immédiat avant même les résultats des crachats !",
      definition = "Maladie infectieuse contagieuse due à Mycobacterium tuberculosis (bacille de Koch), transmise par voie aérienne (aérosols de toux), à tropisme pulmonaire prioritaire.",
      riskFactors = listOf(
        "Contage récent avec un patient bacillifère, séjour ou origine de zone de forte endémie.",
        "Précarité, promiscuité, immunodépression (VIH, anti-TNF alpha, corticoïdes au long cours), dénutrition, diabète, éthylisme."
      ),
      targetPopulation = "Sujets contacts d'un cas index, patients immunodéprimés (bilan pré-biothérapie par IGRA/IDR), migrants récents de zones endémiques, enfants exposés (BCG).",
      preventionMeasures = listOf(
        "Dépistage et traitement complet observé (6 mois) de toute tuberculose maladie pour casser la chaîne de transmission.",
        "Dépistage des sujets contacts (IDR ou test IGRA Quantiféron + radio thoracique) et traitement des tuberculoses latentes à risque.",
        "Vaccination BCG ciblée des nourrissons à risque élevé."
      ),
      screeningRecommendations = "Radio de thorax + recherche de BK dans les expectorations (ou tubages gastriques le matin à jeun) sur 3 jours consécutifs devant toute toux > 2-3 semaines.",
      practicalAdvice = listOf(
        "Ne jamais prescrire de fluoroquinolone en probabiliste sur une pneumopathie atypique traînante sans avoir éliminé la tuberculose (décapite le BK et induit des résistances).",
        "Déclaration obligatoire et vérification systématique du statut VIH."
      ),
      warningSigns = listOf(
        "Hémoptysie franche, détresse respiratoire",
        "Signes méningés subaigus (méningite tuberculeuse) ou paraplégie (mal de Pott rachidien)",
        "Miliaire tuberculeuse fébrile dyspnéisante"
      )
    ),

    // 17. Maladies respiratoires (Asthme, BPCO, Tabac)
    PreventionTopic(
      id = "respiratory_diseases",
      title = "Maladies Respiratoires Chroniques (Asthme, BPCO)",
      subtitle = "Sevrage tabagique, EFR, éducation inhalateurs et vaccinations",
      tag = "Pneumologie",
      keyPoints = listOf(
        "BPCO : 1ère cause = tabagisme (> 80%) ; diagnostic de certitude par spirométrie (VEMS/CVF < 0.70 après bronchodilatateur).",
        "Asthme : inflammation bronchique chronique réversible ; vérifier la technique d'inhalation à chaque consultation.",
        "Vaccinations indispensables : grippe annuelle, pneumocoque, Covid-19 et VRS chez les patients respiratoires chroniques."
      ),
      clinicalMessage = "La mesure de la fréquence respiratoire et la recherche d'un silence auscultatoire sont vitales devant toute crise d'asthme.",
      definition = "Pathologies obstructives chroniques des voies aériennes (Asthme inflammatoire variable ; BPCO chronique progressive liée au tabac ou polluants).",
      riskFactors = listOf(
        "Tabagisme actif et passif, expositions professionnelles (poussières, silice, biomasse, fumées domestiques).",
        "Terrain atopique et allergènes (acariens, pollens, poils d'animaux) pour l'asthme."
      ),
      targetPopulation = "Fumeurs >= 40 ans (dépistage spirométrique devant toux/expectoration ou dyspnée d'effort), enfants/adultes asthmatiques, travailleurs exposés.",
      preventionMeasures = listOf(
        "Sevrage tabagique total (seule mesure freinant le déclin accéléré du VEMS dans la BPCO).",
        "Éviction des allergènes identifiés, lutte contre l'humidité/moisissures domestiques, réhabilitation respiratoire.",
        "Utilisation d'une chambre d'inhalation ou vérification régulière du maniement des sprays."
      ),
      screeningRecommendations = "Spirométrie / EFR devant toute toux productive matinale chronique ('bronchite du fumeur'), sifflements ou dyspnée d'effort chez un sujet tabagique ou atopique.",
      practicalAdvice = listOf(
        "Chez un BPCO hypercapnique chronique : viser une SpO2 cible entre 88 et 92% sous oxygène (éviter l'hyperoxie dépresseur de la commande respiratoire).",
        "Ne jamais prescrire de bêtabloquants non cardiosélectifs chez un asthmatique."
      ),
      warningSigns = listOf(
        "Asthme aigu grave : impossibilité de parler ou tousser, FR >= 30/min, FC >= 120 bpm, silence auscultatoire, cyanose",
        "Exacerbation BPCO avec tirage, astérixis (flapping tremor d'hypercapnie) ou troubles de vigilance"
      )
    ),

    // 18. Malnutrition & Dénutrition
    PreventionTopic(
      id = "malnutrition",
      title = "Malnutrition & Dénutrition : Repérage & Soins",
      subtitle = "Perte de poids, IMC, albuminémie et renutrition progressive",
      tag = "Nutrition",
      keyPoints = listOf(
        "Critères diagnostiques de dénutrition adulte : perte de poids >= 5% en 1 mois ou >= 10% en 6 mois, ou IMC < 18.5 (< 22 si >= 70 ans), ou sarcopénie.",
        "Chez l'enfant (< 5 ans) : dépistage par périmètre brachial (PB < 115 mm = malnutrition aiguë sévère) et z-score Poids/Taille.",
        "Attention au syndrome de renutrition inappropriée (SRI) : réalimenter progressivement et suppléer en phosphore, potassium, magnésium et vitamine B1 !"
      ),
      clinicalMessage = "Peser et mesurer systématiquement chaque patient à l'admission permet de dépister une dénutrition souvent invisible.",
      definition = "État pathologique résultant d'apports nutritionnels insuffisants ou inadaptés aux besoins énergétiques et protéiques de l'organisme, augmentant mortalité et infections.",
      riskFactors = listOf(
        "Âge avancé, isolement social, troubles de déglutition, mauvaise denture, précarité.",
        "Pathologies hypercataboliques : cancers, sepsis, insuffisance d'organe, malabsorption, régimes restrictifs abusifs."
      ),
      targetPopulation = "Nourrissons/jeunes enfants (croissance), personnes âgées >= 70 ans, patients hospitalisés, oncologiques ou porteurs de maladies chroniques.",
      preventionMeasures = listOf(
        "Alimentation fractionnée, enrichie en protéines et énergie (produits laitiers, œufs, viande/poisson, légumineuses).",
        "Compléments nutritionnels oraux (CNO) pris en dehors des repas si ingesta insuffisants.",
        "Allaitement maternel exclusif jusqu'à 6 mois et diversification adaptée chez le nourrisson."
      ),
      screeningRecommendations = "Pesée à l'entrée + calcul d'IMC et pourcentage de perte de poids ; périmètre brachial (PB) et recherche d'œdèmes bilatéraux chez le jeune enfant.",
      practicalAdvice = listOf(
        "Chez l'adulte très dénutri : débuter l'apport calorique prudemment (10-15 kcal/kg/j au début) avec vitamine B1 pour prévenir le syndrome de renutrition inappropriée.",
        "Évaluer la courbe de poids des 6 derniers mois dès l'interrogatoire."
      ),
      warningSigns = listOf(
        "Œdèmes nutritionnels bilatéraux des pieds/membres inférieurs (kwashiorkor) ou fonte musculaire extrême (marasme)",
        "Hypoglycémie, hypothermie ou hypophosphorémie brutale à la reprise alimentaire",
        "Troubles de déglutition avec fausses routes répétées"
      )
    ),

    // 19. Obésité et surpoids
    PreventionTopic(
      id = "obesity",
      title = "Surpoids & Obésité : Prévention Métabolique",
      subtitle = "IMC, tour de taille, activité physique et approche bienveillante",
      tag = "Nutrition",
      keyPoints = listOf(
        "Surpoids = IMC 25-29.9 kg/m² ; Obésité = IMC >= 30 kg/m². Le tour de taille (>= 94 cm H / >= 80 cm F) évalue l'adiposité viscérale.",
        "Favorise DT2, HTA, syndrome d'apnées du sommeil (SAOS), stéatose hépatique (MASLD), arthrose et certains cancers.",
        "Prise en charge durable sans régimes draconiens : alimentation équilibrée, activité physique adaptée et accompagnement sans stigmatisation."
      ),
      clinicalMessage = "Adopter toujours une posture thérapeutique non culpabilisante centrée sur la santé globale plutôt que le chiffre seul.",
      definition = "Accumulation excessive ou anormale de masse grasse corporelle susceptible d'altérer la santé métabolique, mécanique et cardiovasculaire.",
      riskFactors = listOf(
        "Sédentarité, alimentation ultra-transformée hypercalorique et boissons sucrées, manque de sommeil.",
        "Facteurs génétiques, stress chronique, certains médicaments (corticoïdes, antipsychotiques, insuline)."
      ),
      targetPopulation = "Enfants/adolescents (rebond d'adiposité précoce), adultes avec IMC >= 25 ou tour de taille élevé, patients présentant syndrome métabolique.",
      preventionMeasures = listOf(
        "Limiter sucres ajoutés, aliments ultra-transformés et grignotage ; favoriser fibres et cuisine maison.",
        "Activité physique progressive régulière (>= 150-300 min/semaine) + réduction du temps assis.",
        "Dépistage et prise en charge des troubles du comportement alimentaire et du SAOS."
      ),
      screeningRecommendations = "Calcul annuel de l'IMC + mesure du tour de taille ; bilan métabolique (glycémie, bilan lipidique, ASAT/ALAT, PA) si obésité.",
      practicalAdvice = listOf(
        "Adapter la taille du brassard tensionnel à la circonférence du bras (un brassard trop petit surestime faussement la TA !).",
        "Rechercher un SAOS (ronflements, apnées décrites, somnolence diurne) devant toute obésité sévère."
      ),
      warningSigns = listOf(
        "Somnolence diurne sévère avec céphalées matinales et pauses respiratoires nocturnes (SAOS)",
        "HTA résistante, dyspnée majeure d'effort ou hypoventilation alvéolaire (syndrome obésité-hypoventilation)",
        "Douleurs articulaires invalidantes limitant toute mobilité"
      )
    ),

    // 20. Maladies rénales chroniques (MRC)
    PreventionTopic(
      id = "chronic_kidney_disease",
      title = "Maladies Rénales Chroniques : Néphroprotection",
      subtitle = "DFG, albuminurie, éviction des AINS et protection rénale",
      tag = "Néphrologie",
      keyPoints = listOf(
        "Principales causes : Diabète et Hypertension Artérielle (> 60% des cas), suivis des glomérulopathies et uropathies.",
        "Dépistage annuel indispensable chez tout diabétique, hypertendu ou sujet âgé : Créatininémie (DFG) + rapport albuminurie/créatininurie.",
        "Règle d'or de néphroprotection : proscrire les AINS, adapter les posologies médicamenteuses et prévenir l'IRA aux produits de contraste."
      ),
      clinicalMessage = "Pas d'automédication par AINS chez un sujet âgé, hypertendu, diabétique ou insuffisant rénal (risque d'IRA fatale) !",
      definition = "Altération persistante (> 3 mois) de la structure ou de la fonction rénale définie par un DFG < 60 mL/min/1.73m² et/ou des marqueurs d'atteinte rénale (albuminurie).",
      riskFactors = listOf(
        "Diabète, HTA, âge > 60 ans, maladies cardiovasculaires athéromateuses, obésité.",
        "Néphrotoxiques répétés (AINS, aminosides, lithium, plantes toxiques), infections urinaires hautes/obstacles urologiques, antécédents familiaux (polykystose)."
      ),
      targetPopulation = "Patients diabétiques, hypertendus, > 60 ans, insuffisants cardiaques, ou exposés régulièrement à des médicaments néphrotoxiques.",
      preventionMeasures = listOf(
        "Contrôle tensionnel strict (< 130/80 mmHg si protéinurie) privilégiant les bloqueurs du SRAA (IEC ou ARA2) et iSGLT2 selon indication.",
        "Équilibre glycémique, réduction des apports en sel, hydratation adaptée, arrêt du tabac.",
        "Éviction absolue des AINS en automédication et adaptation posologique systématique au DFG."
      ),
      screeningRecommendations = "Dépistage annuel chez les patients à risque : Créatininémie avec estimation du DFG (CKD-EPI) + Albuminurie sur échantillon (ratio A/C) + Bandelette urinaire.",
      practicalAdvice = listOf(
        "Toujours suspendre temporairement metformine, IEC/ARA2 et diurétiques lors d'une gastro-entérite aiguë fébrile avec déshydratation ('sick day rules').",
        "Demander une créatinine avant tout examen avec injection d'iode chez le sujet à risque."
      ),
      warningSigns = listOf(
        "Oligurie (< 400 mL/24h) ou anurie brutale (penser obstacle = échographie rénale urgente)",
        "Œdèmes diffus avec prise de poids rapide, dyspnée d'OAP par surcharge hydrosodée",
        "Signes ECG d'hyperkaliémie sévère ou acidose profonde"
      )
    ),

    // 21. Hygiène alimentaire
    PreventionTopic(
      id = "food_hygiene",
      title = "Hygiène Alimentaire & Toxi-Infections (TIAC)",
      subtitle = "Chaîne du froid, cuisson, conservation et prévention parasitaire",
      tag = "Santé Publique",
      keyPoints = listOf(
        "Respect strict des 5 clés OMS : propreté, séparation cru/cuit, cuisson à cœur, maintien aux bonnes températures (< 4°C ou > 63°C), eau sûre.",
        "Ne jamais recongeler un produit décongelé ; consommer rapidement les préparations à base d'œufs crus.",
        "Chez la femme enceinte séronégative pour la toxoplasmose et contre la Listéria : viandes bien cuites, fromages pasteurisés, lavage soigneux des crudités."
      ),
      clinicalMessage = "Devant des cas groupés de diarrhées/vomissements après un repas commun : penser à une TIAC et déclarer rapidement.",
      definition = "Ensemble des pratiques garantissant la salubrité et l'innocuité des aliments de la production à la consommation pour prévenir intoxications, gastro-entérites et parasitoses.",
      riskFactors = listOf(
        "Rupture de la chaîne du froid, contamination croisée planche/couteau cru vers cuit, cuisson insuffisante.",
        "Consommation d'eau non potable ou de coquillages/laits crus chez les sujets fragiles (femme enceinte, immunodéprimé, nourrisson, senior)."
      ),
      targetPopulation = "Population générale, restaurations collectives hospitalières, et tout particulièrement femmes enceintes, jeunes enfants et immunodéprimés.",
      preventionMeasures = listOf(
        "Lavage des mains avant toute manipulation alimentaire et après contact avec viandes crues/œufs.",
        "Planches et couteaux distincts pour aliments crus et prêts à consommer.",
        "Réfrigérateur maintenu entre 0°C et +4°C ; nettoyage régulier et respect des DLC."
      ),
      screeningRecommendations = "Enquête alimentaire minutieuse (horaire des repas des 72 dernières heures, convives malades) devant toute gastro-entérite aiguë collective.",
      practicalAdvice = listOf(
        "Chez l'enfant avec gastro-entérite aiguë : priorité absolue au Soluté de Réhydratation Orale (SRO) par petites gorgées répétées.",
        "Rappeler les interdits spécifiques de grossesse (Listéria, Toxoplasme)."
      ),
      warningSigns = listOf(
        "Signes de déshydratation aiguë : pli cutané persistant, yeux cernés, oligurie, hypotension, léthargie",
        "Diarrhée glairo-sanglante très fébrile (syndrome dysentérique bactérien)",
        "Troubles neurologiques après conserves maison (diplopie, dysphagie = botulisme !)"
      )
    ),

    // 22. Hygiène corporelle
    PreventionTopic(
      id = "body_hygiene",
      title = "Hygiène Corporelle & Prévention Cutanée",
      subtitle = "Soins quotidiens, plis, hygiène bucco-dentaire et escarres",
      tag = "Prévention",
      keyPoints = listOf(
        "L'hygiène corporelle quotidienne préserve la barrière cutanée (première défense contre Staphylococcus/Streptococcus).",
        "Soins bucco-dentaires (brossage biquotidien) : préviennent caries, endocardites bactériennes et pneumopathies d'inhalation.",
        "Chez le patient alité : effleurage, changements de position toutes les 2-3h et séchage doux des plis (prévention des escarres et mycoses)."
      ),
      clinicalMessage = "Une mauvaise hygiène bucco-dentaire est une porte d'entrée majeure de bactériémies et d'endocardites infectieuses.",
      definition = "Ensemble des soins d'entretien de la peau, des muqueuses, des phanères et de la cavité buccale assurant intégrité tégumentaire, confort et prévention des surinfections.",
      riskFactors = listOf(
        "Alitement prolongé, perte d'autonomie, macération (incontinence, obésité des plis), diabète.",
        "Utilisation de savons trop décapants altérant le film hydrolipidique ; partage de serviettes ou rasoirs."
      ),
      targetPopulation = "Toute personne ; vigilance maximale chez les nourrissons, patients diabétiques, patients alités/âgés, porteurs de valvulopathies cardiaques.",
      preventionMeasures = listOf(
        "Toilette quotidienne à l'eau tiède et syndet doux ; séchage minutieux des espaces interdigitaux et plis cutanés.",
        "Brossage dentaire biquotidien et contrôle dentaire annuel (essentiel chez les valvulaires et diabétiques).",
        "Effleurage et prévention d'escarres sur les points d'appui (sacrum, talons) chez le malade alité."
      ),
      screeningRecommendations = "Inspection cutanée systématique des points d'appui (rougeur ne blanchissant pas à la vitro-pression = escarre stade 1) et des pieds chez le patient hospitalisé ou diabétique.",
      practicalAdvice = listOf(
        "Ne jamais masser énergiquement une zone rouge suspecte d'escarre débutante : pratiquer la décharge d'appui immédiate.",
        "Éduquer les patients diabétiques à sécher soigneusement entre les orteils pour éviter les intertrigos portes d'entrée d'érysipèle."
      ),
      warningSigns = listOf(
        "Rougeur cutanée persistante ne disparaissant pas à la pression sur le sacrum ou le talon (escarre débutante)",
        "Grosse jambe rouge aiguë fébrile (érysipèle/dermohypodermite bactérienne)",
        "Abcès dentaire fébrile avec trismus ou œdème facial (cellulite cervico-faciale)"
      )
    ),

    // 23. Prévention des accidents domestiques & professionnels
    PreventionTopic(
      id = "accidents_prevention",
      title = "Prévention des Accidents Domestiques & Pros",
      subtitle = "Chutes du sujet âgé, brûlures, intoxications et sécurité AES",
      tag = "Sécurité & Santé",
      keyPoints = listOf(
        "Enfant (< 5 ans) : prévenir noyades (surveillance permanente), brûlures (poignées vers l'intérieur), ingestions de toxiques/piles boutons et suffocations.",
        "Sujet âgé : prévenir les chutes (retirer tapis glissants, éclairage nocturne, révision des psychotropes/hypotenseurs).",
        "Soignants/Pros : équipements de protection individuelle (EPI), conteneurs OPCT sécurisés, prévention TMS du dos."
      ),
      clinicalMessage = "Toute ingestion de pile bouton chez un enfant est une urgence endoscopique immédiate (risque de perforation œsophagienne en 2h) !",
      definition = "Actions visant à éviter les traumatismes involontaires à domicile (chutes, brûlures, noyades, intoxications) et en milieu professionnel ou hospitalier (AES, TMS, risques chimiques).",
      riskFactors = listOf(
        "Âges extrêmes (curiosité motrice du petit enfant ; troubles de l'équilibre, baisse visuelle et polymédication du senior).",
        "Stockage de produits ménagers en bouteilles alimentaires, absence de détecteur de fumée/CO, fatigue ou geste précipité au travail."
      ),
      targetPopulation = "Familles avec jeunes enfants, seniors fragiles à domicile ou en institution, et professionnels/étudiants exposés aux risques biologiques ou physiques.",
      preventionMeasures = listOf(
        "Enfant : médicaments/produits caustiques en hauteur fermés à clé, barrières de sécurité, détecteur de fumée, surveillance stricte au bain/piscine.",
        "Senior : ergonomie du logement (barres d'appui, tapis supprimés), chaussage fermé, réévaluation régulière de la polymédication (benzodiazépines).",
        "Milieu de soins : port de gants/lunettes selon geste, conteneur à aiguilles au plus près du soin, zéro recapuchonnage."
      ),
      screeningRecommendations = "Recherche d'hypotension orthostatique, test 'Get Up and Go' et revue d'ordonnance après toute chute chez la personne âgée ; déclaration immédiate de tout accident de travail / AES.",
      practicalAdvice = listOf(
        "En cas d'ingestion de produit caustique : NE JAMAIS faire vomir ni faire boire (risque de second passage brûlant l'œsophage).",
        "En cas de brûlure thermique récente : refroidissement immédiat à l'eau tempérée (15°C pendant 15 min) sans glace directe."
      ),
      warningSigns = listOf(
        "Ingestion de pile bouton ou produit caustique, inhalation de corps étranger (syndrome de pénétration)",
        "Céphalées + nausées + asthénie chez plusieurs membres d'un même foyer (intoxication au monoxyde de carbone CO !)",
        "Chute avec impotence fonctionnelle, rotation externe du membre inférieur (col du fémur) ou traumatisme crânien sous anticoagulant"
      )
    )
  )
}
