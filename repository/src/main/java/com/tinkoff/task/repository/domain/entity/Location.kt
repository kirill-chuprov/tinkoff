package com.tinkoff.task.repository.domain.entity

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

data class Location(
  val latitude: Double = Double.MIN_VALUE,
  val longitude: Double = Double.MIN_VALUE
)