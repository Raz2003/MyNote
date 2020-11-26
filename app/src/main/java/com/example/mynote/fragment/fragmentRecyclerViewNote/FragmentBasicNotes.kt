package com.example.mynote.fragment.fragmentRecyclerViewNote

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.Adapters.NotesAdapter
import com.example.mynote.R
import com.example.mynote.data.BasicNote
import com.example.mynote.data.Notes
import com.example.mynote.databinding.FragmentRecyclerViewNoteBinding
import com.example.mynote.dialogViewNote.DialogNote
import com.example.mynote.dialogViewNote.DialogViewModel
import com.example.mynote.fragment.fragmentCreateNewNote.CreateNewNote

class FragmentBasicNotes : Fragment(), AdapterOnItemClickListener
{
    private lateinit var mViewModel : RecyclerViewNoteViewModel
    private lateinit var mAdapter : NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        mViewModel = ViewModelProvider(this).get(RecyclerViewNoteViewModel::class.java)
        lifecycle.addObserver(mViewModel)
        mAdapter = NotesAdapter()
        mAdapter.listenerAdapter = this

        val binding : FragmentRecyclerViewNoteBinding = DataBindingUtil.inflate(inflater ,R.layout.fragment_recycler_view_note,container, false)
        binding.viewModelRcNote = mViewModel
        binding.lifecycleOwner = this

        RecyclerViewNoteViewModel.allNotes.observe(viewLifecycleOwner , Observer{
            binding.RecyclerViewNotes.apply {
                adapter = mAdapter
                mAdapter.setList(it)
            }
        })

        return binding.root
    }

    override fun onItemClickListener(item: Notes){
        DialogViewModel.dataNote = item
        DialogNote().show(childFragmentManager, "test")
    }
}