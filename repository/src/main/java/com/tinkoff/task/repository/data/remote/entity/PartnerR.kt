package com.tinkoff.task.repository.data.remote.entity

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.tinkoff.task.repository.domain.entity.Partner

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
@JsonObject
data class PartnerR(
  @JsonField(name = ["id"]) var id: String? = "",
  @JsonField(name = ["name"]) var name: String? = "",
  @JsonField(name = ["picture"]) var picture: String? = "",
  @JsonField(name = ["url"]) var url: String? = "",
  @JsonField(name = ["limitations"]) var limitations: String? = "",
  @JsonField(name = ["depositionDuration"]) var depositionDuration: String? = "",
  @JsonField(name = ["pointType"]) var pointType: String? = "",
  @JsonField(name = ["description"]) var description: String? = "",
  @JsonField(name = ["moneyMin"]) var moneyMin: Int? = Int.MIN_VALUE,
  @JsonField(name = ["moneyMax"]) var moneyMax: Int? = Int.MIN_VALUE

  )

fun PartnerR.toDomain() = Partner(
  id = id ?: "",
  name = name ?: "",
  picture = picture ?: "",
  url = url ?: "",
  limitations = limitations ?: "",
  depositionDuration = depositionDuration ?: "",
  pointType = pointType ?: "",
  description = description ?: "",
  moneyMin = moneyMin ?: Int.MIN_VALUE,
  moneyMax = moneyMin ?: Int.MIN_VALUE
)