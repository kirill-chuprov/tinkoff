package com.tinkoff.task.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.tinkoff.task.R
import com.tinkoff.task.common.BaseFragment
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.databinding.FragmentListBinding
import com.tinkoff.task.ui.list.ListStateIntent.GetPartners
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>(), BaseView<ListState> {

  private val vmListScreen: ListViewModel by viewModel()

  override fun resLayoutId(): Int = R.layout.fragment_list

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
        Observable.just(GetPartners)
      )
    ).subscribe(vmListScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmListScreen.stateReceived().observe(this, Observer { state -> render(state) })
  }

  override fun render(state: ListState) {
    viewBinding!!.viewState = state
  }
}