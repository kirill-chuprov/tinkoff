package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import io.reactivex.Completable

class RunCleanPointsTaskUseCase(private val depositePointsRepository: DepositePointsRepository) {
  fun runCleanPointsTaskUseCase(): Completable = depositePointsRepository.runCleanPointsTask()
}