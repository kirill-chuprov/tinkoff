package com.tinkoff.task.repository.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinkoff.task.repository.data.local.entity.PartnerL
import io.reactivex.Flowable

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
@Dao
interface PartnerDao {

  @Query("SELECT * FROM partners ORDER BY partnerId ASC")
  fun getAll(): Flowable<List<PartnerL>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(partners: List<PartnerL>): List<Long>
}