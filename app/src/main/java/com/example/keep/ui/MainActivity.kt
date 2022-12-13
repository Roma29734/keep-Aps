package com.example.keep.ui



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.keep.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun updateDate(state: Boolean) {
        val sheared = getSharedPreferences("stateFav", MODE_PRIVATE)

        sheared.edit().apply{
            putBoolean("STATE_KEY", state)
        }.apply()
    }

    fun readDate(): Boolean {
        val sheared = getSharedPreferences("stateFav", MODE_PRIVATE)
        return sheared.getBoolean("STATE_KEY", false)
    }

    override fun onDestroy() {
        super.onDestroy()
        updateDate(false)
    }
}



