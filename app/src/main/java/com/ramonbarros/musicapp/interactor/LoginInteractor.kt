package com.ramonbarros.musicapp.interactor

import android.util.Patterns.EMAIL_ADDRESS
import com.ramonbarros.musicapp.domain.LoginData
import com.ramonbarros.musicapp.domain.LoginResult
import com.ramonbarros.musicapp.repository.LoginRepository

class LoginInteractor {
    val repo = LoginRepository()

    suspend fun login(data: LoginData): LoginResult {
        var result = treatInputData(data)
        if (result.error == "") {
            result = repo.loginToFirebase(data)
        }
        return result
    }

    suspend fun register(data: LoginData): LoginResult {
        var result = treatInputData(data)
        if (result.error == "") {
            result = repo.registerToFirebase(data)
        }
        return result
    }

    suspend fun resetPassword(data: LoginData): LoginResult {
        var result = treatInputData(data)
        if (result.error == "") {
            result = repo.resetPassFirebase(data)
        }
        return result
    }

    fun treatInputData(data: LoginData): LoginResult {
        val result = LoginResult()

        if (data.email.isBlank()) {
            result.error = "EMPTY EMAIL"
            return result
        }

        if (data.password.isBlank()) {
            result.error = "EMPTY PASSWORD"
            return result
        }

        if (data.password.length < 6) {
            result.error = "PASSWORD MINIMUM LENGTH"
            return result
        }

        if (data.password.length > 15) {
            result.error = "PASSWORD MAXIMUM LENGTH"
            return result
        }

        if (!isEmailValid(data.email)) {
            result.error = "EMAIL INVALID"
            return result
        }
        return result
    }

    fun isEmailValid(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }
}