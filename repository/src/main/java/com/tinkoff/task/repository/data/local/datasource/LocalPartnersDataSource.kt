package com.tinkoff.task.repository.data.local.datasource

import com.tinkoff.task.repository.data.local.db.PartnerDao
import com.tinkoff.task.repository.data.local.entity.toDomain
import com.tinkoff.task.repository.domain.datasource.PartnersDataSource
import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.repository.domain.entity.toLocal
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
class LocalPartnersDataSource(private val partnersDao: PartnerDao) : PartnersDataSource {
  override fun getPartners(accountType: String): Observable<List<Partner>> =
    partnersDao.getAll().map { if (it.isEmpty()) emptyList() else it.map { it.toDomain() } }.toObservable()

  override fun getPartnerById(id: String): Single<Partner> =
    partnersDao.findPartnerById(id)
      .map { it.toDomain() }
      .onErrorReturn { Partner.empty }

  override fun observePartners(): Observable<List<Partner>> =
    partnersDao.getAll().map { if (it.isEmpty()) emptyList() else it.map { it.toDomain() } }.toObservable()

  override fun savePartners(list: List<Partner>): Completable =
    Completable.fromCallable {
      val partners = list.map { it.toLocal() }
      partnersDao.insertAll(partners)
    }
}