package com.tinkoff.task.repository.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.tinkoff.task.repository.domain.entity.DepositePoint

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

@Entity(
  tableName = "deposite_points",
  foreignKeys = [
    ForeignKey(
      entity = PartnerL::class,
      parentColumns = ["id"],
      childColumns = ["partnerName"],
      onDelete = CASCADE
    )]
)
data class DepositePointL(
  val externalId: Int = Int.MIN_VALUE,
  val partnerName: String = "",
  val imgUrl: String = "",
  val hasSeen: Boolean = false,
  val location: LocationL = LocationL(),
  val workHours: String = "",
  val addressInfo: String = "",
  @PrimaryKey
  val fullAddress: String = "",
  val verificationInfo: String = ""
)

fun DepositePointL.toDomain() = DepositePoint(
  externalId = externalId,
  partnerName = partnerName,
  picture = imgUrl,
  hasSeen = hasSeen,
  location = location.toDomain(),
  workHours = workHours,
  addressInfo = addressInfo,
  fullAddress = fullAddress,
  verificationInfo = verificationInfo
)