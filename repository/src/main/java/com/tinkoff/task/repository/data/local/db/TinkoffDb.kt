package com.tinkoff.task.repository.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tinkoff.task.repository.data.local.entity.EntityL

@Database(entities = [EntityL::class], version = 1, exportSchema = false)
@TypeConverters(DbTypeConverters::class)
abstract class TinkoffDb : RoomDatabase() {

  abstract fun entityDao(): EntityDao

}