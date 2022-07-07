package com.alok.twitter.model.service.module

import com.alok.twitter.model.service.AccountService
import com.alok.twitter.model.service.LogService
import com.alok.twitter.model.service.StorageService
import com.alok.twitter.model.service.impl.AccountServiceImpl
import com.alok.twitter.model.service.impl.LogServiceImpl
import com.alok.twitter.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}