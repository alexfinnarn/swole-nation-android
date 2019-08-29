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
    @PrimaryKey
    var machineName: String,
    var label: String,
    var sets: Map<Int, Double>,
    var equipment: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var exerciseId: Int = 0
}