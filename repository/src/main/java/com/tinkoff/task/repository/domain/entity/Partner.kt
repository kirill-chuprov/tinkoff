package com.tinkoff.task.repository.domain.entity

import com.tinkoff.task.repository.data.local.entity.PartnerL

/**
 * Created by Kirill Chuprov on 5/23/19.
 */

data class Partner(
  var partnerId: String ="",
  var name: String = "",
  var picture: String = "",
  var url: String = "",
  var depositionDuration: String = "",
  var limitations: String = "",
  var pointType: String = "",
  var description: String = "",
  var moneyMin: Int = Int.MIN_VALUE,
  var moneyMax: Int = Int.MIN_VALUE
)

fun Partner.toLocal() =
  PartnerL(
    partnerId = partnerId,
    name = name,
    picture = picture,
    url = url,
    depositionDuration = depositionDuration,
    limitations = limitations,
    pointType = pointType,
    description = description,
    moneyMin = moneyMin,
    moneyMax = moneyMax
  )