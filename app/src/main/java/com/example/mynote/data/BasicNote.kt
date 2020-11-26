package com.example.mynote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class Notes{
    fun getItemId() : Int{
        return when(this){
            is BasicNote -> this.id
            is PrivateNote -> this.id
        }
    }
}

@Entity(tableName = "basic_notes")
data class BasicNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var header : String,
    var body : String) : Notes()

@Entity(tableName = "private_notes")
data class PrivateNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var header : String,
    var body : String,
    var pass : String) : Notes()
