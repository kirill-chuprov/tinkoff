package com.tinkoff.task.repository.domain.repository

import com.tinkoff.task.repository.domain.entity.DepositePoint
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

interface MapsRepository {
  fun getSingleObject(id: Int): Observable<DepositePoint>

  fun getObjectsNearYou(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>>

  fun getObjectsInBoundaries(
    southwestLongitude: Double,
    northeastLatitude: Double,
    northeastLongitude: Double,
    southwestLatitude: Double,
    radius: Float
  ): Observable<List<DepositePoint>>
}