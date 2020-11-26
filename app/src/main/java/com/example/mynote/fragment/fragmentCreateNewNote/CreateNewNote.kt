package com.example.mynote.fragment.fragmentCreateNewNote

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.R
import com.example.mynote.databinding.FragmentCreateNewNoteBinding
import com.example.mynote.dialogAddPassNewNote.DialogNewPass
import com.example.mynote.fragment.fragmentTabLayout.FragmentTabLayout
import com.example.mynote.utlis.utilReplaceFragment
import com.theartofdev.edmodo.cropper.CropImage

class CreateNewNote : Fragment(),ListenerNewNote
{
    private lateinit var mViewModel : CreateNewFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(CreateNewFragmentViewModel::class.java)
        val binding : FragmentCreateNewNoteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_new_note,container,false)
        binding.viewModelCreateNewNote = mViewModel
        binding.lifecycleOwner = this
        mViewModel.newNoteListener = this

        binding.fgCreateNoteSwitchPass.setOnClickListener {
            if(binding.fgCreateNoteSwitchPass.isChecked) DialogNewPass().show(childFragmentManager, "")
            else{mViewModel.offPassword()}
        }

//        binding.fgCreateNoteAddPhotoNote.setOnClickListener {
//            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(activity!!)
//        }

        return binding.root
    }

    override fun onEmpty(){
        Toast.makeText(this.context, "Empty!",Toast.LENGTH_SHORT).show()
    }

    override fun successCreateNote(){
        Toast.makeText(this.context, "Success!",Toast.LENGTH_SHORT).show()
        utilReplaceFragment(FragmentTabLayout(),true)
    }

    override fun errorCreate() {
        Toast.makeText(this.context, "error",Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            val result = CropImage.getActivityResult(data)
                    //DialogAddPhotoViewModel.photoNote.postValue(result.uri)
        }
    }
}