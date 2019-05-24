package com.tinkoff.task.repository.data.local.db

import android.content.Context
import androidx.room.Room

class TinkoffDbProvider {
  companion object {

    @Volatile private var INSTANCE: TinkoffDb? = null

    fun getInstance(context: Context): TinkoffDb =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
      }

    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(context, TinkoffDb::class.java, "TinkoffDatabase")
        .allowMainThreadQueries()
        .build()
  }}