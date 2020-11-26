package com.example.mynote.fragment.fragmentCreateNewNote

interface ListenerNewNote {
    fun onEmpty()
    fun successCreateNote()
    fun errorCreate()
}