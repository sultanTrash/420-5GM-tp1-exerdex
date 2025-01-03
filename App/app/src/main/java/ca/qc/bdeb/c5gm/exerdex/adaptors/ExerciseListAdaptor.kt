package ca.qc.bdeb.c5gm.exerdex.adaptors

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import ca.qc.bdeb.c5gm.exerdex.MainActivity
import ca.qc.bdeb.c5gm.exerdex.R
import ca.qc.bdeb.c5gm.exerdex.data.Exercise
import ca.qc.bdeb.c5gm.exerdex.room.ExerciseDatabase
import ca.qc.bdeb.c5gm.exerdex.viewholders.ItemExerciseHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseListAdaptor(
    val ctx: Context,
    var exercisesList: MutableList<Exercise>,
    private val finishExercise: (item: Exercise) -> Unit,
    private val deleteExercise: (item: Exercise) -> Unit,
    private val editExercise: (item: Exercise) -> Unit,
    private val showExercise: (item: Exercise) -> Unit,
    //private val editExercise: (isEdit: Boolean, exerciseToEdit: Exercise?) -> Unit,
) : RecyclerView.Adapter<ItemExerciseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemExerciseHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.exercise_item, parent, false)
        return ItemExerciseHolder(view)
    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }

    override fun onBindViewHolder(holder: ItemExerciseHolder, position: Int) {
        exercisesList.sortBy { it.exerciseRawData.category }

        Log.d("Sorting...",exercisesList.toString())

        val item = exercisesList[position]

        if(position == 0 || exercisesList[position -1].exerciseRawData.category != item.exerciseRawData.category){
            holder.category.visibility = View.VISIBLE
            holder.category.text = item.exerciseRawData.category.name.replaceFirstChar { it.uppercase() }
        }else{
            holder.category.visibility = View.GONE
        }

        holder.exercise.text = item.exerciseRawData.name

        if(!item.isImportant){
            holder.important.visibility = View.GONE
        }else{
            holder.important.setImageResource(R.drawable.baseline_star_24)
        }
        /*
        * Source : https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/join-to-string.html
        * */
        if (item.setList.size > 3){
            holder.sets.text = (item.setList.joinToString("\n", "", "", 3) { it.toString() }) + " ${item.setList.size - 3} more"
        }else{
            holder.sets.text = item.setList.joinToString("\n") { it.toString() }
        }
        holder.check.setImageResource(R.drawable.baseline_check_circle_24)
        holder.edit.setImageResource(R.drawable.baseline_edit_24)
        holder.cancel.setImageResource(R.drawable.baseline_cancel_24)



        holder.check.setOnClickListener {
            finishExercise(item)
        }

        holder.edit.setOnClickListener {
            editExercise(item)
        }

        holder.cancel.setOnClickListener {
            deleteExercise(item)
        }
        holder.itemView.setOnClickListener{
            showExercise(item)
        }
    }
}