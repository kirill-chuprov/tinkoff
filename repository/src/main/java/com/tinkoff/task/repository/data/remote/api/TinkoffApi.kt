package com.tinkoff.task.repository.data.remote.api

import com.tinkoff.task.repository.data.remote.entity.EntityR
import io.reactivex.Observable
import retrofit2.http.GET

interface TinkoffApi {

  @GET("path")
  fun getEntities(): Observable<List<EntityR>>
}