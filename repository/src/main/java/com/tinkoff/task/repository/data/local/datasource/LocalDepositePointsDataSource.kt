package com.tinkoff.task.repository.data.local.datasource

import com.tinkoff.task.repository.data.local.db.DepositePointDao
import com.tinkoff.task.repository.data.local.entity.toDomain
import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.toLocal
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
class LocalDepositePointsDataSource(private val depositePointDao: DepositePointDao) :
  DepositePointsDataSource {
  override fun observeDepositePoints(): Observable<List<DepositePoint>> =
    depositePointDao.getAll().map { if (it.isEmpty()) emptyList() else it.map { it.toDomain() } }.toObservable()

  override fun saveDepositePoints(depositePoints: List<DepositePoint>): Completable =
    Completable.fromCallable {
      val points = depositePoints.map { it.toLocal() }
      depositePointDao.insertAll(points)
    }

  override fun saveCurrentDepositePointsOnMap(depositePoints: List<DepositePoint>): Completable {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getDepositePointsAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> =
    Observable.just(emptyList())
}