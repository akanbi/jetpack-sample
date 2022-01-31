package com.akanbi.hilt.presentation

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akanbi.commonandroid.FlowState
import com.akanbi.commonandroid.postError
import com.akanbi.commonandroid.postLoading
import com.akanbi.commonandroid.postSuccess
import com.akanbi.commonkotlin.extensions.ProviderContext
import com.akanbi.hilt.R
import com.akanbi.hilt.domain.GetUserListUseCase
import com.akanbi.hilt.network.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: GetUserListUseCase,
    private val context: ProviderContext
) : ViewModel() {
    private var _userLiveData: MutableLiveData<FlowState<ArrayList<User>>> = MutableLiveData()
    val userLiveData: LiveData<FlowState<ArrayList<User>>> = _userLiveData

    fun list() {
        _userLiveData.postLoading(VISIBLE)
        viewModelScope.launch(context.main) {
            useCase.execute(
                onSuccess = {
                    _userLiveData.postLoading(GONE)
                    _userLiveData.postSuccess(it as ArrayList<User>)
                },
                onError = {
                    _userLiveData.postLoading(GONE)
                    _userLiveData.postError(R.string.error_user_list)
                }
            )
        }
    }

}