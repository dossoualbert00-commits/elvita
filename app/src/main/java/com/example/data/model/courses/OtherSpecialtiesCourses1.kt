package com.example.data.model.courses

import com.example.data.model.SemiologyCategory

object OtherSpecialtiesCourses1 {
  val courses: List<MedicalCourse> = listOf(
    MedicalCourse(
      id = "course_cardio_sca",
      title = "Syndrome Coronarien Aigu (SCA ST+ et NSTEMI)",
      specialty = SemiologyCategory.CARDIOVASCULAR,
      subtitle = "Urgence coronaire absolue — « Time is muscle » : ECG < 10 minutes et revascularisation",
      definition = "Ischémie myocardique aiguë par rupture ou érosion d'une plaque d'athérome coronaire avec thrombose endoluminale totale (SCA avec sus-décalage persistant du segment ST = STEMI) ou subocclusive/distale (SCA sans sus-décalage ST = NSTEMI / angor instable).",
      epidemiology = "Urgence vitale majeure, 1ère cause de mort subite chez l'adulte (> 80 000 syndromes coronariens aigus/an en France). Pronostic directement lié au délai entre début des symptômes et désobstruction coronaire.",
      etiologiesAndRiskFactors = listOf(
        "Athérosclérose coronaire (95%) favorisée par les facteurs de risque cardiovasculaires majeurs : Tabac, Diabète, HTA, Dyslipidémie (LDL), Âge, Hérédité coronaire précoce",
        "Causes non athéromateuses (< 5%) : Dissection coronaire spontanée (femme jeune / post-partum !), spasme coronaire (Prinzmetal / cocaïne !!), embolie coronaire (endocardite, FA)"
      ),
      physiopathology = "Rupture de plaque instable à chape fibreuse fine -> activation plaquettaire et coagulation -> thrombus occlusif complet (ischémie transmurale -> sus-décalage ST puis onde Q de nécrose) ou incomplet (nécrose sous-endocardique avec libération de troponine = NSTEMI).",
      classificationAndPathology = "1) SCA ST+ (STEMI) : sus-décalage ST persistant >= 20 min dans >= 2 dérivations contiguës (>= 1 mm, ou >= 2–2.5 mm en V2–V3 ; ou BBG récent/critères de Sgarbossa, ou sous-décalage V1–V3 = miroir d'infarctus postérieur V7–V9 !). 2) SCA ST- : Troponine ultrasensible positive (NSTEMI) vs négative en cinétique (Angor instable).",
      clinicalSigns = "Douleur thoracique typique : MÉDIOTHORACIQUE RÉTROSTERNALE EN BARRE, CONSTRICTIVE (« serrement en étau »), intense, PROLONGÉE (>= 20 minutes), résistante à la Trinitrine sublinguale (si STEMI !), irradiant aux mâchoires, cou, épaules et bord interne du bras gauche, accompagnée de sueurs froides, nausées et angoisse de mort imminente. PIÈGES FRÉQUENTS : forme épigastrique pure avec vomissements (infarctus inférieur !), dyspnée/OAP brutal indolore chez le diabétique ou sujet âgé ! Rechercher signes d'IVG (crépitants, Killip), souffle d'IM aiguë ou signe d'IVD (infarctus VD associé à l'infarctus inférieur !).",
      paraclinicalExams = listOf(
        "ECG 18 DÉRIVATIONS (12 + V3R, V4R + V7, V8, V9) RÉALISÉ ET INTERPRÉTÉ EN < 10 MINUTES !! -> Élément décisionnel immédiat pour le SCA ST+ (ne JAMAIS attendre la troponine pour lancer la désobstruction d'un SCA ST+ !!)",
        "Troponine ultrasensible (H0 et H1/H3 en cinétique) pour le diagnostic des SCA ST- (NSTEMI)",
        "Bilan biologique parallèle : NFS, ionogramme, créatinine, hémostase, glycémie, bilan lipidique (EAL)",
        "Échocardiographie transthoracique (troubles de cinétique segmentaire, FEVG, complication mécanique : tamponnade, CIV, IM aiguë !) — ne doit pas retarder la coronarographie d'un STEMI !"
      ),
      diagnosticCriteria = listOf(
        "STEMI : Douleur angineuse + Sus-décalage persistant ST dans >= 2 dérivations contiguës -> Coronarographie / Thrombolyse immédiate sans attendre troponine",
        "NSTEMI : Douleur/ischémie + Pas de sus-décalage ST persistant + Élévation/cinétique significative de troponine ultrasensible"
      ),
      clinicalForms = listOf(
        "Infarctus inférieur (DII, DIII, aVF -> faire V3R/V4R pour chercher extension au VD et se méfier de BAV complet !)",
        "Infarctus postérieur vrai (sous-décalage V1–V3 avec grand R -> faire V7–V9 montrant le sus-décalage !!)",
        "Choc cardiogénique (Killip IV) et arrêt cardiaque sur fibrillation ventriculaire précoce"
      ),
      evolutionAndPrognosis = "Complications précoces : fibrillation ventriculaire (mort subite), BAV, choc cardiogénique, rupture cardiaque/CIV/IM aiguë, péricardite précoce. Tardives : insuffisance cardiaque ischémique, anévrisme VG, syndrome de Dressler.",
      managementPrinciples = listOf(
        "SCA ST+ (< 12h) : DÉSOSTRUCTION EN EXTRÊME URGENCE : Angioplastie coronaire primaire (si délai ponction <= 120 min) ; sinon FIBRINOLYSE IV préhospitalière immédiate (si absence de contre-indication et délai > 120 min !)",
        "Traitement antithrombotique urgent : Aspirine (150–300 mg charge) + Inhibiteur P2Y12 (Prasugrel ou Ticagrélor) + Anticoagulation (HNF/Énoxaparine/Bivalirudine) + Morphine si douleur intense (O2 uniquement si SpO2 < 90% !)",
        "SCA ST- (NSTEMI) : Stratification du risque (GRACE) -> Coronarographie en urgence (< 2h si instable/arythmie/douleur réfractaire ; < 24h si haut risque troponine/ECG)",
        "Prévention secondaire à vie (« BASIC ») : Bêtabloquant, Antiagrégant (double 12 mois puis aspirine à vie), Statine forte dose (objectif LDL < 0.55 g/L !), IEC (si FEVG <= 40% / HTA / diabète), Contrôle FDRCV + sevrage tabagique + réadaptation cardiaque !"
      ),
      differentialDiagnoses = listOf(
        "Dissection aortique (PIÈGE MORTEL si anticoagulée/thrombolysée à tort !! -> asymétrie pouls/TA > 20 mmHg, douleur migratrice déchirante dorsale, souffle IA -> Angioscanner aortique au moindre doute !)",
        "Embolie pulmonaire, Péricardite aiguë (sus-décalage CONCAVE diffus sans miroir + fievre/frottement), Myocardite, Takotsubo, RGO/Ulcère"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Douleur épigastrique piège avec sus-décalage inférieur",
      clinicalCaseScenario = "M. A., 59 ans (cas fictif pédagogique), diabétique de type 2 et tabagique 30 PA, arrive aux urgences pour une violente « douleur d'estomac » apparue il y a 45 minutes avec nausées et sueurs froides. PA 92/58 mmHg, FC 48/min, champs pulmonaires clairs. L'ECG 18 dérivations réalisé à 5 minutes montre un sus-décalage du segment ST de 4 mm en DII, DIII, aVF avec miroir en DI-aVL, et un sus-décalage de 2 mm en V4R !",
      clinicalCaseCorrection = "1) SCA ST+ (STEMI) INFÉRIEUR ÉTENDU AU VENTRICULE DROIT (sus-décalage DII, DIII, aVF + V4R !) compliqué de bradycardie sinusale/vagal ou BAV et d'hypotension (piège digestif classique chez le diabétique !).\n" +
        "2) NE PAS ATTENDRE la troponine et SURTOUT NE PAS DONNER DE DÉRIVÉS NITRÉS (contre-indiqués dans l'infarctus du VD car ils effondrent la précharge et aggravent le choc !!).\n" +
        "3) CAT immédiate : Appel SAMU/coronarographiste pour angioplastie primaire < 90 min, Aspirine + Ticagrélor/Prasugrel + HNF, remplissage prudent au NaCl 0.9% si hypotension sur infarctus VD (+ Atropine si bradycardie symptomatique).",
      keyPoints = listOf(
        "ECG 18 dérivations en < 10 minutes devant toute douleur thoracique ou épigastrique suspecte — JAMAIS attendre troponine pour un SCA ST+ !",
        "Toujours éliminer cliniquement une DISSECTION AORTIQUE avant anticoagulants/thrombolyse",
        "Infarctus inférieur -> faire V3R/V4R ; si extension VD -> ZÉRO dérivés nitrés + remplissage prudent !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Orientation ECG d'urgence : SCA ST+ vs Péricardite vs Dissection Aortique",
          headers = listOf("Pathologie", "Douleur", "ECG caractéristique", "Piège thérapeutique fatal"),
          rows = listOf(
            listOf("SCA ST+ (STEMI)", "Constrictive en barre, sueurs", "Sus-décalage CONVEXE systématisé + MIROIR", "Attendre la troponine (perte de myocarde !)"),
            listOf("Péricardite aiguë", "Inspiratoire, soulagée penché en avant", "Sus-décalage CONCAVE diffus SANS miroir + sous-PQ", "Thrombolyser / anticoaguler -> Hémopéricarde !"),
            listOf("Dissection aortique", "Déchirante migratrice vers le dos", "Souvent normal (sauf extension coronaire droite)", "Anticoaguler / thrombolyser -> Rupture mortelle !")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_cardio_ic",
      title = "Insuffisance Cardiaque Chronique et Aiguë",
      specialty = SemiologyCategory.CARDIOVASCULAR,
      subtitle = "Classification FEVG (rEF / mrEF / pEF) et les 4 piliers thérapeutiques de survie",
      definition = "Syndrome clinique complexe caractérisé par des symptômes typiques (dyspnée, orthopnée, asthénie) et/ou des signes de rétention hydrosodée (râles crépitants, OMI, turgescence jugulaire), dus à une anomalie structurelle ou fonctionnelle cardiaque abaissant le débit et/ou élevant les pressions intracardiaques.",
      epidemiology = "Problème majeur de santé publique (> 1.5 million de patients en France), 1ère cause d'hospitalisation après 65 ans avec réhospitalisations fréquentes et pronostic sévère (~50% de mortalité à 5 ans sans traitement optimal).",
      etiologiesAndRiskFactors = listOf(
        "Cardiopathie ISCHÉMIQUE (post-infarctus — cause n°1 de l'IC à FEVG réduite !)",
        "Cardiopathie HYPERTENSIVE (cause majeure d'IC à FEVG préservée chez le sujet âgé !)",
        "Valvulopathies (rétrécissement aortique serré, insuffisance mitrale), Troubles du rythme prolongés (FA rapide = « rythmocardiomyopathie » !)",
        "Cardiomyopathies dilatées/hypertrophiques/restrictives (génétiques, alcool, chimiothérapie anthracyclines/trastuzumab, myocardite, amylose TTR/AL chez le sujet âgé !!)"
      ),
      physiopathology = "Baisse du débit ou défaut de relaxation -> activation chronique délétère des systèmes neuro-hormonaux (Système Rénine-Angiotensine-Aldostérone SRAA + Système Sympathique) provoquant vasoconstriction, rétention hydrosodée, hypertrophie/fibrose et remodelage ventriculaire aggravant la défaillance.",
      classificationAndPathology = "Classification ESC selon la FEVG à l'ETT : 1) IC à FEVG RÉDUITE (HFrEF <= 40%) ; 2) IC à FEVG MODÉRÉMENT RÉDUITE (HFmrEF 41–49%) ; 3) IC à FEVG PRÉSERVÉE (HFpEF >= 50% avec anomalie diastolique/BNP). Gravité fonctionnelle selon la classe NYHA (I à IV).",
      clinicalSigns = "INSUFFISANCE CARDIAQUE GAUCHE : dyspnée d'effort (NYHA II–III) puis de repos (NYHA IV), ORTHOPNÉE (nombre d'oreillers), dyspnée paroxystique nocturne, toux nocturne ; auscultation : râles crépitants des bases en marée montante, galop B3, souffle d'IM fonctionnelle, choc de pointe dévié. INSUFFISANCE CARDIAQUE DROITE : TURGESCENCE JUGULAIRE à 45°, REFLUX HÉPATO-JUGULAIRE, hépatomégalie douloureuse lisse (« foie cardiaque »), OMI blancs mous déclives prenant le godet, prise de poids rapide, épanchement pleural/ascite.",
      paraclinicalExams = listOf(
        "BNP ou NT-proBNP (très forte valeur prédictive NÉGATIVE pour exclure une IC : BNP < 35 pg/mL ou NT-proBNP < 125 pg/mL en chronique ; < 100 / < 300 en aigu !)",
        "ÉCHOCARDIOGRAPHIE TRANSTHORACIQUE (ETT — EXAMEN CLÉ OBLIGATOIRE) : mesure de la FEVG (<= 40% vs >= 50%), pressions de remplissage, valvulopathies, HTAP, étiologie ischémique/hypertrophique/amylose",
        "ECG (Séquelle d'IDM, HVG, BBG large -> candidat resynchronisation !, FA) + Radio thorax (cardiomégalie, surcharge vasculaire/alvéolaire)",
        "Biologie complète : Ionogramme, Créatinine/DFG, NFS + BILAN MARTIAL COMPLET (Ferritine + CST -> recherche carence martiale fréquente à supplémenter par Fer IV !), TSH, HbA1c, bilan hépatique"
      ),
      diagnosticCriteria = listOf(
        "Symptômes/signes d'IC + Preuve objective de dysfonction cardiaque à l'ETT (et/ou élévation significative des peptides natriurétiques BNP/NT-proBNP)"
      ),
      clinicalForms = listOf(
        "Décompensation cardiaque aiguë / OAP (rechercher les facteurs déclenchants par l'acronyme « CHAMPIT » : SCA, HTA, Arythmie, Mécanique valvulaire, Embolie pulmonaire, Infection, Tamponnade / défaut observance !)",
        "IC à FEVG préservée (HFpEF) du sujet âgé hypertendu / obèse / diabétique (ou amylose TTR -> scintigraphie osseuse !)"
      ),
      evolutionAndPrognosis = "Transformée dans l'HFrEF grâce à l'association précoce des 4 piliers pharmacologiques (réduction majeure de la mortalité et des hospitalisations !).",
      managementPrinciples = listOf(
        "TRAITEMENT SYMPTOMATIQUE DE LA CONGESTION : Diurétique de l'anse (Furosémide / Bumétanide) adapté au poids et aux signes de surcharge + restriction sodée modérée + pesée régulière",
        "LES 4 PILIERS DE FOND DE L'IC À FEVG RÉDUITE (<= 40% — TOUS RECOMMANDÉS D'EMBLÉE !) : 1) IEC ou ARNI (Sacubitril/Valsartan) ; 2) Bêtabloquant cardiosélectif (Bisoprolol, Carvédilol, Métoprolol, Nébivolol) ; 3) Antagoniste des récepteurs aux minéralocorticoïdes ARM (Spironolactone / Éplérénone) ; 4) Inhibiteur SGLT2 (Dapagliflozine / Empagliflozine — indiqué dans TOUTE IC quelle que soit la FEVG !!)",
        "Traitements additionnels si indiqués : Fer IV si carence martiale (Ferritine < 100 ou 100–299 avec CST < 20%), Défibrillateur (DAI) / Resynchronisation (CRT) si FEVG <= 35% sous traitement optimal et QRS larges (BBG >= 130–150 ms)",
        "Éducation thérapeutique capitale (observance, signes d'alerte : prise poids > 2 kg en 3 jours, régime sans excès de sel, vaccinations, activité physique adaptée)"
      ),
      differentialDiagnoses = listOf(
        "BPCO / Asthme, Embolie pulmonaire, Insuffisance rénale avec surcharge, Cirrhose / Syndrome néphrotique (pour les OMI mais SANS turgescence jugulaire ni élévation pressions gauches !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Bilan initial d'une IC à FEVG réduite",
      clinicalCaseScenario = "M. D., 64 ans (cas fictif pédagogique), ancien infarctus antérieur, consulte pour dyspnée d'effort croissante (NYHA III) depuis 2 mois et œdèmes des chevilles apparus cette semaine (+3 kg). PA 128/78 mmHg, FC 78/min, crépitants fins des deux bases, turgescence jugulaire. ECG : onde Q en V1–V4 et BBG complet (QRS 155 ms). NT-proBNP 2450 pg/mL. ETT : FEVG 30%, hypokinésie antérieure. Créatinine 105 µmol/L, K+ 4.4 mmol/L, Ferritine 65 µg/L, CST 14%.",
      clinicalCaseCorrection = "1) Insuffisance cardiaque globale à FEVG RÉDUITE (HFrEF 30%) d'origine ischémique en poussée congestive modérée, avec CARENCE MARTIALE associée (Ferritine < 100 + CST < 20%).\n" +
        "2) Traitement urgent symptomatique : Furosémide pour décongestionner + supplémentation en FER INTRAVEINEUX (Carboxymaltose ferrique — améliore symptômes et réhospitalisations !).\n" +
        "3) Instauration rapide et titrage progressif des 4 PILIERS DE FOND : IEC (ou Sacubitril/Valsartan) + Bêtabloquant (à introduire hors congestion aiguë sévère) + Spironolactone + Gliflozine (Dapa/Empagliflozine). À 3 mois si FEVG <= 35% persistante avec BBG 155 ms : indication de Resynchronisation + DAI !",
      keyPoints = listOf(
        "NT-proBNP/BNP normaux excluent l'IC avec excellente VPN ; l'ETT confirme et classe la FEVG (<= 40% vs >= 50%)",
        "4 piliers vitaux de l'IC à FEVG réduite : IEC/ARNI + Bêtabloquant + ARM (Spironolactone) + iSGLT2 (Gliflozine)",
        "Gliflozines recommandées quelle que soit la FEVG ; toujours doser Ferritine + CST (carence martiale -> Fer IV !)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Les 4 Piliers Thérapeutiques de l'Insuffisance Cardiaque à FEVG réduite (<= 40%)",
          headers = listOf("Classe", "Exemples clés", "Effet majeur", "Précautions biologiques"),
          rows = listOf(
            listOf("IEC ou ARNI", "Ramipril ou Sacubitril/Valsartan", "Baisse mortalité + hospitalisations", "Surveiller K+, Créatinine, hypotension"),
            listOf("Bêtabloquant IC", "Bisoprolol, Carvédilol, Nébivolol", "Baisse mort subite + remodelage", "Introduire hors OAP aigu ; titrer lentement"),
            listOf("ARM", "Spironolactone, Éplérénone", "Anti-fibrose + baisse mortalité", "Risque HYPERKALIÉMIE + créat (surveiller K+)"),
            listOf("Inhibiteur SGLT2", "Dapagliflozine, Empagliflozine", "Efficace toute FEVG + néphroprotecteur", "Dose unique fixe, peu d'effet sur PA/K+")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_cardio_endocardite",
      title = "Endocardite Infectieuse (EI)",
      specialty = SemiologyCategory.CARDIOVASCULAR,
      subtitle = "Végétations valvulaires infectieuses — Hémocultures avant tout antibiotique et critères de Duke",
      definition = "Infection grave de l'endocarde valvulaire (valve native ou prothétique) ou du matériel intracardiaque (pacemaker/DAI) par un micro-organisme (bactérie ou champignon) formant des végétations septiques et des destructions valvulaires.",
      epidemiology = "Incidence ~35 cas/million/an, en hausse chez les sujets âgés porteurs de prothèses valvulaires (TAVI/chirurgie) ou dispositifs implantés. Mortalité hospitalière toujours lourde (~15–25%).",
      etiologiesAndRiskFactors = listOf(
        "Staphylocoques (Staphylococcus aureus ++ : formes aiguës fulminantes, toxicomanie IV -> valve tricuspide, cathéters !)",
        "Streptocoques oraux (viridans, mitis, sanguinis -> soins dentaires sur valvulopathie) et Streptococcus gallolyticus (ex-bovis -> IMPOSE COLOSCOPIE TOTALE SYSTÉMATIQUE pour rechercher un cancer colorectal !!)",
        "Entérocoques (sujet âgé, porte d'entrée urinaire ou digestive), Groupe HACEK, Coxiella burnetii / Bartonella (EI à hémocultures négatives !)",
        "Cardiopathies à HAUT RISQUE D'EI : Prothèse valvulaire, Antécédent d'EI, Cardiopathie congénitale cyanogène non réparée"
      ),
      physiopathology = "Lésion endothéliale sur flux turbulent (valvulopathie, prothèse) -> thrombus fibrino-plaquettaire stérile -> greffe bactérienne lors d'une bactériémie -> prolifération bactérienne protégée au sein de la végétation -> destruction valvulaire (fuite aiguë, abcès péri-annulaire) + emboles septiques systémiques/pulmonaires + complexes immuns circulants.",
      classificationAndPathology = "Critères diagnostiques ESC 2023 (Duke modifiés) combinant 2 Critères Majeurs (1) Hémocultures positives typiques >= 2 flacons distincts ; 2) Imagerie positive à l'ETT/ETO/TEP-TDM : végétation, abcès, pseudo-anévrisme, désinsertion de prothèse) et 5 Critères Mineurs (terrain prédisposant, fièvre > 38°C, phénomènes vasculaires emboliques, phénomènes immunologiques, microbiologie atypique).",
      clinicalSigns = "TOUTE FIÈVRE INEXPLIQUÉE CHEZ UN PORTEUR DE PROTHÈSE VALVULAIRE OU DE SOUFFLE EST UNE ENDOCARDITE JUSQU'À PREUVE DU CONTRAIRE !! Fièvre prolongée ou aiguë, altération de l'état général, apparition ou modification d'un SOUFFLE D'INSUFFISANCE VALVULAIRE (IA ou IM aiguë), splénomégalie. SIGNES PÉRIPHÉRIQUES CLASSIQUES (inconstants mais très évocateurs !) : faux panaris d'Osler (nodules rouges douloureux des pulpes digitales), placards érythémateux palmo-plantaires de Janeway (indolores), purpura pétéchial, hémorragies sous-unguéales en flammèches, taches de Roth au fond d'œil. Complication inaugurale fréquente : AVC ischémique ou hémorragique (embole septique / anévrisme mycotique !).",
      paraclinicalExams = listOf(
        "3 PAIRES D'HÉMOCULTURES (aérobie + anaérobie) espacées, RÉALISÉES AVANT TOUTE ANTIBIOTHÉRAPIE (règle d'or absolue hors choc septique imminent ! Prévenir le labo pour incubation prolongée si HACEK/Bartonella ; sérologies Coxiella/Bartonella si négatives)",
        "ÉCHOCARDIOGRAPHIE TRANSTHORACIQUE (ETT) PUIS TRANSŒSOPHAGIENNE (ETO) SYSTÉMATIQUE (l'ETO a une sensibilité bien supérieure pour voir végétations, abcès de l'anneau, perforations et prothèses !)",
        "Bilan d'extension embolique (même asymptomatique !) : TDM cérébral/IRM cérébrale + Scanner thoraco-abdomino-pelvien (emboles spléniques, rénaux, pulmonaires si cœur droit ; recherche anévrisme mycotique !)",
        "TEP-Scanner au 18-FDG (très utile pour confirmer EI sur prothèse valvulaire ou sonde de pacemaker !)",
        "Recherche et éradication de la PORTE D'ENTRÉE (panoramique dentaire + stomato, ou COLOSCOPIE OBLIGATOIRE si Streptococcus gallolyticus ou Entérocoque !)"
      ),
      diagnosticCriteria = listOf(
        "EI certaine (Duke) : 2 critères majeurs, OU 1 majeur + 3 mineurs, OU 5 mineurs ; EI possible : 1 majeur + 1 mineur ou 3 mineurs"
      ),
      clinicalForms = listOf(
        "EI sur valve prothétique précoce (< 1 an : Staph coagulase négative/S. aureus/fongique) vs tardive (> 1 an)",
        "EI du cœur droit (valve tricuspide chez usager de drogues IV : emboles pulmonaires septiques multiples avec cavitations pulmonaires !!)"
      ),
      evolutionAndPrognosis = "Risque d'insuffisance cardiaque aiguë réfractaire (première cause de décès et d'indication chirurgicale urgente !), de trouble de conduction (allongement du PR / BAV complet traduisant un ABCÈS DU SEPTUM sur EI aortique !!), et d'AVC embolique majeur (surtout si végétation > 10–15 mm !).",
      managementPrinciples = listOf(
        "Prise en charge multidisciplinaire en centre expert (« Endocarditis Team ») ; ZÉRO antibiotique à l'aveugle avant les 3 paires d'hémocultures (sauf choc septique) !",
        "Antibiothérapie bactéricide IV PROLONGÉE à fortes doses (4 à 6 semaines !) adaptée précisément à l'antibiogramme et à la CMI (ex: Pénicilline/Ceftriaxone +/- Gentamicine si strepto ; Oxacilline/Daptomycine/Vancomycine si staph)",
        "3 INDICATIONS CLASSIQUES DE CHIRURGIE CARDIAQUE EN URGENCE : 1) Insuffisance cardiaque aiguë / OAP réfractaire sur destruction valvulaire ; 2) Infection incontrôlée (abcès péri-annulaire, faux anévrisme, BAV sur EI aortique, fièvre/hémocs persistantes, végétation fongique) ; 3) Prévention embolique (végétation > 10 mm ayant déjà embolisé, ou très grosse > 15 mm mobile !)",
        "Antibioprophylaxie (Amoxicilline 2g 1h avant geste dentaire invasif) strictement réservée aux patients à HAUT RISQUE + hygiène bucco-dentaire et cutanée rigoureuse !"
      ),
      differentialDiagnoses = listOf(
        "Rhumatisme articulaire aigu, Endocardite marastique / lupique non bactérienne (Libman-Sacks), Myxome de l'oreillette (mime une EI fébrile emboligène !), Spondylodiscite ou foyer profond isolé chez un porteur de souffle"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Prothèse aortique fébrile avec PR allongé",
      clinicalCaseScenario = "M. B., 70 ans (cas fictif pédagogique), porteur d'une bioprothèse aortique posée il y a 3 ans, consulte pour fièvre à 38.6°C évoluant depuis 10 jours avec fatigue intense et apparition hier d'une baisse brutale de force du membre supérieur gauche résolutive en 2h (AIT) ainsi que d'un petit nodule rouge très douloureux sur la pulpe de l'index droit. ECG : rythme sinusal avec PR allongé récent à 280 ms (BAV 1er degré nouveau !).",
      clinicalCaseCorrection = "1) ENDOCARDITE INFECTIEUSE SUR BIOPROTHÈSE AORTIQUE TRÈS PROBABLE (fièvre + faux panaris d'Osler + AIT embolique + prothèse = terrain à haut risque).\n" +
        "2) L'apparition d'un NOUVEAU BAV (allongement du PR à 280 ms) sur une EI aortique est un DRAPEAU ROUGE ABSOLU D'ABCÈS PÉRI-ANNULAIRE DU SEPTUM !\n" +
        "3) CAT urgente : 3 paires d'hémocultures IMMÉDIATES avant tout antibiotique + ETT et ETO en urgence (+ TEP-TDM / IRM cérébrale pour bilan embolique) -> Antibiothérapie bactéricide IV probabiliste sur prothèse après prélèvements -> Avis chirurgical cardiaque URGENT (abcès péri-valvulaire + événement embolique).",
      keyPoints = listOf(
        "Fièvre + Souffle cardiaque (ou Prothèse valvulaire) = ENDOCARDITE INFECTIEUSE jusqu'à preuve du contraire -> 3 Hémocultures AVANT ATB + ETT/ETO !",
        "Hémoculture à Streptococcus gallolyticus (ex-bovis) ou Entérocoque = COLOSCOPIE OBLIGATOIRE (cancer colorectal !)",
        "Apparition d'un allongement du PR / BAV lors d'une EI aortique = ABCÈS SEPTAL PÉRI-ANNULAIRE -> Chirurgie urgente !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Portes d'entrée et germes clés de l'Endocardite Infectieuse",
          headers = listOf("Germe isolé", "Porte d'entrée / Terrain type", "Examen / Geste spécifique obligatoire"),
          rows = listOf(
            listOf("Streptocoques oraux", "Soins dentaires, mauvaise hygiène bucco-dentaire", "Panoramique dentaire + avulsion foyers"),
            listOf("Streptococcus gallolyticus", "Tube digestif (tumeur/polype colique !)", "COLOSCOPIE TOTALE obligatoire"),
            listOf("Staphylococcus aureus", "Cutanée, cathéter IV, toxicomanie IV (tricuspide !)", "Retrait matériel IV + recherche spondylodiscite"),
            listOf("Entérocoques", "Urinaire, biliaire, digestive (sujet âgé)", "Bilan uro-digestif / coloscopie")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_inf_paludisme",
      title = "Paludisme Simple & Paludisme Grave d'Importation",
      specialty = SemiologyCategory.INFECTIOUS,
      subtitle = "Toute fièvre au retour de zone d'endémie est un paludisme jusqu'à preuve du contraire — Artésunate IV",
      definition = "Parasitose fébrile aiguë potentiellement mortelle due à la multiplication intra-érythrocytaire d'un protozoaire du genre Plasmodium (essentiellement P. falciparum pour les formes graves), transmis par la piqûre nocturne d'un moustique femelle du genre Anopheles.",
      epidemiology = "Plus de 240 millions de cas et ~600 000 décès/an dans le monde (Afrique subsaharienne ++). Première cause de fièvre grave au retour des tropiques (~2 500–3 000 cas d'importation/an en France, dont ~90% liés à P. falciparum d'Afrique).",
      etiologiesAndRiskFactors = listOf(
        "5 espèces humaines : P. falciparum (LE PLUS FRÉQUENT ET QUASI SEUL RESPONSABLE DE MORTALITÉ / PALUDISME GRAVE, résistance chloroquinique généralisée !), P. vivax et P. ovale (accès bénins avec hypnozoïtes hépatiques -> rechutes tardives, nécessitant Primaquine !), P. malariae, P. knowlesi (Asie du Sud-Est)",
        "Facteurs de gravité / vulnérabilité : absence de prémunition (voyageur européen ou sujet originaire vivant en Europe depuis des années !), femme enceinte, jeune enfant, splénectomisé, retard diagnostique > 48h !"
      ),
      physiopathology = "Injection de sporozoïtes -> phase hépatique asymptomatique (7–14 jours) -> libération de mérozoïtes envahissant les hématies -> éclatement rythmique des hématies (fièvre, hémolyse aiguë, splénomégalie). Pour P. falciparum : expression de protéines d'adhérence créant une SÉQUESTRATION des hématies parasitées dans les capillaires profonds (cerveau = neuropaludisme, rein, poumon, placenta !).",
      classificationAndPathology = "Distinction vitale immédiate : Accès palustre SIMPLE (aucun critère de gravité clinique/biologique et pas de vomissements incoercibles) vs PALUDISME GRAVE À P. FALCIPARUM (au moins UN critère OMS de gravité -> réanimation et ARTÉSUNATE IV IMMÉDIAT !).",
      clinicalSigns = "Accès de primo-invasion : fièvre élevée brutale, céphalées, courbatures (« syndrome pseudo-grippal estival ») ET SOUVENT TROUBLES DIGESTIFS TROMPEURS (nausées, vomissements, diarrhée, douleur abdominale = piège faisant errer vers une gastro-entérite !). Accès palustre typique (frissons -> chaleur -> sueurs) inconstant au début. CRITÈRES CLINIQUES DE GRAVITÉ OMS À RECHERCHER D'EMBLÉE : TROUBLE DE LA CONSCIENCE (somnolence, confusion, Glasgow < 11 = NEUROPALUDISME), convulsions répétées, prostration extrême, détresse respiratoire / OAP lésionnel, état de choc (PAS < 80 mmHg), ictère clinique franc, saignement anormal, hémoglobinurie macroscopique (« urines porto » !).",
      paraclinicalExams = listOf(
        "EXAMEN DE RÉFÉRENCE EN EXTRÊME URGENCE (< 2 heures après prélèvement !!) : FROTTIS SANGUIN MINCE (identifie l'ESPÈCE et quantifie la PARASITÉMIE en % d'hématies parasitées !) + GOUTTE ÉPAISSE (plus sensible !) +/- Test de Diagnostic Rapide (TDR antigénique HRP2) en complément immédiat",
        "Bilan biologique OBLIGATOIRE immédiat de recherche de critères de gravité : NFS + PLAQUETTES (thrombopénie < 150 G/L Quasi CONSTANTE = excellente alerte mais pas un critère de gravité isolé ; Hb < 7 g/dL ou < 5 chez enfant = CRITÈRE GRAVE !), Créatinine (> 265 µmol/L = IRA grave !), Bilirubine totale (> 50 µmol/L), GLYCÉMIE (HYPOGLYCÉMIE < 2.2 mmol/L = GRAVE !!), GAZ DU SANG + LACTATES (> 5 mmol/L) + Bicarbonates (< 15 mmol/L = Acidose métabolique grave !), Bilan hépatique",
        "ECG systématique AVANT tout antipaludique (vérifier espace QTc !) ; si troubles de conscience fébriles : ponction lombaire après imagerie si doute avec méningite !"
      ),
      diagnosticCriteria = listOf(
        "Mise en évidence du Plasmodium sur frottis mince / goutte épaisse (ou TDR positif) ; Paludisme grave si >= 1 critère clinique ou biologique OMS (dont Parasitémie > 4% chez sujet non immun en France !)"
      ),
      clinicalForms = listOf(
        "Neuropaludisme (coma fébrile ou convulsions chez sujet revenant de zone tropicale — urgence absolue de réanimation !)",
        "Fièvre bilieuse hémoglobinurique (hémolyse intravasculaire aiguë massive + urines « porto » + insuffisance rénale anurique)"
      ),
      evolutionAndPrognosis = "Urgence mortelle en 24–48h en l'absence de diagnostic précoce et d'Artésunate IV dans les formes graves. La guérison sans séquelle est la règle si traité précocement (mais surveillance obligatoire de l'hémolyse retardée post-Artésunate à J7, J14, J21, J28 !).",
      managementPrinciples = listOf(
        "PALUDISME GRAVE (>= 1 critère OMS dont parasitémie > 4% ou vomissements empêchant la voie orale) : HOSPITALISATION EN RÉANIMATION / USC + ARTÉSUNATE INTRAVEINEUX (2.4 mg/kg à H0, H12, H24 puis toutes les 24h jusqu'à tolérance orale et parasitémie effondrée) puis RELAIS COMPLET PAR VOIE ORALE (ACT : Artéméther-Luméfantrine ou Dihydroartémisinine-Pipéraquine)",
        "PALUDISME SIMPLE À P. FALCIPARUM : Traitement oral par ACT en 1ère intention (Artéméther-Luméfantrine ou Dihydroartémisinine-Pipéraquine ; ou Atovaquone-Proguanil) — SURVEILLANCE À J3, J7 et J28 (frottis + Hb !)",
        "JAMAIS de Chloroquine pour P. falciparum ! Prévention voyageur : protection moustiques + chimioprophylaxie adaptée (Atovaquone-Proguanil ou Doxycycline)",
        "Mesures symptomatiques : resucrage immédiat si hypoglycémie, remplissage prudent (attention au risque d'OAP !), transfusion si Hb critique, épuration extrarénale si IRA anurique"
      ),
      differentialDiagnoses = listOf(
        "Arboviroses (Dengue, Chikungunya, Zika), Fièvre typhoïde, Hépatite virale A/E, Méningite bactérienne, Pyélonéphrite, Primo-infection VIH, Fièvre hémorragique virale (Ebola/Lassa si zone épidémique -> interrogatoire précis du séjour !)"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Troubles digestifs pièges et critères de gravité au retour d'Afrique",
      clinicalCaseScenario = "M. K., 35 ans (cas fictif pédagogique), né en France et revenu il y a 10 jours d'un séjour de 3 semaines en Côte d'Ivoire sans chimioprophylaxie, consulte pour fièvre à 39.8°C, céphalées intenses, diarrhée aqueuse et vomissements depuis 48h. Aux urgences, il devient confus (Glasgow 13) et présente un subictère. Plaquettes 42 G/L, Hb 10.2 g/dL, Glycémie 2.0 mmol/L, Bilirubine 68 µmol/L, Créatinine 140 µmol/L, Lactates 4.2 mmol/L. Frottis sanguin : P. falciparum à 6.5%.",
      clinicalCaseCorrection = "1) PALUDISME GRAVE D'IMPORTATION À PLASMODIUM FALCIPARUM réunissant plusieurs critères OMS majeurs : troubles neurologiques (Glasgow 13), hypoglycémie (< 2.2 mmol/L), hyperbilirubinémie (> 50 µmol/L), hyperparasitémie (> 4% = 6.5%), et vomissements.\n" +
        "2) Réanimation immédiate : Resucrage IV immédiat (G30%/G10%) pour l'hypoglycémie, admission en Réanimation/USC.\n" +
        "3) Traitement étiologique salvateur en extrême urgence : ARTÉSUNATE INTRAVEINEUX (2.4 mg/kg H0, H12, H24...), puis relais oral complet par ACT dès amélioration, et surveillance NFS/hémolyse jusqu'à J28 !",
      keyPoints = listOf(
        "RÈGLE D'OR : Toute fièvre (ou gastro-entérite !) dans les 3 mois suivant un retour de zone tropicale = FROTTIS + GOUTTE ÉPAISSE EN URGENCE !",
        "Thrombopénie fébrile au retour d'Afrique = très forte présomption de paludisme",
        "Paludisme grave (>= 1 critère OMS ou parasitémie > 4%) = Réanimation + ARTÉSUNATE IV EN URGENCE (suivi hémolyse post-artésunate jusqu'à J28 !)"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Principaux critères OMS de Paludisme Grave à Plasmodium falciparum (Adulte en France)",
          headers = listOf("Défaillance", "Critère clinique ou biologique exact"),
          rows = listOf(
            listOf("Neurologique", "Tout trouble de conscience (obnubilation, Glasgow < 11), convulsions répétées"),
            listOf("Respiratoire / Hémodynamique", "OAP lésionnel / SDRA ou État de choc (PAS < 80 mmHg)"),
            listOf("Métabolique / Rénal", "Acidose (HCO3- < 15 / pH < 7.35), Lactates > 5 mmol/L, Hypoglycémie < 2.2 mmol/L, Créat > 265 µmol/L"),
            listOf("Hémato / Hépatique / Parasitémie", "Anémie grave (< 7 g/dL), Hémorragie, Ictère/Bilirubine > 50 µmol/L, Parasitémie > 4 %")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_inf_meningite",
      title = "Méningites Aiguës Bactériennes & Purpura Fulminans",
      specialty = SemiologyCategory.INFECTIOUS,
      subtitle = "Urgence infectieuse absolue — Prise en charge chronométrée : Purpura = Ceftriaxone IV/IM avant tout !",
      definition = "Inflammation aiguë des méninges (leptoméninges) et du liquide cérébrospinal (LCS) liée à l'invasion par un agent infectieux bactérien (urgence vitale absolue : méningocoque, pneumocoque, Listeria) ou viral (le plus souvent bénin).",
      epidemiology = "Les méningites bactériennes restent redoutables (~10–20% de mortalité et séquelles neurologiques/auditives lourdes sans antibiothérapie ni corticothérapie immédiate). Chez l'adulte : Pneumocoque (n°1 > 25 ans) et Méningocoque (sujet jeune < 25 ans, foyers épidémiques) prédominent.",
      etiologiesAndRiskFactors = listOf(
        "Neisseria meningitidis (Méningocoque — jeune adulte, collectivité -> PURPURA FULMINANS + Déclaration Obligatoire + antibioprophylaxie des contacts !)",
        "Streptococcus pneumoniae (Pneumocoque — n°1 adulte > 25 ans, brèche ostéoméningée/trauma crânien, otite/sinusite/pneumonie, asplénie, alcoolisme !)",
        "Listeria monocytogenes (femme enceinte, sujet > 50 ans, immunodéprimé, alcoolisme -> tableau rhombencéphalitique avec atteinte des paires crâniennes !! -> résistante aux C3G -> imposer AMOXICILLINE + GENTAMICINE !!)",
        "Virus (Entérovirus > 80%, HSV-1/2, VZV -> tableau de méningo-encéphalite si signes de localisation/confusion -> Aciclovir IV !!)"
      ),
      physiopathology = "Colonisation nasopharyngée -> bactériémie -> franchissement de la barrière hémato-méningée -> multiplication rapide dans le LCS dépourvu de défenses humorales -> intense réaction inflammatoire provoquant œdème cérébral, HTIC, vascularite et thromboses cérébrales (ou choc endotoxique CIVD foudroyant dans le purpura fulminans méningococcique !).",
      classificationAndPathology = "Opposition cytologique et biochimique capitale du LCS : Méningite BACTÉRIENNE PURULENTE (liquide trouble, > 100–1000 éléments à prédominance PNN, hypoglycorachie < 0.4 × glycémie, hyperprotéinorachie > 1 g/L) vs VIRALE (liquide clair, lymphocytaire ou panaché au tout début, normoglycorachie, protéinorachie < 1 g/L) vs LISTÉRIA / TUBERCULOSE (liquide clair panaché/lymphocytaire + HYPOGLYCORACHIE !!).",
      clinicalSigns = "SYNDROME MÉNINGÉ FÉBRILE : Fièvre élevée + CÉPHALÉES intenses en casque diffuses rebelles aux antalgiques + PHOTOPHOBIE / PHONOPHOBIE + VOMISSEMENTS faciles en jet + RAIDEUR DE NUQUE douloureuse invincible à la flexion passive (signes de Kernig et de Brudzinski !), patient couché « en chien de fusil » dos à la lumière. SIGNES DE GRAVITÉ / URGENCE EXTRÊME : TOUT ÉLÉMENT PURPURIQUE NÉCROTIQUE OU ECCHYMOTIQUE >= 3 mm NE S'EFFAÇANT PAS À LA VITROPRESSION = PURPURA FULMINANS !! + Troubles de conscience/coma, crises convulsives, déficit neurologique focal, signes d'engagement (mydriase, bradycardie/HTA) ou choc septique.",
      paraclinicalExams = listOf(
        "RECHERCHE IMMÉDIATE DE PURPURA EN DÉSHABILLANT INTÉGRALEMENT LE PATIENT (sous-vêtements compris !)",
        "PONCTION LOMBAIRE (PL) EN URGENCE + HÉMOCULTURES (mais CONTRE-INDIQUÉE AVANT UN SCANNER CÉRÉBRAL SI ET SEULEMENT SI : signes de localisation neurologique, crises épileptiques focales ou récentes, troubles de conscience sévères Glasgow <= 11, immunodépression sévère ou suspicion d'engagement -> DANS CE CAS : HÉMOCULTURES + ANTIBIOTIQUE + CORTICOÏDES IMMÉDIATS AVANT LE SCANNER !!)",
        "Analyse du LCS : Cytologie (PNN vs lymphocytes), Biochimie (Protéinorachie, Glycorachie comparée à glycémie veineuse simultanée, Lactates > 3.2 mmol/L), Direct Gram + Culture + Antibiogramme + PCR multiplex / pneumocoque / méningocoque / HSV",
        "Bilan sanguin : Glycémie simultanée obligatoire, NFS, CRP/PCT, Ionogramme, Créatinine, Hémostase (CIVD si purpura !)"
      ),
      diagnosticCriteria = listOf(
        "Syndrome méningé fébrile + Analyse du LCS (pléiocytose > 5–10/mm³, hypoglycorachie et hyperprotéinorachie dans les formes bactériennes) + Identification microbiologique (Direct/Culture/PCR ou Hémocultures)"
      ),
      clinicalForms = listOf(
        "PURPURA FULMINANS (urgence préhospitalière absolue : fièvre + >= 1 tache purpurique nécrotique/ecchymotique >= 3 mm s'étendant -> INJECTION IMMÉDIATE DE CEFTRIAXONE IV ou IM avant tout transport et sans PL !!)",
        "Rhombencéphalite à Listeria (installation progressive sur quelques jours + fièvre + atteinte des nerfs crâniens / ataxie cérébelleuse chez sujet âgé/enceinte/immunodéprimé)",
        "Méningo-encéphalite herpétique (fièvre + troubles comportement/confusion + hallucinations/aphasie + crises temporales -> ACICLOVIR IV IMMÉDIAT sans attendre IRM/PL !)"
      ),
      evolutionAndPrognosis = "Chaque heure de retard antibiotique augmente exponentiellement la mortalité et les séquelles (surdité définitive post-pneumocoque, amputations post-purpura fulminans, épilepsie). La Dexaméthasone précoce réduit de moitié la mortalité et la surdité dans la méningite à pneumocoque !",
      managementPrinciples = listOf(
        "1) PURPURA FULMINANS : CEFTRIAXONE 1–2g IV/IM IMMÉDIATE dès la constatation (à domicile/ambulance) + Appel SAMU/Réa (aucune PL avant stabilisation !)",
        "2) MÉNINGITE BACTÉRIENNE SANS PURPURA : PL immédiate si pas de CI à la PL -> DÉXAMÉTHASONE IV (10 mg avant ou en même temps que la 1ère dose d'ATB !) + C3G MÉNINGÉE FORTE DOSE (Ceftriaxone 100 mg/kg/j ou Céfotaxime 300 mg/kg/j !)",
        "3) AJOUTER AMOXICILLINE + GENTAMICINE si sujet > 50–60 ans, femme enceinte, immunodéprimé ou signes rhombencéphalitiques (couvrir LISTÉRIA impérativement car C3G totalement inactives sur Listeria !!)",
        "4) Isolement GOUTTELETTES si méningocoque suspecté (24h après début C3G) + Déclaration Obligatoire ARS + Antibioprophylaxie des sujets contacts proches par Rifampicine 2 jours (ou Ciprofloxacine / Ceftriaxone) + Recherche porte d'entrée ORL / brèche si pneumocoque !"
      ),
      differentialDiagnoses = listOf(
        "Hémorragie sous-arachnoïdienne (début brutal « en coup de tonnerre », apyrétique au début -> TDM cérébral d'emblée !)",
        "Méningite virale bénigne, Neuropaludisme, Abcès cérébral, Méningisme réactionnel d'une pyélonéphrite ou pneumonie de l'enfant"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Fièvre, raideur de nuque et déficit focal",
      clinicalCaseScenario = "M. L., 66 ans (cas fictif pédagogique), éthylique chronique avec otite moyenne négligée depuis 5 jours, est amené confus (Glasgow 10) avec fièvre à 39.7°C, raideur de nuque nette et hémiparésie droite débutante. Pas de purpura. Glycémie capillaire normale.",
      clinicalCaseCorrection = "1) Méningo-encéphalite / méningite bactérienne grave présumée à Pneumocoque (otite porte d'entrée + alcoolisme) OU Listéria (âge > 50 ans + alcoolisme), avec CONTRE-INDICATION FORMELLE À LA PL D'EMBLÉE (car Glasgow <= 11 + déficit moteur focal = hémiparésie droite -> risque d'engagement cérébral !).\n" +
        "2) RÈGLE D'OR : NE PAS ATTENDRE LE SCANNER POUR TRAITER ! Faire immédiatement 2 hémocultures puis injecter SUR-LE-CHAMP : DEXAMÉTHASONE 10 mg IV + CEFOTAXIME/CEFTRIAXONE forte dose méningée + AMOXICILLINE + GENTAMICINE (car > 50 ans / terrain listérien -> couvrir Pneumocoque ET Listéria !) +/- Aciclovir si doute encéphalite.\n" +
        "3) Réaliser ensuite le SCANNER CÉRÉBRAL EN URGENCE puis la PL seulement si absence d'effet de masse/engagement.",
      keyPoints = listOf(
        "TOUJOURS déshabiller le patient pour chercher un PURPURA : si purpura nécrotique >= 3 mm -> Ceftriaxone IV/IM IMMÉDIATEMENT avant toute PL ou transport !",
        "Contre-indication à la PL d'emblée (déficit focal, convulsions, Glasgow <= 11, immunodépression) -> Hémocultures + Corticoïde + ATB D'ABORD -> puis Scanner -> puis PL !",
        "C3G ne couvre JAMAIS la Listéria -> Toujours associer AMOXICILLINE (+ Gentamicine) si sujet > 50 ans, enceinte, immunodéprimé ou liquide clair hypoglycorachique !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Orientation du Liquide Cérébro-Spinal (LCS) à la Ponction Lombaire",
          headers = listOf("Étiologie", "Aspect & Cellules", "Glycorachie / Glycémie", "Protéinorachie"),
          rows = listOf(
            listOf("Méningite Bactérienne (Pneumo/Méningo)", "Trouble, > 100–1000/mm³ (> 50% PNN)", "EFFONDRÉE (< 0.4 × glycémie)", "Très élevée (> 1 g/L)"),
            listOf("Méningite Virale", "Clair, 10–500/mm³ (Lymphocytaire)", "NORMALE (> 0.5 × glycémie)", "Normale ou modérée (< 1 g/L)"),
            listOf("Listériose ou Tuberculose", "Clair (ou opalescent), Lympho/Panaché", "ABAISSÉE (< 0.4–0.5 × glycémie)", "Élevée (> 1 g/L)")
          )
        )
      )
    ),
    MedicalCourse(
      id = "course_neuro_avc",
      title = "Accidents Vasculaires Cérébraux (AVC Ischémique & Hémorragique)",
      specialty = SemiologyCategory.NEUROLOGICAL,
      subtitle = "Déficit neurologique focal BRUTAL — Imagerie cérébrale immédiate et thrombolyse < 4h30",
      definition = "Déficit neurologique focal d'apparition BRUTALE (en quelques secondes ou minutes) d'origine vasculaire, dû soit à une ischémie artérielle cérébrale (AVC ischémique — 80–85%), soit à une rupture vasculaire parenchymateuse (hématome intracérébral — 15–20%). Un AIT régresse totalement sans lésion visible à l'IRM de diffusion.",
      epidemiology = "1ère cause de handicap moteur acquis de l'adulte, 2e cause de démence, 3e cause de mortalité (~140 000–150 000 AVC/an en France). Urgence thérapeutique absolue (« Time is brain » : 2 millions de neurones perdus par minute !).",
      etiologiesAndRiskFactors = listOf(
        "FACTEUR DE RISQUE MAJEUR N°1 (Ischémique ET Hémorragique) : HYPERTENSION ARTÉRIELLE (HTA) ! (+ tabac, diabète, dyslipidémie, sédentarité)",
        "Étiologies d'AVC ISCHÉMIQUE : Macroangiopathie (athérome bifurcation carotidienne / sténose intracrânienne), Cardio-embolique (FIBRILLATION AURICULAIRE ++, thrombus VG, valve mécanique, endocardite, FOP chez sujet jeune !), Microangiopathie (lacunes sur HTA), Causes sujet jeune = DISSECTION ARTÉRIELLE CERVICALE (carotide/vertébrale + syndrome de Claude Bernard-Horner/cervicalgie !)",
        "Étiologies d'AVC HÉMORRAGIQUE : Rupture de micro-anévrismes profonds sur HTA CHRONIQUE (noyaux gris centraux, thalamus, protubérance, cervelet !), Angiopathie amyloïde lobaire (sujet âgé > 70 ans), Surdosage anticoagulant (AVK/AOD !), Malformation artério-veineuse / cavernome / tumeur hémorragique"
      ),
      physiopathology = "Occlusion artérielle -> noyau central de nécrose irréversible entouré d'une ZONE DE PÉNOMBRE ISCHÉMIQUE encore viable mais menacée si la perfusion n'est pas restaurée rapidement par thrombolyse IV (< 4h30) et/ou thrombectomie mécanique (< 6h à 24h selon imagerie de perfusion !).",
      classificationAndPathology = "Territoire CAROTIDIEN (artère cérébrale moyenne / sylvienne : hémiparésie/hémiplégie brachio-faciale controlatérale, aphasie si hémisphère majeur gauche, héminégligence si mineur droit, HLH ; artère cérébrale antérieure : déficit prédominant au membre inférieur !). Territoire VERTÉBRO-BASILAIRE (ataxie, vertige, diplopie, syndromes alternes, hémianopsie latérale homonyme si artère cérébrale postérieure, coma si tronc basilaire !).",
      clinicalSigns = "DÉBUT BRUTAL D'UN DÉFICIT FOCAL (alerte « FAST / Vite » : asymétrie du Visage / paralysie faciale, faiblesse d'un Bras/jambe = hémiparésie, Trouble de la parole = aphasie ou dysarthrie). Aucune différence clinique fiable ne permet de distinguer un AVC ischémique d'un hématome cérébral sans imagerie ! Score clinique standardisé de gravité et de suivi : SCORE NIHSS. Vérifier heure exacte de début (ou « dernière heure vu asymptomatique » si déficit au réveil !!), glycémie capillaire immédiate (éliminer une HYPOGLYCÉMIE mimant un AVC !!), TA, température et troubles de déglutition !",
      paraclinicalExams = listOf(
        "GLYCÉMIE CAPILLAIRE IMMÉDIATE dès les 1ères minutes (tout déficit neurologique brutal peut être une hypoglycémie curable immédiatement !)",
        "IMAGERIE CÉRÉBRALE EN EXTRÊME URGENCE (< 25 min après arrivée) pour séparer Ischémie vs Hémorragie : IRM CÉRÉBRALE EN SÉQUENCES DE DIFFUSION/FLAIR/T2*/TOF (examen de référence, voit l'ischémie dès les premières minutes + thrombus + sang en T2*) OU SCANNER CÉRÉBRAL SANS INJECTION + ANGIOSCANNER (élimine hémorragie immédiatement et détecte occlusion proximale de gros tronc !)",
        "Bilan étiologique systématique : ECG + Télémétrie / Holter (rechercher FA paroxystique !), Écho-Doppler TSA / Angioscanner TSA (sténose carotide > 50–70% -> endartériectomie rapide sous 15 jours !), ETT +/- ETO, EAL, HbA1c, NFS, bilan hémostase"
      ),
      diagnosticCriteria = listOf(
        "Déficit neurologique focal brutal + Imagerie cérébrale distinguant l'AVC ischémique (hypersignal Diffusion/hypodensité) vs Hématome intracérébral (hyperdensité spontanée TDM / anomalie T2*)"
      ),
      clinicalForms = listOf(
        "Accident Ischémique Transitoire (AIT : déficit brutal régressif complètement avec IRM de diffusion NORMALE -> URGENCE ABSOLUE avec risque d'AVC constitué de 10% dans la semaine -> score ABCD2 + bilan TSA/ECG/IRM le jour même + Aspirine immédiate dès l'hémorragie exclue !)",
        "Syndrome de Wallenberg (infarctus latéral du bulbe sur dissection vertébrale : grand vertige rotatoire + vomissements + syndrome de Claude Bernard-Horner + anesthésie thermo-algique croisée + dysphagie/voix bitonale !)"
      ),
      evolutionAndPrognosis = "Complications précoces : extension de l'infarctus (œdème cérébral malin sylvien -> craniectomie décompressive < 48h si sujet jeune !), transformation hémorragique, pneumopathie d'inhalation (tester déglutition AVANT toute boisson/comprimé !!), TVP/EP, escarres, dépression.",
      managementPrinciples = listOf(
        "ALERTE « FILIÈRE AVC / APPEL 15 » -> Admission directe en Unité Neuro-Vasculaire (UNV) !",
        "AVC ISCHÉMIQUE AIGU (< 4h30) : THROMBOLYSE INTRAVEINEUSE (Altéplase ou Ténectéplase) en l'absence de contre-indication + THROMBECTOMIE MÉCANIQUE endovasculaire en urgence (< 6h, voire jusqu'à 24h si tissu récupérable !) en cas d'occlusion proximale d'un gros tronc (carotide interne, M1 sylvienne, tronc basilaire) !",
        "GESTION DE LA TENSION ARTÉRIELLE (RÈGLE CRITIQUE) : DANS L'AVC ISCHÉMIQUE NON THROMBOLYSÉ, RESPECTER L'HYPERTENSION PROTECTRICE jusqu'à 220/120 mmHg (NE PAS BAISSER LA TA brutalement car cela aggrave la zone de pénombre !!) ; si thrombolyse prévue : abaisser prudemment < 185/110 mmHg ; dans l'HÉMATOME CÉRÉBRAL : baisser la PAS cible rapidement vers ~140 mmHg (+ antagonisation immédiate par PPSB/Vitamine K ou idarucizumab/andexanet si sous anticoagulant !!)",
        "NE RIEN DONNER PAR LA BOUCHE (ni eau ni comprimé !) avant un TEST DE DÉGLUTITION ; Aspirine dès que l'hémorragie est exclue (après 24h si thrombolyse !); prévention secondaire ciblée (antiagrégant + statine si athérome ; anticoagulant si FA introduit selon taille infarctus)"
      ),
      differentialDiagnoses = listOf(
        "HYPOGLYCÉMIE (toujours dextro d'abord !), Crise épileptique partielle / déficit post-critique de Todd, Aura migraineuse (installation progressive « en tache d'huile » sur 15–20 min !), Tumeur cérébrale, Poussée de SEP, Conversion fonctionnelle"
      ),
      clinicalCaseTitle = "Cas clinique (cas fictif pédagogique) : Hémiplégie droite et aphasie à H+1h30",
      clinicalCaseScenario = "Mme F., 69 ans (cas fictif pédagogique), sans antécédent hémorragique ni chirurgie récente, présente brutalement à 10h00 devant son mari une impossibilité de parler (aphasie motrice) et une paralysie complète du bras droit et de la face droite. Elle arrive en UNV à 11h15. Glycémie capillaire 1.15 g/L. PA 172/94 mmHg, FC 96/min irrégulière (FA non connue !), NIHSS = 14. L'IRM cérébrale immédiate (11h30) montre un hypersignal Diffusion sylvien superficiel gauche avec FLAIR encore négatif, pas d'hémorragie en T2*, et un arrêt brutal de l'artère cérébrale moyenne gauche M1 au TOF !",
      clinicalCaseCorrection = "1) AVC ISCHÉMIQUE SYLVIEN GAUCHE AIGU SUR OCCLUSION PROXIMALE DE M1, probable origine cardio-embolique (FA inaugurale), vu à H+1h30 (< 4h30) avec mismatch Diffusion+/FLAIR-.\n" +
        "2) NE PAS BAISSER sa TA à 172/94 mmHg (elle est déjà < 185/110 mmHg requise pour la thrombolyse !).\n" +
        "3) Double urgence immédiate : THROMBOLYSE IV (Altéplase/Ténectéplase immédiate car < 4h30 et pas de CI) + TRANSFERT IMMÉDIAT EN NEURORADIOLOGIE INTERVENTIONNELLE POUR THROMBECTOMIE MÉCANIQUE (car occlusion proximale de M1 !) -> puis anticoagulation différée en UNV selon l'étendue finale de l'infarctus.",
      keyPoints = listOf(
        "Déficit neurologique focal brutal = Glycémie capillaire + Appel 15 / UNV + Imagerie cérébrale (IRM ou TDM) en urgence",
        "Ne JAMAIS faire baisser la TA brutalement dans un AVC ischémique (respecter jusqu'à 220/120 hors thrombolyse, < 185/110 si thrombolyse !)",
        "< 4h30 = Thrombolyse IV ; Occlusion proximale gros tronc (carotide/M1/basilaire) = Thrombectomie mécanique urgente ; Test de déglutition avant tout verre d'eau !"
      ),
      summaryTables = listOf(
        CourseSummaryTable(
          title = "Gestion urgente comparée : AVC Ischémique vs Hématome Intracérébral",
          headers = listOf("Paramètre", "AVC Ischémique", "Hématome Intracérébral"),
          rows = listOf(
            listOf("Imagerie TDM sans injection", "Normale au début ou hypodensité tardive", "Hyperdensité spontanée immédiate"),
            listOf("Cible tensionnelle aiguë", "Respecter jusqu'à 220/120 (< 185/110 si thrombolyse)", "Abaisser rapidement vers PAS ~140 mmHg"),
            listOf("Traitement spécifique urgent", "Thrombolyse IV (< 4h30) + Thrombectomie (si gros tronc)", "Antagonisation urgente si sous anticoagulant + avis neurochir si cervelet/jeune")
          )
        )
      )
    )
  )
}
