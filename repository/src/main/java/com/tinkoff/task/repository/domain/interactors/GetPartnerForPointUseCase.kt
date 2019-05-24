package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.repository.domain.repository.PartnersRepository
import io.reactivex.Single

class GetPartnerForPointUseCase(private val partnersRepository: PartnersRepository) {

  fun getPartnerById(id:String): Single<Partner> = partnersRepository.getPartnerById(id)
}