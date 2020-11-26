package com.example.mynote.fragment.fragmentDisplayNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.R
import com.example.mynote.databinding.FragmentDispalyNoteBinding
import com.example.mynote.fragment.fragmentRecyclerViewNote.FragmentBasicNotes
import com.example.mynote.utlis.utilReplaceFragment


class FragmentDisplayNote : Fragment(),ViewModelDisplayNoteListener{

    private lateinit var mViewModel : ViewModelDisplayNote

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(ViewModelDisplayNote::class.java)
        mViewModel.mListener = this
        val binding : FragmentDispalyNoteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dispaly_note,container,false)
        binding.bindDisplayNote = mViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun replaceFragment(){
        parentFragmentManager.popBackStack()
        utilReplaceFragment(FragmentBasicNotes(),false)
    }
}