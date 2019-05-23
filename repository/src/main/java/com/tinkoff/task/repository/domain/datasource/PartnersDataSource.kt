package com.tinkoff.task.repository.domain.datasource

import com.tinkoff.task.repository.domain.entity.Partner
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/23/19.
 */

interface PartnersDataSource {
  fun getPartners(accountType: String): Observable<List<Partner>>
  fun savePartners(list: List<Partner>): Completable
}