package com.tinkoff.task.repository.data.remote.entity

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.tinkoff.task.repository.domain.entity.Location

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

@JsonObject
data class LocationR(
  @JsonField(name = ["latitude"]) var latitude: Double? = Double.MIN_VALUE,
  @JsonField(name = ["longitude"]) var longitude: Double? = Double.MIN_VALUE
)

fun LocationR.toDomain() = Location(
  latitude = latitude ?: Double.MIN_VALUE,
  longitude = latitude ?: Double.MIN_VALUE
)