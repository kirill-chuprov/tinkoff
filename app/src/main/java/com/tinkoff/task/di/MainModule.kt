package com.tinkoff.task.di

import com.tinkoff.task.common.GlideImageLoader
import com.tinkoff.task.common.ImageBindingAdapter
import com.tinkoff.task.common.ImageLoader
import com.tinkoff.task.ui.detail.DetailViewModel
import com.tinkoff.task.ui.list.ListViewModel
import com.tinkoff.task.ui.map.MapViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

val mainModule = module {

    single<ImageLoader> { GlideImageLoader }
    viewModel { MapViewModel(get(),get()) }
    viewModel { ListViewModel(get()) }
    viewModel { DetailViewModel() }

}

class BindingComponent : androidx.databinding.DataBindingComponent, KoinComponent {

    private val imgLoader: ImageBindingAdapter by inject()

    override fun getImageBindingAdapter(): ImageBindingAdapter = imgLoader
}