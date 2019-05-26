package com.tinkoff.task.ui.depositepointdetail

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.tinkoff.task.R
import com.tinkoff.task.common.BaseFragment
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.databinding.FragmentDetailBinding
import com.tinkoff.task.ui.depositepointdetail.DetailStateIntent.GetDataForDepositePoint
import com.tinkoff.task.ui.depositepointslist.FULL_ADDRESS
import com.tinkoff.task.ui.depositepointslist.PARTNER_NAME
import com.tinkoff.task.ui.depositepointslist.PICTURE
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(), BaseView<DetailState> {

  private val vmDetailScreen: DetailViewModel by viewModel()
  private val fullAddress by lazy { arguments?.getString(FULL_ADDRESS) ?: "" }

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

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(viewBinding!!) {
      (this.constraintRoot as ViewGroup).layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }
  }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(GetDataForDepositePoint(fullAddress))
      )
    ).subscribe(vmDetailScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmDetailScreen.stateReceived().observe(this, Observer { state -> render(state) })
  }

  override fun render(state: DetailState) {
    if (state.success) {
      viewBinding!!.viewState = state
      with(viewBinding!!) {
        tvWorkingHoursTitle.text = getString(R.string.work_hours)
        tvEnrollmentTitle.text = getString(R.string.enrollment)
        tvRestrictionsTitle.text = getString(R.string.restrictions)
        tvConditionsTitle.text = getString(R.string.conditions)
        tvInformationTitle.text = getString(R.string.information)
      }
    }

  }
}