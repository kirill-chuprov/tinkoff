package com.tinkoff.task.ui.depositepointdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.tinkoff.task.R
import com.tinkoff.task.common.BaseFragment
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.databinding.FragmentDetailBinding
import com.tinkoff.task.ui.depositepointslist.ListStateIntent.ObservePartners
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(), BaseView<DetailState> {

  private val vmDetailScreen: DetailViewModel by viewModel()

  override fun resLayoutId(): Int = R.layout.fragment_detail

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    handleStates()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = super.onCreateView(inflater, container, savedInstanceState)
    .also {
      initIntents()
    }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(ObservePartners)
      )
    ).subscribe(vmDetailScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmDetailScreen.stateReceived().observe(this, Observer { state -> render(state) })
  }

  override fun render(state: DetailState) {
    viewBinding!!.viewState = state
  }
}