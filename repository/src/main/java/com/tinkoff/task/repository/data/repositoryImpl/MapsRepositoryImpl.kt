package com.tinkoff.task.repository.data.repositoryImpl

import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.MapsRepository
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
class MapsRepositoryImpl(
  val remoteDepositePointsDataSource: DepositePointsDataSource,
  val localDepositePointsDataSource: DepositePointsDataSource
) : MapsRepository {

  override fun getDepositePointsAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> = remoteDepositePointsDataSource.getDepositePointsAround(longitude,latitude,radius)

}