package com.example.mynote.data

import io.reactivex.Single

class NoteRepository(val dao : NoteDao) {

    fun updatePrivateNote(privateNote: PrivateNote){
        dao.updatePrivateNote(privateNote)
    }

    fun updateBasicNote(basicNote: BasicNote){
        dao.updateBasicNote(basicNote)
    }

     fun addBasicNote(basicNote : BasicNote){
        dao.addBasicNote(basicNote)
    }

    fun addPrivateNote(privateNote : PrivateNote){
        dao.addPrivateNote(privateNote)
    }

     fun getAllBasicNotes() : Single<List<BasicNote>> {
        return dao.getAllBasicNotes()
    }

    fun getAllPrivateNotes() : Single<List<PrivateNote>>{
        return dao.getAllPrivateNotes()
    }

    fun deleteBasicNote(basicNote : BasicNote){
        dao.deleteBasicNote(basicNote)
    }

    fun deletePrivateNote(privateNote:PrivateNote){
        dao.deletePrivateNote(privateNote)
    }

    fun deleteAllNotes(){
        dao.deleteAllNotes()
    }
}