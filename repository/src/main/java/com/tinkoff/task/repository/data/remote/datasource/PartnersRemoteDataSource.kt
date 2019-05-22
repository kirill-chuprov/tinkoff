package com.tinkoff.task.repository.data.remote.datasource

import com.tinkoff.task.repository.data.remote.api.TinkoffApi
import com.tinkoff.task.repository.domain.datasource.PartnersDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
class PartnersRemoteDataSource(private val api: TinkoffApi) : PartnersDataSource {
  override fun getSingleObject(id: Int): Observable<DepositePoint> {
    TODO("not implemented")
  }

  override fun getObjectsNearYou(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> {
    TODO("not implemented")
  }

  override fun getObjectsInBoundaries(
    southwestLongitude: Double,
    northeastLatitude: Double,
    northeastLongitude: Double,
    southwestLatitude: Double,
    radius: Float
  ): Observable<List<DepositePoint>> {
    TODO("not implemented")
  }
}