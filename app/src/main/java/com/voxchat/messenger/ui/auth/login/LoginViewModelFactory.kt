package com.voxchat.messenger.ui.auth.login

import android.content.Context
import com.voxchat.messenger.data.manager.SecureStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LoginViewModelFactory {

    @Provides
    @ViewModelScoped
    fun provideLoginViewModel(@ApplicationContext context: Context): LoginViewModel {
        val secureStorage = SecureStorage(context)
        return LoginViewModel(secureStorage)
    }
}
