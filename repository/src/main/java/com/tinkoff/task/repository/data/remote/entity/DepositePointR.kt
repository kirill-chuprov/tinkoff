package com.tinkoff.task.repository.data.remote.entity

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Location

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

@JsonObject
data class DepositePointR(
  @JsonField(name = ["externalId"]) var externalId: Int? = Int.MIN_VALUE,
  @JsonField(name = ["partnerName"]) var partnerName: String? = "",
  @JsonField(name = ["location"]) var location: LocationR? = LocationR(),
  @JsonField(name = ["workHours"]) var workHours: String? = "",
  @JsonField(name = ["addressInfo"]) var addressInfo: String? = "",
  @JsonField(name = ["fullAddress"]) var fullAddress: String? = "",
  @JsonField(name = ["verificationInfo"]) var verificationInfo: String? = ""

)

fun DepositePointR.toDomain() = DepositePoint(
  externalId = externalId ?: Int.MIN_VALUE,
  partnerName = partnerName ?: "",
  location = if (location != null) location!!.toDomain() else Location(),
  workHours = workHours ?: "",
  addressInfo = addressInfo ?: "",
  fullAddress = fullAddress ?: "",
  verificationInfo = verificationInfo ?: ""
)