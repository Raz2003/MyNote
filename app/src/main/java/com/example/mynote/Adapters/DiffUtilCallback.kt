package com.example.mynote.Adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.mynote.data.BasicNote
import com.example.mynote.data.Notes

class DiffUtilCallback(private val oldList : List<Notes> , private val newList: List<Notes>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {//метод для "слабой" проверки , отдельного значения
       return oldList[oldItemPosition].getItemId() == newList[newItemPosition].getItemId()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean { //проверка всего item
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}