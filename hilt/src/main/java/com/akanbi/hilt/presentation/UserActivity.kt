package com.akanbi.hilt.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akanbi.hilt.databinding.ActivityUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {
//    private val viewModel by viewModels<>()
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}