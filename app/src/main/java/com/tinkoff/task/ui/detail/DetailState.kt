package com.tinkoff.task.ui.detail

data class DetailState(
  val success: Boolean = false,  val loading: Boolean = false,
  val error: Throwable? = null
)

sealed class DetailStateIntent {
  object GetPointData : DetailStateIntent()
}

sealed class DetailStateChange {
  class Error(val error: Throwable) : DetailStateChange()
  object HideError : DetailStateChange()
  object Loading : DetailStateChange()
  object Success : DetailStateChange()
}