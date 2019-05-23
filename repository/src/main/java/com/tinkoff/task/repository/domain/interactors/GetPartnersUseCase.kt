package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.repository.PartnersRepository
import io.reactivex.Completable

/**
 * Created by Kirill Chuprov on 5/23/19.
 */

class GetPartnersUseCase(private val partnersRepository: PartnersRepository) {

  fun getPartnersUseCase(accountType:String): Completable = partnersRepository.getPartners(accountType)
}