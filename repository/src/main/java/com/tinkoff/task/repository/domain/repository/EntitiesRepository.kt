package com.tinkoff.task.repository.domain.repository

import com.tinkoff.task.repository.domain.entity.Entity
import io.reactivex.Observable

interface EntitiesRepository {

  fun observeEntities(): Observable<List<Entity>>

}