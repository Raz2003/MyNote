package com.example.mynote.dialogIsTruePass

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynote.R
import com.example.mynote.data.Notes
import com.example.mynote.dialogViewNote.DialogNote
import com.example.mynote.dialogViewNote.DialogViewModel
import com.example.mynote.utlis.fgShowToast
import kotlinx.android.synthetic.main.dialog_is_true_pass.view.*

class DialogIsTruePass : DialogFragment(), TruePassListener{

    private lateinit var mViewModel : DialogIsTruePassViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.dialog_is_true_pass,container,false)
        mViewModel = ViewModelProvider(this).get(DialogIsTruePassViewModel::class.java)
        mViewModel.mListener = this
        view.ed_is_true_pass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! >= 4){
                    mViewModel.isTruePass(s.toString())
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })
        return view
    }

    override fun truePass(dataItem: Notes) {
        DialogViewModel.dataNote = dataItem
        DialogNote().show(childFragmentManager,"")
        dialog?.hide()
    }

    override fun falsePass(){
        fgShowToast("Пароль не верный!",false)
        dialog?.cancel()
    }
}