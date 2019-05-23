package com.tinkoff.task.repository.di

import android.content.Context.MODE_PRIVATE
import com.tinkoff.task.repository.data.local.datasource.LocalDepositePointsDataSource
import com.tinkoff.task.repository.data.local.datasource.LocalPartnersDataSource
import com.tinkoff.task.repository.data.local.db.TinkoffDbProvider
import com.tinkoff.task.repository.data.remote.api.TinkoffApiProvider
import com.tinkoff.task.repository.data.remote.datasource.RemoteDepositePointsDataSource
import com.tinkoff.task.repository.data.remote.datasource.RemotePartnersDataSource
import com.tinkoff.task.repository.data.repositoryImpl.DepositePointsRepositoryImpl
import com.tinkoff.task.repository.data.repositoryImpl.PartnersRepositoryImpl
import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.datasource.PartnersDataSource
import com.tinkoff.task.repository.domain.interactors.GetDepositePointAroundUseCase
import com.tinkoff.task.repository.domain.interactors.GetPartnersUseCase
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import com.tinkoff.task.repository.domain.repository.PartnersRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val repoModule = module {

  single { TinkoffDbProvider.getInstance(androidContext()).depositePointDao() }
  single { TinkoffDbProvider.getInstance(androidContext()).partnerDao() }

  single { TinkoffApiProvider.createApi() }

  single { androidContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE) }

  single<DepositePointsDataSource>("local") { LocalDepositePointsDataSource(get()) }
  single<DepositePointsDataSource>("remote") { RemoteDepositePointsDataSource(get()) }

  single<PartnersDataSource>("local") { LocalPartnersDataSource(get()) }
  single<PartnersDataSource>("remote") { RemotePartnersDataSource(get()) }

  single<DepositePointsRepository> { DepositePointsRepositoryImpl(get("remote"), get("local")) }
  single<PartnersRepository> { PartnersRepositoryImpl(get("remote"), get("local")) }

  factory { GetDepositePointAroundUseCase(get()) }
  factory { GetPartnersUseCase(get()) }

}