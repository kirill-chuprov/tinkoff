package com.tinkoff.task.repository.data.local.entity

import androidx.room.Entity
import com.tinkoff.task.repository.domain.entity.Location

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
@Entity
data class LocationL(
  var latitude: Double = Double.MIN_VALUE,
  var longitude: Double = Double.MIN_VALUE
)

fun LocationL.toDomain() = Location(
  latitude = latitude,
  longitude = longitude
)



