package com.example.mynote.fragment.fragmentRecyclerViewPrivateNotes

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.example.mynote.data.AppDatabase
import com.example.mynote.data.NoteRepository
import com.example.mynote.data.PrivateNote
import com.example.mynote.fragment.fragmentRecyclerViewNote.RecyclerNoteViewModelListener
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PrivateNotesViewModel(application : Application) : AndroidViewModel(application), LifecycleObserver{
    private var compDisposable = CompositeDisposable()
    private var repository : NoteRepository
    var listenerViewModel : RecyclerNoteViewModelListener? = null

    companion object{
        var rvData = MutableLiveData<List<PrivateNote>>()
    }

    init {
        val dao = AppDatabase.getDatabase(getApplication()).createNote()
        repository = NoteRepository(dao)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun getAllPrivateNotes(){
        compDisposable.add(
            repository.getAllPrivateNotes()
                .subscribeOn(Schedulers.io())
                .subscribe({rvData.postValue(it)},{})
        )
    }

    override fun onCleared() {
        super.onCleared()
        compDisposable.dispose()
    }
}