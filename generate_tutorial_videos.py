#!/usr/bin/env python3
"""
Générateur de 4 Vidéos Tutoriels Fluides HD (720x1280, 9:16, 30 fps, H.264 + AAC)
pour présenter l'utilité et l'utilisation pas-à-pas d'EL VITA aux étudiants en médecine.
Sortie dans : docs/videos/
"""

import math
import os
import shutil
import struct
import subprocess
import wave

import generate_presentation_pdf as gpp

# Format mobile vertical HD 9:16 (360x640 pt -> 720x1280 px à 144 DPI)
VW = 360.0
VH = 640.0
gpp.PAGE_W = VW
gpp.PAGE_H = VH

from generate_presentation_pdf import (
    PDFDocument,
    NAVY, NAVY_MID, BLUE, BLUE_LIGHT, BLUE_PALE,
    TEAL, TEAL_PALE, GREEN, GREEN_DARK, GREEN_PALE,
    ORANGE, ORANGE_PALE, RED, RED_PALE,
    TEXT_DARK, TEXT_BODY, TEXT_MUTED, BORDER, BG_LIGHT, WHITE,
    wrap_text, text_width
)

DARK_CARD = (17/255, 28/255, 46/255)       # #111C2E
DARK_BG = (8/255, 16/255, 30/255)          # #08101E
DARK_BORDER = (35/255, 55/255, 88/255)     # #233758
SLATE_200 = (226/255, 232/255, 240/255)
SLATE_300 = (203/255, 213/255, 225/255)
SLATE_400 = (148/255, 163/255, 184/255)
MINT_SOFT = (167/255, 243/255, 208/255)
AMBER_SOFT = (253/255, 230/255, 138/255)
RED_SOFT = (252/255, 165/255, 165/255)

TMP_DIR = "/tmp/elvita_video_build"
OUT_DIR = "docs/videos"


def prepare_assets():
    os.makedirs(TMP_DIR, exist_ok=True)
    os.makedirs(OUT_DIR, exist_ok=True)
    cmds = [
        ["convert", "docs/assets/logo.png", "-background", "#0B192C", "-flatten", "-quality", "92", f"{TMP_DIR}/logo.jpg"],
        ["convert", "docs/assets/banner.jpg", "-strip", "-interlace", "none", "-quality", "90", f"{TMP_DIR}/banner.jpg"],
        ["convert", "docs/assets/founder.jpg", "-strip", "-interlace", "none", "-quality", "92", f"{TMP_DIR}/founder.jpg"],
    ]
    for c in cmds:
        subprocess.run(c, check=True)


def render_pdf_layer(pdf: PDFDocument, pdf_path: str, prefix: str, alpha=False):
    pdf.save(pdf_path)
    dev = "pngalpha" if alpha else "png16m"
    subprocess.run([
        "gs", "-dNOPAUSE", "-dBATCH", "-q",
        f"-sDEVICE={dev}", "-r144",
        "-dTextAlphaBits=4", "-dGraphicsAlphaBits=4",
        f"-sOutputFile={prefix}_%d.png",
        pdf_path
    ], check=True)


def generate_ambient_audio(wav_path: str, scene_durations: list, xfade_dur: float = 0.5):
    """Génère une piste sonore douce, chaleureuse et calme avec cloches légères aux transitions."""
    total_dur = sum(scene_durations) - xfade_dur * (len(scene_durations) - 1)
    sr = 22050
    n_samples = int(total_dur * sr)

    # Progression d'accords douce (Do maj7 -> La min9 -> Fa maj7 -> Sol6)
    chords = [
        (261.63, 329.63, 392.00, 493.88),
        (220.00, 261.63, 329.63, 392.00),
        (174.61, 220.00, 261.63, 329.63),
        (196.00, 246.88, 293.66, 392.00),
    ]
    # Instants exacts de transition
    t_marks = []
    acc = 0.0
    for d in scene_durations[:-1]:
        acc += (d - xfade_dur)
        t_marks.append(acc)

    samples = bytearray()
    for i in range(n_samples):
        t = i / sr
        chord_idx = int(t / 4.5) % len(chords)
        freqs = chords[chord_idx]

        # Enveloppe globale fade in/out
        env = 1.0
        if t < 0.8:
            env = t / 0.8
        elif t > total_dur - 1.2:
            env = max(0.0, (total_dur - t) / 1.2)

        # Nappe harmonique très douce (-24 dB)
        pad = 0.0
        for idx, f in enumerate(freqs):
            w = 0.25 if idx == 0 else 0.16
            pad += w * math.sin(2.0 * math.pi * f * t)

        # Clochette cristalline subtile à l'entrée de chaque nouvelle scène
        chime = 0.0
        for tm in t_marks:
            dt = t - tm
            if 0.0 <= dt < 1.1:
                chime_env = math.exp(-4.5 * dt) * min(1.0, dt / 0.015)
                chime += 0.14 * chime_env * (
                    0.7 * math.sin(2.0 * math.pi * 587.33 * dt) +
                    0.3 * math.sin(2.0 * math.pi * 880.00 * dt)
                )

        val = env * (0.14 * pad + chime)
        val = max(-0.85, min(0.85, val))
        samples.extend(struct.pack('<h', int(val * 32767)))

    with wave.open(wav_path, 'wb') as wf:
        wf.setnchannels(1)
        wf.setsampwidth(2)
        wf.setframerate(sr)
        wf.writeframes(bytes(samples))


def draw_scene_bg(pdf: PDFDocument, ep_label: str, step_idx: int, total_steps: int, headline: str, sub: str):
    pdf.new_page()
    # Fond sombre élégant médical
    pdf.rect(0, 0, VW, VH, fill=DARK_BG)
    # Halo supérieur discret
    pdf.rect(0, 0, VW, 150, fill=NAVY)
    pdf.rect(0, 150, VW, 3, fill=BLUE)

    # Top bar logo + badge épisode
    pdf.rounded_rect(18, 18, 30, 30, r=7, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=0.8)
    pdf.draw_image('logo', 20, 20, 26, 26)
    pdf.draw_text("EL VITA", 55, 29, size=10.5, color=WHITE, font='F2')
    pdf.draw_text("TUTORIEL VIDÉO", 55, 41, size=7.0, color=BLUE_LIGHT, font='F2')

    pdf.rounded_rect(VW - 128, 22, 110, 22, r=11, fill=NAVY_MID, stroke=TEAL, lw=0.9)
    pdf.draw_text(ep_label, VW - 73, 36, size=7.2, color=WHITE, font='F2', align='center')

    # Indicateur d'étapes (points)
    dot_y = 66
    step_pill_w = 18
    gap = 6
    total_w = total_steps * step_pill_w + (total_steps - 1) * gap
    sx = (VW - total_w) / 2.0
    for s in range(total_steps):
        col = BLUE_LIGHT if s == step_idx else (TEAL if s < step_idx else DARK_BORDER)
        pdf.rounded_rect(sx + s * (step_pill_w + gap), dot_y, step_pill_w, 4.5, r=2.2, fill=col)

    # Titre principal de la scène
    pdf.draw_paragraph(headline, 20, 92, VW - 40, size=15.0, leading=19.5, color=WHITE, font='F2', align='center')
    if sub:
        pdf.draw_paragraph(sub, 22, 134, VW - 44, size=8.4, leading=11.5, color=SLATE_300, font='F1', align='center')

    # Pied de page discret (au-dessus de la barre de progression animée)
    pdf.draw_text("EL VITA — 100% Hors-Ligne • Android & iPhone", VW / 2.0, VH - 20, size=7.2, color=SLATE_400, font='F1', align='center')


def draw_fg_card(pdf: PDFDocument, x: float, y: float, w: float, h: float, title: str, accent=BLUE, body_fn=None):
    pdf.new_page()
    # Carte principale avec bordure nette (transparent autour)
    pdf.rounded_rect(x, y, w, h, r=14, fill=DARK_CARD, stroke=accent, lw=1.4)
    pdf.rounded_rect(x, y, w, 34, r=14, fill=NAVY_MID)
    pdf.rect(x, y + 20, w, 14, fill=NAVY_MID)
    pdf.line(x, y + 34, x + w, y + 34, stroke=accent, lw=1.0)
    pdf.draw_text(title, x + 14, y + 21, size=9.6, color=BLUE_LIGHT if accent == BLUE else WHITE, font='F2')
    if body_fn:
        body_fn(pdf, x, y + 46, w, h - 46)


def draw_fg_bullets(pdf: PDFDocument, x: float, y: float, w: float, items: list, y_step=44):
    cy = y
    for num, head, desc, col in items:
        pdf.rounded_rect(x + 12, cy, w - 24, y_step - 8, r=9, fill=NAVY, stroke=DARK_BORDER, lw=0.8)
        pdf.circle(x + 28, cy + (y_step - 8) / 2.0, 10, fill=col)
        pdf.draw_text(num, x + 28, cy + (y_step - 8) / 2.0 + 3.2, size=8.2, color=WHITE, font='F2', align='center')
        pdf.draw_text(head, x + 46, cy + 14, size=8.8, color=WHITE, font='F2')
        pdf.draw_paragraph(desc, x + 46, cy + 27, w - 76, size=7.6, leading=10.2, color=SLATE_300, font='F1')
        cy += y_step


def draw_tap_callout(pdf: PDFDocument, x: float, y: float, w: float, label: str, col=TEAL):
    pdf.rounded_rect(x, y, w, 28, r=14, fill=col, stroke=WHITE, lw=1.0)
    pdf.draw_text(label, x + w / 2.0, y + 17.5, size=8.4, color=WHITE, font='F2', align='center')


def load_base_images(pdf: PDFDocument):
    pdf.load_image('logo', f"{TMP_DIR}/logo.jpg")
    pdf.load_image('banner', f"{TMP_DIR}/banner.jpg")
    pdf.load_image('founder', f"{TMP_DIR}/founder.jpg")


# ==============================================================================
# DÉFINITION DES 4 VIDÉOS TUTORIELS
# ==============================================================================

def build_video_1():
    """01 — Découverte, Profil Photo & 23 Fiches Prévention"""
    scenes = [
        {
            'dur': 5.8,
            'ep': 'VIDÉO 1 / 4',
            'head': 'EL VITA : Ton Compagnon Clinique',
            'sub': '100% Hors-Ligne, Gratuit & Sans Publicité',
            'fg': lambda p: _v1_scene1(p),
        },
        {
            'dur': 7.2,
            'ep': 'VIDÉO 1 / 4',
            'head': 'Pourquoi l\'utiliser en stage ?',
            'sub': '3 difficultés réelles réglées en une application',
            'fg': lambda p: _v1_scene2(p),
        },
        {
            'dur': 7.5,
            'ep': 'VIDÉO 1 / 4',
            'head': 'Accueil & Profil avec Photo',
            'sub': 'Personnalise ton espace en stockage local sécurisé',
            'fg': lambda p: _v1_scene3(p),
        },
        {
            'dur': 7.5,
            'ep': 'VIDÉO 1 / 4',
            'head': '23 Fiches Prévention & Vigilance',
            'sub': 'Réflexes immédiats en garde et recherche instantanée',
            'fg': lambda p: _v1_scene4(p),
        },
        {
            'dur': 7.0,
            'ep': 'VIDÉO 1 / 4',
            'head': 'Parcours Découverte en 3 Min',
            'sub': 'Teste facilement les 4 modules depuis l\'Accueil',
            'fg': lambda p: _v1_scene5(p),
        },
        {
            'dur': 5.8,
            'ep': 'VIDÉO 1 / 4',
            'head': 'Prêt à l\'essayer dès aujourd\'hui ?',
            'sub': 'Un outil indépendant au service des étudiants en médecine',
            'fg': lambda p: _v1_scene6(p),
        },
    ]
    compile_video("01_EL_VITA_Decouverte_et_Accueil.mp4", scenes)


def _v1_scene1(pdf: PDFDocument):
    pdf.new_page()
    x, y, w, h = 22, 174, VW - 44, 412
    pdf.rounded_rect(x, y, w, h, r=16, fill=DARK_CARD, stroke=BLUE, lw=1.5)
    pdf.rounded_rect(x + (w - 86)/2, y + 26, 86, 86, r=18, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=1.4)
    pdf.draw_image('logo', x + (w - 74)/2, y + 32, 74, 74)
    pdf.draw_text("EL VITA", VW/2, y + 142, size=22.0, color=WHITE, font='F2', align='center')
    pdf.draw_text("Apprendre. Comprendre. Pratiquer.", VW/2, y + 162, size=9.6, color=BLUE_LIGHT, font='F2', align='center')
    pdf.draw_image('banner', x + 16, y + 180, w - 32, 146)
    draw_tap_callout(pdf, x + 24, y + 344, w - 48, "Android (.APK)  &  iPhone (Safari PWA)", TEAL)
    pdf.draw_text("Conçu pour les externes & étudiants (3e à 7e année)", VW/2, y + 392, size=7.8, color=SLATE_300, font='F1', align='center')


def _v1_scene2(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("1", "Zéro Internet en salle ou garde", "Tout est embarqué : 304 fiches, 31 cours, 90 QCM, 23 fiches prévention et 5 calculateurs.", BLUE),
            ("2", "Fini la dispersion des notes", "Constantes, Glasgow, analyses et observations sont centralisés par patient.", TEAL),
            ("3", "Secret médical 100% préservé", "Aucune donnée ne quitte ton téléphone : tout reste chiffré localement.", GREEN_DARK),
        ], y_step=102)
    draw_fg_card(pdf, 20, 176, VW - 40, 396, "LES 3 PROBLÈMES RÉSOLUS AU QUOTIDIEN", BLUE, body)


def _v1_scene3(pdf: PDFDocument):
    def body(p, x, y, w, h):
        # Simuler header accueil
        p.rounded_rect(x + 14, y, w - 28, 62, r=10, fill=NAVY, stroke=BLUE, lw=1.0)
        p.draw_image('logo', x + 24, y + 13, 34, 34)
        p.draw_text("EL VITA", x + 66, y + 27, size=10.5, color=WHITE, font='F2')
        p.draw_text("Compagnon Clinique", x + 66, y + 40, size=7.4, color=BLUE_LIGHT, font='F1')
        p.circle(x + w - 66, y + 31, 13, fill=NAVY_MID, stroke=TEAL, lw=1.2)
        p.draw_text("DOS", x + w - 66, y + 34, size=6.2, color=WHITE, font='F2', align='center')
        p.circle(x + w - 34, y + 31, 13, fill=BLUE, stroke=WHITE, lw=1.2)
        p.draw_text("PRO", x + w - 34, y + 34, size=6.2, color=WHITE, font='F2', align='center')

        draw_fg_bullets(p, x, y + 76, w, [
            ("1", "Ouvre ton Profil (en haut à droite)", "Renseigne ton nom, ton année (DFASM...), ta faculté et ton service hospitalier.", BLUE),
            ("2", "Ajoute ta Photo Locale", "Choisis depuis ta Galerie ou prends une photo avec la Caméra. Stockée uniquement sur ton appareil.", TEAL),
            ("3", "Thème Sombre ou Clair", "Bascule à tout moment selon le confort visuel de garde de nuit.", GREEN_DARK),
        ], y_step=84)
    draw_fg_card(pdf, 20, 174, VW - 40, 412, "ACCUEIL : TON PROFIL & TES DOSSIERS", TEAL, body)


def _v1_scene4(pdf: PDFDocument):
    def body(p, x, y, w, h):
        p.rounded_rect(x + 14, y, w - 28, 96, r=10, fill=(2/255, 45/255, 78/255), stroke=BLUE_LIGHT, lw=1.0)
        p.rounded_rect(x + 24, y + 12, 92, 16, r=8, fill=RED)
        p.draw_text("INFECTIOLOGIE", x + 70, y + 22.5, size=6.5, color=WHITE, font='F2', align='center')
        p.draw_text("Paludisme : Urgence & Diagnostic", x + 24, y + 44, size=9.6, color=WHITE, font='F2')
        p.draw_paragraph("Toute fièvre au retour de zone d'endémie est un paludisme jusqu'à preuve du contraire.", x + 24, y + 58, w - 52, size=7.6, leading=10.5, color=MINT_SOFT, font='F1')

        draw_fg_bullets(p, x, y + 110, w, [
            ("1", "23 Fiches de Vigilance Sanitaire", "Paludisme grave, Polio/PFA, Tuberculose, Rougeole, Choléra, Tétanos, dépistages...", BLUE),
            ("2", "Message Clinique Prioritaire", "Affiché en premier pour retenir le réflexe vital en moins de 10 secondes.", TEAL),
            ("3", "Recherche Instantanée", "Appuie sur '23 Fiches Repères' pour filtrer toutes les fiches hors-ligne.", GREEN_DARK),
        ], y_step=80)
    draw_fg_card(pdf, 20, 174, VW - 40, 414, "SECTION A : 23 FICHES PRÉVENTION", BLUE, body)


def _v1_scene5(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("1", "Créer un patient test (30 sec)", "Icône Dossiers -> '+ Nouveau Dossier' avec un alias (ex: M. K.D.).", BLUE),
            ("2", "Tester Mesures + Glasgow (45 sec)", "Entre des constantes et un score E+V+M sur ce patient.", TEAL),
            ("3", "Lancer un bilan NFS ou BU (45 sec)", "Obtiens une conclusion médicale structurée automatiquement archivée.", GREEN_DARK),
            ("4", "Ouvrir Clinique -> Observation", "Admire la synchronisation automatique sans aucune double saisie !", ORANGE),
        ], y_step=80)
    draw_fg_card(pdf, 20, 174, VW - 40, 404, "ESSAI GUIDÉ EN 3 MINUTES CHRONO", GREEN_DARK, body)


def _v1_scene6(pdf: PDFDocument):
    pdf.new_page()
    x, y, w, h = 22, 176, VW - 44, 396
    pdf.rounded_rect(x, y, w, h, r=16, fill=DARK_CARD, stroke=BLUE_LIGHT, lw=1.4)
    pdf.draw_image('founder', x + (w - 86)/2, y + 22, 86, 129)
    pdf.draw_text("ALBERT DOSSOU", VW/2, y + 174, size=14.0, color=WHITE, font='F2', align='center')
    pdf.draw_text("Fondateur & Propriétaire d'EL VITA", VW/2, y + 191, size=8.6, color=BLUE_LIGHT, font='F2', align='center')
    pdf.draw_paragraph(
        "Même si tu n'utilises pas l'application tous les jours, teste-la 3 minutes et partage tes remarques pour améliorer la formation médicale !",
        x + 22, y + 216, w - 44, size=8.3, leading=12.0, color=SLATE_200, font='F1', align='center'
    )
    draw_tap_callout(pdf, x + 22, y + 286, w - 44, "dossoualbert00@gmail.com", BLUE)
    pdf.draw_text("Suite dans la Vidéo 2 : Dossiers, Mesures & Glasgow", VW/2, y + 346, size=8.0, color=MINT_SOFT, font='F2', align='center')


# ==============================================================================
# VIDÉO 2 — DOSSIERS PATIENTS, MESURES & GLASGOW
# ==============================================================================

def build_video_2():
    scenes = [
        {'dur': 5.8, 'ep': 'VIDÉO 2 / 4', 'head': 'Dossiers, Mesures & Glasgow', 'sub': 'Centraliser les paramètres vitaux sans jamais les mélanger', 'fg': _v2_scene1},
        {'dur': 7.5, 'ep': 'VIDÉO 2 / 4', 'head': '1. Dossier Patient Centralisé', 'sub': 'La source unique pour constantes, bilans et observations', 'fg': _v2_scene2},
        {'dur': 7.8, 'ep': 'VIDÉO 2 / 4', 'head': '2. Saisir les Constantes Vitales', 'sub': 'Calcul auto PAM, IMC & alertes cliniques immédiates', 'fg': _v2_scene3},
        {'dur': 7.2, 'ep': 'VIDÉO 2 / 4', 'head': '3. Courbes d\'Évolution Réelles', 'sub': 'Visualiser la réponse au traitement au fil des jours', 'fg': _v2_scene4},
        {'dur': 8.2, 'ep': 'VIDÉO 2 / 4', 'head': '4. Score de Glasgow dans Mesures', 'sub': 'Sélection du patient obligatoire + alerte coma <= 8', 'fg': _v2_scene5},
        {'dur': 5.8, 'ep': 'VIDÉO 2 / 4', 'head': 'Zéro Oubli au Tour de Salle', 'sub': 'Tout l\'historique horodaté reste prêt dans le dossier', 'fg': _v2_scene6},
    ]
    compile_video("02_EL_VITA_Dossiers_Mesures_et_Glasgow.mp4", scenes)


def _v2_scene1(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("+", "1 Dossier = 1 Patient Isolé", "Aucune donnée d'un patient ne risque d'être mélangée avec un autre malade.", BLUE),
            ("+", "Constantes + Glasgow réunis", "Le module Mesures regroupe paramètres vitaux, courbes et évaluation neurologique GCS.", TEAL),
            ("+", "Horodatage automatique", "Chaque saisie enregistre date et heure pour suivre l'évolution en garde.", GREEN_DARK),
        ], y_step=102)
    draw_fg_card(pdf, 20, 174, VW - 40, 404, "POURQUOI CENTRALISER CHAQUE PATIENT ?", BLUE, body)


def _v2_scene2(pdf: PDFDocument):
    def body(p, x, y, w, h):
        p.rounded_rect(x + 14, y, w - 28, 76, r=10, fill=NAVY, stroke=TEAL, lw=1.0)
        p.draw_text("Mme Claire B. (68 ans • Pneumologie)", x + 24, y + 20, size=9.0, color=WHITE, font='F2')
        p.draw_text("Motif : PFLA • Chambre 214", x + 24, y + 36, size=7.8, color=BLUE_LIGHT, font='F1')
        p.draw_text("3 const.  •  2 GCS  •  2 bilans  •  1 obs.", x + 24, y + 54, size=7.6, color=MINT_SOFT, font='F2')

        draw_fg_bullets(p, x, y + 92, w, [
            ("1", "Créer un dossier (alias anonyme)", "Nom/initiales, âge, sexe, service, chambre et motif d'admission.", BLUE),
            ("2", "Bouton 'Voir Dossier'", "Consulte l'ensemble des constantes, GCS, bilans biologiques et observations en un coup d'oeil.", TEAL),
            ("3", "Bouton 'Sélectionner'", "Active ce patient dans Mesures, Analyses et Clinique simultanément.", GREEN_DARK),
        ], y_step=84)
    draw_fg_card(pdf, 20, 174, VW - 40, 416, "GESTION DES DOSSIERS PATIENTS", TEAL, body)


def _v2_scene3(pdf: PDFDocument):
    def body(p, x, y, w, h):
        p.rounded_rect(x + 14, y, w - 28, 86, r=10, fill=(45/255, 22/255, 26/255), stroke=RED, lw=1.1)
        p.draw_text("ALERTE CRITIQUE DÉTECTÉE", x + 24, y + 20, size=8.6, color=RED_SOFT, font='F2')
        p.draw_text("PAM : 61.7 mmHg  |  IMC : 24.9 kg/m2", x + 24, y + 38, size=8.8, color=WHITE, font='F2')
        p.draw_paragraph("SpO2 88% (hypoxémie sévère) • Hypotension : risque d'état de choc.", x + 24, y + 54, w - 52, size=7.5, leading=10.2, color=RED_SOFT, font='F1')

        draw_fg_bullets(p, x, y + 100, w, [
            ("1", "Associe d'abord le dossier patient", "Choisis le patient dans la liste déroulante en haut de 'Mesures'.", BLUE),
            ("2", "Entre les 9 paramètres", "T°, SpO2, PAS, PAD, FC, FR, Glycémie, Poids et Taille.", TEAL),
            ("3", "Lecture pédagogique immédiate", "Vert (stable), Orange (vigilance), Rouge (urgence vitale).", ORANGE),
        ], y_step=82)
    draw_fg_card(pdf, 20, 174, VW - 40, 416, "CONSTANTES VITALES & CALCULS AUTO", BLUE, body)


def _v2_scene4(pdf: PDFDocument):
    def body(p, x, y, w, h):
        # Courbe stylisée SVG-like
        p.rounded_rect(x + 14, y, w - 28, 146, r=10, fill=NAVY, stroke=DARK_BORDER, lw=1.0)
        p.draw_text("Évolution Température (°C) & SpO2 (%)", x + 24, y + 20, size=8.4, color=BLUE_LIGHT, font='F2')
        for gy in (48, 78, 108, 130):
            p.line(x + 30, y + gy, x + w - 30, y + gy, stroke=DARK_BORDER, lw=0.6)
        # Courbe rouge T° qui baisse
        pts_t = [(x+40, y+52), (x+95, y+68), (x+150, y+92), (x+205, y+110), (x+260, y+118)]
        for i in range(len(pts_t)-1):
            p.line(pts_t[i][0], pts_t[i][1], pts_t[i+1][0], pts_t[i+1][1], stroke=RED, lw=2.2)
            p.circle(pts_t[i][0], pts_t[i][1], 3, fill=RED)
        p.circle(pts_t[-1][0], pts_t[-1][1], 3, fill=RED)
        # Courbe verte SpO2 qui remonte
        pts_s = [(x+40, y+122), (x+95, y+110), (x+150, y+86), (x+205, y+66), (x+260, y+56)]
        for i in range(len(pts_s)-1):
            p.line(pts_s[i][0], pts_s[i][1], pts_s[i+1][0], pts_s[i+1][1], stroke=GREEN, lw=2.2)
            p.circle(pts_s[i][0], pts_s[i][1], 3, fill=GREEN)
        p.circle(pts_s[-1][0], pts_s[-1][1], 3, fill=GREEN)

        draw_fg_bullets(p, x, y + 162, w, [
            ("1", "Sous-onglet 'Historique & Courbes'", "Dès 2 mesures enregistrées sur un patient, ses courbes réelles se tracent automatiquement.", TEAL),
            ("2", "Liste chronologique complète", "Toutes les mesures antérieures restent consultables en dessous du graphique.", BLUE),
        ], y_step=84)
    draw_fg_card(pdf, 20, 174, VW - 40, 412, "HISTORIQUE & COURBES ÉVOLUTIVES", GREEN_DARK, body)


def _v2_scene5(pdf: PDFDocument):
    def body(p, x, y, w, h):
        p.rounded_rect(x + 14, y, w - 28, 72, r=10, fill=(45/255, 22/255, 26/255), stroke=RED, lw=1.2)
        p.draw_text("GCS Total : 7 / 15  (E2 V2 M3)", x + 24, y + 22, size=11.0, color=WHITE, font='F2')
        p.draw_paragraph("COMA GRAVE (GCS <= 8) : risque de perte de protection des voies aériennes -> Intubation urgente.", x + 24, y + 38, w - 52, size=7.6, leading=10.5, color=RED_SOFT, font='F1')

        draw_fg_bullets(p, x, y + 86, w, [
            ("!", "Dossier Patient OBLIGATOIRE", "Le score de Glasgow est dans 'Mesures'. Impossible d'enregistrer sans avoir choisi un patient.", RED),
            ("1", "Coter E (1-4), V (1-5) et M (1-6)", "Le score total /15 s'affiche avec le détail E/V/M et son interprétation.", BLUE),
            ("2", "Historique Neurologique Daté", "Chaque évaluation est conservée dans le dossier pour surveiller toute aggravation.", TEAL),
        ], y_step=84)
    draw_fg_card(pdf, 20, 174, VW - 40, 416, "SCORE DE GLASGOW (GCS) DANS MESURES", RED, body)


def _v2_scene6(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("OK", "Un dossier fiable et propre", "Toutes tes constantes et tes scores de Glasgow sont datés et rattachés au bon malade.", GREEN_DARK),
            ("->", "Étape suivante : Analyses Biologiques", "Découvre comment interpréter 5 bilans complets et synchroniser vers l'observation dans la Vidéo 3.", BLUE),
        ], y_step=120)
        draw_tap_callout(pdf, x + 24, y + 266, w - 48, "Suivant : Vidéo 3 — Analyses & Clinique", TEAL)
    draw_fg_card(pdf, 20, 176, VW - 40, 396, "RÉSUMÉ DU MODULE MESURES", TEAL, body)


# ==============================================================================
# VIDÉO 3 — ANALYSES BIOLOGIQUES (5) & CLINIQUE + TOUR DE SALLE
# ==============================================================================

def build_video_3():
    scenes = [
        {'dur': 5.8, 'ep': 'VIDÉO 3 / 4', 'head': 'Analyses & Tour de Salle', 'sub': 'Interpréter la biologie et préparer sa présentation clinique', 'fg': _v3_scene1},
        {'dur': 8.2, 'ep': 'VIDÉO 3 / 4', 'head': '5 Bilans Biologiques Complets', 'sub': 'NFS, Rein/Iono, Foie/Pancréas, Hémostase & Bandelette Urinaire', 'fg': _v3_scene2},
        {'dur': 7.5, 'ep': 'VIDÉO 3 / 4', 'head': 'Une Conclusion Prête à Retenir', 'sub': 'Des associations biologiques claires archivées dans le dossier', 'fg': _v3_scene3},
        {'dur': 7.5, 'ep': 'VIDÉO 3 / 4', 'head': 'Synchronisation dans Clinique', 'sub': 'Constantes, Glasgow et bilans importés sans double saisie', 'fg': _v3_scene4},
        {'dur': 8.4, 'ep': 'VIDÉO 3 / 4', 'head': 'Formation Clinique (Sections A à F)', 'sub': 'Interrogatoire, Organisation, Synthèse, Transitions & Oral', 'fg': _v3_scene5},
        {'dur': 5.8, 'ep': 'VIDÉO 3 / 4', 'head': 'Routine Parfaite en Stage', 'sub': 'De l\'entrée du malade à la présentation orale au senior', 'fg': _v3_scene6},
    ]
    compile_video("03_EL_VITA_Analyses_et_Clinique_Tour_de_Salle.mp4", scenes)


def _v3_scene1(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("1", "Ne plus lire les chiffres isolément", "EL VITA croise les valeurs entre elles (ex: Hb + GB + Plaquettes) pour poser une synthèse logique.", BLUE),
            ("2", "Rattacher au dossier patient", "Chaque conclusion biologique s'enregistre dans le dossier et nourrit l'Observation Médicale.", TEAL),
            ("3", "Se former en continu", "6 volets complets pour maîtriser l'interrogatoire et parler avec assurance au tour de salle.", GREEN_DARK),
        ], y_step=102)
    draw_fg_card(pdf, 20, 174, VW - 40, 404, "DU LABORATOIRE AU LIT DU MALADE", BLUE, body)


def _v3_scene2(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("A", "NFS Complète (6 volets)", "Anémie micro/macrocytaire, bicytopénie, pancytopénie, agranulocytose (PNN < 0.5).", BLUE),
            ("B", "UECréat / Ionogramme & Rein", "DFG Cockcroft-Gault, ratio Urée/Créat (IRA fonctionnelle) & Trou Anionique.", TEAL),
            ("C", "Bilan Hépatique & Pancréatique", "Cytolyse vs cholestase, atteinte mixte et pancréatite aiguë (Lipase > 3N).", GREEN_DARK),
            ("D", "Hémostase & Inflammation", "CRP, PCT, TP, INR, ratio TCA, Fibrinogène, D-Dimères & alerte CIVD.", ORANGE),
            ("E", "Bandelette Urinaire (BU)", "Leuco + Nitrites (infection urinaire), protéinurie et alerte acidocétose (Glu+ + Cét+).", RED),
        ], y_step=66)
    draw_fg_card(pdf, 20, 174, VW - 40, 416, "LES 5 MOTEURS D'ANALYSES BIOLOGIQUES", TEAL, body)


def _v3_scene3(pdf: PDFDocument):
    def body(p, x, y, w, h):
        p.rounded_rect(x + 14, y, w - 28, 128, r=10, fill=NAVY, stroke=BLUE_LIGHT, lw=1.1)
        p.draw_text("Conclusion Médicale Générale (Exemple NFS) :", x + 24, y + 20, size=8.4, color=BLUE_LIGHT, font='F2')
        p.draw_paragraph(
            "« La NFS a objectivé une anémie microcytaire hypochrome associée à une thrombocytose réactionnelle, avec conservation de la lignée blanche. »",
            x + 24, y + 36, w - 52, size=8.0, leading=11.5, color=WHITE, font='F1'
        )
        p.draw_text("Orientations : Doser Ferritine + CRP, rechercher saignement.", x + 24, y + 92, size=7.6, color=MINT_SOFT, font='F2')
        p.draw_text("✓ Bilan archivé dans le dossier du patient", x + 24, y + 112, size=7.6, color=GREEN, font='F2')

        draw_fg_bullets(p, x, y + 144, w, [
            ("+", "Paramètres normaux & perturbés séparés", "Visualise immédiatement ce qui est normal et ce qui alerte.", BLUE),
            ("+", "Mécanismes physiopathologiques", "Un bloc dépliable explique le 'pourquoi' pour mieux retenir.", TEAL),
        ], y_step=84)
    draw_fg_card(pdf, 20, 174, VW - 40, 414, "INTERPRÉTATION STRUCTURÉE & ARCHIVÉE", BLUE, body)


def _v3_scene4(pdf: PDFDocument):
    def body(p, x, y, w, h):
        p.rounded_rect(x + 14, y, w - 28, 102, r=10, fill=(10/255, 48/255, 46/255), stroke=TEAL, lw=1.1)
        p.draw_text("Dossier Synchronisé : Mme Claire B.", x + 24, y + 20, size=8.6, color=MINT_SOFT, font='F2')
        p.draw_paragraph(
            "• Motif importé automatiquement\n• Dernières constantes & Score de Glasgow GCS 15/15\n• 3 derniers bilans biologiques rattachés",
            x + 24, y + 36, w - 52, size=7.6, leading=12.0, color=WHITE, font='F1'
        )
        draw_fg_bullets(p, x, y + 118, w, [
            ("1", "Synchronisation non destructive", "Pré-remplit les champs vides sans jamais effacer tes propres phrases.", TEAL),
            ("2", "Rédiger l'Observation Structurée", "Génère un compte-rendu complet sauvegardé dans le dossier.", BLUE),
            ("3", "Mise à Jour Clinique (J2, J3...)", "Note l'évolution quotidienne du malade jour après jour.", GREEN_DARK),
        ], y_step=76)
    draw_fg_card(pdf, 20, 174, VW - 40, 414, "CLINIQUE : OBSERVATION SYNCHRONISÉE", GREEN_DARK, body)


def _v3_scene5(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("A", "Interrogatoire (14 points)", "Phrases pros, réponses types, questions de relance & mini-QCM.", BLUE),
            ("B", "Organisation (9 rubriques)", "Exemples concrets + 6 exercices de tri pour ne plus rien mélanger.", TEAL),
            ("C", "Observation (4 ateliers)", "Comparatifs 'À proscrire vs Bonne rédaction' avec corrections.", GREEN_DARK),
            ("D", "Synthèse (5 cas) & E. Transitions (11)", "Raisonnement sur 5 cas fictifs + revue des 10 appareils.", ORANGE),
            ("F", "Présentation Orale au Senior", "Grille interactive 8 critères + 5 scripts comparatifs guidés.", NAVY_MID),
        ], y_step=66)
    draw_fg_card(pdf, 20, 174, VW - 40, 416, "FORMATION CLINIQUE PÉDAGOGIQUE (A -> F)", BLUE, body)


def _v3_scene6(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("1", "Dossier Patient créé", "Avec alias respectant le secret médical.", BLUE),
            ("2", "Constantes + Glasgow saisis", "Alertes vérifiées et courbes prêtes.", TEAL),
            ("3", "Bilans interprétés", "Conclusions archivées dans Analyses.", GREEN_DARK),
            ("4", "Observation + Présentation Orale", "Prête en 30 sec devant le chef de service !", ORANGE),
        ], y_step=76)
        draw_tap_callout(pdf, x + 22, y + 314, w - 44, "Suivant : Vidéo 4 — Révisions & Install", BLUE)
    draw_fg_card(pdf, 20, 174, VW - 40, 414, "TON CIRCUIT GAGNANT EN STAGE", TEAL, body)


# ==============================================================================
# VIDÉO 4 — SÉMIOLOGIE (304 / 31 / 90) & INSTALLATION ANDROID / IPHONE
# ==============================================================================

def build_video_4():
    scenes = [
        {'dur': 5.8, 'ep': 'VIDÉO 4 / 4', 'head': 'Sémiologie, Cours & QCM', 'sub': 'Réviser partout hors-ligne + Installer sur Android et iPhone', 'fg': _v4_scene1},
        {'dur': 7.5, 'ep': 'VIDÉO 4 / 4', 'head': '304 Fiches Sémiologiques', 'sub': '14 spécialités médicales & 14 rubriques standardisées', 'fg': _v4_scene2},
        {'dur': 7.8, 'ep': 'VIDÉO 4 / 4', 'head': '31 Cours & 90 QCM Officiels', 'sub': 'Tableaux, cas fictifs corrigés & score en direct', 'fg': _v4_scene3},
        {'dur': 7.5, 'ep': 'VIDÉO 4 / 4', 'head': 'Installer sur ANDROID (.APK)', 'sub': 'En 3 étapes simples, 100% gratuit et hors-ligne', 'fg': _v4_scene4},
        {'dur': 8.2, 'ep': 'VIDÉO 4 / 4', 'head': 'Installer sur iPHONE (Safari)', 'sub': 'Une vraie appli plein écran sans passer par l\'App Store', 'fg': _v4_scene5},
        {'dur': 6.0, 'ep': 'VIDÉO 4 / 4', 'head': 'Rejoins l\'Expérimentation !', 'sub': 'Teste, partage à tes confrères & donne ton avis', 'fg': _v4_scene6},
    ]
    compile_video("04_EL_VITA_Semiologie_QCM_et_Installation.mp4", scenes)


def _v4_scene1(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("304", "Fiches de Sémiologie", "Couvrant 14 spécialités cliniques avec 14 rubriques fixes.", BLUE),
            ("31", "Cours Médicaux Universitaires", "Avec tableaux synthétiques et cas cliniques fictifs corrigés.", TEAL),
            ("90", "QCM Officiels Interactifs", "Pour s'entraîner avant les examens avec perles cliniques.", GREEN_DARK),
        ], y_step=102)
    draw_fg_card(pdf, 20, 174, VW - 40, 404, "TA BIBLIOTHÈQUE MÉDICALE DE POCHE", BLUE, body)


def _v4_scene2(pdf: PDFDocument):
    def body(p, x, y, w, h):
        p.rounded_rect(x + 14, y, w - 28, 70, r=10, fill=NAVY, stroke=BLUE, lw=1.0)
        p.draw_text("Filtres : Cardio • Pneumo • Neuro • Digestif • Dermato...", x + 24, y + 20, size=7.6, color=BLUE_LIGHT, font='F2')
        p.draw_text("Douleur Thoracique Angineuse", x + 24, y + 38, size=10.0, color=WHITE, font='F2')
        p.draw_text("1. Définition -> 6. Recherche -> 12. Gravité -> 14. Points Clés", x + 24, y + 54, size=7.2, color=MINT_SOFT, font='F1')

        draw_fg_bullets(p, x, y + 86, w, [
            ("1", "Filtre par spécialité (14)", "Cardio, Pneumo, Neuro, Digestif, Uro/Néphro, Hémato, Endoc, Pédiatrie, Gynéco, Dermato, Infectio, Psychiatrie...", BLUE),
            ("2", "Barre de recherche instantanée", "Tape un symptôme ou syndrome et trouve sa fiche en 1 seconde.", TEAL),
            ("3", "Structure uniforme en 14 points", "Même plan clair pour retenir vite l'interrogatoire, l'examen et les pièges.", GREEN_DARK),
        ], y_step=84)
    draw_fg_card(pdf, 20, 174, VW - 40, 414, "304 FICHES SÉMIOLOGIQUES", TEAL, body)


def _v4_scene3(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("A", "Sous-onglet 'Cours Médicaux (31)'", "14 rubriques complètes + tableaux récapitulatifs clairs par pathologie.", BLUE),
            ("B", "Cas Fictifs Pédagogiques Corrigés", "Lis la mise en situation, réfléchis, puis déplie la correction détaillée.", TEAL),
            ("C", "Sous-onglet 'Quiz QCM (90)'", "4 choix (A/B/C/D), score en direct, explication et Perle Clinique à retenir.", GREEN_DARK),
        ], y_step=102)
    draw_fg_card(pdf, 20, 174, VW - 40, 404, "31 COURS & 90 QCM INTERACTIFS", GREEN_DARK, body)


def _v4_scene4(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("1", "Télécharge 'EL VITA.apk'", "Depuis le message WhatsApp, Telegram ou e-mail reçu.", GREEN_DARK),
            ("2", "Autorise l'installation", "Ouvre le fichier -> si alerte : Paramètres -> coche 'Autoriser cette source'.", BLUE),
            ("3", "Installe et Ouvre", "L'icône EL VITA apparaît sur ton téléphone. Fonctionne 100% hors-ligne !", TEAL),
        ], y_step=94)
        draw_tap_callout(pdf, x + 22, y + 292, w - 44, "Compatible Android — Zéro connexion requise", GREEN_DARK)
    draw_fg_card(pdf, 20, 174, VW - 40, 404, "INSTALLATION SUR ANDROID (.APK)", GREEN_DARK, body)


def _v4_scene5(pdf: PDFDocument):
    def body(p, x, y, w, h):
        draw_fg_bullets(p, x, y, w, [
            ("1", "Ouvre le lien dans SAFARI", "https://dossoualbert00-commits.github.io/elvita/", BLUE),
            ("2", "Appuie sur Partager (en bas)", "Le bouton carré avec la flèche qui pointe vers le haut.", TEAL),
            ("3", "Choisis 'Sur l'écran d'accueil'", "Puis valide sur 'Ajouter' en haut à droite.", GREEN_DARK),
        ], y_step=94)
        draw_tap_callout(pdf, x + 22, y + 292, w - 44, "S'ouvre en plein écran & 100% hors-ligne !", BLUE)
    draw_fg_card(pdf, 20, 174, VW - 40, 404, "INSTALLATION SUR IPHONE (SAFARI PWA)", BLUE, body)


def _v4_scene6(pdf: PDFDocument):
    pdf.new_page()
    x, y, w, h = 22, 176, VW - 44, 396
    pdf.rounded_rect(x, y, w, h, r=16, fill=DARK_CARD, stroke=TEAL, lw=1.5)
    pdf.draw_image('founder', x + (w - 82)/2, y + 20, 82, 123)
    pdf.draw_text("ALBERT DOSSOU", VW/2, y + 164, size=14.0, color=WHITE, font='F2', align='center')
    pdf.draw_text("Fondateur & Propriétaire d'EL VITA", VW/2, y + 181, size=8.6, color=BLUE_LIGHT, font='F2', align='center')
    pdf.draw_paragraph(
        "C'est volontaire : même si tu n'as pas besoin de l'utiliser tous les jours, essaie EL VITA quelques minutes et aide-nous à l'expérimenter !",
        x + 20, y + 204, w - 40, size=8.2, leading=11.8, color=SLATE_200, font='F1', align='center'
    )
    draw_tap_callout(pdf, x + 22, y + 272, w - 44, "Retours : dossoualbert00@gmail.com", BLUE)
    draw_tap_callout(pdf, x + 22, y + 312, w - 44, "iPhone : dossoualbert00-commits.github.io/elvita/", TEAL)


# ==============================================================================
# MOTEUR D'ANIMATION & COMPILATION FFMPEG FLUIDE 30 FPS
# ==============================================================================

def compile_video(filename: str, scenes: list):
    tag = filename.replace('.mp4', '')
    work = os.path.join(TMP_DIR, tag)
    os.makedirs(work, exist_ok=True)

    # 1. Rendre toutes les couches BG et FG en PDF -> PNG haute définition (720x1280)
    bg_pdf = PDFDocument()
    load_base_images(bg_pdf)
    total_s = len(scenes)
    for idx, sc in enumerate(scenes):
        draw_scene_bg(bg_pdf, sc['ep'], idx, total_s, sc['head'], sc['sub'])
    render_pdf_layer(bg_pdf, f"{work}/bg.pdf", f"{work}/bg", alpha=False)

    fg_pdf = PDFDocument()
    load_base_images(fg_pdf)
    for sc in scenes:
        sc['fg'](fg_pdf)
    render_pdf_layer(fg_pdf, f"{work}/fg.pdf", f"{work}/fg", alpha=True)

    # 2. Générer la piste audio ambiante douce synchronisée aux transitions
    durs = [sc['dur'] for sc in scenes]
    xfade_d = 0.50
    wav_path = f"{work}/audio.wav"
    generate_ambient_audio(wav_path, durs, xfade_dur=xfade_d)
    total_vid_dur = sum(durs) - xfade_d * (len(durs) - 1)

    # 3. Construire chaque clip animé à 30 fps (entrée fluide glissée + micro-respiration)
    clip_paths = []
    for idx, sc in enumerate(scenes):
        bg_img = f"{work}/bg_{idx+1}.png"
        fg_img = f"{work}/fg_{idx+1}.png"
        clip_out = f"{work}/clip_{idx+1}.mp4"
        d = sc['dur']
        # Glissement vers le haut avec décélération quadratique sur 0.55s + respiration douce +/-2px
        y_expr = "if(lt(t,0.55), 56*pow(1-t/0.55,2), 2.5*sin(2*PI*(t-0.55)/3.8))"
        fg_filter = (
            f"[1:v]format=rgba,fade=t=in:st=0:d=0.35:alpha=1[fga];"
            f"[0:v][fga]overlay=x=0:y='{y_expr}':shortest=1,fps=30,format=yuv420p[v]"
        )
        subprocess.run([
            "ffmpeg", "-y", "-loglevel", "error",
            "-loop", "1", "-t", f"{d:.2f}", "-i", bg_img,
            "-loop", "1", "-t", f"{d:.2f}", "-i", fg_img,
            "-filter_complex", fg_filter,
            "-map", "[v]",
            "-c:v", "libx264", "-preset", "fast", "-crf", "19",
            "-pix_fmt", "yuv420p",
            clip_out
        ], check=True)
        clip_paths.append(clip_out)

    # 4. Chaîner les clips avec xfade (transition smoothleft/slideleft fluide) + barre de progression animée + audio
    inputs = []
    for cp in clip_paths:
        inputs.extend(["-i", cp])
    inputs.extend(["-i", wav_path])

    filter_parts = []
    curr_label = "[0:v]"
    offset = 0.0
    transitions = ["smoothleft", "smoothup", "smoothleft", "fade", "smoothleft"]
    for i in range(1, len(clip_paths)):
        offset += durs[i - 1] - xfade_d
        out_label = f"[xf{i}]"
        tr = transitions[(i - 1) % len(transitions)]
        filter_parts.append(f"{curr_label}[{i}:v]xfade=transition={tr}:duration={xfade_d:.2f}:offset={offset:.2f}{out_label}")
        curr_label = out_label

    # Barre de progression animée en bas (de 0 à 720px sur toute la durée de la vidéo)
    # Utilisation de drawbox avec un overlay couleur animé via crop/scale ou plusieurs segments?
    # Note: drawbox w= ne supporte pas t dans toutes les versions; utilisons color=c=#38BDF8 overlay x='(t/TOTAL-1)*720'!
    audio_idx = len(clip_paths)
    progress_filter = (
        f"color=c=#38BDF8:s=720x8:d={total_vid_dur:.2f}[pbar];"
        f"{curr_label}[pbar]overlay=x='-720+(t/{total_vid_dur:.2f})*720':y=1272:shortest=1,format=yuv420p[vfinal]"
    )
    filter_parts.append(progress_filter)
    full_filter = ";".join(filter_parts)

    out_mp4 = os.path.join(OUT_DIR, filename)
    subprocess.run([
        "ffmpeg", "-y", "-loglevel", "error",
        *inputs,
        "-filter_complex", full_filter,
        "-map", "[vfinal]",
        "-map", f"{audio_idx}:a",
        "-c:v", "libx264", "-preset", "medium", "-crf", "22",
        "-pix_fmt", "yuv420p", "-movflags", "+faststart",
        "-c:a", "aac", "-b:a", "96k", "-shortest",
        out_mp4
    ], check=True)

    sz_kb = os.path.getsize(out_mp4) // 1024
    print(f"Video generated: {out_mp4} ({sz_kb} KB, ~{total_vid_dur:.1f}s)")


def main():
    prepare_assets()
    build_video_1()
    build_video_2()
    build_video_3()
    build_video_4()
    shutil.rmtree(TMP_DIR, ignore_errors=True)
    print("ALL 4 TUTORIAL VIDEOS GENERATED SUCCESSFULLY.")


if __name__ == "__main__":
    main()
