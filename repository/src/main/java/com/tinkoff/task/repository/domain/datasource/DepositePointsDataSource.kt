package com.tinkoff.task.repository.domain.datasource

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Partner
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

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

  fun saveDepositePoints(depositePoints: List<DepositePoint>): Completable

  fun getDepositePoint(fullAddress: String): Single<DepositePoint>
}