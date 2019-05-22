package com.tinkoff.task.repository.data.remote.entity.dto

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.tinkoff.task.repository.data.remote.entity.DepositePointR

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

@JsonObject
class DepositePointsResponse(
  @JsonField(name = ["resultCode"]) var resultCode: String? = null,
  @JsonField(name = ["payload"]) var payload: List<DepositePointR>? = null,
  @JsonField(name = ["trackingId"]) var trackingId: String? = null
)