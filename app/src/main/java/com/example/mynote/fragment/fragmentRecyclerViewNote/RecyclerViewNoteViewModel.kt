package com.example.mynote.fragment.fragmentRecyclerViewNote

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mynote.data.AppDatabase
import com.example.mynote.data.BasicNote
import com.example.mynote.data.NoteRepository
import com.example.mynote.data.Notes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecyclerViewNoteViewModel(application : Application) : AndroidViewModel(application),LifecycleObserver
{
    private var compDispose = CompositeDisposable()
    private var repository : NoteRepository
    var listenerViewModel : RecyclerNoteViewModelListener? = null

    companion object{
        var allNotes = MutableLiveData<List<BasicNote>>()
    }

    init{
        val dao = AppDatabase.getDatabase(getApplication()).createNote()
        repository = NoteRepository(dao)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
     private fun getAllBasicNotes(){
        compDispose.add(
            repository.getAllBasicNotes()
                .subscribeOn(Schedulers.io())
                .subscribe({allNotes.postValue(it)},{})
        )
    }

    override fun onCleared(){
        super.onCleared()
        compDispose.dispose()
    }
}