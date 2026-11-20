package com.example.data.model

data class TransitionPhraseItem(
  val phrase: String,
  val context: String
)

data class TransitionSystemGroup(
  val systemName: String,
  val transitionPhrases: List<String>,
  val targetedQuestions: List<String>,
  val mistakesToAvoid: List<String>
)

data class TransitionSection(
  val id: String,
  val title: String,
  val subtitle: String,
  val objective: String,
  val recommendedPhrases: List<TransitionPhraseItem>,
  val phrasesToAvoid: List<String>,
  val systems: List<TransitionSystemGroup> = emptyList()
)

data class OralPresentationCase(
  val id: String,
  val title: String,
  val specialty: String,
  val durationTarget: String,
  val patientProfile: String,
  val badVersion: String,
  val referenceScript: String,
  val keyStrengths: List<String>,
  val seniorQuestions: List<Pair<String, String>>
)

data class OralEvaluationCriterion(
  val title: String,
  val description: String
)

object ClinicalTransitionsProvider {
  val transitionSections: List<TransitionSection> = transitionSectionsData
  val oralPresentationCases: List<OralPresentationCase> = oralPresentationCasesData
  val oralEvaluationCriteria: List<OralEvaluationCriterion> = oralEvaluationCriteriaData
}
