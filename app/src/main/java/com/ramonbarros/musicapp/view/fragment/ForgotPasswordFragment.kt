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
import com.ramonbarros.musicapp.databinding.FragmentForgotPasswordBinding
import com.ramonbarros.musicapp.databinding.FragmentRegisterBinding
import com.ramonbarros.musicapp.domain.LoginData
import com.ramonbarros.musicapp.domain.LoginResult
import com.ramonbarros.musicapp.viewmodel.ForgotPasswordViewModel
import com.ramonbarros.musicapp.viewmodel.RegisterViewModel

class ForgotPasswordFragment : Fragment() {

    lateinit var binding: FragmentForgotPasswordBinding
    lateinit var navController: NavController
    lateinit var ctx: Context;
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        navController = findNavController()
        binding.forgotPasswordFragment = this
        binding.lifecycleOwner = this
        viewModel.screenResult.observe(viewLifecycleOwner) { resetPassResult ->
            proccessResetPassword(resetPassResult)
        }


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    fun proccessResetPassword(res: LoginResult){
        if (res.error != "") {
            Toast.makeText(context, res.error, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(context, R.string.passwordReset, Toast.LENGTH_LONG).show()
        navController.navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
    }

    fun resetPassword(){
        val email = binding.etEmailForgot.text.toString()
        val data = LoginData(email, "1020304050")

        viewModel.resetPassword(data, ctx)
    }

}