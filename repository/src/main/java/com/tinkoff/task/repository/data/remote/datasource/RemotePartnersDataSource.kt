package com.tinkoff.task.repository.data.remote.datasource

import com.tinkoff.task.repository.data.remote.api.TinkoffApi
import com.tinkoff.task.repository.data.remote.entity.toDomain
import com.tinkoff.task.repository.domain.datasource.PartnersDataSource
import com.tinkoff.task.repository.domain.entity.Partner
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
class RemotePartnersDataSource(private val api: TinkoffApi) :
  PartnersDataSource {
  override fun observePartners(): Observable<List<Partner>> = throw UnsupportedOperationException()

  override fun getPartnerById(id: String): Single<Partner> = throw UnsupportedOperationException()

  override fun savePartners(list: List<Partner>): Completable =
    throw UnsupportedOperationException()

  override fun getPartners(accountType: String): Observable<List<Partner>> =
    api.getPartners(accountType).map { response -> response.payload?.map { it.toDomain() } }

}