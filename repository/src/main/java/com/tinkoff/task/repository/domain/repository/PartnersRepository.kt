package com.tinkoff.task.repository.domain.repository

import io.reactivex.Completable

/**
 * Created by Kirill Chuprov on 5/23/19.
 */
interface PartnersRepository {

  fun getPartners(accountType:String): Completable
}