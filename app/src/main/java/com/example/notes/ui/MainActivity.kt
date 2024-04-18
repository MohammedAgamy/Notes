package com.example.notes.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.database.NoteDataBase
import com.example.notes.database.NoteRepository
import com.example.notes.models.NoteViewModel
import com.example.notes.models.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
lateinit var noteViewModel:NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()

    }


    fun  setUpViewModel()
    {
        //handler view model to start data
        //dataBase to crate room dataBAse
        val noteRepository =NoteRepository(NoteDataBase(this))
        val viewModelF =NoteViewModelFactory(application,noteRepository)
        noteViewModel =ViewModelProvider(this,viewModelF)[NoteViewModel::class.java]

    }




















/*

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataBase:NoteDataBase
    lateinit var viewModel: NoteViewModel
    lateinit var adapterNote: AdapterNote
    lateinit var selectedNote :NoteEntityModel


  private  val updateNote =registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

      if (result.resultCode == Activity.RESULT_OK) {
          val note = result.data?.getSerializableExtra("note") as? NoteEntityModel
          if (note != null) {
              viewModel.insertNote(note)
          }

      }
*/
  }


       /* iniUi()

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        viewModel.allNote.observe(this){ list->

            list?.let {
                adapterNote.updateList(list)

            }
        }

        dataBase =NoteDataBase.curationDataBase(this)

    }

    fun iniUi(){
        binding.showListNote.setHasFixedSize(true)
        binding.showListNote.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        adapterNote= AdapterNote(this,this)
        binding.showListNote.adapter=adapterNote

        val getContent =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->

            if (result.resultCode ==Activity.RESULT_OK)
            {
                val note =result.data?.getSerializableExtra("note") as? NoteEntityModel
                if (note != null)
                {
                    viewModel.insertNote(note)
                }

            }

        }

        binding.addBtn.setOnClickListener{
            val intent =Intent(this,AddNoteActivity::class.java)
            getContent.launch(intent)
        }

        binding.searshNote.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null)
                {
                    adapterNote.filterSearch(newText)
                }
                return true
            }

        })

    }

    override fun onNoteClickList(noteEntityModel: NoteEntityModel) {
        val intent =Intent(this@MainActivity ,AddNoteActivity::class.java)
        intent.putExtra("current_note" ,noteEntityModel)
        updateNote.launch(intent)
    }

    override fun onLongNoteClickList(noteEntityModel: NoteEntityModel, cardView: CardView) {
       selectedNote= noteEntityModel
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {
        val popUp =PopupMenu(this,cardView)
        popUp.setOnMenuItemClickListener(this@MainActivity)
        popUp.inflate(R.menu.pop_up_menu)
        popUp.show()

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId ==R.id.delete_note)
        {
            viewModel.deleteNote(selectedNote)
            return true
        }
        return false
    }*/

