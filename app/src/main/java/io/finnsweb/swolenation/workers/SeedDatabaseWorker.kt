package io.finnsweb.swolenation.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import io.finnsweb.swolenation.data.AppDatabase
import io.finnsweb.swolenation.data.Exercise
import io.finnsweb.swolenation.data.Workout
import io.finnsweb.swolenation.utilities.EXERCISES_FILENAME
import io.finnsweb.swolenation.utilities.WORKOUTS_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope {
        // Seed workouts.
        try {
            applicationContext.assets.open(WORKOUTS_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use {jsonReader ->
                    val workoutType = object : TypeToken<List<Workout>>(){}.type
                    val workoutList: List<Workout> = Gson().fromJson(jsonReader, workoutType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.workoutDao().insertAll(workoutList)

                    Result.success()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "toooo the database", e)
            Result.failure()
        }

        // Seed exercises.
        try {
            applicationContext.assets.open(EXERCISES_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use {jsonReader ->
                    val exerciseType = object : TypeToken<List<Exercise>>(){}.type
                    val exerciseList: List<Exercise> = Gson().fromJson(jsonReader, exerciseType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.exerciseDao().insertAll(exerciseList)

                    Result.success()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error seeding exercises to the database", e)
            Result.failure()
        }
    }
}