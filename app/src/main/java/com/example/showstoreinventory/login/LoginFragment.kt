package com.example.showstoreinventory.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.showstoreinventory.R
import com.example.showstoreinventory.databinding.FragmentLoginBinding
import com.example.showstoreinventory.model.LoginState

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel = this@LoginFragment.viewModel
            lifecycleOwner = this@LoginFragment
            buttonLogin.setOnClickListener {
                this@LoginFragment.viewModel.logIn()
            }
            buttonLoginExistingAccount.setOnClickListener {
                this@LoginFragment.viewModel.logInWithExistAccount()
            }
        }
        viewModel.logInSuccessful.observe(viewLifecycleOwner) { loginState ->
            when (loginState) {
                LoginState.SUCCESS -> {
                    findNavController().navigate(LoginFragmentDirections.toWelcomeFragment())
                }

                else -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.please_enter_valid_email_and_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}