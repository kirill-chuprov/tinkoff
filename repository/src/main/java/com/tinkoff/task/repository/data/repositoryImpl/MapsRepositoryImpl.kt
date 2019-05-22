package com.tinkoff.task.repository.data.repositoryImpl

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.MapsRepository
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
class MapsRepositoryImpl : MapsRepository {
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