package com.example.mynote.fragment.fragmentCreateNewNote

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.mynote.data.AppDatabase
import com.example.mynote.data.BasicNote
import com.example.mynote.data.NoteRepository
import com.example.mynote.data.PrivateNote
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CreateNewFragmentViewModel(application: Application) : AndroidViewModel(application)
{
    private var disposable = CompositeDisposable()

    companion object{
        var password : String? = null
    }

     var nameNote : String? = null
     var noteText : String? = null

    lateinit var newNoteListener : ListenerNewNote
    private var repository : NoteRepository

    init {
        val dao = AppDatabase.getDatabase(getApplication()).createNote()
        repository = NoteRepository(dao)
    }

    fun saveNewNote(view : View)
    {
        if(nameNote?.trim().isNullOrEmpty() || noteText?.trim().isNullOrEmpty())
        {
            newNoteListener.onEmpty()
            return
        }
        disposable.add(
            Single.fromCallable{
                if(password==null){
                    repository.addBasicNote(BasicNote(0,nameNote!!,noteText!!))
                }else{
                    repository.addPrivateNote(PrivateNote(0,nameNote!!,noteText!!, password!!))
                }
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({newNoteListener.successCreateNote()},{
                    Toast.makeText(getApplication(), "${it.message}",Toast.LENGTH_LONG).show()
                })
        )
    }

    fun offPassword(){
        password = null
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}