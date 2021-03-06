package com.tinkoff.task.repository.data.remote.datasource

import com.tinkoff.task.repository.data.remote.api.TinkoffApi
import com.tinkoff.task.repository.data.remote.entity.toDomain
import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
class RemoteDepositePointsDataSource(private val api: TinkoffApi) : DepositePointsDataSource {
  override fun getDepositePoint(fullAddress: String): Single<DepositePoint> =
    throw UnsupportedOperationException()

  override fun saveDepositePoints(depositePoints: List<DepositePoint>): Completable =
    throw UnsupportedOperationException()

  override fun runCleanPointsTask(): Completable = throw UnsupportedOperationException()

  override fun getDepositePointsAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> =
    api.getDepositionPoints(latitude = latitude, longitude = longitude, radius = radius)
      .map { response -> response.payload?.map { it.toDomain() } }

  override fun observeDepositePoints(): Observable<List<DepositePoint>> =
    throw UnsupportedOperationException()

}