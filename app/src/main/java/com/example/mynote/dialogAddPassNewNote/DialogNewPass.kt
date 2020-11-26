package com.example.mynote.dialogAddPassNewNote

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.R
import com.example.mynote.databinding.DialogAddPassNewNoteBinding
import com.example.mynote.utlis.fgShowToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogNewPass : BottomSheetDialogFragment(),addPassListener{

    private lateinit var mViewModel : ViewModelDialogNewPass

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(ViewModelDialogNewPass::class.java)
        mViewModel.mListener = this
        val binding : DialogAddPassNewNoteBinding = DataBindingUtil.inflate(inflater , R.layout.dialog_add_pass_new_note, container , false)
        binding.bindDialogAddPassViewModel = mViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun closeDialog() {
        fgShowToast("Пароль добавлен!",false)
        dialog?.cancel()
    }

    override fun wrongPass(){
        fgShowToast("Пароль меньше 4 символов",true)
    }
}