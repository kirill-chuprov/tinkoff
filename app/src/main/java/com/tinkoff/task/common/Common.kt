package com.tinkoff.task.common

import android.content.res.Resources
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent
import com.jakewharton.rxbinding2.widget.afterTextChangeEvents
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlin.LazyThreadSafetyMode.NONE
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun changeViewImageResource(imageView: ImageView, @DrawableRes resId: Int) {
  imageView.rotation = 0f
  imageView.animate()
    .rotationBy(360f)
    .setDuration(400)
    .setInterpolator(OvershootInterpolator())
    .start()

  imageView.postDelayed({ imageView.setImageResource(resId) }, 120)
}

inline fun <T, reified R> Observable<T>.startWithAndErrHandleWithIO(
  startWith: R,
  noinline errorHandler: (Throwable) -> Observable<R>
): Observable<Any> =
  this.cast(Any::class.java)
    .startWith(startWith)
    .onErrorResumeNext(errorHandler)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

inline fun <T, reified R> Observable<T>.errHandleWithIO(
  noinline errorHandler: (Throwable) -> Observable<R>
): Observable<Any> =
  this.cast(Any::class.java)
    .onErrorResumeNext(errorHandler)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

val editTextAfterTextChangeTransformer by lazy(NONE) {
  ObservableTransformer<TextViewAfterTextChangeEvent, String> {
    it.skip(1)
      .map { it.editable().toString() }
      .distinctUntilChanged()
      .debounce(350, MILLISECONDS)
  }
}

inline fun <T> TextView.debouncedAfterTextChanges(noinline mapper: (String) -> T): Observable<T> =
  afterTextChangeEvents().compose(editTextAfterTextChangeTransformer).map(mapper)

fun createPictureUrl(density: String, pictureName: String) =
  "https://static.tinkoff.ru/icons/deposition-partners-v3/$density/$pictureName"