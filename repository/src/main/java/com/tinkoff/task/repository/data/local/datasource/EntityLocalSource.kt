package com.tinkoff.task.repository.data.local.datasource

import com.tinkoff.task.repository.data.local.db.EntityDao
import com.tinkoff.task.repository.data.local.entity.EntityL
import com.tinkoff.task.repository.domain.datasource.EntityDataSource
import com.tinkoff.task.repository.domain.entity.Entity
import io.reactivex.Observable

class EntityLocalSource(private val entityDao: EntityDao) : EntityDataSource {

  override fun observeEntities(): Observable<List<Entity>> =
    entityDao.getAll()
      .map { if (it.isEmpty()) emptyList() else it.map { item -> item.toDomain() } }
      .toObservable()

  private fun EntityL.toDomain() =
    Entity(
      id = id,
      data = data
    )

  private fun Entity.toLocal() =
    EntityL(
      id = id,
      data = data
    )
}