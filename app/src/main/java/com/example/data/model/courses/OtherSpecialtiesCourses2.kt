package com.example.data.model.courses

import com.example.data.model.SemiologyCategory

object OtherSpecialtiesCourses2 {
  val courses: List<MedicalCourse> = listOf(
    MedicalCourse(
      id = "course_dig_cirrhose",
      title = "Cirrhose Hépatique & Hypertension Portale (Décompensations)",
      specialty = SemiologyCategory.DIGESTIVE,
      subtitle = "Fibrose mutilante et nodules de régénération — Ascite, Hémorragie digestive et Encéphalopathie",
      definition = "Stade ultime et irréversible (sauf contrôle précoce de la cause) des hépatopathies chroniques, défini anatomopathologiquement par une fibrose annulaire diffuse associée à des nodules de régénération hépatocytaire désorganisant l'architecture lobulaire et vasculaire du foie.",
      epidemiology = "Problème de santé publique majeur en France (~700 000 patients atteints, ~15 000 décès/an par insuffisance hépatocellulaire, hémorragie par rupture de varices ou carcinome hépatocellulaire).",
      etiologiesAndRiskFactors = listOf(
        "Alcoolisation chronique (cause la plus fréquente en France ~50–60%)",
        "Stéatohépatite métabolique (MASH / ex-NASH : obésité, diabète de type 2, syndrome métabolique — en très forte progression !)",
        "Hépatites virales chroniques B (VHB) et C (VHC)",
        "Causes plus rares à toujours éliminer : Hémochromatose génétique (CST > 45%, ferritine haute), Maladie de Wilson (< 40 ans), Hépatite auto-immune, Cholangite biliaire primitive / sclérosante"
      ),
      physiopathology = "La fibrose et les nodules compriment les sinusoïdes hépatiques entraînant deux conséquences majeures : 1) HYPERTENSION PORTALE (HTP : développement de dérivations porto-caves = varices œsophagiennes, splénomégalie, circulation veineuse collatérale abdominale, ascite) ; 2) INSUFFISANCE HÉPATOCELLULAIRE (IHC : baisse de synthèse de l'albumine, des facteurs de coagulation TP/V, et défaut d'épuration ammoniacale -> encéphalopathie + ictère).",
      classificationAndPathology = "Score pronostique de CHILD-PUGH (5 paramètres : Bilirubine, Albuminémie, TP/INR, Ascite, Encéphalopathie -> Classe A bien compensée 5–6 pts, Classe B 7–9 pts, Classe C décompensée sévère 10–15 pts). Score MELD (INR, Bilirubine, Créatinine) utilisé pour l'attribution des greffons hépatiques.",
      clinicalSigns = "Forme compensée souvent ASYMPTOMATIQUE ! Signes d'examen : FOIE DUR À BORD INFÉRIEUR TRANCHANT (volume normal, augmenté ou atrophié). SIGNES D'IHC : angiomes stellaires (territoire cave supérieur !), érythrose palmaire, ongles blancs, ictère conjonctival/cutané, hippocratisme digital, fœtor hepaticus, gynécomastie, astérixis (encéphalopathie !). SIGNES D'HTP : circulation veineuse collatérale abdominale (CVC), splénomégalie, ASCITE (matité déclive des flancs mobile + signe du flot).",
      paraclinicalExams = listOf(
        "Biologie : Baisse du TP ET DU FACTEUR V (spécifique d'IHC vs carence en vitamine K où Facteur V reste NORMAL !!), Hypoalbuminémie (< 35 g/L), Hyperbilirubinémie, Thrombopénie (< 150 G/L par hypersplénisme sur HTP = signe très précoce !), Gamma-GT/Transaminases",
        "Diagnostic non invasif de fibrose F4 : Élastométrie impulsionnelle (FibroScan) ou scores sanguins (FibroTest, FIB-4) évitant souvent la biopsie hépatique",
        "Échographie abdominale DOPPLER + DOSAGE AFP tous les 6 MOIS à vie = DÉPISTAGE OBLIGATOIRE DU CARCINOME HÉPATOCELLULAIRE (CHC) + recherche de thrombose porte !",
        "Endoscopie œso-gastro-duodénale (EOGD) : dépistage et grading des VARICES ŒSOPHAGIENNES/GASTRIQUES",
        "PONCTION D'ASCITE EXPLORATRICE OBLIGATOIRE à chaque nouvelle décompensation ou hospitalisation (comptage PNN >= 250/mm³ = INFECTION SPONTANÉE DU LIQUIDE D'ASCITE même sans fièvre !!)"
      ),
      diagnosticCriteria = listOf(
        "Arguments clinico-biologiques + Imagerie (foie dysmorphique à contours bosselés + HTP) + Élastométrie (> 12–15 kPa) ou histologie (fibrose annulaire + nodules de régénération)"
      ),
      clinicalForms = listOf(
        "4 DÉCOMPENSATIONS MAJEURES : 1) Ascite & Infection du liquide d'ascite (PNN >= 250/mm³ -> C3G IV + Albumine J1–J3 !) ; 2) Hémorragie digestive par rupture de varices œsophagiennes ; 3) Encéphalopathie hépatique (astérixis -> chercher cause déclenchante : hémorragie, infection, diurétiques, benzodiazépines !!) ; 4) Syndrome hépatorénal & Carcinome hépatocellulaire"
      ),
      evolutionAndPrognosis = "Transformation majeure si sevrage complet d'alcool et guérison virale VHC/contrôle VHB. Les décompensations engagent le pronostic vital et conduisent à évaluer la transplantation hépatique.",
      managementPrinciples = listOf(
        "Traitement étiologique prioritaire : SEVRAGE ALCOOLIQUE COMPLET définitif (mesure salvatrice n°1 !), antiviraux VHB/VHC, perte de poids/équilibre diabétique (MASH), éviction stricte des médicaments hépatotoxiques, AINS (contre-indiqués formellement car déclenchent insuffisance rénale aiguë/hémorragie !!) et sédatifs/benzodiazépines",
        "Ascite : Régime modérément hyposodé (4–6 g/j) + Spironolactone +/- Furosémide (attention créatinine/ionogramme !) ; si ascite tendue -> ponctions évacuatrices avec compensation par ALBUMINE à partir de >= 5 litres retirés !",
        "Prévention rupture de varices œsophagiennes : Bêtabloquant non cardiosélectif (Carvédilol ou Propranolol) ou ligatures élastiques endoscopiques",
        "Infection liquide d'ascite (PNN >= 250/mm³) : Céfotaxime/Ceftriaxone IV + Albumine IV J1 et J3 ! Encéphalopathie : traiter cause déclenchante + Lactulose + Rifaximine"
      ),
      differentialDiagnoses = listOf(
        "Hypertension portale non cirrhotique (thrombose portale, bilharziose, syndrome de Budd-Chiari), Ascite néoplasique (carcinose péritonéale -> protides ascite > 25 g/L + cytologie maligne) ou cardiaque"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Confusion et ascite chez un patient cirrhotique",
      clinicalCaseScenario = "M. C., 58 ans (cas fictif pédagogique), suivi pour une cirrhose alcoolique Child B, est amené par sa famille pour une inversion du rythme veille-sommeil puis confusion depuis 24h avec fièvre modérée à 37.9°C et augmentation du volume abdominal. Examen : astérixis (flapping tremor) net aux deux mains, ascite modérée indolore, pas de méléna. Ponction d'ascite exploratrice systématique : liquide citrin, protides 11 g/L, 620 leucocytes/mm³ dont 480 PNN/mm³ !",
      clinicalCaseCorrection = "1) ENCÉPHALOPATHIE HÉPATIQUE déclenchée par une INFECTION SPONTANÉE DU LIQUIDE D'ASCITE (PNN >= 250/mm³ = 480/mm³ ! — souvent asymptomatique sur le plan abdominal et révélée uniquement par l'encéphalopathie !!).\n" +
        "2) Traitement urgent immédiat : Céfotaxime IV (ou Ceftriaxone) + PERFUSION D'ALBUMINE 20% à J1 (1.5 g/kg) et J3 (1 g/kg) pour prévenir le syndrome hépatorénal !\n" +
        "3) Ajouter Lactulose (cible 2–3 selles molles/jour) pour l'encéphalopathie, proscrire tout sédatif/benzodiazépine et tout AINS, faire hémocultures + ECBU et écho abdominale.",
      keyPoints = listOf(
        "Foie dur tranchant + Angiomes stellaires + Thrombopénie + Baisse TP ET Facteur V = Cirrhose décompensée",
        "Ponction d'ascite OBLIGATOIRE à chaque décompensation/hospitalisation : PNN >= 250/mm³ = Infection liquide d'ascite -> C3G IV + Albumine J1/J3 !",
        "Surveillance semestrielle (tous les 6 mois) par Échographie + AFP (dépistage Carcinome Hépatocellulaire !) ; ZÉRO AINS et ZÉRO benzodiazépines !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Score pronostique de Child-Pugh de la Cirrhose (5–15 points)",
          headers = listOf("Paramètre", "1 point", "2 points", "3 points"),
          rows = listOf(
            listOf("Encéphalopathie hépatique", "Absente", "Grade I–II (astérixis/confusion)", "Grade III–IV (coma)"),
            listOf("Ascite", "Absente", "Minime / contrôlée", "Tendue / réfractaire"),
            listOf("Bilirubine (µmol/L)", "< 35", "35–50", "> 50"),
            listOf("Albumine (g/L)", "> 35", "28–35", "< 28"),
            listOf("TP (%)", "> 50 %", "40–50 %", "< 40 %")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_dig_pancreatite",
      title = "Pancréatite Aiguë (Œdémateuse vs Nécrosante)",
      specialty = SemiologyCategory.DIGESTIVE,
      subtitle = "Lipasémie >= 3N + Douleur transfixiante — Jeûne, hydratation et recherche de la cause biliaire/alcoolique",
      definition = "Autodigestion inflammatoire aiguë du pancréas par activation intrapancréatique prématurée des enzymes digestives (trypsine), évoluant soit sous forme bénigne œdémateuse (80%), soit sous forme nécrosante grave (20%) exposant aux défaillances d'organes.",
      epidemiology = "Incidence ~30–40/100 000/an. Mortalité < 2% dans les formes bénignes œdémateuses mais jusqu'à 20–30% dans les formes nécrosantes avec défaillance viscérale ou surinfection de nécrose.",
      etiologiesAndRiskFactors = listOf(
        "LES 2 CAUSES PRINCIPALES (80% des cas) : 1) LITHIASE BILIAIRE / MIGRATION D'UN CALCUL DANS LA VOIE BILIAIRE PRINCIPALE (n°1 chez la femme et > 50 ans ! -> pic précoce d'ALAT !!) ; 2) ALCOOLISATION CHRONIQUE (n°1 chez l'homme d'âge moyen !)",
        "Autres causes (20% — bilan complet si pas de lithiase ni d'alcool !) : Hypertriglycéridémie majeure (> 10 g/L / 11 mmol/L), Hypercalcémie, Médicaments (Azathioprine, Acide valproïque, Diurétiques thiazidiques...), Post-CPRE, Tumeur pancréatique (TIPMP ou adénocarcinome obstructif chez > 50 ans sans cause !), Auto-immune (IgG4)"
      ),
      physiopathology = "Activation enzymatique prématurée intracellulaire -> œdème interstitiel (forme œdémateuse) ou cytostéatonécrose péripancréatique et nécrose glandulaire (forme nécrosante) avec libération massive de médiateurs inflammatoires déclenchant un syndrome de réponse inflammatoire systémique (SIRS), troisième secteur hypovolémique majeur, SDRA et choc.",
      classificationAndPathology = "Classification révisée d'Atlanta : Pancréatite aiguë Bénigne (aucune défaillance d'organe ni complication), Modérément sévère (défaillance transitoire < 48h ou complication locale), SÉVÈRE (défaillance d'organe PERSISTANTE > 48h : rénale, respiratoire ou hémodynamique).",
      clinicalSigns = "DOULEUR PANCRÉATIQUE TYPIQUE : douleur épigastrique d'apparition rapide en quelques heures, INTENSE, TRANSFIXIANTE (irradiant vers le dos et l'omoplate), en barre sous-costale, PROLONGÉE (> 24h), résistante aux antalgiques usuels et CALMÉE EN POSITION PENCHÉE EN AVANT (« en chien de fusil » !!), accompagnée de vomissements abondants n'apportant pas de soulagement et d'un iléus réflexe (arrêt gaz). Examen : douleur provoquée épigastrique sans vraie contracture (« empâtement épigastrique »), ecchymoses tardives des flancs (signe de Grey-Turner) ou péri-ombilicales (signe de Cullen) dans les formes hémorragiques graves !",
      paraclinicalExams = listOf(
        "DIAGNOSTIC POSITIF IMMÉDIAT = DOULEUR TYPIQUE + LIPASÉMIE >= 3 FOIS LA NORMALE (>= 3N) ! (Ne plus doser l'amylasémie ni faire de scanner immédiatement dans les 1ères heures si diagnostic certain !)",
        "ÉCHOGRAPHIE HÉPATO-BILIAIRE DÈS L'ADMISSION + TRANSAMINASES ALAT (rechercher l'origine LITHIASIQUE BILIAIRE : ALAT > 3N a une forte valeur prédictive positive de lithiase de la voie biliaire !!)",
        "SCANNER ABDOMINAL INJECTÉ OPTIMAL ENTRE 72 ET 96 HEURES après le début des douleurs (réalisé trop tôt < 48h, il sous-estime l'étendue de la nécrose ! ; évalue nécrose glandulaire et coulées selon l'indice CTSI de Balthazar)",
        "Bilan de gravité et étiologique : NFS (hémoconcentration -> hypovolémie !), CRP à H48 (> 150 mg/L prédit une forme grave !), Urée/Créatinine, Gaz du sang/Lactates, Calcémie, Triglycérides à jeun"
      ),
      diagnosticCriteria = listOf(
        "Au moins 2 des 3 critères d'Atlanta : 1) Douleur abdominale pancréatique typique ; 2) Lipasémie >= 3N ; 3) Imagerie (TDM/IRM) évocatrice"
      ),
      clinicalForms = listOf(
        "Pancréatite aiguë biliaire avec ANGIOCHOLITE associée (fièvre + ictère + frissons -> indication formelle à CPRE + sphinctérotomie endoscopique en URGENCE < 24h !)",
        "Surinfection tardive de coulée de nécrose (à J10–J21 : reprise fièvre + choc -> ponction guidée + ATB + nécrosectomie endoscopique « step-up » !)"
      ),
      evolutionAndPrognosis = "80% de guérison simple en 4–7 jours sous réhydratation et antalgie ; 20% nécrosantes nécessitant réanimation, avec risque tardif de pseudokystes (> 4 semaines), surinfection de nécrose, diabète ou insuffisance pancréatique exocrine.",
      managementPrinciples = listOf(
        "RÉHYDRATATION INTRAVEINEUSE PRÉCOCE ET ADAPTÉE par cristalloïdes équilibrés (Ringer Lactate — corrige le 3e secteur sans excès massif !)",
        "ANTALGIE PUISSANTE (Paracétamol + Morphiniques IV titrés si besoin ; le mythe du spasme du sphincter d'Oddi ne contre-indique pas la morphine !)",
        "JEÛNE COURT STRICTEMENT LIMITÉ À LA PHASE DOULOUREUSE : reprise rapide de l'alimentation orale dès amélioration des douleurs/vomissements dans les formes bénignes ; NUTRITION ENTÉRALE (sonde naso-gastrique ou naso-jéjunale) PRÉCOCE dans les formes graves (préserve la barrière intestinale et réduit la surinfection de nécrose !)",
        "PAS D'ANTIBIOPROPHYLAXIE SYSTÉMATIQUE (réservée uniquement à une angiocholite prouvée ou une infection documentée de nécrose !) ; CHOLÉCYSTECTOMIE lors de la même hospitalisation si pancréatite biliaire bénigne pour éviter la récidive mortelle !"
      ),
      differentialDiagnoses = listOf(
        "Ulcère gastroduodénal perforé (contracture « ventre de bois », pneumopéritoine à l'imagerie), Infarctus mésentérique (lactates ++), Syndrome coronarien inférieur (ECG !), Dissection aortique, Colique hépatique simple (lipasémie < 3N)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Douleur épigastrique soulagée en chien de fusil et ALAT hautes",
      clinicalCaseScenario = "Mme M., 52 ans (cas fictif pédagogique), obèse, consulte aux urgences pour une violente douleur épigastrique transfixiante vers le dos évoluant depuis 8 heures, soulagée uniquement lorsqu'elle s'assoit penchée en avant sur ses genoux, avec vomissements bilieux. Apyrétique, TA 135/80, FC 98/min, abdomen sensible sans contracture. Lipasémie à 1 450 UI/L (N < 60, soit 24 N !), ALAT à 290 UI/L (5 N), bilirubine normale, triglycérides et calcium normaux.",
      clinicalCaseCorrection = "1) PANCRÉATITE AIGUË D'ORIGINE LITHIASIQUE BILIAIRE très probable (douleur typique + lipasémie > 3N + élévation franche des ALAT > 3N très évocatrice d'une migration calculeuse chez une femme > 50 ans !).\n" +
        "2) Examens immédiats : ÉCHOGRAPHIE HÉPATO-BILIAIRE (calculs vésiculaires ? dilatation VBP ?) ; PAS de scanner abdominal immédiat à H8 (à programmer à H72–H96 pour évaluer la nécrose !).\n" +
        "3) CAT : Réhydratation IV (Ringer Lactate), antalgie IV (paracétamol + morphine si besoin), jeûne bref jusqu'à sédation des douleurs, ZÉRO antibiotique en l'absence d'angiocholite, puis cholécystectomie avant la sortie si forme bénigne !",
      keyPoints = listOf(
        "Diagnostic immédiat = Douleur pancréatique (« penchée en avant ») + Lipasémie >= 3N (PAS besoin de scanner précoce si typique !)",
        "Scanner injecté idéal à H72–H96 (car avant 48–72h la nécrose est sous-estimée !) ; Échographie + ALAT dès l'admission (chercher calcul biliaire !)",
        "Zéro antibioprophylaxie sans preuve d'infection ; reprendre alimentation orale ou entérale tôt ; cholécystectomie rapide si biliaire !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Calendrier optimal des examens et gestes dans la Pancréatite Aiguë",
          headers = listOf("Délai", "Examen / Geste clé", "Objectif clinique précis"),
          rows = listOf(
            listOf("H0 (Admission)", "Lipasémie (>= 3N), ALAT, Calcémie, TG, Écho biliaire", "Confirmer diagnostic + dépister cause biliaire/métabolique"),
            listOf("H0–H48", "Réhydratation IV Ringer, Antalgie, CRP à H48 (> 150)", "Corriger 3e secteur et prédire la sévérité"),
            listOf("H72–H96", "Scanner abdominal avec injection de produit de contraste", "Quantifier exactement la nécrose glandulaire et les coulées")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_nephro_ira_irc",
      title = "Insuffisance Rénale Aiguë (IRA) vs Chronique (IRC)",
      specialty = SemiologyCategory.URINARY,
      subtitle = "Distinguer aigu vs chronique + Triade Prérénale / Organique / Obstructive",
      definition = "L'IRA est une baisse brutale et potentiellement réversible du débit de filtration glomérulaire (DFG) définie par KDIGO (hausse créatinine >= +26.5 µmol/L en 48h, ou × 1.5 en 7j, ou diurèse < 0.5 mL/kg/h >= 6h). L'IRC est une altération irréversible persistante > 3 mois (DFG < 60 mL/min/1.73m² et/ou marqueurs d'atteinte rénale).",
      epidemiology = "L'IRA complique 5–15% des hospitalisations et > 40% des patients en réanimation. L'IRC touche ~10% de la population adulte (diabète et HTA représentent > 50% des causes d'IRC terminale !).",
      etiologiesAndRiskFactors = listOf(
        "IRA OBSTRUCTIVE / POST-RÉNALE (à ÉLIMINER EN TOUT PREMIER par échographie + globe vésical !!) : HBP/globe vésical, cancer prostate/col utérin, lithiase urétérale bilatérale ou sur rein unique, fibrose rétropéritonéale",
        "IRA FONCTIONNELLE / PRÉ-RÉNALE (reins sains hypoperfusés !) : déshydratation, hémorragie, choc, insuffisance cardiaque, cirrhose + association toxique classique (« AINS + IEC/ARA2 + Diurétiques » !!)",
        "IRA ORGANIQUE / PARENCHYMATEUSE : Nécrose Tubulaire Aiguë (NTA > 80% : ischémie prolongée ou néphrotoxiques aminosides/iode/cisplatine !), Glomérulonéphrite rapidement progressive (GNRP -> hématurie + protéinurie -> urgence PBR !!), Néphrite interstitielle aiguë immuno-allergique (IPP, bêta-lactamines), Microangiopathie thrombotique / Néphropathie vasculaire"
      ),
      physiopathology = "Dans l'IRA fonctionnelle, l'hypoperfusion stimule aldostérone et ADH -> réabsorption maximale d'eau, de sodium (Na+ urinaire < 20 mmol/L, FeNa < 1%) et d'urée (rapport Urée/Créat élevé). Dans la NTA, les cellules tubulaires nécrosées perdent leur capacité de concentration (Na+ urinaire > 40 mmol/L). Dans l'IRC, la perte néphronique progressive entraîne anémie (défaut d'EPO) et hypocalcémie (défaut de calcitriol).",
      classificationAndPathology = "Stades KDIGO de l'IRA (1 à 3 selon créatinine ×1.5–3 ou anurie). Stades de l'IRC (1 à 5 selon DFG CKD-EPI : G3a/3b 30–59, G4 15–29, G5 < 15 mL/min = stade de suppléance).",
      clinicalSigns = "IRA : souvent asymptomatique biologiquement au début ou oligo-anurie (< 500 mL/24h), signes de surcharge (OAP, HTA, œdèmes) ou de déshydratation (pli cutané, hypotension orthostatique = fonctionnelle !). RECHERCHER SYSTÉMATIQUEMENT UN GLOBE VÉSICAL (matité sus-pubienne convexe en haut !) ET DES SIGNES D'URGENCE VITALE : DYSPNÉE/OAP réfractaire, SIGNES ECG D'HYPERKALIÉMIE (ondes T amples pointues, élargissement QRS -> arrêt cardiaque !), encéphalopathie/péricardite urémique. IRC : asthénie, pâleur (anémie), prurit, nycturie précoce, HTA.",
      paraclinicalExams = listOf(
        "1) ECG IMMÉDIAT DEVANT TOUTE IRA OU HYPERKALIÉMIE (>= 6.0 mmol/L = DANGER MORTEL IMMINENT !)",
        "2) ÉCHOGRAPHIE RÉNALE ET VÉSICALE EN URGENCE (double intérêt immédiat : A) Éliminer une OBSTRUCTION = dilatation pyélocalicielle/globe ; B) Distinguer IRA vs IRC : REINS DE PETITE TAILLE < 10 cm ET CORTEX AMINCI = IRC ; reins de taille normale/augmentée = IRA ou diabète/amylose/PKRAD !!)",
        "3) Biologie sang pour différencier IRA vs IRC : ANÉMIE normochrome normocytaire arégénérative + HYPOCALCÉMIE / HYPERPHOSPHORÉMIE = arguments forts pour IRC (car présents dès longue date) ; Créatininémie antérieure à chercher !",
        "4) Ionogramme urinaire + Protéinurie/Créatininurie + ECBU (hématurie + cylindres hématiques = glomérulonéphrite -> bilan auto-immun ANCA, anti-MBG, C3/C4 et PBR rapide !)"
      ),
      diagnosticCriteria = listOf(
        "IRA : Élévation brutale créatinine (KDIGO) avec reins de taille normale (hors obstacle) sans antécédent rénal ; IRC : DFG < 60 et/ou albuminurie/anomalie > 3 mois avec petits reins + anémie/hypocalcémie"
      ),
      clinicalForms = listOf(
        "Syndrome néphritique / GNRP (IRA rapidement progressive + HTA + hématurie glomérulaire + protéinurie -> URGENCE BIOPSIE RÉNALE pour vascularite/lupus/Goodpasture !)",
        "Rhabdomyolyse traumatique/compressive (CPK géantes > 5000 + hyperkaliémie foudroyante + hypocalcémie précoce + bandelette urinaire « sang+++ » SANS hématie au microscope = MYOGLOBINURIE !!)"
      ),
      evolutionAndPrognosis = "IRA fonctionnelle/obstructive lève rapidement la créatinine après réhydratation ou dérivation urinaire (attention au syndrome de levée d'obstacle = polyurie massive à compenser !). Risque d'hyperkaliémie fatale ou OAP en urgence.",
      managementPrinciples = listOf(
        "URGENCES VITALES IMMÉDIATES = 5 INDICATIONS CLASSIQUES D'ÉPURATION EXTRARÉNALE (DIALYSE EN URGENCE « AEIOU ») : Acidose métabolique sévère réfractaire (pH < 7.15) ; Électrolytes (HYPERKALIÉMIE MENACANTE > 6.5 ou signes ECG réfractaire -> Gluconate de Calcium IV d'abord pour protéger le cœur + Insuline-Glucose + Salbutamol nébulisé !!) ; Intoxications dialysables ; OAP / Surcharge réfractaire aux diurétiques ; Urémie symptomatique (péricardite ou encéphalopathie)",
        "IRA OBSTRUCTIVE : DÉRIVATION URINAIRE EN URGENCE (Sondage vésical/cathéter sus-pubien si globe ; sonde JJ ou néphrostomie percutanée si obstacle haut !)",
        "IRA FONCTIONNELLE : Réhydratation IV prudente (NaCl 0.9%) + ARRÊT TEMPORAIRE des néphrotoxiques et « triade mortelle » (AINS, IEC/ARA2, diurétiques, metformine = risque d'acidose lactique !!)",
        "IRC : Néphroprotection stricte (contrôle TA < 130/80, IEC/ARA2 + Inhibiteurs SGLT2 si protéinurie/diabète, arrêt tabac, ajustement posologique des médicaments !)"
      ),
      differentialDiagnoses = listOf(
        "Fausse hyperkaliémie par hémolyse in vitro (toujours vérifier l'ECG et contrôler sur tube hépariné sans garrot si patient totalement asymptomatique !)",
        "Élévation isolée créatinine sans baisse de DFG (grosse masse musculaire, triméthoprime/cimétidine bloquant la sécrétion tubulaire)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Hyperkaliémie menaçante et obstruction sur globe vésical",
      clinicalCaseScenario = "M. R., 77 ans (cas fictif pédagogique), traité par Ramipril + Ibuprofène depuis 5 jours pour une sciatique, consulte pour asthénie extrême et absence de miction depuis 24 heures. Examen : volumineuse masse hypogastrique mate convexe en haut très douloureuse à la palpation. Créatinine 540 µmol/L (était à 85 il y a 1 mois), K+ 6.9 mmol/L, HCO3- 14 mmol/L. ECG : ondes T très amples pointues symétriques et disparition des ondes P !",
      clinicalCaseCorrection = "1) INSUFFISANCE RÉNALE AIGUË OBSTRUCTIVE (globe vésical aigu) AGGRAVÉE PAR AINS + IEC, COMPLIQUÉE D'HYPERKALIÉMIE MENAÇANTE CARDIOTOXIQUE (signes ECG graves : T pointues + disparition onde P -> risque imminent de fibrillation ventriculaire !!).\n" +
        "2) PRIORITÉ ABSOLUE EN < 2 MINUTES AVANT TOUT : GLUCONATE DE CALCIUM 10% IV LENT (protège immédiatement la membrane myocardique — ne baisse pas le K+ mais sauve le cœur !) + Insuline-Glucosé IV + Salbutamol nébulisé + scope continu.\n" +
        "3) SIMULTANÉMENT : SONDAGE VÉSICAL ÉVACUATEUR DOUX EN URGENCE + arrêt Ramipril et Ibuprofène + surveillance stricte de la diurèse (prévenir/compenser un syndrome de levée d'obstacle !).",
      keyPoints = listOf(
        "Devant toute IRA : 1) ECG (Hyperkaliémie ?) ; 2) Palpation hypogastrique + Échographie rénale (Obstacle ? Taille des reins vs IRC ?) ; 3) Arrêter AINS / IEC / Metformine !",
        "Anémie normocytaire + Hypocalcémie + Petits reins (< 10 cm) = Insuffisance Rénale CHRONIQUE",
        "Signes ECG d'hyperkaliémie = GLUCONATE DE CALCIUM IV EN EXTRÊME URGENCE puis transfert K+ intracellulaire (Insuline-Glucose + Salbutamol) +/- Dialyse"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Distinction sémiologique et biologique : IRA Fonctionnelle vs Organique (NTA) vs IRC",
          headers = listOf("Paramètre", "IRA Fonctionnelle", "IRA Organique (NTA)", "IRC évoluée"),
          rows = listOf(
            listOf("Taille reins (Écho)", "Normale (10–12 cm)", "Normale / augmentée", "PETITS REINS (< 10 cm) cortex aminci"),
            listOf("Anémie / Hypocalcémie", "Absentes (récent)", "Absentes (récent)", "PRÉSENTES (EPO / Calcitriol bas)"),
            listOf("Natriurèse (Na+ u)", "< 20 mmol/L (FeNa < 1%)", "> 40 mmol/L (FeNa > 2%)", "Variable"),
            listOf("Urée / Créat (plasma)", "Élevé (> 100 en µmol)", "Proportionnel", "Proportionnel")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_endo_acidocetose",
      title = "Acidocétose Diabétique (ACD) & Coma Hyperosmolaire",
      specialty = SemiologyCategory.ENDOCRINOLOGY,
      subtitle = "Urgences métaboliques du diabète — Piège mortel de la KALIÉMIE avant de débuter l'insuline !",
      definition = "L'acidocétose diabétique (ACD) est une décompensation métabolique grave par carence absolue ou relative en insuline (triade : Hyperglycémie >= 2.5 g/L [ou parfois normale sous iSGLT2 !], Cétonémie/Cétonurie majeure >= 3 mmol/L, Acidose métabolique pH < 7.30 et HCO3- < 15–18 mmol/L). L'état hyperosmolaire (sujet âgé DT2) associe hyperglycémie extrême > 6 g/L et osmolarité >= 320 mOsm/L sans cétose franche ni acidose.",
      epidemiology = "Mode de révélation fréquent du Diabète de Type 1 (enfant/adulte jeune), ou complication d'une interruption d'insuline ou d'une infection intercurrente. Mortalité faible (< 1–2% dans l'ACD si bien prise en charge) mais élevée (10–15%) dans le coma hyperosmolaire du sujet âgé.",
      etiologiesAndRiskFactors = listOf(
        "Diabète de type 1 INAUGURAL (ne jamais manquer chez l'enfant/ado avec syndrome polyuro-polydipsique + douleurs abdominales !)",
        "ARRÊT OU SOUS-DOSAGE D'INSULINE (ou panne de pompe à insuline sous-cutanée ++)",
        "FACTEUR DÉCLENCHANT À CHERCHER SYSTÉMATIQUEMENT : INFECTION (urinaire, pulmonaire, cutanée), Infarctus du myocarde (faire ECG !), Traumatisme, Corticoïdes, Inhibiteur SGLT2 (donne des ACD « EUGLYCÉMIQUES » avec glycémie < 2 g/L pièges !!)"
      ),
      physiopathology = "Carence en insuline + excès de glucagon/catécholamines -> impossibilité d'utiliser le glucose -> glycogénolyse et néoglucogenèse massives -> HYPERGLYCÉMIE -> polyurie osmotique -> DÉSHYDRATATION SÉVÈRE ET FUITE MASSIVE DE POTASSIUM URINAIRE (déplétion potassique totale constante malgré une kaliémie sanguine parfois faussement normale/haute par sortie cellulaire en acidose !!). Parallèlement : lipolyse massive -> cétogenèse hépatique (bêta-hydroxybutyrate) -> ACIDOSE MÉTABOLIQUE À TROU ANIONIQUE ÉLEVÉ.",
      classificationAndPathology = "Sévérité de l'ACD : Légère (pH 7.25–7.30, HCO3- 15–18), Modérée (pH 7.00–7.24, HCO3- 10–15), SÉVÈRE (pH < 7.00, HCO3- < 10, trouble de conscience).",
      clinicalSigns = "Début rapide (< 24–48h) après syndrome polyuro-polydipsique intense et amaigrissement : DÉSHYDRATATION GLOBALE (pli cutané, yeux cernés, tachycardie, hypotension), DYSPNÉE AMPLIFIÉE DE KUSSMAUL (ample, lente/rapide en 4 temps, sans râles pulmonaires -> vise à éliminer le CO2 pour compenser l'acidose !), HALEINE ACÉTONIQUE (« odeur de pomme reinette »), NAUSÉES, VOMISSEMENTS ET DOULEURS ABDOMINALES INTENSES (piège mimant une appendicite ou urgence chirurgicale chez l'enfant/ado !!), confusion ou coma.",
      paraclinicalExams = listOf(
        "DIAGNOSTIC AU LIT EN 1 MINUTE : Glycémie capillaire + CÉTONÉMIE CAPILLAIRE (> 3.0 mmol/L = acidocétose ! ; ou Cétonurie >= ++ à la BU)",
        "Gaz du sang artériels/veineux : pH < 7.30, HCO3- < 15–18 mmol/L, PaCO2 basse (compensation respiratoire), Lactates",
        "IONOGRAMME SANGUIN EN URGENCE + ECG IMMÉDIAT : KALIÉMIE (!! PIÈGE MORTEL : vérifier K+ AVANT L'INSULINE !!), Natrémie mesurée et Natrémie corrigée [Na+ + 1.6 × (Glycémie g/L - 1)], Chlore/Trou anionique élevé (> 16), Urée/Créatinine",
        "Recherche du facteur déclenchant : ECG (SCA silencieux ?), BU/ECBU, Radio thorax, NFS/CRP, Hémocultures, Troponine"
      ),
      diagnosticCriteria = listOf(
        "Triade diagnostique : Glycémie > 2.5 g/L (attention < 2 g/L possible si iSGLT2/jeûne/grossesse !) + Cétonémie >= 3.0 mmol/L (ou cétonurie ++) + pH < 7.30 / HCO3- < 15–18 mmol/L"
      ),
      clinicalForms = listOf(
        "Acidocétose euglycémique sous iSGLT2 (Gliflozines) ou grossesse : glycémie peu élevée (< 2 g/L) MAIS cétonémie > 3 et acidose sévère -> d'où l'obligation de mesurer la CÉTONÉMIE devant tout mal-être/vomissement diabétique !",
        "État Hyperosmolaire (Sujet âgé DT2 : déshydratation majeure, troubles neurologiques, glycémie > 6 g/L, osmolarité > 320 mOsm/L, SANS acidocétose !)"
      ),
      evolutionAndPrognosis = "Complications iatrogènes redoutables à éviter : 1) HYPOKALIÉMIE SÉVÈRE MORTELLE (trouble du rythme) si insuline démarrée avec K+ < 3.3 mmol/L sans recharger en K+ !! ; 2) ŒDÈME CÉRÉBRAL (surtout chez l'enfant si réhydratation trop brutale ou chute trop rapide de la glycémie/natrémie ou bicarbonates !); 3) Hypoglycémie.",
      managementPrinciples = listOf(
        "1) RÉHYDRATATION IV IMMÉDIATE : NaCl 0.9% (1 L sur la 1ère heure puis adapté ; ajouter G5%/G10% dès que la glycémie descend < 2.5 g/L pour pouvoir continuer l'insuline jusqu'à disparition de la cétose !!)",
        "2) RÈGLE ABSOLUE SUR LA KALIÉMIE : NE JAMAIS DÉMARRER L'INSULINE SI K+ < 3.3 mmol/L (d'abord perfuser du KCl !!) ; si K+ entre 3.3 et 5.2 -> Insuline + KCl IV systématique d'emblée ; si K+ > 5.2 -> Insuline seule au début et introduire KCl dès que K+ < 5.0 !",
        "3) INSULINOTHÉRAPIE IV CONTINUE AU POUSSE-SERINGUE (Actrapid 0.1 UI/kg/h IVSE — JAMAIS arrêter l'insuline IV tant que la cétonémie n'est pas < 0.6 mmol/L et pH > 7.30 !!)",
        "4) PAS DE BICARBONATES IV en routine (dangereux -> œdème cérébral, hypokaliémie !) sauf pH gravissime < 6.90 ; Traitement de la CAUSE déclenchante (ATB, etc.) + Éducation thérapeutique avant sortie !"
      ),
      differentialDiagnoses = listOf(
        "Acidose lactique (choc, metformine sur IRA -> lactates très élevés, cétonémie basse), État hyperosmolaire pur (pas d'acidose/cétose), Cétose de jeûne/alcoolique (pH normal ou peu bas, glycémie basse/normale), Urgence chirurgicale abdominale vraie (attention : la douleur de l'ACD régresse en quelques heures sous insuline/hydratation ; si elle persiste -> chercher vraie appendicite/cholécystite gâchette !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Vomissements, dyspnée de Kussmaul et piège du K+",
      clinicalCaseScenario = "Léo, 19 ans (cas fictif pédagogique), sans antécédent connu, consulte pour amaigrissement de 5 kg en 3 semaines, soif intense et ce matin nausées, douleurs abdominales diffuses et respiration très ample et rapide (FR 28/min, auscultation pulmonaire claire). Haleine fruitée. Dextro : 4.8 g/L, Cétonémie capillaire : 5.6 mmol/L. Gaz du sang : pH 7.12, HCO3- 8 mmol/L, PaCO2 22 mmHg. Ionogramme : Na+ 131, K+ 3.0 mmol/L !",
      clinicalCaseCorrection = "1) ACIDOCÉTOSE DIABÉTIQUE MODÉRÉE À SÉVÈRE RÉVÉLANT UN DIABÈTE DE TYPE 1 INAUGURAL (polyurie-polydipsie + dyspnée de Kussmaul + glycémie 4.8 g/L + cétonémie 5.6 + pH 7.12).\n" +
        "2) ALERTE VITALE IMMÉDIATE : La kaliémie de départ est DÉJÀ BASSE À 3.0 mmol/L (< 3.3 mmol/L !!), ce qui traduit un déficit potassique corporel massif !\n" +
        "3) CONDUITE À TENIR : INTERDICTION DE DÉBUTER L'INSULINE IV TOUT DE SUITE (elle ferait plonger le K+ à 2.0 -> arrêt cardiaque !). Débuter IMMÉDIATEMENT la réhydratation par NaCl 0.9% + RECHARGE POTASSIQUE IV PAR KCl sous scope ECG -> dès que K+ >= 3.3 mmol/L, débuter l'insuline IVSE à 0.1 UI/kg/h (puis ajouter G5% dès glycémie < 2.5 g/L jusqu'à normalisation de la cétonémie < 0.6) !",
      keyPoints = listOf(
        "ACD = Glycémie > 2.5 g/L (sauf iSGLT2 !) + Cétonémie >= 3.0 mmol/L + pH < 7.30 / HCO3- < 15–18",
        "TOUJOURS vérifier K+ AVANT l'insuline : si K+ < 3.3 mmol/L -> KCl d'abord, JAMAIS d'insuline avant remontée >= 3.3 !",
        "Ne jamais arrêter l'insuline IV quand la glycémie baisse < 2.5 g/L : passer au G5% + NaCl et poursuivre l'insuline jusqu'à disparition complète de la cétonémie (< 0.6) !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Comparaison Acidocétose Diabétique (ACD) vs État Hyperosmolaire (EHO)",
          headers = listOf("Critère", "Acidocétose Diabétique (ACD)", "État Hyperosmolaire (EHO)"),
          rows = listOf(
            listOf("Terrain type", "DT1 (sujet jeune, révélation ou arrêt insuline)", "DT2 sujet âgé (> 70 ans, perte accès à l'eau)"),
            listOf("Glycémie", ">= 2.5 g/L (voire < 2 sous iSGLT2)", "Très élevée (> 6.0 g/L)"),
            listOf("Cétonémie / Acidose", "Cétonémie >= 3 mmol/L, pH < 7.30, Kussmaul", "Absente/minime, pH >= 7.30, Osmolarité >= 320 mOsm/L"),
            listOf("Priorité thérapeutique", "NaCl 0.9% + KCl (si >= 3.3) + Insuline IVSE", "RÉHYDRATATION MASSIVE PRIORITAIRE + Insuline faible dose")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_hemato_anemies",
      title = "Anémies : Démarche Diagnostique (VGM & Réticulocytes)",
      specialty = SemiologyCategory.HEMATOLOGY,
      subtitle = "Arbre décisionnel universel devant une baisse d'Hémoglobine — Microcytaire vs Normo/Macrocytaire",
      definition = "Diminution de la concentration sanguine en hémoglobine en dessous des valeurs normales pour l'âge et le sexe (< 13 g/dL chez l'homme adulte, < 12 g/dL chez la femme non enceinte, < 10.5–11 g/dL chez la femme enceinte), traduisant un défaut de production médullaire (arégénérative) ou un excès de pertes/destruction (régénérative).",
      epidemiology = "Anomalie hématologique la plus fréquente en médecine (~25% de la population mondiale). La carence martiale reste la 1ère cause mondiale ; chez l'adulte/homme/ménopausée, elle impose impérativement de rechercher un saignement digestif occulte !",
      etiologiesAndRiskFactors = listOf(
        "ANÉMIE MICROCYTAIRE (VGM < 80 fL) : 1) CARENCE MARTIALE (ferritine basse -> règles abondantes femme jeune ; CANCER COLORECTAL / ULCÈRE à éliminer absolument chez homme ou femme ménopausée !!) ; 2) INFLAMMATION CHRONIQUE (ferritine haute/normale, CRP haute !) ; 3) Thalassémies (pseudopolyglobulie microcytaire GR normaux/élevés !), Anémie sidéroblastique",
        "ANÉMIE NORMO/MACROCYTAIRE RÉGÉNÉRATIVE (Réticulocytes >= 120–150 G/L) : 1) HÉMORRAGIE AIGUË (> 48–72h) ; 2) ANÉMIE HÉMOLYTIQUE (LDH haut, Bilirubine libre haute, Haptoglobine EFFONDRÉE ! — auto-immune Coombs+, drépanocytose, G6PD, valve mécanique, MAT schizocytes !)",
        "ANÉMIE NORMO/MACROCYTAIRE ARÉGÉNÉRATIVE (Réticulocytes < 120 G/L) : Carence en VITAMINE B12 (Biermer -> gastrectomie/atrophie fundique + signes neuro !) ou FOLATES (B9), Alcoolisme, Hypothyroïdie, Insuffisance rénale (EPO), Envahissement/insuffisance médullaire (Myélodysplasie, Leucémie, Aplasie !)"
      ),
      physiopathology = "La microcytose traduit un défaut de synthèse de l'hémoglobine (déficit en fer ou en globine -> mitoses érythroblastiques supplémentaires -> petits globules rouges). La macrocytose mégaloblastique (B9/B12) traduit un défaut de synthèse de l'ADN -> asynchronisme de maturation noyau-cytoplasme -> gros globules rouges fragiles détruits en moelle (avortement intramédullaire -> LDH très hauts malgré réticulocytes bas !!).",
      classificationAndPathology = "Deux examens initiaux suffisent à orienter toute anémie : 1) Le VGM (Microcytaire < 80 fL | Normocytaire 80–100 fL | Macrocytaire > 100 fL) ; 2) Le taux de RÉTICULOCYTES (pour toute anémie normo- ou macrocytaire : Régénérative >= 120–150 G/L vs Arégénérative < 120 G/L).",
      clinicalSigns = "SYNDROME ANÉMIQUE : PÂLEUR cutanéo-muqueuse (conjonctives inférieures, paumes !), dyspnée d'effort, asthénie, tachycardie, souffle systolique fonctionnel anorganique, céphalées/acouphènes/vertiges. SIGNES D'ORIENTATION ÉTIOLOGIQUE : ongles cassants/koïlonychie/perlèche/pica (carence martiale !), SUBICTÈRE + SPLÉNOMÉGALIE + urines foncées (hémolyse !), GLOSSITE DE HUNTER + TROUBLES NEUROLOGIQUES (paresthésies, syndrome cordonal postérieur / ataxie = carence B12 !). SIGNES DE MAUVAISE TOLÉRANCE (TRANSFUSION URGENTE !!) : angor, dyspnée de repos, confusion, choc/hypotension.",
      paraclinicalExams = listOf(
        "AVANT TOUTE MICROCYTOSE (< 80 fL) : FERRITINÉMIE + CRP (Ferritine basse < 15–30 µg/L = CARENCE MARTIALE ; Ferritine élevée + CRP élevée = INFLAMMATION ; si les deux sont normaux/hauts avec GR élevés -> Électrophorèse Hb pour Thalassémie !)",
        "AVANT TOUTE ANÉMIE NORMO/MACROCYTAIRE : RÉTICULOCYTES + FROTTIS SANGUIN (schizocytes = MAT urgence ! ; sphérocytes/drépanocytes !)",
        "Si Réticulocytes ÉLEVÉS (>= 120 G/L) : Bilan d'HÉMOLYSE (LDH, Bilirubine libre, Haptoglobine effondrée !) + TEST DE COOMBS DIRECT + recherche saignement aigu",
        "Si Réticulocytes BAS (< 120 G/L) : TSH, Créatinine, Bilan hépatique/alcool, Dosage VITAMINE B12 & FOLATES (B9) -> Si tout normal : MYÉLOGRAMME (chercher myélodysplasie, leucémie aiguë, myélome !)",
        "TOUTE CARENCE MARTIALE CHEZ UN HOMME OU UNE FEMME MÉNOPAUSÉE = FIBROSCOPIE GASTRIQUE + COLOSCOPIE OBLIGATOIRES !!"
      ),
      diagnosticCriteria = listOf(
        "Hb < 13 g/dL (H) ou < 12 g/dL (F) -> Classement obligatoire selon VGM (FERRITINE si < 80 fL ; RÉTICULOCYTES si >= 80 fL)"
      ),
      clinicalForms = listOf(
        "Maladie de Biermer (gastrite auto-immune anti-facteur intrinsèque -> carence B12 + glossite + atteinte neurologique cordonale + risque adénocarcinome gastrique !)",
        "Microangiopathie Thrombotique MAT (PTT/SHU : Anémie hémolytique mécanique avec SCHIZOCYTES + Thrombopénie -> URGENCE VITALE ABSOLUE -> échanges plasmatiques sans transfuser de plaquettes !!)"
      ),
      evolutionAndPrognosis = "Dépend entièrement de la rapidité d'installation et de la cause : une hémorragie aiguë à 7 g/dL tue si non transfusée ; une carence martiale chronique très bien tolérée à 6 g/dL chez une jeune femme se corrige par du fer oral sans transfusion systématique si tolérance parfaite !",
      managementPrinciples = listOf(
        "TRAITER LA TOLÉRANCE AVANT LE CHIFFRE : Transfusion de culots globulaires (CGR) indiquée selon seuil Hb (< 7 g/dL sujet sain ; < 8–9 g/dL si coronarien/insuffisant cardiaque !) ET surtout signes de mauvaise tolérance (angor, dyspnée repos, choc) ! Toujours prélever bilan étiologique (B12/Folates/Ferritine/Coombs) AVANT la transfusion !",
        "Carence martiale : TRAITER LA CAUSE (ménorragies, tumeur/ulcère digestif !) + Fer oral (60–100 mg/j fer élément durant 3–6 mois pour reconstituer les réserves de ferritine, ou Fer IV si intolérance/malabsorption/MICI/IC !)",
        "Carence B12 (Biermer) : Vitamine B12 IM (ou forte dose orale) À VIE si Biermer/gastrectomie — attention à la crise réticulocytaire + hypokaliémie initiale si anémie très profonde ! JAMAIS donner de Folates (B9) seuls sans exclure B12 (masque l'anémie tout en aggravant la neuropathie B12 !!)",
        "Hémolyse auto-immune : Corticothérapie (+ acide folique B9 préventif dans toute hémolyse chronique !)"
      ),
      differentialDiagnoses = listOf(
        "Fausse anémie par HÉMODILUTION (grossesse physiologique T2–T3 -> seuil < 10.5–11 g/dL ; splénomégalie géante ; gammapathie monoclonale IgM)",
        "Hémorragie très récente (< 6–12 heures : l'Hb et l'hématocrite restent TROMPEUSEMENT NORMAUX tant que l'hémodilution compensatrice n'est pas faite -> ne jamais se fier à une Hb normale en plein choc hémorragique aigu !!)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Anémie microcytaire de l'homme de 65 ans",
      clinicalCaseScenario = "M. P., 65 ans (cas fictif pédagogique), sans signe digestif apparent ni saignement extériorisé, consulte pour fatigue à l'effort et pâleur. NFS : Hb 8.8 g/dL, VGM 69 fL, CCMH abaissée, Plaquettes 460 G/L, GB normaux. Ferritinémie effondrée à 6 µg/L, CRP < 3 mg/L.",
      clinicalCaseCorrection = "1) ANÉMIE MICROCYTAIRE HYPOCHROME PAR CARENCE MARTIALE SÉVÈRE (Ferritine à 6 µg/L + thrombocytose réactionnelle fréquente dans le déficit en fer ; pas d'inflammation car CRP normale).\n" +
        "2) RÈGLE ABSOLUE : Chez un homme de 65 ans (comme chez une femme ménopausée), une anémie ferriprive sans saignement visible est un CANCER COLORECTAL (ou lésion digestive haute) JUSQU'À PREUVE DU CONTRAIRE !\n" +
        "3) CAT : Prescrire EN PRIORITÉ une ENDOSCOPIE DIGESTIVE HAUTE (FOGD) ET UNE COLOSCOPIE TOTALE (+ biopsies duodénales si négatives -> maladie cœliaque) + supplémentation en fer oral ou IV après évaluation de tolérance cardiaque.",
      keyPoints = listOf(
        "VGM < 80 fL = FERRITINE + CRP d'abord ; Carence martiale chez l'homme ou femme ménopausée = COLOSCOPIE + FOGD OBLIGATOIRES !",
        "VGM >= 80 fL = RÉTICULOCYTES + FROTTIS SANGUIN d'abord (>= 120 G/L : Hémorragie ou Hémolyse ; < 120 G/L : B12/Folates/TSH puis Myélogramme)",
        "Triade d'Hémolyse = LDH haut + Bilirubine libre haute + Haptoglobine EFFONDRÉE -> faire Coombs + chercher Schizocytes (MAT !)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Arbre décisionnel biologique simplifié devant toute Anémie",
          headers = listOf("VGM", "Examen de 1ère ligne", "Résultat clé", "Diagnostics principaux"),
          rows = listOf(
            listOf("< 80 fL (Microcytaire)", "Ferritine + CRP", "Ferritine basse / CRP normale", "Carence martiale (FOGD + Coloscopie si H/ménopausée !)"),
            listOf("< 80 fL (Microcytaire)", "Ferritine + CRP", "Ferritine haute + CRP haute", "Anémie inflammatoire (VGM normal au début puis bas)"),
            listOf(">= 80 fL (Normo/Macro)", "Réticulocytes + Frottis", "Réticulocytes >= 120 G/L", "Hémorragie aiguë ou Hémolyse (LDH/Hapto/Coombs)"),
            listOf(">= 80 fL (Normo/Macro)", "Réticulocytes + B12/B9", "Réticulocytes < 120 G/L", "Carence B12/Folates, Hypothyroïdie, Alcool, Myélodysplasie")
          )
        )
      )
    )
  )
}
