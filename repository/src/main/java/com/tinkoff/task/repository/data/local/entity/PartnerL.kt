package com.tinkoff.task.repository.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tinkoff.task.repository.domain.entity.Partner

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
@Entity(
  tableName = "partners",
  indices = [Index(value = ["id"], unique = true)]
)
data class PartnerL(
  @PrimaryKey
  val id: String = "",
  val name: String = "",
  val picture: String = "",
  val url: String = "",
  val limitations: String = "",
  val depositionDuration: String = "",
  val pointType: String = "",
  val description: String = "",
  val moneyMin: Int = Int.MIN_VALUE,
  val moneyMax: Int = Int.MIN_VALUE
)

fun PartnerL.toDomain() = Partner(
  id = id,
  name = name,
  picture = picture,
  url = url,
  limitations = limitations,
  depositionDuration = depositionDuration,
  pointType = pointType,
  description = description,
  moneyMin = moneyMin,
  moneyMax = moneyMin
)