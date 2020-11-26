package com.example.mynote.dialogAddPhoto

import android.app.Application
import android.net.Uri
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class DialogAddPhotoViewModel(application : Application) : AndroidViewModel(application){

    companion object{
        var photoNote = MutableLiveData<Uri>()
    }

    lateinit var photoListener : AddPhotoListener

    fun offPhoto(view : View){//если фото не устроило
        photoNote.postValue(null)
        photoListener.hidePhoto()
    }
}