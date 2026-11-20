/**
 * EL VITA — Compagnon Clinique & Pédagogique (iOS PWA / Web Offline)
 * Version 6.0 — Synchronisation complète avec la mise à niveau EL VITA Android
 */

// 1. Clés de Stockage Local (Rétrocompatibles avec versions précédentes)
const STORAGE_KEY_PROFILE = 'elvita_web_student_profile';
const STORAGE_KEY_PROFILE_PHOTO = 'elvita_web_student_profile_photo';
const STORAGE_KEY_THEME = 'elvita_web_theme';
const STORAGE_KEY_PATIENTS = 'elvita_web_patients';
const STORAGE_KEY_PATIENT_RECORDS = 'elvita_web_patient_records';

const defaultProfile = {
  name: "Étudiant en médecine",
  studyYear: "DFASM 2 (5e Année)",
  university: "Faculté des Sciences de la Santé",
  hospital: "CHU - Pôle Médecine Interne"
};

let currentProfile = JSON.parse(localStorage.getItem(STORAGE_KEY_PROFILE)) || { ...defaultProfile };
let profilePhotoDataUrl = localStorage.getItem(STORAGE_KEY_PROFILE_PHOTO) || "";

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
let patientRecords = JSON.parse(localStorage.getItem(STORAGE_KEY_PATIENT_RECORDS)) || {};
let activePatientId = 0;
let quizScore = { correct: 0, totalAnswered: 0 };
let answeredQuizIds = new Set();
let oralChecklistChecked = new Set();

// Utilitaires
function parseNum(id, defaultVal = null) {
  const el = document.getElementById(id);
  if (!el) return defaultVal;
  const v = (el.value || '').toString().trim().replace(',', '.');
  if (v === '') return defaultVal;
  const n = parseFloat(v);
  return isNaN(n) ? defaultVal : n;
}

function safeHtml(str) {
  return String(str ?? '')
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#39;');
}

function escapeJsArg(str) {
  return String(str ?? '')
    .replace(/\\/g, '\\\\')
    .replace(/'/g, "\\'")
    .replace(/"/g, '&quot;')
    .replace(/\n/g, ' ');
}

function formatNow() {
  const d = new Date();
  const pad = n => String(n).padStart(2, '0');
  return `${pad(d.getDate())}/${pad(d.getMonth() + 1)}/${d.getFullYear()} ${pad(d.getHours())}:${pad(d.getMinutes())}`;
}

function ensurePatientRecord(pid) {
  const key = String(pid);
  if (!patientRecords[key]) {
    patientRecords[key] = { vitals: [], glasgow: [], labs: [], observations: [] };
  }
  return patientRecords[key];
}

function savePatientRecords() {
  try {
    localStorage.setItem(STORAGE_KEY_PATIENT_RECORDS, JSON.stringify(patientRecords));
  } catch (e) {
    console.warn('Stockage local saturé:', e);
  }
}

// 2. Navigation Principale (5 Onglets) & Sous-Onglets
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
    renderVitalsHistory();
    renderGlasgowHistory();
  } else if (tabId === 'semiology') {
    renderCategoryPills();
    renderSheets();
    renderCourses();
    renderQuiz();
  }
}

function switchSubTab(module, subTabId) {
  const barSelector = `#tab-${module} .module-tab-btn`;
  const panelSelector = `#tab-${module} .subtab-panel`;
  document.querySelectorAll(barSelector).forEach(b => b.classList.remove('active'));
  document.querySelectorAll(panelSelector).forEach(p => { p.style.display = 'none'; });

  const btn = document.getElementById(`tab-btn-${module}-${subTabId}`);
  const panel = document.getElementById(`${module}-sub-${subTabId}`);
  if (btn) btn.classList.add('active');
  if (panel) panel.style.display = 'block';

  if (module === 'vitals') {
    if (subTabId === 'curves') {
      renderVitalsChart();
      renderVitalsHistory();
    } else if (subTabId === 'glasgow') {
      renderGlasgowHistory();
    }
  } else if (module === 'semiology') {
    if (subTabId === 'sheets') renderSheets();
    else if (subTabId === 'courses') renderCourses();
    else if (subTabId === 'quiz') renderQuiz();
  }
}

// 3. Modales
function openModal(modalId) {
  const modal = document.getElementById(modalId);
  if (!modal) return;
  modal.classList.add('active');
  if (modalId === 'modal-patients') renderPatientsList();
  if (modalId === 'modal-clinical-training') renderClinicalTraining('anamnesis');
  if (modalId === 'modal-all-preventions') renderAllPreventionsList();
  if (modalId === 'modal-settings') loadProfileToUI();
}

function closeModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.remove('active');
}

// 4. Profil Étudiant + Photo Locale (Galerie / Caméra)
function loadProfileToUI() {
  const setVal = (id, v) => { const el = document.getElementById(id); if (el) el.value = v || ''; };
  setVal('prof-name', currentProfile.name);
  setVal('prof-year', currentProfile.studyYear);
  setVal('prof-uni', currentProfile.university);
  setVal('prof-hosp', currentProfile.hospital);
  updateProfilePhotoUI();
}

function updateProfilePhotoUI() {
  const preview = document.getElementById('prof-avatar-preview');
  const removeBtn = document.getElementById('prof-photo-remove-btn');
  const headerBtn = document.getElementById('home-profile-btn');

  if (profilePhotoDataUrl) {
    if (preview) preview.innerHTML = `<img src="${profilePhotoDataUrl}" alt="Photo de profil">`;
    if (removeBtn) removeBtn.style.display = 'inline-block';
    if (headerBtn) headerBtn.innerHTML = `<img src="${profilePhotoDataUrl}" alt="Profil" class="header-avatar-img">`;
  } else {
    if (preview) preview.textContent = '👤';
    if (removeBtn) removeBtn.style.display = 'none';
    if (headerBtn) headerBtn.textContent = '👤';
  }
}

function handleProfilePhotoUpload(inputEl) {
  const file = inputEl?.files?.[0];
  if (!file) return;
  const reader = new FileReader();
  reader.onload = (ev) => {
    const img = new Image();
    img.onload = () => {
      const maxSize = 320;
      let w = img.width;
      let h = img.height;
      if (w > h && w > maxSize) { h = Math.round(h * maxSize / w); w = maxSize; }
      else if (h >= w && h > maxSize) { w = Math.round(w * maxSize / h); h = maxSize; }

      const canvas = document.createElement('canvas');
      canvas.width = w;
      canvas.height = h;
      const ctx = canvas.getContext('2d');
      ctx.drawImage(img, 0, 0, w, h);
      const dataUrl = canvas.toDataURL('image/jpeg', 0.82);

      profilePhotoDataUrl = dataUrl;
      try {
        localStorage.setItem(STORAGE_KEY_PROFILE_PHOTO, dataUrl);
      } catch (e) {
        alert("Impossible d'enregistrer cette image en stockage local (taille excessive).");
        return;
      }
      updateProfilePhotoUI();
    };
    img.src = ev.target.result;
  };
  reader.readAsDataURL(file);
  inputEl.value = '';
}

function removeProfilePhoto() {
  profilePhotoDataUrl = "";
  localStorage.removeItem(STORAGE_KEY_PROFILE_PHOTO);
  updateProfilePhotoUI();
}

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

// 5. Section A : Prévention Médicale & Vigilance (23 Fiches)
function getPreventionTopics() {
  return window.ALL_PREVENTION_TOPICS || [];
}

function renderPreventions() {
  const topics = getPreventionTopics();
  const badge = document.getElementById('prevention-count-badge');
  if (badge) badge.textContent = `${topics.length} Fiches Repères`;

  const container = document.getElementById('prevention-carousel-container');
  if (!container) return;

  container.innerHTML = topics.map(p => `
    <div class="prev-card" onclick="openPreventionDetail('${escapeJsArg(p.id)}')">
      <div>
        <div class="prev-top-row">
          <span class="prev-tag">${safeHtml(p.tag)}</span>
          <span style="font-size: 16px;">🏥</span>
        </div>
        <div class="prev-title">${safeHtml(p.title)}</div>
        <div class="prev-sub">${safeHtml(p.subtitle)}</div>
        <div class="prev-desc">${safeHtml(p.definition || (p.keyPoints && p.keyPoints[0]) || '')}</div>
      </div>
      <button class="prev-btn" onclick="event.stopPropagation(); openPreventionDetail('${escapeJsArg(p.id)}')">
        <span>${safeHtml((p.clinicalMessage || '').substring(0, 38))}...</span>
        <span>→</span>
      </button>
    </div>
  `).join('');
}

function openAllPreventionsModal() {
  renderAllPreventionsList();
  openModal('modal-all-preventions');
}

function renderAllPreventionsList() {
  const container = document.getElementById('all-preventions-list');
  if (!container) return;
  const q = (document.getElementById('prevention-search-input')?.value || '').toLowerCase().trim();
  const list = getPreventionTopics().filter(p =>
    !q || p.title.toLowerCase().includes(q) || p.tag.toLowerCase().includes(q) || p.subtitle.toLowerCase().includes(q)
  );

  if (list.length === 0) {
    container.innerHTML = `<div style="text-align:center; padding: 20px; color: var(--text-secondary);">Aucune fiche prévention trouvée.</div>`;
    return;
  }

  container.innerHTML = list.map(p => `
    <div class="card mb-2" style="cursor: pointer;" onclick="closeModal('modal-all-preventions'); openPreventionDetail('${escapeJsArg(p.id)}')">
      <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:4px;">
        <span class="prev-tag">${safeHtml(p.tag)}</span>
        <span style="color:#38BDF8;">›</span>
      </div>
      <div style="font-size: 14.5px; font-weight: 800; color: var(--text-primary);">${safeHtml(p.title)}</div>
      <div style="font-size: 12px; color: var(--text-secondary); margin-top: 2px;">${safeHtml(p.subtitle)}</div>
    </div>
  `).join('');
}

function openPreventionDetail(prevId) {
  const p = getPreventionTopics().find(item => item.id === prevId);
  if (!p) return;

  const listBlock = (title, items, color = '#CBD5E1') => (items && items.length)
    ? `<div class="card mb-2">
         <strong style="font-size: 13px; color: #38BDF8;">${title}</strong>
         <ul style="padding-left: 18px; margin-top: 6px; font-size: 12.5px; line-height: 1.55; color: ${color};">
           ${items.map(it => `<li style="margin-bottom: 4px;">${safeHtml(it)}</li>`).join('')}
         </ul>
       </div>`
    : '';

  const modalBody = document.getElementById('prevention-detail-body');
  modalBody.innerHTML = `
    <span class="prev-tag">${safeHtml(p.tag)}</span>
    <h2 style="font-size: 18px; font-weight: 900; color: #38BDF8; margin-top: 8px; margin-bottom: 4px;">${safeHtml(p.title)}</h2>
    <div style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">${safeHtml(p.subtitle)}</div>

    <div class="card mb-2" style="background: rgba(2, 132, 199, 0.12); border-left: 4px solid #0284C7;">
      <div style="font-weight: 800; color: #38BDF8; margin-bottom: 4px;">Message Clinique Prioritaire :</div>
      <div style="font-size: 13px; color: #F8FAFC; line-height: 1.5;">${safeHtml(p.clinicalMessage)}</div>
    </div>

    ${p.definition ? `<div class="card mb-2"><strong style="font-size:13px; color:#38BDF8;">1. Définition :</strong><p style="font-size:12.5px; margin-top:4px; line-height:1.5;">${safeHtml(p.definition)}</p></div>` : ''}
    ${p.targetPopulation ? `<div class="card mb-2"><strong style="font-size:13px; color:#38BDF8;">2. Population cible :</strong><p style="font-size:12.5px; margin-top:4px; line-height:1.5;">${safeHtml(p.targetPopulation)}</p></div>` : ''}
    ${listBlock('3. Facteurs de risque :', p.riskFactors)}
    ${listBlock('4. Mesures de prévention :', p.preventionMeasures)}
    ${p.screeningRecommendations ? `<div class="card mb-2"><strong style="font-size:13px; color:#38BDF8;">5. Dépistage recommandé :</strong><p style="font-size:12.5px; margin-top:4px; line-height:1.5;">${safeHtml(p.screeningRecommendations)}</p></div>` : ''}
    ${listBlock('6. Conseils pratiques :', p.practicalAdvice)}
    ${listBlock('7. Signes d\'alerte / Gravité :', p.warningSigns, '#FCA5A5')}
    ${listBlock('Points clés à retenir :', p.keyPoints, '#A7F3D0')}
  `;
  openModal('modal-prevention-detail');
}

// 6. Centralisation Dossiers Patients & Synchronisation Automatique
function initPatientsSelect() {
  const selects = [
    { id: 'vitals-patient-select', emptyText: 'Mesure ponctuelle (sans dossier associé)', emptyVal: '0' },
    { id: 'glasgow-patient-select', emptyText: '— Sélectionner un dossier patient —', emptyVal: '0' },
    { id: 'labs-patient-select', emptyText: 'Analyse ponctuelle (sans dossier associé)', emptyVal: '0' },
    { id: 'obs-patient-select', emptyText: 'Saisir un nouveau dossier patient', emptyVal: 'new' }
  ];

  selects.forEach(cfg => {
    const sel = document.getElementById(cfg.id);
    if (!sel) return;
    let html = `<option value="${cfg.emptyVal}">${cfg.emptyText}</option>`;
    patientsList.forEach(p => {
      html += `<option value="${p.id}">${safeHtml(p.name)} (${p.age} ans, ${safeHtml(p.department)})</option>`;
    });
    sel.innerHTML = html;
    if (activePatientId > 0) sel.value = String(activePatientId);
  });
}

function syncActivePatient(val) {
  const pid = parseInt(val, 10) || 0;
  activePatientId = pid;

  ['vitals-patient-select', 'glasgow-patient-select', 'labs-patient-select'].forEach(id => {
    const sel = document.getElementById(id);
    if (sel) sel.value = String(pid);
  });

  const obsSel = document.getElementById('obs-patient-select');
  if (obsSel) obsSel.value = pid > 0 ? String(pid) : 'new';

  const gcsWarn = document.getElementById('glasgow-patient-warning');
  if (gcsWarn && pid > 0) gcsWarn.style.display = 'none';

  renderGlasgowHistory();
  renderVitalsChart();
  renderVitalsHistory();
  if (pid > 0) syncDossierIntoObs(pid);
}

function renderPatientsList() {
  const container = document.getElementById('patients-list-container');
  if (!container) return;

  const query = (document.getElementById('patient-search-input')?.value || '').toLowerCase().trim();
  const filtered = patientsList.filter(p =>
    p.name.toLowerCase().includes(query) ||
    p.department.toLowerCase().includes(query) ||
    (p.condition || '').toLowerCase().includes(query)
  );

  if (filtered.length === 0) {
    container.innerHTML = `<div style="text-align: center; color: var(--text-secondary); padding: 20px;">Aucun dossier trouvé.</div>`;
    return;
  }

  container.innerHTML = filtered.map(p => {
    const rec = ensurePatientRecord(p.id);
    const counts = `${rec.vitals.length} const. • ${rec.glasgow.length} GCS • ${rec.labs.length} bilans • ${rec.observations.length} obs.`;
    return `
      <div class="card mb-2">
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <strong>${safeHtml(p.name)}</strong>
          <span class="prev-tag" style="font-size: 10px;">${safeHtml(p.department)}</span>
        </div>
        <div style="font-size: 12px; color: var(--text-secondary); margin-top: 4px;">
          ${p.age} ans • ${safeHtml(p.gender)} • ${safeHtml(p.room)}
        </div>
        <div style="font-size: 12px; color: #38BDF8; margin-top: 2px;">${safeHtml(p.condition)}</div>
        <div style="font-size: 11px; color: #94A3B8; margin-top: 4px;">${counts}</div>
        <div style="display: flex; gap: 8px; margin-top: 10px;">
          <button type="button" class="btn-secondary-sm" style="flex:1;" onclick="openPatientDossier(${p.id})">📂 Voir Dossier</button>
          <button type="button" class="btn-secondary-sm" style="flex:1; background: rgba(2,132,199,0.2); border-color:#0284C7; color:#38BDF8;" onclick="selectPatientForVisit(${p.id})">✓ Sélectionner</button>
        </div>
      </div>
    `;
  }).join('');
}

function openPatientDossier(patientId) {
  const p = patientsList.find(item => item.id === patientId);
  if (!p) return;
  const rec = ensurePatientRecord(p.id);

  const renderSection = (title, arr, formatter) => `
    <div class="card mb-2">
      <div style="font-size: 13.5px; font-weight: 800; color: #38BDF8; margin-bottom: 6px;">${title} (${arr.length})</div>
      ${arr.length === 0 ? `<div style="font-size:12px; color:var(--text-secondary);">Aucune entrée enregistrée pour ce patient.</div>` : arr.map(formatter).join('')}
    </div>
  `;

  document.getElementById('patient-dossier-body').innerHTML = `
    <span class="prev-tag">${safeHtml(p.department)}</span>
    <h2 style="font-size: 18px; font-weight: 900; color: #F8FAFC; margin-top: 8px;">${safeHtml(p.name)}</h2>
    <div style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
      ${p.age} ans • ${safeHtml(p.gender)} • ${safeHtml(p.room)}<br>
      <strong style="color:#38BDF8;">Motif :</strong> ${safeHtml(p.condition)}
    </div>

    ${renderSection('📈 Constantes Vitales', rec.vitals, v => `
      <div style="font-size:12px; padding:6px 0; border-bottom:1px solid rgba(255,255,255,0.06);">
        <strong>${safeHtml(v.formattedDateTime)}</strong> — T° ${v.temp}°C, PA ${v.pas}/${v.pad} mmHg, FC ${v.fc} bpm, FR ${v.fr}/min, SpO2 ${v.spo2}%
      </div>
    `)}

    ${renderSection('🧠 Scores de Glasgow (GCS)', rec.glasgow, g => `
      <div style="font-size:12px; padding:6px 0; border-bottom:1px solid rgba(255,255,255,0.06);">
        <strong>${safeHtml(g.formattedDateTime)}</strong> — <strong>${g.total}/15</strong> (E${g.eye} V${g.verbal} M${g.motor}) : ${safeHtml(g.summary)}
      </div>
    `)}

    ${renderSection('🧪 Examens & Conclusions Biologiques', rec.labs, l => `
      <div style="font-size:12px; padding:6px 0; border-bottom:1px solid rgba(255,255,255,0.06);">
        <strong>[${safeHtml(l.testType)}] ${safeHtml(l.formattedDateTime)}</strong><br>
        <span>${safeHtml(l.conclusion || l.summary)}</span>
      </div>
    `)}

    ${renderSection('📋 Observations & Mises à jour', rec.observations, o => `
      <div style="font-size:12px; padding:6px 0; border-bottom:1px solid rgba(255,255,255,0.06);">
        <strong>[${safeHtml(o.type)}] ${safeHtml(o.formattedDateTime)}</strong><br>
        <span style="color:var(--text-secondary);">${safeHtml(o.summary)}</span>
      </div>
    `)}

    <button class="btn-primary" onclick="closeModal('modal-patient-dossier'); selectPatientForVisit(${p.id});">Ouvrir dans Clinique</button>
  `;

  closeModal('modal-patients');
  openModal('modal-patient-dossier');
}

function selectPatientForVisit(patientId) {
  const p = patientsList.find(item => item.id === patientId);
  if (!p) return;

  closeModal('modal-patients');
  syncActivePatient(p.id);

  const setVal = (id, v) => { const el = document.getElementById(id); if (el) el.value = v; };
  setVal('obs-patient-name', p.name);
  setVal('obs-age', p.age);
  setVal('obs-gender', p.gender);
  setVal('obs-dept', p.department);
  setVal('obs-room', p.room);

  syncDossierIntoObs(p.id);
  switchTab('clinical');
}

function syncDossierIntoObs(patientId) {
  const p = patientsList.find(item => item.id === patientId);
  const banner = document.getElementById('obs-patient-sync-banner');
  if (!p) {
    if (banner) banner.style.display = 'none';
    return;
  }

  const rec = ensurePatientRecord(p.id);
  const lastV = rec.vitals[0];
  const lastG = rec.glasgow[0];
  const lastLab = rec.labs[0];

  const vitalsLine = lastV
    ? `Constantes (${lastV.formattedDateTime}) : T° ${lastV.temp}°C, PA ${lastV.pas}/${lastV.pad} mmHg (PAM ${lastV.pam}), FC ${lastV.fc} bpm, FR ${lastV.fr}/min, SpO2 ${lastV.spo2}%, Glycémie ${lastV.gly} g/L.`
    : '';
  const gcsLine = lastG ? `Conscience (${lastG.formattedDateTime}) : Score de Glasgow ${lastG.total}/15 (E${lastG.eye} V${lastG.verbal} M${lastG.motor}).` : '';
  const labsLine = rec.labs.slice(0, 3).map(l => `• [${l.testType} ${l.formattedDateTime}] ${l.conclusion || l.summary}`).join('\n');

  if (banner) {
    banner.innerHTML = `
      <strong>🔄 Dossier synchronisé : ${safeHtml(p.name)}</strong><br>
      <span>• Motif : ${safeHtml(p.condition)}</span><br>
      ${vitalsLine ? `<span>• ${safeHtml(vitalsLine)}</span><br>` : ''}
      ${gcsLine ? `<span>• ${safeHtml(gcsLine)}</span><br>` : ''}
      ${lastLab ? `<span>• Dernier bilan (${safeHtml(lastLab.testType)}) : ${safeHtml(lastLab.conclusion || lastLab.summary)}</span>` : '<span>• Aucun résultat biologique enregistré pour ce dossier.</span>'}
    `;
    banner.style.display = 'block';
  }

  // Remplissage non destructif des champs de l'observation
  const motifEl = document.getElementById('obs-motif');
  if (motifEl && (!motifEl.dataset.edited || motifEl.value.trim() === '')) {
    motifEl.value = p.condition || motifEl.value;
  }
  const genEl = document.getElementById('obs-general');
  if (genEl && (!genEl.dataset.edited || genEl.value.trim() === '')) {
    genEl.value = [vitalsLine, gcsLine].filter(Boolean).join('\n');
  }
  const paraEl = document.getElementById('obs-paraclinical');
  if (paraEl && (!paraEl.dataset.edited || paraEl.value.trim() === '')) {
    paraEl.value = labsLine;
  }
}

function loadSelectedPatientIntoObs(val) {
  if (val === 'new') {
    activePatientId = 0;
    const banner = document.getElementById('obs-patient-sync-banner');
    if (banner) banner.style.display = 'none';
    document.getElementById('obs-patient-name').value = "";
    document.getElementById('obs-age').value = "45";
    document.getElementById('obs-dept').value = "Médecine Interne";
    document.getElementById('obs-room').value = "Chambre 101";
    const g = document.getElementById('obs-general'); if (g) g.value = "";
    const pa = document.getElementById('obs-paraclinical'); if (pa) pa.value = "";
  } else {
    selectPatientForVisit(parseInt(val, 10));
  }
}

function saveNewPatient() {
  const name = document.getElementById('new-patient-name').value.trim();
  const age = parseInt(document.getElementById('new-patient-age').value, 10) || 50;
  const gender = document.getElementById('new-patient-gender').value;
  const dept = document.getElementById('new-patient-dept').value.trim() || "Médecine Interne";
  const room = document.getElementById('new-patient-room').value.trim() || "Chambre";
  const cond = document.getElementById('new-patient-cond').value.trim() || "Hospitalisation";

  if (!name) {
    alert("Veuillez saisir un nom ou un alias de patient.");
    return;
  }

  const newPatient = { id: Date.now(), name, age, gender, department: dept, room, condition: cond };
  patientsList.unshift(newPatient);
  localStorage.setItem(STORAGE_KEY_PATIENTS, JSON.stringify(patientsList));
  ensurePatientRecord(newPatient.id);
  savePatientRecords();

  initPatientsSelect();
  closeModal('modal-add-patient');
  selectPatientForVisit(newPatient.id);
  alert(`Dossier patient créé avec succès pour ${name} !`);
}

// 7. MOTEUR MESURES : Constantes Vitales & Score de Glasgow (GCS)
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

  const pam = ((pas + 2 * pad) / 3).toFixed(1);
  const imc = height > 0 ? (weight / Math.pow(height / 100, 2)).toFixed(1) : '22.0';

  let alertLevel = 'NORMAL';
  let badgeText = 'Paramètres Stables et Physiologiques';
  const points = [];

  if (temp >= 38.5) points.push({ name: 'Température', obs: `${temp}°C — Syndrome fébrile franc`, level: temp >= 39.5 ? 'CRITICAL' : 'MODERATE' });
  else if (temp < 35.5) points.push({ name: 'Température', obs: `${temp}°C — Hypothermie (risque de sepsis ou choc)`, level: 'CRITICAL' });

  if (pas < 90 || parseFloat(pam) < 65) points.push({ name: 'Hémodynamique', obs: `PAS ${pas} / PAM ${pam} mmHg — Hypotension / Risque de choc`, level: 'CRITICAL' });
  else if (pas >= 180 || pad >= 110) points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Crise hypertensive sévère`, level: 'CRITICAL' });
  else if (pas >= 140 || pad >= 90) points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Hypertension artérielle`, level: 'MODERATE' });

  if (spo2 < 90) points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Hypoxémie sévère (O2 urgent)`, level: 'CRITICAL' });
  else if (spo2 <= 94) points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Désaturation modérée`, level: 'MODERATE' });

  if (fr > 24) points.push({ name: 'Respiration', obs: `FR ${fr}/min — Polypnée / tachypnée`, level: fr >= 30 ? 'CRITICAL' : 'MODERATE' });
  else if (fr < 10) points.push({ name: 'Respiration', obs: `FR ${fr}/min — Bradypnée critique`, level: 'CRITICAL' });

  if (fc >= 120) points.push({ name: 'Fréquence cardiaque', obs: `FC ${fc} bpm — Tachycardie marquée`, level: fc >= 130 ? 'CRITICAL' : 'MODERATE' });
  else if (fc < 50) points.push({ name: 'Fréquence cardiaque', obs: `FC ${fc} bpm — Bradycardie sévère`, level: 'CRITICAL' });

  if (gly < 0.70) points.push({ name: 'Glycémie', obs: `${gly} g/L — Hypoglycémie (resucrage immédiat)`, level: 'CRITICAL' });
  else if (gly > 2.0) points.push({ name: 'Glycémie', obs: `${gly} g/L — Hyperglycémie franche`, level: 'MODERATE' });

  if (points.some(p => p.level === 'CRITICAL')) {
    alertLevel = 'CRITICAL';
    badgeText = 'URGENCE CLINIQUE : Anomalies Vitales Critiques';
  } else if (points.some(p => p.level === 'MODERATE')) {
    alertLevel = 'MODERATE';
    badgeText = 'Vigilance Clinique : Paramètres Perturbés';
  }

  const pid = parseInt(document.getElementById('vitals-patient-select')?.value, 10) || 0;
  let savedNote = '';
  if (pid > 0) {
    const rec = ensurePatientRecord(pid);
    rec.vitals.unshift({
      timestamp: Date.now(),
      formattedDateTime: formatNow(),
      temp, pas, pad, fc, fr, spo2, gly, weight, height, pam, imc, alertLevel,
      summary: badgeText
    });
    savePatientRecords();
    const pObj = patientsList.find(x => x.id === pid);
    savedNote = `<div style="margin-top:8px; font-size:12px; color:#34D399; font-weight:700;">✓ Mesure enregistrée dans le dossier de ${safeHtml(pObj?.name || 'Patient')}.</div>`;
    renderVitalsChart();
    renderVitalsHistory();
    syncDossierIntoObs(pid);
  }

  const resultContainer = document.getElementById('vitals-result');
  resultContainer.innerHTML = `
    <div class="result-card" style="border-left: 4px solid ${alertLevel === 'CRITICAL' ? 'var(--alert-red)' : alertLevel === 'MODERATE' ? 'var(--alert-orange)' : 'var(--alert-green)'};">
      <div style="font-size: 15px; font-weight: 800; color: ${alertLevel === 'CRITICAL' ? '#EF4444' : alertLevel === 'MODERATE' ? '#F59E0B' : '#10B981'}; margin-bottom: 8px;">
        ● ${badgeText}
      </div>
      <div class="form-row mb-2" style="background: rgba(255,255,255,0.04); padding: 8px; border-radius: 8px;">
        <div><strong>PAM :</strong> ${pam} mmHg</div>
        <div><strong>IMC :</strong> ${imc} kg/m²</div>
      </div>
      <h4 style="font-size: 13.5px; font-weight: 800; margin-top: 10px; margin-bottom: 6px; color: #38BDF8;">Interprétation Systématique :</h4>
      ${points.length > 0 ? points.map(p => `
        <div style="font-size: 12.5px; margin-bottom: 5px; line-height: 1.5; color: ${p.level === 'CRITICAL' ? '#FCA5A5' : '#FDE68A'};">
          • <strong>${p.name} :</strong> ${p.obs}
        </div>
      `).join('') : '<div style="font-size: 12.5px; color: #A7F3D0;">Tous les paramètres vitaux mesurés sont physiologiques et dans les normes.</div>'}
      ${savedNote}
    </div>
  `;
  resultContainer.style.display = 'block';
  resultContainer.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

function calculateAndSaveGlasgow() {
  const pid = parseInt(document.getElementById('glasgow-patient-select')?.value, 10) || 0;
  const warnBox = document.getElementById('glasgow-patient-warning');
  if (pid <= 0) {
    if (warnBox) {
      warnBox.style.display = 'block';
      warnBox.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
    }
    return;
  }
  if (warnBox) warnBox.style.display = 'none';

  const e = parseInt(document.getElementById('gcs-e')?.value, 10) || 4;
  const v = parseInt(document.getElementById('gcs-v')?.value, 10) || 5;
  const m = parseInt(document.getElementById('gcs-m')?.value, 10) || 6;
  const total = e + v + m;

  let alertLevel = 'NORMAL';
  let severity = "Score GCS 15/15 — Conscience Parfaitement Normale";
  let color = "#10B981";
  let alertDetails = "Patient vigilant, parfaitement orienté dans le temps et l'espace, répondant aux ordres simples.";

  if (total <= 8) {
    alertLevel = 'CRITICAL';
    severity = `Score GCS ${total}/15 — COMA GRAVE (DÉTRESSE NEUROLOGIQUE)`;
    color = "#DC2626";
    alertDetails = "🚨 DANGER VITAL IMMÉDIAT (GCS ≤ 8). Perte des réflexes de protection des voies aériennes : indication formelle d'intubation orotrachéale en séquence rapide.";
  } else if (total <= 12) {
    alertLevel = 'MODERATE';
    severity = `Score GCS ${total}/15 — Altération Modérée de la Conscience`;
    color = "#F97316";
    alertDetails = "Somnolence pathologique / coma léger. Surveillance neurologique horaire rapprochée et TDM cérébral recommandé.";
  } else if (total < 15) {
    alertLevel = 'MODERATE';
    severity = `Score GCS ${total}/15 — Altération Légère / Confusion`;
    color = "#F59E0B";
    alertDetails = "Altération discrète de la vigilance. Rechercher une cause métabolique (hypoglycémie, hypoxie), toxique ou neurologique.";
  }

  const rec = ensurePatientRecord(pid);
  rec.glasgow.unshift({
    timestamp: Date.now(),
    formattedDateTime: formatNow(),
    eye: e, verbal: v, motor: m, total, alertLevel,
    summary: severity
  });
  savePatientRecords();
  renderGlasgowHistory();
  syncDossierIntoObs(pid);

  const pObj = patientsList.find(x => x.id === pid);
  const res = document.getElementById('gcs-result');
  res.innerHTML = `
    <div class="result-card" style="border-left: 4px solid ${color};">
      <div style="font-size: 13px; color: var(--text-secondary); margin-bottom: 2px;">Détail : E${e} V${v} M${m}</div>
      <div style="font-size: 24px; font-weight: 900; color: #38BDF8; margin-bottom: 4px;">GCS Total : ${total} / 15</div>
      <div style="font-size: 14.5px; font-weight: 800; color: ${color}; margin-bottom: 8px;">● ${severity}</div>
      <div style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5;">${alertDetails}</div>
      <div style="font-size: 11.5px; color: #94A3B8; margin-top: 8px;">Rappel : Toujours écarter hypoglycémie, hypoxie (SpO2) et état de choc devant toute baisse du GCS.</div>
      <div style="margin-top: 8px; font-size: 12px; color: #34D399; font-weight: 700;">✓ Score enregistré dans le dossier de ${safeHtml(pObj?.name || 'Patient')}.</div>
    </div>
  `;
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

function renderGlasgowHistory() {
  const listEl = document.getElementById('glasgow-history-list');
  if (!listEl) return;
  const pid = parseInt(document.getElementById('glasgow-patient-select')?.value, 10) || activePatientId || 0;
  if (pid <= 0) {
    listEl.innerHTML = 'Sélectionnez un dossier patient pour consulter ses scores de Glasgow enregistrés.';
    return;
  }
  const rec = ensurePatientRecord(pid);
  if (rec.glasgow.length === 0) {
    listEl.innerHTML = 'Aucun score de Glasgow enregistré pour ce patient.';
    return;
  }
  listEl.innerHTML = rec.glasgow.map(g => `
    <div style="padding: 8px 0; border-bottom: 1px solid rgba(255,255,255,0.06);">
      <div style="display:flex; justify-content:space-between;">
        <strong style="color:#38BDF8;">${g.total}/15 (E${g.eye} V${g.verbal} M${g.motor})</strong>
        <span style="font-size:11px; color:var(--text-secondary);">${safeHtml(g.formattedDateTime)}</span>
      </div>
      <div style="font-size:12px; color:#CBD5E1; margin-top:2px;">${safeHtml(g.summary)}</div>
    </div>
  `).join('');
}

function renderVitalsHistory() {
  const listEl = document.getElementById('vitals-history-list');
  if (!listEl) return;
  const pid = parseInt(document.getElementById('vitals-patient-select')?.value, 10) || activePatientId || 0;
  if (pid <= 0) {
    listEl.innerHTML = 'Sélectionnez un dossier patient pour afficher son historique de constantes.';
    return;
  }
  const rec = ensurePatientRecord(pid);
  if (rec.vitals.length === 0) {
    listEl.innerHTML = 'Aucune mesure enregistrée pour ce dossier.';
    return;
  }
  listEl.innerHTML = rec.vitals.map(v => `
    <div style="padding: 8px 0; border-bottom: 1px solid rgba(255,255,255,0.06);">
      <div style="display:flex; justify-content:space-between;">
        <strong style="color:#F8FAFC;">${safeHtml(v.formattedDateTime)}</strong>
        <span class="prev-tag">${safeHtml(v.alertLevel)}</span>
      </div>
      <div style="font-size:12px; color:#CBD5E1; margin-top:3px;">
        T° ${v.temp}°C • PA ${v.pas}/${v.pad} mmHg (PAM ${v.pam}) • FC ${v.fc} bpm • FR ${v.fr}/min • SpO2 ${v.spo2}% • Gly ${v.gly} g/L
      </div>
    </div>
  `).join('');
}

function renderVitalsChart() {
  const container = document.getElementById('vitals-chart-container');
  if (!container) return;

  const pid = parseInt(document.getElementById('vitals-patient-select')?.value, 10) || activePatientId || 0;
  const rec = pid > 0 ? ensurePatientRecord(pid) : null;
  const series = rec && rec.vitals.length >= 2 ? rec.vitals.slice(0, 6).reverse() : null;

  if (!series) {
    container.innerHTML = `
      <svg viewBox="0 0 400 180" style="width: 100%; height: auto; background: #0E1626; border-radius: 12px; padding: 10px;">
        <line x1="40" y1="30" x2="380" y2="30" stroke="#23354D" stroke-dasharray="4"/>
        <line x1="40" y1="70" x2="380" y2="70" stroke="#23354D" stroke-dasharray="4"/>
        <line x1="40" y1="110" x2="380" y2="110" stroke="#23354D" stroke-dasharray="4"/>
        <line x1="40" y1="150" x2="380" y2="150" stroke="#23354D"/>
        <text x="5" y="35" fill="#64748B" font-size="10">39.5°</text>
        <text x="5" y="75" fill="#64748B" font-size="10">38.5°</text>
        <text x="5" y="115" fill="#64748B" font-size="10">37.5°</text>
        <text x="5" y="155" fill="#64748B" font-size="10">36.5°</text>
        <polyline fill="none" stroke="#EF4444" stroke-width="3" points="50,38 120,55 190,82 260,105 330,118"/>
        <polyline fill="none" stroke="#2DD4BF" stroke-width="2.5" stroke-dasharray="2" points="50,140 120,125 190,95 260,70 330,60"/>
        <text x="55" y="172" fill="#EF4444" font-size="10" font-weight="bold">━ Température (°C)</text>
        <text x="200" y="172" fill="#2DD4BF" font-size="10" font-weight="bold">┅ SpO2 (%)</text>
      </svg>
      <div style="font-size: 11.5px; color: var(--text-secondary); text-align: center; margin-top: 6px;">
        Courbe pédagogique de référence (enregistrez ≥ 2 mesures sur un dossier patient pour tracer son évolution réelle)
      </div>
    `;
    return;
  }

  const ptsTemp = series.map((v, idx) => {
    const x = 50 + idx * (290 / (series.length - 1));
    const y = Math.max(20, Math.min(150, 150 - ((v.temp - 36.0) * 30)));
    return `${x.toFixed(0)},${y.toFixed(0)}`;
  }).join(' ');

  const ptsSpo2 = series.map((v, idx) => {
    const x = 50 + idx * (290 / (series.length - 1));
    const y = Math.max(20, Math.min(150, 150 - ((v.spo2 - 80) * 6)));
    return `${x.toFixed(0)},${y.toFixed(0)}`;
  }).join(' ');

  container.innerHTML = `
    <svg viewBox="0 0 400 180" style="width: 100%; height: auto; background: #0E1626; border-radius: 12px; padding: 10px;">
      <line x1="40" y1="30" x2="380" y2="30" stroke="#23354D" stroke-dasharray="4"/>
      <line x1="40" y1="90" x2="380" y2="90" stroke="#23354D" stroke-dasharray="4"/>
      <line x1="40" y1="150" x2="380" y2="150" stroke="#23354D"/>
      <polyline fill="none" stroke="#EF4444" stroke-width="3" points="${ptsTemp}"/>
      <polyline fill="none" stroke="#2DD4BF" stroke-width="2.5" stroke-dasharray="3" points="${ptsSpo2}"/>
      <text x="55" y="172" fill="#EF4444" font-size="10" font-weight="bold">━ Température (°C)</text>
      <text x="200" y="172" fill="#2DD4BF" font-size="10" font-weight="bold">┅ SpO2 (%)</text>
    </svg>
    <div style="font-size: 11.5px; color: var(--text-secondary); text-align: center; margin-top: 6px;">
      Évolution réelle sur ${series.length} mesures enregistrées pour ce patient
    </div>
  `;
}

// 8. MOTEUR ANALYSES BIOLOGIQUES (5 Bilans complets & Conclusions structurées)
function saveLabToPatient(testType, summary, conclusion, alertLevel) {
  const pid = parseInt(document.getElementById('labs-patient-select')?.value, 10) || activePatientId || 0;
  if (pid <= 0) return '';
  const rec = ensurePatientRecord(pid);
  rec.labs.unshift({
    timestamp: Date.now(),
    formattedDateTime: formatNow(),
    testType,
    summary,
    conclusion,
    alertLevel
  });
  savePatientRecords();
  syncDossierIntoObs(pid);
  const pObj = patientsList.find(x => x.id === pid);
  return `<div style="margin-top:10px; font-size:12px; color:#34D399; font-weight:700;">✓ Bilan (${safeHtml(testType)}) enregistré dans le dossier de ${safeHtml(pObj?.name || 'Patient')}.</div>`;
}

function renderStructuredLabCard({ title, alertLevel, conclusion, normalParams = [], abnormalParams = [], associations = [], orientations = [], mechanisms = [], caveat = '', extraTopHtml = '', savedMsg = '' }) {
  const borderColor = alertLevel === 'CRITICAL' ? 'var(--alert-red)' : alertLevel === 'MODERATE' ? 'var(--alert-orange)' : '#0284C7';
  return `
    <div class="result-card" style="border-left: 4px solid ${borderColor};">
      <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:8px;">
        <h3 style="font-size:15px; font-weight:900; color:#38BDF8;">${safeHtml(title)}</h3>
        <span class="prev-tag">${safeHtml(alertLevel)}</span>
      </div>

      <div style="background: rgba(2,132,199,0.12); padding: 10px; border-radius: 8px; margin-bottom: 10px;">
        <div style="font-size: 12px; font-weight: 800; color: #38BDF8; margin-bottom: 4px;">Conclusion Médicale Générale :</div>
        <div style="font-size: 13px; font-weight: 700; color: #F8FAFC; line-height: 1.55;">${safeHtml(conclusion)}</div>
      </div>

      ${extraTopHtml}

      ${abnormalParams.length ? `
        <div style="margin-bottom:8px;">
          <strong style="font-size:12.5px; color:#FCA5A5;">⚠️ Paramètres Perturbés :</strong>
          <ul style="padding-left:16px; margin-top:4px; font-size:12px; color:#FECACA; line-height:1.5;">
            ${abnormalParams.map(x => `<li>${safeHtml(x)}</li>`).join('')}
          </ul>
        </div>
      ` : ''}

      ${normalParams.length ? `
        <div style="margin-bottom:8px;">
          <strong style="font-size:12.5px; color:#34D399;">✓ Paramètres dans les Normes :</strong>
          <ul style="padding-left:16px; margin-top:4px; font-size:12px; color:#A7F3D0; line-height:1.5;">
            ${normalParams.map(x => `<li>${safeHtml(x)}</li>`).join('')}
          </ul>
        </div>
      ` : ''}

      ${associations.length ? `
        <div style="margin-bottom:8px;">
          <strong style="font-size:12.5px; color:#FBBF24;">🔗 Associations Biologiques :</strong>
          <ul style="padding-left:16px; margin-top:4px; font-size:12px; color:#FDE68A; line-height:1.5;">
            ${associations.map(x => `<li>${safeHtml(x)}</li>`).join('')}
          </ul>
        </div>
      ` : ''}

      ${orientations.length ? `
        <div style="margin-bottom:8px;">
          <strong style="font-size:12.5px; color:#38BDF8;">🧭 Orientations Diagnostiques & CAT :</strong>
          <ul style="padding-left:16px; margin-top:4px; font-size:12px; color:#CBD5E1; line-height:1.5;">
            ${orientations.map(x => `<li>${safeHtml(x)}</li>`).join('')}
          </ul>
        </div>
      ` : ''}

      ${mechanisms.length ? `
        <details style="margin-top:8px; background:rgba(255,255,255,0.03); padding:8px 10px; border-radius:8px;">
          <summary style="cursor:pointer; font-size:12.5px; font-weight:700; color:#BAE6FD;">📚 Mécanismes Physiopathologiques Pédagogiques</summary>
          <div style="margin-top:6px; font-size:12px; color:#CBD5E1; line-height:1.5;">
            ${mechanisms.map(m => `<div style="margin-bottom:4px;"><strong>${safeHtml(m[0])} :</strong> ${safeHtml(m[1])}</div>`).join('')}
          </div>
        </details>
      ` : ''}

      ${caveat ? `<div style="margin-top:10px; font-size:11.5px; color:#94A3B8; font-style:italic; line-height:1.45;">${safeHtml(caveat)}</div>` : ''}
      ${savedMsg}
    </div>
  `;
}

// 8A. NFS Complète (6 volets + tous cas limites)
function calculateNFS() {
  const hb = parseNum('nfs-hb');
  const ht = parseNum('nfs-ht');
  const gr = parseNum('nfs-gr');
  const retic = parseNum('nfs-retic');
  const gb = parseNum('nfs-gb');
  const pnn = parseNum('nfs-pnn');
  const lympho = parseNum('nfs-lympho');
  const mono = parseNum('nfs-mono');
  const eosino = parseNum('nfs-eosino');
  const baso = parseNum('nfs-baso');
  const plt = parseNum('nfs-plt');
  const vpm = parseNum('nfs-vpm');

  let alert = 'NORMAL';
  const raise = s => { if (s === 'CRITICAL') alert = 'CRITICAL'; else if (s === 'MODERATE' && alert !== 'CRITICAL') alert = 'MODERATE'; };

  const normalParams = [];
  const abnormalParams = [];
  const associations = [];
  const orientations = [];

  const vgm = (gr && ht && gr > 0) ? (ht * 10) / gr : null;
  const ccmh = (hb && ht && ht > 0) ? (hb * 100) / ht : null;

  const isAnemia = hb !== null && hb < 12.0;
  const isPolyglobulia = (hb !== null && hb > 17.5) || (ht !== null && ht > 54.0);
  const isMicrocytic = vgm !== null && vgm < 80.0;
  const isMacrocytic = vgm !== null && vgm > 100.0;
  const isHypochromic = ccmh !== null && ccmh < 32.0;

  const isLeukopenia = gb !== null && gb < 4.0;
  const isHyperleukocytosis = gb !== null && gb > 10.0;
  const isAgranulocytosis = pnn !== null && pnn < 0.5;
  const isNeutropenia = pnn !== null && pnn < 1.5;
  const isNeutrophilia = pnn !== null && pnn > 7.5;
  const isLymphocytosis = lympho !== null && lympho > 4.0;
  const isLymphopenia = lympho !== null && lympho < 1.0;
  const isEosinophilia = eosino !== null && eosino > 0.5;
  const isMonocytosis = mono !== null && mono > 1.0;

  const isThrombopenia = plt !== null && plt < 150.0;
  const isSevereThrombopenia = plt !== null && plt < 50.0;
  const isThrombocytosis = plt !== null && plt > 400.0;

  if (hb !== null) {
    if (hb < 8.0) { raise('CRITICAL'); abnormalParams.push(`Hémoglobine : ${hb} g/dL [ANÉMIE PROFONDE SÉVÈRE]`); }
    else if (isAnemia) { raise('MODERATE'); abnormalParams.push(`Hémoglobine : ${hb} g/dL [ANÉMIE]`); }
    else if (isPolyglobulia) { raise('MODERATE'); abnormalParams.push(`Hémoglobine : ${hb} g/dL [POLYGLOBULIE]`); }
    else normalParams.push(`Hémoglobine : ${hb} g/dL (norme 12 - 17)`);
  }
  if (vgm !== null) {
    if (isMicrocytic) { raise('MODERATE'); abnormalParams.push(`VGM : ${vgm.toFixed(1)} fL [MICROCYTOSE]`); }
    else if (isMacrocytic) { raise('MODERATE'); abnormalParams.push(`VGM : ${vgm.toFixed(1)} fL [MACROCYTOSE]`); }
    else normalParams.push(`VGM : ${vgm.toFixed(1)} fL (norme 80 - 100)`);
  }
  if (gb !== null) {
    if (isHyperleukocytosis) { raise(gb > 30 ? 'CRITICAL' : 'MODERATE'); abnormalParams.push(`Leucocytes (GB) : ${gb} G/L [HYPERLEUCOCYTOSE]`); }
    else if (isLeukopenia) { raise('MODERATE'); abnormalParams.push(`Leucocytes (GB) : ${gb} G/L [LEUCOPÉNIE]`); }
    else normalParams.push(`Leucocytes (GB) : ${gb} G/L (norme 4 - 10)`);
  }
  if (pnn !== null) {
    if (isAgranulocytosis) { raise('CRITICAL'); abnormalParams.push(`PNN : ${pnn} G/L [AGRANULOCYTOSE CRITIQUE < 0.5]`); }
    else if (isNeutropenia) { raise('MODERATE'); abnormalParams.push(`PNN : ${pnn} G/L [NEUTROPÉNIE]`); }
    else if (isNeutrophilia) { raise('MODERATE'); abnormalParams.push(`PNN : ${pnn} G/L [POLYNUCLÉOSE NEUTROPHILE]`); }
    else normalParams.push(`PNN : ${pnn} G/L (norme 1.5 - 7.5)`);
  }
  if (plt !== null) {
    if (isSevereThrombopenia) { raise('CRITICAL'); abnormalParams.push(`Plaquettes : ${plt} G/L [THROMBOPÉNIE SÉVÈRE < 50]`); }
    else if (isThrombopenia) { raise('MODERATE'); abnormalParams.push(`Plaquettes : ${plt} G/L [THROMBOPÉNIE]`); }
    else if (isThrombocytosis) { raise('MODERATE'); abnormalParams.push(`Plaquettes : ${plt} G/L [THROMBOCYTOSE]`); }
    else normalParams.push(`Plaquettes : ${plt} G/L (norme 150 - 400)`);
  }

  const isRedCytopenia = isAnemia;
  const isWhiteCytopenia = isLeukopenia || isNeutropenia || isAgranulocytosis;
  const isPlateletCytopenia = isThrombopenia;
  const cytopenias = (isRedCytopenia ? 1 : 0) + (isWhiteCytopenia ? 1 : 0) + (isPlateletCytopenia ? 1 : 0);

  const isRedNormal = !isAnemia && !isPolyglobulia;
  const isWhiteNormal = !isLeukopenia && !isHyperleukocytosis && !isNeutropenia && !isAgranulocytosis && !isNeutrophilia && !isLymphocytosis && !isLymphopenia && !isEosinophilia && !isMonocytosis;
  const isPlateletNormal = !isThrombopenia && !isThrombocytosis;

  const qualifAnemie = `${isMicrocytic ? 'microcytaire' : isMacrocytic ? 'macrocytaire' : 'normocytaire'} ${isHypochromic ? 'hypochrome' : 'normochrome'}`.trim();

  if (cytopenias >= 3) raise('CRITICAL');
  else if (cytopenias === 2) raise('CRITICAL');

  let conclusion = "";
  if (cytopenias >= 3) {
    conclusion = `La NFS a objectivé une pancytopénie associant une anémie ${qualifAnemie}, une leucopénie (${gb ?? '-'} G/L) et une thrombopénie (${plt ?? '-'} G/L).`;
    associations.push("Pancytopénie des 3 lignées sanguines — Urgence hématologique (myélogramme / BOM).");
    orientations.push("Évoquer origine centrale (aplasie, leucémie aiguë, SMD, carence B12 profonde) ou périphérique (SAM, hypersplénisme).");
  } else if (cytopenias === 2) {
    if (isRedCytopenia && isPlateletCytopenia) {
      const third = isHyperleukocytosis ? `associée à une hyperleucocytose (${gb} G/L) — sans baisse de la lignée blanche` : "avec conservation de la lignée blanche";
      conclusion = `La NFS a objectivé une bicytopénie associant une anémie ${qualifAnemie} et une thrombopénie (${plt ?? '-'} G/L), ${third}.`;
    } else if (isRedCytopenia && isWhiteCytopenia) {
      conclusion = `La NFS a objectivé une bicytopénie associant une anémie ${qualifAnemie} et une leucopénie (${gb ?? '-'} G/L), avec conservation plaquettaire.`;
    } else {
      conclusion = `La NFS a objectivé une bicytopénie associant une leucopénie (${gb ?? '-'} G/L) et une thrombopénie (${plt ?? '-'} G/L), avec conservation de la lignée rouge.`;
    }
    associations.push("Bicytopénie : atteinte conjointe de 2 lignées hématopoïétiques.");
    orientations.push("Avis hématologique, frottis sanguin et discussion de myélogramme.");
  } else if (isAnemia && isMicrocytic && isThrombocytosis) {
    conclusion = `La NFS a objectivé une anémie microcytaire ${isHypochromic ? 'hypochrome' : 'normochrome'} associée à une thrombocytose réactionnelle (${plt} G/L), avec conservation de la lignée blanche.`;
    associations.push("Anémie microcytaire + thrombocytose réactionnelle : profil typique de carence martiale ou syndrome inflammatoire.");
    orientations.push("Doser Ferritine + CRP et rechercher un saignement occulte (digestif/gynécologique).");
  } else if (isHyperleukocytosis && isRedNormal && isPlateletNormal) {
    const pred = isNeutrophilia ? ' à prédominance neutrophile' : isLymphocytosis ? ' à prédominance lymphocytaire' : isEosinophilia ? ' avec hyperéosinophilie' : '';
    conclusion = `La NFS a objectivé une hyperleucocytose${pred}, avec conservation des lignées rouge et plaquettaire.`;
    orientations.push("Confronter à la clinique et CRP (infection bactérienne si PNN élevés vs virose/LLC si lymphocytes).");
  } else if (isLeukopenia && isRedNormal && isPlateletNormal) {
    if (pnn === null) conclusion = "La NFS a objectivé une leucopénie, nécessitant une analyse de la formule leucocytaire pour préciser la population concernée.";
    else if (isNeutropenia) conclusion = `La NFS a objectivé une leucopénie avec neutropénie (${pnn} G/L de PNN), avec conservation des lignées rouge et plaquettaire.`;
    else conclusion = `La NFS a objectivé une leucopénie (${gb} G/L), avec conservation des lignées rouge et plaquettaire.`;
    orientations.push("Évoquer infection virale aiguë, origine médicamenteuse ou toxique.");
  } else if (isAnemia && isWhiteNormal && isPlateletNormal) {
    conclusion = `La NFS a objectivé une anémie ${qualifAnemie}, avec conservation des lignées blanche et plaquettaire.`;
    orientations.push(isMicrocytic ? "Ferritine + CRP (carence martiale vs inflammation)." : isMacrocytic ? "Vitamine B12, folates, TSH, bilan hépatique." : "Numération des réticulocytes (régénérative vs arégénérative).");
  } else if (isThrombopenia && isRedNormal && isWhiteNormal) {
    conclusion = `La NFS a objectivé une thrombopénie isolée (${plt} G/L), sans anomalie significative des autres lignées sanguines.`;
    orientations.push("Toujours contrôler la numération plaquettaire sur tube citraté (éliminer fausse thrombopénie à l'EDTA).");
  } else if (isThrombocytosis && isRedNormal && isWhiteNormal) {
    conclusion = `La NFS a objectivé une thrombocytose isolée (${plt} G/L), avec conservation des lignées rouge et blanche.`;
    orientations.push("Rechercher carence martiale, syndrome inflammatoire, asplénie ou syndrome myéloprolifératif (JAK2).");
  } else if (isPolyglobulia && isWhiteNormal && isPlateletNormal) {
    conclusion = `La NFS a objectivé une érythrocytose / polyglobulie (Hb : ${hb} g/dL), avec conservation des lignées blanche et plaquettaire.`;
    orientations.push("Éliminer hémoconcentration puis rechercher polyglobulie primitive (JAK2) ou hypoxie chronique.");
  } else if (isAgranulocytosis && isRedNormal && isPlateletNormal) {
    conclusion = `La NFS a objectivé une agranulocytose aiguë sévère (PNN : ${pnn} G/L < 0.5 G/L) — Urgence vitale infectieuse absolue.`;
    orientations.push("Isolement protecteur, hémocultures si fièvre, arrêt immédiat des médicaments imputables.");
  } else if (isRedNormal && isWhiteNormal && isPlateletNormal) {
    conclusion = "La NFS ne montre pas d'anomalie hématologique significative. Les trois lignées sanguines présentent des valeurs globalement dans les intervalles de référence.";
  } else {
    const parts = [];
    if (isAnemia) parts.push(`une anémie ${qualifAnemie}`);
    if (isPolyglobulia) parts.push("une polyglobulie");
    if (isHyperleukocytosis) parts.push("une hyperleucocytose");
    if (isLeukopenia) parts.push("une leucopénie");
    if (isThrombopenia) parts.push("une thrombopénie");
    if (isThrombocytosis) parts.push("une thrombocytose");
    conclusion = parts.length ? `La NFS a objectivé ${parts.join(' et ')}.` : "La NFS présente des variations modérées à corréler à la clinique.";
  }

  const redSummary = isAnemia ? `Anémie ${qualifAnemie}${retic !== null ? (retic >= 120 ? ' régénérative' : ' arégénérative') : ''}.` : `Lignée rouge conservée (Hb ${hb ?? '-'} g/dL${vgm ? ', VGM ' + vgm.toFixed(1) + ' fL' : ''}).`;
  const whiteSummary = isAgranulocytosis ? `Agranulocytose critique (PNN ${pnn} G/L).` : isHyperleukocytosis ? `Hyperleucocytose (${gb} G/L).` : isLeukopenia ? `Leucopénie (${gb} G/L).` : `Lignée blanche conservée (GB ${gb ?? '-'} G/L, PNN ${pnn ?? '-'} G/L).`;
  const pltSummary = isThrombopenia ? `Thrombopénie (${plt} G/L).` : isThrombocytosis ? `Thrombocytose (${plt} G/L).` : `Plaquettes normales (${plt ?? '-'} G/L${vpm ? ', VPM ' + vpm + ' fL' : ''}).`;

  const extraTopHtml = `
    <div style="font-size:12.5px; margin-bottom:10px; padding:8px; border-radius:8px; background:rgba(255,255,255,0.03);">
      <div><strong>🔴 Lignée Rouge :</strong> ${safeHtml(redSummary)}</div>
      <div style="margin-top:3px;"><strong>⚪ Lignée Blanche :</strong> ${safeHtml(whiteSummary)}</div>
      <div style="margin-top:3px;"><strong>🟡 Plaquettes :</strong> ${safeHtml(pltSummary)}</div>
    </div>
  `;

  const savedMsg = saveLabToPatient('NFS', badgeSummary(alert), conclusion, alert);
  const res = document.getElementById('nfs-result');
  res.innerHTML = renderStructuredLabCard({
    title: 'Analyse Complète de l\'Hémogramme (NFS)',
    alertLevel: alert,
    conclusion,
    normalParams,
    abnormalParams,
    associations,
    orientations,
    mechanisms: [
      ['Hémoglobine & VGM', 'Le VGM (< 80 microcytose vs > 100 macrocytose) et les réticulocytes (< 120 G/L arégénératif vs > 120 G/L régénératif) classent toute anémie.'],
      ['Formule Leucocytaire', 'Toujours interpréter en valeurs absolues (G/L) et jamais en pourcentages !'],
      ['Plaquettes', 'Toute thrombopénie découverte fortuitement doit être contrôlée sur tube citraté (éliminer agrégats à l\'EDTA).']
    ],
    caveat: "RAPPEL MÉDICAL : L'hémogramme est un examen d'orientation biologique qui doit toujours être confronté à l'examen clinique et au frottis sanguin.",
    extraTopHtml,
    savedMsg
  });
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

function badgeSummary(alert) {
  return alert === 'CRITICAL' ? 'Anomalies critiques' : alert === 'MODERATE' ? 'Perturbations modérées' : 'Bilan normal';
}

// 8B. UECréat & Ionogramme
function calculateRenal() {
  const urea = parseNum('ren-urea');
  const creat = parseNum('ren-creat');
  const na = parseNum('ren-na');
  const k = parseNum('ren-k');
  const cl = parseNum('ren-cl');
  const hco3 = parseNum('ren-hco3');
  const gly = parseNum('ren-gly');
  const age = parseNum('ren-age', 65);
  const weight = parseNum('ren-weight', 70);
  const isFemale = document.getElementById('ren-gender')?.value === 'F';

  let alert = 'NORMAL';
  const raise = s => { if (s === 'CRITICAL') alert = 'CRITICAL'; else if (s === 'MODERATE' && alert !== 'CRITICAL') alert = 'MODERATE'; };
  const normalParams = [], abnormalParams = [], associations = [], orientations = [], findings = [];

  if (k !== null) {
    if (k < 3.0) { raise('CRITICAL'); abnormalParams.push(`Kaliémie : ${k} mmol/L [HYPOKALIÉMIE SÉVÈRE]`); findings.push(`une hypokaliémie sévère (${k} mmol/L)`); orientations.push("ECG immédiat (onde U) et recharge K+ prudente."); }
    else if (k < 3.5) { raise('MODERATE'); abnormalParams.push(`Kaliémie : ${k} mmol/L [HYPOKALIÉMIE]`); findings.push(`une hypokaliémie (${k} mmol/L)`); }
    else if (k >= 6.0) { raise('CRITICAL'); abnormalParams.push(`Kaliémie : ${k} mmol/L [HYPERKALIÉMIE MENAÇANTE]`); findings.push(`une hyperkaliémie sévère (${k} mmol/L)`); orientations.push("ECG en extrême urgence (ondes T pointues, QRS large) ; gluconate de calcium IV si signes ECG."); }
    else if (k > 5.0) { raise('MODERATE'); abnormalParams.push(`Kaliémie : ${k} mmol/L [HYPERKALIÉMIE MODÉRÉE]`); findings.push(`une hyperkaliémie (${k} mmol/L)`); orientations.push("Éliminer fausse hyperkaliémie par hémolyse du tube."); }
    else normalParams.push(`Kaliémie : ${k} mmol/L (norme 3.5 - 5.0)`);
  }

  if (na !== null) {
    if (na < 125) { raise('CRITICAL'); abnormalParams.push(`Natrémie : ${na} mmol/L [HYPONATRÉMIE SÉVÈRE]`); findings.push(`une hyponatrémie profonde (${na} mmol/L)`); orientations.push("Correction lente (<= 8-10 mmol/L/24h) pour éviter la myélinolyse centro-pontine."); }
    else if (na < 135) { raise('MODERATE'); abnormalParams.push(`Natrémie : ${na} mmol/L [HYPONATRÉMIE]`); findings.push(`une hyponatrémie (${na} mmol/L)`); }
    else if (na > 150) { raise('CRITICAL'); abnormalParams.push(`Natrémie : ${na} mmol/L [HYPERNATRÉMIE SÉVÈRE]`); findings.push(`une hypernatrémie sévère (${na} mmol/L)`); }
    else if (na > 145) { raise('MODERATE'); abnormalParams.push(`Natrémie : ${na} mmol/L [HYPERNATRÉMIE]`); findings.push(`une hypernatrémie (${na} mmol/L)`); }
    else normalParams.push(`Natrémie : ${na} mmol/L (norme 135 - 145)`);
  }

  if (hco3 !== null) {
    if (hco3 < 15) { raise('CRITICAL'); abnormalParams.push(`HCO3- : ${hco3} mmol/L [ACIDOSE MÉTABOLIQUE SÉVÈRE]`); findings.push(`une baisse sévère des bicarbonates (${hco3} mmol/L)`); }
    else if (hco3 < 22) { raise('MODERATE'); abnormalParams.push(`HCO3- : ${hco3} mmol/L [DIMINUÉE]`); findings.push(`une diminution des bicarbonates (${hco3} mmol/L)`); }
    else if (hco3 > 32) { raise('MODERATE'); abnormalParams.push(`HCO3- : ${hco3} mmol/L [ÉLEVÉE]`); findings.push(`une élévation des bicarbonates (${hco3} mmol/L)`); }
    else normalParams.push(`Réserve alcaline HCO3- : ${hco3} mmol/L`);
  }

  let clcrText = '';
  if (creat !== null && creat > 0) {
    if (creat > 110) abnormalParams.push(`Créatininémie : ${creat} µmol/L [ÉLEVÉE]`);
    else normalParams.push(`Créatininémie : ${creat} µmol/L (norme 60 - 110)`);

    if (age && weight) {
      let cl = ((140 - age) * weight) / (creat * 0.814);
      if (isFemale) cl *= 0.85;
      clcrText = `${cl.toFixed(1)} mL/min`;
      if (cl < 30) { raise('CRITICAL'); findings.push(`une insuffisance rénale sévère/terminale (DFG ${clcrText})`); }
      else if (cl < 60) { raise('MODERATE'); findings.push(`une altération modérée du DFG (${clcrText})`); orientations.push("Adapter la posologie des médicaments à élimination rénale."); }
    }
  }

  if (urea !== null && creat !== null && creat > 0) {
    const ratio = (urea * 1000) / creat;
    if (ratio > 100 && (creat > 110 || urea > 7.5)) {
      associations.push(`Rapport Urée/Créatinine élevé (${ratio.toFixed(0)} > 100) orientant vers une IRA fonctionnelle (hypovolémie).`);
    }
  }
  if (na !== null && cl !== null && hco3 !== null) {
    const ta = na - (cl + hco3);
    if (ta > 16) {
      raise('MODERATE');
      associations.push(`Trou anionique plasmatique augmenté (${ta.toFixed(1)} mmol/L > 16) : acidose métabolique organique (lactates, cétones).`);
    }
  }
  if (gly !== null) {
    if (gly < 0.70) { raise('CRITICAL'); abnormalParams.push(`Glycémie : ${gly} g/L [HYPOGLYCÉMIE]`); findings.push(`une hypoglycémie (${gly} g/L)`); }
    else if (gly > 2.0) { raise('MODERATE'); abnormalParams.push(`Glycémie : ${gly} g/L [HYPERGLYCÉMIE]`); findings.push(`une hyperglycémie (${gly} g/L)`); }
    else normalParams.push(`Glycémie : ${gly} g/L`);
  }

  const conclusion = findings.length === 0
    ? "Le bilan ionique et rénal ne montre pas d'anomalie significative ; les électrolytes et la fonction glomérulaire évalués sont dans les normes de référence."
    : `Le bilan hydro-électrolytique et rénal a objectivé ${findings.join(', ')}, à confronter sans délai à l'état d'hydratation, à la diurèse et au tracé ECG si dyskaliémie.`;

  const extraTopHtml = clcrText ? `<div style="margin-bottom:8px; font-size:13px; color:#38BDF8; font-weight:800;">Clairance Cockcroft-Gault estimée : ${clcrText}</div>` : '';
  const savedMsg = saveLabToPatient('UECREAT', badgeSummary(alert), conclusion, alert);

  const res = document.getElementById('ren-result');
  res.innerHTML = renderStructuredLabCard({
    title: 'Ionogramme, Équilibre Acido-Basique & Rein',
    alertLevel: alert,
    conclusion,
    normalParams,
    abnormalParams,
    associations,
    orientations,
    mechanisms: [
      ['Kaliémie (K+)', 'Toute dyskaliémie sévère menace le pronostic rythmique cardiaque (ECG immédiat).'],
      ['Natrémie (Na+)', 'Reflet de l\'état d\'hydratation intracellulaire et de l\'osmolarité plasmatique efficace.'],
      ['Cockcroft-Gault & Urée/Créat', 'Un rapport Urée(mmol)*1000 / Créat(µmol) > 100 oriente vers une IRA fonctionnelle (hypoperfusion).']
    ],
    caveat: "RAPPEL CLINIQUE : Toujours comparer à une créatininémie antérieure de référence et vérifier les traitements néphrotoxiques.",
    extraTopHtml,
    savedMsg
  });
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 8C. Bilan Hépatique & Pancréatique
function calculateHepatic() {
  const alat = parseNum('hep-alat');
  const asat = parseNum('hep-asat');
  const pal = parseNum('hep-pal');
  const ggt = parseNum('hep-ggt');
  const bili = parseNum('hep-bili');
  const biliConj = parseNum('hep-bili-conj');
  const lipase = parseNum('hep-lipase');

  let alert = 'NORMAL';
  const raise = s => { if (s === 'CRITICAL') alert = 'CRITICAL'; else if (s === 'MODERATE' && alert !== 'CRITICAL') alert = 'MODERATE'; };
  const normalParams = [], abnormalParams = [], associations = [], orientations = [], findings = [];

  const hasCytolysis = (alat !== null && alat > 45) || (asat !== null && asat > 35);
  const hasCholestasis = (pal !== null && pal > 130) || (ggt !== null && ggt > 50);

  if (alat !== null || asat !== null) {
    if ((alat && alat > 450) || (asat && asat > 350)) {
      raise('CRITICAL');
      abnormalParams.push(`Transaminases : ALAT ${alat ?? '-'} / ASAT ${asat ?? '-'} UI/L [CYTOLYSE MAJEURE > 10N]`);
      findings.push("une cytolyse hépatique majeure (> 10N)");
      orientations.push("Évoquer en urgence : hépatite virale aiguë, toxicité médicamenteuse (paracétamol !) ou foie de choc ; vérifier TP/Facteur V.");
    } else if (hasCytolysis) {
      raise('MODERATE');
      abnormalParams.push(`Transaminases : ALAT ${alat ?? '-'} / ASAT ${asat ?? '-'} UI/L [CYTOLYSE MODÉRÉE]`);
      findings.push("une cytolyse hépatocytaire modérée");
    } else {
      normalParams.push(`Transaminases : ALAT ${alat ?? '-'} / ASAT ${asat ?? '-'} UI/L`);
    }
  }

  if (pal !== null || ggt !== null) {
    if (hasCholestasis) {
      raise('MODERATE');
      abnormalParams.push(`Cholestase : PAL ${pal ?? '-'} / GGT ${ggt ?? '-'} UI/L [ÉLEVÉES]`);
      findings.push("une cholestase biologique");
      orientations.push("Échographie hépatobiliaire indispensable pour éliminer une dilatation des voies biliaires.");
    } else {
      normalParams.push(`PAL ${pal ?? '-'} / GGT ${ggt ?? '-'} UI/L`);
    }
  }

  if (bili !== null) {
    if (bili > 50) { raise('CRITICAL'); abnormalParams.push(`Bilirubine totale : ${bili} µmol/L (Conj. ${biliConj ?? '-'}) [ICTÈRE FRANC]`); findings.push(`une hyperbilirubinémie franche (${bili} µmol/L)`); }
    else if (bili > 21) { raise('MODERATE'); abnormalParams.push(`Bilirubine totale : ${bili} µmol/L [SUBICTÈRE]`); findings.push(`une hyperbilirubinémie modérée (${bili} µmol/L)`); }
    else normalParams.push(`Bilirubine totale : ${bili} µmol/L`);
  }

  if (lipase !== null) {
    if (lipase > 180) {
      raise('CRITICAL');
      abnormalParams.push(`Lipasémie : ${lipase} UI/L [> 3N — PANCRÉATITE AIGUË]`);
      findings.push(`une hyperlipasémie > 3N (${lipase} UI/L) compatible avec une pancréatite aiguë`);
      orientations.push("Hospitalisation, jeûne/antalgie, rechercher lithiase biliaire (écho) et alcool.");
    } else if (lipase > 60) {
      raise('MODERATE');
      abnormalParams.push(`Lipasémie : ${lipase} UI/L [< 3N]`);
      findings.push(`une élévation modérée de la lipasémie (${lipase} UI/L)`);
    } else {
      normalParams.push(`Lipasémie : ${lipase} UI/L`);
    }
  }

  if (hasCytolysis && hasCholestasis) {
    associations.push("Atteinte hépatique mixte (cytolyse + cholestase), classique lors d'une migration lithiasique ou hépatite toxique.");
  }

  const conclusion = findings.length === 0
    ? "Le bilan hépatopancréatique est dans les valeurs de référence, sans cytolyse, sans cholestase ni hyperlipasémie."
    : `Le bilan hépatopancréatique a objectivé ${findings.join(', ')}, à confronter à l'examen abdominal, au TP et à l'échographie.`;

  const savedMsg = saveLabToPatient('HEPATIC', badgeSummary(alert), conclusion, alert);
  const res = document.getElementById('hep-result');
  res.innerHTML = renderStructuredLabCard({
    title: 'Bilan Hépatique & Pancréatique',
    alertLevel: alert,
    conclusion,
    normalParams,
    abnormalParams,
    associations,
    orientations,
    mechanisms: [
      ['Transaminases (ASAT/ALAT)', 'Témoins de souffrance hépatocytaire ; ne mesurent pas la fonction de synthèse du foie.'],
      ['Lipasémie', 'Un seuil > 3 fois la normale avec douleur épigastrique typique pose le diagnostic de pancréatite aiguë.']
    ],
    caveat: "RAPPEL CLINIQUE : Seuls le TP, le Facteur V et l'albuminémie évaluent l'insuffisance hépatocellulaire.",
    savedMsg
  });
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 8D. Hémostase & Inflammation
function calculateCoag() {
  const crp = parseNum('coag-crp');
  const vs = parseNum('coag-vs');
  const pct = parseNum('coag-pct');
  const tp = parseNum('coag-tp');
  const inr = parseNum('coag-inr');
  const tca = parseNum('coag-tca');
  const fib = parseNum('coag-fib');
  const ddim = parseNum('coag-ddim');

  let alert = 'NORMAL';
  const raise = s => { if (s === 'CRITICAL') alert = 'CRITICAL'; else if (s === 'MODERATE' && alert !== 'CRITICAL') alert = 'MODERATE'; };
  const normalParams = [], abnormalParams = [], associations = [], orientations = [], findings = [];

  if (crp !== null) {
    if (crp > 100) { raise('CRITICAL'); abnormalParams.push(`CRP : ${crp} mg/L [INFLAMMATION MAJEURE]`); findings.push(`un syndrome inflammatoire majeur (CRP ${crp} mg/L)`); }
    else if (crp > 10) { raise('MODERATE'); abnormalParams.push(`CRP : ${crp} mg/L [ÉLEVÉE]`); findings.push(`un syndrome inflammatoire biologique (CRP ${crp} mg/L)`); }
    else normalParams.push(`CRP : ${crp} mg/L`);
  }
  if (vs !== null) {
    if (vs > 20) { raise('MODERATE'); abnormalParams.push(`VS : ${vs} mm/h [ACCÉLÉRÉE]`); }
    else normalParams.push(`VS : ${vs} mm/h`);
  }
  if (pct !== null) {
    if (pct > 2.0) { raise('CRITICAL'); abnormalParams.push(`Procalcitonine : ${pct} ng/mL [SEPSIS PROBABLE]`); findings.push(`une élévation majeure de la procalcitonine (${pct} ng/mL)`); }
    else if (pct >= 0.5) { raise('MODERATE'); abnormalParams.push(`Procalcitonine : ${pct} ng/mL [ÉLEVÉE]`); findings.push(`une procalcitonine modérément élevée (${pct} ng/mL)`); }
    else normalParams.push(`Procalcitonine : ${pct} ng/mL`);
  }
  if (tp !== null) {
    if (tp < 50) { raise('CRITICAL'); abnormalParams.push(`TP : ${tp}% [EFFONDREMENT CRITIQUE]`); findings.push(`une baisse sévère du TP (${tp}%)`); orientations.push("Distinguer carence en vitamine K/AVK (Facteur V normal) vs insuffisance hépatocellulaire (Facteur V bas)."); }
    else if (tp < 70) { raise('MODERATE'); abnormalParams.push(`TP : ${tp}% [ABAISSÉ]`); findings.push(`une diminution du TP (${tp}%)`); }
    else normalParams.push(`TP : ${tp}%`);
  }
  if (inr !== null) {
    if (inr >= 4.0) { raise('CRITICAL'); abnormalParams.push(`INR : ${inr} [SURDOSAGE CRITIQUE]`); findings.push(`un INR très élevé (${inr})`); }
    else if (inr > 1.2) { raise('MODERATE'); abnormalParams.push(`INR : ${inr} [ALLONGÉ]`); }
    else normalParams.push(`INR : ${inr}`);
  }
  if (tca !== null) {
    if (tca > 1.3) { raise('MODERATE'); abnormalParams.push(`Ratio TCA : ${tca} [ALLONGÉ]`); findings.push(`un allongement du TCA (ratio ${tca})`); }
    else normalParams.push(`Ratio TCA : ${tca}`);
  }
  if (fib !== null) {
    if (fib < 1.5) { raise('CRITICAL'); abnormalParams.push(`Fibrinogène : ${fib} g/L [HYPOFIBRINOGÉNÉMIE]`); findings.push(`une hypofibrinogénémie (${fib} g/L)`); }
    else if (fib > 4.0) { raise('MODERATE'); abnormalParams.push(`Fibrinogène : ${fib} g/L [INFLAMMATION]`); }
    else normalParams.push(`Fibrinogène : ${fib} g/L`);
  }
  if (ddim !== null) {
    if (ddim > 500) { raise('MODERATE'); abnormalParams.push(`D-Dimères : ${ddim} ng/mL [POSITIFS > 500]`); findings.push(`une élévation des D-Dimères (${ddim} ng/mL)`); orientations.push("Confronter au score de Wells/Genève et imagerie (écho-Doppler / angioscanner) avant de conclure à une MTEV."); }
    else normalParams.push(`D-Dimères : ${ddim} ng/mL (VPN > 98% si probabilité non forte)`);
  }

  if (tp !== null && tp < 60 && fib !== null && fib < 1.5 && ddim !== null && ddim > 1000) {
    raise('CRITICAL');
    associations.push("Triade évocatrice de CIVD (TP bas + fibrinogène bas + D-Dimères très élevés) à confronter aux plaquettes.");
  }

  const conclusion = findings.length === 0
    ? "Le bilan inflammatoire et de coagulation est globalement équilibré dans les limites de référence."
    : `Le bilan inflammatoire et d'hémostase a objectivé ${findings.join(', ')}, à confronter au terrain (anticoagulants, foie) et aux signes cliniques.`;

  const savedMsg = saveLabToPatient('COAG_INFLAM', badgeSummary(alert), conclusion, alert);
  const res = document.getElementById('coag-result');
  res.innerHTML = renderStructuredLabCard({
    title: 'Bilan Inflammatoire & Hémostase',
    alertLevel: alert,
    conclusion,
    normalParams,
    abnormalParams,
    associations,
    orientations,
    mechanisms: [
      ['CRP & Procalcitonine', 'La PCT oriente vers une infection bactérienne systémique sévère.'],
      ['D-Dimères', 'Excellente valeur prédictive négative pour exclure TVP/EP si probabilité clinique faible ou intermédiaire.']
    ],
    caveat: "RAPPEL CLINIQUE : Toujours demander si le patient reçoit un anticoagulant (AVK, AOD, héparine).",
    savedMsg
  });
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 8E. Bandelette Urinaire & ECBU
function calculateUrine() {
  const leuco = document.getElementById('urine-leuco')?.value || 'Négatif';
  const nitrites = document.getElementById('urine-nitrites')?.value || 'Négatif';
  const prot = document.getElementById('urine-prot')?.value || 'Négatif';
  const hem = document.getElementById('urine-hem')?.value || 'Négatif';
  const cet = document.getElementById('urine-cet')?.value || 'Négatif';
  const glu = document.getElementById('urine-glu')?.value || 'Négatif';

  let alert = 'NORMAL';
  const raise = s => { if (s === 'CRITICAL') alert = 'CRITICAL'; else if (s === 'MODERATE' && alert !== 'CRITICAL') alert = 'MODERATE'; };
  const normalParams = [], abnormalParams = [], associations = [], orientations = [], findings = [];

  const hasLeuco = leuco !== 'Négatif';
  const hasNitrites = nitrites === 'Positif';
  const hasProt = prot !== 'Négatif';
  const hasHem = hem !== 'Négatif';
  const hasCet = cet !== 'Négatif';
  const hasGlu = glu !== 'Négatif';

  if (hasLeuco && hasNitrites) {
    raise('MODERATE');
    abnormalParams.push(`Leucocytes (${leuco}) + Nitrites (Positif)`);
    findings.push("une leucocyturie associée à des nitrites positifs");
    associations.push("Leucocytes + Nitrites positifs hautement compatible avec une infection urinaire à entérobactérie (E. coli).");
    orientations.push("Réaliser un ECBU avec antibiogramme si critère de complication ou fièvre.");
  } else if (hasLeuco) {
    raise('MODERATE');
    abnormalParams.push(`Leucocytes (${leuco}), Nitrites (Négatif)`);
    findings.push(`une leucocyturie isolée (${leuco})`);
    orientations.push("ECBU recommandé si symptômes (germe sans nitrate réductase ou infection décapitée).");
  } else if (hasNitrites) {
    raise('MODERATE');
    abnormalParams.push("Nitrites Positifs isolés");
    findings.push("une positivité isolée des nitrites");
  } else {
    normalParams.push("Leucocytes et Nitrites négatifs");
  }

  if (hasProt) {
    if (prot.includes('3+')) { raise('CRITICAL'); abnormalParams.push(`Protéinurie massive (${prot})`); findings.push(`une protéinurie massive (${prot})`); orientations.push("Dosage quantitatif protéinurie/créatininurie, albuminémie et fonction rénale."); }
    else { raise('MODERATE'); abnormalParams.push(`Protéinurie : ${prot}`); findings.push(`une protéinurie (${prot})`); }
  } else normalParams.push("Protéinurie : Négative");

  if (hasHem) { raise('MODERATE'); abnormalParams.push(`Hématurie : ${hem}`); findings.push(`une hématurie (${hem})`); }
  else normalParams.push("Hématurie : Négative");

  if (hasGlu) { raise('MODERATE'); abnormalParams.push(`Glycosurie : ${glu}`); findings.push(`une glycosurie (${glu})`); }
  else normalParams.push("Glycosurie : Négative");

  if (hasCet) {
    if (hasGlu) {
      raise('CRITICAL');
      associations.push("GLYCOSURIE + CÉTONURIE : Alerte majeure devant faire éliminer immédiatement une ACIDOCÉTOSE DIABÉTIQUE !");
      orientations.push("Contrôler immédiatement glycémie, ionogramme sanguin et gaz du sang/HCO3-.");
    } else raise('MODERATE');
    abnormalParams.push(`Cétonurie : ${cet}`);
    findings.push(`une cétonurie (${cet})`);
  } else normalParams.push("Cétonurie : Négative");

  const conclusion = findings.length === 0
    ? "La bandelette urinaire est strictement normale, avec excellente valeur prédictive négative contre une infection urinaire en l'absence d'atypie clinique."
    : `La bandelette urinaire a objectivé ${findings.join(', ')}, nécessitant une confrontation aux symptômes mictionnels, à la température et, selon le cas, un ECBU ou un dosage quantitatif.`;

  const savedMsg = saveLabToPatient('URINE_BU', badgeSummary(alert), conclusion, alert);
  const res = document.getElementById('urine-result');
  res.innerHTML = renderStructuredLabCard({
    title: 'Bandelette Urinaire & Orientation ECBU',
    alertLevel: alert,
    conclusion,
    normalParams,
    abnormalParams,
    associations,
    orientations,
    mechanisms: [
      ['Estérase leucocytaire & Nitrites', 'Chez la femme non ménopausée, BU (Leuco - et Nitrites -) a une VPN > 95% contre l\'infection urinaire.'],
      ['Glycosurie + Cétonurie', 'Signature d\'alerte d\'acidocétose diabétique nécessitant contrôle urgent de la glycémie et des gaz du sang.']
    ],
    caveat: "RAPPEL CLINIQUE : La bandelette est un test d'orientation rapide qui ne remplace jamais l'ECBU ni le dosage quantitatif de la protéinurie.",
    savedMsg
  });
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// 9. FORMATION CLINIQUE PÉDAGOGIQUE (6 Sections complètes : A à F)
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
        <div style="font-size: 11.5px; color: var(--text-secondary);">Référentiel Méthodique de l'Externe en Médecine (Sections 1 à 6)</div>
      </div>
    </div>

    <div class="training-nav-tabs">
      <button class="training-tab-btn ${subTab === 'anamnesis' ? 'active' : ''}" onclick="renderClinicalTraining('anamnesis')">A. Interrogatoire (14)</button>
      <button class="training-tab-btn ${subTab === 'organization' ? 'active' : ''}" onclick="renderClinicalTraining('organization')">B. Organisation (9)</button>
      <button class="training-tab-btn ${subTab === 'observation' ? 'active' : ''}" onclick="renderClinicalTraining('observation')">C. Observation (4)</button>
      <button class="training-tab-btn ${subTab === 'synthesis' ? 'active' : ''}" onclick="renderClinicalTraining('synthesis')">D. Synthèse (5)</button>
      <button class="training-tab-btn ${subTab === 'transitions' ? 'active' : ''}" onclick="renderClinicalTraining('transitions')">E. Transitions (11)</button>
      <button class="training-tab-btn ${subTab === 'oral' ? 'active' : ''}" onclick="renderClinicalTraining('oral')">F. Présentation Orale</button>
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
        Explorez les <strong>14 étapes chronologiques</strong> de l'interrogatoire au lit du malade avec formulations professionnelles, réponses types, questions de relance et exercices.
      </p>
      ${skills.map((s, idx) => `
        <div class="training-card" id="train-card-${s.id}">
          <div class="training-card-header" onclick="toggleTrainingAccordion('train-body-${s.id}')">
            <div>
              <div style="font-size: 14px; font-weight: 800; color: #F8FAFC;">${safeHtml(s.title)}</div>
              <div style="font-size: 11.5px; color: #38BDF8; margin-top: 2px;">${safeHtml(s.subtitle)}</div>
            </div>
            <span style="font-size: 14px; color: #94A3B8;">▼</span>
          </div>
          <div id="train-body-${s.id}" style="display: ${idx === 0 ? 'block' : 'none'}; margin-top: 10px; border-top: 1px solid #1E2D4A; padding-top: 10px;">
            <p style="font-size: 12.5px; line-height: 1.5; color: #CBD5E1; margin-bottom: 8px;">${safeHtml(s.explanation)}</p>
            ${s.phrasings?.length ? `
              <div class="phrasing-box">
                <strong style="color: #38BDF8;">💬 Formulations professionnelles recommandées :</strong>
                <ul style="padding-left: 16px; margin-top: 4px;">${s.phrasings.map(ph => `<li>${safeHtml(ph)}</li>`).join('')}</ul>
              </div>
            ` : ''}
            ${s.errors?.length ? `
              <div class="error-box">
                <strong style="color: #F87171;">⚠️ Erreurs et attitudes à éviter :</strong>
                <ul style="padding-left: 16px; margin-top: 4px;">${s.errors.map(er => `<li>${safeHtml(er)}</li>`).join('')}</ul>
              </div>
            ` : ''}
            ${s.patientResponses?.length ? `
              <div class="card mb-2" style="background: rgba(255,255,255,0.03); padding: 10px;">
                <strong style="font-size: 12.5px; color: #FBBF24;">🗣️ Réponses types du patient & relances :</strong>
                ${s.patientResponses.map(pr => `
                  <div style="font-size: 12px; margin-top: 6px; padding-bottom: 4px; border-bottom: 1px solid rgba(255,255,255,0.05);">
                    <div style="color: #CBD5E1;"><em>Patient :</em> « ${safeHtml(pr[0])} »</div>
                    <div style="color: #34D399;"><em>Relance :</em> ${safeHtml(pr[1])}</div>
                  </div>
                `).join('')}
              </div>
            ` : ''}
            ${s.followUpQuestions?.length ? `
              <div class="phrasing-box" style="border-left-color: #10B981; background: rgba(16,185,129,0.08);">
                <strong style="color: #34D399;">🔎 Questions complémentaires de précision :</strong>
                <ul style="padding-left: 16px; margin-top: 4px;">${s.followUpQuestions.map(fq => `<li>${safeHtml(fq)}</li>`).join('')}</ul>
              </div>
            ` : ''}
            ${s.exerciseQuestion ? `
              <div class="card mb-2" style="background: rgba(255,255,255,0.03); padding: 10px; margin-top: 8px;">
                <strong style="font-size: 12.5px; color: #34D399;">🎯 Exercice pratique :</strong>
                <div style="font-size: 12px; margin-top: 4px; margin-bottom: 8px;">${safeHtml(s.exerciseQuestion)}</div>
                <div style="display: flex; flex-direction: column; gap: 6px;">
                  ${(s.exerciseChoices || []).map((ch, cIdx) => `
                    <button class="quiz-option-btn" style="min-height: 40px; padding: 8px 10px; font-size: 12px;" onclick="handleTrainingChoice(${s.id}, ${cIdx})">
                      <span class="opt-letter" style="width: 22px; height: 22px; font-size: 11px;">${['A', 'B', 'C', 'D'][cIdx]}</span>
                      <span class="opt-text">${safeHtml(ch)}</span>
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
  }

  if (subTab === 'organization') {
    const categories = window.ALL_INFO_CATEGORIES || [];
    const exs = window.ALL_CLASSIFICATION_EXERCISES || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
        Les <strong>9 rubriques fondamentales</strong> pour classer chaque information clinique, accompagnées d'exemples et d'exercices interactifs.
      </p>
      ${categories.map(c => `
        <div class="training-card">
          <div style="font-size: 14.5px; font-weight: 800; color: #38BDF8; margin-bottom: 4px;">${safeHtml(c.categoryName)}</div>
          <p style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5; margin-bottom: 8px;">${safeHtml(c.description)}</p>
          <div class="phrasing-box">
            <strong style="color: #38BDF8;">Ce qu'il faut consigner :</strong>
            <ul style="padding-left: 16px; margin-top: 4px;">${(c.whatToInclude || []).map(item => `<li>${safeHtml(item)}</li>`).join('')}</ul>
          </div>
          ${c.examples?.length ? `
            <div class="card mb-2" style="background: rgba(255,255,255,0.03); padding: 8px 10px;">
              <strong style="font-size:12px; color:#A7F3D0;">📝 Exemples concrets en dossier :</strong>
              <ul style="padding-left: 16px; margin-top: 4px; font-size: 12px;">${c.examples.map(ex => `<li>${safeHtml(ex)}</li>`).join('')}</ul>
            </div>
          ` : ''}
          ${c.whatToExclude ? `<div style="font-size: 12px; color: #FCA5A5; margin-top: 4px;"><strong>À ne pas confondre :</strong> ${safeHtml(c.whatToExclude)}</div>` : ''}
          ${c.hospitalExample ? `<div style="font-size: 12px; color: #94A3B8; margin-top: 4px; font-style: italic;"><strong>Exemple type :</strong> « ${safeHtml(c.hospitalExample)} »</div>` : ''}
        </div>
      `).join('')}

      <div style="font-size: 14px; font-weight: 900; color: #34D399; margin: 14px 0 8px 0;">🎯 Exercices de Classification (${exs.length})</div>
      ${exs.map((ex, idx) => `
        <div class="training-card" id="class-ex-${idx}">
          <div style="font-size: 12.5px; font-weight: 700; color: #F8FAFC; margin-bottom: 8px;">« ${safeHtml(ex.rawPatientQuote)} »</div>
          <div style="display: flex; flex-direction: column; gap: 6px;">
            ${(ex.options || []).map((opt, oIdx) => `
              <button class="quiz-option-btn" style="min-height: 38px; padding: 7px 10px; font-size: 12px;" onclick="handleClassExercise(${idx}, ${oIdx})">
                <span class="opt-text">${safeHtml(opt)}</span>
              </button>
            `).join('')}
          </div>
          <div id="class-fb-${idx}" class="quiz-feedback-box" style="display: none; font-size: 12px;"></div>
        </div>
      `).join('')}
    `;
  }

  if (subTab === 'observation') {
    const obsSkills = window.ALL_OBSERVATION_SKILLS || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
        Méthodologie rigoureuse d'observation au lit du malade et de rédaction médicale.
      </p>
      ${obsSkills.map((o, idx) => `
        <div class="training-card">
          <div style="font-size: 14.5px; font-weight: 800; color: #38BDF8; margin-bottom: 4px;">${safeHtml(o.title)}</div>
          <div style="font-size: 12px; color: #A7F3D0; margin-bottom: 6px;"><strong>Objectif :</strong> ${safeHtml(o.objective)}</div>
          <p style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5; margin-bottom: 8px;">${safeHtml(o.detailedGuide)}</p>
          ${o.keyPoints?.length ? `<ul style="padding-left: 16px; font-size: 12px; color: #BAE6FD; margin-bottom: 8px;">${o.keyPoints.map(kp => `<li>${safeHtml(kp)}</li>`).join('')}</ul>` : ''}
          <div class="phrasing-box">
            <strong style="color: #34D399;">✓ Bon exemple de rédaction :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.5;">${safeHtml(o.goodExample)}</p>
          </div>
          <div class="error-box">
            <strong style="color: #F87171;">✗ Exemple à proscrire :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.5;">${safeHtml(o.badExample)}</p>
          </div>
          ${o.additionalExamples?.length ? `
            <div class="card mb-2" style="background: rgba(255,255,255,0.03); padding: 8px 10px;">
              <strong style="font-size: 12px; color: #38BDF8;">🔄 Comparaisons additionnelles :</strong>
              ${o.additionalExamples.map(ae => `
                <div style="font-size: 11.5px; margin-top: 6px; border-bottom: 1px solid rgba(255,255,255,0.05); padding-bottom: 4px;">
                  <div style="color: #FCA5A5;">✗ ${safeHtml(ae[0])}</div>
                  <div style="color: #6EE7B7;">✓ ${safeHtml(ae[1])}</div>
                </div>
              `).join('')}
            </div>
          ` : ''}
          ${o.exercisePrompt ? `
            <details style="margin-top:6px;">
              <summary style="cursor:pointer; font-size:12px; font-weight:700; color:#38BDF8;">🎯 Exercice & Correction (${idx + 1})</summary>
              <div style="font-size:12px; margin-top:6px; color:#E2E8F0;"><strong>Consigne :</strong> ${safeHtml(o.exercisePrompt)}</div>
              <div style="font-size:12px; margin-top:4px; color:#A7F3D0;"><strong>Correction :</strong> ${safeHtml(o.exerciseCorrection)}</div>
            </details>
          ` : ''}
        </div>
      `).join('')}
    `;
  }

  if (subTab === 'synthesis') {
    const cases = window.ALL_SYNTHESIS_CASES || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
        Cas cliniques guidés <em>(cas fictifs pédagogiques)</em> pour structurer la synthèse syndromique et hiérarchiser l'urgence.
      </p>
      ${cases.map(cs => `
        <div class="training-card">
          <div style="font-size: 15px; font-weight: 900; color: #38BDF8; margin-bottom: 4px;">${safeHtml(cs.title)}</div>
          <div style="font-size: 12px; color: #F8FAFC; margin-bottom: 6px;"><strong>Profil Patient :</strong> ${safeHtml(cs.patientProfile)}</div>
          <div class="card mb-2" style="background: rgba(255,255,255,0.02); font-size: 12px;">
            <div><strong>Symptômes recueillis :</strong> ${safeHtml((cs.symptomsCollected || []).join(' • '))}</div>
            <div style="margin-top: 4px;"><strong>Signes physiques retrouvés :</strong> ${safeHtml((cs.physicalSignsFound || []).join(' • '))}</div>
          </div>
          <div class="phrasing-box">
            <strong style="color: #38BDF8;">Cheminement diagnostique :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.5;">${safeHtml(cs.clinicalReasoningPath)}</p>
          </div>
          <div style="font-size: 12.5px; margin-top: 6px;"><strong>Hypothèse principale :</strong> <span style="color: #34D399; font-weight: 700;">${safeHtml(cs.primaryHypothesis)}</span></div>
          <div style="font-size: 12.5px; margin-top: 4px;"><strong>Urgence à éliminer :</strong> <span style="color: #F87171; font-weight: 700;">${safeHtml(cs.emergencyToRuleOut)}</span></div>
          <div style="font-size: 12px; margin-top: 6px; color: #BAE6FD;">
            <strong>Plan d'action immédiat :</strong>
            <ul style="padding-left: 16px; margin-top: 2px;">${(cs.immediateActionPlan || []).map(act => `<li>${safeHtml(act)}</li>`).join('')}</ul>
          </div>
        </div>
      `).join('')}
    `;
  }

  if (subTab === 'transitions') {
    const sections = window.ALL_TRANSITION_SECTIONS || [];
    return `
      <p style="font-size: 12.5px; color: var(--text-secondary); margin-bottom: 12px;">
        <strong>11 étapes de transition clinique</strong> pour fluidifier le passage d'une rubrique à l'autre et conduire une revue complète des 10 appareils.
      </p>
      ${sections.map((sec, idx) => `
        <div class="training-card">
          <div class="training-card-header" onclick="toggleTrainingAccordion('trans-body-${idx}')">
            <div>
              <div style="font-size: 14px; font-weight: 800; color: #F8FAFC;">${safeHtml(sec.title)}</div>
              <div style="font-size: 11.5px; color: #38BDF8; margin-top: 2px;">${safeHtml(sec.subtitle)}</div>
            </div>
            <span style="font-size: 14px; color: #94A3B8;">▼</span>
          </div>
          <div id="trans-body-${idx}" style="display: ${idx === 0 ? 'block' : 'none'}; margin-top: 10px; border-top: 1px solid #1E2D4A; padding-top: 10px;">
            <div style="font-size: 12px; color: #A7F3D0; margin-bottom: 8px;"><strong>Objectif :</strong> ${safeHtml(sec.objective)}</div>
            <div class="phrasing-box">
              <strong style="color: #38BDF8;">💬 Formulations de transition recommandées :</strong>
              <ul style="padding-left: 16px; margin-top: 4px;">
                ${(sec.recommendedPhrases || []).map(rp => `<li><strong>« ${safeHtml(rp.phrase)} »</strong><br><span style="color:#94A3B8; font-size:11.5px;">${safeHtml(rp.context)}</span></li>`).join('')}
              </ul>
            </div>
            ${sec.phrasesToAvoid?.length ? `
              <div class="error-box">
                <strong style="color: #F87171;">⚠️ Formulations maladroites à éviter :</strong>
                <ul style="padding-left: 16px; margin-top: 4px;">${sec.phrasesToAvoid.map(pa => `<li>${safeHtml(pa)}</li>`).join('')}</ul>
              </div>
            ` : ''}
            ${sec.systems?.length ? `
              <div style="margin-top: 10px;">
                <strong style="font-size: 13px; color: #38BDF8;">Revue systématique des 10 appareils :</strong>
                ${sec.systems.map((sys, sIdx) => `
                  <details style="margin-top:6px; background:rgba(255,255,255,0.03); padding:8px 10px; border-radius:8px;">
                    <summary style="cursor:pointer; font-size:12.5px; font-weight:700; color:#F8FAFC;">${sIdx + 1}. ${safeHtml(sys.systemName)}</summary>
                    <div style="font-size:12px; margin-top:6px; color:#38BDF8;"><strong>Phrases de transition :</strong></div>
                    <ul style="padding-left:16px; font-size:11.5px; color:#CBD5E1;">${sys.transitionPhrases.map(p => `<li>${safeHtml(p)}</li>`).join('')}</ul>
                    <div style="font-size:12px; margin-top:6px; color:#34D399;"><strong>Questions ciblées :</strong></div>
                    <ul style="padding-left:16px; font-size:11.5px; color:#CBD5E1;">${sys.targetedQuestions.map(q => `<li>${safeHtml(q)}</li>`).join('')}</ul>
                    <div style="font-size:12px; margin-top:6px; color:#FCA5A5;"><strong>Pièges à éviter :</strong></div>
                    <ul style="padding-left:16px; font-size:11.5px; color:#FECACA;">${sys.mistakesToAvoid.map(m => `<li>${safeHtml(m)}</li>`).join('')}</ul>
                  </details>
                `).join('')}
              </div>
            ` : ''}
          </div>
        </div>
      `).join('')}
    `;
  }

  if (subTab === 'oral') {
    const criteria = window.ALL_ORAL_EVAL_CRITERIA || [];
    const cases = window.ALL_ORAL_CASES || [];
    return `
      <div class="card mb-3" style="background: rgba(2,132,199,0.08);">
        <div style="font-size: 14px; font-weight: 800; color: #38BDF8; margin-bottom: 8px;">Grille d'Auto-Évaluation en 8 Critères (${oralChecklistChecked.size}/${criteria.length})</div>
        ${criteria.map((c, idx) => {
          const checked = oralChecklistChecked.has(idx);
          return `
            <div class="eval-check-item ${checked ? 'checked' : ''}" onclick="toggleOralCheck(${idx})">
              <span style="font-size:16px;">${checked ? '✅' : '⬜'}</span>
              <div>
                <div style="font-size:12.5px; font-weight:800; color:#F8FAFC;">${safeHtml(c.title)}</div>
                <div style="font-size:11.5px; color:var(--text-secondary); margin-top:2px;">${safeHtml(c.description)}</div>
              </div>
            </div>
          `;
        }).join('')}
      </div>

      <div style="font-size: 14.5px; font-weight: 800; color: #F8FAFC; margin-bottom: 8px;">
        Présentations Comparatives Guidées (${cases.length} cas fictifs pédagogiques)
      </div>
      ${cases.map(cs => `
        <div class="training-card">
          <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:4px;">
            <span class="prev-tag">${safeHtml(cs.specialty)}</span>
            <span style="font-size:11px; color:#94A3B8;">⏱️ ${safeHtml(cs.durationTarget)}</span>
          </div>
          <div style="font-size: 14.5px; font-weight: 800; color: #38BDF8; margin-bottom: 4px;">${safeHtml(cs.title)}</div>
          <div style="font-size: 12px; color: #CBD5E1; margin-bottom: 8px;">${safeHtml(cs.patientProfile)}</div>
          <div class="error-box">
            <strong style="color: #F87171;">✗ Présentation désorganisée à éviter :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.5;">« ${safeHtml(cs.badVersion)} »</p>
          </div>
          <div class="phrasing-box" style="border-left-color:#10B981; background:rgba(16,185,129,0.08);">
            <strong style="color: #34D399;">✓ Script de Présentation Structurée de Référence :</strong>
            <p style="font-size: 12px; margin-top: 4px; line-height: 1.55;">« ${safeHtml(cs.referenceScript)} »</p>
          </div>
          ${cs.keyStrengths?.length ? `
            <div style="font-size: 12px; margin-top: 6px; color: #BAE6FD;">
              <strong>Points forts :</strong>
              <ul style="padding-left:16px; margin-top:2px;">${cs.keyStrengths.map(ks => `<li>${safeHtml(ks)}</li>`).join('')}</ul>
            </div>
          ` : ''}
          ${cs.seniorQuestions?.length ? `
            <details style="margin-top:8px;">
              <summary style="cursor:pointer; font-size:12px; font-weight:700; color:#FBBF24;">🩺 Questions probables du Senior au tour de salle</summary>
              ${cs.seniorQuestions.map(sq => `
                <div style="font-size:12px; margin-top:6px; padding:6px; background:rgba(255,255,255,0.03); border-radius:6px;">
                  <div style="color:#FDE68A;"><strong>Q :</strong> ${safeHtml(sq[0])}</div>
                  <div style="color:#A7F3D0; margin-top:2px;"><strong>R :</strong> ${safeHtml(sq[1])}</div>
                </div>
              `).join('')}
            </details>
          ` : ''}
        </div>
      `).join('')}
    `;
  }
  return '';
}

function toggleOralCheck(idx) {
  if (oralChecklistChecked.has(idx)) oralChecklistChecked.delete(idx);
  else oralChecklistChecked.add(idx);
  renderClinicalTraining('oral');
}

function toggleTrainingAccordion(bodyId) {
  const el = document.getElementById(bodyId);
  if (!el) return;
  el.style.display = el.style.display === 'none' ? 'block' : 'none';
}

function handleTrainingChoice(skillId, selectedIdx) {
  const s = (window.ALL_ANAMNESIS_SKILLS || []).find(item => item.id === skillId);
  if (!s) return;
  const correctIdx = s.correctChoice;
  const card = document.getElementById(`train-card-${skillId}`);
  if (!card) return;
  card.querySelectorAll('.quiz-option-btn').forEach((b, idx) => {
    b.onclick = null;
    if (idx === correctIdx) b.classList.add('correct');
    else if (idx === selectedIdx) b.classList.add('wrong');
  });
  const fb = document.getElementById(`train-feedback-${skillId}`);
  if (fb) {
    const ok = selectedIdx === correctIdx;
    fb.innerHTML = `<strong style="color: ${ok ? '#34D399' : '#F87171'};">${ok ? '✓ Exact !' : '✗ Incorrect.'}</strong><div style="margin-top:4px; line-height:1.5;">${safeHtml(s.exerciseCorrection)}</div>`;
    fb.style.display = 'block';
  }
}

function handleClassExercise(idx, selectedOptIdx) {
  const ex = (window.ALL_CLASSIFICATION_EXERCISES || [])[idx];
  if (!ex) return;
  const chosen = (ex.options || [])[selectedOptIdx];
  const correct = ex.correctCategory;
  const card = document.getElementById(`class-ex-${idx}`);
  if (!card) return;
  card.querySelectorAll('.quiz-option-btn').forEach((b, oIdx) => {
    b.onclick = null;
    const optVal = (ex.options || [])[oIdx];
    if (optVal === correct) b.classList.add('correct');
    else if (oIdx === selectedOptIdx) b.classList.add('wrong');
  });
  const fb = document.getElementById(`class-fb-${idx}`);
  if (fb) {
    const ok = chosen === correct;
    fb.innerHTML = `<strong style="color: ${ok ? '#34D399' : '#F87171'};">${ok ? '✓ Exact !' : '✗ Incorrect.'}</strong><div style="margin-top:4px;">${safeHtml(ex.explanation)}</div>`;
    fb.style.display = 'block';
  }
}

// 10. SÉMIOLOGIE (304 Fiches 14 rubriques + 31 Cours Médicaux + 90 QCM)
let currentApparatusFilter = 'all';

function renderCategoryPills() {
  const container = document.getElementById('semiology-category-pills');
  if (!container) return;
  const cats = window.ALL_SEMIOLOGY_CATEGORIES || [];
  container.innerHTML = cats.map(c => `
    <button class="pill-item ${currentApparatusFilter === c.slug ? 'active' : ''}" id="pill-app-${c.slug}" onclick="setApparatusFilter('${c.slug}')">
      ${safeHtml(c.label)}
    </button>
  `).join('');
}

function setApparatusFilter(cat) {
  currentApparatusFilter = cat;
  document.querySelectorAll('#semiology-category-pills .pill-item').forEach(b => b.classList.remove('active'));
  const btn = document.getElementById(`pill-app-${cat}`);
  if (btn) btn.classList.add('active');

  renderSheets();
  renderCourses();
  renderQuiz();
}

function onSemiologySearch() {
  renderSheets();
  renderCourses();
}

function renderSheets() {
  const container = document.getElementById('semiology-sheets-list');
  if (!container || !window.ALL_SEMIOLOGY_SHEETS) return;

  const query = (document.getElementById('semiology-search-input')?.value || '').toLowerCase().trim();
  const sheets = window.ALL_SEMIOLOGY_SHEETS.filter(s => {
    const matchCat = currentApparatusFilter === 'all' || s.cat === currentApparatusFilter;
    const matchQuery = !query || s.name.toLowerCase().includes(query) || (s.definition || '').toLowerCase().includes(query) || (s.categoryName || '').toLowerCase().includes(query);
    return matchCat && matchQuery;
  });

  const heading = document.getElementById('semiology-sheets-heading');
  if (heading) heading.textContent = `Encyclopédie Sémiologique (${sheets.length} fiches)`;

  if (sheets.length === 0) {
    container.innerHTML = `<div class="card" style="text-align:center; color:var(--text-secondary);">Aucune fiche ne correspond à ce filtre.</div>`;
    return;
  }

  container.innerHTML = sheets.map(s => `
    <div class="card mb-3" style="cursor: pointer;" onclick="openSheetModal('${escapeJsArg(s.id)}')">
      <div style="display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 4px;">
        <span class="prev-tag">${safeHtml(s.categoryName)} • ${safeHtml(s.subCategory || 'Signe')}</span>
        <span style="color: #38BDF8; font-size: 16px;">›</span>
      </div>
      <h3 style="font-size: 15px; font-weight: 800; color: #F8FAFC; margin-bottom: 4px;">${safeHtml(s.name)}</h3>
      <p style="font-size: 12.5px; color: var(--text-secondary); line-height: 1.5;">${safeHtml((s.definition || '').substring(0, 145))}...</p>
    </div>
  `).join('');
}

function openSheetModal(sheetId) {
  const s = (window.ALL_SEMIOLOGY_SHEETS || []).find(item => item.id === sheetId);
  if (!s) return;

  const textCard = (numTitle, val, extraStyle = '') => val
    ? `<div class="card mb-2" style="${extraStyle}"><strong>${numTitle}</strong><p style="font-size:12.5px; margin-top:4px; line-height:1.55; color:var(--text-primary);">${safeHtml(val)}</p></div>`
    : '';
  const listCard = (numTitle, arr, extraStyle = '') => (arr && arr.length)
    ? `<div class="card mb-2" style="${extraStyle}"><strong>${numTitle}</strong><ul style="padding-left:18px; font-size:12.5px; margin-top:4px; line-height:1.55;">${arr.map(x => `<li style="margin-bottom:3px;">${safeHtml(x)}</li>`).join('')}</ul></div>`
    : '';

  document.getElementById('modal-sheet-content-body').innerHTML = `
    <span class="prev-tag">${safeHtml(s.categoryName)} • ${safeHtml(s.subCategory || 'Signe')}</span>
    <h2 style="font-size: 18px; font-weight: 900; color: #38BDF8; margin: 10px 0 12px 0;">${safeHtml(s.name)}</h2>

    ${textCard('1. Définition Sémiologique :', s.definition, 'background: rgba(2, 132, 199, 0.1);')}
    ${textCard('2. Physiopathologie et Mécanisme :', s.physiopathology)}
    ${listCard('3. Étiologies Principales :', s.etiologies)}
    ${textCard('4. Circonstances d\'Apparition :', s.circumstances)}
    ${textCard('5. Interrogatoire :', s.anamnesis)}
    ${textCard('6. Méthode de Recherche :', s.searchMethod)}
    ${textCard('7. Examen Clinique :', s.physicalExam)}
    ${listCard('8. Signes Associés :', s.associatedSigns)}
    ${textCard('9. Signification Clinique :', s.clinicalSignificance)}
    ${listCard('10. Diagnostics Différentiels :', s.differentialDiagnoses)}
    ${listCard('11. Examens Complémentaires Utiles :', s.paraclinical)}
    ${listCard('12. Complications ou Signes de Gravité :', s.gravitySigns, 'background: rgba(220, 38, 38, 0.1); border-color: var(--alert-red);')}
    ${textCard('13. Limites et Pièges Diagnostiques :', s.pitfallsAndLimits, 'background: rgba(245, 158, 11, 0.08); border-color: var(--alert-orange);')}
    ${listCard('14. Points Essentiels à Retenir :', s.keyPoints, 'background: rgba(16, 185, 129, 0.1); border-color: var(--alert-green);')}
  `;
  openModal('modal-sheet-detail');
}

function renderCourses() {
  const container = document.getElementById('semiology-courses-list');
  if (!container || !window.ALL_MEDICAL_COURSES) return;

  const query = (document.getElementById('semiology-search-input')?.value || '').toLowerCase().trim();
  const courses = window.ALL_MEDICAL_COURSES.filter(c => {
    const matchCat = currentApparatusFilter === 'all' || c.cat === currentApparatusFilter;
    const matchQuery = !query || c.title.toLowerCase().includes(query) || (c.subtitle || '').toLowerCase().includes(query) || (c.definition || '').toLowerCase().includes(query);
    return matchCat && matchQuery;
  });

  const heading = document.getElementById('semiology-courses-heading');
  if (heading) heading.textContent = `Cours Médicaux Universitaires (${courses.length} cours)`;

  if (courses.length === 0) {
    container.innerHTML = `<div class="card" style="text-align:center; color:var(--text-secondary);">Aucun cours médical pour ce filtre.</div>`;
    return;
  }

  container.innerHTML = courses.map(c => `
    <div class="card mb-3" style="cursor: pointer;" onclick="openCourseModal('${escapeJsArg(c.id)}')">
      <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:4px;">
        <span class="prev-tag">${safeHtml(c.specialtyLabel)}</span>
        <span style="color:#38BDF8; font-size:16px;">›</span>
      </div>
      <h3 style="font-size:15px; font-weight:800; color:#F8FAFC; margin-bottom:4px;">${safeHtml(c.title)}</h3>
      <div style="font-size:12px; color:#38BDF8; margin-bottom:4px;">${safeHtml(c.subtitle)}</div>
      <p style="font-size:12px; color:var(--text-secondary); line-height:1.45;">${safeHtml((c.definition || '').substring(0, 130))}...</p>
    </div>
  `).join('');
}

function openCourseModal(courseId) {
  const c = (window.ALL_MEDICAL_COURSES || []).find(item => item.id === courseId);
  if (!c) return;

  const textCard = (t, v, style = '') => v ? `<div class="card mb-2" style="${style}"><strong>${t}</strong><p style="font-size:12.5px; margin-top:4px; line-height:1.55;">${safeHtml(v)}</p></div>` : '';
  const listCard = (t, arr, style = '') => (arr && arr.length) ? `<div class="card mb-2" style="${style}"><strong>${t}</strong><ul style="padding-left:18px; font-size:12.5px; margin-top:4px; line-height:1.55;">${arr.map(x => `<li style="margin-bottom:3px;">${safeHtml(x)}</li>`).join('')}</ul></div>` : '';

  const tablesHtml = (c.summaryTables || []).map(tb => `
    <div class="card mb-2">
      <strong style="color:#38BDF8;">📊 ${safeHtml(tb.title)}</strong>
      <div class="course-table-wrap">
        <table class="course-table">
          <thead><tr>${(tb.headers || []).map(h => `<th>${safeHtml(h)}</th>`).join('')}</tr></thead>
          <tbody>
            ${(tb.rows || []).map(row => `<tr>${row.map(cell => `<td>${safeHtml(cell)}</td>`).join('')}</tr>`).join('')}
          </tbody>
        </table>
      </div>
    </div>
  `).join('');

  document.getElementById('modal-course-content-body').innerHTML = `
    <div style="display:flex; justify-content:space-between; align-items:center; flex-wrap:wrap; gap:6px;">
      <span class="prev-tag">${safeHtml(c.specialtyLabel)}</span>
      <span style="font-size:11px; color:#94A3B8;">${safeHtml(c.academicLevel)}</span>
    </div>
    <h2 style="font-size:18px; font-weight:900; color:#38BDF8; margin:10px 0 4px 0;">${safeHtml(c.title)}</h2>
    <div style="font-size:12.5px; color:var(--text-secondary); margin-bottom:12px;">${safeHtml(c.subtitle)}</div>

    ${textCard('1. Définition :', c.definition, 'background: rgba(2,132,199,0.1);')}
    ${textCard('2. Épidémiologie :', c.epidemiology)}
    ${listCard('3. Étiologies & Facteurs de Risque :', c.etiologiesAndRiskFactors)}
    ${textCard('4. Physiopathologie :', c.physiopathology)}
    ${textCard('5. Anatomopathologie / Classification :', c.classificationAndPathology)}
    ${textCard('6. Clinique (Signes Fonctionnels & Physiques) :', c.clinicalSigns)}
    ${listCard('7. Examens Paracliniques :', c.paraclinicalExams)}
    ${listCard('8. Critères Diagnostiques :', c.diagnosticCriteria)}
    ${listCard('9. Formes Cliniques :', c.clinicalForms)}
    ${textCard('10. Évolution, Complications & Pronostic :', c.evolutionAndPrognosis)}
    ${listCard('11. Principes de Prise en Charge :', c.managementPrinciples)}
    ${listCard('12. Diagnostics Différentiels :', c.differentialDiagnoses)}

    ${tablesHtml}

    <div class="card mb-2" style="background: rgba(245,158,11,0.08); border-color: var(--alert-orange);">
      <strong style="color:#FBBF24;">13. ${safeHtml(c.clinicalCaseTitle || 'Cas Clinique Pédagogique (cas fictif pédagogique)')}</strong>
      <p style="font-size:12.5px; margin-top:6px; line-height:1.55;">${safeHtml(c.clinicalCaseScenario)}</p>
      <details style="margin-top:8px;">
        <summary style="cursor:pointer; font-size:12.5px; font-weight:800; color:#34D399;">Voir l'analyse et la correction</summary>
        <p style="font-size:12.5px; margin-top:6px; line-height:1.55; color:#A7F3D0;">${safeHtml(c.clinicalCaseCorrection)}</p>
      </details>
    </div>

    ${listCard('14. Points Clés à Retenir :', c.keyPoints, 'background: rgba(16,185,129,0.1); border-color: var(--alert-green);')}
  `;
  openModal('modal-course-detail');
}

function renderQuiz() {
  const container = document.getElementById('semiology-quiz-list');
  if (!container || !window.ALL_QUIZ_QUESTIONS) return;

  const questions = window.ALL_QUIZ_QUESTIONS.filter(q => currentApparatusFilter === 'all' || q.cat === currentApparatusFilter);

  const tabBtn = document.getElementById('tab-btn-semiology-quiz');
  if (tabBtn) tabBtn.textContent = `Quiz QCM (${quizScore.correct}/${window.ALL_QUIZ_QUESTIONS.length})`;

  const badge = document.getElementById('quiz-counter-badge');
  if (badge) badge.textContent = `${questions.length} Questions Disponibles`;

  if (questions.length === 0) {
    container.innerHTML = `<div class="card" style="text-align:center; color:var(--text-secondary);">Aucune question QCM pour cette spécialité (sélectionnez "Tous les appareils" ou une spécialité majeure).</div>`;
    return;
  }

  container.innerHTML = questions.map((q, idx) => `
    <div class="card mb-3" id="quiz-box-${q.id}">
      <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
        <span class="prev-tag">${safeHtml(q.categoryName)}</span>
        <span style="font-size: 11.5px; color: var(--text-secondary); font-weight: 700;">Question ${idx + 1} / ${questions.length}</span>
      </div>
      <div style="font-size: 14.5px; font-weight: 800; color: #F8FAFC; margin-bottom: 12px; line-height: 1.5;">${safeHtml(q.q)}</div>
      <div class="quiz-options-group">
        ${(q.opts || []).map((opt, oIdx) => `
          <button type="button" class="quiz-option-btn" id="btn-opt-${q.id}-${oIdx}" onclick="handleQuizAnswer('${escapeJsArg(q.id)}', ${oIdx})">
            <span class="opt-letter">${['A', 'B', 'C', 'D'][oIdx]}</span>
            <span class="opt-text">${safeHtml(opt)}</span>
          </button>
        `).join('')}
      </div>
      <div id="quiz-feedback-${q.id}" class="quiz-feedback-box" style="display: none;"></div>
    </div>
  `).join('');
}

function handleQuizAnswer(qId, selectedIdx) {
  const q = (window.ALL_QUIZ_QUESTIONS || []).find(item => item.id === qId);
  if (!q) return;
  const box = document.getElementById(`quiz-box-${qId}`);
  if (!box) return;

  box.querySelectorAll('.quiz-option-btn').forEach((btn, idx) => {
    btn.onclick = null;
    if (idx === q.correct) btn.classList.add('correct');
    else if (idx === selectedIdx) btn.classList.add('wrong');
  });

  const isCorrect = selectedIdx === q.correct;
  if (!answeredQuizIds.has(qId)) {
    answeredQuizIds.add(qId);
    if (isCorrect) quizScore.correct++;
    quizScore.totalAnswered++;
  }

  const tabBtn = document.getElementById('tab-btn-semiology-quiz');
  if (tabBtn) tabBtn.textContent = `Quiz QCM (${quizScore.correct}/${window.ALL_QUIZ_QUESTIONS.length})`;

  const fb = document.getElementById(`quiz-feedback-${qId}`);
  if (fb) {
    fb.innerHTML = `
      <div style="display: flex; align-items: center; justify-content: space-between; margin-bottom: 6px;">
        <strong style="color: ${isCorrect ? '#34D399' : '#F87171'}; font-size: 14px;">
          ${isCorrect ? '✓ Excellente réponse !' : '✗ Réponse incorrecte.'}
        </strong>
        <span style="font-size: 11px; color: #94A3B8;">Score : ${quizScore.correct}/${quizScore.totalAnswered}</span>
      </div>
      <p style="font-size: 12.5px; color: #CBD5E1; line-height: 1.5; margin: 0;">${safeHtml(q.exp)}</p>
      ${q.clinicalPearl ? `<div style="margin-top:6px; font-size:12px; color:#A7F3D0;"><strong>💎 Perle clinique :</strong> ${safeHtml(q.clinicalPearl)}</div>` : ''}
    `;
    fb.style.display = 'block';
  }
}

// 11. Observation Clinique & Mise à Jour Quotidienne
function generateClinicalDraft() {
  const patient = document.getElementById('obs-patient-name').value.trim() || 'Patient';
  const age = document.getElementById('obs-age').value;
  const gender = document.getElementById('obs-gender').value;
  const dept = document.getElementById('obs-dept').value;
  const motif = document.getElementById('obs-motif').value;
  const hdm = document.getElementById('obs-hdm').value;
  const resp = document.getElementById('obs-resp').value;
  const cv = document.getElementById('obs-cv').value;
  const digNeuro = document.getElementById('obs-dig-neuro').value;
  const general = document.getElementById('obs-general')?.value || '';
  const paraclinical = document.getElementById('obs-paraclinical')?.value || '';
  const synthese = document.getElementById('obs-synthese').value;

  const text = [
    `OBSERVATION MÉDICALE INITIALE`,
    `SERVICE : ${dept}`,
    `PATIENT : ${patient} (${age} ans, ${gender})`,
    ``,
    `1. MOTIF D'ADMISSION :`,
    motif,
    ``,
    `2. HISTOIRE DE LA MALADIE :`,
    hdm,
    ``,
    `3. EXAMEN GÉNÉRAL & CONSTANTES :`,
    general || 'Constantes non renseignées.',
    ``,
    `4. EXAMEN PHYSIQUE PAR APPAREILS :`,
    `• Respiratoire : ${resp}`,
    `• Cardiovasculaire : ${cv}`,
    `• Digestif & Neurologique : ${digNeuro}`,
    ``,
    `5. EXAMENS PARACLINIQUES COMPLÉMENTAIRES :`,
    paraclinical || 'En attente des résultats.',
    ``,
    `6. SYNTHÈSE CLINIQUE & HYPOTHÈSES :`,
    synthese,
    ``,
    `El Vita est un outil d'aide à l'observation clinique et ne se substitue pas au jugement médical.`
  ].join('\n');

  const pid = parseInt(document.getElementById('obs-patient-select')?.value, 10) || activePatientId || 0;
  let savedMsg = '';
  if (pid > 0) {
    const rec = ensurePatientRecord(pid);
    rec.observations.unshift({
      timestamp: Date.now(),
      formattedDateTime: formatNow(),
      type: 'Observation Initiale',
      summary: synthese.substring(0, 140),
      text
    });
    savePatientRecords();
    savedMsg = `<div style="margin-top:8px; font-size:12px; color:#34D399; font-weight:700;">✓ Observation archivée dans le dossier du patient.</div>`;
  }

  const res = document.getElementById('obs-result');
  res.innerHTML = `
    <div class="result-card" style="border-left: 4px solid var(--alert-green);">
      <div style="font-weight: 800; color: #34D399; margin-bottom: 6px;">✓ Observation Clinique Structurée Générée</div>
      <pre style="white-space: pre-wrap; font-family: inherit; font-size: 12.5px; line-height: 1.6; color: #CBD5E1;">${safeHtml(text)}</pre>
      ${savedMsg}
    </div>
  `;
  res.style.display = 'block';
  res.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

function saveDailyUpdate() {
  const day = document.getElementById('daily-day').value.trim() || 'Jx';
  const date = document.getElementById('daily-date').value.trim() || formatNow();
  const state = document.getElementById('daily-state').value.trim();
  const exam = document.getElementById('daily-exam').value.trim();
  const plan = document.getElementById('daily-plan').value.trim();

  const summary = `${day} (${date}) : ${state} — ${plan}`;
  const pid = activePatientId || parseInt(document.getElementById('obs-patient-select')?.value, 10) || 0;
  if (pid > 0) {
    const rec = ensurePatientRecord(pid);
    rec.observations.unshift({
      timestamp: Date.now(),
      formattedDateTime: formatNow(),
      type: `Mise à jour ${day}`,
      summary,
      text: `${summary}\nExamen : ${exam}`
    });
    savePatientRecords();
  }

  const res = document.getElementById('daily-result');
  if (res) {
    res.innerHTML = `<div class="result-card" style="border-left: 4px solid var(--alert-green); font-size: 12.5px; color: #A7F3D0;">✓ Mise à jour clinique <strong>${safeHtml(day)}</strong> enregistrée localement${pid > 0 ? ' dans le dossier du patient sélectionné' : ''}.</div>`;
    res.style.display = 'block';
  }
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
  renderCategoryPills();
  renderSheets();
  renderCourses();
  renderQuiz();
  updateProfilePhotoUI();

  const heroName = document.getElementById('hero-student-name');
  if (heroName) heroName.textContent = currentProfile.name || 'Confrère Externe';

  // Marquer édition manuelle sur champs d'observation pour synchronisation non destructive
  ['obs-motif', 'obs-general', 'obs-paraclinical'].forEach(id => {
    const el = document.getElementById(id);
    if (el) el.addEventListener('input', () => { el.dataset.edited = '1'; });
  });

  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js').then((reg) => {
      reg.update();
    }).catch(() => {});

    navigator.serviceWorker.addEventListener('controllerchange', () => {
      window.location.reload();
    });
  }
});
