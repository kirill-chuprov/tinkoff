package com.tinkoff.task.ui.depositepointbottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tinkoff.task.R
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.databinding.FragmentDepositePointBottomSheetBinding
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetStateIntent.GetDataForDepositePoint
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DepositePointBottomSheetFragment : BottomSheetDialogFragment(),
  BaseView<DepositePointBottomSheetState> {

  private var viewBinding: FragmentDepositePointBottomSheetBinding? = null
  private var viewSubscriptions: Disposable? = null
  private var compositeDisposable: CompositeDisposable? = null
  private val vmDepositePointBottomSheetScreen: DepositePointBottomSheetViewModel by viewModel()
  private var fullAddress: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    handleStates()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    viewBinding = DataBindingUtil.inflate(
      LayoutInflater.from(context),
      R.layout.fragment_deposite_point_bottom_sheet,
      container,
      false
    )

    savedInstanceState?.let { fullAddress = it.getString("fullAddress") }
    fullAddress = arguments?.getString("fullAddress") ?: ""

    initIntents()

    return viewBinding!!.root
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString("fullAddress", fullAddress)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    compositeDisposable?.dispose()
    viewSubscriptions?.dispose()
    viewBinding = null
  }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(GetDataForDepositePoint(fullAddress))
      )
    ).subscribe(vmDepositePointBottomSheetScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmDepositePointBottomSheetScreen.stateReceived()
      .observe(this, Observer { state -> render(state) })
  }

  override fun render(state: DepositePointBottomSheetState) {
    viewBinding!!.viewState = state
  }

  companion object {
    fun newInstance(fullAddress: String): DepositePointBottomSheetFragment =
      DepositePointBottomSheetFragment().apply {
        arguments = bundleOf("fullAddress" to fullAddress)
      }
  }
}
