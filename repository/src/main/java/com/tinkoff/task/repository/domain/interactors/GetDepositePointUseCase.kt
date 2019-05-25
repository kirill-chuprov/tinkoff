package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import io.reactivex.Single

class GetDepositePointUseCase(private val depositePointsRepository: DepositePointsRepository) {
  fun getDepositePointUseCase(fullAddress: String): Single<DepositePoint> =
    depositePointsRepository.getDepositePoint(fullAddress)
}