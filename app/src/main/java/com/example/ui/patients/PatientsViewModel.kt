package com.example.ui.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.ClinicalUpdateEntity
import com.example.data.local.entities.GlasgowEvaluationEntity
import com.example.data.local.entities.LabResultEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import com.example.data.repository.MedicalRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class PatientDetailState(
  val patient: PatientEntity? = null,
  val observations: List<ClinicalObservationEntity> = emptyList(),
  val updates: List<ClinicalUpdateEntity> = emptyList(),
  val vitals: List<VitalEntity> = emptyList(),
  val glasgowEvaluations: List<GlasgowEvaluationEntity> = emptyList(),
  val labResults: List<LabResultEntity> = emptyList()
)

@OptIn(ExperimentalCoroutinesApi::class)
class PatientsViewModel(private val repository: MedicalRepository) : ViewModel() {

  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

  val patientsList: StateFlow<List<PatientEntity>> = _searchQuery
    .flatMapLatest { query -> repository.searchPatients(query) }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  private val _selectedPatientId = MutableStateFlow<Long?>(null)
  val selectedPatientId: StateFlow<Long?> = _selectedPatientId.asStateFlow()

  val patientDetail: StateFlow<PatientDetailState> = _selectedPatientId
    .flatMapLatest { pid ->
      if (pid == null) {
        flowOf(PatientDetailState())
      } else {
        combine(
          repository.observePatient(pid),
          repository.getObservationsForPatient(pid),
          repository.getUpdatesForPatient(pid),
          repository.getVitalsForPatient(pid),
          repository.getGlasgowForPatient(pid),
          repository.getLabResultsForPatient(pid)
        ) { args ->
          val patient = args[0] as PatientEntity?
          @Suppress("UNCHECKED_CAST")
          val observations = args[1] as List<ClinicalObservationEntity>
          @Suppress("UNCHECKED_CAST")
          val updates = args[2] as List<ClinicalUpdateEntity>
          @Suppress("UNCHECKED_CAST")
          val vitals = args[3] as List<VitalEntity>
          @Suppress("UNCHECKED_CAST")
          val glasgow = args[4] as List<GlasgowEvaluationEntity>
          @Suppress("UNCHECKED_CAST")
          val labs = args[5] as List<LabResultEntity>

          PatientDetailState(
            patient = patient,
            observations = observations,
            updates = updates,
            vitals = vitals,
            glasgowEvaluations = glasgow,
            labResults = labs
          )
        }
      }
    }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PatientDetailState())

  fun setSearchQuery(query: String) {
    _searchQuery.value = query
  }

  fun selectPatient(patientId: Long?) {
    _selectedPatientId.value = patientId
  }

  fun createNewPatient(
    name: String,
    age: Int,
    gender: String,
    dept: String,
    room: String,
    condition: String,
    onSuccess: (Long) -> Unit
  ) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val patient = PatientEntity(
      fullNameOrAlias = name,
      age = age,
      gender = gender,
      admissionDate = sdf.format(Date()),
      department = dept,
      roomNumber = room,
      primaryCondition = condition
    )
    viewModelScope.launch {
      val id = repository.insertPatient(patient)
      selectPatient(id)
      onSuccess(id)
    }
  }

  fun deletePatient(patient: PatientEntity, onDeleted: () -> Unit) {
    viewModelScope.launch {
      repository.deletePatient(patient)
      selectPatient(null)
      onDeleted()
    }
  }
}
