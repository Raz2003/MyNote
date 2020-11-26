package com.example.mynote.fragment.fragmentTabLayout

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel

class TabLayoutViewModel(application : Application) : AndroidViewModel(application){

    var listener : ViewModelListener? = null

    fun clickCreateNote(view : View){
        listener?.replaceFragment()
    }
}