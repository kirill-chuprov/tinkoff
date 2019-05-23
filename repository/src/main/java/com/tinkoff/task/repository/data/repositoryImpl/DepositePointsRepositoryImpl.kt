package com.tinkoff.task.repository.data.repositoryImpl

import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
class DepositePointsRepositoryImpl(
  private val remoteDepositePointsDataSource: DepositePointsDataSource,
  private val localDepositePointsDataSource: DepositePointsDataSource
) : DepositePointsRepository {

  override fun getDepositePointsAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> = remoteDepositePointsDataSource.getDepositePointsAround(longitude,latitude,radius)

  override fun observeDepositePoints(): Observable<List<DepositePoint>>  = localDepositePointsDataSource.observeDepositePoints()

}