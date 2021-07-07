package net.scootergo.app.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import net.scootergo.app.repository.MainRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() , LifecycleObserver{

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val responseLiveData = liveData(Dispatchers.IO) {
        isLoading.postValue(true)
        emit(repository.getScooterList())
        isLoading.postValue(false)
    }
}