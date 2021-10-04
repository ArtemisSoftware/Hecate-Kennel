package com.artemissoftware.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.artemissoftware.domain.repository.DogRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}