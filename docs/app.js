/**
 * EL VITA — Compagnon Clinique & Pédagogique
 * Logique Complète 100% Fidèle aux Maquettes Android
 */

// 1. Initialisation & Persistance
const STORAGE_KEY_PROFILE = 'elvita_web_student_profile';
const STORAGE_KEY_THEME = 'elvita_web_theme';
const STORAGE_KEY_PATIENTS = 'elvita_web_patients';

const defaultProfile = {
  name: "Étudiant en médecine",
  studyYear: "DFASM 2 (5e Année)",
  university: "Faculté des Sciences de la Santé",
  hospital: "CHU - Pôle Médecine Interne"
};

let currentProfile = JSON.parse(localStorage.getItem(STORAGE_KEY_PROFILE)) || defaultProfile;

const defaultPatients = [
  {
    id: 1,
    name: "Mme Claire B. (Cas Pédagogique)",
    age: 68,
    gender: "Femme",
    department: "Pneumologie",
    room: "Chambre 214",
    condition: "Pneumopathie franche lobaire aiguë (PFLA)"
  },
  {
    id: 2,
    name: "M. Marc T. (Cas Pédagogique)",
    age: 54,
    gender: "Homme",
    department: "Cardiologie",
    room: "Chambre 105",
    condition: "Syndrome Coronarien Aigu (SCA ST-)"
  }
];

let patientsList = JSON.parse(localStorage.getItem(STORAGE_KEY_PATIENTS)) || defaultPatients;

// 2. Gestion du Thème
function initTheme() {
  const savedTheme = localStorage.getItem(STORAGE_KEY_THEME) || 'dark';
  document.documentElement.setAttribute('data-theme', savedTheme);
}

function toggleTheme() {
  const current = document.documentElement.getAttribute('data-theme') || 'dark';
  const next = current === 'dark' ? 'light' : 'dark';
  document.documentElement.setAttribute('data-theme', next);
  localStorage.setItem(STORAGE_KEY_THEME, next);
}

// 3. Navigation Principale (5 Onglets)
function switchTab(tabId) {
  document.querySelectorAll('.tab-content').forEach(el => el.classList.remove('active'));
  document.querySelectorAll('.nav-item').forEach(el => el.classList.remove('active'));

  const targetContent = document.getElementById(`tab-${tabId}`);
  const targetNav = document.getElementById(`nav-${tabId}`);
  if (targetContent) targetContent.classList.add('active');
  if (targetNav) targetNav.classList.add('active');

  window.scrollTo({ top: 0, behavior: 'smooth' });
}

// Sous-onglets (Underlined Tabs)
function switchSubTab(parentTab, subId) {
  const container = document.getElementById(`tab-${parentTab}`);
  if (!container) return;

  container.querySelectorAll('.subtab-panel').forEach(el => el.style.display = 'none');
  container.querySelectorAll('.module-tab-btn').forEach(el => el.classList.remove('active'));

  const targetPanel = document.getElementById(`${parentTab}-sub-${subId}`);
  const targetBtn = document.getElementById(`tab-btn-${parentTab}-${subId}`);
  if (targetPanel) targetPanel.style.display = 'block';
  if (targetBtn) targetBtn.classList.add('active');

  if (parentTab === 'vitals' && subId === 'curves') {
    renderVitalsChart();
  }
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

// 5. Section A : Prévention Médicale & Vigilance (6 Fiches exactes Image 1)
const preventionTopics = [
  {
    id: "malaria",
    tag: "Infectiologie",
    title: "Paludisme : Urgence & Diagnostic",
    subtitle: "Goutte épaisse, TDR et critères de gravité OMS",
    desc: "Toute fièvre au retour de zone d'endémie est un paludisme jusqu'à preuve du contraire.",
    action: "Traitement du neuropaludisme / accès grave : Artésunate intraveineux en extrême urgence.",
    points: [
      "Toute fièvre au retour de zone d'endémie est un paludisme jusqu'à preuve du contraire.",
      "Rechercher immédiatement les signes de gravité : coma (Glasgow <= 8), détresse respiratoire (acidose métabolique), convulsions répétées, hypoglycémie, ictère clinique, collapsus.",
      "Diagnostic parasitologique en urgence absolue : Frottis sanguin et Goutte Épaisse (GE) couplés au Test de Diagnostic Rapide (TDR)."
    ]
  },
  {
    id: "polio",
    tag: "Santé Publique",
    title: "Poliomyélite & Paralysies Flasques",
    subtitle: "Surveillance mondiale et couverture vaccinale",
    desc: "Transmission féco-orale interhumaine par les poliovirus sauvages ou dérivés de souches.",
    action: "Toute paralysie flasque aiguë (PFA) chez l'enfant impose déclaration et prélèvement de selles.",
    points: [
      "Transmission féco-orale interhumaine prédominant en zone d'assainissement précaire.",
      "Tableau clinique classique : paralysie flasque aiguë asymétrique avec abolition des réflexes ostéotendineux (ROT) sans trouble sensitif associé.",
      "Prévention universelle par la primovaccination complète (VPI injectable et VPO)."
    ]
  },
  {
    id: "hygiene",
    tag: "Sécurité du soin",
    title: "Hygiène des Mains & Précautions Standard",
    subtitle: "Les 5 indications OMS au lit du patient",
    desc: "La friction hydro-alcoolique (FHA) sur mains sèches et non souillées est la référence absolue.",
    action: "FHA systématique avant et après chaque contact avec le malade.",
    points: [
      "1. Avant de toucher un patient.",
      "2. Avant un geste aseptique (pose de cathéter, pansement, injection).",
      "3. Après un risque d'exposition à un liquide biologique.",
      "4. Après avoir touché un patient.",
      "5. Après avoir touché l'environnement immédiat du patient."
    ]
  },
  {
    id: "vaccines",
    tag: "Immunologie",
    title: "Vaccination des Étudiants et Soignants",
    subtitle: "Protection individuelle et des patients vulnérables",
    desc: "Hépatite B obligatoire avec contrôle du titre des anticorps anti-HBs (> 100 UI/L = immunité).",
    action: "Protéger le soignant, c'est avant tout protéger le patient immunodéprimé hospitalisé.",
    points: [
      "Vaccination Hépatite B obligatoire avec sérologie de contrôle anti-HBs.",
      "DTP (Diphtérie, Tétanos, Poliomyélite) à jour selon le calendrier en vigueur.",
      "Vaccinations recommandées : Coqueluche, ROR, Grippe saisonnière annuelle."
    ]
  },
  {
    id: "cardio_prev",
    tag: "Cardiologie",
    title: "Prévention Cardiovasculaire Primaire",
    subtitle: "Contrôle des facteurs de risque modifiables",
    desc: "Objectif tensionnel : PA < 140/90 mmHg (voire < 130/80 mmHg si bien toléré chez le coronarien).",
    action: "Calculer systématiquement le risque cardiovasculaire global (SCORE2) chez l'adulte.",
    points: [
      "Sevrage tabagique complet : bénéfice cardiovasculaire net dès le premier mois.",
      "Activité physique aérobie régulière : au moins 150 minutes par semaine.",
      "Alimentation type méditerranéenne et réduction des graisses saturées."
    ]
  },
  {
    id: "nosocomial",
    tag: "Hygiène",
    title: "Infections Associées aux Soins (IAS)",
    subtitle: "Bon usage des antibiotiques et asepsie",
    desc: "La sonde vésicale est la première cause d'infection nosocomiale : pas de sonde de confort !",
    action: "Réévaluer toute antibiothérapie probabiliste à 48-72h avec les résultats bactériologiques.",
    points: [
      "Retirer tout dispositif invasif (cathéter veineux périphérique, sonde urinaire) dès qu'il n'est plus strictement indispensable.",
      "Respect rigoureux des précautions d'isolement : Contact (BMR), Gouttelettes, Air (Tuberculose)."
    ]
  }
];

function renderPreventions() {
  const container = document.getElementById('prevention-carousel-container');
  if (!container) return;

  container.innerHTML = preventionTopics.map(p => `
    <div class="prevention-card" onclick="openPreventionDetail('${p.id}')">
      <div>
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <span class="prev-tag">${p.tag}</span>
          <span style="color: var(--medical-blue); font-size: 14px;">🏥</span>
        </div>
        <div class="prev-title">${p.title}</div>
        <div class="prev-sub">${p.subtitle}</div>
        <div class="prev-desc">${p.desc}</div>
      </div>
      <div class="prev-action-row">
        <span style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; max-width: 220px;">${p.action}</span>
        <span>→</span>
      </div>
    </div>
  `).join('');
}

function openPreventionDetail(topicId) {
  const topic = preventionTopics.find(t => t.id === topicId);
  if (!topic) return;

  const body = document.getElementById('prevention-detail-body');
  body.innerHTML = `
    <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 12px;">
      <span class="prev-tag">${topic.tag}</span>
      <span style="font-size: 13px; font-weight: 700; color: #38BDF8;">Fiche Médicale de Prévention</span>
    </div>

    <h2 style="font-size: 18px; font-weight: 900; margin-bottom: 4px;">${topic.title}</h2>
    <div style="font-size: 13px; color: var(--medical-blue-light); font-weight: 600; margin-bottom: 14px;">${topic.subtitle}</div>

    <div class="card mb-3" style="background: rgba(2, 132, 199, 0.1); border-color: rgba(2, 132, 199, 0.35);">
      <div style="font-size: 12px; font-weight: 700; color: #38BDF8; margin-bottom: 4px;">Message Clinique Réflexe</div>
      <div style="font-size: 13.5px; font-weight: 800; color: #F8FAFC;">${topic.action}</div>
    </div>

    <div style="font-size: 14px; font-weight: 800; margin-bottom: 8px;">Points Clés de Prévention & Recommandations :</div>
    ${topic.points.map(pt => `
      <div class="card mb-2" style="padding: 10px 14px; display: flex; align-items: flex-start; gap: 10px;">
        <span style="color: var(--alert-green); font-size: 16px;">✓</span>
        <span style="font-size: 13px; line-height: 1.5;">${pt}</span>
      </div>
    `).join('')}

    <div class="card mt-3" style="background: rgba(245, 158, 11, 0.12); border-color: #F59E0B;">
      <div style="font-size: 12px; font-weight: 700; color: #F59E0B;">Vigilance en Stage Hospitalier :</div>
      <div style="font-size: 12.5px; color: #FDE68A; margin-top: 4px;">
        Toute situation clinique inhabituelle ou tout signe de gravité doit être immédiatement partagé avec l'interne ou le médecin senior.
      </div>
    </div>
  `;

  openModal('modal-prevention-detail');
}

function openAllPreventionsModal() {
  openPreventionDetail('malaria');
}

// 6. Gestion des Patients & Dossiers
function initPatientsSelect() {
  const vitalsSel = document.getElementById('vitals-patient-select');
  const obsSel = document.getElementById('obs-patient-select');

  if (vitalsSel) {
    vitalsSel.innerHTML = `
      <option value="0">Mesure ponctuelle (sans dossier associé)</option>
      ${patientsList.map(p => `<option value="${p.id}">${p.name} (${p.room})</option>`).join('')}
    `;
  }

  if (obsSel) {
    obsSel.innerHTML = `
      <option value="new">Saisir un nouveau dossier patient</option>
      ${patientsList.map(p => `<option value="${p.id}">${p.name} - ${p.department} (${p.room})</option>`).join('')}
    `;
  }

  renderPatientsList();
}

function renderPatientsList() {
  const container = document.getElementById('patients-list-container');
  if (!container) return;

  const query = (document.getElementById('patient-search-input')?.value || '').toLowerCase().trim();
  const filtered = patientsList.filter(p => !query || p.name.toLowerCase().includes(query) || p.department.toLowerCase().includes(query) || p.condition.toLowerCase().includes(query));

  container.innerHTML = filtered.map(p => `
    <div class="card mb-2" style="cursor: pointer; padding: 12px;" onclick="selectPatientForVisit(${p.id})">
      <div style="display: flex; justify-content: space-between; align-items: center;">
        <span style="font-size: 14px; font-weight: 800; color: #F8FAFC;">${p.name}</span>
        <span class="prev-tag">${p.gender}, ${p.age} ans</span>
      </div>
      <div style="font-size: 12px; color: #38BDF8; margin-top: 4px;">${p.department} • ${p.room}</div>
      <div style="font-size: 12px; color: var(--text-secondary); margin-top: 2px;">${p.condition}</div>
    </div>
  `).join('');
}

function selectPatientForVisit(patientId) {
  const p = patientsList.find(item => item.id === patientId);
  if (!p) return;

  closeModal('modal-patients');

  // Remplir observation
  document.getElementById('obs-patient-name').value = p.name;
  document.getElementById('obs-age').value = p.age;
  document.getElementById('obs-gender').value = p.gender;
  document.getElementById('obs-dept').value = p.department;
  document.getElementById('obs-room').value = p.room;

  // Remplir select
  const obsSel = document.getElementById('obs-patient-select');
  if (obsSel) obsSel.value = p.id;
  const vitalsSel = document.getElementById('vitals-patient-select');
  if (vitalsSel) vitalsSel.value = p.id;

  switchTab('clinical');
}

function loadSelectedPatientIntoObs(val) {
  if (val === 'new') {
    document.getElementById('obs-patient-name').value = "";
    document.getElementById('obs-age').value = "45";
    document.getElementById('obs-dept').value = "Médecine Interne";
    document.getElementById('obs-room').value = "Chambre 101";
  } else {
    selectPatientForVisit(parseInt(val));
  }
}

function saveNewPatient() {
  const name = document.getElementById('new-patient-name').value.trim();
  const age = parseInt(document.getElementById('new-patient-age').value) || 50;
  const gender = document.getElementById('new-patient-gender').value;
  const dept = document.getElementById('new-patient-dept').value.trim() || "Médecine Interne";
  const room = document.getElementById('new-patient-room').value.trim() || "Chambre";
  const cond = document.getElementById('new-patient-cond').value.trim() || "Hospitalisation";

  if (!name) {
    alert("Veuillez saisir un nom ou un alias de patient.");
    return;
  }

  const newPatient = {
    id: Date.now(),
    name,
    age,
    gender,
    department: dept,
    room,
    condition: cond
  };

  patientsList.unshift(newPatient);
  localStorage.setItem(STORAGE_KEY_PATIENTS, JSON.stringify(patientsList));

  initPatientsSelect();
  closeModal('modal-add-patient');
  selectPatientForVisit(newPatient.id);
  alert(`Dossier patient créé avec succès pour ${name} !`);
}

// 7. MOTEUR CLINIQUE : Constantes Vitales
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
    <div class="card" style="border-left: 4px solid ${alertLevel === 'CRITICAL' ? 'var(--alert-red)' : alertLevel === 'MODERATE' ? 'var(--alert-orange)' : 'var(--alert-green)'};">
      <div style="font-size: 14.5px; font-weight: 800; color: ${alertLevel === 'CRITICAL' ? '#EF4444' : alertLevel === 'MODERATE' ? '#F59E0B' : '#10B981'}; margin-bottom: 8px;">
        ● ${badgeText}
      </div>
      <div class="form-row mb-2">
        <div><strong>PAM :</strong> ${pam.toFixed(1)} mmHg</div>
        <div><strong>IMC :</strong> ${imc.toFixed(1)} kg/m²</div>
      </div>
      <h4 style="font-size: 13px; font-weight: 800; margin-top: 8px; margin-bottom: 4px;">Points Systématiques d'Examen :</h4>
      ${points.length > 0 ? points.map(p => `
        <div style="font-size: 12.5px; margin-bottom: 4px; color: ${p.level === 'CRITICAL' ? '#FCA5A5' : '#FDE68A'};">
          • <strong>${p.name} :</strong> ${p.obs}
        </div>
      `).join('') : '<div style="font-size: 12.5px; color: #A7F3D0;">Tous les paramètres vitaux mesurés sont physiologiques.</div>'}
    </div>
  `;
  resultContainer.style.display = 'block';
}

function renderVitalsChart() {
  const container = document.getElementById('vitals-chart-container');
  if (!container) return;

  container.innerHTML = `
    <svg viewBox="0 0 400 180" style="width: 100%; height: auto; background: #0E1626; border-radius: 12px; padding: 10px;">
      <!-- Grille -->
      <line x1="40" y1="30" x2="380" y2="30" stroke="#23354D" stroke-dasharray="4"/>
      <line x1="40" y1="70" x2="380" y2="70" stroke="#23354D" stroke-dasharray="4"/>
      <line x1="40" y1="110" x2="380" y2="110" stroke="#23354D" stroke-dasharray="4"/>
      <line x1="40" y1="150" x2="380" y2="150" stroke="#23354D"/>

      <!-- Axes Labels -->
      <text x="5" y="35" fill="#64748B" font-size="10">39.5°</text>
      <text x="5" y="75" fill="#64748B" font-size="10">38.5°</text>
      <text x="5" y="115" fill="#64748B" font-size="10">37.5°</text>
      <text x="5" y="155" fill="#64748B" font-size="10">36.5°</text>

      <!-- Courbe Température (Mme Claire B.) -->
      <polyline fill="none" stroke="#EF4444" stroke-width="3" points="50,38 120,55 190,82 260,105 330,118"/>
      <circle cx="50" cy="38" r="4" fill="#EF4444"/>
      <circle cx="120" cy="55" r="4" fill="#EF4444"/>
      <circle cx="190" cy="82" r="4" fill="#EF4444"/>
      <circle cx="260" cy="105" r="4" fill="#EF4444"/>
      <circle cx="330" cy="118" r="4" fill="#EF4444"/>

      <!-- Courbe SpO2 (Teal) -->
      <polyline fill="none" stroke="#2DD4BF" stroke-width="2.5" stroke-dasharray="2" points="50,140 120,125 190,95 260,70 330,60"/>
      <circle cx="50" cy="140" r="3.5" fill="#2DD4BF"/>
      <circle cx="330" cy="60" r="3.5" fill="#2DD4BF"/>

      <!-- Légende -->
      <text x="60" y="172" fill="#EF4444" font-size="10" font-weight="bold">━ Température (°C)</text>
      <text x="210" y="172" fill="#2DD4BF" font-size="10" font-weight="bold">┅ SpO2 (%)</text>
    </svg>
    <div style="font-size: 11.5px; color: var(--text-secondary); text-align: center; margin-top: 6px;">
      Évolution thermique et respiratoire sous Amoxicilline (J0 à J2)
    </div>
  `;
}

// 8. MOTEUR CLINIQUE : NFS en 6 Volets Complète
function calculateNFS() {
  const hb = parseFloat(document.getElementById('nfs-hb').value) || 13.5;
  const ht = parseFloat(document.getElementById('nfs-ht').value) || 41.0;
  const gr = parseFloat(document.getElementById('nfs-gr').value) || 4.7;
  const gb = parseFloat(document.getElementById('nfs-gb').value) || 7.2;
  const pnn = parseFloat(document.getElementById('nfs-pnn').value) || 4.5;
  const lympho = parseFloat(document.getElementById('nfs-lympho').value) || 2.1;
  const mono = parseFloat(document.getElementById('nfs-mono').value) || 0.5;
  const eosino = parseFloat(document.getElementById('nfs-eosino').value) || 0.15;
  const baso = parseFloat(document.getElementById('nfs-baso').value) || 0.05;
  const plt = parseFloat(document.getElementById('nfs-plt').value) || 240;
  const retic = parseFloat(document.getElementById('nfs-retic').value) || 65;

  const vgm = (ht * 10) / gr;
  const ccmh = (hb / ht) * 100;

  let isAnemia = hb < 12.0;
  let isMicrocytic = vgm < 80.0;
  let isMacrocytic = vgm > 100.0;
  let isHypochromic = ccmh < 32.0;
  let isThrombopenia = plt < 150.0;
  let isThrombocytosis = plt > 400.0;
  let isHyperleukocytosis = gb > 10.0;
  let isLeukopenia = gb < 4.0;
  let isNeutropenia = pnn < 1.5;
  let isAgranulocytosis = pnn < 0.5;

  let alertLevel = 'NORMAL';
  if (hb < 8.0 || isAgranulocytosis || plt < 20.0) {
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
    conclusionText = `La NFS a objectivé une thrombopénie isolée (${plt} G/L) sans anomalie significative des autres lignées sanguines.`;
  } else {
    conclusionText = "La NFS présente des variations hématologiques modérées à corréler au contexte clinique.";
  }

  const redSummary = isAnemia 
    ? `Anémie ${isMicrocytic ? 'microcytaire (VGM ' + vgm.toFixed(1) + ' fL)' : isMacrocytic ? 'macrocytaire' : 'normocytaire'}, ${isHypochromic ? 'hypochrome (CCMH ' + ccmh.toFixed(1) + '%)' : 'normochrome'}${retic ? (retic < 120 ? ' arégénérative' : ' régénérative') : ''}.`
    : `Lignée rouge normale (Hb ${hb} g/dL, VGM ${vgm.toFixed(1)} fL, Réticulocytes ${retic} G/L).`;

  const whiteSummary = isAgranulocytosis
    ? `AGRANULOCYTOSE MAJEURE (PNN ${pnn} G/L < 0.5 G/L) — Risque vital septique immédiat !`
    : isHyperleukocytosis
      ? `Hyperleucocytose franche (${gb} G/L) à prédominance neutrophile (${pnn} G/L).`
      : `Lignée leucocytaire physiologique (GB ${gb} G/L, PNN ${pnn} G/L).`;

  const pltSummary = isThrombopenia
    ? `Thrombopénie à ${plt} G/L.`
    : isThrombocytosis
      ? `Thrombocytose à ${plt} G/L.`
      : `Plaquettes dans les normes (${plt} G/L, VPM ${document.getElementById('nfs-vpm').value} fL).`;

  const res = document.getElementById('nfs-result');
  res.innerHTML = `
    <div class="card" style="border-left: 4px solid #0284C7; margin-top: 14px;">
      <h3 style="font-size: 15px; font-weight: 800; color: #38BDF8; margin-bottom: 6px;">Conclusion & Synthèse Hématologique Globale :</h3>
      <p style="font-size: 13.5px; font-weight: 700; line-height: 1.6; color: #F8FAFC;">${conclusionText}</p>
    </div>

    <div class="card mb-2" style="padding: 12px;">
      <div style="font-size: 13px; font-weight: 700; margin-bottom: 6px;">Analyse Détaillée des Lignées :</div>
      <div style="font-size: 12.5px; margin-bottom: 4px;"><strong>🔴 Lignée Rouge :</strong> ${redSummary}</div>
      <div style="font-size: 12.5px; margin-bottom: 4px;"><strong>⚪ Lignée Blanche :</strong> ${whiteSummary}</div>
      <div style="font-size: 12.5px;"><strong>🟡 Plaquettes :</strong> ${pltSummary}</div>
    </div>
  `;
  res.style.display = 'block';
}

// 9. SÉMIOLOGIE : FICHES ET QUIZ
let currentApparatusFilter = 'all';

function setApparatusFilter(cat) {
  currentApparatusFilter = cat;
  document.querySelectorAll('.pills-scroll .pill-item').forEach(b => b.classList.remove('active'));
  const btn = document.getElementById(`pill-app-${cat}`);
  if (btn) btn.classList.add('active');

  renderSheets();
  renderQuiz();
}

function renderSheets() {
  const container = document.getElementById('semiology-sheets-list');
  if (!container || !window.ALL_SEMIOLOGY_SHEETS) return;

  const query = (document.getElementById('semiology-search-input')?.value || '').toLowerCase().trim();
  const sheets = window.ALL_SEMIOLOGY_SHEETS.filter(s => {
    const matchCat = currentApparatusFilter === 'all' || s.cat === currentApparatusFilter;
    const matchQuery = !query || s.name.toLowerCase().includes(query) || s.definition.toLowerCase().includes(query);
    return matchCat && matchQuery;
  });

  container.innerHTML = sheets.map(s => `
    <div class="card mb-3" style="cursor: pointer;" onclick="openSheetModal('${s.id}')">
      <div style="display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 4px;">
        <span class="prev-tag">${s.categoryName} • ${s.subCategory}</span>
        <span style="color: #38BDF8; font-size: 16px;">›</span>
      </div>
      <h3 style="font-size: 15.5px; font-weight: 800; color: #F8FAFC; margin-bottom: 4px;">${s.name}</h3>
      <p style="font-size: 12.5px; color: var(--text-secondary); line-height: 1.5;">${s.definition}</p>
    </div>
  `).join('');
}

function openSheetModal(sheetId) {
  const s = (window.ALL_SEMIOLOGY_SHEETS || []).find(item => item.id === sheetId);
  if (!s) return;

  const modalBody = document.getElementById('modal-sheet-content-body');
  modalBody.innerHTML = `
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
      <span class="prev-tag">${s.categoryName} • ${s.subCategory}</span>
    </div>
    <h2 style="font-size: 18px; font-weight: 900; color: #38BDF8; margin-bottom: 12px;">${s.name}</h2>

    <div class="card mb-2" style="background: rgba(2, 132, 199, 0.1);">
      <strong>1. Définition Sémiologique :</strong>
      <p style="font-size: 13px; margin-top: 4px; line-height: 1.6;">${s.definition}</p>
    </div>

    <div class="card mb-2">
      <strong>2. Physiopathologie :</strong>
      <p style="font-size: 12.5px; margin-top: 4px; color: var(--text-secondary); line-height: 1.5;">${s.physiopathology}</p>
    </div>

    <div class="card mb-2">
      <strong>3. Étiologies Principales :</strong>
      <ul style="padding-left: 18px; font-size: 12.5px; margin-top: 4px; line-height: 1.5;">
        ${s.etiologies.map(e => `<li>${e}</li>`).join('')}
      </ul>
    </div>

    <div class="card mb-2">
      <strong>4. Examen Physique & Anamnèse :</strong>
      <p style="font-size: 12.5px; margin-top: 4px;"><strong>Anamnèse :</strong> ${s.anamnesis}</p>
      <p style="font-size: 12.5px; margin-top: 4px;"><strong>Examen :</strong> ${s.physicalExam}</p>
    </div>

    <div class="card mb-2" style="background: rgba(220, 38, 38, 0.1); border-color: var(--alert-red);">
      <strong style="color: #F87171;">5. Signes de Gravité / Alerte :</strong>
      <ul style="padding-left: 18px; font-size: 12.5px; margin-top: 4px;">
        ${s.gravitySigns.map(g => `<li>${g}</li>`).join('')}
      </ul>
    </div>

    <div class="card mb-2" style="background: rgba(16, 185, 129, 0.1); border-color: var(--alert-green);">
      <strong style="color: #34D399;">6. Points Clés à Retenir :</strong>
      <ul style="padding-left: 18px; font-size: 12.5px; margin-top: 4px;">
        ${s.keyPoints.map(k => `<li>${k}</li>`).join('')}
      </ul>
    </div>
  `;

  openModal('modal-sheet-detail');
}

function renderQuiz() {
  const container = document.getElementById('semiology-quiz-list');
  if (!container || !window.ALL_QUIZ_QUESTIONS) return;

  const questions = window.ALL_QUIZ_QUESTIONS.filter(q => {
    return currentApparatusFilter === 'all' || q.cat === currentApparatusFilter;
  });

  const badge = document.getElementById('quiz-counter-badge');
  if (badge) badge.textContent = `${questions.length} Questions Disponibles`;

  container.innerHTML = questions.map((q, idx) => `
    <div class="card mb-3" id="quiz-box-${q.id}">
      <span class="prev-tag mb-2">${q.categoryName} • Question ${idx + 1} / ${questions.length}</span>
      <div style="font-size: 14.5px; font-weight: 800; margin-top: 8px; margin-bottom: 12px; line-height: 1.5;">${q.q}</div>
      ${q.opts.map((opt, oIdx) => `
        <div class="card mb-2" style="cursor: pointer; padding: 10px 14px; background: #0E1626;" onclick="handleQuizAnswer('${q.id}', ${oIdx}, ${q.correct}, '${escapeHtml(q.exp)}')">
          <strong style="color: #38BDF8;">${['A', 'B', 'C', 'D'][oIdx]}.</strong> ${opt}
        </div>
      `).join('')}
      <div id="quiz-feedback-${q.id}" style="display: none; margin-top: 10px; padding: 12px; border-radius: 8px; background: rgba(255,255,255,0.06);"></div>
    </div>
  `).join('');
}

function handleQuizAnswer(qId, selected, correct, exp) {
  const box = document.getElementById(`quiz-box-${qId}`);
  if (!box) return;

  const cards = box.querySelectorAll('.card');
  cards.forEach((c, idx) => {
    c.onclick = null;
    if (idx === correct) {
      c.style.background = "#064E3B";
      c.style.borderColor = "#10B981";
    } else if (idx === selected) {
      c.style.background = "#7F1D1D";
      c.style.borderColor = "#DC2626";
    }
  });

  const fb = document.getElementById(`quiz-feedback-${qId}`);
  if (fb) {
    const ok = selected === correct;
    fb.innerHTML = `
      <strong style="color: ${ok ? '#34D399' : '#F87171'};">${ok ? '✓ Bonne réponse !' : '✗ Réponse incorrecte.'}</strong>
      <p style="font-size: 12.5px; margin-top: 4px; line-height: 1.5;">${exp}</p>
    `;
    fb.style.display = 'block';
  }
}

// 10. Profil Étudiant
function saveProfile() {
  const name = document.getElementById('prof-name').value.trim() || "Étudiant en médecine";
  const year = document.getElementById('prof-year').value.trim();
  const uni = document.getElementById('prof-uni').value.trim();
  const hosp = document.getElementById('prof-hosp').value.trim();

  currentProfile = { name, studyYear: year, university: uni, hospital: hosp };
  localStorage.setItem(STORAGE_KEY_PROFILE, JSON.stringify(currentProfile));

  const heroName = document.getElementById('hero-student-name');
  if (heroName) heroName.textContent = name;

  closeModal('modal-settings');
  alert("Profil étudiant enregistré avec succès !");
}

function generateClinicalDraft() {
  const patient = document.getElementById('obs-patient-name').value;
  const motif = document.getElementById('obs-motif').value;
  const hdm = document.getElementById('obs-hdm').value;
  const resp = document.getElementById('obs-resp').value;
  const synthese = document.getElementById('obs-synthese').value;

  const res = document.getElementById('obs-result');
  res.innerHTML = `
    <div class="card" style="border-left: 4px solid var(--alert-green); margin-top: 14px;">
      <div style="font-weight: 800; color: #34D399; margin-bottom: 6px;">✓ Observation Clinique Structurée Générée</div>
      <pre style="white-space: pre-wrap; font-family: inherit; font-size: 12.5px; line-height: 1.6; color: #CBD5E1;">
OBSERVATION MÉDICALE INITIALE
PATIENT : ${patient}

MOTIF D'ADMISSION :
${motif}

HISTOIRE DE LA MALADIE :
${hdm}

EXAMEN RESPIRATOIRE :
${resp}

SYNTHÈSE CLINIQUE & CONDUITE À TENIR :
${synthese}

El Vita est un outil d'aide à l'observation clinique et ne se substitue pas au jugement médical.</pre>
    </div>
  `;
  res.style.display = 'block';
}

function escapeHtml(str) {
  return (str || '').replace(/'/g, "\\'").replace(/"/g, '&quot;');
}

// Initialisation globale
document.addEventListener('DOMContentLoaded', () => {
  initTheme();
  renderPreventions();
  initPatientsSelect();
  renderSheets();
  renderQuiz();

  const heroName = document.getElementById('hero-student-name');
  if (heroName) heroName.textContent = currentProfile.name;

  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js').then((reg) => {
      reg.update();
    }).catch(() => {});

    navigator.serviceWorker.addEventListener('controllerchange', () => {
      console.log('Nouveau Service Worker activé, rafraîchissement...');
      window.location.reload();
    });
  }
});
