package com.example.showstoreinventory.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showstoreinventory.model.LoginState

class LoginViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    private var _logInSuccessful: MutableLiveData<LoginState> = MutableLiveData()
    var logInSuccessful: LiveData<LoginState> = _logInSuccessful

    fun logIn() {
        if (email?.trim()?.isNotEmpty() == true && password?.trim()?.isNotEmpty() == true) {
            _logInSuccessful.value = LoginState.SUCCESS
        } else {
            _logInSuccessful.value = LoginState.ERROR
        }
    }

    fun logInWithExistAccount() {
        _logInSuccessful.value = LoginState.SUCCESS
    }
}