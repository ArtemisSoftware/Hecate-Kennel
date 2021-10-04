package com.artemissoftware.hecatekennel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.artemissoftware.domain.repository.DogRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: DogRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}