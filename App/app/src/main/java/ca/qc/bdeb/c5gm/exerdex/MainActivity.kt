package ca.qc.bdeb.c5gm.exerdex

import android.content.Context
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ItemExerciseHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val layout: ConstraintLayout
    val exercise: TextView
    val sets: TextView
    val check: ImageView
    val edit: ImageView
    val cancel: ImageView

    init {
        layout = itemView as ConstraintLayout
        exercise = itemView.findViewById(R.id.exerciseTextView)
        sets = itemView.findViewById(R.id.setsTextView)
        check = itemView.findViewById(R.id.checkView)
        edit = itemView.findViewById(R.id.editView)
        cancel = itemView.findViewById(R.id.deleteView)
    }
}

class ExerciseListAdaptor(val ctx: Context, val activity: MainActivity, var data: List<Exercise>): RecyclerView.Adapter<ItemExerciseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemExerciseHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.exercise_item, parent, false)
        return ItemExerciseHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemExerciseHolder, position: Int) {
        val item = data[position]

        holder.exercise.text = item.name
        /*
        * Source : https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/join-to-string.html
        * */
        holder.sets.text = item.setList.joinToString("\n") { it.toString() }
        holder.check.setImageResource(R.drawable.baseline_check_circle_24)
        holder.edit.setImageResource(R.drawable.baseline_edit_24)
        holder.cancel.setImageResource(R.drawable.baseline_cancel_24)

        holder.check.setOnClickListener{

        }

        holder.edit.setOnClickListener {

        }

        holder.cancel.setOnClickListener {

        }
    }

}

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapteur: ExerciseListAdaptor

    //val test:Exercise = Exercise(name = "Test", category = MuscleCategory.ABS, setList = listOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        val exercisesList: List<Exercise> = setUpExercises()
        adapteur = ExerciseListAdaptor(applicationContext, this, exercisesList)
        recyclerView.adapter = adapteur
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpExercises(): List<Exercise>{
        return listOf(
            Exercise(
                name = "Bench Press",
                description = "A compound exercise targeting the chest, triceps, and shoulders.",
                category = MuscleCategory.CHEST,
                setList = listOf(
                    Set(setOrder = 1, weight = 135f, reps = 10),
                    Set(setOrder = 2, weight = 145f, reps = 8),
                    Set(setOrder = 3, weight = 155f, reps = 6)
                )
            ),
            Exercise(
                name = "Squat",
                description = "A lower body compound exercise targeting the quadriceps, hamstrings, and glutes.",
                category = MuscleCategory.QUADS,
                setList = listOf(
                    Set(setOrder = 1, weight = 185f, reps = 10),
                    Set(setOrder = 2, weight = 205f, reps = 8),
                    Set(setOrder = 3, weight = 225f, reps = 6)
                )
            ),
            Exercise(
                name = "Deadlift",
                description = "A compound movement working the entire posterior chain.",
                category = MuscleCategory.BACK,
                setList = listOf(
                    Set(setOrder = 1, weight = 225f, reps = 8),
                    Set(setOrder = 2, weight = 245f, reps = 6),
                    Set(setOrder = 3, weight = 265f, reps = 4)
                )
            ),
            Exercise(
                name = "Overhead Press",
                description = "An upper body exercise focusing on the shoulders and triceps.",
                category = MuscleCategory.SHOULDERS,
                setList = listOf(
                    Set(setOrder = 1, weight = 95f, reps = 10),
                    Set(setOrder = 2, weight = 105f, reps = 8),
                    Set(setOrder = 3, weight = 115f, reps = 6)
                )
            ),
            Exercise(
                name = "Barbell Row",
                description = "An exercise to target the back, focusing on the lats and rhomboids.",
                category = MuscleCategory.BACK,
                setList = listOf(
                    Set(setOrder = 1, weight = 135f, reps = 10),
                    Set(setOrder = 2, weight = 145f, reps = 8),
                    Set(setOrder = 3, weight = 155f, reps = 6)
                )
            ),
            Exercise(
                name = "Bicep Curl",
                description = "An isolation exercise for the biceps.",
                category = MuscleCategory.BICEPS,
                setList = listOf(
                    Set(setOrder = 1, weight = 25f, reps = 12),
                    Set(setOrder = 2, weight = 30f, reps = 10),
                    Set(setOrder = 3, weight = 35f, reps = 8)
                )
            ),
            Exercise(
                name = "Tricep Pushdown",
                description = "An isolation exercise for the triceps using a cable machine.",
                category = MuscleCategory.TRICEPS,
                setList = listOf(
                    Set(setOrder = 1, weight = 40f, reps = 12),
                    Set(setOrder = 2, weight = 45f, reps = 10),
                    Set(setOrder = 3, weight = 50f, reps = 8)
                )
            )
        )
    }
}