package com.akanbi.jetpacksample.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akanbi.jetpacksample.infrastructure.dao.DAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class Repository<T>(private val dao: DAO<T>) {

    fun save(model: T): LiveData<Void?> {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(model)
            withContext(Dispatchers.Main) {
                return@withContext MutableLiveData<Void?>(null)
            }
        }
        return MutableLiveData(null)
    }

    fun edit(model: T): LiveData<Void?> {
        CoroutineScope(Dispatchers.IO).launch {
            dao.update(model)
            withContext(Dispatchers.Main) {
                return@withContext MutableLiveData<Void?>(null)
            }
        }
        return MutableLiveData(null)
    }

    fun delete(model: T): LiveData<Void?> {
        CoroutineScope(Dispatchers.IO).launch {
            dao.delete(model)
            withContext(Dispatchers.Main) {
                return@withContext MutableLiveData<Void?>(null)
            }
        }
        return MutableLiveData(null)
    }

}