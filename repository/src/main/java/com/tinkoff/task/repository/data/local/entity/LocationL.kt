package com.tinkoff.task.repository.data.local.entity

import androidx.room.Entity
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.tinkoff.task.repository.domain.entity.Location

/**
 * Created by Kirill Chuprov on 5/22/19.
 */
@Entity
@JsonObject
data class LocationL(
  @JsonField var latitude: Double = Double.MIN_VALUE,
  @JsonField var longitude: Double = Double.MIN_VALUE
)

fun LocationL.toDomain() = Location(
  latitude = latitude,
  longitude = longitude
)



