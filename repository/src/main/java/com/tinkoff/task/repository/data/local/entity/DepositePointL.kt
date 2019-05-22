package com.tinkoff.task.repository.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tinkoff.task.repository.data.remote.entity.LocationR
import com.tinkoff.task.repository.data.remote.entity.toDomain
import com.tinkoff.task.repository.domain.entity.DepositePoint

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

@Entity
data class DepositePointL(
  @PrimaryKey
  val externalId: Int = Int.MIN_VALUE,
  val partnerName: String = "",
  val location: LocationR = LocationR(),
  val workHours: String = "",
  val addressInfo: String = "",
  val fullAddress: String = "",
  val verificationInfo: String = ""
)

fun DepositePointL.toDomain() = DepositePoint(
  externalId = externalId,
  partnerName = partnerName,
  location = location.toDomain(),
  workHours = workHours,
  addressInfo = addressInfo,
  fullAddress = fullAddress,
  verificationInfo = verificationInfo
)