package com.app.rocket

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.app.rocket.databinding.ActivityMainBinding
import com.app.rocket.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

}