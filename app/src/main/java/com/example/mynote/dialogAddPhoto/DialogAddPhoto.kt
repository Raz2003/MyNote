package com.example.mynote.dialogAddPhoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.R
import com.example.mynote.databinding.DialogAddPhotoForNoteBinding

class DialogAddPhoto : DialogFragment() , AddPhotoListener{

    private lateinit var mViewModel : DialogAddPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(DialogAddPhotoViewModel::class.java)
        mViewModel.photoListener = this
        val binding : DialogAddPhotoForNoteBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_photo_for_note,container,false)
        binding.dialogAddPhotoViewPhoto.setImageURI(DialogAddPhotoViewModel.photoNote.value)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun hidePhoto(){
        //dialog?.hide()
    }
}