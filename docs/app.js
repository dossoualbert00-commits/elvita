/**
 * EL VITA — Compagnon Clinique & Pédagogique
 * Logique Complète 100% Fidèle aux Maquettes Android
 * Version 4.0 (Toutes fonctions de calcul, formation pédagogique 14 points & 90 QCM interactifs)
 */

// 1. Initialisation & Persistance Locale
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

// Score Quiz
let quizScore = { correct: 0, totalAnswered: 0 };

// Utilitaires de parsing
function parseNum(id, defaultVal = 0) {
  const el = document.getElementById(id);
  if (!el) return defaultVal;
  const v = (el.value || '').toString().trim().replace(',', '.');
  const n = parseFloat(v);
  return isNaN(n) ? defaultVal : n;
}

function escapeHtml(str) {
  return (str || '')
    .replace(/\\/g, '\\\\')
    .replace(/'/g, "\\'")
    .replace(/"/g, '&quot;')
    .replace(/\n/g, ' ');
}

// 2. Gestion des Onglets Principaux (5 Onglets M3)
function switchTab(tabId) {
  document.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
  document.querySelectorAll('.bottom-nav .nav-item').forEach(item => item.classList.remove('active'));

  const targetTab = document.getElementById(`tab-${tabId}`);
  const targetNav = document.getElementById(`nav-${tabId}`);

  if (targetTab) targetTab.classList.add('active');
  if (targetNav) targetNav.classList.add('active');

  window.scrollTo({ top: 0, behavior: 'smooth' });

  if (tabId === 'vitals') {
    renderVitalsChart();
  } else if (tabId === 'semiology') {
    renderSheets();
    renderQuiz();
  }
}

// 3. Gestion des Sous-Onglets (Clinique, Mesures, Analyses, Sémiologie)
function switchSubTab(module, subTabId) {
  if (module === 'vitals') {
    document.querySelectorAll('#tab-vitals .module-tab-btn').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('#tab-vitals .subtab-panel').forEach(p => p.style.display = 'none');
    
    const btn = document.getElementById(`tab-btn-vitals-${subTabId}`);
    const panel = document.getElementById(`vitals-sub-${subTabId}`);
    if (btn) btn.classList.add('active');
    if (panel) panel.style.display = 'block';

    if (subTabId === 'curves') renderVitalsChart();
  } else if (module === 'clinical') {
    document.querySelectorAll('#tab-clinical .module-tab-btn').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('#tab-clinical .subtab-panel').forEach(p => p.style.display = 'none');
    
    const btn = document.getElementById(`tab-btn-clinical-${subTabId}`);
    const panel = document.getElementById(`clinical-sub-${subTabId}`);
    if (btn) btn.classList.add('active');
    if (panel) panel.style.display = 'block';
  } else if (module === 'labs') {
    document.querySelectorAll('#tab-labs .subtab-pill').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('#tab-labs .subtab-panel').forEach(p => p.style.display = 'none');

    const btn = document.getElementById(`pill-labs-${subTabId}`);
    const panel = document.getElementById(`labs-sub-${subTabId}`);
    if (btn) btn.classList.add('active');
    if (panel) panel.style.display = 'block';
  } else if (module === 'semiology') {
    document.querySelectorAll('#tab-semiology .module-tab-btn').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('#tab-semiology .subtab-panel').forEach(p => p.style.display = 'none');

    const btn = document.getElementById(`tab-btn-semiology-${subTabId}`);
    const panel = document.getElementById(`semiology-sub-${subTabId}`);
    if (btn) btn.classList.add('active');
    if (panel) panel.style.display = 'block';
  }
}

// 4. Modales
function openModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) {
    modal.classList.add('active');
    if (modalId === 'modal-patients') renderPatientsList();
    if (modalId === 'modal-clinical-training') renderClinicalTraining('anamnesis');
  }
}

function closeModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.remove('active');
}

// 5. Section A : Prévention Médicale & Vigilance (6 Fiches Répertoire Android)
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
      "Goutte épaisse et frottis sanguin : examen de référence pour quantification de la parasitémie.",
      "Test de Diagnostic Rapide (TDR) antigène HRP2 : positif en quelques minutes.",
      "Critères de gravité OMS : coma, détresse respiratoire, acidose, ictère, oligo-anurie, anémie sévère (Hb < 7 g/dL), hémorragies, collapsus hémodynamique, hypoglycémie (< 2.2 mmol/L), hyperparasitémie (> 4%).",
      "Traitement d'accès simple : Dérivés de l'Artémisinine en combinaison orale (ACT : Arthéméter-Luméfantrine ou Artésunate-Amodiaquine).",
      "Traitement d'accès grave : Artésunate injectable IV (2.4 mg/kg à H0, H12, H24 puis quotidien) avec surveillance glycémique stricte."
    ]
  },
  {
    id: "polio",
    tag: "Santé Publique",
    title: "Poliomyélite & Paralysie Flasque Aiguë",
    subtitle: "Surveillance épidémiologique et éradication mondiale",
    desc: "Transmission féco-orale, risque de paralysie motrice flasque asymétrique sans atteinte sensitive.",
    action: "Toute paralysie flasque aiguë (PFA) chez l'enfant < 15 ans impose 2 prélèvements de selles à 24-48h d'intervalle.",
    points: [
      "Poliovirus à tropisme pour les motoneurones de la corne antérieure de la moelle épinière.",
      "Signes cardinaux : paralysie flasque aiguë, asymétrique, prédominant aux membres inférieurs, abolition des réflexes ostéotendineux, sans aucun déficit sensitif objectif.",
      "Surveillance épidémiologique : Déclaration obligatoire immédiate de tout cas de Paralysie Flasque Aiguë (PFA).",
      "Prélèvement virologique standardisé : 2 échantillons de selles à 24-48h d'intervalle dans les 14 jours suivant l'installation du déficit.",
      "Prévention absolue : Vaccination universelle (Vaccin Polio Oral VPO et Vaccin Polio Inactivé VPI)."
    ]
  },
  {
    id: "tb",
    tag: "Pneumo-Infectio",
    title: "Tuberculose : Dépistage & Isolement",
    subtitle: "Toux traînante > 3 semaines, sueurs nocturnes et hémoptysie",
    desc: "Transmission aéroportée par gouttelettes de Pflügge. Isolement respiratoire type Air (masque FFP2) immédiat.",
    action: "Isolement respiratoire strict en chambre individuelle et quadrithérapie antituberculeuse (RHZE).",
    points: [
      "Triade clinique évocatrice : Toux chronique > 3 semaines, sueurs nocturnes abondantes, altération de l'état général (amaigrissement, asthénie, anorexie).",
      "Signe d'alerte : Toute hémoptysie impose une recherche immédiate de BK (bacilles de Koch).",
      "Examens diagnostiques : Radiographie thoracique (lésions apicales excavées), expectorations pour examen microscopique (Ziehl-Neelsen) et test moléculaire GeneXpert MTB/RIF.",
      "Mesure barrière : Isolement respiratoire immédiat (chambre individuelle à pression négative si possible, masque chirurgical pour le patient, masque FFP2 pour les soignants).",
      "Schéma thérapeutique standard : 2 mois de quadrithérapie (Rifampicine, Isoniazide, Pyrazinamide, Éthambutol) puis 4 mois de bithérapie (Rifampicine, Isoniazide)."
    ]
  },
  {
    id: "measles",
    tag: "Pédiatrie & Vigilance",
    title: "Rougeole & Vigilance Épidémique",
    subtitle: "Signe de Koplik, éruption descendante et risque de complications",
    desc: "Maladie virale hautement contagieuse (R0 = 12 à 18). Éruption maculo-papuleuse débutant derrière les oreilles.",
    action: "Éviction de la collectivité, déclaration obligatoire et vaccination post-exposition dans les 72 heures.",
    points: [
      "Phase d'invasion (catarrhe oculo-naso-bronchique) : Fièvre élevée (39-40°C), conjonctivite avec larmoiement, rhinite, toux rauque.",
      "Signe pathognomonique précoce : Signe de Koplik (taches blanchâtres sur fond érythémateux à la face interne des joues en regard des molaires).",
      "Phase éruptive : Éruption maculo-papuleuse érythémateuse débutant à la racine des cheveux et derrière les oreilles, à extension descendante en 3 jours.",
      "Complications graves : Pneumopathie bactérienne surajoutée, encéphalite aiguë rougeoleuse, panencéphalite subaiguë sclérosante (PESS).",
      "Isolement immédiat : Chambre individuelle et précautions air jusqu'à 5 jours après le début de l'éruption."
    ]
  },
  {
    id: "cholera",
    tag: "Urgences & WASH",
    title: "Choléra : Déshydratation Aiguë & Prise en Charge",
    subtitle: "Diarrhée aqueuse en 'eau de riz' et état de choc hypovolémique",
    desc: "Vibrio cholerae produisant une entérotoxine provoquant une fuite hydrique et électrolytique massive.",
    action: "Réhydratation immédiate intraveineuse (Ringer Lactate selon plan C OMS) pour sauver le pronostic vital.",
    points: [
      "Selles diarrhéiques afébriles profuses, blanchâtres, d'aspect caractéristique en 'eau de riz', sans glaires ni sang.",
      "Vitesse de déshydratation foudroyante : perte pouvant dépasser 1 litre par heure, conduisant au choc hypovolémique en quelques heures.",
      "Signes de déshydratation sévère (Plan C OMS) : Léthargie ou coma, yeux très enfoncés, pli cutané s'effaçant très lentement (> 2 secondes), pouls radial imprenable.",
      "Protocole de réhydratation : Ringer Lactate IV 100 mL/kg (30 mL/kg en 30 min puis 70 mL/kg en 2h30 chez l'adulte).",
      "Traitement d'appoint : Sels de Réhydratation Orale (SRO) dès que le patient peut boire, antibiothérapie précoce (Doxycycline ou Azithromycine) et mesures WASH."
    ]
  },
  {
    id: "tetanus",
    tag: "Urgences & Réanimation",
    title: "Tétanos : Prophylaxie des Plaies & Trismus",
    subtitle: "Clostridium tetani, contractures musculaires et absence d'immunité naturelle",
    desc: "Toxine tétanospasmine bloquant les interneurones inhibiteurs spinaux (GABA et glycine).",
    action: "Parage chirurgical de toute plaie souillée, vérification du statut vaccinal et sérum/immunoglobulines spécifiques si indiqué.",
    points: [
      "Infection non contagieuse liée à la pénétration de spores telluriques dans une plaie cutanéo-muqueuse.",
      "Premier signe clinique : Le trismus (contracture douloureuse et invincible des muscles masséters interdisant l'ouverture de la bouche, afébrile).",
      "Généralisation : Faciès sardonique (rire sardonique), opisthotonos (hyper-extension du tronc en arc de cercle), contractures généralisées déclenchées au moindre bruit ou contact lumineux.",
      "Danger vital : Spasme laryngé et paralysie des muscles respiratoires imposant l'intubation trachéale, curarisation et ventilation mécanique en réanimation.",
      "Prophylaxie systématique des plaies : Nettoyage abondant, désinfection, vaccin antitétanique de rappel et immunoglobulines tétaniques humaines (250 UI) si plaie à haut risque et statut vaccinal incertain."
    ]
  }
];

function renderPreventions() {
  const container = document.getElementById('prevention-carousel-container');
  if (!container) return;

  container.innerHTML = preventionTopics.map(p => `
    <div class="prev-card" onclick="openPreventionDetail('${p.id}')">
      <div class="prev-top-row">
        <span class="prev-tag">${p.tag}</span>
        <span style="font-size: 16px;">🏥</span>
      </div>
      <div class="prev-title">${p.title}</div>
      <div class="prev-sub">${p.subtitle}</div>
      <div class="prev-desc">${p.desc}</div>
      <button class="prev-btn" onclick="event.stopPropagation(); openPreventionDetail('${p.id}')">
        <span>${p.action.substring(0, 42)}...</span>
        <span>→</span>
      </button>
    </div>
  `).join('');
}

function openPreventionDetail(prevId) {
  const p = preventionTopics.find(item => item.id === prevId);
  if (!p) return;

  const modalBody = document.getElementById('prevention-detail-body');
  modalBody.innerHTML = `
    <span class="prev-tag mb-2">${p.tag}</span>
    <h2 style="font-size: 18px; font-weight: 900; color: #38BDF8; margin-top: 8px; margin-bottom: 4px;">${p.title}</h2>
    <div style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 14px;">${p.subtitle}</div>

    <div class="card mb-3" style="background: rgba(2, 132, 199, 0.12); border-left: 4px solid #0284C7;">
      <div style="font-weight: 800; color: #38BDF8; margin-bottom: 4px;">Conduite à Tenir Clinique Immédiate :</div>
      <div style="font-size: 13px; color: #F8FAFC; line-height: 1.5;">${p.action}</div>
    </div>

    <h4 style="font-size: 14px; font-weight: 800; margin-bottom: 8px;">Points Clés de Prise en Charge :</h4>
    <ul style="padding-left: 18px; font-size: 13px; line-height: 1.6; color: #CBD5E1;">
      ${p.points.map(pt => `<li style="margin-bottom: 6px;">${pt}</li>`).join('')}
    </ul>
  `;

  openModal('modal-prevention-detail');
}

function openAllPreventionsModal() {
  openPreventionDetail('malaria');
}

// 6. Gestion des Dossiers Patients
function initPatientsSelect() {
  const selects = ['vitals-patient-select', 'obs-patient-select'];
  selects.forEach(sId => {
    const sel = document.getElementById(sId);
    if (!sel) return;

    let optionsHtml = '';
    if (sId === 'vitals-patient-select') {
      optionsHtml += `<option value="0">Mesure ponctuelle (sans dossier associé)</option>`;
    } else {
      optionsHtml += `<option value="new">Saisir un nouveau dossier patient</option>`;
    }

    patientsList.forEach(p => {
      optionsHtml += `<option value="${p.id}">${p.name} (${p.age} ans, ${p.department})</option>`;
    });

    sel.innerHTML = optionsHtml;
  });
}

function renderPatientsList() {
  const container = document.getElementById('patients-list-container');
  if (!container) return;

  const query = (document.getElementById('patient-search-input')?.value || '').toLowerCase();
  const filtered = patientsList.filter(p => 
    p.name.toLowerCase().includes(query) || 
    p.department.toLowerCase().includes(query) || 
    p.condition.toLowerCase().includes(query)
  );

  if (filtered.length === 0) {
    container.innerHTML = `<div style="text-align: center; color: var(--text-secondary); padding: 20px;">Aucun dossier trouvé.</div>`;
    return;
  }

  container.innerHTML = filtered.map(p => `
    <div class="card mb-2" style="cursor: pointer;" onclick="selectPatientForVisit(${p.id})">
      <div style="display: flex; justify-content: space-between; align-items: center;">
        <strong>${p.name}</strong>
        <span class="prev-tag" style="font-size: 10px;">${p.department}</span>
      </div>
      <div style="font-size: 12px; color: var(--text-secondary); margin-top: 4px;">
        ${p.age} ans • ${p.gender} • ${p.room}
      </div>
      <div style="font-size: 12px; color: #38BDF8; margin-top: 2px;">
        ${p.condition}
      </div>
    </div>
  `).join('');
}

function selectPatientForVisit(patientId) {
  const p = patientsList.find(item => item.id === patientId);
  if (!p) return;

  closeModal('modal-patients');

  // Remplir formulaire observation
  const obsName = document.getElementById('obs-patient-name');
  if (obsName) obsName.value = p.name;
  const obsAge = document.getElementById('obs-age');
  if (obsAge) obsAge.value = p.age;
  const obsGender = document.getElementById('obs-gender');
  if (obsGender) obsGender.value = p.gender;
  const obsDept = document.getElementById('obs-dept');
  if (obsDept) obsDept.value = p.department;
  const obsRoom = document.getElementById('obs-room');
  if (obsRoom) obsRoom.value = p.room;

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
  const temp = parseNum('vital-temp', 37.0);
  const pas = parseNum('vital-pas', 120);
  const pad = parseNum('vital-pad', 80);
  const fc = parseNum('vital-fc', 75);
  const fr = parseNum('vital-fr', 16);
  const spo2 = parseNum('vital-spo2', 98);
  const gly = parseNum('vital-gly', 1.0);
  const weight = parseNum('vital-weight', 70);
  const height = parseNum('vital-height', 175);

  const pam = (pas + (2 * pad)) / 3;
  const imc = height > 0 ? (weight / Math.pow(height / 100, 2)) : 22.0;

  let alertLevel = 'NORMAL';
  let badgeText = 'Paramètres Stables et Physiologiques';
  let points = [];

  // Température
  if (temp >= 38.5) {
    points.push({ name: 'Température', obs: `${temp}°C — Syndrome fébrile franc (rechercher foyer infectieux)`, level: temp >= 39.5 ? 'CRITICAL' : 'MODERATE' });
  } else if (temp < 35.5) {
    points.push({ name: 'Température', obs: `${temp}°C — Hypothermie (risque de choc ou sepsis sévère)`, level: 'CRITICAL' });
  }

  // Hémodynamique
  if (pas < 90 || pam < 65) {
    points.push({ name: 'Hémodynamique', obs: `PAS ${pas} mmHg / PAM ${pam.toFixed(1)} mmHg — Hypotension artérielle / Risque de choc`, level: 'CRITICAL' });
  } else if (pas >= 180 || pad >= 110) {
    points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Crise hypertensive sévère (rechercher souffrance viscérale)`, level: 'CRITICAL' });
  } else if (pas >= 140 || pad >= 90) {
    points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Hypertension artérielle modérée`, level: 'MODERATE' });
  }

  // Oxygénation
  if (spo2 < 90) {
    points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Hypoxémie sévère imposant oxygénothérapie urgente`, level: 'CRITICAL' });
  } else if (spo2 <= 94) {
    points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Hypoxémie modérée à surveiller`, level: 'MODERATE' });
  }

  // Respiration
  if (fr > 24) {
    points.push({ name: 'Respiration', obs: `FR ${fr}/min — Polypnée / tachypnée`, level: fr >= 30 ? 'CRITICAL' : 'MODERATE' });
  } else if (fr < 10) {
    points.push({ name: 'Respiration', obs: `FR ${fr}/min — Bradypnée critique (risque d'épuisement)`, level: 'CRITICAL' });
  }

  // Rythme cardiaque
  if (fc >= 120) {
    points.push({ name: 'Fréquence cardiaque', obs: `FC ${fc} bpm — Tachycardie marquée`, level: 'MODERATE' });
  } else if (fc < 50) {
    points.push({ name: 'Fréquence cardiaque', obs: `FC ${fc} bpm — Bradycardie à surveiller`, level: 'MODERATE' });
  }

  // Glycémie
  if (gly < 0.70) {
    points.push({ name: 'Glycémie', obs: `${gly} g/L — Hypoglycémie (resucrage oral ou IV immédiat)`, level: 'CRITICAL' });
  } else if (gly > 2.0) {
    points.push({ name: 'Glycémie', obs: `${gly} g/L — Hyperglycémie significative`, level: 'MODERATE' });
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
    <div class="result-card" style="border-left: 4px solid ${alertLevel === 'CRITICAL' ? 'var(--alert-red)' : alertLevel === 'MODERATE' ? 'var(--alert-orange)' : 'var(--alert-green)'};">
      <div style="font-size: 15px; font-weight: 800; color: ${alertLevel === 'CRITICAL' ? '#EF4444' : alertLevel === 'MODERATE' ? '#F59E0B' : '#10B981'}; margin-bottom: 8px;">
        ● ${badgeText}
      </div>
      <div class="form-row mb-2" style="background: rgba(255,255,255,0.04); padding: 8px; border-radius: 8px;">
        <div><strong>PAM calculée :</strong> ${pam.toFixed(1)} mmHg</div>
        <div><strong>Indice de Masse Corporelle (IMC) :</strong> ${imc.toFixed(1)} kg/m²</div>
      </div>
      <h4 style="font-size: 13.5px; font-weight: 800; margin-top: 10px; margin-bottom: 6px; color: #38BDF8;">Interprétation Systématique :</h4>
      ${points.length > 0 ? points.map(p => `
        <div style="font-size: 12.5px; margin-bottom: 5px; line-height: 1.5; color: ${p.level === 'CRITICAL' ? '#FCA5A5' : '#FDE68A'};">
          • <strong>${p.name} :</strong> ${p.obs}
        </div>
      `).join('') : '<div style="font-size: 12.5px; color: #A7F3D0;">Tous les paramètres vitaux mesurés sont physiologiques et dans les normes.</div>'}
    </div>
  `;
  resultContainer.style.display = 'block';
  resultContainer.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
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

      <text x="50" y="170" fill="#64748B" font-size="10">J0</text>
      <text x="120" y="170" fill="#64748B" font-size="10">J1 Matin</text>
      <text x="190" y="170" fill="#64748B" font-size="10">J1 Soir</text>
      <text x="260" y="170" fill="#64748B" font-size="10">J2 Matin</text>
      <text x="330" y="170" fill="#64748B" font-size="10">J2 Soir</text>

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

// 8. MOTEUR ANALYSES BIOLOGIQUES & SCORES
// 8A. NFS Complète
function calculateNFS() {
  const hb = parseNum('nfs-hb', 13.5);
  const ht = parseNum('nfs-ht', 41.0);
  const gr = parseNum('nfs-gr', 4.7);
  const gb = parseNum('nfs-gb', 7.2);
  const pnn = parseNum('nfs-pnn', 4.5);
  const lympho = parseNum('nfs-lympho', 2.1);
  const mono = parseNum('nfs-mono', 0.5);
  const eosino = parseNum('nfs-eosino', 0.15);
  const baso = parseNum('nfs-baso', 0.05);
  const plt = parseNum('nfs-plt', 240);
  const retic = parseNum('nfs-retic', 65);
  const vpm = parseNum('nfs-vpm', 9.5);

  const vgm = gr > 0 ? (ht * 10) / gr : 88.0;
  const ccmh = ht > 0 ? (hb / ht) * 100 : 33.0;

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

  let conclusionText = "";
  if (!isAnemia && !isHyperleukocytosis && !isLeukopenia && !isThrombopenia && !isThrombocytosis) {
    conclusionText = "La NFS ne montre pas d'anomalie hématologique significative. Les trois lignées sanguines présentent des valeurs physiologiques.";
  } else if (isAnemia && isThrombopenia && isLeukopenia) {
    conclusionText = "La NFS a objectivé une pancytopénie associant anémie, leucopénie et thrombopénie, nécessitant un myélogramme en urgence.";
  } else if (isAnemia && isThrombopenia) {
    conclusionText = "La NFS a objectivé une bicytopénie associant anémie et thrombopénie avec conservation relative de la lignée blanche.";
  } else if (isAnemia && isThrombocytosis) {
    conclusionText = `La NFS a objectivé une anémie ${isMicrocytic ? 'microcytaire' : 'normocytaire'} associée à une thrombocytose réactionnelle évoquant une carence martiale ou un syndrome inflammatoire.`;
  } else if (isAnemia) {
    conclusionText = `La NFS a objectivé une anémie ${isMicrocytic ? 'microcytaire' : isMacrocytic ? 'macrocytaire' : 'normocytaire'} ${isHypochromic ? 'hypochrome' : 'normochrome'} isolée.`;
  } else if (isHyperleukocytosis) {
    conclusionText = `La NFS a objectivé une hyperleucocytose (${gb} G/L) à prédominance neutrophile (${pnn} G/L) orientant vers une infection bactérienne.`;
  } else if (isThrombopenia) {
    conclusionText = `La NFS a objectivé une thrombopénie isolée (${plt} G/L).`;
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
      : `Plaquettes dans les normes (${plt} G/L, VPM ${vpm} fL).`;

  const res = document.getElementById('nfs-result');
  res.innerHTML = `
    <div class="result-card" style="border-left: 4px solid #0284C7;">
      <h3 style="font-size: 15px; font-weight: 800; color: #38BDF8; margin-bottom: 6px;">Conclusion & Synthèse Hématologique Globale :</h3>
      <p style="font-size: 13.5px; font-weight: 700; line-height: 1.6; color: #F8FAFC;">${conclusionText}</p>
      <div style="font-size: 12.5px; margin-top: 10px; margin-bottom: 4px;"><strong>🔴 Lignée Rouge :</strong> ${redSummary}</div>
      <div style="font-size: 12.5px; margin-bottom: 4px;"><strong>⚪ Lignée Blanche :</strong> ${whiteSummary}</div>
      <div style="font-size: 12.5px;"><strong>🟡 Plaquettes :</strong> ${pltSummary}</div>
    </div>
  `;
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 8B. Fonction Rénale & Cockcroft-Gault
function calculateRenal() {
  const creat = parseNum('ren-creat', 78);
  const age = parseNum('ren-age', 65);
  const weight = parseNum('ren-weight', 70);
  const gender = document.getElementById('ren-gender')?.value || 'M';

  if (creat <= 0) {
    alert("Veuillez saisir une valeur de créatininémie valide.");
    return;
  }

  const k = gender === 'M' ? 1.23 : 1.04;
  const clcr = ((140 - age) * weight * k) / creat;

  let stage = "";
  let badgeColor = "#10B981";
  let recommendation = "";

  if (clcr >= 90) {
    stage = "Stade 1 : Fonction rénale normale (DFG conservé)";
    badgeColor = "#10B981";
    recommendation = "Surveillance habituelle. Pas d'adaptation posologique requise pour la majorité des thérapeutiques.";
  } else if (clcr >= 60) {
    stage = "Stade 2 : Insuffisance rénale chronique légère";
    badgeColor = "#F59E0B";
    recommendation = "Surveillance annuelle de la fonction rénale et de la protéinurie. Éviter les agents néphrotoxiques (AINS, produits de contraste iodés non hydratés).";
  } else if (clcr >= 30) {
    stage = "Stade 3 : Insuffisance rénale chronique modérée";
    badgeColor = "#F97316";
    recommendation = "ADAPTATION POSOLOGIQUE IMPÉRATIVE de tous les médicaments à élimination rénale (anticoagulants, antibiotiques, antidiabétiques oraux). Évaluation néphrologique.";
  } else if (clcr >= 15) {
    stage = "Stade 4 : Insuffisance rénale chronique sévère";
    badgeColor = "#EF4444";
    recommendation = "Prise en charge néphrologique spécialisée étroite, prévention des complications cardiovasculaires, de l'anémie et de l'hyperkaliémie. Préservation du capital veineux pour abord de dialyse.";
  } else {
    stage = "Stade 5 : Insuffisance rénale terminale (DFG < 15 mL/min)";
    badgeColor = "#DC2626";
    recommendation = "URGENCE NÉPHROLOGIQUE. Indication formelle de suppléance rénale (hémodialyse, dialyse péritonéale ou inscription sur liste de transplantation).";
  }

  const res = document.getElementById('ren-result');
  res.innerHTML = `
    <div class="result-card" style="border-left: 4px solid ${badgeColor};">
      <div style="font-size: 13px; color: var(--text-secondary); margin-bottom: 2px;">Clairance estimée selon Cockcroft-Gault :</div>
      <div style="font-size: 22px; font-weight: 900; color: #38BDF8; margin-bottom: 6px;">${clcr.toFixed(1)} mL/min</div>
      <div style="font-size: 14.5px; font-weight: 800; color: ${badgeColor}; margin-bottom: 8px;">● ${stage}</div>
      <div style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5;"><strong>Conduite Clinique :</strong> ${recommendation}</div>
    </div>
  `;
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 8C. Bilan Hépatique
function calculateHepatic() {
  const alat = parseNum('hep-alat', 32);
  const asat = parseNum('hep-asat', 28);
  const pal = parseNum('hep-pal', 85);
  const ggt = parseNum('hep-ggt', 25);
  const bili = parseNum('hep-bili', 12);
  const lipase = parseNum('hep-lipase', 38);

  let findings = [];
  let isAlert = false;

  // Cytolyse
  if (alat > 45 || asat > 35) {
    const ratio = alat > 0 ? (asat / alat).toFixed(2) : "1";
    if (alat > 450 || asat > 350) {
      findings.push(`🚨 <strong>Cytolyse hépatique majeure (> 10N) :</strong> Évoquer en priorité une hépatite virale aiguë (A, B, E), médicamenteuse/toxique (paracétamol) ou ischémique (foie de choc).`);
      isAlert = true;
    } else {
      findings.push(`⚠️ <strong>Cytolyse hépatique modérée :</strong> ALAT ${alat} UI/L, ASAT ${asat} UI/L (Ratio de De Ritis ASAT/ALAT = ${ratio}). Évoquer NASH, hépatite chronique virale ou alcoolique.`);
    }
  }

  // Cholestase
  if (pal > 120 || ggt > 55) {
    findings.push(`⚠️ <strong>Syndrome de cholestase biologique :</strong> PAL ${pal} UI/L, GGT ${ggt} UI/L. Échographie abdominale requise pour rechercher une dilatation des voies biliaires (lithiase, tumeur péri-ampullaire).`);
  }

  // Pancréatite
  if (lipase > 180) {
    findings.push(`🚨 <strong>Pancréatite aiguë (> 3N) :</strong> Lipasémie à ${lipase} UI/L. Hospitalisation immédiate en urgence, à jeun, analgésie multimodale, scanner abdomino-pelvien injecté à H48-H72.`);
    isAlert = true;
  }

  // Bilirubine
  if (bili > 25) {
    findings.push(`🟡 <strong>Hyperbilirubinémie (${bili} µmol/L) :</strong> Ictère cutanéo-muqueux clinique à surveiller, typer la bilirubine (conjuguée vs libre).`);
  }

  if (findings.length === 0) {
    findings.push(`✓ <strong>Bilan hépato-biliaire et pancréatique strictement normal :</strong> Absence de cytolyse, de cholestase ou de souffrance pancréatique aiguë.`);
  }

  const res = document.getElementById('hep-result');
  res.innerHTML = `
    <div class="result-card" style="border-left: 4px solid ${isAlert ? 'var(--alert-red)' : '#0284C7'};">
      <h3 style="font-size: 15px; font-weight: 800; color: #38BDF8; margin-bottom: 8px;">Synthèse du Bilan Hépatique & Pancréatique :</h3>
      <ul style="padding-left: 18px; font-size: 13px; line-height: 1.6; color: #F8FAFC;">
        ${findings.map(f => `<li style="margin-bottom: 6px;">${f}</li>`).join('')}
      </ul>
    </div>
  `;
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 8D. Hémostase & Inflammation
function calculateCoag() {
  const crp = parseNum('coag-crp', 5);
  const tp = parseNum('coag-tp', 98);
  const inr = parseNum('coag-inr', 1.0);
  const ddim = parseNum('coag-ddim', 280);

  let items = [];

  // CRP
  if (crp > 50) {
    items.push(`🚨 <strong>Syndrome inflammatoire biologique franc (CRP ${crp} mg/L) :</strong> Évoquer en priorité une infection bactérienne sévère ou une connectivite/vascularite aiguë.`);
  } else if (crp > 5) {
    items.push(`⚠️ <strong>Syndrome inflammatoire modéré (CRP ${crp} mg/L) :</strong> Infection virale ou bactérienne débutante, foyer inflammatoire localisé.`);
  } else {
    items.push(`✓ <strong>CRP normale (< 5 mg/L) :</strong> Absence de syndrome inflammatoire systémique détectable.`);
  }

  // Hémostase (TP / INR)
  if (tp < 70) {
    items.push(`⚠️ <strong>Baisse du Taux de Prothrombine (TP ${tp}%, INR ${inr}) :</strong> Évoquer insuffisance hépatocellulaire, déficit en vitamine K (cholestase, carence d'apport) ou traitement antivitamine K (AVK).`);
  } else {
    items.push(`✓ <strong>Hémostase normale :</strong> TP ${tp}%, INR ${inr}.`);
  }

  // D-Dimères
  if (ddim > 500) {
    items.push(`⚠️ <strong>D-Dimères élevés (${ddim} ng/mL > 500 ng/mL) :</strong> Suspicion de maladie thromboembolique veineuse (phlébite ou embolie pulmonaire) à confronter aux scores de probabilité clinique de Wells.`);
  } else {
    items.push(`✓ <strong>D-Dimères négatifs (< 500 ng/mL) :</strong> Valeur prédictive négative > 98% permettant d'éliminer formellement une thrombose veineuse profonde ou embolie pulmonaire en cas de probabilité clinique non forte.`);
  }

  const res = document.getElementById('coag-result');
  res.innerHTML = `
    <div class="result-card" style="border-left: 4px solid #0284C7;">
      <h3 style="font-size: 15px; font-weight: 800; color: #38BDF8; margin-bottom: 8px;">Analyse Hémostase, Coagulation & Marqueurs Inflammatoires :</h3>
      <ul style="padding-left: 18px; font-size: 13px; line-height: 1.6; color: #F8FAFC;">
        ${items.map(it => `<li style="margin-bottom: 6px;">${it}</li>`).join('')}
      </ul>
    </div>
  `;
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 8E. Score de Glasgow (GCS)
function calculateGlasgow() {
  const e = parseInt(document.getElementById('gcs-e')?.value) || 4;
  const v = parseInt(document.getElementById('gcs-v')?.value) || 5;
  const m = parseInt(document.getElementById('gcs-m')?.value) || 6;

  const total = e + v + m;

  let severity = "";
  let color = "#10B981";
  let alertDetails = "";

  if (total === 15) {
    severity = "Score GCS 15/15 — Conscience Parfaitement Normale";
    color = "#10B981";
    alertDetails = "Patient vigilant, parfaitement orienté dans le temps et l'espace, répondant aux ordres simples de façon adaptée.";
  } else if (total >= 13) {
    severity = `Score GCS ${total}/15 — Traumatisme Crânien Léger / Confusion`;
    color = "#F59E0B";
    alertDetails = "Altération discrète de la vigilance. Surveillance neurologique horaire recommandée (diamètre et réactivité pupillaire, motricité).";
  } else if (total >= 9) {
    severity = `Score GCS ${total}/15 — Traumatisme Crânien Modéré / Coma Léger`;
    color = "#F97316";
    alertDetails = "Coma modéré. Risque d'aggravation secondaire. Scanner cérébral sans injection en urgence et avis neurochirurgical/réanimation.";
  } else {
    severity = `Score GCS ${total}/15 — COMA GRAVE (DÉTRESSE NEUROLOGIQUE)`;
    color = "#DC2626";
    alertDetails = "🚨 DANGER VITAL IMMÉDIAT (GCS ≤ 8). Perte des réflexes de protection des voies aériennes supérieures. INDICATION FORMELLE ET IMMÉDIATE D'INTUBATION OROTRACHÉALE sous sédation en séquence rapide.";
  }

  const res = document.getElementById('gcs-result');
  res.innerHTML = `
    <div class="result-card" style="border-left: 4px solid ${color};">
      <div style="font-size: 13px; color: var(--text-secondary); margin-bottom: 2px;">Détail : Yeux = ${e}/4, Verbe = ${v}/5, Motricité = ${m}/6</div>
      <div style="font-size: 24px; font-weight: 900; color: #38BDF8; margin-bottom: 4px;">GCS Total : ${total} / 15</div>
      <div style="font-size: 14.5px; font-weight: 800; color: ${color}; margin-bottom: 8px;">● ${severity}</div>
      <div style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5;">${alertDetails}</div>
    </div>
  `;
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 9. FORMATION CLINIQUE PÉDAGOGIQUE (14 Compétences & Modules)
let currentTrainingSubTab = 'anamnesis';

function openClinicalTrainingModal() {
  renderClinicalTraining('anamnesis');
  openModal('modal-clinical-training');
}

function renderClinicalTraining(subTab = 'anamnesis') {
  currentTrainingSubTab = subTab;
  const container = document.getElementById('clinical-training-content');
  if (!container) return;

  container.innerHTML = `
    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 14px;">
      <div style="width: 38px; height: 38px; border-radius: 50%; background: #0284C7; display: flex; align-items: center; justify-content: center; font-size: 20px;">🎓</div>
      <div>
        <h2 style="font-size: 17px; font-weight: 900; color: #38BDF8;">Formation Clinique Pédagogique</h2>
        <div style="font-size: 11.5px; color: var(--text-secondary);">Référentiel Méthodique de l'Externe en Médecine</div>
      </div>
    </div>

    <!-- Sous-onglets de la formation -->
    <div class="training-nav-tabs">
      <button class="training-tab-btn ${subTab === 'anamnesis' ? 'active' : ''}" onclick="renderClinicalTraining('anamnesis')">A. Interrogatoire (14)</button>
      <button class="training-tab-btn ${subTab === 'organization' ? 'active' : ''}" onclick="renderClinicalTraining('organization')">B. Organisation (9)</button>
      <button class="training-tab-btn ${subTab === 'observation' ? 'active' : ''}" onclick="renderClinicalTraining('observation')">C. Observation (5)</button>
      <button class="training-tab-btn ${subTab === 'synthesis' ? 'active' : ''}" onclick="renderClinicalTraining('synthesis')">D. Synthèse Clinique</button>
    </div>

    <div id="training-tab-content-body">
      ${getTrainingTabContentHtml(subTab)}
    </div>
  `;
}

function getTrainingTabContentHtml(subTab) {
  if (subTab === 'anamnesis') {
    const skills = window.ALL_ANAMNESIS_SKILLS || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px; line-height: 1.5;">
        Explorez les <strong>14 étapes chronologiques</strong> pour mener un interrogatoire médical structuré, bienveillant et diagnostiquement efficace au lit du malade.
      </p>
      ${skills.map((s, idx) => `
        <div class="training-card" id="train-card-${s.id}">
          <div class="training-card-header" onclick="toggleTrainingAccordion('train-body-${s.id}')">
            <div>
              <div style="font-size: 14px; font-weight: 800; color: #F8FAFC;">${s.title}</div>
              <div style="font-size: 11.5px; color: #38BDF8; margin-top: 2px;">${s.subtitle}</div>
            </div>
            <span style="font-size: 14px; color: #94A3B8;">▼</span>
          </div>
          <div id="train-body-${s.id}" style="display: ${idx === 0 ? 'block' : 'none'}; margin-top: 10px; border-top: 1px solid #1E2D4A; padding-top: 10px;">
            <p style="font-size: 12.5px; line-height: 1.5; color: #CBD5E1; margin-bottom: 8px;">${s.explanation}</p>
            
            ${s.phrasings && s.phrasings.length > 0 ? `
              <div class="phrasing-box">
                <strong style="color: #38BDF8;">💬 Formulations professionnelles recommandées :</strong>
                <ul style="padding-left: 16px; margin-top: 4px;">
                  ${s.phrasings.map(ph => `<li>${ph}</li>`).join('')}
                </ul>
              </div>
            ` : ''}

            ${s.errors && s.errors.length > 0 ? `
              <div class="error-box">
                <strong style="color: #F87171;">⚠️ Erreurs et attitudes à proscrire :</strong>
                <ul style="padding-left: 16px; margin-top: 4px;">
                  ${s.errors.map(er => `<li>${er}</li>`).join('')}
                </ul>
              </div>
            ` : ''}

            ${s.exerciseQuestion ? `
              <div class="card mb-2" style="background: rgba(255,255,255,0.03); padding: 10px; margin-top: 8px;">
                <strong style="font-size: 12.5px; color: #34D399;">🎯 Exercice d'application clinique :</strong>
                <div style="font-size: 12px; margin-top: 4px; margin-bottom: 8px;">${s.exerciseQuestion}</div>
                <div style="display: flex; flex-direction: column; gap: 6px;">
                  ${s.exerciseChoices.map((ch, cIdx) => `
                    <button class="quiz-option-btn" style="min-height: 40px; padding: 8px 10px; font-size: 12px;" onclick="handleTrainingChoice(${s.id}, ${cIdx}, ${s.correctChoice}, '${escapeHtml(s.exerciseCorrection)}')">
                      <span class="opt-letter" style="width: 22px; height: 22px; font-size: 11px;">${['A', 'B', 'C', 'D'][cIdx]}</span>
                      <span class="opt-text">${ch}</span>
                    </button>
                  `).join('')}
                </div>
                <div id="train-feedback-${s.id}" class="quiz-feedback-box" style="display: none; font-size: 12px;"></div>
              </div>
            ` : ''}
          </div>
        </div>
      `).join('')}
    `;
  } else if (subTab === 'organization') {
    const categories = window.ALL_INFO_CATEGORIES || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
        Les <strong>9 rubriques fondamentales</strong> pour organiser les données recueillies dans le dossier médical.
      </p>
      ${categories.map(c => `
        <div class="training-card">
          <div style="font-size: 14.5px; font-weight: 800; color: #38BDF8; margin-bottom: 4px;">${c.categoryName}</div>
          <p style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5; margin-bottom: 8px;">${c.description}</p>
          <div class="phrasing-box">
            <strong style="color: #38BDF8;">Ce qu'il faut consigner :</strong>
            <ul style="padding-left: 16px; margin-top: 4px;">
              ${c.whatToInclude.map(item => `<li>${item}</li>`).join('')}
            </ul>
          </div>
          ${c.whatToExclude ? `<div style="font-size: 12px; color: #FCA5A5; margin-top: 4px;"><strong>À ne pas confondre :</strong> ${c.whatToExclude}</div>` : ''}
          ${c.hospitalExample ? `<div style="font-size: 12px; color: #94A3B8; margin-top: 4px; font-style: italic;"><strong>Exemple concret :</strong> « ${c.hospitalExample} »</div>` : ''}
        </div>
      `).join('')}
    `;
  } else if (subTab === 'observation') {
    const obsSkills = window.ALL_OBSERVATION_SKILLS || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
        Méthodologie de rédaction et d'observation au lit du malade.
      </p>
      ${obsSkills.map(o => `
        <div class="training-card">
          <div style="font-size: 14.5px; font-weight: 800; color: #38BDF8; margin-bottom: 4px;">${o.title}</div>
          <div style="font-size: 12px; color: #A7F3D0; margin-bottom: 6px;"><strong>Objectif :</strong> ${o.objective}</div>
          <p style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5; margin-bottom: 8px;">${o.detailedGuide}</p>
          <div class="phrasing-box">
            <strong style="color: #34D399;">✓ Bon exemple de rédaction :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.5;">${o.goodExample}</p>
          </div>
          <div class="error-box">
            <strong style="color: #F87171;">✗ Exemple déconseillé :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.5;">${o.badExample}</p>
          </div>
        </div>
      `).join('')}
    `;
  } else if (subTab === 'synthesis') {
    const cases = window.ALL_SYNTHESIS_CASES || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
        Cas cliniques guidés pour perfectionner la <strong>synthèse diagnostique par problème</strong>.
      </p>
      ${cases.map(cs => `
        <div class="training-card">
          <div style="font-size: 15px; font-weight: 900; color: #38BDF8; margin-bottom: 4px;">${cs.title}</div>
          <div style="font-size: 12px; color: #F8FAFC; margin-bottom: 6px;"><strong>Profil Patient :</strong> ${cs.patientProfile}</div>
          <div class="card mb-2" style="background: rgba(255,255,255,0.02); font-size: 12px;">
            <div><strong>Symptômes recueillis :</strong> ${cs.symptomsCollected.join(', ')}</div>
            <div style="margin-top: 4px;"><strong>Signes physiques retrouvés :</strong> ${cs.physicalSignsFound.join(', ')}</div>
          </div>
          <div class="phrasing-box">
            <strong style="color: #38BDF8;">Cheminement diagnostique :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.5;">${cs.clinicalReasoningPath}</p>
          </div>
          <div style="font-size: 12.5px; margin-top: 6px;"><strong>Hypothèse principale :</strong> <span style="color: #34D399; font-weight: 700;">${cs.primaryHypothesis}</span></div>
          <div style="font-size: 12.5px; margin-top: 4px;"><strong>Urgence à éliminer :</strong> <span style="color: #F87171; font-weight: 700;">${cs.emergencyToRuleOut}</span></div>
          <div style="font-size: 12px; margin-top: 6px; color: #BAE6FD;">
            <strong>Plan d'action immédiat :</strong>
            <ul style="padding-left: 16px; margin-top: 2px;">
              ${cs.immediateActionPlan.map(act => `<li>${act}</li>`).join('')}
            </ul>
          </div>
        </div>
      `).join('')}
    `;
  }
  return '';
}

function toggleTrainingAccordion(bodyId) {
  const el = document.getElementById(bodyId);
  if (!el) return;
  el.style.display = el.style.display === 'none' ? 'block' : 'none';
}

function handleTrainingChoice(skillId, selectedIdx, correctIdx, correction) {
  const card = document.getElementById(`train-card-${skillId}`);
  if (!card) return;

  const buttons = card.querySelectorAll('.quiz-option-btn');
  buttons.forEach((b, idx) => {
    b.onclick = null;
    if (idx === correctIdx) {
      b.classList.add('correct');
    } else if (idx === selectedIdx) {
      b.classList.add('wrong');
    }
  });

  const fb = document.getElementById(`train-feedback-${skillId}`);
  if (fb) {
    const ok = selectedIdx === correctIdx;
    fb.innerHTML = `
      <strong style="color: ${ok ? '#34D399' : '#F87171'};">${ok ? '✓ Exact !' : '✗ Incorrect.'}</strong>
      <div style="margin-top: 4px; line-height: 1.5;">${correction}</div>
    `;
    fb.style.display = 'block';
  }
}

// 10. SÉMIOLOGIE : 29 FICHES & 90 QCM OFFICIELS
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
    const matchQuery = !query || s.name.toLowerCase().includes(query) || (s.definition || '').toLowerCase().includes(query);
    return matchCat && matchQuery;
  });

  container.innerHTML = sheets.map(s => `
    <div class="card mb-3" style="cursor: pointer;" onclick="openSheetModal('${s.id}')">
      <div style="display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 4px;">
        <span class="prev-tag">${s.categoryName} • ${s.subCategory || 'Signe'}</span>
        <span style="color: #38BDF8; font-size: 16px;">›</span>
      </div>
      <h3 style="font-size: 15.5px; font-weight: 800; color: #F8FAFC; margin-bottom: 4px;">${s.name}</h3>
      <p style="font-size: 12.5px; color: var(--text-secondary); line-height: 1.5;">${(s.definition || '').substring(0, 140)}...</p>
    </div>
  `).join('');
}

function openSheetModal(sheetId) {
  const s = (window.ALL_SEMIOLOGY_SHEETS || []).find(item => item.id === sheetId);
  if (!s) return;

  const modalBody = document.getElementById('modal-sheet-content-body');
  modalBody.innerHTML = `
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
      <span class="prev-tag">${s.categoryName} • ${s.subCategory || 'Signe'}</span>
    </div>
    <h2 style="font-size: 18px; font-weight: 900; color: #38BDF8; margin-bottom: 12px;">${s.name}</h2>

    <div class="card mb-2" style="background: rgba(2, 132, 199, 0.1);">
      <strong>1. Définition Sémiologique :</strong>
      <p style="font-size: 13px; margin-top: 4px; line-height: 1.6;">${s.definition || 'Non précisé'}</p>
    </div>

    ${s.physiopathology ? `
      <div class="card mb-2">
        <strong>2. Physiopathologie :</strong>
        <p style="font-size: 12.5px; margin-top: 4px; color: var(--text-secondary); line-height: 1.5;">${s.physiopathology}</p>
      </div>
    ` : ''}

    ${s.etiologies && s.etiologies.length > 0 ? `
      <div class="card mb-2">
        <strong>3. Étiologies Principales :</strong>
        <ul style="padding-left: 18px; font-size: 12.5px; margin-top: 4px; line-height: 1.5;">
          ${s.etiologies.map(e => `<li>${e}</li>`).join('')}
        </ul>
      </div>
    ` : ''}

    <div class="card mb-2">
      <strong>4. Examen Physique & Anamnèse :</strong>
      ${s.anamnesis ? `<p style="font-size: 12.5px; margin-top: 4px;"><strong>Anamnèse :</strong> ${s.anamnesis}</p>` : ''}
      ${s.physicalExam ? `<p style="font-size: 12.5px; margin-top: 4px;"><strong>Examen Physique :</strong> ${s.physicalExam}</p>` : ''}
      ${s.searchMethod ? `<p style="font-size: 12.5px; margin-top: 4px;"><strong>Méthode de recherche :</strong> ${s.searchMethod}</p>` : ''}
    </div>

    ${s.gravitySigns && s.gravitySigns.length > 0 ? `
      <div class="card mb-2" style="background: rgba(220, 38, 38, 0.1); border-color: var(--alert-red);">
        <strong style="color: #F87171;">5. Signes de Gravité / Alerte Vitale :</strong>
        <ul style="padding-left: 18px; font-size: 12.5px; margin-top: 4px;">
          ${s.gravitySigns.map(g => `<li>${g}</li>`).join('')}
        </ul>
      </div>
    ` : ''}

    ${s.paraclinical && s.paraclinical.length > 0 ? `
      <div class="card mb-2">
        <strong>6. Examens Complémentaires Clés :</strong>
        <ul style="padding-left: 18px; font-size: 12.5px; margin-top: 4px;">
          ${s.paraclinical.map(p => `<li>${p}</li>`).join('')}
        </ul>
      </div>
    ` : ''}

    ${s.keyPoints && s.keyPoints.length > 0 ? `
      <div class="card mb-2" style="background: rgba(16, 185, 129, 0.1); border-color: var(--alert-green);">
        <strong style="color: #34D399;">7. Points Clés à Retenir pour l'Examen :</strong>
        <ul style="padding-left: 18px; font-size: 12.5px; margin-top: 4px;">
          ${s.keyPoints.map(k => `<li>${k}</li>`).join('')}
        </ul>
      </div>
    ` : ''}
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
      <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
        <span class="prev-tag">${q.categoryName}</span>
        <span style="font-size: 11.5px; color: var(--text-secondary); font-weight: 700;">Question ${idx + 1} / ${questions.length}</span>
      </div>
      <div style="font-size: 14.5px; font-weight: 800; color: #F8FAFC; margin-bottom: 12px; line-height: 1.5;">${q.q}</div>
      <div class="quiz-options-group">
        ${q.opts.map((opt, oIdx) => `
          <button type="button" class="quiz-option-btn" id="btn-opt-${q.id}-${oIdx}" onclick="handleQuizAnswer('${q.id}', ${oIdx}, ${q.correct}, '${escapeHtml(q.exp)}')">
            <span class="opt-letter">${['A', 'B', 'C', 'D'][oIdx]}</span>
            <span class="opt-text">${opt}</span>
          </button>
        `).join('')}
      </div>
      <div id="quiz-feedback-${q.id}" class="quiz-feedback-box" style="display: none;"></div>
    </div>
  `).join('');
}

function handleQuizAnswer(qId, selectedIdx, correctIdx, exp) {
  const box = document.getElementById(`quiz-box-${qId}`);
  if (!box) return;

  const buttons = box.querySelectorAll('.quiz-option-btn');
  buttons.forEach((btn, idx) => {
    btn.onclick = null; // Désactiver pour empêcher de recliquer
    if (idx === correctIdx) {
      btn.classList.add('correct');
    } else if (idx === selectedIdx) {
      btn.classList.add('wrong');
    }
  });

  const fb = document.getElementById(`quiz-feedback-${qId}`);
  if (fb) {
    const isCorrect = selectedIdx === correctIdx;
    if (isCorrect) quizScore.correct++;
    quizScore.totalAnswered++;

    fb.innerHTML = `
      <div style="display: flex; align-items: center; justify-content: space-between; margin-bottom: 6px;">
        <strong style="color: ${isCorrect ? '#34D399' : '#F87171'}; font-size: 14px;">
          ${isCorrect ? '✓ Excellente réponse !' : '✗ Réponse incorrecte.'}
        </strong>
        <span style="font-size: 11px; color: #94A3B8;">Score : ${quizScore.correct}/${quizScore.totalAnswered}</span>
      </div>
      <p style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5; margin: 0;">${exp}</p>
    `;
    fb.style.display = 'block';
  }
}

// 11. Profil Étudiant
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
    <div class="result-card" style="border-left: 4px solid var(--alert-green);">
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
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 12. Thème Sombre / Clair
function initTheme() {
  const saved = localStorage.getItem(STORAGE_KEY_THEME) || 'dark';
  document.documentElement.setAttribute('data-theme', saved);
}

function toggleTheme() {
  const curr = document.documentElement.getAttribute('data-theme') || 'dark';
  const next = curr === 'dark' ? 'light' : 'dark';
  document.documentElement.setAttribute('data-theme', next);
  localStorage.setItem(STORAGE_KEY_THEME, next);
}

// 13. Initialisation Globale
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
