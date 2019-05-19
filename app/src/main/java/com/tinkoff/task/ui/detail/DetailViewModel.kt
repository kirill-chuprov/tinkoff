package com.tinkoff.task.ui.detail

import com.tinkoff.task.common.BaseViewModel
import com.tinkoff.task.common.startWithAndErrHandleWithIO
import com.tinkoff.task.ui.detail.DetailStateChange.*
import com.tinkoff.task.ui.detail.DetailStateIntent.*
import io.reactivex.Observable

class DetailViewModel() : BaseViewModel<DetailState>() {

  override fun initState(): DetailState = DetailState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> = 
    Observable.merge(
      listOf(
           intentStream.ofType(GetSampleData::class.java)
                    .map { Success }
                    .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
       )
    )

  override fun reduceState(previousState: DetailState, stateChange: Any): DetailState = 
  when (stateChange) {
            is Loading -> previousState.copy(
                loading = true,
                success = false,
                error = null
            )

            is Success -> previousState.copy(
                loading = false,
                success = true,
                error = null
            )

            is Error -> previousState.copy(
                loading = false,
                success = false,
                error = stateChange.error
            )

            is HideError -> previousState.copy(error = null)

            else -> previousState
        }
}