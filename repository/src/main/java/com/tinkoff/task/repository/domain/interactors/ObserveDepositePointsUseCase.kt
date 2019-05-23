package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
class ObserveDepositePointsUseCase(private val depositePointsRepository: DepositePointsRepository) {
  fun observeDepositePoints(): Observable<List<DepositePoint>> = depositePointsRepository.observeDepositePoints()
}