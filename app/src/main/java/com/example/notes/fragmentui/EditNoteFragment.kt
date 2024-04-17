package com.example.notes.fragmentui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.databinding.FragmentEditNoteBinding
import com.example.notes.models.NoteEntityModel
import com.example.notes.models.NoteViewModel
import com.example.notes.ui.MainActivity

class EditNoteFragment  : Fragment(R.layout.fragment_edit_note) , MenuProvider{

    private var editNoteFragment: FragmentEditNoteBinding? = null
    private val binding get() = editNoteFragment!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var entitynoteFragment: NoteEntityModel
    private  val args: EditNoteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        editNoteFragment=FragmentEditNoteBinding.inflate(inflater,container,false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manuHost: MenuHost = requireActivity()
        manuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel = (activity as MainActivity).noteViewModel

        entitynoteFragment = args.note!!

        binding.editNoteTitle.setText(entitynoteFragment.title)
        binding.editNoteDesc.setText(entitynoteFragment.note)

        binding.editNoteFab.setOnClickListener {
            val title = binding.editNoteTitle.text.toString().trim()
            val note = binding.editNoteDesc.text.toString().trim()

            if (title.isNotEmpty())
            {
                val note= NoteEntityModel(entitynoteFragment.id,title, note )
                noteViewModel.update(note)
                view.findNavController().popBackStack(R.id.homeFragment,false)
                Toast.makeText(context, "Note  Saved", Toast.LENGTH_SHORT).show()

            }

            else{
                Toast.makeText(context, "Note Not Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteNote()
    {
        AlertDialog.Builder(activity).apply {
            setTitle("DeleteNote")
            setMessage("Do You need This Note")
            setPositiveButton("Delete"){_,_ ->
                noteViewModel.delete(entitynoteFragment)
                Toast.makeText(context, "Note Not Saved",Toast.LENGTH_SHORT).show()
                view?.findNavController()!!.popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Cancel",null)
        }.create().show()

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note, menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId)
        {
            R.id.saveMenu ->{
                deleteNote()
                true
            }
            else->false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteFragment=null
    }

}