package com.tinkoff.task.repository.data.remote.api

import com.tinkoff.task.repository.data.remote.entity.dto.DepositePointsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val V1 = "v1"

interface TinkoffApi {

  @GET("$V1/deposition_points")
  fun getDepositionPoints(
    @Query("longitude") longitude: Double,
    @Query("latitude") latitude: Double,
    @Query("radius") radius: Int
  ): Observable<DepositePointsResponse>
}