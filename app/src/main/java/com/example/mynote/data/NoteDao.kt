package com.example.mynote.data

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface NoteDao {

    @Update
    fun updatePrivateNote(privateNote: PrivateNote)

    @Query("SELECT * FROM private_notes ORDER BY id ASC")
    fun getAllPrivateNotes() : Single<List<PrivateNote>>

    @Query("SELECT * FROM basic_notes ORDER BY id ASC")
     fun getAllBasicNotes() : Single<List<BasicNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBasicNote(basicNote : BasicNote)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPrivateNote(privateNote: PrivateNote)

    @Delete
    fun deleteBasicNote(basicNote: BasicNote)

    @Update
    fun updateBasicNote(basicNote : BasicNote)

    @Delete
    fun deletePrivateNote(privateNote : PrivateNote)

    @Query("DELETE FROM basic_notes")
    fun deleteAllNotes()
}