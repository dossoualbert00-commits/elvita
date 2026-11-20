package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "glasgow_evaluations",
  foreignKeys = [
    ForeignKey(
      entity = PatientEntity::class,
      parentColumns = ["id"],
      childColumns = ["patientId"],
      onDelete = ForeignKey.CASCADE
    )
  ],
  indices = [Index(value = ["patientId"])]
)
data class GlasgowEvaluationEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val patientId: Long,
  val eyeOpening: Int,
  val verbalResponse: Int,
  val motorResponse: Int,
  val totalScore: Int,
  val alertLevel: String = "NORMAL", // "NORMAL", "MODERATE", "CRITICAL"
  val interpretationText: String = "",
  val pedagogicalNote: String = "",
  val timestamp: Long = System.currentTimeMillis(),
  val formattedDateTime: String = ""
)
