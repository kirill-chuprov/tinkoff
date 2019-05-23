package com.tinkoff.task.repository.data.local.db

import androidx.room.TypeConverter
import com.bluelinelabs.logansquare.LoganSquare
import com.tinkoff.task.repository.data.remote.entity.LocationR
import com.tinkoff.task.repository.domain.entity.Location

class DbTypeConverters {

  @TypeConverter
  fun fromStringToLocation(value: String): LocationR =
    LoganSquare.parse(value, LocationR::class.java)

  @TypeConverter
  fun fromLocationToString(location: LocationR): String = LoganSquare.serialize(location)
}
