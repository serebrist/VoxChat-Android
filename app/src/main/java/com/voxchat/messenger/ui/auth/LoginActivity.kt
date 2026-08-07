package com.voxchat.messenger.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.voxchat.messenger.R
import com.voxchat.messenger.databinding.ActivityLoginBinding
import com.voxchat.messenger.domain.viewmodel.LoginState
import com.voxchat.messenger.domain.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collectLatest { state ->
                    when (state) {
                        is LoginState.Idle -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        is LoginState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.loginButton.isEnabled = false
                        }
                        is LoginState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.loginButton.isEnabled = true
                            // Переход к списку чатов
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        is LoginState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.loginButton.isEnabled = true
                            Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        }
                        is LoginState.NavigateToRegister -> {
                            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                        }
                    }
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val jid = binding.jidEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            
            if (jid.isEmpty() || password.isEmpty()) {
                Snackbar.make(binding.root, "Заполните все поля", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            viewModel.login(jid, password)
        }
        
        binding.registerTextView.setOnClickListener {
            viewModel.navigateToRegister()
        }
    }
}
