package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyDermatologyExpansion2 {
  val sheets: List<SemiologySheet> = listOf(
    SemiologySheet(
      id = "derm_alopecies_pelade",
      name = "Alopécies : Effluvium Télogène, Pelade, Androgénétique & Teignes",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Phanéres & Cuir chevelu",
      definition = "Chute ou raréfaction localisée ou diffuse des cheveux, distinguant fondamentalement les alopécies non cicatricielles (follicules conservés, repousse possible) des alopécies cicatricielles (destruction définitive des follicules).",
      physiopathology = "Interruption brutale du cycle pilaire (effluvium télogène), attaque auto-immune du bulbe (pelade), miniaturisation androgéno-dépendante, parasitose dermatophytique (teigne) ou inflammation chronique destructrice (lupus discoïde, lichen plan pilaire).",
      etiologies = listOf(
        "Diffuse non cicatricielle : Effluvium télogène (2–4 mois après stress, accouchement, fièvre, chirurgie), carence martiale, hypothyroïdie, carence en zinc, médicaments (chimiothérapie = anagène !)",
        "Circonscrite non cicatricielle : Pelade (plaques lisses ivoire, cheveux en point d'exclamation), Teigne tondante de l'enfant (plaque squameuse à cheveux cassés courts !), Trichotillomanie",
        "Cicatricielle (urgence diagnostique car perte définitive !) : Lupus érythémateux discoïde, Lichen plan pilaire, Folliculite décalvante"
      ),
      circumstances = "Chute abondante sur l'oreiller/brosse depuis quelques semaines ou apparition de plaques glabres bien limitées du cuir chevelu.",
      anamnesis = "Rechercher date de début, événement déclenchant 2–4 mois avant (fièvre, grossesse, choc), prise de médicaments, contact animalier/scolaire (teigne enfant), douleurs/prurit du cuir chevelu, signes d'hypothyroïdie ou hyperandrogénie.",
      searchMethod = "Examiner la peau du cuir chevelu (lisse vs squameuse vs cicatricielle atrophique sans orifices pilaires !), réaliser le TEST DE TRACTION (pull test : > 3 cheveux par pincée) et la dermoscopie (trichoscopie).",
      physicalExam = "1. EFFLUVIUM TÉLOGÈNE : raréfaction diffuse homogène sans plaque, test de traction franchement positif à bulbes blancs. 2. PELADE : plaque alopécique ronde parfaitement LISSE, couleur blanc ivoire, sans squame ni cicatrice, avec cheveux périphériques cassés en 'point d'exclamation'. 3. TEIGNE : enfant ++, plaque squameuse arrondie avec cheveux cassés grisâtres à quelques mm + parfois kérion suppuré très inflammatoire. 4. ALOPÉCIE CICATRICIELLE : peau lisse atrophique/érythémateuse sans aucun orifice folliculaire visible.",
      associatedSigns = listOf(
        "Ongles ponctués en dé à coudre ou trachyonychie (pelade)",
        "Signes d'hyperandrogénie chez la femme (hirsutisme, acné, troubles des règles -> SOPK ou tumeur)",
        "Adénopathie occipitale ou cervicale (teigne inflammatoire / kérion)"
      ),
      clinicalSignificance = "La disparition des orifices folliculaires définit l'alopécie cicatricielle qui impose une biopsie rapide pour préserver le capital pilaire restant ; toute plaque squameuse alopécique de l'enfant est une teigne jusqu'à preuve mycologique.",
      differentialDiagnoses = listOf(
        "Syphilis secondaire en plaques dites 'en clairière' (toujours penser à faire TPHA-VDRL devant une alopécie en plaques atypique de l'adulte !)",
        "Alopécie de traction mécanique (coiffures serrées fronto-temporales)",
        "Trichotillomanie (cheveux cassés de longueurs irrégulières)"
      ),
      complementaryExams = listOf(
        "Alopécie diffuse récente : NFS, Ferritine, TSH (+ bilan hormonal testostérone/DHEA-S si femme avec hyperandrogénie) + sérologie Syphilis (TPHA/VDRL) si alopécie en clairière !",
        "Plaque squameuse à cheveux cassés : Prélèvement MYCOLOGIQUE (examen direct + culture) + examen en lumière de Wood",
        "Suspicion d'alopécie cicatricielle : BIOPSIE du cuir chevelu en zone active"
      ),
      gravitySigns = listOf(
        "Disparition des orifices pilaires / croûtes purulentes (risque d'alopécie définitive)",
        "Pelade décalvante totale ou universelle",
        "Alopécie brutale avec signes systémiques (lupus systémique !)"
      ),
      pitfallsAndLimits = "1. Appliquer un dermocorticoïde sur une teigne faussement prise pour une pelade ou un psoriasis aggrave l'infection fongique en profondeur (kérion) ! 2. Ne jamais oublier la sérologie syphilitique devant une alopécie 'en clairière' mitée de l'adulte.",
      keyPoints = listOf(
        "Orifices pilaires disparus = Cicatricielle (irréversible -> biopsie urgente) vs Conservés = Non cicatricielle.",
        "Plaque parfaitement lisse blanc ivoire + point d'exclamation = Pelade.",
        "Enfant + plaque squameuse + cheveux cassés courts = Teigne -> prélèvement mycologique."
      )
    ),
    SemiologySheet(
      id = "derm_mycoses_dermatophyties_candidoses",
      name = "Mycoses Cutanéo-Muqueuses : Dermatophyties, Candidoses & Pityriasis",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Syndromes",
      definition = "Infections fongiques superficielles de la peau, des plis et des muqueuses opposant sémiologiquement les dermatophytes (toujours pathogènes, bordure centrifuge active) aux levures Candida (saprophytes digestives devenant opportunistes dans les plis humides).",
      physiopathology = "Dermatophytes (Trichophyton, Microsporum) : kératinophiles purs (jamais sur muqueuses !), contamination interhumaine, animale ou tellurique. Candida albicans : prolifération favorisée par macération, diabète, antibiotiques ou corticoïdes.",
      etiologies = listOf(
        "Dermatophytie peau glabre ('herpès circiné'), intertrigo des orteils (pied d'athlète : 4e espace), onychomycose",
        "Candida albicans : intertrigo des grands plis (inguinaux, sous-mammaires), perlèche, muguet buccal, vulvo-vaginite",
        "Malassezia furfur : Pityriasis versicolor (macules chamois ou hypopigmentées finement squameuses du haut du tronc)"
      ),
      circumstances = "Plaques rouges circulaires qui s'étendent vers l'extérieur, fissure macérée entre les petits orteils ou rougeur suintante douloureuse du pli sous-mammaire.",
      anamnesis = "Rechercher macération/chaussures de sport, animaux de compagnie (chaton/lapin pour dermatophyte !), diabète déséquilibré, antibiothérapie récente, corticothérapie inhalée sans rinçage buccal (muguet).",
      searchMethod = "Inspection de TOUS les plis (grands plis ET 4e-5e espaces interorteils !), ongles et muqueuse buccale (grattage doux à l'abaisse-langue pour vérifier si l'enduit blanchâtre se détache = Candida !).",
      physicalExam = "1. DERMATOPHYTIE PEAU GLABRE : placard annulaire érythémato-squameux à BORDURE SURÉLEVÉE ACTIVE VÉSICULEUSE ET CENTRIFUGE avec GUÉRISON CENTRALE. 2. INTERTRIGO CANDIDIQUE : érythème vernissé suintant du FOND DU PLI avec fissure centrale, enduit blanchâtre crémeux, BORDURE EN COLLERETTE DESQUAMATIVE et PUSTULES SATELLITES EN PEAU SAINE autour ! 3. PITYRIASIS VERSICOLOR : macules à bords nets couleur chamois (peau claire) ou achromiques (peau bronzée/foncée) sur tronc/cou avec 'signe du copeau' au grattage doux à la curette.",
      associatedSigns = listOf(
        "Muguet buccal : dépôts blanchâtres en 'lait caillé' se détachant au raclage (contrairement au lichen plan ou leucoplasie fixe !)",
        "Porte d'entrée pour érysipèle de jambe (intertrigo interorteils)"
      ),
      clinicalSignificance = "Chaque intertrigo du pied doit être dépisté et traité car c'est la 1re porte d'entrée de l'érysipèle ; toute candidose buccale/œsophagienne persistante sans corticoïde inhalé fait rechercher VIH ou diabète.",
      differentialDiagnoses = listOf(
        "Psoriasis inversé des plis (rouge vif sec vernissé bien limité SANS pustule satellite ni collerette ni fissure suintante !)",
        "Érythrasma (Corynebacterium : plaque brunâtre du pli inguinal fluorescent rouge corail en lumière de Wood !)",
        "Eczéma nummulaire (bordures mal limitées sans guérison centrale)"
      ),
      complementaryExams = listOf(
        "Prélèvement MYCOLOGIQUE (grattage des squames en bordure active ou sous-unguéal) AVANT tout traitement antifongique ou corticoïde pour les onychomycoses, teignes et lésions douteuses !",
        "Glycémie à jeun (HbA1c) et sérologie VIH si candidoses récidivantes/profuses sans cause évidente"
      ),
      gravitySigns = listOf(
        "Candidose œsophagienne avec dysphagie/odynophagie (immunodépression sévère / VIH !)",
        "Tinea incognito étendue après application prolongée de dermocorticoïdes"
      ),
      pitfallsAndLimits = "1. NE JAMAIS traiter une onychomycose sans prélèvement mycologique préalable (50% des ongles dystrophiques sont du psoriasis ou des microtraumatismes, pas des mycoses !). 2. Ne jamais prescrire de dermocorticoïde sur une lésion suspecte de mycose.",
      keyPoints = listOf(
        "Dermatophytie = anneau à bordure active centrifuge et centre guéri (jamais les muqueuses !).",
        "Candidose des plis = atteinte du fond du pli + collerette squameuse + pustules satellites.",
        "Toujours prélever en mycologie avant un traitement long d'onychomycose."
      )
    ),
    SemiologySheet(
      id = "derm_impetigo_furoncle_folliculite",
      name = "Infections Cutanées Bactériennes : Impétigo, Folliculite, Furoncle & Anthrax",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Syndromes",
      definition = "Infections bactériennes superficielles ou annexielles pilo-sébacées principalement dues à Staphylococcus aureus et Streptococcus pyogenes.",
      physiopathology = "Impétigo : infection épidermique très contagieuse (toxine exfoliative ou effraction mineure). Folliculite / Furoncle : infection profonde et nécrotique du follicule pilo-sébacé par S. aureus (parfois souche sécrétrice de leucocidine de Panton-Valentine = furonculose récidivante !).",
      etiologies = listOf(
        "Impétigo : S. aureus (souvent forme bulleuse néonatale/enfant) et Streptocoque A",
        "Folliculite / Furoncle / Anthrax : Staphylococcus aureus (portage nasal chronique fréquent !)"
      ),
      circumstances = "Croûtes jaunâtres autour de la bouche chez un enfant d'école primaire OU nodule rouge chaud très douloureux sur la fesse/nuque avec bourbillon central.",
      anamnesis = "Rechercher contage scolaire/familial (impétigo très contagieux !), manipulation/trituration d'un furoncle facial (danger !), récidives (furonculose -> portage nasal / PVL ?), diabète, immunodépression.",
      searchMethod = "Inspection de l'aspect des croûtes, localisation par rapport aux poils et recherche de la ZONE DANGER CENTRO-FACIALE ('triangle naso-labial').",
      physicalExam = "1. IMPÉTIGO : vésiculo-pustules fragiles laissant rapidement place à des CROÛTES MÉLICÉRIQUES JAUNÂTRES ('couleur de miel') entourées d'un halo érythémateux, péri-orificielles (bouche, narines) ou sur lésions préexistantes (impétiginisation d'eczéma/gale) ; chez l'enfant < 2 ans : forme bulleuse avec bulles flasques > 1 cm. 2. FOLLICULITE : petite pustule centrée par un poil. 3. FURONCLE : nodule érythémateux chaud douloureux centré par un poil évoluant vers une nécrose centrale jaune (BOURBILLON) qui s'élimine en laissant un cratère. 4. ANTHRAX : agglomérat de plusieurs furoncles formant un placard induré polyfistulisé très douloureux fébrile (nuque, dos).",
      associatedSigns = listOf(
        "Adénopathie satellite sensible sans fièvre élevée dans les formes simples",
        "Fièvre, placard rouge extensif ou œdème palpébral si complication"
      ),
      clinicalSignificance = "Un furoncle isolé simple guérit par soins locaux antiseptiques et chaleur SANS antibiotiques ni incision traumatisante ; la manipulation d'un furoncle de la lèvre supérieure ou de l'aile du nez expose à la staphylococcie maligne de la face !",
      differentialDiagnoses = listOf(
        "Herpès labial surinfecté (interroger le début par vésicules en bouquet récidivantes)",
        "Hidradénite suppurée / maladie de Verneuil (nodules douloureux récidivants des aisselles/aines avec fistules/cicatrices SANS bourbillon folliculaire typique !)",
        "Ecthyma (impétigo creusant ulcéré des membres inférieurs)"
      ),
      complementaryExams = listOf(
        "Formes simples : DIAGNOSTIC CLINIQUE sans prélèvement !",
        "Furonculose récidivante ou anthrax / furoncle compliqué : prélèvement bactériologique du pus (antibiogramme + recherche PVL) + prélèvement des gîtes de portage (narines, périnée) et glycémie à jeun"
      ),
      gravitySigns = listOf(
        "STAPHYLOCOCCIE MALIGNE DE LA FACE (placard œdémateux rouge violacé fébrile de la lèvre supérieure/aile du nez -> thrombophlébite du sinus caverneux !)",
        "Impétigo étendu (> 2% surface corporelle ou > 6 lésions) ou néonatal bulleux",
        "Furoncle compliqué d'érysipèle/abcès ou chez immunodéprimé/valvulopathe"
      ),
      pitfallsAndLimits = "1. NE JAMAIS PRESSSER OU TRITURER un furoncle du visage (risque de diffusion veineuse vers le sinus caverneux !). 2. Tout impétigo survenant chez un enfant impose l'éviction scolaire 72h après début du traitement si lésions non couvrables.",
      keyPoints = listOf(
        "Impétigo = croûtes mélicériques 'couleur miel' péri-orificielles très contagieuses (S. aureus / Strepto A).",
        "Furoncle = nodule centré par un poil + bourbillon nécrotique -> JAMAIS presser sur le visage (risque thrombophlébite sinus caverneux) !",
        "Anthrax (agglomérat de furoncles) ou furoncle compliqué = antibiothérapie antistaphylococcique."
      )
    ),
    SemiologySheet(
      id = "derm_erythrodermie",
      name = "Érythrodermie (> 90% Surface Corporelle) : Orientation Étiologique & Urgence",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Syndromes",
      definition = "Syndrome dermatologique GRAVE défini par un érythème généralisé touchant plus de 90% de la surface corporelle, associé à une desquamation d'importance variable et évoluant de façon prolongée.",
      physiopathology = "Vasodilatation cutanée massive et rupture de la barrière épidermique entraînant déperdition thermique (hypothermie ou fièvre), pertes hydro-électrolytiques et protéiques majeures, hypercatabolisme et haut risque infectieux.",
      etiologies = listOf(
        "Les 4 grandes causes de l'adulte ('PEPT' / Psoriasis-Eczéma-Lymphome-Toxidermie) : 1. PSORIASIS généralisé (souvent après arrêt brutal de corticoïdes généraux !) ; 2. ECZÉMA / Dermatite atopique ; 3. TOXIDERMIE MÉDICAMENTEUSE (DRESS ++) ; 4. LYMPHOME CUTANÉ T épidermotrope / Syndrome de SÉZARY !",
        "Causes plus rares : Gale norvégienne hyperkératosique, pemphigus foliacé, pityriasis rubra pilaire"
      ),
      circumstances = "Patient devenu 'rouge de la tête aux pieds' depuis quelques jours ou semaines avec frissons, squames dans les draps et prurit féroce.",
      anamnesis = "Rechercher antécédent connu de psoriasis ou d'eczéma, prise de corticoïdes oraux récents (arrêt ?), TOUS nouveaux médicaments des 2 derniers mois (allopurinol, antiépileptiques = DRESS !), altération de l'état général.",
      searchMethod = "Estimer la surface (> 90%), palper TOUTES les aires ganglionnaires, prendre température et constantes hémodynamiques, vérifier les muqueuses et rechercher une kératodermie palmo-plantaire ou un ectropion.",
      physicalExam = "Érythème rouge vif ou rouge sombre violacé recouvrant > 90% du tégument (parfois îlots de peau saine), desquamation fine ou en larges lambeaux, œdème des membres inférieurs et du visage (DRESS !), ECTROPION des paupières, polyadénopathies volumineuses fermes ('dermatopathiques' ou syndrome de Sézary), ongles épaissis brillants usés par le grattage, parfois alopécie.",
      associatedSigns = listOf(
        "Hypothermie paradoxale ou fièvre élevée (dérégulation thermique !)",
        "Frissons permanents par vasodilatation cutanée",
        "Polyadénopathies et hépatosplénomégalie"
      ),
      clinicalSignificance = "Toute érythrodermie est une URGENCE DERMATOLOGIQUE HOSPITALIÈRE menaçant le pronostic vital par choc hypovolémique, défaillance cardiaque à haut débit, sepsis ou dénutrition.",
      differentialDiagnoses = listOf(
        "Exanthème scarlatiniforme ou syndrome de choc toxinique aigu (< 90% ou évolution aiguë en heures/jours avec hypotension)",
        "Nécrolyse épidermique toxique (Lyell) si décollement bulleux massif + érosions muqueuses + Nikolsky positif"
      ),
      complementaryExams = listOf(
        "HOSPITALISATION SYSTÉMATIQUE : NFS + FROTTIS SANGUIN à la recherche de CELLULES DE SÉZARY (lymphocytes à noyau cérébriforme) et d'hyperéosinophilie (DRESS !)",
        "Ionogramme, créatinine, albumine (hypoalbuminémie fréquente), bilan hépatique, CRP, hémocultures si fièvre/hypothermie",
        "BIOPSIES CUTANÉES MULTIPLES (2 ou 3 sites pour confirmer psoriasis, eczéma, DRESS ou lymphome T cutané) + immunophénotypage lymphocytaire sanguin"
      ),
      gravitySigns = listOf(
        "Sepsis / bactériémie à point de départ cutané (Staphylococcus aureus ++)",
        "Insuffisance cardiaque à haut débit ou collapsus hypovolémique",
        "Hypothermie sévère (< 35.5°C) masquant un sepsis !"
      ),
      pitfallsAndLimits = "1. Prescrire aveuglément une corticothérapie orale face à une érythrodermie sans connaître la cause est dangereux (peut faire flamber une gale norvégienne ou déclencher un rebond fatal si psoriasis !). 2. Toujours rechercher une cellule de Sézary sur frottis devant toute érythrodermie chronique prurigineuse avec polyadénopathies.",
      keyPoints = listOf(
        "Érythrodermie = > 90% de surface cutanée rouge + desquamation -> HOSPITALISATION OBLIGATOIRE.",
        "Les 4 étiologies majeures : Psoriasis, Eczéma, Toxidermie (DRESS), Lymphome T (Sézary).",
        "Toujours demander Frottis sanguin (cellules de Sézary, éosinophiles), Bilan hydro-électrolytique/albuminémie et Biopsies cutanées."
      )
    ),
    SemiologySheet(
      id = "derm_escarre_prevention_stades",
      name = "Escarre Cutanée : Évaluation Clinique des Stades (I à IV) & Zones d'Appui",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Lésions élémentaires",
      definition = "Lésion ischémique localisée de la peau et/ou des tissus sous-jacents située sur une proéminence osseuse, causée par une pression prolongée ou associée à un cisaillement.",
      physiopathology = "Compression prolongée des vaisseaux entre une surface dure (matelas/fauteuil) et une saillie osseuse dépassant la pression capillaire (> 32 mmHg), aggravée par l'immobilité, la dénutrition, l'incontinence et l'artériopathie.",
      etiologies = listOf(
        "Alitement prolongé (réanimation, post-opératoire fracture du col fémoral, AVC, gériatrie)",
        "Facteurs favorisants majeurs : dénutrition (hypoalbuminémie), incontinence urinaire/fécale, neuropathie sensitive (diabète, paraplégie), hypoperfusion/hypotension"
      ),
      circumstances = "Surveillance quotidienne des points d'appui chez tout patient alité ou à mobilité réduite.",
      anamnesis = "Durée d'immobilisation (parfois quelques heures au sol après chute à domicile !), état nutritionnel, sensation douloureuse locale (absente si neuropathie/paraplégie), score de risque (Braden ou Norton).",
      searchMethod = "Inspection quotidienne systématique de TOUS LES POINTS D'APPUI en décubitus dorsal et latéral : SACRUM, TALONS, trochanters, ischions (fauteuil !), malléoles, occiput, omoplates ; réaliser impérativement le TEST À LA VITROPRESSION (pression digitale sur une rougeur pour voir si elle blanchit !).",
      physicalExam = "Classification internationale (NPUAP/EPUAP) : STADE I = ÉRYTHÈME PERSISTANT NE BLANCHISSANT PAS À LA VITROPRESSION sur peau intacte (signal d'alarme réversible !) ; STADE II = Perte partielle de l'épaisseur cutanée : PHLYCTÈNE (cloque séreuse ou hématique) ou désépidermisation superficielle ; STADE III = Perte complète du derme et hypoderme : ULCÈRE PROFOND exposant la graisse sous-cutanée (parfois nécrose noire ou fibrine jaune) SANS atteinte osseuse/tendineuse ; STADE IV = NÉCROSE PROFONDE exposant OS, TENDON ou MUSCLE avec parfois décollements/tunnels sous-cutanés.",
      associatedSigns = listOf(
        "Escarre inclassable si plaque de nécrose noire sèche couvrant totalement le fond (impossible de voir la profondeur avant détersion !)",
        "Signes d'infection profonde : pus malodorant, érythème extensif périphérique, contact osseux, fièvre"
      ),
      clinicalSignificance = "Le STADE I (rougeur non blanchissante à la vitropression) est la dernière étape entièrement réversible par DÉCHARGE TOTALE IMMÉDIATE de la zone ; un retard de quelques heures fait basculer vers la nécrose profonde.",
      differentialDiagnoses = listOf(
        "Érythème réversible simple d'appui (blanchit à la pression du doigt -> prévention renforcée !)",
        "Dermite associée à l'incontinence (rougeur diffuse des plis fessiers sans correspondre à une saillie osseuse !)",
        "Ulcère artériel ou angiodermite nécrotique"
      ),
      complementaryExams = listOf(
        "AUCUN prélèvement bactériologique systématique par écouvillon sur une escarre non infectée (toute escarre ouverte est colonisée par la flore !)",
        "Bilan nutritionnel biologique (albumine, préalbumine, CRP) pour corriger la dénutrition",
        "Si suspicion d'ostéite sur escarre stade IV fébrile : biopsie osseuse après nettoyage + hémocultures + IRM pelvienne/calcanéenne"
      ),
      gravitySigns = listOf(
        "Stade IV avec exposition osseuse et ostéite / arthrite septique (hanche, sacrum)",
        "Bactériémie à point de départ d'escarre infectée chez le sujet âgé",
        "Nécrose talonnière chez patient artéritique (NE JAMAIS déterger mécaniquement une escarre sèche talonnière d'artéritique avant revascularisation !)"
      ),
      pitfallsAndLimits = "1. RÈGLE D'OR DU TALON SEC : ne jamais débrider ou ramollir une plaque de nécrose noire sèche et stable du talon chez un patient en ischémie artérielle sévère (risque de surinfection et d'amputation !). 2. Masser vigoureusement une rougeur stade I aggrave les lésions microvasculaires : décharge stricte uniquement !",
      keyPoints = listOf(
        "Stade I = érythème qui NE BLANCHIT PAS à la pression du doigt -> DÉCHARGE IMMÉDIATE (jamais de massage !).",
        "Stade II = phlyctène/érosion ; Stade III = hypoderme sans os ; Stade IV = os/tendon/muscle exposé.",
        "Sites prioritaires : Sacrum et Talons (alitement), Ischions (fauteuil) ; pas de prélèvement de surface systématique."
      )
    ),
    SemiologySheet(
      id = "derm_lupus_dermatomyosite_connectivites",
      name = "Signes Cutanés des Connectivites : Lupus Érythémateux & Dermatomyosite",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Syndromes",
      definition = "Manifestations dermatologiques spécifiques permettant d'évoquer et parfois de diagnostiquer précocement une maladie auto-immune systémique (lupus érythémateux systémique, dermatomyosite, sclérodermie).",
      physiopathology = "Dépôts de complexes immuns à la jonction dermo-épidermique (lupus) et microangiopathie complément-médiée (dermatomyosite), souvent fortement exacerbés par les rayonnements UV (photosensibilité).",
      etiologies = listOf(
        "Lupus Érythémateux (aigu en loup / subaigu annulaire / chronique discoïde)",
        "Dermatomyosite (souvent paranéoplasique chez l'adulte > 40–50 ans -> bilan néoplasique impératif !)",
        "Sclérodermie systémique (sclérodactylie, ulcérations pulpaires, télangiectasies)"
      ),
      circumstances = "Éruption rouge des joues déclenchée par le soleil chez une femme jeune avec arthralgies OU paupières violacées associées à une faiblesse des cuisses et des épaules.",
      anamnesis = "Rechercher PHOTOSENSIBILITÉ marquée après exposition solaire minime, phénomène de Raynaud, arthralgies inflammatoires, fatigue/faiblesse musculaire pour monter les escaliers ou se coiffer, perte de poids.",
      searchMethod = "Inspection du visage (en notant le respect des sillons naso-géniens dans le lupus vs atteinte des paupières dans la dermatomyosite !), du dos des mains (articulations vs phalanges !) et du lit unguéal (capillaroscopie/signe de la manucure).",
      physicalExam = "1. LUPUS AIGU : ÉRYTHÈME MALAIRE EN AILES DE PAPILONS ('en vespertilio') sur joues et racine du nez ÉPARGNANT LES SILLONS NASO-GÉNIENS ; LUPUS DISCOÏDE : plaques érythémato-squameuses atrophiques bien limitées avec alopécie cicatricielle définitive du cuir chevelu/conque de l'oreille. 2. DERMATOMYOSITE : ÉRYTHÈME HÉLIOTROPE LILAS DES PAUPIÈRES SUPÉRIEURES (œdème violacé 'en lunettes') + PAPULES DE GOTTRON (papules violacées squameuses sur le DOS DES ARTICULATIONS MCP ET IPP — alors que le lupus épargne les articulations et siège entre les jointures !) + érythème péri-unguéal douloureux ('signe de la manucure').",
      associatedSigns = listOf(
        "Déficit moteur proximal symétrique des ceintures + myalgies + dysphagie (dermatomyosite)",
        "Phénomène de Raynaud triphasique, ulcérations buccales indolores du palais (lupus !)",
        "Alopécie non cicatricielle diffuse lors des poussées lupiques"
      ),
      clinicalSignificance = "La constatation d'un érythème liliacé des paupières + papules de Gottron signe la dermatomyosite avant même le dosage des CPK ; chez l'adulte > 40 ans, la dermatomyosite impose une recherche active de cancer occulte (sein, ovaire, poumon, côlon).",
      differentialDiagnoses = listOf(
        "Rosacée (télangiectasies et papulo-pustules sans photosensibilité vraie ni arthralgies ni signes biologiques)",
        "Dermite de contact des paupières (prurigineuse et eczématiforme, sans déficit musculaire)",
        "Psoriasis des coudes/genoux ou dermatite séborrhéique (touche LES sillons naso-géniens, contrairement au lupus !)"
      ),
      complementaryExams = listOf(
        "Lupus suspect : AAN (anticorps antinucléaires), anti-ADN natif, anti-Sm, anti-SSA/SSB, complément (C3, C4), NFS, créatinine + bandelette urinaire / protéinurie (rechercher glomérulonéphrite lupique !)",
        "Dermatomyosite suspecte : CPK (très élevées !), aldolase, EMG, IRM musculaire + biopsie musculaire/cutanée + bilan néoplasique complet chez adulte (TDM TAP, mammographie, coloscopie...)"
      ),
      gravitySigns = listOf(
        "Atteinte rénale (protéinurie/hématurie = néphropathie lupique), péricardite ou neurolupus",
        "Dysphagie (fausse route) ou pneumopathie interstitielle diffuse (anti-MDA5 / anti-synthétases) dans la dermatomyosite"
      ),
      pitfallsAndLimits = "1. Répéter l'astuce sémiologique des mains : LUPUS = rougeur ENTRE les articulations (dos des phalanges) vs DERMATOMYOSITE = Papules de Gottron SUR les bosses articulaires MCP/IPP ! 2. Au visage : DERMITE SÉBORRHÉIQUE touche les sillons nasogéniens, LUPUS les respecte !",
      keyPoints = listOf(
        "Lupus aigu = rash malaire en ailes de papillon respectant les sillons naso-géniens + photosensibilité + arthralgies (toujours faire protéinurie !).",
        "Dermatomyosite = paupières liliacées (héliotrope) + papules de Gottron sur MCP/IPP + déficit proximal -> doser CPK et chercher un cancer chez l'adulte.",
        "Sur le dos des mains : Gottron = SUR les articulations (DM) ; Lupus = ENTRE les articulations."
      )
    ),
    SemiologySheet(
      id = "derm_ist_ulcerations_genitales",
      name = "Ulcérations Génitales & IST : Syphilis Primaire, Herpès vs Chancre Mou",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Syndromes",
      definition = "Érosions ou ulcérations des muqueuses ou de la peau génitale acquises principalement par transmission sexuelle, dont le caractère douloureux/induré et le nombre orientent vers l'étiologie.",
      physiopathology = "Inoculation muqueuse directe de Treponema pallidum (chancre syphilitique : vascularite oblitérante indolore), HSV-2/1 (nécrose épithéliale vésiculeuse douloureuse récidivante) ou Haemophilus ducreyi.",
      etiologies = listOf(
        "Herpès génital (HSV-2 ou HSV-1 : 1re cause en Europe)",
        "Syphilis primaire (Treponema pallidum : grande simulatrice en forte recrudescence !)",
        "Chancre mou (Haemophilus ducreyi) et Donovanose / Lymphogranulomatose vénérienne (zones tropicales / HSH)",
        "Cause non infectieuse à ne pas méconnaître : Aphtose génitale / Maladie de Behçet, Érythème pigmenté fixe médicamenteux !"
      ),
      circumstances = "Apparition d'une plaie ou de brûlures génitales jours ou semaines après un rapport sexuel non protégé.",
      anamnesis = "Délai par rapport au dernier rapport suspect (herpès 2–7 jours ; syphilis 3 semaines en moyenne = 10–90 jours !), épisodes antérieurs similaires au même endroit (herpès récurrent ou érythème pigmenté fixe !), prise médicamenteuse, aphtes buccaux associés (Behçet !).",
      searchMethod = "Examiner les organes génitaux avec gants (nombre de lésions, aspect des bords, propreté du fond, SENSIBILITÉ ET INDURATION DE LA BASE !) et palper les aires ganglionnaires INGUINALES bilatérales.",
      physicalExam = "1. CHANCRE SYPHILITIQUE : Classiquement UNIQUE, arrondi, propre couleur chair rouge vif, à bords nets, STRICTEMENT INDOLORE, reposant sur une BASE INDURÉE ('carte de visite' cartonnée à la palpation gantée !), accompagné d'une adénopathie inguinale froide indolore mobile ('préfet de l'aine' + ganglions satellites). 2. HERPÈS GÉNITAL : ÉROSIONS MULTIPLES SUPERFICIELLES POLYCYCLIQUES confluentes très DOULOUREUSES, précédées de vésicules en bouquet, base souple, adénopathies inguinales sensibles (en primo-infection : fièvre et dysurie intense !). 3. CHANCRE MOU : ulcération creusante sale purulente très douloureuse, base MOLLE, associée à un gros ganglion inguinal inflammatoire fluctuant (bubon).",
      associatedSigns = listOf(
        "Éruption maculeuse rosée du tronc puis papules cuivrées palmo-plantaires (syphilis secondaire 6 sem après = 'collerette de Biett' !)",
        "Aphtes buccaux bipolaires récidivants + uvéite (maladie de Behçet)"
      ),
      clinicalSignificance = "La présentation clinique du chancre syphilitique peut être trompeuse (ulcérations multiples ou surinfectées douloureuses dans 30% des cas) : TOUTE ulcération génitale impose systématiquement un bilan IST complet dont SÉROLOGIE SYPHILITIQUE ET VIH !",
      differentialDiagnoses = listOf(
        "Maladie de Behçet (aphtose bipolaire buccale + génitale douloureuse laissant des cicatrices)",
        "Érythème pigmenté fixe bulleux médicamenteux (récidive pile au même site après prise d'AINS, paracétamol ou antibiotique !)",
        "Carcinome épidermoïde génital si ulcération chronique indurée > 1 mois chez sujet âgé"
      ),
      complementaryExams = listOf(
        "Devant TOUTE ulcération génitale : SÉROLOGIE SYPHILIS (TPHA/VDRL ou test tréponémique + RPR, à recontrôler à J15 si négative précocement !), SÉROLOGIE VIH, VHB/VHC",
        "Prélèvement local par écouvillon sur la lésion pour PCR multiplex IST (HSV-1/2, Treponema pallidum, Chlamydia LGV, H. ducreyi)"
      ),
      gravitySigns = listOf(
        "Rétention aiguë d'urines réflexe sur primo-infection herpétique féminine hyperalgique",
        "Syphilis méconnue non traitée évoluant vers la neurosyphilis ou transmission materno-fœtale (grossesse !)"
      ),
      pitfallsAndLimits = "1. Ne JAMAIS exclure une syphilis sur la simple positivité ou négativité de la douleur (les chancres atypiques existent !) ni sur une sérologie trop précoce (la sérologie peut être encore négative pendant les 5 à 10 premiers jours du chancre !). 2. Toujours dépister et traiter le/les partenaires sexuels.",
      keyPoints = listOf(
        "Chancre syphilitique typique = Unique + Propre + Indolore + Base INDURÉE + Adénopathie froide indolore.",
        "Herpès génital = Vésicules en bouquet -> érosions polycycliques multiples très DOULOUREUSES + base molle.",
        "Toute ulcération génitale = PCR locale + Sérologie Syphilis (répétée si trop précoce) + VIH + traitement des partenaires."
      )
    ),
    SemiologySheet(
      id = "derm_dermite_seborrheique_pityriasis_rose",
      name = "Dermite Séborrhéique & Pityriasis Rosé de Gibert",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Syndromes",
      definition = "Deux dermatoses érythémato-squameuses bénignes très fréquentes de l'adulte jeune à différencier du psoriasis et de la syphilis secondaire.",
      physiopathology = "Dermite séborrhéique : réaction inflammatoire chronique à la levure saprophyte Malassezia dans les zones riches en glandes sébacées. Pityriasis rosé de Gibert : éruption aiguë bénigne spontanément résolutive en 4–8 semaines (réactivation HHV-6/HHV-7 probable).",
      etiologies = listOf(
        "Dermite séborrhéique : terrain séborrhéique + stress, fatigue ; formes sévères/profuses devant faire évoquer VIH ou maladie de Parkinson (hyperséborrhée !)",
        "Pityriasis rosé de Gibert : éruption para-virale bénigne de l'adolescent/adulte jeune (10–35 ans)"
      ),
      circumstances = "Pellicules grasses et rougeurs autour du nez et des sourcils (dermite séborrhéique) OU éruption rosée en 'arbre de Noël' sur le tronc apparue après une première plaque isolée (Gibert).",
      anamnesis = "Rechercher poussées déclenchées par le stress, amélioration au soleil (dermite séborrhéique), médaille inaugurale 1–2 semaines avant l'éruption diffuse (Gibert), rapports à risque (écarter syphilis secondaire !).",
      searchMethod = "Examiner les zones séborrhéiques du visage (sillons nasogéniens, sourcils, lisière du cuir chevelu, pré-sternal), le tronc (orientation des taches selon les plis cutanés) ET IMPÉRATIVEMENT LES PAUMES, PLANTES ET MUQUEUSES !",
      physicalExam = "1. DERMITE SÉBORRHÉIQUE : plaques érythémateuses recouvertes de SQUAMES GRASSES JAUNÂTRES non adhérentes siégeant aux SILLONS NASO-GÉNIENS, inter-sourcilier, lisière du cuir chevelu ('pellicules') et région médio-thoracique ; prurit modéré ou nul. 2. PITYRIASIS ROSÉ DE GIBERT : débute par une 'PLAQUE MÈRE' (médaillon unique arrondi de 2–5 cm à bordure finement squameuse vers l'intérieur), suivi 7–14 jours plus tard de multiples médaillons plus petits et macules ovalaires rosées alignés selon les lignes de tension du dos en 'ARBRE DE NOËL', RESPECTANT LE VISAGE, LES PAUMES ET LES PLANTES !",
      associatedSigns = listOf(
        "Respect strict des paumes, plantes et muqueuses dans le pityriasis rosé de Gibert (capital !)",
        "Croûtes de lait jaunâtres du cuir chevelu et siège du nourrisson (dermite séborrhéique infantile)"
      ),
      clinicalSignificance = "Tout tableau ressemblant à un pityriasis rosé de Gibert OU à une dermite séborrhéique sévère/étendue justifie une vigilance sérologique (VIH si dermite séborrhéique profuse ; TPHA/VDRL au moindre doute sur syphilis secondaire !).",
      differentialDiagnoses = listOf(
        "SYPHILIS SECONDAIRE ('roséole syphilitique' + syphilides papulo-squameuses) : TOUCHE LES PAUMES ET LES PLANTES et s'accompagne de polyadénopathies/plaques muqueuses !",
        "Psoriasis (squames sèches épaisses blanches sur coudes/genoux)",
        "Lupus érythémateux (respecte les sillons naso-géniens !) ou dermatophytie (médaillon unique -> prélèvement mycologique !)"
      ),
      complementaryExams = listOf(
        "Formes typiques : DIAGNOSTIC CLINIQUE",
        "SÉROLOGIE SYPHILIS (TPHA/VDRL) INDISPENSABLE devant tout 'pityriasis rosé' atypique (lésions palmo-plantaires, absence de plaque mère, fièvre, adénopathies) !",
        "Sérologie VIH à proposer devant une dermite séborrhéique brutale, profuse ou résistante aux antifongiques locaux"
      ),
      gravitySigns = listOf(
        "Méconnaissance d'une syphilis secondaire contagieuse prise à tort pour un pityriasis rosé bénin",
        "Dermite séborrhéique profuse révélant une immunodépression (VIH avancé)"
      ),
      pitfallsAndLimits = "1. Le 'piège classique du Pityriasis Rosé' : toujours regarder les paumes, les plantes et la bouche ; la présence de lésions palmo-plantaires exclut le Gibert et impose une sérologie syphilis immédiate ! 2. Ne jamais prescrire de dermocorticoïdes forts au long cours sur le visage pour une dermite séborrhéique (cortico-dépendance et rosacée stéroïdienne ! -> préférer kétoconazole/ciclopirox).",
      keyPoints = listOf(
        "Dermite séborrhéique = squames grasses jaunâtres des SILLONS NASO-GÉNIENS, sourcils et cuir chevelu (à l'inverse du lupus qui épargne ces sillons !).",
        "Pityriasis rosé de Gibert = Plaque mère -> éruption du tronc en arbre de Noël épargnant visage, paumes et plantes.",
        "Au moindre doute palmo-plantaire ou muqueux devant un Gibert -> Sérologie Syphilis (TPHA/VDRL) !"
      )
    )
  )
}
