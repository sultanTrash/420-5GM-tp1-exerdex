package ca.qc.bdeb.c5gm.exerdex.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ExerciseRaw (
    var name: String,
    val description: String = "",
    val category: MuscleCategory,
    val imageUri: String? = null,
    val userId: String,
    @PrimaryKey(autoGenerate = true) val exRawId: Int = 0
) : Parcelable {
    fun toCompactString(): String {
        return "name='$name', description='$description', category=$category"
    }
}
