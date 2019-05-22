package com.tinkoff.task.repository.domain.entity

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

data class DepositePoint(
  var externalId: Int = Int.MIN_VALUE,
  var partnerName: String = "",
  var location: Location = Location(),
  var workHours: String = "",
  var addressInfo: String = "",
  var fullAddress: String = "",
  var verificationInfo: String = ""
)