package com.example.ui.labs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.medical.BiologicalInterpretation
import com.example.ui.components.AlertLevelChip
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabsScreen(
  viewModel: LabsViewModel,
  initialSubTab: Int = 0,
  modifier: Modifier = Modifier
) {
  var currentTab by remember(initialSubTab) { mutableIntStateOf(initialSubTab.coerceIn(0, 5)) }
  val patients by viewModel.patients.collectAsState()
  val selectedPatientId by viewModel.selectedPatientId.collectAsState()
  val nfsState by viewModel.nfsState.collectAsState()
  val ueCreatState by viewModel.ueCreatState.collectAsState()
  val hepaticState by viewModel.hepaticState.collectAsState()
  val inflamCoagState by viewModel.inflamCoagState.collectAsState()
  val urineState by viewModel.urineState.collectAsState()
  val filteredLabResults by viewModel.filteredLabResults.collectAsState()

  val selectedPatient = patients.find { it.id == selectedPatientId }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Header
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)
        .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
      Column {
        Text(
          text = "Analyses Biologiques",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = Color.White
        )
        Text(
          text = "NFS complète, Ionogramme, Foie, Hémostase & Urines",
          style = MaterialTheme.typography.bodySmall,
          color = Color.White.copy(alpha = 0.85f)
        )
      }
    }

    // Patient Dossier Selector Bar for Centralized Storage
    Surface(
      color = MaterialTheme.colorScheme.surface,
      tonalElevation = 1.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        var patExpanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
          expanded = patExpanded,
          onExpandedChange = { patExpanded = !patExpanded }
        ) {
          OutlinedTextField(
            value = selectedPatient?.let { "Dossier : ${it.fullNameOrAlias} (${it.department})" }
              ?: "Sélectionner un dossier patient (requis pour enregistrer)",
            onValueChange = {},
            readOnly = true,
            label = { Text("Dossier patient associé") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = patExpanded) },
            modifier = Modifier
              .menuAnchor()
              .fillMaxWidth()
              .testTag("labs_patient_selector"),
            shape = RoundedCornerShape(10.dp),
            singleLine = true
          )
          ExposedDropdownMenu(
            expanded = patExpanded,
            onDismissRequest = { patExpanded = false }
          ) {
            DropdownMenuItem(
              text = { Text("Tous les dossiers (lecture seule)") },
              onClick = {
                viewModel.onPatientSelected(null)
                patExpanded = false
              }
            )
            patients.forEach { p ->
              DropdownMenuItem(
                text = { Text("${p.fullNameOrAlias} — ${p.department} (${p.roomNumber})") },
                onClick = {
                  viewModel.onPatientSelected(p.id)
                  patExpanded = false
                }
              )
            }
          }
        }
      }
    }

    ScrollableTabRow(
      selectedTabIndex = currentTab,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.primary,
      edgePadding = 12.dp
    ) {
      Tab(
        selected = currentTab == 0,
        onClick = { currentTab = 0 },
        text = { Text("A. NFS Complète", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = currentTab == 1,
        onClick = { currentTab = 1 },
        text = { Text("B. UECréat / Rein", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = currentTab == 2,
        onClick = { currentTab = 2 },
        text = { Text("C. Bilan Hépatique", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = currentTab == 3,
        onClick = { currentTab = 3 },
        text = { Text("D. Inflammation & Hémostase", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = currentTab == 4,
        onClick = { currentTab = 4 },
        text = { Text("E. Bandelette Urinaire", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = currentTab == 5,
        onClick = { currentTab = 5 },
        text = { Text("F. Historique (${filteredLabResults.size})", fontWeight = FontWeight.SemiBold) }
      )
    }

    when (currentTab) {
      0 -> NfsSection(viewModel, nfsState, selectedPatientId != null)
      1 -> UeCreatSection(viewModel, ueCreatState, selectedPatientId != null)
      2 -> HepaticSection(viewModel, hepaticState, selectedPatientId != null)
      3 -> InflamCoagSection(viewModel, inflamCoagState, selectedPatientId != null)
      4 -> UrineSection(viewModel, urineState, selectedPatientId != null)
      5 -> LabsHistorySection(
        results = filteredLabResults,
        patients = patients,
        selectedPatientName = selectedPatient?.fullNameOrAlias,
        onDelete = { viewModel.deleteLabResult(it) }
      )
    }
  }
}

// ══════════════════════════════════════════════════════
// A. SECTION NFS ENRICHIE
// ══════════════════════════════════════════════════════
@Composable
fun NfsSection(viewModel: LabsViewModel, state: NfsFormState, hasPatientSelected: Boolean) {
  val context = LocalContext.current

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item { LegalDisclaimerBanner() }

    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "1. Lignée Érythrocytaire (Rouge)",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.hb,
              onValueChange = { viewModel.updateNfsField(hb = it) },
              label = { Text("Hb (g/dL)") },
              placeholder = { Text("12 - 16") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f).testTag("nfs_hb"),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.ht,
              onValueChange = { viewModel.updateNfsField(ht = it) },
              label = { Text("Ht (%)") },
              placeholder = { Text("38 - 50") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.gr,
              onValueChange = { viewModel.updateNfsField(gr = it) },
              label = { Text("GR (T/L)") },
              placeholder = { Text("4.2 - 5.6") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.retic,
              onValueChange = { viewModel.updateNfsField(retic = it) },
              label = { Text("Réticulocytes (G/L)") },
              placeholder = { Text("20 - 120 G/L") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.rdw,
              onValueChange = { viewModel.updateNfsField(rdw = it) },
              label = { Text("RDW / IDR (%)") },
              placeholder = { Text("11.5 - 14.5 %") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = "2. Lignée Leucocytaire (Blanche) & Formule",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.gb,
              onValueChange = { viewModel.updateNfsField(gb = it) },
              label = { Text("GB Total (G/L)") },
              placeholder = { Text("4.0 - 10.0") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f).testTag("nfs_gb"),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.pnn,
              onValueChange = { viewModel.updateNfsField(pnn = it) },
              label = { Text("PNN (G/L)") },
              placeholder = { Text("1.5 - 7.0") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.lympho,
              onValueChange = { viewModel.updateNfsField(lympho = it) },
              label = { Text("Lympho (G/L)") },
              placeholder = { Text("1.5 - 4.0") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.mono,
              onValueChange = { viewModel.updateNfsField(mono = it) },
              label = { Text("Mono (G/L)") },
              placeholder = { Text("0.2 - 1.0") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.eosino,
              onValueChange = { viewModel.updateNfsField(eosino = it) },
              label = { Text("Éosino (G/L)") },
              placeholder = { Text("0.05 - 0.5") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.baso,
              onValueChange = { viewModel.updateNfsField(baso = it) },
              label = { Text("Baso (G/L)") },
              placeholder = { Text("< 0.1") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = "3. Lignée Thrombocytaire (Plaquettes)",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.plt,
              onValueChange = { viewModel.updateNfsField(plt = it) },
              label = { Text("Plaquettes (G/L)") },
              placeholder = { Text("150 - 400 G/L") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f).testTag("nfs_plt"),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.vpm,
              onValueChange = { viewModel.updateNfsField(vpm = it) },
              label = { Text("VPM (fL)") },
              placeholder = { Text("7.0 - 11.0 fL") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }
        }
      }
    }

    state.interpretation?.let { interp ->
      item {
        NfsDualLevelResultCard(
          interp = interp,
          hasPatientSelected = hasPatientSelected
        ) {
          viewModel.saveResult(
            type = "NFS",
            summary = "NFS : ${interp.title}",
            interpretation = buildString {
              append(interp.professionalMedicalConclusion.ifBlank { interp.generalConclusion })
              if (interp.details.isNotEmpty()) {
                append("\n\n")
                append(interp.details.joinToString("\n"))
              }
            },
            alertLevel = interp.alertLevel,
            valuesJson = "Hb ${state.hb} g/dL, GB ${state.gb} G/L, PNN ${state.pnn} G/L, Plq ${state.plt} G/L",
            onPatientMissing = {
              Toast.makeText(context, "Sélectionnez d'abord un dossier patient en haut de l'écran.", Toast.LENGTH_SHORT).show()
            }
          ) {
            Toast.makeText(context, "Analyse NFS enregistrée dans le dossier patient !", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════
// B. SECTION UECRÉAT & REIN
// ══════════════════════════════════════════════════════
@Composable
fun UeCreatSection(viewModel: LabsViewModel, state: UeCreatFormState, hasPatientSelected: Boolean) {
  val context = LocalContext.current

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item { LegalDisclaimerBanner() }

    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "Ionogramme Sanguin & Bilan Rénal",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
              value = state.urea,
              onValueChange = { viewModel.updateUeCreatField(urea = it) },
              label = { Text("Urée (mmol/L)") },
              placeholder = { Text("2.5 - 7.5") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f).testTag("ue_urea"),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.creat,
              onValueChange = { viewModel.updateUeCreatField(creat = it) },
              label = { Text("Créatinine (µmol/L)") },
              placeholder = { Text("60 - 110") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f).testTag("ue_creat"),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
              value = state.na,
              onValueChange = { viewModel.updateUeCreatField(na = it) },
              label = { Text("Sodium Na+ (mmol/L)") },
              placeholder = { Text("135 - 145") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f).testTag("ue_na"),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.k,
              onValueChange = { viewModel.updateUeCreatField(k = it) },
              label = { Text("Potassium K+ (mmol/L)") },
              placeholder = { Text("3.5 - 5.0") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f).testTag("ue_k"),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
              value = state.cl,
              onValueChange = { viewModel.updateUeCreatField(cl = it) },
              label = { Text("Chlore Cl- (mmol/L)") },
              placeholder = { Text("98 - 106") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.hco3,
              onValueChange = { viewModel.updateUeCreatField(hco3 = it) },
              label = { Text("Bicarbonates (mmol/L)") },
              placeholder = { Text("22 - 28") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
              value = state.age,
              onValueChange = { viewModel.updateUeCreatField(age = it) },
              label = { Text("Âge patient (ans)") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.weight,
              onValueChange = { viewModel.updateUeCreatField(weight = it) },
              label = { Text("Poids (kg)") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }
        }
      }
    }

    state.interpretation?.let { interp ->
      item {
        InterpretationResultCard(interp = interp, hasPatientSelected = hasPatientSelected) {
          viewModel.saveResult(
            type = "UE_CREAT",
            summary = "Ionogramme & Rein : ${interp.title}",
            interpretation = buildString {
              append(interp.generalConclusion.ifBlank { interp.summary })
              if (interp.details.isNotEmpty()) {
                append("\n\n")
                append(interp.details.joinToString("\n"))
              }
            },
            alertLevel = interp.alertLevel,
            valuesJson = "Urée ${state.urea} mmol/L, Créat ${state.creat} µmol/L, Na+ ${state.na}, K+ ${state.k} mmol/L",
            onPatientMissing = {
              Toast.makeText(context, "Sélectionnez d'abord un dossier patient en haut de l'écran.", Toast.LENGTH_SHORT).show()
            }
          ) {
            Toast.makeText(context, "Bilan UECréat enregistré dans le dossier patient !", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════
// C. SECTION BILAN HÉPATIQUE & PANCRÉAS
// ══════════════════════════════════════════════════════
@Composable
fun HepaticSection(viewModel: LabsViewModel, state: HepaticFormState, hasPatientSelected: Boolean) {
  val context = LocalContext.current

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item { LegalDisclaimerBanner() }

    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "Bilan Hépatique & Pancréatique",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.alat,
              onValueChange = { viewModel.updateHepaticField(alat = it) },
              label = { Text("ALAT / TGP (UI/L)") },
              placeholder = { Text("< 45") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.asat,
              onValueChange = { viewModel.updateHepaticField(asat = it) },
              label = { Text("ASAT / TGO (UI/L)") },
              placeholder = { Text("< 35") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.pal,
              onValueChange = { viewModel.updateHepaticField(pal = it) },
              label = { Text("PAL (UI/L)") },
              placeholder = { Text("40 - 130") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.ggt,
              onValueChange = { viewModel.updateHepaticField(ggt = it) },
              label = { Text("GGT (UI/L)") },
              placeholder = { Text("< 50") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.biliTotal,
              onValueChange = { viewModel.updateHepaticField(biliTotal = it) },
              label = { Text("Bilirubine Totale") },
              placeholder = { Text("5 - 21 µmol/L") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.biliConj,
              onValueChange = { viewModel.updateHepaticField(biliConj = it) },
              label = { Text("Bili Conjuguée") },
              placeholder = { Text("< 5 µmol/L") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          OutlinedTextField(
            value = state.lipase,
            onValueChange = { viewModel.updateHepaticField(lipase = it) },
            label = { Text("Lipasémie (UI/L)") },
            placeholder = { Text("Norme < 60 UI/L (> 3N dans la pancréatite)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            singleLine = true
          )
        }
      }
    }

    state.interpretation?.let { interp ->
      item {
        InterpretationResultCard(interp = interp, hasPatientSelected = hasPatientSelected) {
          viewModel.saveResult(
            type = "HEPATIC",
            summary = "Bilan Hépatique : ${interp.title}",
            interpretation = buildString {
              append(interp.generalConclusion.ifBlank { interp.summary })
              if (interp.details.isNotEmpty()) {
                append("\n\n")
                append(interp.details.joinToString("\n"))
              }
            },
            alertLevel = interp.alertLevel,
            valuesJson = "ALAT ${state.alat}, ASAT ${state.asat}, PAL ${state.pal}, GGT ${state.ggt}, BiliT ${state.biliTotal}, Lipase ${state.lipase}",
            onPatientMissing = {
              Toast.makeText(context, "Sélectionnez d'abord un dossier patient en haut de l'écran.", Toast.LENGTH_SHORT).show()
            }
          ) {
            Toast.makeText(context, "Bilan hépatique enregistré dans le dossier patient !", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════
// D. SECTION INFLAMMATION & HÉMOSTASE
// ══════════════════════════════════════════════════════
@Composable
fun InflamCoagSection(viewModel: LabsViewModel, state: InflamCoagFormState, hasPatientSelected: Boolean) {
  val context = LocalContext.current

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item { LegalDisclaimerBanner() }

    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "Bilan Inflammatoire & Coagulation",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.crp,
              onValueChange = { viewModel.updateInflamCoagField(crp = it) },
              label = { Text("CRP (mg/L)") },
              placeholder = { Text("< 5 mg/L") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.vs,
              onValueChange = { viewModel.updateInflamCoagField(vs = it) },
              label = { Text("VS (mm 1ère h)") },
              placeholder = { Text("< 20") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.pct,
              onValueChange = { viewModel.updateInflamCoagField(pct = it) },
              label = { Text("Procalcitonine (ng/mL)") },
              placeholder = { Text("< 0.1 (sepsis si > 0.5)") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.dDimeres,
              onValueChange = { viewModel.updateInflamCoagField(dDimeres = it) },
              label = { Text("D-Dimères (ng/mL)") },
              placeholder = { Text("< 500") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.tp,
              onValueChange = { viewModel.updateInflamCoagField(tp = it) },
              label = { Text("Taux Prothrombine (%)") },
              placeholder = { Text("70 - 100%") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.inr,
              onValueChange = { viewModel.updateInflamCoagField(inr = it) },
              label = { Text("INR") },
              placeholder = { Text("0.8 - 1.2") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = state.tcaRatio,
              onValueChange = { viewModel.updateInflamCoagField(tcaRatio = it) },
              label = { Text("TCA (Ratio Patient/Témoin)") },
              placeholder = { Text("0.8 - 1.2") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
            OutlinedTextField(
              value = state.fibrinogene,
              onValueChange = { viewModel.updateInflamCoagField(fibrinogene = it) },
              label = { Text("Fibrinogène (g/L)") },
              placeholder = { Text("2.0 - 4.0") },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              singleLine = true
            )
          }
        }
      }
    }

    state.interpretation?.let { interp ->
      item {
        InterpretationResultCard(interp = interp, hasPatientSelected = hasPatientSelected) {
          viewModel.saveResult(
            type = "INFLAM_COAG",
            summary = "Inflammation/Hémostase : ${interp.title}",
            interpretation = buildString {
              append(interp.generalConclusion.ifBlank { interp.summary })
              if (interp.details.isNotEmpty()) {
                append("\n\n")
                append(interp.details.joinToString("\n"))
              }
            },
            alertLevel = interp.alertLevel,
            valuesJson = "CRP ${state.crp} mg/L, PCT ${state.pct}, TP ${state.tp}%, INR ${state.inr}, D-Dimères ${state.dDimeres}",
            onPatientMissing = {
              Toast.makeText(context, "Sélectionnez d'abord un dossier patient en haut de l'écran.", Toast.LENGTH_SHORT).show()
            }
          ) {
            Toast.makeText(context, "Bilan d'hémostase et inflammation enregistré !", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════
// E. SECTION BANDELETTE URINAIRE & ECBU
// ══════════════════════════════════════════════════════
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrineSection(viewModel: LabsViewModel, state: UrineDipstickFormState, hasPatientSelected: Boolean) {
  val context = LocalContext.current

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item { LegalDisclaimerBanner() }

    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Text(
            text = "Bandelette Urinaire & ECBU",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          DropdownSelector(
            label = "Leucocytes urinaires",
            selected = state.leucocytes,
            options = listOf("Négatif", "Trace", "1+", "2+", "3+"),
            onSelect = { viewModel.updateUrineField(leucocytes = it) }
          )

          DropdownSelector(
            label = "Nitrites",
            selected = state.nitrites,
            options = listOf("Négatif", "Positif"),
            onSelect = { viewModel.updateUrineField(nitrites = it) }
          )

          DropdownSelector(
            label = "Protéinurie",
            selected = state.proteinurie,
            options = listOf("Négatif", "Trace", "1+ (0.3 g/L)", "2+ (1.0 g/L)", "3+ (>= 3.0 g/L)"),
            onSelect = { viewModel.updateUrineField(proteinurie = it) }
          )

          DropdownSelector(
            label = "Hématurie / Sang",
            selected = state.hematurie,
            options = listOf("Négatif", "Trace", "1+", "2+", "3+"),
            onSelect = { viewModel.updateUrineField(hematurie = it) }
          )

          DropdownSelector(
            label = "Cétonurie",
            selected = state.cetones,
            options = listOf("Négatif", "Trace", "1+", "2+", "3+"),
            onSelect = { viewModel.updateUrineField(cetones = it) }
          )
        }
      }
    }

    state.interpretation?.let { interp ->
      item {
        InterpretationResultCard(interp = interp, hasPatientSelected = hasPatientSelected) {
          viewModel.saveResult(
            type = "URINE",
            summary = "Bandelette Urinaire : ${interp.title}",
            interpretation = buildString {
              append(interp.generalConclusion.ifBlank { interp.summary })
              if (interp.details.isNotEmpty()) {
                append("\n\n")
                append(interp.details.joinToString("\n"))
              }
            },
            alertLevel = interp.alertLevel,
            valuesJson = "Leuco ${state.leucocytes}, Nitrites ${state.nitrites}, Prot ${state.proteinurie}, Sang ${state.hematurie}",
            onPatientMissing = {
              Toast.makeText(context, "Sélectionnez d'abord un dossier patient en haut de l'écran.", Toast.LENGTH_SHORT).show()
            }
          ) {
            Toast.makeText(context, "Résultats urinaires enregistrés dans le dossier patient !", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(
  label: String,
  selected: String,
  options: List<String>,
  onSelect: (String) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded },
    modifier = Modifier.fillMaxWidth()
  ) {
    OutlinedTextField(
      value = selected,
      onValueChange = {},
      readOnly = true,
      label = { Text(label) },
      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
      modifier = Modifier.fillMaxWidth().menuAnchor(),
      shape = RoundedCornerShape(10.dp)
    )
    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      options.forEach { option ->
        DropdownMenuItem(
          text = { Text(option) },
          onClick = {
            onSelect(option)
            expanded = false
          }
        )
      }
    }
  }
}

// ══════════════════════════════════════════════════════
// F. SECTION HISTORIQUE DES ANALYSES
// ══════════════════════════════════════════════════════
@Composable
fun LabsHistorySection(
  results: List<com.example.data.local.entities.LabResultEntity>,
  patients: List<com.example.data.local.entities.PatientEntity>,
  selectedPatientName: String?,
  onDelete: (com.example.data.local.entities.LabResultEntity) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    item {
      Text(
        text = selectedPatientName?.let { "Historique des analyses — $it (${results.size})" }
          ?: "Toutes les analyses enregistrées (${results.size})",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.primary
      )
    }

    if (results.isEmpty()) {
      item {
        Box(
          modifier = Modifier.fillMaxWidth().padding(40.dp),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = Icons.Default.Biotech, contentDescription = null, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.outline)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Aucune analyse enregistrée pour cette sélection.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
          }
        }
      }
    } else {
      items(results) { res ->
        val patientLabel = patients.find { it.id == res.patientId }?.fullNameOrAlias
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
          Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "${res.testType}${patientLabel?.let { " • $it" } ?: ""}",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
              )
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = res.date, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                Spacer(modifier = Modifier.width(6.dp))
                AlertLevelChip(alertLevel = res.alertLevel)
              }
            }
            Text(text = res.summaryTitle, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            if (res.valuesMapJson.isNotBlank()) {
              Text(
                text = "Paramètres : ${res.valuesMapJson}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
            Text(text = res.interpretationText, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
              Text(
                text = "Supprimer",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                  .clickable { onDelete(res) }
                  .padding(horizontal = 4.dp, vertical = 2.dp)
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun InterpretationResultCard(
  interp: BiologicalInterpretation,
  hasPatientSelected: Boolean = true,
  onSave: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = interp.title,
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface,
          modifier = Modifier.weight(1f)
        )
        AlertLevelChip(alertLevel = interp.alertLevel)
      }

      Text(
        text = interp.summary,
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
        color = MaterialTheme.colorScheme.primary
      )

      if (interp.computedMetrics.isNotEmpty()) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          interp.computedMetrics.forEach { (k, v) ->
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
              Text("$k : $v", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
            }
          }
        }
      }

      // General Conclusion Box (explicit per exam)
      val conclusionText = interp.generalConclusion.ifBlank { interp.professionalMedicalConclusion }
      if (conclusionText.isNotBlank()) {
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = when (interp.alertLevel) {
            "CRITICAL" -> AlertRed.copy(alpha = 0.08f)
            "MODERATE" -> AlertOrange.copy(alpha = 0.08f)
            else -> MedicalBlue.copy(alpha = 0.08f)
          },
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            when (interp.alertLevel) {
              "CRITICAL" -> AlertRed.copy(alpha = 0.45f)
              "MODERATE" -> AlertOrange.copy(alpha = 0.45f)
              else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
            }
          )
        ) {
          Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
              text = "Conclusion générale de l'examen",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.ExtraBold),
              color = when (interp.alertLevel) {
                "CRITICAL" -> AlertRed
                "MODERATE" -> AlertOrange
                else -> MaterialTheme.colorScheme.primary
              }
            )
            Text(
              text = conclusionText,
              style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold, lineHeight = 20.sp),
              color = MaterialTheme.colorScheme.onSurface
            )
          }
        }
      }

      if (interp.identifiedAssociations.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
          Text(
            text = "Associations & Profils identifiés :",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = Color(0xFF4F46E5))
          )
          interp.identifiedAssociations.forEach { assoc ->
            Text(text = "• $assoc", style = MaterialTheme.typography.bodySmall)
          }
        }
      }

      if (interp.diagnosticOrientations.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
          Text(
            text = "Orientations diagnostiques à discuter (avec la clinique) :",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
          )
          interp.diagnosticOrientations.forEach { orient ->
            Text(text = "→ $orient", style = MaterialTheme.typography.bodySmall)
          }
        }
      }

      Text(
        text = "Analyse Pédagogique & Signification Clinique :",
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
      )
      interp.details.forEach { d ->
        Row(modifier = Modifier.padding(start = 4.dp)) {
          Text("• ", fontWeight = FontWeight.Bold)
          Text(text = d, style = MaterialTheme.typography.bodySmall)
        }
      }

      if (interp.pedagogicalMechanisms.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "Physiopathologie & Mécanismes :",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
          )
          interp.pedagogicalMechanisms.forEach { (param, expl) ->
            Text(
              text = "$param : $expl",
              style = MaterialTheme.typography.bodySmall,
              modifier = Modifier.padding(start = 4.dp)
            )
          }
        }
      }

      if (interp.clinicalPearls.isNotEmpty()) {
        Text(
          text = "Éléments à rechercher & Examens complémentaires :",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
        )
        interp.clinicalPearls.forEach { p ->
          Row(modifier = Modifier.padding(start = 4.dp)) {
            Text("💡 ", fontSize = 12.sp)
            Text(text = p, style = MaterialTheme.typography.bodySmall)
          }
        }
      }

      if (interp.clinicalContextCaveat.isNotBlank()) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFEF3C7))
            .padding(10.dp)
        ) {
          Text(
            text = interp.clinicalContextCaveat,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = Color(0xFF78350F), lineHeight = 16.sp)
          )
        }
      }

      Spacer(modifier = Modifier.height(4.dp))

      Button(
        onClick = onSave,
        modifier = Modifier.fillMaxWidth().height(46.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
      ) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = if (hasPatientSelected) "Enregistrer dans le dossier patient" else "Sélectionnez un patient pour enregistrer",
          fontWeight = FontWeight.SemiBold
        )
      }
    }
  }
}

@Composable
fun NfsDualLevelResultCard(
  interp: BiologicalInterpretation,
  hasPatientSelected: Boolean = true,
  onSave: () -> Unit
) {
  var selectedTab by remember { mutableIntStateOf(0) }

  Card(
    modifier = Modifier.fillMaxWidth().testTag("nfs_dual_level_card"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
  ) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      // Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Hémogramme (NFS) — Analyse à 2 Niveaux",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Interprétation médicale globale & Démarche pédagogique",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        AlertLevelChip(alertLevel = interp.alertLevel)
      }

      // Computed metrics (VGM, TCMH, CCMH)
      if (interp.computedMetrics.isNotEmpty()) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          interp.computedMetrics.forEach { (k, v) ->
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
              Text(
                text = "$k : $v",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
              )
            }
          }
        }
      }

      // Dual-level tab selector
      TabRow(
        selectedTabIndex = selectedTab,
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.clip(RoundedCornerShape(10.dp))
      ) {
        Tab(
          selected = selectedTab == 0,
          onClick = { selectedTab = 0 },
          text = {
            Text(
              text = "A. Interprétation Globale",
              fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,
              fontSize = 12.5.sp
            )
          }
        )
        Tab(
          selected = selectedTab == 1,
          onClick = { selectedTab = 1 },
          text = {
            Text(
              text = "B. Analyse Pédagogique",
              fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,
              fontSize = 12.5.sp
            )
          }
        )
      }

      if (selectedTab == 0) {
        // ══════════════════════════════════════════════════════════════════
        // NIVEAU A — INTERPRÉTATION MÉDICALE GLOBALE DU BILAN
        // STRUCTURE EN 6 POINTS CONFORME AU CAHIER DES CHARGES :
        // 1. État de la lignée rouge.
        // 2. État de la lignée blanche.
        // 3. État de la lignée plaquettaire.
        // 4. Identification des associations d'anomalies.
        // 5. Conclusion et interprétation générale de la NFS.
        // 6. Explication clinique et éléments à approfondir.
        // ══════════════════════════════════════════════════════════════════
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

          // 1. ÉTAT DE LA LIGNÉE ROUGE
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "1. État de la Lignée Rouge (Érythrocytaire)",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = interp.redLineSummary.ifEmpty { "Paramètres de la lignée rouge analysés." },
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
              )
            }
          }

          // 2. ÉTAT DE LA LIGNÉE BLANCHE
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "2. État de la Lignée Blanche (Leucocytaire)",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = interp.whiteLineSummary.ifEmpty { "Paramètres de la lignée blanche analysés en valeurs absolues." },
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
              )
            }
          }

          // 3. ÉTAT DE LA LIGNÉE PLAQUETTAIRE
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "3. État de la Lignée Thrombocytaire (Plaquettes)",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = interp.plateletLineSummary.ifEmpty { "Numération plaquettaire analysée." },
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
              )
            }
          }

          // 4. IDENTIFICATION DES ASSOCIATIONS D'ANOMALIES
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF6366F1).copy(alpha = 0.08f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF6366F1).copy(alpha = 0.35f))
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "4. Identification des Associations d'Anomalies",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF4F46E5)
              )
              if (interp.identifiedAssociations.isNotEmpty()) {
                interp.identifiedAssociations.forEach { assoc ->
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("• ", fontWeight = FontWeight.Bold, color = Color(0xFF4F46E5))
                    Text(
                      text = assoc,
                      style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                  }
                }
              } else {
                Text(
                  text = "Absence d'association multilignée pathologique.",
                  style = MaterialTheme.typography.bodySmall
                )
              }
            }
          }

          // 5. CONCLUSION ET INTERPRÉTATION GÉNÉRALE DE LA NFS
          Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
              containerColor = if (interp.alertLevel == "CRITICAL") AlertRed.copy(alpha = 0.10f)
              else if (interp.alertLevel == "MODERATE") AlertOrange.copy(alpha = 0.10f)
              else MedicalBlue.copy(alpha = 0.10f)
            ),
            border = androidx.compose.foundation.BorderStroke(
              1.5.dp,
              if (interp.alertLevel == "CRITICAL") AlertRed
              else if (interp.alertLevel == "MODERATE") AlertOrange
              else MaterialTheme.colorScheme.primary
            )
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "5. CONCLUSION ET INTERPRÉTATION GÉNÉRALE DE LA NFS",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
                  color = if (interp.alertLevel == "CRITICAL") AlertRed
                  else if (interp.alertLevel == "MODERATE") AlertOrange
                  else MaterialTheme.colorScheme.primary
                )
              }

              Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 1.dp
              ) {
                Text(
                  text = interp.professionalMedicalConclusion.ifEmpty { interp.generalConclusion },
                  style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                  ),
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.padding(12.dp)
                )
              }
            }
          }

          // 6. EXPLICATION CLINIQUE ET ÉLÉMENTS À APPROFONDIR
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
              Text(
                text = "6. Explication Clinique et Éléments à Approfondir",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )

              interp.nfsExplanation?.let { exp ->
                // Signification
                Column {
                  Text(
                    text = "Signification médicale de l'anomalie :",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                  )
                  Text(
                    text = exp.significance,
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp)
                  )
                }

                // Lignées concernées
                Column {
                  Text(
                    text = "Lignées concernées :",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                  )
                  Text(
                    text = exp.affectedLines,
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp)
                  )
                }

                // Mécanismes ou causes
                if (exp.mechanismsAndCauses.isNotEmpty()) {
                  Column {
                    Text(
                      text = "Mécanismes ou causes possibles à envisager :",
                      style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    exp.mechanismsAndCauses.forEach { cause ->
                      Row(modifier = Modifier.padding(start = 4.dp, top = 2.dp), verticalAlignment = Alignment.Top) {
                        Text("→ ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Text(text = cause, style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp))
                      }
                    }
                  }
                }

                // Éléments cliniques à rechercher
                if (exp.clinicalSignsToSearch.isNotEmpty()) {
                  Column {
                    Text(
                      text = "Éléments cliniques à rechercher au lit du malade :",
                      style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    exp.clinicalSignsToSearch.forEach { sign ->
                      Row(modifier = Modifier.padding(start = 4.dp, top = 2.dp), verticalAlignment = Alignment.Top) {
                        Text("• ", fontWeight = FontWeight.Bold, color = AlertOrange)
                        Text(text = sign, style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp))
                      }
                    }
                  }
                }

                // Examens complémentaires utiles
                if (exp.recommendedExams.isNotEmpty()) {
                  Column {
                    Text(
                      text = "Examens complémentaires éventuellement utiles :",
                      style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    exp.recommendedExams.forEach { exam ->
                      Row(modifier = Modifier.padding(start = 4.dp, top = 2.dp), verticalAlignment = Alignment.Top) {
                        Text("🔬 ", fontSize = 11.sp)
                        Text(text = exam, style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp))
                      }
                    }
                  }
                }
              }

              // Limites de l'interprétation
              Box(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(8.dp))
                  .background(Color(0xFFFEF3C7))
                  .padding(10.dp)
              ) {
                Text(
                  text = interp.clinicalContextCaveat,
                  style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = Color(0xFF78350F), lineHeight = 16.sp)
                )
              }
            }
          }
        }
      } else {
        // ══════════════════════════════════════════════════════════════════
        // NIVEAU B — ANALYSE PÉDAGOGIQUE APPROFONDIE
        // ══════════════════════════════════════════════════════════════════
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "Ce que représente chaque paramètre et mécanismes physiologiques :",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
          )

          interp.pedagogicalMechanisms.forEach { (param, expl) ->
            Card(
              shape = RoundedCornerShape(10.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Text(
                  text = param,
                  style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                  color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                  text = expl,
                  style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            }
          }

          if (interp.clinicalPearls.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "Perles de l'Interne & Pièges à Éviter :",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
            )
            interp.clinicalPearls.forEach { pearl ->
              Row(modifier = Modifier.padding(start = 4.dp), verticalAlignment = Alignment.Top) {
                Text("💡 ", fontSize = 12.sp)
                Text(text = pearl, style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp))
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Button(
        onClick = onSave,
        modifier = Modifier.fillMaxWidth().height(46.dp).testTag("save_nfs_analysis_btn"),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
      ) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = if (hasPatientSelected) "Enregistrer l'analyse NFS dans le dossier patient" else "Sélectionnez un patient pour enregistrer la NFS",
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}
