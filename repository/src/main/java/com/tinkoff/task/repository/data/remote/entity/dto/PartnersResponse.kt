package com.tinkoff.task.repository.data.remote.entity.dto

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.tinkoff.task.repository.data.remote.entity.DepositePointR
import com.tinkoff.task.repository.data.remote.entity.PartnerR

/**
 * Created by Kirill Chuprov on 5/23/19.
 */

@JsonObject
class PartnersResponse(
  @JsonField(name = ["resultCode"]) var resultCode: String? = null,
  @JsonField(name = ["payload"]) var payload: List<PartnerR>? = null,
  @JsonField(name = ["trackingId"]) var trackingId: String? = null
)