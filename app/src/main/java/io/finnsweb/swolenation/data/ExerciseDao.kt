package io.finnsweb.swolenation.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getExercises(): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun getExercise(exerciseId: Int): LiveData<Exercise>

    @Insert
    fun insertExercise(exercise: Exercise): Long

    @Update
    fun updateExercise(exercise: Exercise)

    @Delete
    fun deleteExercise(exercise: Exercise)
}