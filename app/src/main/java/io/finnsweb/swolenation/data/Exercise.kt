package io.finnsweb.swolenation.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    indices = [Index("name")],
    foreignKeys = [androidx.room.ForeignKey(
        entity = Workout::class,
        parentColumns = ["id"],
        childColumns = ["id"]
    )]
)
data class Exercise(
    var machineName: String,
    var label: String,
    var sets: List<Set>,
    var intervals: List<Int>,
    var equipment: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var exerciseId: Int = 0
}