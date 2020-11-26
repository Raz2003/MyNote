package com.example.mynote

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:hideBtnSave")
fun goneBtnSave(view : View,show : Boolean){
    view.visibility = if(show) View.VISIBLE else View.GONE
}