package com.tinkoff.task.application

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.tinkoff.task.di.BindingComponent
import com.tinkoff.task.di.mainModule
import com.tinkoff.task.repository.di.repoModule
import org.koin.android.ext.android.startKoin

class TinkoffApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                mainModule, repoModule
            )
        )
        DataBindingUtil.setDefaultComponent(BindingComponent())
    }
}