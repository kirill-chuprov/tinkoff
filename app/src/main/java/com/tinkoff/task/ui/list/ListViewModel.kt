package com.tinkoff.task.ui.list

import com.tinkoff.task.common.BaseViewModel
import com.tinkoff.task.common.startWithAndErrHandleWithIO
import com.tinkoff.task.ui.list.ListStateChange.*
import com.tinkoff.task.ui.list.ListStateIntent.*
import io.reactivex.Observable

class ListViewModel() : BaseViewModel<ListState>() {

  override fun initState(): ListState = ListState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> = 
    Observable.merge(
      listOf(
           intentStream.ofType(GetSampleData::class.java)
                    .map { Success }
                    .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
       )
    )

  override fun reduceState(previousState: ListState, stateChange: Any): ListState = 
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