package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.MapsRepository
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

class GetPointsInBoundariesUseCase constructor(private val mapsRepository: MapsRepository) {

  fun getObjectInBoundariesUseCase(
    southwestLongitude: Double,
    northeastLatitude: Double,
    northeastLongitude: Double,
    southwestLatitude: Double,
    radius: Float
  ): Observable<List<DepositePoint>> = mapsRepository.getObjectsInBoundaries(
    southwestLongitude, northeastLatitude, northeastLongitude, southwestLatitude, radius
  )

}