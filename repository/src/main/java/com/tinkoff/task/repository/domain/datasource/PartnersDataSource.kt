package com.tinkoff.task.repository.domain.datasource

import androidx.room.Query
import com.tinkoff.task.repository.domain.entity.Partner
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Kirill Chuprov on 5/23/19.
 */

interface PartnersDataSource {
  fun getPartners(accountType: String): Observable<List<Partner>>
  fun getPartnerById(id: String): Single<Partner>
  fun observePartners():Observable<List<Partner>>
  fun savePartners(list: List<Partner>): Completable
}