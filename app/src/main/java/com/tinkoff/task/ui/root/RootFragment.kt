package com.tinkoff.task.ui.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tinkoff.task.R
import com.tinkoff.task.databinding.FragmentRootBinding
import com.tinkoff.task.ui.detail.DetailFragment
import com.tinkoff.task.ui.map.MapFragment

class RootFragment : Fragment() {

    private var viewBinding: FragmentRootBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_root,
            container,
            false
        )

        with(viewBinding!!) {
            viewpager.adapter = RootPagerAdapter(childFragmentManager)
            tabs.setupWithViewPager(viewpager)
        }

        return viewBinding!!.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    inner class RootPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> MapFragment()
                else -> DetailFragment()
            }

        override fun getPageTitle(position: Int): CharSequence? =
            when (position) {
                0 -> getString(R.string.map)
                else -> getString(R.string.list)
            }
    }
}