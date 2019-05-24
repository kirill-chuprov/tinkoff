package com.tinkoff.task.repository.data.local.db

import androidx.room.TypeConverter
import com.bluelinelabs.logansquare.LoganSquare
import com.tinkoff.task.repository.data.local.entity.LocationL

class DbTypeConverters {

  @TypeConverter
  fun fromStringToLocation(value: String): LocationL =
    LoganSquare.parse(value, LocationL::class.java)

  @TypeConverter
  fun fromLocationToString(location: LocationL): String = LoganSquare.serialize(location)
}
