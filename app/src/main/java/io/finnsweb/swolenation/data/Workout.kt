package io.finnsweb.swolenation.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workouts",
    indices = [Index("name")]
)
data class Workout(
    var name: String,
    var exercises: List<Exercise>?,
    var startTime: Int?,
    var endTime: Int?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var workoutId: Int = 0
    var completed: Boolean = false
}