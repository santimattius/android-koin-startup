package com.santimattius.template.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.santimattius.template.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel.popularMovies()
    }
}