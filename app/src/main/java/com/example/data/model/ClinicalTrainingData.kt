package com.example.data.model

data class AnamnesisSkill(
  val id: Int,
  val title: String,
  val subtitle: String,
  val explanation: String,
  val professionalPhrasings: List<String>,
  val errorsToAvoid: List<String>,
  val patientResponsesExamples: List<Pair<String, String>>, // (Réponse possible du patient, Question de relance recommandée)
  val followUpQuestions: List<String>,
  val practicalExerciseQuestion: String,
  val practicalExerciseChoices: List<String>,
  val correctChoiceIndex: Int,
  val practicalExerciseCorrection: String
)

data class InfoCategoryGuide(
  val categoryName: String,
  val description: String,
  val whatToInclude: List<String>,
  val whatToExclude: String,
  val hospitalExample: String,
  val examples: List<String> = emptyList()
)

data class ClassificationExercise(
  val rawPatientQuote: String,
  val options: List<String>,
  val correctCategory: String,
  val explanation: String
)

data class ObservationMethodSkill(
  val title: String,
  val objective: String,
  val detailedGuide: String,
  val keyPoints: List<String>,
  val badExample: String,
  val goodExample: String,
  val exercisePrompt: String,
  val exerciseCorrection: String,
  val additionalExamples: List<Pair<String, String>> = emptyList()
)

data class SynthesisCaseTraining(
  val id: String,
  val title: String,
  val patientProfile: String,
  val symptomsCollected: List<String>,
  val physicalSignsFound: List<String>,
  val clinicalReasoningPath: String,
  val primaryHypothesis: String,
  val emergencyToRuleOut: String,
  val immediateActionPlan: List<String>
)

object ClinicalTrainingDataProvider {
  val anamnesisSkills: List<AnamnesisSkill> = anamnesisSkillsPart1 + anamnesisSkillsPart2
  val infoCategoriesGuide: List<InfoCategoryGuide> = infoCategoriesGuideList
  val classificationExercises: List<ClassificationExercise> = classificationExercisesList
  val observationSkills: List<ObservationMethodSkill> = observationSkillsList
  val synthesisCases: List<SynthesisCaseTraining> = synthesisCasesList
}
