package com.tinkoff.task.repository.data.repositoryImpl

import com.tinkoff.task.repository.domain.datasource.PartnersDataSource
import com.tinkoff.task.repository.domain.repository.PartnersRepository
import io.reactivex.Completable

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
class PartnersRepositoryImpl(
  private val remotePartnersDataSource: PartnersDataSource,
  private val localPartnersDataSource: PartnersDataSource
) : PartnersRepository {
  override fun getPartners(accountType: String): Completable =
    remotePartnersDataSource.getPartners(accountType)
      .flatMapCompletable { localPartnersDataSource.savePartners(it) }

}