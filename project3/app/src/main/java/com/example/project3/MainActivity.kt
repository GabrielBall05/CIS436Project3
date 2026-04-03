package com.example.project3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project3.ui.BreedDetailsFragment
import com.example.project3.ui.BreedSelectorFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Manually add fragments if they haven't been added yet
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.selectorContainer, BreedSelectorFragment())
                .replace(R.id.detailsContainer, BreedDetailsFragment())
                .commit()
        }
    }
}