package com.tinkoff.task.repository.di

import android.content.Context.MODE_PRIVATE
import com.tinkoff.task.repository.data.local.datasource.EntityLocalSource
import com.tinkoff.task.repository.data.local.datasource.LocalDepositePointsDataSource
import com.tinkoff.task.repository.data.local.db.TinkoffDbProvider
import com.tinkoff.task.repository.data.remote.api.TinkoffApiProvider
import com.tinkoff.task.repository.data.remote.datasource.EntityRemoteSource
import com.tinkoff.task.repository.data.remote.datasource.RemoteDepositePointsDataSource
import com.tinkoff.task.repository.data.repositoryImpl.EntitiesRepositoryImpl
import com.tinkoff.task.repository.data.repositoryImpl.MapsRepositoryImpl
import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.datasource.EntityDataSource
import com.tinkoff.task.repository.domain.interactors.GetDepositePointAroundUseCase
import com.tinkoff.task.repository.domain.interactors.ObserveEntitiesUseCase
import com.tinkoff.task.repository.domain.repository.EntitiesRepository
import com.tinkoff.task.repository.domain.repository.MapsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val repoModule = module {

  single { TinkoffDbProvider.getInstance(androidContext()).entityDao() }

  single { TinkoffApiProvider.createApi() }

  single { androidContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE) }

  single<EntityDataSource>("local") { EntityLocalSource(get()) }
  single<EntityDataSource>("remote") { EntityRemoteSource(get()) }

  single<DepositePointsDataSource>("local") { LocalDepositePointsDataSource() }
  single<DepositePointsDataSource>("remote") { RemoteDepositePointsDataSource(get()) }

  single<MapsRepository> { MapsRepositoryImpl(get("remote"), get("local")) }

  single<EntitiesRepository> { EntitiesRepositoryImpl(get("local"), get("remote")) }

  factory { ObserveEntitiesUseCase(get()) }
  factory { GetDepositePointAroundUseCase(get()) }

}