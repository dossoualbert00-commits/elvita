#!/usr/bin/env python3
"""
Générateur du Guide de Tutoriels Utilisateurs EL VITA (PDF Multi-pages Haute Qualité)
Explique concrètement l'utilité clinique de chaque module et guide l'utilisateur pas à pas.
"""

import os
from generate_presentation_pdf import (
    PDFDocument, PAGE_W, PAGE_H,
    NAVY, NAVY_MID, BLUE, BLUE_LIGHT, BLUE_PALE,
    TEAL, TEAL_PALE, GREEN, GREEN_DARK, GREEN_PALE,
    ORANGE, ORANGE_PALE, RED, RED_PALE,
    TEXT_DARK, TEXT_BODY, TEXT_MUTED, BORDER, BG_LIGHT, WHITE,
    section_heading, bullet_item, wrap_text, text_width
)


def draw_tutorial_header_footer(pdf: PDFDocument, page_num: int, total_pages: int, tag: str):
    pdf.rect(0, 0, PAGE_W, 44, fill=NAVY)
    pdf.rect(0, 44, PAGE_W, 3, fill=TEAL)
    pdf.draw_image('logo', 36, 9, 26, 26)
    pdf.draw_text("EL VITA — TUTORIELS", 70, 21, size=9.6, color=WHITE, font='F2')
    pdf.draw_text("Guide pratique au lit du malade", 70, 33, size=7.1, color=BLUE_LIGHT, font='F1')
    pdf.draw_text(tag.upper(), PAGE_W - 36, 26, size=7.2, color=BLUE_LIGHT, font='F2', align='right')

    pdf.line(36, PAGE_H - 36, PAGE_W - 36, PAGE_H - 36, stroke=BORDER, lw=0.7)
    pdf.draw_text("EL VITA — Tutoriels Utilisateurs (Android & iPhone) | Albert DOSSOU (dossoualbert00@gmail.com)", 36, PAGE_H - 22, size=7.3, color=TEXT_MUTED, font='F1')
    pdf.draw_text(f"Page {page_num} / {total_pages}", PAGE_W - 36, PAGE_H - 22, size=7.8, color=NAVY, font='F2', align='right')


def step_card(pdf: PDFDocument, x: float, y: float, w: float, num: str, title: str, body: str, accent=BLUE, bg=BG_LIGHT) -> float:
    lines = wrap_text(body, w - 50, 8.2, 'F1')
    h = max(48.0, 26 + len(lines) * 11.5 + 8)
    pdf.rounded_rect(x, y, w, h, r=8, fill=bg, stroke=BORDER, lw=0.8)
    pdf.rect(x, y, 4, h, fill=accent)
    pdf.circle(x + 22, y + 20, 10.5, fill=accent)
    pdf.draw_text(num, x + 22, y + 23.5, size=8.5, color=WHITE, font='F2', align='center')
    pdf.draw_text(title, x + 40, y + 18, size=9.2, color=NAVY, font='F2')
    pdf.draw_paragraph(body, x + 40, y + 32, w - 50, size=8.1, leading=11.4, color=TEXT_BODY, font='F1')
    return y + h + 8


def utility_box(pdf: PDFDocument, x: float, y: float, w: float, title: str, text: str, bg=TEAL_PALE, stroke=TEAL, title_col=TEAL) -> float:
    lines = wrap_text(text, w - 24, 8.2, 'F1')
    h = 26 + len(lines) * 11.5 + 8
    pdf.rounded_rect(x, y, w, h, r=8, fill=bg, stroke=stroke, lw=1.0)
    pdf.draw_text(title, x + 12, y + 17, size=8.8, color=title_col, font='F2')
    pdf.draw_paragraph(text, x + 12, y + 31, w - 24, size=8.1, leading=11.4, color=TEXT_DARK, font='F1')
    return y + h + 10


def build_tutorials_pdf():
    pdf = PDFDocument()
    for name, fname in [
        ('logo', 'logo.jpg'),
        ('founder', 'founder.jpg'),
        ('banner', 'banner.jpg'),
    ]:
        pdf.load_image(name, os.path.join('/tmp/tutorial_assets', fname))

    TOTAL_PAGES = 6

    # =========================================================================
    # PAGE 1 : COUVERTURE — À QUOI SERT EL VITA & PARCOURS EXPRESS 3 MINUTES
    # =========================================================================
    pdf.new_page()
    pdf.rect(0, 0, PAGE_W, 250, fill=NAVY)
    pdf.rect(0, 250, PAGE_W, 5, fill=TEAL)

    pdf.rounded_rect(36, 26, 240, 20, r=10, fill=NAVY_MID, stroke=TEAL, lw=1.0)
    pdf.draw_text("MANUEL PRATIQUE & TUTORIELS UTILISATEURS", 156, 39.5, size=7.6, color=BLUE_LIGHT, font='F2', align='center')

    pdf.rounded_rect(36, 58, 62, 62, r=12, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=1.3)
    pdf.draw_image('logo', 40, 62, 54, 54)

    pdf.draw_text("COMPRENDRE & UTILISER EL VITA", 112, 82, size=20, color=WHITE, font='F2')
    pdf.draw_text("Votre Compagnon Clinique & Pédagogique — 100% Hors-Ligne", 112, 102, size=10.0, color=BLUE_LIGHT, font='F2')
    pdf.draw_text("Guide étape par étape pour les externes, étudiants en médecine et internes", 112, 118, size=8.6, color=(203/255, 213/255, 225/255), font='F1')

    pdf.line(36, 136, PAGE_W - 36, 136, stroke=(30/255, 58/255, 138/255), lw=0.9)
    pdf.draw_paragraph(
        "À quoi sert concrètement EL VITA sur le terrain ? Ce guide vous montre comment gagner du temps en stage, éviter les pièges en garde, centraliser vos observations et réviser efficacement sans aucune connexion Internet.",
        36, 154, PAGE_W - 72, size=9.2, leading=13.5, color=(226/255, 232/255, 240/255), font='F1'
    )

    pdf.rounded_rect(36, 204, 168, 22, r=11, fill=GREEN_DARK, stroke=GREEN, lw=0.9)
    pdf.draw_text("ZÉRO INTERNET REQUIS", 120, 218.5, size=7.6, color=WHITE, font='F2', align='center')
    pdf.rounded_rect(214, 204, 180, 22, r=11, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=0.9)
    pdf.draw_text("SECRET MÉDICAL PRÉSERVÉ", 304, 218.5, size=7.6, color=BLUE_LIGHT, font='F2', align='center')
    pdf.rounded_rect(404, 204, 155, 22, r=11, fill=NAVY_MID, stroke=TEAL, lw=0.9)
    pdf.draw_text("ANDROID & IPHONE", 481.5, 218.5, size=7.6, color=WHITE, font='F2', align='center')

    y = 272
    pdf.draw_text("POURQUOI EL VITA EXISTE ? (3 PROBLÈMES RÉELS, 1 SOLUTION)", 36, y, size=11.0, color=NAVY, font='F2')
    y += 14

    problems = [
        ("1. Coupures réseau en salle & en garde",
         "Dans les services hospitaliers ou de nuit, Internet passe mal. EL VITA embarque 304 fiches, 31 cours, 90 QCM, 23 fiches prévention et 5 calculateurs biologiques directement dans votre poche.",
         BLUE, BLUE_PALE),
        ("2. Dispersion des formules & notes",
         "Au lieu de noter les constantes sur un papier, de chercher Cockcroft-Gault sur un site et de rédiger l'observation ailleurs, EL VITA centralise tout dans un seul dossier patient sur votre téléphone.",
         TEAL, TEAL_PALE),
        ("3. Respect strict du secret médical",
         "Aucune donnée patient ne part sur un serveur distant. Vos dossiers restent 100% locaux sur l'appareil (et il est conseillé d'utiliser des initiales ou pseudonymes).",
         GREEN_DARK, GREEN_PALE),
    ]
    cw = (PAGE_W - 72 - 20) / 3
    for i, (pt, pd, ac, bgc) in enumerate(problems):
        cx = 36 + i * (cw + 10)
        pdf.rounded_rect(cx, y, cw, 118, r=8, fill=bgc, stroke=BORDER, lw=0.8)
        pdf.rect(cx, y, cw, 4, fill=ac)
        pdf.draw_paragraph(pt, cx + 10, y + 18, cw - 20, size=8.4, leading=11.0, color=NAVY, font='F2')
        pdf.draw_paragraph(pd, cx + 10, y + 44, cw - 20, size=7.5, leading=10.5, color=TEXT_BODY, font='F1')

    y += 134
    pdf.draw_text("PARCOURS DÉCOUVERTE EN 3 MINUTES (VOTRE PREMIER ESSAI)", 36, y, size=11.0, color=NAVY, font='F2')
    y += 12

    y = step_card(pdf, 36, y, PAGE_W - 72, "1", "Personnalisez votre Profil (30 sec)",
                  "Appuyez sur l'icône Profil (en haut à droite de l'Accueil), renseignez votre nom et service, puis ajoutez votre photo depuis la Galerie ou la Caméra (enregistrée localement).", BLUE)
    y = step_card(pdf, 36, y, PAGE_W - 72, "2", "Créez un Dossier Patient Test (30 sec)",
                  "Appuyez sur l'icône Dossiers Patients (en haut à droite) -> '+ Nouveau Dossier'. Saisissez un alias (ex: M. K.D.), l'âge, le service et le motif d'admission.", TEAL)
    y = step_card(pdf, 36, y, PAGE_W - 72, "3", "Prenez Constantes + Score de Glasgow (45 sec)",
                  "Dans l'onglet 'Mesures', sélectionnez votre patient, saisissez les constantes (PAM & IMC calculées) puis enregistrez un score de Glasgow E+V+M.", GREEN)
    y = step_card(pdf, 36, y, PAGE_W - 72, "4", "Testez un Bilan & admirez la Synchronisation (45 sec)",
                  "Dans 'Analyses', lancez une NFS ou une Bandelette Urinaire sur ce patient, puis ouvrez 'Clinique -> Observation Médicale' : ses données se pré-remplissent automatiquement !", NAVY)

    # Bottom Founder Banner
    fy = PAGE_H - 96
    pdf.rounded_rect(36, fy, PAGE_W - 72, 74, r=10, fill=BLUE_PALE, stroke=BLUE, lw=1.1)
    pdf.draw_image('founder', 46, fy + 7, 40, 60)
    pdf.draw_text("ALBERT DOSSOU — Fondateur & Propriétaire d'EL VITA", 98, fy + 22, size=9.4, color=NAVY, font='F2')
    pdf.draw_paragraph(
        "Initiative médicale indépendante, gratuite et volontaire. Vos retours d'expérience (même après un essai rapide de 3 minutes) aident directement à améliorer les prochaines versions.",
        98, fy + 35, PAGE_W - 146, size=7.6, leading=10.5, color=TEXT_BODY, font='F1'
    )
    pdf.draw_text("Contact retours : dossoualbert00@gmail.com  |  iPhone : https://dossoualbert00-commits.github.io/elvita/", 98, fy + 64, size=7.5, color=BLUE, font='F2')
    pdf.add_link(98, fy + 55, 420, 12, "mailto:dossoualbert00@gmail.com")

    # =========================================================================
    # PAGE 2 : TUTORIEL 1 — ACCUEIL, PROFIL PHOTO & PRÉVENTION (23 FICHES)
    # =========================================================================
    pdf.new_page()
    draw_tutorial_header_footer(pdf, 2, TOTAL_PAGES, "Tutoriel 1 : Accueil, Profil & Prévention")

    y = section_heading(pdf, "T1", "Démarrer avec l'Accueil, le Profil Photo & la Prévention", "Comment utiliser l'écran d'accueil comme réflexe rapide de garde", 36, 62)

    y = utility_box(
        pdf, 36, y, PAGE_W - 72,
        "À QUOI SERT L'ONGLET ACCUEIL ?",
        "L'Accueil est votre tableau de bord en stage : en moins de 5 secondes, vous accédez aux dossiers patients (haut droite), à votre profil personnalisé, aux 23 fiches d'alerte épidémique/prévention et aux 4 raccourcis d'action rapide (Saisie mesures, Courbes, Observation, Analyses).",
        bg=BLUE_PALE, stroke=BLUE, title_col=BLUE
    )

    # Banner visual preview
    pdf.rounded_rect(36, y, PAGE_W - 72, 134, r=10, fill=WHITE, stroke=BORDER, lw=0.9)
    pdf.draw_image('banner', 46, y + 6, 224, 122)
    pdf.draw_text("CE QUE VOUS TROUVEZ EN HAUT DE L'APP :", 282, y + 22, size=9.0, color=NAVY, font='F2')
    by = y + 38
    by = bullet_item(pdf, 282, by, PAGE_W - 326, "Bouton Dossiers :", "Ouvrez ou créez un dossier patient à tout moment.", dot_color=BLUE, size=7.8, leading=11.0)
    by = bullet_item(pdf, 282, by, PAGE_W - 326, "Bouton Profil :", "Photo locale (galerie/caméra), nom, faculté, hôpital & thème clair/sombre.", dot_color=TEAL, size=7.8, leading=11.0)
    by = bullet_item(pdf, 282, by, PAGE_W - 326, "Charte Déontologique :", "Rappel constant que l'outil aide le raisonnement sans remplacer le médecin.", dot_color=GREEN_DARK, size=7.8, leading=11.0)
    y += 146

    pdf.draw_text("ÉTAPES DÉTAILLÉES — PROFIL & 23 FICHES PRÉVENTION", 36, y, size=10.5, color=NAVY, font='F2')
    y += 14

    y = step_card(pdf, 36, y, PAGE_W - 72, "A", "Personnaliser votre Profil avec Photo Locale",
                  "Ouvrez l'icône Profil (en haut à droite). Appuyez sur 'Galerie' pour choisir une image ou 'Caméra' pour prendre une photo. Elle est compressée et stockée uniquement dans votre appareil, visible dans l'en-tête et modifiable/supprimable à tout moment.", BLUE)
    y = step_card(pdf, 36, y, PAGE_W - 72, "B", "Consulter les 23 Fiches de Prévention & Vigilance",
                  "Faites défiler le carrousel horizontal sur l'Accueil ou touchez '23 Fiches Repères' pour ouvrir la bibliothèque complète avec barre de recherche (Paludisme grave, Poliomyélite/PFA, Tuberculose, Rougeole, Choléra, Tétanos, vaccination, dépistages...).", TEAL)
    y = step_card(pdf, 36, y, PAGE_W - 72, "C", "Lire l'essentiel d'une fiche de prévention en urgence",
                  "Chaque fiche affiche en premier le 'Message Clinique Prioritaire', suivi de 7 blocs clairs : Définition, Population cible, Facteurs de risque, Mesures de prévention, Dépistage, Conseils pratiques, Signes d'alerte/gravité et Points clés.", GREEN_DARK)
    y = step_card(pdf, 36, y, PAGE_W - 72, "D", "Utiliser les 4 boutons 'Accès Rapide'",
                  "En bas de l'Accueil, les 4 tuiles vous envoient directement vers : Saisie constantes, Historique & Courbes, Rédaction d'Observation et Laboratoire NFS.", NAVY)

    # =========================================================================
    # PAGE 3 : TUTORIEL 2 — DOSSIERS PATIENTS, MESURES & SCORE DE GLASGOW
    # =========================================================================
    pdf.new_page()
    draw_tutorial_header_footer(pdf, 3, TOTAL_PAGES, "Tutoriel 2 : Dossiers, Mesures & Glasgow")

    y = section_heading(pdf, "T2", "Centraliser vos Patients & Suivre Constantes + Glasgow", "Ne plus jamais mélanger les paramètres de deux patients en salle", 36, 62)

    y = utility_box(
        pdf, 36, y, PAGE_W - 72,
        "À QUOI SERVENT LES DOSSIERS CENTRALISÉS & LE MODULE MESURES ?",
        "Chaque dossier patient devient la source unique de vérité : toutes les constantes vitales, scores de Glasgow, bilans biologiques et observations enregistrés pour un patient restent rattachés à son dossier, datés et consultables à tout instant sans risque de confusion.",
        bg=TEAL_PALE, stroke=TEAL, title_col=TEAL
    )

    y = step_card(pdf, 36, y, PAGE_W - 72, "1", "Créer et Consulter un Dossier Patient Centralisé",
                  "Depuis Dossiers Patients -> '+ Nouveau Dossier', entrez un alias, âge, sexe, service, chambre et motif. Cliquez ensuite sur 'Voir Dossier' pour retrouver l'historique complet chronologique du patient (constantes, GCS, bilans, observations).", BLUE)
    y = step_card(pdf, 36, y, PAGE_W - 72, "2", "Saisir & Interpréter les Constantes Vitales",
                  "Dans l'onglet 'Mesures -> Saisie des Constantes', choisissez le dossier patient en haut, entrez Température, SpO2, PAS/PAD, FC, FR, Glycémie, Poids et Taille. EL VITA calcule la PAM (PAS + 2*PAD)/3 et l'IMC, puis affiche une analyse systématique par appareil.", TEAL)
    y = step_card(pdf, 36, y, PAGE_W - 72, "3", "Suivre l'Évolution sur les Courbes",
                  "Allez dans 'Mesures -> Historique & Courbes' : dès que vous avez enregistré au moins 2 mesures sur un même patient, la courbe réelle de Température et de SpO2 se trace pour visualiser la réponse au traitement.", GREEN_DARK)
    y = step_card(pdf, 36, y, PAGE_W - 72, "4", "Évaluer le Score de Glasgow (GCS) — Dossier Obligatoire",
                  "Le Score de Glasgow se trouve désormais dans 'Mesures -> Score de Glasgow'. Règle de sécurité : vous DEVEZ sélectionner un patient pour enregistrer le score. Choisissez E (1-4), V (1-5) et M (1-6) : le score /15 s'affiche avec alerte rouge immédiate si GCS <= 8 (danger voies aériennes).", RED)

    # Alert levels box
    pdf.rounded_rect(36, y + 2, PAGE_W - 72, 118, r=10, fill=ORANGE_PALE, stroke=ORANGE, lw=1.0)
    pdf.draw_text("CODES COULEURS DES ALERTES CLINIQUES DANS EL VITA", 50, y + 20, size=9.2, color=ORANGE, font='F2')
    ay = y + 36
    ay = bullet_item(pdf, 50, ay, PAGE_W - 100, "ROUGE (URGENCE CRITIQUE) :", "PAS < 90 ou PAM < 65, PA >= 180/110, SpO2 < 90%, T° < 35.5°C ou >= 39.5°C, FC < 50 ou >= 130, Glycémie < 0.70 g/L, ou GCS <= 8.", dot_color=RED, size=7.9, leading=11.2)
    ay = bullet_item(pdf, 50, ay, PAGE_W - 100, "ORANGE (VIGILANCE) :", "Fièvre 38.5-39.4°C, SpO2 90-94%, HTA modérée, polypnée > 24/min, tachycardie 120-129 bpm, GCS 9-14.", dot_color=ORANGE, size=7.9, leading=11.2)
    ay = bullet_item(pdf, 50, ay, PAGE_W - 100, "VERT (STABLE) :", "Paramètres dans les valeurs physiologiques habituelles et conscience normale (GCS 15/15).", dot_color=GREEN_DARK, size=7.9, leading=11.2)

    # =========================================================================
    # PAGE 4 : TUTORIEL 3 — MODULE ANALYSES BIOLOGIQUES (5 EXAMENS COMPLETS)
    # =========================================================================
    pdf.new_page()
    draw_tutorial_header_footer(pdf, 4, TOTAL_PAGES, "Tutoriel 3 : Analyses Biologiques (5 Bilans)")

    y = section_heading(pdf, "T3", "Maîtriser les 5 Bilans Biologiques & Conclusions Guidées", "Comprendre les associations biologiques plutôt que lire un chiffre isolé", 36, 62)

    y = utility_box(
        pdf, 36, y, PAGE_W - 72,
        "À QUOI SERT LE MODULE ANALYSES ?",
        "Devant un bilan de laboratoire, l'étudiant sait parfois qu'une valeur est haute ou basse mais peine à formuler une conclusion globale. EL VITA relie les paramètres entre eux (ex: Hb + VGM + GB + Plaquettes -> bicytopénie ou pancytopénie), affiche une Conclusion Médicale Générale claire, explique la physiopathologie et l'enregistre dans le dossier patient.",
        bg=BLUE_PALE, stroke=BLUE, title_col=BLUE
    )

    y = step_card(pdf, 36, y, PAGE_W - 72, "0", "Réflexe Avant Chaque Bilan : Rattacher au Dossier Patient",
                  "En haut de l'onglet 'Analyses', sélectionnez le patient dans la liste déroulante. Votre conclusion biologique sera automatiquement horodatée dans son dossier et prête à être reprise dans votre Observation Médicale.", NAVY)

    bilans = [
        ("A. NFS Complète (Hémogramme en 6 volets)",
         "Saisissez Hb, Ht, GR, réticulocytes, GB, formule (PNN, Lympho, Mono, Eosino, Baso) et Plaquettes. EL VITA calcule VGM et CCMH, identifie anémie micro/normo/macrocytaire, thrombocytose réactionnelle, leucopénie, agranulocytose (PNN < 0.5 G/L), bicytopénie ou pancytopénie.", BLUE),
        ("B. UECréat / Ionogramme & Fonction Rénale",
         "Saisissez Urée, Créatinine, Na+, K+, Cl-, HCO3-, Glycémie + Age/Poids/Sexe. Calcul automatique du DFG (Cockcroft-Gault), du rapport Urée/Créatinine (orientant vers une IRA fonctionnelle si > 100) et du Trou Anionique plasmatique.", TEAL),
        ("C. Bilan Hépatique & Pancréatique",
         "Entrez ALAT, ASAT, PAL, GGT, Bilirubine Totale/Conjuguée et Lipase. Différencie cytolyse (>10N critique), cholestase, atteinte mixte et pancréatite aiguë (Lipase > 3N).", GREEN_DARK),
        ("D. Hémostase & Inflammation",
         "Entrez CRP, VS, Procalcitonine (PCT), TP, INR, ratio TCA, Fibrinogène et D-Dimères. Détecte sepsis bactérien probable, surdosage AVK, carence/IHC et triade évocatrice de CIVD.", ORANGE),
        ("E. Bandelette Urinaire (BU) & Orientation ECBU",
         "Sélectionnez Leucocytes, Nitrites, Protéinurie, Hématurie, Cétonurie et Glycosurie. Détecte infection urinaire probable (Leuco + Nitrites), protéinurie massive 3+ et alerte critique d'acidocétose (Glycosurie + Cétonurie).", RED),
    ]
    for idx, (bt, bd, col) in enumerate(bilans, start=1):
        y = step_card(pdf, 36, y, PAGE_W - 72, str(idx), bt, bd, col)

    # =========================================================================
    # PAGE 5 : TUTORIEL 4 — CLINIQUE, OBSERVATION SYNCHRONISÉE & FORMATION A-F
    # =========================================================================
    pdf.new_page()
    draw_tutorial_header_footer(pdf, 5, TOTAL_PAGES, "Tutoriel 4 : Clinique & Tour de Salle")

    y = section_heading(pdf, "T4", "Rédiger l'Observation & Réussir le Tour de Salle", "De l'interrogatoire au lit du patient jusqu'à la présentation orale au senior", 36, 62)

    y = utility_box(
        pdf, 36, y, PAGE_W - 72,
        "À QUOI SERT LA SYNCHRONISATION DANS CLINIQUE ?",
        "Quand vous sélectionnez un patient dans 'Clinique -> Observation Médicale', un bandeau vert s'affiche et importe automatiquement son motif d'admission, ses dernières constantes, son dernier score de Glasgow et ses 3 derniers bilans biologiques. Vous gagnez un temps précieux sans jamais perdre vos textes déjà saisis.",
        bg=GREEN_PALE, stroke=GREEN_DARK, title_col=GREEN_DARK
    )

    y = step_card(pdf, 36, y, PAGE_W - 72, "1", "Générer l'Observation Médicale & les Mises à Jour Jx",
                  "Complétez l'Histoire de la Maladie, l'examen par appareils et votre synthèse, puis cliquez sur 'Rédiger & Enregistrer'. Utilisez le sous-onglet 'Mise à Jour Clinique' pour noter l'évolution quotidienne (J2, J3...) dans le dossier.", BLUE)

    pdf.rounded_rect(36, y + 2, PAGE_W - 72, 232, r=10, fill=BG_LIGHT, stroke=BLUE, lw=1.1)
    pdf.draw_text("FORMATION CLINIQUE PÉDAGOGIQUE (BOUTON EN HAUT DE 'CLINIQUE') : 6 VOLETS", 50, y + 21, size=9.2, color=NAVY, font='F2')
    ty = y + 36
    training_sections = [
        ("A. Interrogatoire (14 points) :",
         "Les 14 étapes chronologiques au lit du malade avec formulations professionnelles exactes, réponses types du patient, questions de relance et mini-QCM d'application.", BLUE),
        ("B. Organisation (9 rubriques) :",
         "Apprendre à classer sans erreur motif, HDM, antécédents, traitements, allergies et mode de vie, avec exemples concrets et 6 exercices interactifs de tri.", TEAL),
        ("C. Observation (4 ateliers) :",
         "Comparatifs 'À proscrire vs Bonne rédaction' et exercices corrigés pour écrire de vraies notes médicales précises et objectives.", GREEN_DARK),
        ("D. Synthèse Clinique (5 cas) :",
         "5 cas fictifs pédagogiques complets montrant comment passer des symptômes aux hypothèses diagnostiques et à l'urgence à éliminer en priorité.", ORANGE),
        ("E. Transitions (11 étapes) :",
         "Phrases naturelles pour fluidifier l'entretien entre deux thèmes, incluant la revue systématique des 10 appareils (questions ciblées et pièges).", BLUE),
        ("F. Présentation Orale au Senior :",
         "Grille d'auto-évaluation interactive à cocher en 8 critères + 5 scripts comparatifs ('présentation brouillonne' vs 'script de référence' + questions probables du senior).", NAVY),
    ]
    for lead, desc, col in training_sections:
        ty = bullet_item(pdf, 50, ty, PAGE_W - 100, lead, desc, dot_color=col, size=8.0, leading=11.3) + 2

    y += 246
    pdf.rounded_rect(36, y, PAGE_W - 72, 68, r=8, fill=ORANGE_PALE, stroke=ORANGE, lw=0.9)
    pdf.draw_text("CAS PRATIQUE IDÉAL EN STAGE (ROUTINE EN 4 ÉTAPES)", 50, y + 17, size=8.8, color=ORANGE, font='F2')
    pdf.draw_paragraph(
        "1) Créez le dossier patient -> 2) Saisissez ses constantes et son Glasgow dans 'Mesures' -> 3) Interprétez son bilan dans 'Analyses' -> 4) Ouvrez 'Clinique' : toute l'observation est prête à être présentée au chef de service avec la grille orale de section F !",
        50, y + 31, PAGE_W - 100, size=8.0, leading=11.4, color=TEXT_DARK, font='F1'
    )

    # =========================================================================
    # PAGE 6 : TUTORIEL 5 — SÉMIOLOGIE (304 / 31 / 90) & INSTALLATION
    # =========================================================================
    pdf.new_page()
    draw_tutorial_header_footer(pdf, 6, TOTAL_PAGES, "Tutoriel 5 : Sémiologie & Installation")

    y = section_heading(pdf, "T5", "Réviser Efficacement & Installer sur Android / iPhone", "304 Fiches Sémiologiques, 31 Cours Médicaux Universitaires & 90 QCM", 36, 62)

    y = step_card(pdf, 36, y, PAGE_W - 72, "1", "304 Fiches Sémiologiques (14 Spécialités, 14 Rubriques)",
                  "Dans l'onglet 'Sémiologie -> Fiches Sémiologie', filtrez par spécialité (Cardio, Pneumo, Neuro, Digestif, Uro/Néphro, Dermato, Pédiatrie, Gynéco, Psychiatrie...) ou tapez un signe dans la barre de recherche. Chaque fiche suit les 14 rubriques standardisées (définition, mécanisme, causes, recherche, gravité, pièges, points clés).", BLUE)
    y = step_card(pdf, 36, y, PAGE_W - 72, "2", "31 Cours Médicaux Universitaires (Tableaux & Cas Corrigés)",
                  "Sous-onglet 'Cours Médicaux (31)' : accédez à des cours structurés en 14 rubriques académiques avec tableaux comparatifs clairs et un cas clinique pédagogique (cas fictif) avec correction dépliable pour vérifier votre raisonnement.", TEAL)
    y = step_card(pdf, 36, y, PAGE_W - 72, "3", "90 QCM Officiels Interactifs",
                  "Sous-onglet 'Quiz QCM' : entraînez-vous par appareil ou sur toute la banque. Le score se met à jour en direct dans l'onglet et chaque question affiche sa justification détaillée + une Perle Clinique à retenir.", GREEN_DARK)

    y += 6
    pdf.draw_text("RAPPEL D'INSTALLATION POUR VOS CONFRÈRES (ANDROID & IPHONE)", 36, y, size=10.5, color=NAVY, font='F2')
    y += 14

    col_w = (PAGE_W - 72 - 12) / 2
    # Android box
    pdf.rounded_rect(36, y, col_w, 166, r=10, fill=GREEN_PALE, stroke=GREEN_DARK, lw=1.1)
    pdf.rect(36, y, col_w, 26, fill=GREEN_DARK)
    pdf.draw_text("UTILISATEURS ANDROID (.APK)", 36 + col_w / 2, y + 17, size=9.0, color=WHITE, font='F2', align='center')
    ay = y + 40
    ay = bullet_item(pdf, 46, ay, col_w - 20, "1.", "Téléchargez le fichier EL VITA.apk reçu.", dot_color=GREEN_DARK, size=7.9, leading=11.0)
    ay = bullet_item(pdf, 46, ay, col_w - 20, "2.", "Appuyez sur le fichier -> si alerte sécurité : Paramètres -> Autoriser cette source.", dot_color=GREEN_DARK, size=7.9, leading=11.0)
    ay = bullet_item(pdf, 46, ay, col_w - 20, "3.", "Appuyez sur Installer puis Ouvrir.", dot_color=GREEN_DARK, size=7.9, leading=11.0)
    ay = bullet_item(pdf, 46, ay, col_w - 20, "4.", "100% hors-ligne dès le premier lancement.", dot_color=GREEN_DARK, size=7.9, leading=11.0)

    # iPhone box
    ix = 36 + col_w + 12
    pdf.rounded_rect(ix, y, col_w, 166, r=10, fill=BLUE_PALE, stroke=BLUE, lw=1.1)
    pdf.rect(ix, y, col_w, 26, fill=BLUE)
    pdf.draw_text("UTILISATEURS IPHONE (SAFARI PWA)", ix + col_w / 2, y + 17, size=9.0, color=WHITE, font='F2', align='center')
    iy = y + 40
    iy = bullet_item(pdf, ix + 10, iy, col_w - 20, "1.", "Ouvrez Safari sur : https://dossoualbert00-commits.github.io/elvita/", dot_color=BLUE, size=7.9, leading=11.0)
    iy = bullet_item(pdf, ix + 10, iy, col_w - 20, "2.", "Appuyez sur le bouton Partager (carré avec flèche vers le haut en bas d'écran).", dot_color=BLUE, size=7.9, leading=11.0)
    iy = bullet_item(pdf, ix + 10, iy, col_w - 20, "3.", "Choisissez 'Sur l'écran d'accueil' puis appuyez sur Ajouter.", dot_color=BLUE, size=7.9, leading=11.0)
    iy = bullet_item(pdf, ix + 10, iy, col_w - 20, "4.", "Ouvrez l'icône EL VITA : mode plein écran et fonctionnement 100% hors-ligne !", dot_color=BLUE, size=7.9, leading=11.0)
    pdf.add_link(ix + 10, y + 32, col_w - 20, 26, "https://dossoualbert00-commits.github.io/elvita/")

    y += 182
    pdf.rounded_rect(36, y, PAGE_W - 72, 100, r=10, fill=NAVY, stroke=BLUE_LIGHT, lw=1.3)
    pdf.draw_image('founder', 48, y + 10, 53, 80)
    pdf.draw_text("PARTICIPATION VOLONTAIRE & AMÉLIORATION CONTINUE", 114, y + 24, size=9.5, color=BLUE_LIGHT, font='F2')
    pdf.draw_paragraph(
        "Votre avis compte ! Même si vous n'utilisez pas l'application tous les jours, essayez-la quelques minutes en stage ou sur un cas d'étude et notez vos remarques (bugs, notions à ajouter, ergonomie).",
        114, y + 38, PAGE_W - 166, size=8.1, leading=11.5, color=WHITE, font='F1'
    )
    pdf.draw_text("Albert DOSSOU — Fondateur & Propriétaire d'EL VITA  |  dossoualbert00@gmail.com", 114, y + 84, size=8.2, color=BLUE_LIGHT, font='F2')
    pdf.add_link(114, y + 74, 410, 14, "mailto:dossoualbert00@gmail.com")

    pdf.save("docs/EL_VITA_Tutoriels_Utilisateurs.pdf")


if __name__ == "__main__":
    build_tutorials_pdf()
