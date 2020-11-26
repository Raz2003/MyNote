package com.example.mynote.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.R
import com.example.mynote.data.BasicNote
import com.example.mynote.data.Notes
import com.example.mynote.data.PrivateNote
import com.example.mynote.fragment.fragmentRecyclerViewNote.AdapterOnItemClickListener
import kotlinx.android.synthetic.main.item_note.view.*
import kotlinx.android.synthetic.main.item_private_note.view.*

class NotesAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var listenerAdapter : AdapterOnItemClickListener? = null
    private var list = listOf<Notes>()
    private lateinit var mResultDuff : DiffUtil.DiffResult//для проверки листа

    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            is BasicNote -> 0
            is PrivateNote -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            0 -> BasicNotesViewHolder(itemView = inflater.inflate(R.layout.item_note,parent , false ),
                listener = listenerAdapter!!
            )
            else -> PrivateNotesViewHolder(itemView = inflater.inflate(R.layout.item_private_note,parent,false),
                listener = listenerAdapter!!
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is BasicNotesViewHolder -> holder.bind(list[position] as BasicNote)
            is PrivateNotesViewHolder -> holder.bind(list[position] as  PrivateNote)
            }
        }

    override fun getItemCount(): Int = list.size

    class BasicNotesViewHolder(itemView : View, private val listener : AdapterOnItemClickListener) : RecyclerView.ViewHolder(itemView){
         private val nameNote : TextView = itemView.view_note_name
         private var bodyNote : TextView = itemView.view_note_body

        fun bind(model : BasicNote){
            nameNote.text = model.header
            bodyNote.text = model.body
            itemView.setOnClickListener {
                listener.onItemClickListener(model)
            }
        }
    }

    class PrivateNotesViewHolder(itemView : View, private val listener : AdapterOnItemClickListener) : RecyclerView.ViewHolder(itemView){
        private val nameNote : TextView = itemView.view_private_note_name
        private val passNote : TextView = itemView.view_private_note_pass

        fun bind(model : PrivateNote){
            nameNote.text = model.header
            passNote.text = encryptTextViewPass(model.pass)

            itemView.setOnClickListener {
                listener.onItemClickListener(model)
            }
        }
    }

    fun setList(newList : List<Notes>){
        mResultDuff = DiffUtil.calculateDiff(DiffUtilCallback(list,newList))//проверяем старыц и новый лист на "схожесть"
        list = newList
        mResultDuff.dispatchUpdatesTo(this)//для перерисовки элементов
    }
}

private fun encryptTextViewPass(pass : String) : String {
    return pass.replace(Regex(".(?=.{2})"),"*")
}