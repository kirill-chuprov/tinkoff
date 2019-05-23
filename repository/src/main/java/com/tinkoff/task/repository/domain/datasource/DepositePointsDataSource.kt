package com.tinkoff.task.repository.domain.datasource

import com.tinkoff.task.repository.domain.entity.DepositePoint
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
interface DepositePointsDataSource {
  fun getDepositePointsAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>>

  fun observeDepositePoints(): Observable<List<DepositePoint>>
}