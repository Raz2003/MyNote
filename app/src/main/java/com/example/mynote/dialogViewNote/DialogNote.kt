package com.example.mynote.dialogViewNote

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.R
import com.example.mynote.databinding.BottomSheetViewNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogNote : BottomSheetDialogFragment(),listenerDialog{

    private lateinit var mViewModel : DialogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(DialogViewModel::class.java)
        mViewModel.listener = this
        val binding : BottomSheetViewNoteBinding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_view_note,container,false)
        binding.bsViewNote = mViewModel
        binding.lifecycleOwner = this
        binding.bsBtnEditNote.setOnClickListener{
            binding.bsEditTextBody.isEnabled = true
            binding.bsBtnUpdateNote.visibility = View.VISIBLE
        }
        return binding.root
    }

    override fun listenerDelete(){
        dialog?.cancel()
    }

    override fun copyCopyNoteBody(noteBody: String){
        val clipBoard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text",noteBody)
        clipBoard.setPrimaryClip(clip)
        Toast.makeText(context , "Copy!",Toast.LENGTH_SHORT).show()
    }
}