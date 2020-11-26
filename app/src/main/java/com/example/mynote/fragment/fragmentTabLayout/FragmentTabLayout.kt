package com.example.mynote.fragment.fragmentTabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.R
import com.example.mynote.databinding.FragmentTabLayoutBinding
import com.example.mynote.fragment.fragmentCreateNewNote.CreateNewNote
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tab_layout.*

class FragmentTabLayout : Fragment(), ViewModelListener{

    private lateinit var mViewModel : TabLayoutViewModel
    private lateinit var tabAdapter : ViewPagerAdapterForTabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val binding : FragmentTabLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_layout,container, false)

        mViewModel = ViewModelProvider(this).get(TabLayoutViewModel::class.java)
        mViewModel.listener = this

        tabAdapter = ViewPagerAdapterForTabLayout(childFragmentManager, lifecycle)

        binding.viewModel = mViewModel
        binding.mainViewPager.adapter = tabAdapter

        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager){tab,pos->
            tab.text = tabAdapter.getPageTitle(pos)
        }.attach()

        return binding.root
    }

    override fun replaceFragment(){
        parentFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.mainContainer,CreateNewNote()).commit()
    }
}