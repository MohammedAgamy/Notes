package com.example.notes.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.NoteLayoutBinding
import com.example.notes.fragmentui.HomeFragmentDirections
import com.example.notes.models.NoteEntityModel
import kotlin.random.Random

class AdapterNote : RecyclerView.Adapter<AdapterNote.MyViewHolder>() {


    class MyViewHolder(val itemBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<NoteEntityModel>() {
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
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.noteTitle.text = currentNote.title
        holder.itemBinding.noteDesc.text = currentNote.note

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }

        holder.itemBinding.cardView.setCardBackgroundColor(holder.itemView.resources.getColor(getRandomColor(), null))
    }


    private fun getRandomColor(): Int {
        val colorList = ArrayList<Int>()

        colorList.add(R.color.NoteColor1)
        colorList.add(R.color.NoteColor2)
        colorList.add(R.color.NoteColor3)
        colorList.add(R.color.NoteColor4)
        colorList.add(R.color.NoteColor5)
        colorList.add(R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(colorList.size)
        return colorList[randomIndex]
    }
}