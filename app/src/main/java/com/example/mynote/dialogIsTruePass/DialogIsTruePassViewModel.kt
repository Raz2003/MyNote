package com.example.mynote.dialogIsTruePass

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mynote.data.BasicNote
import com.example.mynote.data.Notes
import com.example.mynote.data.PrivateNote

class DialogIsTruePassViewModel() : ViewModel(){

    lateinit var mListener : TruePassListener

    companion object{
        var note : Notes? = null
    }

    fun isTruePass(userPass : String){
        note.let {
            when(it){
                is BasicNote -> return
                is PrivateNote -> {
                    if(userPass == it.pass) mListener.truePass(it)
                    else{mListener.falsePass()}
                }
            }
        }
    }
}