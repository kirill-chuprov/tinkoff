package com.tinkoff.task.repository.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinkoff.task.repository.data.local.entity.DepositePointL
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Kirill Chuprov on 5/23/19.
 */

@Dao
interface DepositePointDao {
  @Query("SELECT * FROM deposite_points ORDER BY externalId ASC")
  fun getAll(): Flowable<List<DepositePointL>>

  @Query("SELECT * FROM deposite_points WHERE fullAddress = :fullAddress")
  fun getDepositePointsByAddress(fullAddress: String): Single<DepositePointL>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(depositePoints: List<DepositePointL>): List<Long>

  @Query("DELETE FROM deposite_points")
  fun deleteAll(): Int
}