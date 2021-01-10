package com.ramonbarros.musicapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ramonbarros.musicapp.R
import com.ramonbarros.musicapp.databinding.FragmentRegisterBinding
import com.ramonbarros.musicapp.domain.LoginData
import com.ramonbarros.musicapp.domain.LoginResult
import com.ramonbarros.musicapp.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var  navController: NavController
    lateinit var ctx: Context;
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        navController = findNavController()
        binding.registerFragment = this
        binding.lifecycleOwner = this
        viewModel.screenResult.observe(viewLifecycleOwner) { registerResult ->
            proccessRegistrationResult(registerResult)
        }

        return binding.root
    }

    fun proccessRegistrationResult(result: LoginResult) {
        if (result.error != "") {
            Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(context, R.string.accountCreated, Toast.LENGTH_LONG).show()
        navController.navigate(R.id.action_registerFragment_to_musicSearchFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    fun register(){
        var email = binding.etEmailRegister.text.toString()
        var password = binding.etPasswordRegister.text.toString()
        var data = LoginData(email, password)

        viewModel.register(data, ctx)

    }
}