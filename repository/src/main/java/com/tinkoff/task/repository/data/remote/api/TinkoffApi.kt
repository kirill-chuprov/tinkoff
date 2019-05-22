package com.tinkoff.task.repository.data.remote.api

import com.tinkoff.task.repository.data.remote.entity.dto.DepositePointsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TinkoffApi {

  @GET("api/v2/deposition_points")
  fun getDepositionPoints(
    @Query("latitude") latitude: String,
    @Query("longitude") language: String,
    @Query("radius") radius: String
  ): Observable<List<DepositePointsResponse>>
}