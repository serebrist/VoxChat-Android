package com.voxchat.messenger.ui.auth.login

import com.voxchat.messenger.data.manager.SecureStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    @ViewModelScoped
    fun provideLoginViewModel(secureStorage: SecureStorage): LoginViewModel {
        return LoginViewModel(secureStorage)
    }
}
