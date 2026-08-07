package com.voxchat.messenger.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.voxchat.messenger.databinding.ActivityRegisterBinding
import com.voxchat.messenger.domain.viewmodel.RegisterState
import com.voxchat.messenger.domain.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collectLatest { state ->
                    when (state) {
                        is RegisterState.Idle -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        is RegisterState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.registerButton.isEnabled = false
                        }
                        is RegisterState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.registerButton.isEnabled = true
                            Snackbar.make(binding.root, "Регистрация успешна!", Snackbar.LENGTH_LONG).show()
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        }
                        is RegisterState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.registerButton.isEnabled = true
                            Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.registerButton.setOnClickListener {
            val displayName = binding.displayNameEditText.text.toString().trim()
            val username = binding.usernameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()
            
            if (displayName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Snackbar.make(binding.root, "Заполните все поля", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            if (password.length < 6) {
                Snackbar.make(binding.root, "Пароль должен быть не менее 6 символов", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            
            if (password != confirmPassword) {
                Snackbar.make(binding.root, "Пароли не совпадают", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            
            viewModel.register(username, displayName, password)
        }
        
        binding.loginTextView.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
