package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.GlasgowEvaluationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GlasgowEvaluationDao {
  @Query("SELECT * FROM glasgow_evaluations ORDER BY timestamp DESC")
  fun getAllGlasgowEvaluations(): Flow<List<GlasgowEvaluationEntity>>

  @Query("SELECT * FROM glasgow_evaluations WHERE patientId = :patientId ORDER BY timestamp DESC")
  fun getGlasgowForPatient(patientId: Long): Flow<List<GlasgowEvaluationEntity>>

  @Query("SELECT * FROM glasgow_evaluations WHERE patientId = :patientId ORDER BY timestamp DESC LIMIT 1")
  suspend fun getLatestGlasgowForPatient(patientId: Long): GlasgowEvaluationEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertGlasgow(evaluation: GlasgowEvaluationEntity): Long

  @Delete
  suspend fun deleteGlasgow(evaluation: GlasgowEvaluationEntity)
}
