package com.tinkoff.task.ui.list

data class ListState(
  val success: Boolean = false,  val loading: Boolean = false,
  val error: Throwable? = null
)

sealed class ListStateIntent {
  object GetSampleData : ListStateIntent()
}

sealed class ListStateChange {
  class Error(val error: Throwable) : ListStateChange()
  object HideError : ListStateChange()
  object Loading : ListStateChange()
  object Success : ListStateChange()
}