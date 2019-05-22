package com.tinkoff.task.repository.data.local.datasource

import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
class LocalDepositePointsDataSource : DepositePointsDataSource {

  override fun getDepositePointsAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> =
    Observable.just(emptyList())
}