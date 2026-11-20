package com.example.ui.clinical

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ClinicalTransitionsProvider
import com.example.data.model.OralPresentationCase
import com.example.data.model.TransitionSection
import com.example.data.model.TransitionSystemGroup
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal

// ══════════════════════════════════════════════════════════════════════════════
// MODULE E — PHRASES DE TRANSITION & CONDUITE DE L'ENTRETIEN (5.1 à 5.11)
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun TransitionsTrainingTab() {
  val sections = ClinicalTransitionsProvider.transitionSections

  Column(
    modifier = Modifier.testTag("transitions_training_tab"),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.SwapHoriz,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(20.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Phrases de Transition & Conduite de l'Entretien (5.1 – 5.11)",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.primary
      )
    }

    Text(
      text = "Formulations prêtes à l'emploi pour enchaîner avec naturel et humanité chaque étape de l'interrogatoire, la revue des 10 appareils, l'examen physique et le passage au médecin.",
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    sections.forEach { section ->
      TransitionSectionCard(section = section)
    }
  }
}

@Composable
private fun TransitionSectionCard(section: TransitionSection) {
  var isExpanded by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("transition_section_${section.id}"),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.48f)),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { isExpanded = !isExpanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = section.title,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = section.subtitle,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MedicalBlue)
          )
        }
        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = "Objectif : ${section.objective}",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium, lineHeight = 17.sp),
            color = MaterialTheme.colorScheme.onSurface
          )

          // Formulations recommandées
          Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MedicalBlue.copy(alpha = 0.08f))
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "💬 Formulations recommandées (${section.recommendedPhrases.size}) :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalBlue)
              )
              section.recommendedPhrases.forEach { item ->
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                  Text(
                    text = item.phrase,
                    style = MaterialTheme.typography.bodySmall.copy(
                      fontStyle = FontStyle.Italic,
                      fontSize = 11.5.sp,
                      fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  Text(
                    text = "↳ Contexte : ${item.context}",
                    style = MaterialTheme.typography.bodySmall.copy(
                      fontSize = 10.5.sp,
                      color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                  )
                }
              }
            }
          }

          // Revue détaillée des 10 appareils pour 5.6
          if (section.systems.isNotEmpty()) {
            Card(
              shape = RoundedCornerShape(8.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
              border = BorderStroke(1.dp, MedicalTeal.copy(alpha = 0.35f))
            ) {
              Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                  text = "🩺 Revue des 10 appareils (Transitions & Questions clés) :",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
                )
                section.systems.forEach { sys ->
                  TransitionSystemSubCard(system = sys)
                }
              }
            }
          }

          // Formulations à éviter
          Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = AlertRed.copy(alpha = 0.08f))
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "⛔ Formulations à éviter :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AlertRed)
              )
              section.phrasesToAvoid.forEach { err ->
                Row(verticalAlignment = Alignment.Top) {
                  Text("• ", color = AlertRed, fontWeight = FontWeight.Bold)
                  Text(text = err, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun TransitionSystemSubCard(system: TransitionSystemGroup) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
  ) {
    Column(modifier = Modifier.padding(8.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = system.systemName,
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
          color = MedicalTeal,
          modifier = Modifier.weight(1f)
        )
        Icon(
          imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = MedicalTeal,
          modifier = Modifier.size(18.dp)
        )
      }

      AnimatedVisibility(visible = expanded) {
        Column(
          modifier = Modifier.padding(top = 6.dp),
          verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
          Text(
            text = "Transitions d'ouverture (${system.transitionPhrases.size}) :",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalBlue)
          )
          system.transitionPhrases.forEach { phrase ->
            Text(
              text = "• $phrase",
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, fontStyle = FontStyle.Italic)
            )
          }

          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Questions ciblées prioritaires :",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
          )
          system.targetedQuestions.forEach { q ->
            Text(
              text = "→ $q",
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp)
            )
          }

          if (system.mistakesToAvoid.isNotEmpty()) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "Pièges fréquents :",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AlertRed)
            )
            system.mistakesToAvoid.forEach { m ->
              Text(
                text = "⛔ $m",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.5.sp, color = AlertRed)
              )
            }
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════════════════════════════
// MODULE F — PRÉSENTATION ORALE AU MÉDECIN SENIOR / STAFF (MODÈLES & GRILLE)
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun OralPresentationTab() {
  val criteria = ClinicalTransitionsProvider.oralEvaluationCriteria
  val cases = ClinicalTransitionsProvider.oralPresentationCases

  Column(
    modifier = Modifier.testTag("oral_presentation_tab"),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.RecordVoiceOver,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(20.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Présentation Orale Structurée au Médecin (60–90 sec)",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.primary
      )
    }

    Text(
      text = "Comparez une restitution mal structurée à un script de référence mot-à-mot pour présenter vos patients avec rigueur lors du tour de salle ou des urgences.",
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    // Grille d'auto-évaluation en 8 points
    Card(
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(containerColor = MedicalBlue.copy(alpha = 0.08f)),
      border = BorderStroke(1.dp, MedicalBlue.copy(alpha = 0.35f))
    ) {
      Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
          text = "Grille d'Auto-Évaluation du Staff (8 Critères d'Excellence) :",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MedicalBlue)
        )
        criteria.forEach { c ->
          Column(modifier = Modifier.padding(vertical = 2.dp)) {
            Text(
              text = "✓ ${c.title}",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, fontSize = 11.5.sp, color = MaterialTheme.colorScheme.onSurface)
            )
            Text(
              text = c.description,
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
          }
        }
      }
    }

    Text(
      text = "5 Cas Modèles Comparatifs (cas fictifs pédagogiques) :",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
    )

    cases.forEach { oralCase ->
      OralPresentationCaseCard(oralCase = oralCase)
    }
  }
}

@Composable
private fun OralPresentationCaseCard(oralCase: OralPresentationCase) {
  var isExpanded by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("oral_case_${oralCase.id}"),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { isExpanded = !isExpanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "${oralCase.specialty} • ${oralCase.durationTarget}",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
          )
          Text(
            text = oralCase.title,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = oralCase.patientProfile,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MedicalBlue)
          )
        }
        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          // Version mal structurée à éviter
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(AlertRed.copy(alpha = 0.09f))
              .padding(10.dp)
          ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = AlertRed, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "Version mal structurée (À éviter) :",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AlertRed)
                )
              }
              Text(
                text = oralCase.badVersion,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, fontStyle = FontStyle.Italic)
              )
            }
          }

          // Version de référence mot-à-mot
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(AlertGreen.copy(alpha = 0.1f))
              .padding(10.dp)
          ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = AlertGreen, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "Version Structurée de Référence (mot-à-mot en ${oralCase.durationTarget}) :",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AlertGreen)
                )
              }
              Text(
                text = oralCase.referenceScript,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp, lineHeight = 17.sp)
              )
            }
          }

          // Points forts commentés
          Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
              Text(
                text = "Pourquoi cette présentation est excellente :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalBlue)
              )
              oralCase.keyStrengths.forEach { s ->
                Text(text = "• $s", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
              }
            }
          }

          // Questions probables du médecin senior
          Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = AlertOrange.copy(alpha = 0.08f))
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "🎓 Questions probables du médecin senior & Réponses modèles :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AlertOrange)
              )
              oralCase.seniorQuestions.forEach { (q, a) ->
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                  Text(
                    text = "Senior : $q",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold)
                  )
                  Text(
                    text = "→ Réponse : $a",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}
