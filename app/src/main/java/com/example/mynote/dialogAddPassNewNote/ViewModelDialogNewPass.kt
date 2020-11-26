package com.example.mynote.dialogAddPassNewNote

import android.app.Application
import android.app.Dialog
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mynote.fragment.fragmentCreateNewNote.CreateNewFragmentViewModel

class ViewModelDialogNewPass() : ViewModel() {

    var password : String? = null
    lateinit var mListener : addPassListener


    fun addPassword(view : View){
        password.let{
            if(it?.length!! >= 4){
                CreateNewFragmentViewModel.password = password
                mListener.closeDialog()
            }else{mListener.wrongPass()}
        }
    }
}