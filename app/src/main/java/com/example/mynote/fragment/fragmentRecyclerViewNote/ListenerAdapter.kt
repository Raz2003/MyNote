package com.example.mynote.fragment.fragmentRecyclerViewNote

import com.example.mynote.data.BasicNote
import com.example.mynote.data.Notes

interface AdapterOnItemClickListener {
    fun onItemClickListener(item : Notes)
}