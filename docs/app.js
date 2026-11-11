/**
 * EL VITA — Compagnon Clinique et Pédagogique Médical
 * Version PWA Complète (100% équivalente Android & iOS Safari)
 */

// 1. Initialisation & Persistance Locale
const STORAGE_KEY_PROFILE = 'elvita_web_student_profile';
const STORAGE_KEY_THEME = 'elvita_web_theme';

const defaultProfile = {
  name: "Étudiant en médecine",
  studyYear: "DFASM 2 (5e Année)",
  university: "Faculté des Sciences de la Santé",
  hospital: "CHU - Pôle Médecine Interne"
};

let currentProfile = JSON.parse(localStorage.getItem(STORAGE_KEY_PROFILE)) || defaultProfile;

// 2. Gestion du Thème (Clair / Sombre)
function initTheme() {
  const savedTheme = localStorage.getItem(STORAGE_KEY_THEME) || 'dark';
  document.documentElement.setAttribute('data-theme', savedTheme);
  const themeBtn = document.getElementById('theme-toggle-btn');
  if (themeBtn) {
    themeBtn.textContent = savedTheme === 'dark' ? '☀️' : '🌙';
  }
}

function toggleTheme() {
  const current = document.documentElement.getAttribute('data-theme') || 'dark';
  const next = current === 'dark' ? 'light' : 'dark';
  document.documentElement.setAttribute('data-theme', next);
  localStorage.setItem(STORAGE_KEY_THEME, next);
  const themeBtn = document.getElementById('theme-toggle-btn');
  if (themeBtn) {
    themeBtn.textContent = next === 'dark' ? '☀️' : '🌙';
  }
}

// 3. Navigation par Onglets (5 Onglets Principaux)
function switchTab(tabId) {
  document.querySelectorAll('.tab-content').forEach(el => el.classList.remove('active'));
  document.querySelectorAll('.nav-item').forEach(el => el.classList.remove('active'));

  const targetContent = document.getElementById(`tab-${tabId}`);
  const targetNav = document.getElementById(`nav-${tabId}`);
  if (targetContent) targetContent.classList.add('active');
  if (targetNav) targetNav.classList.add('active');

  window.scrollTo({ top: 0, behavior: 'smooth' });
}

function switchSubTab(parentTab, subId) {
  const container = document.getElementById(`tab-${parentTab}`);
  if (!container) return;

  container.querySelectorAll('.subtab-panel').forEach(el => el.style.display = 'none');
  container.querySelectorAll('.pill-btn').forEach(el => el.classList.remove('active'));

  const targetPanel = document.getElementById(`${parentTab}-sub-${subId}`);
  const targetBtn = document.getElementById(`pill-${parentTab}-${subId}`);
  if (targetPanel) targetPanel.style.display = 'block';
  if (targetBtn) targetBtn.classList.add('active');
}

// 4. Modales
function openModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.add('active');
}

function closeModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.remove('active');
}

// 5. MOTEUR CLINIQUE : Constantes Vitales Complètes
function calculateVitals() {
  const temp = parseFloat(document.getElementById('vital-temp').value) || 37.0;
  const pas = parseInt(document.getElementById('vital-pas').value) || 120;
  const pad = parseInt(document.getElementById('vital-pad').value) || 80;
  const fc = parseInt(document.getElementById('vital-fc').value) || 75;
  const fr = parseInt(document.getElementById('vital-fr').value) || 16;
  const spo2 = parseInt(document.getElementById('vital-spo2').value) || 98;
  const gly = parseFloat(document.getElementById('vital-gly').value) || 1.0;
  const weight = parseFloat(document.getElementById('vital-weight').value) || 70;
  const height = parseFloat(document.getElementById('vital-height').value) || 175;

  const pam = (pas + (2 * pad)) / 3;
  const imc = weight / Math.pow(height / 100, 2);

  let alertLevel = 'NORMAL';
  let badgeText = 'Paramètres Stables';
  let points = [];

  if (temp >= 38.5) {
    points.push({ name: 'Température', obs: `${temp}°C — Syndrome fébrile franc`, level: temp >= 39.5 ? 'CRITICAL' : 'MODERATE' });
  } else if (temp < 35.5) {
    points.push({ name: 'Température', obs: `${temp}°C — Hypothermie`, level: 'CRITICAL' });
  }

  if (pas < 90 || pam < 65) {
    points.push({ name: 'Hémodynamique', obs: `PAS ${pas} mmHg / PAM ${pam.toFixed(1)} mmHg — Hypotension artérielle / État de choc potentiel`, level: 'CRITICAL' });
  } else if (pas >= 180 || pad >= 110) {
    points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Crise hypertensive sévère`, level: 'CRITICAL' });
  } else if (pas >= 140 || pad >= 90) {
    points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Hypertension artérielle modérée`, level: 'MODERATE' });
  }

  if (spo2 < 90) {
    points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Hypoxémie sévère requérant oxygénothérapie urgente`, level: 'CRITICAL' });
  } else if (spo2 <= 94) {
    points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Hypoxémie modérée`, level: 'MODERATE' });
  }

  if (fr > 24) {
    points.push({ name: 'Respiration', obs: `FR ${fr}/min — Polypnée / tachypnée`, level: fr >= 30 ? 'CRITICAL' : 'MODERATE' });
  } else if (fr < 10) {
    points.push({ name: 'Respiration', obs: `FR ${fr}/min — Bradypnée critique`, level: 'CRITICAL' });
  }

  if (fc >= 120) {
    points.push({ name: 'Fréquence cardiaque', obs: `FC ${fc} bpm — Tachycardie marquée`, level: 'MODERATE' });
  } else if (fc < 50) {
    points.push({ name: 'Fréquence cardiaque', obs: `FC ${fc} bpm — Bradycardie`, level: 'MODERATE' });
  }

  if (points.some(p => p.level === 'CRITICAL')) {
    alertLevel = 'CRITICAL';
    badgeText = 'URGENCE CLINIQUE : Anomalies Vitales Critiques';
  } else if (points.some(p => p.level === 'MODERATE')) {
    alertLevel = 'MODERATE';
    badgeText = 'Vigilance Clinique : Paramètres Perturbés';
  }

  const resultContainer = document.getElementById('vitals-result');
  resultContainer.innerHTML = `
    <div class="alert-badge ${alertLevel}">
      <span>●</span> ${badgeText}
    </div>
    <div class="form-row mb-3">
      <div class="section-callout">
        <strong>PAM (Pression Moyenne) :</strong> ${pam.toFixed(1)} mmHg
      </div>
      <div class="section-callout">
        <strong>IMC :</strong> ${imc.toFixed(1)} kg/m² ${imc >= 30 ? '(Obésité)' : imc >= 25 ? '(Surpoids)' : '(Normal)'}
      </div>
    </div>
    <h4 style="font-size: 14px; font-weight: 700; margin-bottom: 8px;">Points Systématiques d'Examen :</h4>
    ${points.length > 0 ? points.map(p => `
      <div class="section-callout ${p.level === 'CRITICAL' ? 'danger' : 'warning'}">
        <strong>${p.name} :</strong> ${p.obs}
      </div>
    `).join('') : '<div class="section-callout">Tous les paramètres vitaux analysés sont physiologiques.</div>'}
  `;
  resultContainer.style.display = 'block';
}

// 6. MOTEUR CLINIQUE : Interprétation Complète NFS en 6 Volets
function calculateNFS() {
  const hb = parseFloat(document.getElementById('nfs-hb').value) || 13.5;
  const ht = parseFloat(document.getElementById('nfs-ht').value) || 40.0;
  const gr = parseFloat(document.getElementById('nfs-gr').value) || 4.5;
  const gb = parseFloat(document.getElementById('nfs-gb').value) || 7.0;
  const pnn = parseFloat(document.getElementById('nfs-pnn').value) || 4.2;
  const lympho = parseFloat(document.getElementById('nfs-lympho').value) || 2.0;
  const plaquettes = parseFloat(document.getElementById('nfs-plt').value) || 250.0;
  const retic = parseFloat(document.getElementById('nfs-retic').value) || null;

  const vgm = (ht * 10) / gr;
  const ccmh = (hb / ht) * 100;

  let isAnemia = hb < 12.0;
  let isMicrocytic = vgm < 80.0;
  let isMacrocytic = vgm > 100.0;
  let isHypochromic = ccmh < 32.0;
  let isThrombopenia = plaquettes < 150.0;
  let isThrombocytosis = plaquettes > 400.0;
  let isHyperleukocytosis = gb > 10.0;
  let isLeukopenia = gb < 4.0;
  let isNeutropenia = pnn < 1.5;
  let isAgranulocytosis = pnn < 0.5;

  let alertLevel = 'NORMAL';
  if (hb < 8.0 || isAgranulocytosis || plaquettes < 20.0) {
    alertLevel = 'CRITICAL';
  } else if (isAnemia || isThrombopenia || isThrombocytosis || isHyperleukocytosis || isLeukopenia) {
    alertLevel = 'MODERATE';
  }

  let conclusionText = "";
  if (!isAnemia && !isHyperleukocytosis && !isLeukopenia && !isThrombopenia && !isThrombocytosis) {
    conclusionText = "La NFS ne montre pas d'anomalie hématologique significative. Les trois lignées sanguines présentent des valeurs globalement dans les intervalles de référence.";
  } else if (isAnemia && isThrombopenia && isLeukopenia) {
    conclusionText = "La NFS a objectivé une pancytopénie associant une anémie, une leucopénie et une thrombopénie, nécessitant une exploration médullaire urgente.";
  } else if (isAnemia && isThrombopenia) {
    conclusionText = "La NFS a objectivé une bicytopénie associant une anémie et une thrombopénie avec conservation relative de la lignée blanche.";
  } else if (isAnemia && isThrombocytosis) {
    conclusionText = `La NFS a objectivé une anémie ${isMicrocytic ? 'microcytaire' : 'normocytaire'} associée à une thrombocytose réactionnelle évoquant en priorité une carence martiale ou un syndrome inflammatoire.`;
  } else if (isAnemia) {
    conclusionText = `La NFS a objectivé une anémie ${isMicrocytic ? 'microcytaire' : isMacrocytic ? 'macrocytaire' : 'normocytaire'} ${isHypochromic ? 'hypochrome' : 'normochrome'} isolée avec conservation des lignées blanche et plaquettaire.`;
  } else if (isHyperleukocytosis) {
    conclusionText = `La NFS a objectivé une hyperleucocytose (${gb} G/L) à prédominance neutrophile avec conservation des lignées rouge et plaquettaire.`;
  } else if (isThrombopenia) {
    conclusionText = `La NFS a objectivé une thrombopénie isolée (${plaquettes} G/L) sans anomalie significative des autres lignées sanguines.`;
  } else {
    conclusionText = "La NFS présente des variations hématologiques modérées à intégrer au contexte clinique.";
  }

  const redSummary = isAnemia 
    ? `Anémie ${isMicrocytic ? 'microcytaire (VGM ' + vgm.toFixed(1) + ' fL)' : isMacrocytic ? 'macrocytaire (VGM ' + vgm.toFixed(1) + ' fL)' : 'normocytaire'}, ${isHypochromic ? 'hypochrome (CCMH ' + ccmh.toFixed(1) + '%)' : 'normochrome'}${retic ? (retic < 120 ? ' arégénérative' : ' régénérative') : ''}.`
    : `Lignée rouge normale (Hb ${hb} g/dL, VGM ${vgm.toFixed(1)} fL).`;

  const whiteSummary = isAgranulocytosis
    ? `AGRANULOCYTOSE MAJEURE (PNN ${pnn} G/L < 0.5 G/L) — Urgence vitale, risque de sepsis fulminant !`
    : isHyperleukocytosis
      ? `Hyperleucocytose à ${gb} G/L avec ${pnn} G/L de PNN.`
      : `Lignée blanche normale (GB ${gb} G/L, PNN ${pnn} G/L).`;

  const pltSummary = isThrombopenia
    ? `Thrombopénie à ${plaquettes} G/L ${plaquettes < 50 ? '(Risque hémorragique accru)' : ''}.`
    : isThrombocytosis
      ? `Thrombocytose à ${plaquettes} G/L (réactionnelle ou myéloproliférative).`
      : `Plaquettes dans les normes (${plaquettes} G/L).`;

  let orientations = [];
  if (isAnemia && isMicrocytic) orientations.push("Carence martiale (saignement occulte digestif, gynécologique ou d'apport)", "Syndrome inflammatoire chronique", "Thalassémie mineure");
  if (isAnemia && isMacrocytic) orientations.push("Carence en vitamine B12 (Biermer) ou folates", "Hépatopathie chronique / éthylisme", "Myélodysplasie");
  if (isHyperleukocytosis) orientations.push("Infection bactérienne systémique aiguë", "Syndrome inflammatoire ou nécrose tissulaire", "Stress métabolique aigu");
  if (isAgranulocytosis) orientations.push("Toxidermie médicamenteuse aiguë", "Infection virale sévère", "Aplasie médullaire");
  if (orientations.length === 0) orientations.push("Paramètres hématologiques dans les limites usuelles");

  const resultContainer = document.getElementById('nfs-result');
  resultContainer.innerHTML = `
    <div class="alert-badge ${alertLevel}">
      <span>●</span> Synthèse Médicale NFS
    </div>

    <div class="section-callout" style="border-left-color: #1976D2; background: rgba(25, 118, 210, 0.08);">
      <h4 style="color: var(--primary); font-weight: 700; margin-bottom: 4px;">1. Conclusion & Interprétation Générale :</h4>
      <p style="font-weight: 600; font-size: 14.5px;">${conclusionText}</p>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 8px;">2. Analyse Systématique des Lignées :</h4>
      <p class="mb-2"><strong>🔴 Lignée Rouge :</strong> ${redSummary}</p>
      <p class="mb-2"><strong>⚪ Lignée Blanche :</strong> ${whiteSummary}</p>
      <p><strong>🟡 Lignée Plaquettaire :</strong> ${pltSummary}</p>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 8px;">3. Orientations Étiologiques Principales :</h4>
      <ul style="padding-left: 20px; font-size: 13.5px;">
        ${orientations.map(o => `<li class="mb-2">${o}</li>`).join('')}
      </ul>
    </div>

    <div class="section-callout warning">
      <strong>4. Conduite à Tenir Clinique :</strong>
      ${isAgranulocytosis ? "Isolement protecteur immédiat, antibiothérapie probabiliste urgente si fièvre, arrêt immédiat des médicaments suspects." :
        isAnemia ? "Doser le bilan martial (Ferritine, coefficient de saturation de la transferrine) et la CRP avant toute supplémentation." :
        "Poursuivre la surveillance biologique en fonction de l'évolution clinique."}
    </div>
  `;
  resultContainer.style.display = 'block';
}

// 7. MOTEUR CLINIQUE : Score de Glasgow
function calculateGlasgow() {
  const e = parseInt(document.getElementById('gcs-e').value) || 4;
  const v = parseInt(document.getElementById('gcs-v').value) || 5;
  const m = parseInt(document.getElementById('gcs-m').value) || 6;
  const total = e + v + m;

  let severity = "Score normal / Conscience normale";
  let alert = "NORMAL";
  let pearl = "État de conscience physiologique.";

  if (total <= 8) {
    severity = "Coma sévère — Indication formelle à l'intubation trachéale protectrice";
    alert = "CRITICAL";
    pearl = "Glasgow <= 8 : risque majeur d'inhalation bronchique et perte des réflexes de déglutition.";
  } else if (total <= 12) {
    severity = "Altération modérée de la conscience / Somnolence pathologique";
    alert = "MODERATE";
    pearl = "Surveillance neuro rapprochée toutes les heures, recherche d'un foyer neurologique ou métabolique.";
  }

  const resultContainer = document.getElementById('gcs-result');
  resultContainer.innerHTML = `
    <div class="alert-badge ${alert}">
      <span>●</span> Score de Glasgow : ${total} / 15
    </div>
    <div class="section-callout ${alert === 'CRITICAL' ? 'danger' : 'warning'}">
      <strong>Interprétation :</strong> ${severity}
    </div>
    <div class="section-callout">
      <strong>Règle Sémiologique :</strong> ${pearl}
    </div>
  `;
  resultContainer.style.display = 'block';
}

// 8. MOTEUR CLINIQUE : Bilan Hépatique & Pancréatique
function calculateHepatic() {
  const alat = parseFloat(document.getElementById('hep-alat').value) || 25;
  const asat = parseFloat(document.getElementById('hep-asat').value) || 28;
  const pal = parseFloat(document.getElementById('hep-pal').value) || 80;
  const ggt = parseFloat(document.getElementById('hep-ggt').value) || 30;
  const bili = parseFloat(document.getElementById('hep-bili').value) || 12;
  const lipase = parseFloat(document.getElementById('hep-lipase').value) || 35;

  let cytolysis = alat > 50 || asat > 50;
  let severeCytolysis = alat > 500 || asat > 500;
  let cholestasis = pal > 130 || ggt > 60 || bili > 21;
  let pancreatitis = lipase > 180; // > 3N

  let alert = "NORMAL";
  if (severeCytolysis || pancreatitis) alert = "CRITICAL";
  else if (cytolysis || cholestasis) alert = "MODERATE";

  const res = document.getElementById('hep-result');
  res.innerHTML = `
    <div class="alert-badge ${alert}"><span>●</span> Bilan Hépatique & Pancréatique</div>
    <div class="section-callout ${severeCytolysis ? 'danger' : cytolysis ? 'warning' : ''}">
      <strong>Cytolyse hépatique :</strong> ${severeCytolysis ? 'CYTOLYSE AIGUË SÉVÈRE (> 10N) : Hépatite aiguë virale, toxique/médicamenteuse (Paracétamol) ou ischémique.' : cytolysis ? 'Cytolyse modérée à investiguer (stéatose, toxique, auto-immune).' : 'Absente (transaminases normales).'}
    </div>
    <div class="section-callout ${cholestasis ? 'warning' : ''}">
      <strong>Cholestase :</strong> ${cholestasis ? 'Syndrome de cholestase présent (GGT/PAL/Bilirubine élevées). Échographie hépato-biliaire indiquée.' : 'Absente.'}
    </div>
    <div class="section-callout ${pancreatitis ? 'danger' : ''}">
      <strong>Lipase :</strong> ${pancreatitis ? 'PANCRÉATITE AIGUË HAUTEMENT PROBABLE (Lipasémie > 3N). Scanner abdominal à 48-72h.' : 'Lipasémie normale.'}
    </div>
  `;
  res.style.display = 'block';
}

// 9. MOTEUR CLINIQUE : Bilan Rénal & Cockcroft-Gault
function calculateRenal() {
  const creat = parseFloat(document.getElementById('ren-creat').value) || 80;
  const age = parseInt(document.getElementById('ren-age').value) || 60;
  const weight = parseFloat(document.getElementById('ren-weight').value) || 70;
  const gender = document.getElementById('ren-gender').value;

  const k = gender === 'M' ? 1.23 : 1.04;
  const clCr = ((140 - age) * weight * k) / creat;

  let stage = "Fonction rénale normale (ClCr >= 90 ml/min)";
  let alert = "NORMAL";
  if (clCr < 15) { stage = "Insuffisance rénale terminale (Stade 5)"; alert = "CRITICAL"; }
  else if (clCr < 30) { stage = "Insuffisance rénale sévère (Stade 4)"; alert = "CRITICAL"; }
  else if (clCr < 60) { stage = "Insuffisance rénale modérée (Stade 3)"; alert = "MODERATE"; }
  else if (clCr < 90) { stage = "Insuffisance rénale légère (Stade 2)"; alert = "MODERATE"; }

  const res = document.getElementById('ren-result');
  res.innerHTML = `
    <div class="alert-badge ${alert}"><span>●</span> Clairance Cockcroft-Gault : ${clCr.toFixed(1)} mL/min</div>
    <div class="section-callout ${alert === 'CRITICAL' ? 'danger' : alert === 'MODERATE' ? 'warning' : ''}">
      <strong>Stade rénal :</strong> ${stage}
    </div>
    <div class="section-callout">
      <strong>Recommandation :</strong> Adapter la posologie de tous les médicaments à élimination rénale.
    </div>
  `;
  res.style.display = 'block';
}

// 10. MOTEUR CLINIQUE : Coagulation & Inflammation
function calculateCoag() {
  const crp = parseFloat(document.getElementById('coag-crp').value) || 5;
  const tp = parseFloat(document.getElementById('coag-tp').value) || 95;
  const inr = parseFloat(document.getElementById('coag-inr').value) || 1.0;
  const ddim = parseFloat(document.getElementById('coag-ddim').value) || 300;

  let sepsis = crp > 100;
  let hypocoag = tp < 70 || inr > 1.5;
  let thrombus = ddim > 500;

  let alert = (sepsis || tp < 50 || inr > 3.0) ? 'CRITICAL' : (crp > 10 || hypocoag || thrombus) ? 'MODERATE' : 'NORMAL';

  const res = document.getElementById('coag-result');
  res.innerHTML = `
    <div class="alert-badge ${alert}"><span>●</span> Bilan Hémostase & Inflammation</div>
    <div class="section-callout ${sepsis ? 'danger' : crp > 10 ? 'warning' : ''}">
      <strong>Syndrome inflammatoire :</strong> ${crp > 10 ? `CRP élevée à ${crp} mg/L ${sepsis ? '(Infection bactérienne sévère / Sepsis probable)' : ''}` : 'CRP normale (< 5 mg/L)'}
    </div>
    <div class="section-callout ${hypocoag ? 'warning' : ''}">
      <strong>Hémostase (TP/INR) :</strong> ${hypocoag ? `Hypocoagulabilité (TP ${tp}%, INR ${inr}). Risque hémorragique.` : 'Bilan de coagulation physiologique.'}
    </div>
    <div class="section-callout ${thrombus ? 'warning' : ''}">
      <strong>D-Dimères :</strong> ${thrombus ? `D-Dimères élevés (${ddim} ng/mL). Permet d'éliminer une EP/TVP uniquement s'ils sont < 500 ng/mL.` : 'D-Dimères normaux : élimine avec une forte valeur prédictive négative une thrombose veineuse profonde / embolie.'}
    </div>
  `;
  res.style.display = 'block';
}

// 11. SÉMIOLOGIE : Affichage des FICHES MÉDICALES (29 Fiches Complètes avec 14 sections)
let currentApparatusFilter = 'all';

function setApparatusFilter(cat) {
  currentApparatusFilter = cat;
  document.querySelectorAll('.apparatus-pill').forEach(btn => btn.classList.remove('active'));
  const activeBtn = document.getElementById(`pill-app-${cat}`);
  if (activeBtn) activeBtn.classList.add('active');

  renderSemiologyView();
}

function switchSemiologyMode(mode) {
  document.getElementById('semiology-mode-sheets-btn').classList.toggle('active', mode === 'sheets');
  document.getElementById('semiology-mode-quiz-btn').classList.toggle('active', mode === 'quiz');

  document.getElementById('semiology-sheets-container').style.display = mode === 'sheets' ? 'block' : 'none';
  document.getElementById('semiology-quiz-container').style.display = mode === 'quiz' ? 'block' : 'none';

  renderSemiologyView();
}

function renderSemiologyView() {
  const isSheetsMode = document.getElementById('semiology-mode-sheets-btn').classList.contains('active');
  if (isSheetsMode) {
    renderSheets();
  } else {
    renderQuiz();
  }
}

function renderSheets() {
  const container = document.getElementById('semiology-sheets-list');
  if (!container || !window.ALL_SEMIOLOGY_SHEETS) return;

  const searchQuery = (document.getElementById('semiology-search-input').value || '').toLowerCase().trim();
  const sheets = window.ALL_SEMIOLOGY_SHEETS.filter(s => {
    const matchCat = currentApparatusFilter === 'all' || s.cat === currentApparatusFilter;
    const matchSearch = !searchQuery || s.name.toLowerCase().includes(searchQuery) || s.definition.toLowerCase().includes(searchQuery);
    return matchCat && matchSearch;
  });

  if (sheets.length === 0) {
    container.innerHTML = '<div class="section-callout">Aucune fiche sémiologique trouvée pour ce filtre.</div>';
    return;
  }

  container.innerHTML = sheets.map(s => `
    <div class="card mb-3" style="cursor: pointer; transition: transform 0.15s ease;" onclick="openSheetModal('${s.id}')">
      <div style="display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 6px;">
        <span class="student-badge" style="background: rgba(25, 118, 210, 0.15); color: var(--primary);">${s.categoryName} • ${s.subCategory}</span>
        <span style="font-size: 18px; color: var(--primary);">›</span>
      </div>
      <h3 style="font-size: 16px; font-weight: 700; margin-bottom: 6px;">${s.name}</h3>
      <p style="font-size: 13px; color: var(--text-secondary); line-height: 1.5;">${s.definition}</p>
    </div>
  `).join('');
}

function openSheetModal(sheetId) {
  const s = (window.ALL_SEMIOLOGY_SHEETS || []).find(item => item.id === sheetId);
  if (!s) return;

  const modalBody = document.getElementById('modal-sheet-content-body');
  modalBody.innerHTML = `
    <div class="mb-3">
      <span class="student-badge" style="background: rgba(25, 118, 210, 0.15); color: var(--primary);">${s.categoryName}</span>
      <h2 style="font-size: 19px; font-weight: 800; margin-top: 6px; color: var(--text-primary);">${s.name}</h2>
    </div>

    <div class="section-callout mb-3">
      <strong>1. Définition Sémiologique :</strong>
      <p style="margin-top: 4px; font-size: 13.5px; line-height: 1.6;">${s.definition}</p>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 6px;">2. Physiopathologie :</h4>
      <p style="font-size: 13px; color: var(--text-secondary); line-height: 1.6;">${s.physiopathology}</p>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 6px;">3. Étiologies Principales :</h4>
      <ul style="padding-left: 18px; font-size: 13px; line-height: 1.6;">
        ${s.etiologies.map(e => `<li>${e}</li>`).join('')}
      </ul>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 6px;">4. Anamnèse & Examen Physique :</h4>
      <p class="mb-2"><strong>Anamnèse :</strong> ${s.anamnesis}</p>
      <p><strong>Examen clinique :</strong> ${s.physicalExam}</p>
    </div>

    <div class="section-callout danger mb-3">
      <strong>5. Signes de Gravité / Alerte :</strong>
      <ul style="padding-left: 18px; font-size: 13px; margin-top: 4px;">
        ${s.gravitySigns.map(g => `<li>${g}</li>`).join('')}
      </ul>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 6px;">6. Diagnostics Différentiels :</h4>
      <ul style="padding-left: 18px; font-size: 13px; line-height: 1.6;">
        ${s.differentialDiagnoses.map(d => `<li>${d}</li>`).join('')}
      </ul>
    </div>

    <div class="section-callout mb-3" style="border-left-color: #10B981; background: rgba(16, 185, 129, 0.08);">
      <strong style="color: #065F46;">7. Points Clés à Retenir :</strong>
      <ul style="padding-left: 18px; font-size: 13px; margin-top: 4px; color: var(--text-primary);">
        ${s.keyPoints.map(k => `<li>${k}</li>`).join('')}
      </ul>
    </div>
  `;

  openModal('modal-sheet-detail');
}

// 12. SÉMIOLOGIE : Entraînement QUIZ (90 Questions Complètes)
let quizScore = { answered: 0, correct: 0 };

function renderQuiz() {
  const container = document.getElementById('semiology-quiz-list');
  if (!container || !window.ALL_QUIZ_QUESTIONS) return;

  const questions = window.ALL_QUIZ_QUESTIONS.filter(q => {
    return currentApparatusFilter === 'all' || q.cat === currentApparatusFilter;
  });

  document.getElementById('quiz-counter-badge').textContent = `${questions.length} Questions Disponibles`;

  container.innerHTML = questions.map((q, qIndex) => `
    <div class="quiz-question-card" id="quiz-card-${q.id}">
      <div style="display: flex; justify-content: space-between; margin-bottom: 8px;">
        <span class="student-badge">${q.categoryName} • Question ${qIndex + 1} / ${questions.length}</span>
      </div>
      <h3 style="font-size: 15px; font-weight: 700; margin-bottom: 14px; line-height: 1.5;">${q.q}</h3>
      <div class="options-group">
        ${q.opts.map((opt, oIndex) => `
          <div class="quiz-option" onclick="handleQuizAnswer('${q.id}', ${oIndex}, ${q.correct}, '${escapeHtml(q.exp)}')">
            <span style="font-weight: 700; min-width: 20px;">${['A', 'B', 'C', 'D'][oIndex]}.</span>
            <span>${opt}</span>
          </div>
        `).join('')}
      </div>
      <div id="quiz-feedback-${q.id}" style="display: none;" class="quiz-explanation"></div>
    </div>
  `).join('');
}

function handleQuizAnswer(qId, selectedIndex, correctIndex, explanation) {
  const card = document.getElementById(`quiz-card-${qId}`);
  if (!card) return;

  const options = card.querySelectorAll('.quiz-option');
  options.forEach((opt, idx) => {
    opt.onclick = null;
    if (idx === correctIndex) {
      opt.classList.add('selected-correct');
    } else if (idx === selectedIndex) {
      opt.classList.add('selected-wrong');
    }
  });

  const feedback = document.getElementById(`quiz-feedback-${qId}`);
  if (feedback) {
    const isCorrect = selectedIndex === correctIndex;
    feedback.innerHTML = `
      <strong style="color: ${isCorrect ? 'var(--success)' : 'var(--danger)'};">
        ${isCorrect ? '✓ Excellente réponse !' : '✗ Réponse incorrecte.'}
      </strong>
      <p style="margin-top: 6px; font-size: 13.5px; line-height: 1.6;">${explanation}</p>
    `;
    feedback.style.display = 'block';
  }
}

// 13. FORMATION CLINIQUE (14 Compétences d'anamnèse & 4 exercices)
function renderClinicalTraining() {
  const container = document.getElementById('clinical-training-content');
  if (!container || !window.ALL_ANAMNESIS_SKILLS) return;

  container.innerHTML = `
    <h3 style="font-size: 16px; font-weight: 800; margin-bottom: 12px;">📚 14 Compétences Pratiques d'Anamnèse Médicale</h3>
    ${window.ALL_ANAMNESIS_SKILLS.map(skill => `
      <div class="card mb-3">
        <span class="student-badge mb-2">Module ${skill.id}</span>
        <h4 style="font-size: 15px; font-weight: 700; margin-bottom: 4px;">${skill.title}</h4>
        <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 10px;">${skill.subtitle}</p>
        <p style="font-size: 13.5px; line-height: 1.6; margin-bottom: 12px;">${skill.explanation}</p>
        
        <div class="section-callout mb-2" style="border-left-color: #10B981; background: rgba(16, 185, 129, 0.08);">
          <strong style="color: #065F46;">Formulations Professionnelles Recommandées :</strong>
          <ul style="padding-left: 18px; font-size: 13px; margin-top: 4px;">
            ${skill.phrasings.map(p => `<li>« ${p} »</li>`).join('')}
          </ul>
        </div>

        <div class="section-callout danger mb-2">
          <strong>Erreurs Fréquentes à Éviter :</strong>
          <ul style="padding-left: 18px; font-size: 13px; margin-top: 4px;">
            ${skill.errors.map(e => `<li>${e}</li>`).join('')}
          </ul>
        </div>
      </div>
    `).join('')}

    <h3 style="font-size: 16px; font-weight: 800; margin-top: 20px; margin-bottom: 12px;">🎯 Exercices Pratiques de Classification de Paroles Patients</h3>
    ${(window.ALL_CLASSIFICATION_EXERCISES || []).map((ex, exIdx) => `
      <div class="card mb-3">
        <span class="student-badge mb-2">Exercice ${exIdx + 1}</span>
        <div class="section-callout mb-3" style="font-style: italic; font-size: 14px;">
          « ${ex.quote} »
        </div>
        <p style="font-size: 13px; font-weight: 600; margin-bottom: 8px;">Dans quelle catégorie de l'observation classez-vous cette information ?</p>
        <div class="options-group">
          ${ex.options.map(opt => `
            <button class="pill-btn mb-2" style="width: 100%; text-align: left; display: block;" onclick="alert('Catégorie correcte : ${ex.correct}\\n\\nExplication : ${escapeHtml(ex.explanation)}')">
              ${opt}
            </button>
          `).join('')}
        </div>
      </div>
    `).join('')}
  `;
}

function escapeHtml(str) {
  return (str || '').replace(/'/g, "\\'").replace(/"/g, '&quot;');
}

// 14. Initialisation au chargement de la page
document.addEventListener('DOMContentLoaded', () => {
  initTheme();
  renderSemiologyView();
  renderClinicalTraining();

  const studentNameEl = document.getElementById('hero-student-name');
  if (studentNameEl) studentNameEl.textContent = currentProfile.name;

  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js')
      .then(() => console.log('EL VITA PWA: Service Worker actif (Mode Hors Connexion prêt)'))
      .catch((err) => console.log('Service Worker notice:', err));
  }
});
