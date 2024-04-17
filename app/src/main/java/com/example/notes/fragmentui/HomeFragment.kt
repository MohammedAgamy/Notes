package com.example.notes.fragmentui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.R
import com.example.notes.adapter.AdapterNote
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.models.NoteEntityModel
import com.example.notes.models.NoteViewModel
import com.example.notes.ui.MainActivity

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener, MenuProvider
    {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: AdapterNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manuHost: MenuHost = requireActivity()
        manuHost.addMenuProvider(this, viewLifecycleOwner,Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).noteViewModel
        setUpHomeRecycler()
        binding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

    }


    //visible ui
    fun updateUi(noteEntityModel: List<NoteEntityModel>?) {
        if (noteEntityModel != null) {
            if (noteEntityModel.isNotEmpty()) {
                binding.emptyNotesImage.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            } else {
                binding.emptyNotesImage.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }

    fun setUpHomeRecycler() {
        noteAdapter = AdapterNote()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner) { note ->
                noteAdapter.differ.submitList(note)
                updateUi(note)
            }
        }
    }


    fun searchNote(query:String?)
    {
        val searchQuery ="%$query"
        noteViewModel.search(searchQuery).observe(viewLifecycleOwner){list->
            noteAdapter.differ.submitList(list)
        }
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch =menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled =false
        menuSearch.setOnQueryTextListener(this)


    }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
           return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText != null)
            {
                searchNote(newText)
            }
            return true
        }

        override fun onDestroy() {
            super.onDestroy()
            homeBinding =null
        }

    }