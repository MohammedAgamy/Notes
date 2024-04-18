package com.example.notes.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteLayoutBinding
import com.example.notes.fragmentui.HomeFragmentDirections
import com.example.notes.models.NoteEntityModel

class AdapterNote :RecyclerView.Adapter<AdapterNote.MyViewHolder>() {


    class MyViewHolder(val itemBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)
        val differCallback = object : DiffUtil.ItemCallback<NoteEntityModel>() {
            override fun areItemsTheSame(oldItem: NoteEntityModel, newItem: NoteEntityModel): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.note == newItem.note &&
                        oldItem.title == newItem.title
            }
            override fun areContentsTheSame(oldItem: NoteEntityModel, newItem: NoteEntityModel): Boolean {
                return oldItem == newItem
            }
        }

    //There are several ways to compare and identify differences between data in Kotlin
        val differ =AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
        NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote =differ.currentList[position]
        holder.itemBinding.noteTitle.text = currentNote.title
        holder.itemBinding.noteDesc.text = currentNote.note

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }









































       /* holder.cardView.setCardBackgroundColor(holder.itemView.resources.getColor(getRandomColor(), null))


        holder.cardView.setOnClickListener {
            noteOnItemClickListener.onNoteClickList(noteList[holder.adapterPosition])
        }

        holder.cardView.setOnLongClickListener {
            noteOnItemClickListener.onLongNoteClickList(noteList[holder.adapterPosition], holder.cardView)
            true
        }
*/
    }


/*

   val cardView: CardView = itemView.findViewById(R.id.card_view)
        val title: TextView = itemView.findViewById(R.id.title)
        val note: TextView = itemView.findViewById(R.id.note)
        val date: TextView = itemView.findViewById(R.id.date)

 fun updateList(note: List<NoteEntityModel>) {
        fullList.clear()
        fullList.addAll(note)
        noteList.clear()
        noteList.addAll(note)
        notifyDataSetChanged()

    }

    fun filterSearch(search: String) {
        noteList.clear()

        for (item in fullList) {
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.date?.lowercase()?.contains(search.lowercase()) == true
            ) {
                noteList.add(item)
            }
        }

        notifyDataSetChanged()
    }*/

/*    fun getRandomColor(): Int {
        val colorList = ArrayList<Int>()

        colorList.add(R.color.NoteColor1)
        colorList.add(R.color.NoteColor2)
        colorList.add(R.color.NoteColor3)
        colorList.add(R.color.NoteColor4)
        colorList.add(R.color.NoteColor5)
        colorList.add(R.color.NoteColor6)
        colorList.add(R.color.NoteColor7)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(colorList.size)
        return colorList[randomIndex]
    }



    interface NoteOnItemClickListener {
        fun onNoteClickList(noteEntityModel: NoteEntityModel)
        fun onLongNoteClickList(noteEntityModel: NoteEntityModel, cardView: CardView)
    }*/



