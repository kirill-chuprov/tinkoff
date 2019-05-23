package com.tinkoff.task.repository.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tinkoff.task.repository.data.remote.entity.LocationR
import com.tinkoff.task.repository.data.remote.entity.toDomain
import com.tinkoff.task.repository.domain.entity.DepositePoint

/**
 * Created by Kirill Chuprov on 5/22/19.
 */

@Entity(
  tableName = "deposite_points",
  foreignKeys = [
    ForeignKey(
      entity = PartnerL::class,
      parentColumns = ["partnerId"],
      childColumns = ["partnerName"],
      onDelete = CASCADE
    ), ForeignKey(
      entity = PartnerL::class,
      parentColumns = ["picture"],
      childColumns = ["imgUrl"],
      onDelete = CASCADE
    )], indices = [Index(value = ["partnerName", "imgUrl"], unique = true)]
)

data class DepositePointL(
  @PrimaryKey
  val externalId: Int = Int.MIN_VALUE,
  @ColumnInfo(name = "partnerName")
  val partnerName: String = "",
  @ColumnInfo(name = "imgUrl", index = true)
  val imgUrl: String = "",
  val hasSeen: Boolean = false,
  val location: LocationR = LocationR(),
  val workHours: String = "",
  val addressInfo: String = "",
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