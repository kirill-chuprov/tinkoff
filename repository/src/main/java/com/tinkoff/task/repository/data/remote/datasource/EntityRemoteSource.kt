package com.tinkoff.task.repository.data.remote.datasource

import com.tinkoff.task.repository.data.remote.api.TinkoffApi
import com.tinkoff.task.repository.domain.datasource.EntityDataSource
import com.tinkoff.task.repository.domain.entity.Entity
import io.reactivex.Observable

class EntityRemoteSource(private val api: TinkoffApi) : EntityDataSource {

  override fun observeEntities(): Observable<List<Entity>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}