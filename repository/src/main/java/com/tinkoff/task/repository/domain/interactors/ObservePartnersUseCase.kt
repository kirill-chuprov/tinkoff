package com.tinkoff.task.repository.domain.interactors

import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.repository.domain.repository.PartnersRepository
import io.reactivex.Observable

class ObservePartnersUseCase(private val partnersRepository: PartnersRepository) {
  fun observePartners(): Observable<List<Partner>> = partnersRepository.observePartners()
}