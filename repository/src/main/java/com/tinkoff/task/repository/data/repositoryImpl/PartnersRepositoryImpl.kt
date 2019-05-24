package com.tinkoff.task.repository.data.repositoryImpl

import com.tinkoff.task.repository.domain.datasource.PartnersDataSource
import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.repository.domain.repository.PartnersRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

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

  override fun getPartnerById(id: String): Single<Partner> =
    localPartnersDataSource.getPartnerById(id)

  override fun observePartners(): Observable<List<Partner>> =
    localPartnersDataSource.observePartners()

}