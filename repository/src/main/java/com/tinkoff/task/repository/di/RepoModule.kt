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
import com.tinkoff.task.repository.domain.interactors.GetDepositePointUseCase
import com.tinkoff.task.repository.domain.interactors.GetPartnerForPointUseCase
import com.tinkoff.task.repository.domain.interactors.GetPartnersUseCase
import com.tinkoff.task.repository.domain.interactors.ObserveDepositePointsUseCase
import com.tinkoff.task.repository.domain.interactors.ObservePartnersUseCase
import com.tinkoff.task.repository.domain.interactors.SaveDepositePointsUseCase
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import com.tinkoff.task.repository.domain.repository.PartnersRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

const val mdpi = "mdpi"
const val hdpi = "hdpi"
const val xhdpi = "xhdpi"
const val xxhdpi = "xxhdpi"

val repoModule = module {

  single { TinkoffDbProvider.getInstance(androidContext()).depositePointDao() }
  single { TinkoffDbProvider.getInstance(androidContext()).partnerDao() }

  single("density") {
    when (androidApplication().resources.displayMetrics.density) {
      1.0f -> mdpi
      1.5f -> hdpi
      2.0f -> xhdpi
      else -> xxhdpi
    }
  }

  single { TinkoffApiProvider.createApi() }

  single { androidContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE) }

  single<DepositePointsDataSource>("local") { LocalDepositePointsDataSource(get()) }
  single<DepositePointsDataSource>("remote") { RemoteDepositePointsDataSource(get()) }

  single<PartnersDataSource>("localPartners") { LocalPartnersDataSource(get()) }
  single<PartnersDataSource>("remote") { RemotePartnersDataSource(get()) }

  single<DepositePointsRepository> {
    DepositePointsRepositoryImpl(
      get("remote"),
      get("local")
    )
  }
  single<PartnersRepository> { PartnersRepositoryImpl(get("remote"), get("localPartners")) }

  factory { GetDepositePointAroundUseCase(get()) }
  factory { GetPartnersUseCase(get()) }
  factory { ObserveDepositePointsUseCase(get()) }
  factory { SaveDepositePointsUseCase(get()) }
  factory { ObservePartnersUseCase(get()) }
  factory { GetDepositePointUseCase(get()) }
  factory { GetPartnerForPointUseCase(get()) }

}