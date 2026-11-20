package com.example.data.model

data class SemiologySheet(
  val id: String,
  val name: String,
  val category: SemiologyCategory,
  val subCategory: String = "Signe clinique", // "Signes fonctionnels", "Signes physiques", "Syndromes", "Lésions élémentaires"

  // 14 Rubriques obligatoires
  val definition: String,                               // 1. Définition
  val physiopathology: String,                          // 2. Physiopathologie et mécanisme
  val etiologies: List<String>,                         // 3. Étiologies principales
  val circumstances: String,                            // 4. Circonstances d'apparition
  val anamnesis: String,                                // 5. Interrogatoire
  val searchMethod: String,                             // 6. Méthode de recherche
  val physicalExam: String,                             // 7. Examen clinique
  val associatedSigns: List<String>,                    // 8. Signes associés
  val clinicalSignificance: String,                     // 9. Signification clinique
  val differentialDiagnoses: List<String>,              // 10. Diagnostics différentiels
  val complementaryExams: List<String>,                 // 11. Examens complémentaires utiles
  val gravitySigns: List<String>,                       // 12. Complications ou signes de gravité
  val pitfallsAndLimits: String,                        // 13. Limites et pièges diagnostiques
  val keyPoints: List<String>                           // 14. Points essentiels à retenir
)

enum class SemiologyCategory(val label: String, val iconName: String, val isSpecialty: Boolean = true) {
  CARDIOVASCULAR("Cardiovasculaire", "favorite"),
  RESPIRATORY("Pleuropulmonaire / Respiratoire", "air"),
  DIGESTIVE("Digestif", "restaurant"),
  NEUROLOGICAL("Neurologique", "psychology"),
  URINARY("Néphrologie & Urologie", "water_drop"),
  ENDOCRINOLOGY("Endocrinologie & Métabolisme", "science"),
  RHEUMATOLOGY("Rhumatologie", "accessibility_new"),
  DERMATOLOGY("Dermatologie", "healing"),
  HEMATOLOGY("Hématologie", "bloodtype"),
  INFECTIOUS("Infectiologie", "coronavirus"),
  ENT("ORL", "hearing"),
  OPHTHALMOLOGY("Ophtalmologie", "visibility"),
  GYNECOLOGY_OBSTETRICS("Gynécologie-Obstétrique", "pregnant_woman"),
  GENERAL("Sémiologie Générale", "medical_services", isSpecialty = false)
}

data class QuizQuestion(
  val id: String,
  val category: SemiologyCategory,
  val question: String,
  val options: List<String>, // Exactly 4 options (A, B, C, D)
  val correctOptionIndex: Int, // 0..3
  val explanation: String,
  val wrongOptionsExplanations: List<String> = emptyList(), // Why other propositions are incorrect
  val clinicalPearl: String = ""
)
