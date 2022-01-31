package com.akanbi.hilt.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.akanbi.commonandroid.observerEvent
import com.akanbi.hilt.databinding.ActivityUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.list()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.userLiveData.observerEvent(this,
            onLoading = {},
            onSuccess = {
                adapter = UserAdapter(it)
            },
            onError = {}
        )
    }

}