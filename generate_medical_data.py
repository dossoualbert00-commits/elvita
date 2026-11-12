#!/usr/bin/env python3
import glob
import re
import json

cat_map = {
    'CARDIOVASCULAR': ('cardio', 'Cardiovasculaire'),
    'RESPIRATORY': ('resp', 'Respiratoire'),
    'NEUROLOGICAL': ('neuro', 'Neurologie'),
    'NEUROLOGY': ('neuro', 'Neurologie'),
    'DIGESTIVE': ('digestive', 'Digestif'),
    'URINARY': ('urinary', 'Néphro-Urinaire'),
    'NEPHROLOGY': ('urinary', 'Néphro-Urinaire'),
    'RHEUMATOLOGY': ('locomotor', 'Locomoteur / Rhumato'),
    'LOCOMOTOR': ('locomotor', 'Locomoteur / Rhumato'),
    'DERMATOLOGY': ('dermatology', 'Dermatologie'),
    'GENERAL': ('general', 'Signes Généraux')
}

# 1. Parse Quiz questions
quiz_files = glob.glob('app/src/main/java/com/example/data/model/quiz/*.kt')
all_questions = []

for fpath in sorted(quiz_files):
    content = open(fpath, 'r', encoding='utf-8').read()
    blocks = content.split('QuizQuestion(')[1:]
    for b in blocks:
        m_id = re.search(r'id\s*=\s*\"([^\"]+)\"', b)
        qid = m_id.group(1) if m_id else ''
        
        m_q = re.search(r'question\s*=\s*\"(.*?)\",', b, re.DOTALL)
        qtext = m_q.group(1).strip() if m_q else ''
        
        m_opts = re.search(r'options\s*=\s*listOf\s*\((.*?)\)\s*,\s*correctOptionIndex', b, re.DOTALL)
        options = []
        if m_opts:
            options = [o.strip(' \n\r\t\"') for o in re.findall(r'\"(.*?)\"', m_opts.group(1), re.DOTALL)]
            
        m_corr = re.search(r'correctOptionIndex\s*=\s*(\d+)', b)
        corr = int(m_corr.group(1)) if m_corr else 0
        
        m_exp = re.search(r'explanation\s*=\s*\"(.*?)\",', b, re.DOTALL)
        exp = m_exp.group(1).strip() if m_exp else ''
        
        m_cat = re.search(r'category\s*=\s*SemiologyCategory\.([A-Z_]+)', b)
        cat_key = m_cat.group(1) if m_cat else 'GENERAL'
        cat_id, cat_name = cat_map.get(cat_key, ('general', 'Général'))
        
        if qid and qtext and options:
            all_questions.append({
                'id': qid,
                'q': qtext,
                'opts': options,
                'correct': corr,
                'exp': exp,
                'cat': cat_id,
                'categoryName': cat_name
            })

print(f"Parsed {len(all_questions)} quiz questions")

# 2. Parse Semiology Sheets
sheet_files = glob.glob('app/src/main/java/com/example/data/model/sheets/*.kt')
all_sheets = []

for fpath in sorted(sheet_files):
    content = open(fpath, 'r', encoding='utf-8').read()
    blocks = content.split('SemiologySheet(')[1:]
    for b in blocks:
        m_id = re.search(r'id\s*=\s*\"([^\"]+)\"', b)
        sid = m_id.group(1) if m_id else ''
        
        m_name = re.search(r'name\s*=\s*\"(.*?)\",', b, re.DOTALL)
        name = m_name.group(1).strip() if m_name else ''
        
        m_sub = re.search(r'subCategory\s*=\s*\"(.*?)\",', b, re.DOTALL)
        sub = m_sub.group(1).strip() if m_sub else ''
        
        m_def = re.search(r'definition\s*=\s*\"(.*?)\",', b, re.DOTALL)
        definition = m_def.group(1).strip() if m_def else ''
        
        m_phys = re.search(r'physiopathology\s*=\s*\"(.*?)\",', b, re.DOTALL)
        physio = m_phys.group(1).strip() if m_phys else ''
        
        m_search = re.search(r'searchMethod\s*=\s*\"(.*?)\",', b, re.DOTALL)
        search_method = m_search.group(1).strip() if m_search else ''
        
        m_ana = re.search(r'anamnesis\s*=\s*\"(.*?)\",', b, re.DOTALL)
        anamnesis = m_ana.group(1).strip() if m_ana else ''
        
        m_pe = re.search(r'physicalExam\s*=\s*\"(.*?)\",', b, re.DOTALL)
        physical_exam = m_pe.group(1).strip() if m_pe else ''
        
        m_par = re.search(r'complementaryExams\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
        paraclinical = [p.strip(' \n\r\t\"') for p in re.findall(r'\"(.*?)\"', m_par.group(1), re.DOTALL)] if m_par else []
        
        m_diff = re.search(r'differentialDiagnoses\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
        diff_diag = [d.strip(' \n\r\t\"') for d in re.findall(r'\"(.*?)\"', m_diff.group(1), re.DOTALL)] if m_diff else []
        
        m_eti = re.search(r'etiologies\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
        etiologies = [e.strip(' \n\r\t\"') for e in re.findall(r'\"(.*?)\"', m_eti.group(1), re.DOTALL)] if m_eti else []
        
        m_grav = re.search(r'gravitySigns\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
        gravity_signs = [g.strip(' \n\r\t\"') for g in re.findall(r'\"(.*?)\"', m_grav.group(1), re.DOTALL)] if m_grav else []
        
        m_kp = re.search(r'keyPoints\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
        key_points = [k.strip(' \n\r\t\"') for k in re.findall(r'\"(.*?)\"', m_kp.group(1), re.DOTALL)] if m_kp else []
        
        m_cat = re.search(r'category\s*=\s*SemiologyCategory\.([A-Z_]+)', b)
        cat_key = m_cat.group(1) if m_cat else 'GENERAL'
        cat_id, cat_name = cat_map.get(cat_key, ('general', 'Général'))
        
        if sid and name:
            all_sheets.append({
                'id': sid,
                'name': name,
                'subCategory': sub,
                'definition': definition,
                'physiopathology': physio,
                'searchMethod': search_method,
                'anamnesis': anamnesis,
                'physicalExam': physical_exam,
                'etiologies': etiologies,
                'gravitySigns': gravity_signs,
                'paraclinical': paraclinical,
                'differentialDiagnosis': diff_diag,
                'keyPoints': key_points,
                'cat': cat_id,
                'categoryName': cat_name
            })

print(f"Parsed {len(all_sheets)} semiology sheets")

# 3. Parse Clinical Training Data
ct_content = open('app/src/main/java/com/example/data/model/ClinicalTrainingData.kt', 'r', encoding='utf-8').read()

# 3A. Anamnesis Skills (14 items)
anamnesis_blocks = ct_content.split('AnamnesisSkill(')[1:]
anamnesis_skills = []
for b in anamnesis_blocks:
    m_id = re.search(r'id\s*=\s*(\d+)', b)
    sid = int(m_id.group(1)) if m_id else len(anamnesis_skills) + 1
    
    m_title = re.search(r'title\s*=\s*\"(.*?)\",', b, re.DOTALL)
    title = m_title.group(1).strip() if m_title else ''
    
    m_sub = re.search(r'subtitle\s*=\s*\"(.*?)\",', b, re.DOTALL)
    sub = m_sub.group(1).strip() if m_sub else ''
    
    m_exp = re.search(r'explanation\s*=\s*\"(.*?)\",', b, re.DOTALL)
    exp = m_exp.group(1).strip() if m_exp else ''
    
    m_phr = re.search(r'professionalPhrasings\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    phrasings = [p.strip(' \n\r\t\"') for p in re.findall(r'\"(.*?)\"', m_phr.group(1), re.DOTALL)] if m_phr else []
    
    m_err = re.search(r'errorsToAvoid\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    errors = [e.strip(' \n\r\t\"') for e in re.findall(r'\"(.*?)\"', m_err.group(1), re.DOTALL)] if m_err else []
    
    m_fol = re.search(r'followUpQuestions\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    follow_up = [f.strip(' \n\r\t\"') for f in re.findall(r'\"(.*?)\"', m_fol.group(1), re.DOTALL)] if m_fol else []
    
    m_eq = re.search(r'practicalExerciseQuestion\s*=\s*\"(.*?)\",', b, re.DOTALL)
    eq = m_eq.group(1).strip() if m_eq else ''
    
    m_ch = re.search(r'practicalExerciseChoices\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    choices = [c.strip(' \n\r\t\"') for c in re.findall(r'\"(.*?)\"', m_ch.group(1), re.DOTALL)] if m_ch else []
    
    m_ci = re.search(r'correctChoiceIndex\s*=\s*(\d+)', b)
    ci = int(m_ci.group(1)) if m_ci else 0
    
    m_ec = re.search(r'practicalExerciseCorrection\s*=\s*\"(.*?)\"', b, re.DOTALL)
    ec = m_ec.group(1).strip() if m_ec else ''
    
    if title:
        anamnesis_skills.append({
            'id': sid,
            'title': title,
            'subtitle': sub,
            'explanation': exp,
            'phrasings': phrasings,
            'errors': errors,
            'followUp': follow_up,
            'exerciseQuestion': eq,
            'exerciseChoices': choices,
            'correctChoice': ci,
            'exerciseCorrection': ec
        })

print(f"Parsed {len(anamnesis_skills)} anamnesis skills")

# 3B. Info Categories (9 items)
info_blocks = ct_content.split('InfoCategoryGuide(')[1:]
info_categories = []
for b in info_blocks:
    m_name = re.search(r'categoryName\s*=\s*\"(.*?)\",', b, re.DOTALL)
    name = m_name.group(1).strip() if m_name else ''
    
    m_desc = re.search(r'description\s*=\s*\"(.*?)\",', b, re.DOTALL)
    desc = m_desc.group(1).strip() if m_desc else ''
    
    m_inc = re.search(r'whatToInclude\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    include = [i.strip(' \n\r\t\"') for i in re.findall(r'\"(.*?)\"', m_inc.group(1), re.DOTALL)] if m_inc else []
    
    m_exc = re.search(r'whatToExclude\s*=\s*\"(.*?)\",', b, re.DOTALL)
    exclude = m_exc.group(1).strip() if m_exc else ''
    
    m_ex = re.search(r'hospitalExample\s*=\s*\"(.*?)\"', b, re.DOTALL)
    example = m_ex.group(1).strip() if m_ex else ''
    
    if name:
        info_categories.append({
            'categoryName': name,
            'description': desc,
            'whatToInclude': include,
            'whatToExclude': exclude,
            'hospitalExample': example
        })

print(f"Parsed {len(info_categories)} info categories")

# 3C. Observation Method Skills (5 items)
obs_blocks = ct_content.split('ObservationMethodSkill(')[1:]
obs_skills = []
for b in obs_blocks:
    m_t = re.search(r'title\s*=\s*\"(.*?)\",', b, re.DOTALL)
    t = m_t.group(1).strip() if m_t else ''
    
    m_obj = re.search(r'objective\s*=\s*\"(.*?)\",', b, re.DOTALL)
    obj = m_obj.group(1).strip() if m_obj else ''
    
    m_guide = re.search(r'detailedGuide\s*=\s*\"(.*?)\",', b, re.DOTALL)
    guide = m_guide.group(1).strip() if m_guide else ''
    
    m_kp = re.search(r'keyPoints\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    kp = [k.strip(' \n\r\t\"') for k in re.findall(r'\"(.*?)\"', m_kp.group(1), re.DOTALL)] if m_kp else []
    
    m_bad = re.search(r'badExample\s*=\s*\"(.*?)\",', b, re.DOTALL)
    bad = m_bad.group(1).strip() if m_bad else ''
    
    m_good = re.search(r'goodExample\s*=\s*\"(.*?)\",', b, re.DOTALL)
    good = m_good.group(1).strip() if m_good else ''
    
    m_prompt = re.search(r'exercisePrompt\s*=\s*\"(.*?)\",', b, re.DOTALL)
    prompt = m_prompt.group(1).strip() if m_prompt else ''
    
    m_corr = re.search(r'exerciseCorrection\s*=\s*\"(.*?)\"', b, re.DOTALL)
    corr = m_corr.group(1).strip() if m_corr else ''
    
    if t:
        obs_skills.append({
            'title': t,
            'objective': obj,
            'detailedGuide': guide,
            'keyPoints': kp,
            'badExample': bad,
            'goodExample': good,
            'exercisePrompt': prompt,
            'exerciseCorrection': corr
        })

print(f"Parsed {len(obs_skills)} observation skills")

# 3D. Synthesis Cases (3 items)
synth_blocks = ct_content.split('SynthesisCaseTraining(')[1:]
synth_cases = []
for b in synth_blocks:
    m_id = re.search(r'id\s*=\s*\"([^\"]+)\"', b)
    cid = m_id.group(1) if m_id else ''
    
    m_t = re.search(r'title\s*=\s*\"(.*?)\",', b, re.DOTALL)
    t = m_t.group(1).strip() if m_t else ''
    
    m_p = re.search(r'patientProfile\s*=\s*\"(.*?)\",', b, re.DOTALL)
    p = m_p.group(1).strip() if m_p else ''
    
    m_sym = re.search(r'symptomsCollected\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    sym = [s.strip(' \n\r\t\"') for s in re.findall(r'\"(.*?)\"', m_sym.group(1), re.DOTALL)] if m_sym else []
    
    m_signs = re.search(r'physicalSignsFound\s*=\s*listOf\s*\((.*?)\)\s*,', b, re.DOTALL)
    signs = [s.strip(' \n\r\t\"') for s in re.findall(r'\"(.*?)\"', m_signs.group(1), re.DOTALL)] if m_signs else []
    
    m_path = re.search(r'clinicalReasoningPath\s*=\s*\"(.*?)\",', b, re.DOTALL)
    path = m_path.group(1).strip() if m_path else ''
    
    m_hyp = re.search(r'primaryHypothesis\s*=\s*\"(.*?)\",', b, re.DOTALL)
    hyp = m_hyp.group(1).strip() if m_hyp else ''
    
    m_em = re.search(r'emergencyToRuleOut\s*=\s*\"(.*?)\",', b, re.DOTALL)
    em = m_em.group(1).strip() if m_em else ''
    
    m_act = re.search(r'immediateActionPlan\s*=\s*listOf\s*\((.*?)\)\s*', b, re.DOTALL)
    act = [a.strip(' \n\r\t\"') for a in re.findall(r'\"(.*?)\"', m_act.group(1), re.DOTALL)] if m_act else []
    
    if cid and t:
        synth_cases.append({
            'id': cid,
            'title': t,
            'patientProfile': p,
            'symptomsCollected': sym,
            'physicalSignsFound': signs,
            'clinicalReasoningPath': path,
            'primaryHypothesis': hyp,
            'emergencyToRuleOut': em,
            'immediateActionPlan': act
        })

print(f"Parsed {len(synth_cases)} synthesis cases")

# Generate docs/medical_data.js
output_js = f"""// Données Médicales Complètes EL VITA — Extraites du Référentiel Clinique Android
window.ALL_QUIZ_QUESTIONS = {json.dumps(all_questions, ensure_ascii=False, indent=2)};

window.ALL_SEMIOLOGY_SHEETS = {json.dumps(all_sheets, ensure_ascii=False, indent=2)};

window.ALL_ANAMNESIS_SKILLS = {json.dumps(anamnesis_skills, ensure_ascii=False, indent=2)};

window.ALL_INFO_CATEGORIES = {json.dumps(info_categories, ensure_ascii=False, indent=2)};

window.ALL_OBSERVATION_SKILLS = {json.dumps(obs_skills, ensure_ascii=False, indent=2)};

window.ALL_SYNTHESIS_CASES = {json.dumps(synth_cases, ensure_ascii=False, indent=2)};
"""

with open('docs/medical_data.js', 'w', encoding='utf-8') as f:
    f.write(output_js)

print("Saved docs/medical_data.js successfully!")
