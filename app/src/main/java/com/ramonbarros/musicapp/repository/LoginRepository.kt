package com.ramonbarros.musicapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.ramonbarros.musicapp.domain.LoginData
import com.ramonbarros.musicapp.domain.LoginResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository {
    suspend fun loginToFirebase(data: LoginData): LoginResult = suspendCoroutine {
        val loginResult = LoginResult()
        val firebaseAuth = FirebaseAuth.getInstance()
        val operation = firebaseAuth.signInWithEmailAndPassword(data.email, data.password)
        operation.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                loginResult.result = "LOGIN_FIREBASE_SUCCESS"
            } else {
                Log.w(TAG, "signInWithEmail:failure", result.exception)
                loginResult.error = result.exception.toString()
            }
            it.resume(loginResult) // resume is the return of suspend functions
        }
    }
    suspend fun registerToFirebase(data: LoginData): LoginResult = suspendCoroutine {
        val registerResult = LoginResult()
        val firebaseAuth = FirebaseAuth.getInstance()
        val operation = firebaseAuth.createUserWithEmailAndPassword(data.email, data.password)
        operation.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                registerResult.result = "LOGIN_FIREBASE_SUCCESS"
            } else {
                Log.w(TAG, "signInWithEmail:failure", result.exception)
                registerResult.error = result.exception.toString()
            }
        }



        it.resume(registerResult)
    }
}