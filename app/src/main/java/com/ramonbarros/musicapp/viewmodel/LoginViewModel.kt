package com.ramonbarros.musicapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ramonbarros.musicapp.R
import com.ramonbarros.musicapp.domain.LoginData
import com.ramonbarros.musicapp.domain.LoginResult
import com.ramonbarros.musicapp.interactor.LoginInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(val app: Application): AndroidViewModel(app), CoroutineScope {

    override val coroutineContext = Dispatchers.Main
    private val interactor = LoginInteractor()

    val screenResult = MutableLiveData<LoginResult>()

    fun login(data: LoginData, ctx: Context){

        launch {
            val result = interactor.treatInputData(data) //treatment of input data
            if (result.error != ""){
                result.error = screenTextError(ctx, result.error)

            } else {
                result.error = ""
                result.result = "Success"
            }
            screenResult.value = result
        }
    }

    fun screenTextError(ctx: Context, err: String): String {
        val errTxt: String = when (err) {
            "EMPTY EMAIL" -> ctx.getString(R.string.emptyEmail)
            "EMPTY PASSWORD" -> ctx.getString(R.string.emptyPassword)
            "PASS MINIMUM LENGTH" -> ctx.getString(R.string.passwordMinimumLimit)
            "PASS MAXIMUM LENGTH" -> ctx.getString(R.string.passwordMaximumLimit)
            "EMAIL INVALID" -> ctx.getString(R.string.invalidEmail)
            else -> err
        }
        return errTxt
    }
}