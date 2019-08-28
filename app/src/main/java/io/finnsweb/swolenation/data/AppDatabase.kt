package io.finnsweb.swolenation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.finnsweb.swolenation.utilities.DATABASE_NAME

@Database(entities = [Workout::class, Exercise::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        // https://gist.github.com/florina-muntenescu/697e543652b03d3d2a06703f5d6b44b5
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME)
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            getInstance(context).workoutDao().insertData(WORKOUT_DATA)
                            getInstance(context).exerciseDao().insertData(EXERCISE_DATA)
                        }
                    }
                })
                .build()


        val EXERCISE_DATA = listOf(Workout("Stronglifts A", "val"), Data("2", "val 2"))
        val WORKOUT_DATA = listOf(Workout("Stronglifts A", ), Data("2", "val 2"))
    }
}