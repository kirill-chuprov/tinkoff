package com.tinkoff.task.ui.depositepointslist

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import com.tinkoff.task.R
import com.tinkoff.task.common.BaseFragment
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.common.px
import com.tinkoff.task.databinding.FragmentListBinding
import com.tinkoff.task.ui.depositepointslist.ListStateIntent.ObserveDepositePoints
import com.tinkoff.task.ui.depositepointslist.ListStateIntent.ObservePartners
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>(), BaseView<ListState> {

  private val vmListScreen: ListViewModel by viewModel()
  private val eventPublisher by lazy { vmListScreen.eventPublisher }
  private val homeAdapter by lazy {
    DepositePointsAdapter(
      eventPublisher
    )
  }

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
      initRecyclerView()
    }

  private fun initRecyclerView() {
    viewBinding!!.rvDepositePoints.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = homeAdapter
      addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
          outRect: Rect,
          view: View,
          parent: RecyclerView,
          state: State
        ) {
          outRect.top = 4.px
          outRect.right = 8.px
          outRect.left = 8.px
          outRect.bottom = 4.px

          if (parent.getChildAdapterPosition(view) == 0) outRect.top = 8.px
        }
      })

    }
  }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(ObservePartners),
        Observable.just(ObserveDepositePoints)
      )
    ).subscribe(vmListScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmListScreen.stateReceived().observe(this, Observer { state -> render(state) })
  }

  override fun render(state: ListState) {
    viewBinding!!.viewState = state
    (viewBinding!!.rvDepositePoints.adapter as DepositePointsAdapter).submitList(state.depositePoints)
  }
}