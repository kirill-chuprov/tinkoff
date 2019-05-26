package com.tinkoff.task.di

import com.tinkoff.task.common.GlideImageLoader
import com.tinkoff.task.common.ImageBindingAdapter
import com.tinkoff.task.common.ImageLoader
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetViewModel
import com.tinkoff.task.ui.depositepointdetail.DetailViewModel
import com.tinkoff.task.ui.depositepointslist.ListViewModel
import com.tinkoff.task.ui.map.MapViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

val mainModule = module {

  single<ImageLoader> { GlideImageLoader }
  single { ImageBindingAdapter(get()) }
  viewModel { MapViewModel(get(), get(),get()) }
  viewModel { ListViewModel(get(), get(), get()) }
  viewModel { DetailViewModel(get(),get(),get()) }
  viewModel { DepositePointBottomSheetViewModel(get(), get(), get()) }

}

class BindingComponent : androidx.databinding.DataBindingComponent, KoinComponent {

  private val imgLoader: ImageBindingAdapter by inject()

  override fun getImageBindingAdapter(): ImageBindingAdapter = imgLoader
}