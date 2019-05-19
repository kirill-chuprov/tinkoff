package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.Entity
import com.tinkoff.task.repository.domain.repository.EntitiesRepository
import io.reactivex.Observable

class ObserveEntitiesUseCase(private val repository: EntitiesRepository) {

  fun execute(): Observable<List<Entity>> = repository.observeEntities()
}
