package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

class GetDepositePointAroundUseCase(private val depositePointsRepository: DepositePointsRepository) {

  fun getDepositePointAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> =
    depositePointsRepository.getDepositePointsAround(longitude, latitude, radius)
}