package com.tinkoff.task.repository.data.repositoryImpl

import com.tinkoff.task.repository.domain.datasource.EntityDataSource
import com.tinkoff.task.repository.domain.entity.Entity
import com.tinkoff.task.repository.domain.repository.EntitiesRepository
import io.reactivex.Observable

class EntitiesRepositoryImpl(
  private val localSource: EntityDataSource,
  private val remoteSource: EntityDataSource
) : EntitiesRepository {

  private val entitiesObservable: Observable<List<Entity>> = localSource.observeEntities().share()

    override fun observeEntities(): Observable<List<Entity>> = entitiesObservable

}