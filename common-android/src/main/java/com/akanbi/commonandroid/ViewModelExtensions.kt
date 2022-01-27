package com.akanbi.commonandroid

import android.view.View.VISIBLE
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class FlowState<D>(
    val status: Status,
    val data: D? = null,
    val error: Throwable? = null,
    val resources: Int = 0,
    val loadingVisibility: Int = VISIBLE
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}

fun <D> MutableLiveData<FlowState<D>>.postLoading(state: Int) {
    value = FlowState(FlowState.Status.LOADING, loadingVisibility = state)
}

fun <D> MutableLiveData<FlowState<D>>.postSuccess(data: D?, loadingVisibility: Int = 0) {
    value = FlowState(FlowState.Status.SUCCESS, data = data, loadingVisibility = loadingVisibility)
}

fun <D> MutableLiveData<FlowState<D>>.postSuccess(data: D?) {
    value = FlowState(FlowState.Status.SUCCESS, data = data)
}

fun <D> MutableLiveData<FlowState<D>>.postError(error: Throwable) {
    value = FlowState(FlowState.Status.ERROR, error = error)
}

fun <D> MutableLiveData<FlowState<D>>.postError(resources: Int) {
    value = FlowState(FlowState.Status.ERROR, resources = resources)
}

fun <D> LiveData<FlowState<D>>.observerEvent(
    lifecycleOwner: LifecycleOwner,
    onLoading: (Int) -> Unit,
    onSuccess: (D) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onErrorWithId: ((Int) -> Unit)? = null

) {
    observe(lifecycleOwner, Observer {
        when (it.status) {
            FlowState.Status.LOADING -> {
                onLoading.invoke(it.loadingVisibility)
            }
            FlowState.Status.SUCCESS -> {
                it.data?.let { data -> onSuccess.invoke(data) }
            }
            FlowState.Status.ERROR -> {
                if (it.resources != 0)
                    it.resources.let { error -> onErrorWithId?.invoke(error) }
                else
                    it.error?.let { error ->
                        onError.invoke(error)
                    }
            }
        }
    })
}