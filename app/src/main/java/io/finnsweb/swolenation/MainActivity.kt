package io.finnsweb.swolenation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import io.finnsweb.swolenation.databinding.ActivityMainBinding
import io.finnsweb.swolenation.dummy.DummyContent

class MainActivity : ListWorkoutFragment.OnListFragmentInteractionListener, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {

    }
}
