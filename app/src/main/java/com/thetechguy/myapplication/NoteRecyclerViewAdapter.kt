package com.thetechguy.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerViewAdapter(private val context: Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {

//    lateinit var notes : MutableList<Note>
    val notes = ArrayList<Note>()
//    lateinit var checkedListofNotes : List<Note>
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textTitle : TextView= itemView.findViewById(R.id.text_view_card_title)
//        val delete : ImageView = itemView.findViewById(R.id.delete_note)
        val card : CardView = itemView.findViewById(R.id.cardView)
        val checkNote : CheckBox = itemView.findViewById(R.id.check_note)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_layout, parent, false))
//        viewHolder.delete.setImageResource(R.drawable.ic_delete)
//        viewHolder.delete.setOnClickListener{
//            listener.onItemClick(notes[viewHolder.adapterPosition])

//        }
        viewHolder.card.setOnClickListener{
            listener.navigateToViewText(notes[viewHolder.adapterPosition])
        }



        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentTitle = notes[position].title
        holder.textTitle.text = currentTitle
//        if(holder.checkNote.isChecked){
//            checkedNote.add(notes[position])
//        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateList(newList: List<Note>)
    {
        notes.clear()
        notes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesRVAdapter {
//    fun onItemClick(note: Note)
//    fun checkNote(note: Note)
    fun navigateToViewText(note: Note)

}
