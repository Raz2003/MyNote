package com.example.mynote.utlis

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mynote.R

fun Fragment.utilReplaceFragment(fragment : Fragment, addStack : Boolean){
    if(addStack){
        parentFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.mainContainer, fragment).commit()
    }else{
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer,fragment).commit()
    }
}

fun Fragment.fgShowToast(text : String,longTime : Boolean){
    when(longTime){
        true -> Toast.makeText(this.context, text , Toast.LENGTH_LONG).show()
        false-> Toast.makeText(this.context,text, Toast.LENGTH_SHORT).show()
    }
}