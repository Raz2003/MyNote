package com.example.mynote.fragment.fragmentDisplayNote

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.example.mynote.data.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewModelDisplayNote(application : Application) : AndroidViewModel(application){

    var noteHeader : String? = null
    var noteBody  : String? = null

    var mListener : ViewModelDisplayNoteListener? = null

    private lateinit var mRepository : NoteRepository
    private var compositeDisposable = CompositeDisposable()


    companion object{
        var itemNote : Notes? = null
    }

    private fun isModel(model : Notes) : Int{
        return when(model){
            is BasicNote -> 0
            is PrivateNote -> 1
        }
    }

    init{
        val dao = AppDatabase.getDatabase(getApplication()).createNote()
        mRepository = NoteRepository(dao)
        initFields()
    }

     private fun initFields(){
         if(isModel(itemNote!!)==0){
             noteHeader = (itemNote as BasicNote).header
             noteBody = (itemNote as BasicNote).body
         }else{
             noteHeader = (itemNote as PrivateNote).header
             noteBody = (itemNote as PrivateNote).body
         }
     }

    override fun onCleared(){
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun deleteCurrentNote(view : View){
        compositeDisposable.add(
            Single.fromCallable{
                mRepository.deleteBasicNote(itemNote!! as BasicNote)
            }.subscribeOn(Schedulers.io())
                .subscribe({mListener?.replaceFragment()},{})
        )
    }
}