package com.tinkoff.task.repository.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tinkoff.task.repository.data.local.entity.DepositePointL
import com.tinkoff.task.repository.data.local.entity.PartnerL

@Database(
  entities = [DepositePointL::class, PartnerL::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(DbTypeConverters::class)
abstract class TinkoffDb : RoomDatabase() {

  abstract fun depositePointDao(): DepositePointDao
  abstract fun partnerDao(): PartnerDao

}