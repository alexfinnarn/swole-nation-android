package io.finnsweb.swolenation.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open()
        } catch (e: Exception) {
            Log.e(TAG, "Error seeding database", e)
            Result.failure()
        }
    }
}