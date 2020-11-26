package com.example.mynote.dialogViewNote

import android.app.Application
import android.os.AsyncTask
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.mynote.data.*
import com.example.mynote.fragment.fragmentRecyclerViewNote.RecyclerViewNoteViewModel
import com.example.mynote.fragment.fragmentRecyclerViewPrivateNotes.PrivateNotesViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DialogViewModel(application : Application) : AndroidViewModel(application){

    companion object{
        var dataNote : Notes? = null
    }

    private var repository : NoteRepository
    private var compDispose = CompositeDisposable()
    var headName : String? = null
    var bodyText : String? = null
    var btnSaveView = false
    lateinit var listener : listenerDialog

    init {
        val dao = AppDatabase.getDatabase(getApplication()).createNote()
        repository = NoteRepository(dao)
        getModel(dataNote!!)
    }

    private fun getModel(model : Notes){
        when(model){
            is BasicNote -> {
               headName = model.header
               bodyText = model.body
             }
            is PrivateNote -> {
                headName = model.header
                bodyText = model.body
            }
        }
    }

    fun updateCurrentNote(view : View){
        compDispose.add(
            Single.fromCallable {
                dataNote.let {data->
                    when(data){
                        is BasicNote ->{
                            val updateNote = BasicNote(data.id,headName!!,bodyText!!)
                            repository.updateBasicNote(updateNote)
                            updateDataBasicNotes()
                        }
                        is PrivateNote ->{
                            val updateNote = PrivateNote(data.id,headName!!,bodyText!!,data.pass)
                            repository.updatePrivateNote(updateNote)
                            updateDataPrivateNotes()
                        }
                    }
                }
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Toast.makeText(getApplication(),"success",Toast.LENGTH_LONG).show()
                },{Toast.makeText(getApplication(),"${it.cause}}",Toast.LENGTH_LONG).show()})
        )
    }

    fun deleteCurrentNote(view : View){
        compDispose.add(
            Single.fromCallable {
                dataNote.let {data->
                    when(data){
                        is BasicNote -> {
                            repository.deleteBasicNote(data)
                            updateDataBasicNotes()
                        }
                        is PrivateNote -> {
                            repository.deletePrivateNote(data)
                            updateDataPrivateNotes()
                        }
                    }
                }
            }.subscribeOn(Schedulers.io())
                .subscribe({listener.listenerDelete()},{})
        )
    }

    fun copyCurrentNote(view : View){
        listener.copyCopyNoteBody(bodyText!!)
    }

    private fun updateDataBasicNotes(){
        compDispose.add(
            repository.getAllBasicNotes()
                .subscribeOn(Schedulers.io())
                .subscribe({RecyclerViewNoteViewModel.allNotes.postValue(it)},{})
        )
    }

    private fun updateDataPrivateNotes(){
        compDispose.add(
            repository.getAllPrivateNotes()
                .subscribeOn(Schedulers.io())
                .subscribe({PrivateNotesViewModel.rvData.postValue(it)},{})
        )
    }

    override fun onCleared(){
        super.onCleared()
        compDispose.dispose()
    }
}