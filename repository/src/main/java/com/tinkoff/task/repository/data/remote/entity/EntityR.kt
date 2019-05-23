package com.tinkoff.task.repository.data.remote.entity

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class EntityR(
  @JsonField(name = ["partnerId"]) var id: String? = "",
  @JsonField(name = ["data"]) var data: String? = ""
)