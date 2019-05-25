package com.tinkoff.task.repository.domain.entity

import com.tinkoff.task.repository.data.local.entity.DepositePointL

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

data class DepositePoint(
  var externalId: Int = Int.MIN_VALUE,
  var partnerName: String = "",
  var picture: String = "",
  var hasSeen: Boolean = false,
  var location: Location = Location(),
  var workHours: String = "",
  var addressInfo: String = "",
  var fullAddress: String = "",
  var verificationInfo: String = ""
) {
  companion object {
    val empty = DepositePoint()
  }
}

fun DepositePoint.toLocal() =
  DepositePointL(
    externalId = externalId,
    partnerName = partnerName,
    hasSeen = hasSeen,
    location = location.toLocal(),
    workHours = workHours,
    addressInfo = addressInfo,
    fullAddress = fullAddress,
    verificationInfo = verificationInfo

  )