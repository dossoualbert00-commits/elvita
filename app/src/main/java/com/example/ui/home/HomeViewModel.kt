package com.example.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.ClinicalUpdateEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import com.example.data.model.PreventionDataProvider
import com.example.data.model.PreventionTopic
import com.example.data.repository.MedicalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
  val recentVitals: List<VitalEntity> = emptyList(),
  val patients: List<PatientEntity> = emptyList(),
  val recentObservations: List<ClinicalObservationEntity> = emptyList(),
  val recentUpdates: List<ClinicalUpdateEntity> = emptyList(),
  val patientCount: Int = 0,
  val vitalCount: Int = 0,
  val observationCount: Int = 0
)

class HomeViewModel(private val repository: MedicalRepository) : ViewModel() {

  val preventionTopics: List<PreventionTopic> = PreventionDataProvider.topics

  val uiState: StateFlow<HomeUiState> = combine(
    repository.getRecentVitals(6),
    repository.getAllPatients(),
    repository.getAllObservations(),
    repository.getAllUpdates()
  ) { vitals, patients, observations, updates ->
    HomeUiState(
      recentVitals = vitals,
      patients = patients,
      recentObservations = observations.take(3),
      recentUpdates = updates.take(3),
      patientCount = patients.size,
      vitalCount = vitals.size,
      observationCount = observations.size
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = HomeUiState()
  )
}
