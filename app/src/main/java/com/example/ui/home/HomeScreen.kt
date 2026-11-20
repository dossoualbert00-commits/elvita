package com.example.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.PreventionTopic
import com.example.ui.components.AlertLevelChip
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.NavyDark

@Composable
fun HomeScreen(
  viewModel: HomeViewModel,
  onNavigateToTab: (Int) -> Unit,
  onOpenDossiers: () -> Unit,
  onOpenProfile: () -> Unit,
  onOpenNewVitals: () -> Unit,
  onOpenNewObservation: () -> Unit,
  onOpenNewUpdate: () -> Unit,
  modifier: Modifier = Modifier
) {
  val state by viewModel.uiState.collectAsState()
  var selectedPreventionTopic by remember { mutableStateOf<PreventionTopic?>(null) }
  var showPreventionLibrary by remember { mutableStateOf(false) }

  if (selectedPreventionTopic != null) {
    BackHandler { selectedPreventionTopic = null }
    PreventionDetailDialog(
      topic = selectedPreventionTopic!!,
      onDismiss = { selectedPreventionTopic = null }
    )
  }

  if (showPreventionLibrary && selectedPreventionTopic == null) {
    BackHandler { showPreventionLibrary = false }
    PreventionLibraryDialog(
      topics = viewModel.preventionTopics,
      onSelectTopic = { topic ->
        showPreventionLibrary = false
        selectedPreventionTopic = topic
      },
      onDismiss = { showPreventionLibrary = false }
    )
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {
    // 1. HEADER SECTION
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(
            Brush.verticalGradient(
              listOf(NavyDark, MaterialTheme.colorScheme.primary)
            )
          )
          .padding(top = 16.dp, bottom = 24.dp, start = 20.dp, end = 20.dp)
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(42.dp)
                  .clip(RoundedCornerShape(10.dp))
                  .background(Color.White)
                  .padding(2.dp),
                contentAlignment = Alignment.Center
              ) {
                Image(
                  painter = painterResource(id = R.drawable.img_elvita_logo),
                  contentDescription = "Logo El Vita",
                  modifier = Modifier.size(36.dp),
                  contentScale = ContentScale.Fit
                )
              }
              Spacer(modifier = Modifier.width(12.dp))
              Column {
                Text(
                  text = "EL VITA",
                  style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.2.sp
                  ),
                  color = Color.White
                )
                Text(
                  text = "Compagnon Clinique & Pédagogique",
                  style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                  color = Color.White.copy(alpha = 0.8f)
                )
              }
            }

            Row {
              IconButton(
                onClick = onOpenDossiers,
                modifier = Modifier
                  .testTag("home_dossiers_button")
                  .size(40.dp)
                  .clip(CircleShape)
                  .background(Color.White.copy(alpha = 0.15f))
              ) {
                Icon(
                  imageVector = Icons.Default.Folder,
                  contentDescription = "Dossiers Patients",
                  tint = Color.White
                )
              }
              Spacer(modifier = Modifier.width(8.dp))
              IconButton(
                onClick = onOpenProfile,
                modifier = Modifier
                  .testTag("home_profile_button")
                  .size(40.dp)
                  .clip(CircleShape)
                  .background(Color.White.copy(alpha = 0.15f))
              ) {
                Icon(
                  imageVector = Icons.Default.Person,
                  contentDescription = "Profil Étudiant",
                  tint = Color.White
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(18.dp))

          // Greeting
          Text(
            text = "Bonjour, Confrère Externe",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
          )
          Text(
            text = "Prêt pour votre apprentissage clinique et le tour de salle d'aujourd'hui ?",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.85f)
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Legal disclaimer banner
          LegalDisclaimerBanner()
        }
      }
    }

    // 2. HERO CLINICAL BANNER
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp)
          .clip(RoundedCornerShape(16.dp))
          .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_med_banner),
          contentDescription = "Bannière apprentissage clinique",
          modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
          contentScale = ContentScale.Crop
        )
      }
    }

    // 3. PRÉVENTION MÉDICALE (CARROUSEL)
    item {
      Column(modifier = Modifier.padding(top = 8.dp)) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "A. Prévention Médicale & Vigilance",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
          )
          Text(
            text = "${viewModel.preventionTopics.size} Fiches Repères →",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .clickable { showPreventionLibrary = true }
              .padding(horizontal = 6.dp, vertical = 4.dp)
              .testTag("prevention_library_btn")
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          items(viewModel.preventionTopics) { topic ->
            PreventionCard(
              topic = topic,
              onClick = { selectedPreventionTopic = topic }
            )
          }
        }
      }
    }

    // 4. ACCÈS RAPIDE AUX MODULES
    item {
      Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
        Text(
          text = "C. Accès Rapide aux Modules",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          QuickActionCard(
            title = "Mesures",
            subtitle = "Constantes & Glasgow",
            icon = Icons.Default.Timeline,
            color = MedicalBlue,
            onClick = { onNavigateToTab(2) }, // Tab Mesures
            modifier = Modifier.weight(1f)
          )
          QuickActionCard(
            title = "Historique",
            subtitle = "Courbes & Évolution",
            icon = Icons.Default.BarChart,
            color = MedicalTeal,
            onClick = { onNavigateToTab(2) }, // Sub-history
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          QuickActionCard(
            title = "Clinique",
            subtitle = "Observations & Visites",
            icon = Icons.Default.Assignment,
            color = Color(0xFF6366F1),
            onClick = { onNavigateToTab(1) }, // Tab Clinique
            modifier = Modifier.weight(1f)
          )
          QuickActionCard(
            title = "Analyses",
            subtitle = "NFS, UECréat, Foie, Urines",
            icon = Icons.Default.Biotech,
            color = Color(0xFF0EA5E9),
            onClick = { onNavigateToTab(3) }, // Tab Analyses
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          QuickActionCard(
            title = "Sémiologie",
            subtitle = "Fiches & Quiz QCM",
            icon = Icons.Default.MenuBook,
            color = Color(0xFF8B5CF6),
            onClick = { onNavigateToTab(4) }, // Tab Sémiologie
            modifier = Modifier.weight(1f)
          )
          QuickActionCard(
            title = "Dossiers",
            subtitle = "${state.patientCount} patients suivis",
            icon = Icons.Default.Folder,
            color = NavyDark,
            onClick = onOpenDossiers,
            modifier = Modifier.weight(1f)
          )
        }
      }
    }

    // 5. DERNIÈRES CONSTANTES
    item {
      Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "B. Dernières Constantes Enregistrées",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
          )
          Text(
            text = "Nouvelle mesure",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
              .clickable { onOpenNewVitals() }
              .padding(4.dp)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (state.recentVitals.isEmpty()) {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "Aucune constante enregistrée pour le moment.\nTapez sur 'Nouvelle mesure' pour démarrer.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        } else {
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            state.recentVitals.take(4).forEach { vital ->
              RecentVitalItem(vital = vital)
            }
          }
        }
      }
    }

    // 6. ACTIVITÉ RÉCENTE
    item {
      Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
        Text(
          text = "D. Activité Récente de l'Étudiant",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))

        Card(
          modifier = Modifier.fillMaxWidth(),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
          Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            ActivityRow(
              icon = Icons.Default.Assignment,
              title = "Observations enregistrées",
              subtitle = "${state.observationCount} observations cliniques structurées dans vos dossiers",
              color = Color(0xFF6366F1)
            )
            ActivityRow(
              icon = Icons.Default.Timeline,
              title = "Mesures physiologiques",
              subtitle = "${state.vitalCount} constantes prises avec interprétation pédagogique",
              color = MedicalBlue
            )
            ActivityRow(
              icon = Icons.Default.CheckCircle,
              title = "Raisonnement clinique",
              subtitle = "Hypothèses syndromiques et fiches de sémiologie consultées",
              color = AlertGreen
            )
          }
        }
      }
    }
  }
}

@Composable
fun PreventionCard(
  topic: PreventionTopic,
  onClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .width(280.dp)
      .height(185.dp)
      .clickable { onClick() }
      .testTag("prevention_card_${topic.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(14.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.primaryContainer)
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = topic.tag,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
              ),
              color = MaterialTheme.colorScheme.onPrimaryContainer
            )
          }
          Icon(
            imageVector = Icons.Default.LocalHospital,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = topic.title,
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Text(
          text = topic.subtitle,
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = topic.keyPoints.firstOrNull() ?: "",
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp, lineHeight = 15.sp),
          color = MaterialTheme.colorScheme.onSurface,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f))
          .padding(horizontal = 8.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = topic.clinicalMessage,
          style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 10.5.sp,
            fontWeight = FontWeight.Medium
          ),
          color = MaterialTheme.colorScheme.onSecondaryContainer,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowForward,
          contentDescription = "Consulter",
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(14.dp)
        )
      }
    }
  }
}

@Composable
fun PreventionDetailDialog(
  topic: PreventionTopic,
  onDismiss: () -> Unit
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.94f)
        .fillMaxHeight(0.88f)
        .testTag("prevention_detail_modal"),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // En-tête
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
              Text(
                text = topic.tag,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = "Fiche Médicale de Prévention",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onPrimaryContainer
            )
          }

          IconButton(
            onClick = onDismiss,
            modifier = Modifier.size(36.dp).testTag("close_prevention_detail")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Fermer les détails"
            )
          }
        }

        // Contenu déroulant
        LazyColumn(
          modifier = Modifier
            .weight(1f)
            .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          item {
            Text(
              text = topic.title,
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
              ),
              color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = topic.subtitle,
              style = MaterialTheme.typography.bodyMedium.copy(
                color = MedicalBlue,
                fontWeight = FontWeight.SemiBold
              )
            )
          }

          // Message Clinique Réflexe
          item {
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = MedicalBlue.copy(alpha = 0.08f)),
              border = androidx.compose.foundation.BorderStroke(1.2.dp, MedicalBlue.copy(alpha = 0.35f))
            ) {
              Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.Top
              ) {
                Icon(
                  imageVector = Icons.Default.LocalHospital,
                  contentDescription = null,
                  tint = MedicalBlue,
                  modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Text(
                    text = "Message Clinique Réflexe",
                    style = MaterialTheme.typography.labelMedium.copy(
                      fontWeight = FontWeight.Bold,
                      color = MedicalBlue
                    )
                  )
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(
                    text = topic.clinicalMessage,
                    style = MaterialTheme.typography.bodyMedium.copy(
                      fontWeight = FontWeight.Bold,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  )
                }
              }
            }
          }

          // 1. Définition
          item {
            PreventionSectionCard(
              title = "1. Définition",
              body = topic.definition,
              accentColor = MaterialTheme.colorScheme.primary
            )
          }

          // 2. Facteurs de risque
          item {
            PreventionBulletSectionCard(
              title = "2. Facteurs de Risque",
              items = topic.riskFactors,
              accentColor = AlertOrange
            )
          }

          // 3. Population concernée (âge / sexe / contexte)
          item {
            PreventionSectionCard(
              title = "3. Population Concernée (Âge, Sexe & Contexte)",
              body = topic.targetPopulation,
              accentColor = MedicalTeal
            )
          }

          // 4. Prévention primaire
          item {
            PreventionBulletSectionCard(
              title = "4. Prévention Primaire",
              items = topic.preventionMeasures,
              accentColor = AlertGreen
            )
          }

          // 5. Dépistage recommandé
          item {
            PreventionSectionCard(
              title = "5. Dépistage Recommandé",
              body = topic.screeningRecommendations,
              accentColor = MedicalBlue
            )
          }

          // 6. Recommandations pratiques & Points clés
          item {
            PreventionBulletSectionCard(
              title = "6. Recommandations Pratiques & Points Clés",
              items = topic.practicalAdvice + topic.keyPoints,
              accentColor = AlertGreen
            )
          }

          // 7. Signes d'alerte
          item {
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = AlertRed.copy(alpha = 0.08f)),
              border = androidx.compose.foundation.BorderStroke(1.dp, AlertRed.copy(alpha = 0.35f)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                  text = "7. Signes d'Alerte & Drapeaux Rouges",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = AlertRed)
                )
                topic.warningSigns.forEach { sign ->
                  Row(verticalAlignment = Alignment.Top) {
                    Icon(
                      imageVector = Icons.Default.Warning,
                      contentDescription = null,
                      tint = AlertRed,
                      modifier = Modifier.size(16.dp).padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                      text = sign,
                      style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp, lineHeight = 19.sp),
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  }
                }
              }
            }
          }

          // Vigilance au lit du malade
          item {
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7)),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B))
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.Top
              ) {
                Icon(
                  imageVector = Icons.Default.Warning,
                  contentDescription = null,
                  tint = Color(0xFFB45309),
                  modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Text(
                    text = "Vigilance en Stage Hospitalier :",
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF92400E)
                    )
                  )
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = "Toute situation clinique inhabituelle ou tout signe de gravité doit être immédiatement partagé avec l'interne ou le médecin senior. L'étudiant ne doit jamais différer une alerte en cas d'instabilité.",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF78350F))
                  )
                }
              }
            }
          }
        }

        // Bouton de fermeture
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
          Button(
            onClick = onDismiss,
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .testTag("dismiss_prevention_detail"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
          ) {
            Text("Fermer la fiche", fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}

@Composable
private fun PreventionSectionCard(
  title: String,
  body: String,
  accentColor: Color
) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = accentColor)
      )
      Text(
        text = body,
        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp, lineHeight = 20.sp),
        color = MaterialTheme.colorScheme.onSurface
      )
    }
  }
}

@Composable
private fun PreventionBulletSectionCard(
  title: String,
  items: List<String>,
  accentColor: Color
) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = accentColor)
      )
      items.distinct().forEach { item ->
        Row(verticalAlignment = Alignment.Top) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = accentColor,
            modifier = Modifier.size(16.dp).padding(top = 2.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp, lineHeight = 19.sp),
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }
    }
  }
}

@Composable
fun PreventionLibraryDialog(
  topics: List<PreventionTopic>,
  onSelectTopic: (PreventionTopic) -> Unit,
  onDismiss: () -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedTag by remember { mutableStateOf<String?>(null) }
  val allTags = remember(topics) { topics.map { it.tag }.distinct() }
  val filtered = remember(topics, searchQuery, selectedTag) {
    topics.filter { topic ->
      val matchesTag = selectedTag == null || topic.tag == selectedTag
      val matchesSearch = searchQuery.isBlank() ||
        topic.title.contains(searchQuery, ignoreCase = true) ||
        topic.subtitle.contains(searchQuery, ignoreCase = true) ||
        topic.definition.contains(searchQuery, ignoreCase = true) ||
        topic.tag.contains(searchQuery, ignoreCase = true)
      matchesTag && matchesSearch
    }
  }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.95f)
        .fillMaxHeight(0.90f)
        .testTag("prevention_library_modal"),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Shield,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = "Prévention Médicale (${topics.size} fiches)",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
              )
              Text(
                text = "Dépistage, santé publique & vigilance clinique",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
          IconButton(onClick = onDismiss) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Fermer")
          }
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Rechercher un thème de prévention...") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth().testTag("prevention_search_input"),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
          )
          Spacer(modifier = Modifier.height(8.dp))
          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
              val isAll = selectedTag == null
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(16.dp))
                  .background(if (isAll) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                  .clickable { selectedTag = null }
                  .padding(horizontal = 12.dp, vertical = 6.dp)
              ) {
                Text(
                  text = "Tous (${topics.size})",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = if (isAll) Color.White else MaterialTheme.colorScheme.onSurface
                )
              }
            }
            items(allTags) { tag ->
              val isSelected = selectedTag == tag
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(16.dp))
                  .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                  .clickable { selectedTag = tag }
                  .padding(horizontal = 12.dp, vertical = 6.dp)
              ) {
                Text(
                  text = tag,
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                  color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                )
              }
            }
          }
        }

        LazyColumn(
          modifier = Modifier.weight(1f),
          contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          items(filtered, key = { it.id }) { topic ->
            Card(
              modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelectTopic(topic) }
                .testTag("prevention_library_item_${topic.id}"),
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
            ) {
              Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = topic.tag,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalBlue)
                  )
                  Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                  )
                }
                Text(
                  text = topic.title,
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = topic.subtitle,
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  maxLines = 2,
                  overflow = TextOverflow.Ellipsis
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun QuickActionCard(
  title: String,
  subtitle: String,
  icon: ImageVector,
  color: Color,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .height(84.dp)
      .clickable { onClick() },
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(color.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center
      ) {
        Icon(imageVector = icon, contentDescription = title, tint = color, modifier = Modifier.size(24.dp))
      }
      Spacer(modifier = Modifier.width(10.dp))
      Column {
        Text(
          text = title,
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = subtitle,
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}

@Composable
fun RecentVitalItem(vital: com.example.data.local.entities.VitalEntity) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 14.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = vital.formattedDateTime.ifBlank { "Mesure récente" },
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          if (vital.temperature != null) {
            Text(
              text = "T°: ${vital.temperature}°C",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
            )
          }
          if (vital.systolicBp != null && vital.diastolicBp != null) {
            Text(
              text = "PA: ${vital.systolicBp}/${vital.diastolicBp}",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
            )
          }
          if (vital.heartRate != null) {
            Text(
              text = "FC: ${vital.heartRate} bpm",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
            )
          }
          if (vital.oxygenSaturation != null) {
            Text(
              text = "SpO2: ${vital.oxygenSaturation}%",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
            )
          }
        }
        if (vital.pedagogicalNote.isNotBlank()) {
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = vital.pedagogicalNote,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }
      Spacer(modifier = Modifier.width(8.dp))
      AlertLevelChip(alertLevel = vital.alertLevel)
    }
  }
}

@Composable
fun ActivityRow(icon: ImageVector, title: String, subtitle: String, color: Color) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Box(
      modifier = Modifier
        .size(36.dp)
        .clip(CircleShape)
        .background(color.copy(alpha = 0.12f)),
      contentAlignment = Alignment.Center
    ) {
      Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
    }
    Spacer(modifier = Modifier.width(12.dp))
    Column {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}
