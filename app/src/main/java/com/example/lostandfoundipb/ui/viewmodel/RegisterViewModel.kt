package com.example.lostandfoundipb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lostandfoundipb.retrofit.ApiService
import com.example.lostandfoundipb.retrofit.models.Confirmation
import com.example.lostandfoundipb.retrofit.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class RegisterViewModel : ViewModel(){
    private var disposable: Disposable? = null
    private var job = Job()
    private var mainScope = CoroutineScope(job + Dispatchers.Main)


    private val register_result = MutableLiveData<Confirmation.Result>()
    val registerResult: LiveData<Confirmation.Result> = register_result

    private val register_string = MutableLiveData<String>()
    val registerString: LiveData<String> = register_string

    fun register(apiService: ApiService, user: User.SignUp){
        mainScope.launch {
            disposable = apiService.register(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    register_result.value = result
                    register_string.value = "Success"
                }, { error ->
                    register_string.value= error.toString()
                })
        }
    }

    fun onPaused(){
        disposable?.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}