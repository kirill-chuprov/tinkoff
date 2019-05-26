package com.tinkoff.task.repository.data.repositoryImpl

import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.datasource.PartnersDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.repository.DepositePointsRepository
import io.reactivex.Completable
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.Single

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
  ): Observable<List<DepositePoint>> =
    remoteDepositePointsDataSource.getDepositePointsAround(longitude, latitude, radius)
      .flatMap { depositePoints ->
        localDepositePointsDataSource.saveDepositePoints(depositePoints)
          .toSingleDefault(Notification.createOnComplete<Any>())
          .toObservable()
          .flatMap { Observable.just(depositePoints) }
      }

  override fun observeDepositePoints(): Observable<List<DepositePoint>> =
    localDepositePointsDataSource.observeDepositePoints()

  override fun saveDepositePoints(depositePoints: List<DepositePoint>): Completable =
    localDepositePointsDataSource.saveDepositePoints(depositePoints)

  override fun getDepositePoint(fullAddress: String): Single<DepositePoint>  = localDepositePointsDataSource.getDepositePoint(fullAddress)

  override fun runCleanPointsTask(): Completable = localDepositePointsDataSource.runCleanPointsTask()
}
