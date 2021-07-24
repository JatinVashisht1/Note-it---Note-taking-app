package com.thetechguy.myapplication

import android.content.Context
import android.graphics.Color
import android.os.strictmode.IntentReceiverLeakedViolation
import android.view.*
import android.widget.*
import androidx.appcompat.widget.ResourceManagerInternal
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView

    var a = Model()

    var isSelectedModeOn = false
    var checkedNotesToDelete = mutableSetOf<Note>()
class NoteRecyclerViewAdapter(private val context: Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {


//    lateinit var notes : MutableList<Note>
    val notes = ArrayList<Note>()

//    lateinit var checkedListofNotes : List<Note>
//    var notess = mutableListOf<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textTitle : TextView= itemView.findViewById(R.id.text_view_card_title)
//        val delete : ImageView = itemView.findViewById(R.id.delete_note)
        val card : CardView = itemView.findViewById(R.id.cardView)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_layout, parent, false))
//        viewHolder.delete.setImageResource(R.drawable.ic_delete)
//        viewHolder.delete.setOnClickListener{
//            listener.onItemClick(notes[viewHolder.adapterPosition])

//        }

        if(!isSelectedModeOn){
            viewHolder.card.setCardBackgroundColor(Color.rgb(96,88,88))
        }

        viewHolder.card.setOnClickListener{
            if(viewHolder.card.isSelected && isSelectedModeOn){
                checkedNotesToDelete.remove(notes[viewHolder.adapterPosition])
                if(checkedNotesToDelete.isEmpty())
                {
                    isSelectedModeOn = false
                }
                viewHolder.card.setCardBackgroundColor(Color.rgb(96,88,88))
                viewHolder.card.isSelected = false
            }
            else if(!viewHolder.card.isSelected && isSelectedModeOn){
                viewHolder.card.isSelected = true
                checkedNotesToDelete.add(notes[viewHolder.adapterPosition])
                viewHolder.card.setCardBackgroundColor(Color.rgb(1,135,134))
            }
            else{
//                checkedNotesToDelete.add(notes[viewHolder.adapterPosition])
                listener.navigateToViewText(notes[viewHolder.adapterPosition])

            }
        }

        viewHolder.card.setOnLongClickListener{

            if(viewHolder.card.isSelected){


                checkedNotesToDelete.remove(notes[viewHolder.adapterPosition])
                if(checkedNotesToDelete.isEmpty()){
                isSelectedModeOn = false
                }
                viewHolder.card.setCardBackgroundColor(Color.rgb(96,88,88))
                viewHolder.card.isSelected = false
            }
            else if(!viewHolder.card.isSelected){
                isSelectedModeOn = true
                viewHolder.card.setCardBackgroundColor(Color.rgb(1,135,134))
                checkedNotesToDelete.add(notes[viewHolder.adapterPosition])
                viewHolder.card.isSelected = true
            }

            true
        }



        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentTitle = notes[position].title
        val currentNote = notes[position]
        holder.textTitle.text = currentTitle
//        if(holder.checkNote.isChecked){
//            checkedNote.add(notes[position])
//        }
        if(!isSelectedModeOn){
            holder.card.setCardBackgroundColor(Color.rgb(96,88,88))
            if(checkedNotesToDelete.isEmpty()){
//            holder.card.isSelected = false
            }
        }


    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateList(newList: List<Note>)
    {
        notes.clear()
        notes.addAll(newList)
        isSelectedModeOn = false
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter {
//    fun onItemClick(note: Note)
//    fun checkNote(note: Note)
    fun navigateToViewText(note: Note)

}
