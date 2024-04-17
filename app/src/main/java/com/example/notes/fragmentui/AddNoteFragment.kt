package com.example.notes.fragmentui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.notes.R
import com.example.notes.adapter.AdapterNote
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.models.NoteEntityModel
import com.example.notes.models.NoteViewModel
import com.example.notes.ui.MainActivity

class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {
    private var addBinding: FragmentAddNoteBinding? = null
    private val binding get() = addBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        addBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val manuHost: MenuHost = requireActivity()
        manuHost.addMenuProvider(this, viewLifecycleOwner,Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).noteViewModel
        noteView = view

    }


    fun saveData(view:View)
    {
        val title = binding.addNoteTitle.text.toString().trim()
        val note = binding.addNoteDesc.text.toString().trim()

        if (title.isNotEmpty())
        {
            val note= NoteEntityModel(0,title, note )
            noteViewModel.addNote(note)
            view.findNavController().popBackStack(R.id.homeFragment,false)
            Toast.makeText(noteView.context, "Note  Saved",Toast.LENGTH_SHORT).show()

        }

        else{
            Toast.makeText(noteView.context, "Note Not Saved",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId)
        {
            R.id.saveMenu ->{
                saveData(noteView)
                true
            }
            else->false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addBinding=null
    }

}