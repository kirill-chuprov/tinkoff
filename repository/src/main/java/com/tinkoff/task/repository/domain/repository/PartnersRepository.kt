package com.tinkoff.task.repository.domain.repository

import android.provider.Telephony.Mms.Part
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Partner
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
interface PartnersRepository {

  fun getPartners(accountType:String): Completable
  fun getPartnerById(id:String): Single<Partner>
  fun observePartners(): Observable<List<Partner>>
}