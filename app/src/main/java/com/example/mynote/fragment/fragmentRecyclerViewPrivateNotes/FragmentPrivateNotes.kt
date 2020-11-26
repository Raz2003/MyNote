package com.example.mynote.fragment.fragmentRecyclerViewPrivateNotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.Adapters.NotesAdapter
import com.example.mynote.R
import com.example.mynote.data.Notes
import com.example.mynote.databinding.FragmentPrivateNotesBinding
import com.example.mynote.dialogIsTruePass.DialogIsTruePass
import com.example.mynote.dialogIsTruePass.DialogIsTruePassViewModel
import com.example.mynote.dialogViewNote.DialogNote
import com.example.mynote.dialogViewNote.DialogViewModel
import com.example.mynote.fragment.fragmentRecyclerViewNote.AdapterOnItemClickListener

class FragmentPrivateNotes : Fragment(), AdapterOnItemClickListener{

    private lateinit var mViewModel : PrivateNotesViewModel
    private lateinit var mAdapter : NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(PrivateNotesViewModel::class.java)
        mAdapter = NotesAdapter()
        mAdapter.listenerAdapter = this
        lifecycle.addObserver(mViewModel)

        val binding : FragmentPrivateNotesBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_private_notes,container, false)
        binding.viewModel = mViewModel
        binding.lifecycleOwner = this

        PrivateNotesViewModel.rvData.observe(viewLifecycleOwner, Observer {
            binding.RecyclerViewNotes.apply {
                adapter = mAdapter
                mAdapter.setList(it)
            }
        })

        return binding.root
    }

    override fun onItemClickListener(item: Notes){
//        DialogViewModel.dataNote = item
//        DialogNote().show(childFragmentManager, "")
        DialogIsTruePassViewModel.note = item
        DialogIsTruePass().show(childFragmentManager, "")
    }

}