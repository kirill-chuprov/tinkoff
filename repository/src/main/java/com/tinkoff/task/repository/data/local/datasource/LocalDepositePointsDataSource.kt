package com.tinkoff.task.repository.data.local.datasource

import android.content.SharedPreferences
import com.tinkoff.task.repository.data.local.db.DepositePointDao
import com.tinkoff.task.repository.data.local.entity.toDomain
import com.tinkoff.task.repository.domain.datasource.DepositePointsDataSource
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.toLocal
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit.MILLISECONDS

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

private const val FIRST_CACHED_TIME = "FIRST_CACHED_TIME"
private const val WERE_DEPOSITE_POINTS_CASHED = "WERE_DEPOSITE_POINTS_CASHED"
private const val TEN_MINUTES = 10 * 60 * 1000

class LocalDepositePointsDataSource(
  private val depositePointDao: DepositePointDao,
  private val sharedPreferences: SharedPreferences
) :
  DepositePointsDataSource {

  override fun observeDepositePoints(): Observable<List<DepositePoint>> =
    depositePointDao.getAll().map { if (it.isEmpty()) emptyList() else it.map { it.toDomain() } }.toObservable()

  override fun getDepositePoint(fullAddress: String): Single<DepositePoint> =
    depositePointDao.getDepositePointsByAddress(fullAddress).map { it.toDomain() }
      .onErrorReturn { DepositePoint.empty }

  override fun saveDepositePoints(depositePoints: List<DepositePoint>): Completable =
    Completable.fromCallable {
      val points = depositePoints.map { it.toLocal() }
      depositePointDao.insertAll(points)

      val werePointsCashed = sharedPreferences.getBoolean(WERE_DEPOSITE_POINTS_CASHED, false)

      if (!werePointsCashed) {
        with(sharedPreferences.edit()) {
          putLong(FIRST_CACHED_TIME, System.currentTimeMillis()).apply()
          putBoolean(WERE_DEPOSITE_POINTS_CASHED, true).apply()
        }

      }
    }

  override fun getDepositePointsAround(
    longitude: Double,
    latitude: Double,
    radius: Int
  ): Observable<List<DepositePoint>> =
    Observable.just(emptyList())

  override fun runCleanPointsTask(): Completable {
    val firstCachedTime = sharedPreferences.getLong(FIRST_CACHED_TIME, 0L)
    val currentTime = System.currentTimeMillis()
    val difference = currentTime - firstCachedTime

    return if (difference > TEN_MINUTES) {
      Completable.fromAction {
        if (firstCachedTime != 0L) {
          sharedPreferences.edit().putBoolean(WERE_DEPOSITE_POINTS_CASHED, false).apply()
          depositePointDao.deleteAll()
        }
      }
    } else {
      Observable.timer(TEN_MINUTES - difference, MILLISECONDS)
        .flatMapCompletable {
          Completable.fromAction {
            sharedPreferences.edit().putBoolean(WERE_DEPOSITE_POINTS_CASHED, false).apply()
            depositePointDao.deleteAll()
          }
        }
        .observeOn(AndroidSchedulers.mainThread())
    }
  }
}