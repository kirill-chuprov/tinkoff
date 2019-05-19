package com.tinkoff.task.repository.domain.datasource

import com.tinkoff.task.repository.domain.entity.Entity
import io.reactivex.Observable

interface EntityDataSource {
  fun observeEntities(): Observable<List<Entity>>
}
