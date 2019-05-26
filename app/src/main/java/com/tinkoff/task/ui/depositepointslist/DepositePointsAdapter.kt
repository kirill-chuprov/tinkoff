package com.tinkoff.task.ui.depositepointslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jakewharton.rxbinding2.view.clicks
import com.tinkoff.task.BR
import com.tinkoff.task.R
import com.tinkoff.task.databinding.ItemDepositionPointBinding
import com.tinkoff.task.ui.depositepointslist.DepositePointsAdapter.DepositionPointViewHolder
import com.tinkoff.task.ui.depositepointslist.ItemState.ItemDepositePoint
import com.tinkoff.task.ui.depositepointslist.ListStateIntent.GoToPointDetail
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit.MILLISECONDS

internal class DepositePointsAdapter(
  private val eventPublisher: PublishSubject<GoToPointDetail>
) :
  ListAdapter<ItemDepositePoint, DepositionPointViewHolder>(CATEGORY_COMPARATOR) {

  private lateinit var inflater: LayoutInflater
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): DepositionPointViewHolder {
    if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)
    return DepositionPointViewHolder(DataBindingUtil.inflate(inflater, viewType, parent, false))
  }

  override fun getItemViewType(position: Int): Int = R.layout.item_deposition_point

  override fun onBindViewHolder(holder: DepositionPointViewHolder, position: Int) =
    holder.bind(getItem(position))

  private companion object {

    private val CATEGORY_COMPARATOR = object : DiffUtil.ItemCallback<ItemDepositePoint>() {

      override fun areItemsTheSame(
        oldItem: ItemDepositePoint,
        newItem: ItemDepositePoint
      ): Boolean =
        oldItem.fullAddress == newItem.fullAddress

      override fun areContentsTheSame(
        oldItem: ItemDepositePoint,
        newItem: ItemDepositePoint
      ): Boolean =
        oldItem == newItem
    }
  }

  inner class DepositionPointViewHolder(private val binding: ViewDataBinding) :
    ViewHolder(binding.root) {

    init {
      if (binding is ItemDepositionPointBinding) {
        binding.root.clicks()
          .throttleFirst(500, MILLISECONDS)
          .map {
            val depositePoint = getItem(adapterPosition) as ItemDepositePoint
            GoToPointDetail(
              depositePoint.fullAddress,
              depositePoint.picture,
              depositePoint.partnerName
            )
          }
          .subscribe(eventPublisher)
      }
    }

    fun bind(data: ItemState) {
      if (binding.setVariable(BR.viewState, data)) binding.executePendingBindings()
    }
  }
}