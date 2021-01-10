package com.ramonbarros.musicapp.view.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ramonbarros.musicapp.R
import com.ramonbarros.musicapp.databinding.FragmentLoginBinding
import com.ramonbarros.musicapp.domain.LoginData
import com.ramonbarros.musicapp.domain.LoginResult
import com.ramonbarros.musicapp.viewmodel.LoginViewModel


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var  navController: NavController
    private val viewModel: LoginViewModel by viewModels()
    lateinit var ctx: Context;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginFragment = this
        binding.lifecycleOwner = this
        navController = findNavController()
        viewModel.screenResult.observe(viewLifecycleOwner) { loginResult ->
            proccessLoginResult(loginResult)
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    fun proccessLoginResult(result: LoginResult) {
        if (result.error != "") {
            Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(context, R.string.connectionStabilished, Toast.LENGTH_LONG).show()
        navController.navigate(R.id.action_loginFragment_to_musicSearchFragment)
    }

    fun login(){
        var email = binding.etEmailLogin.text.toString()
        var password = binding.etPasswordLogin.text.toString()
        var data = LoginData(email, password)

        viewModel.login(data, ctx)

    }

    fun navToRegister(){
        navController.navigate(R.id.action_loginFragment_to_registerFragment)

    }
}