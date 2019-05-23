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
  indices = [Index(value = ["picture"], unique = true), Index(value = ["partnerId"], unique = true)]
)
data class PartnerL(
  @ColumnInfo(name = "partnerId")
  val partnerId: String = "",
  val name: String = "",
  @ColumnInfo(name = "picture")
  val picture: String = "",
  val url: String = "",
  val limitations: String = "",
  val depositionDuration: String = "",
  val pointType: String = "",
  val description: String = "",
  val moneyMin: Int = Int.MIN_VALUE,
  val moneyMax: Int = Int.MIN_VALUE
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}

fun PartnerL.toDomain() = Partner(
  partnerId = partnerId,
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