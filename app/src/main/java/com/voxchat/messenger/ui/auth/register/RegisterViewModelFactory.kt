package com.voxchat.messenger.ui.auth.register

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
object RegisterViewModelFactory {

    @Provides
    @ViewModelScoped
    fun provideRegisterViewModel(@ApplicationContext context: Context): RegisterViewModel {
        val secureStorage = SecureStorage(context)
        return RegisterViewModel(secureStorage)
    }
}
