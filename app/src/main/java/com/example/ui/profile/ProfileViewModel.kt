package com.example.ui.profile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.repository.MedicalRepository
import com.example.data.storage.AndroidSharedPrefsStorage
import com.example.data.storage.PreferenceStorage
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class StudentProfile(
  val name: String = "Étudiant en médecine",
  val studyYear: String = "DFASM 2 (5e Année)",
  val university: String = "Faculté des Sciences de la Santé",
  val currentHospital: String = "CHU - Pôle Médecine Interne",
  val isDarkMode: Boolean = false,
  val photoPath: String? = null
)

class ProfileViewModel(
  private val preferenceStorage: PreferenceStorage,
  private val repository: MedicalRepository,
  private val database: AppDatabase
) : ViewModel() {

  // Constructeur de commodité Android pour compatibilité native directe
  constructor(context: Context, repository: MedicalRepository, database: AppDatabase) :
    this(AndroidSharedPrefsStorage(context), repository, database)

  private val _profile = MutableStateFlow(loadPersistedProfile())
  val profile: StateFlow<StudentProfile> = _profile.asStateFlow()

  private val _stats = MutableStateFlow(Triple(0, 0, 0))
  val stats: StateFlow<Triple<Int, Int, Int>> = _stats.asStateFlow()

  init {
    loadStats()
  }

  private fun loadPersistedProfile(): StudentProfile {
    // Si la préférence existe, la charger.
    // Garantir qu'aucun résidu de nom d'exemple ne subsiste
    val savedName = preferenceStorage.getString("student_name", null)
    val finalName = if (savedName.isNullOrBlank() || savedName == "Albert D." || savedName == "Albert Dossou") {
      "Étudiant en médecine"
    } else {
      savedName
    }
    val year = preferenceStorage.getString("student_year", "DFASM 2 (5e Année)") ?: "DFASM 2 (5e Année)"
    val uni = preferenceStorage.getString("student_university", "Faculté des Sciences de la Santé") ?: "Faculté des Sciences de la Santé"
    val hosp = preferenceStorage.getString("student_hospital", "CHU - Pôle Médecine Interne") ?: "CHU - Pôle Médecine Interne"
    val isDark = preferenceStorage.getBoolean("app_dark_mode", false)
    val savedPhoto = preferenceStorage.getString("student_photo_path", null)
      ?.takeIf { it.isNotBlank() && File(it).exists() }

    return StudentProfile(
      name = finalName,
      studyYear = year,
      university = uni,
      currentHospital = hosp,
      isDarkMode = isDark,
      photoPath = savedPhoto
    )
  }

  fun loadStats() {
    viewModelScope.launch {
      val c = repository.getCounts()
      _stats.value = c
    }
  }

  fun updateProfile(name: String, year: String, uni: String, hosp: String) {
    val cleanName = name.trim().ifEmpty { "Étudiant en médecine" }
    preferenceStorage.putString("student_name", cleanName)
    preferenceStorage.putString("student_year", year)
    preferenceStorage.putString("student_university", uni)
    preferenceStorage.putString("student_hospital", hosp)

    _profile.value = _profile.value.copy(
      name = cleanName,
      studyYear = year,
      university = uni,
      currentHospital = hosp
    )
  }

  fun savePhotoFromUri(context: Context, uri: Uri, onComplete: (Boolean) -> Unit = {}) {
    viewModelScope.launch {
      val savedPath = withContext(Dispatchers.IO) {
        try {
          val destFile = File(context.filesDir, "student_profile_photo_${System.currentTimeMillis()}.jpg")
          context.contentResolver.openInputStream(uri)?.use { input ->
            destFile.outputStream().use { output ->
              input.copyTo(output)
            }
          } ?: return@withContext null
          // Clean previous saved photo files if different
          _profile.value.photoPath?.let { oldPath ->
            val oldFile = File(oldPath)
            if (oldFile.exists() && oldFile.absolutePath != destFile.absolutePath) {
              oldFile.delete()
            }
          }
          destFile.absolutePath
        } catch (_: Exception) {
          null
        }
      }
      if (savedPath != null) {
        preferenceStorage.putString("student_photo_path", savedPath)
        _profile.value = _profile.value.copy(photoPath = savedPath)
        onComplete(true)
      } else {
        onComplete(false)
      }
    }
  }

  fun savePhotoFromFilePath(filePath: String, onComplete: (Boolean) -> Unit = {}) {
    val file = File(filePath)
    if (!file.exists() || file.length() == 0L) {
      onComplete(false)
      return
    }
    preferenceStorage.putString("student_photo_path", file.absolutePath)
    _profile.value = _profile.value.copy(photoPath = file.absolutePath)
    onComplete(true)
  }

  fun removePhoto() {
    _profile.value.photoPath?.let { path ->
      val f = File(path)
      if (f.exists()) f.delete()
    }
    preferenceStorage.putString("student_photo_path", "")
    _profile.value = _profile.value.copy(photoPath = null)
  }

  fun toggleDarkMode(enabled: Boolean) {
    preferenceStorage.putBoolean("app_dark_mode", enabled)
    _profile.value = _profile.value.copy(isDarkMode = enabled)
  }

  fun reseedDemoData(onComplete: () -> Unit) {
    viewModelScope.launch {
      AppDatabase.seedPedagogicalDatabase(database)
      loadStats()
      onComplete()
    }
  }
}
