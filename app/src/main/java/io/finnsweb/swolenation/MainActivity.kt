package io.finnsweb.swolenation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import io.finnsweb.swolenation.data.Workout
import io.finnsweb.swolenation.databinding.ActivityMainBinding

class MainActivity : ListWorkoutFragment.OnWorkoutListFragmentInteractionListener, AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    }

    override fun onWorkoutListFragmentInteraction(item: Workout?) {
        Log.v("MyThing", "clicked " + item?.name)
    }
}
