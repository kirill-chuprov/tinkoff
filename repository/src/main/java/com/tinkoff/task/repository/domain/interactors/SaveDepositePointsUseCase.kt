package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import io.reactivex.Observable

class SaveDepositePointsUseCase(private val depositePointsRepository: DepositePointsRepository) {
  fun saveDepositePointsUseCase(depositePoints: List<DepositePoint>): Observable<List<DepositePoint>> =
    depositePointsRepository.saveDepositePoints(depositePoints).toObservable()
}