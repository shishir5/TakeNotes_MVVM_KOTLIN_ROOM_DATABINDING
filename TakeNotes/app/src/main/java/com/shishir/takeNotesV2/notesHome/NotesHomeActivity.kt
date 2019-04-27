package com.shishir.takeNotesV2.notesHome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.shishir.takeNotesV2.R
import com.shishir.takeNotesV2.databinding.ActivityNotesHomeBinding
import com.shishir.takeNotesV2.noteDetails.NoteDetailsActivity
import com.shishir.takeNotesV2.util.IntentUtil

class NotesHomeActivity : AppCompatActivity(), View.OnClickListener, NotesAdapter.INoteItemClickListener {
    lateinit var mNotesAdapter: NotesAdapter

    //home screen view model
    lateinit var mNotesViewModel: NotesViewModel

    //databinding
    lateinit var mBinding: ActivityNotesHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notes_home)
        initView()
        subscribeToNotes()
    }

    private fun subscribeToNotes() {
        mNotesViewModel.getAllNotes().observe(this, Observer {
            it?.let {
                mNotesAdapter.setNotes(it)
            }
        })
    }

    private fun initView() {
        mBinding.fabAddNote.setOnClickListener(this)
        mNotesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mBinding.recycler.layoutManager = GridLayoutManager(this, 3)
        mNotesAdapter = NotesAdapter(this)
        mBinding.recycler.adapter = mNotesAdapter
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fab_add_note -> {
                openNoteScreen(null)
            }
        }
    }

    private fun openNoteScreen(noteId: Int?) {
        val intent = Intent(this, NoteDetailsActivity::class.java)
        if (noteId != null) {
            intent.putExtra(IntentUtil.NOTE_ID, noteId)
        }
        startActivity(intent)
    }

    override fun onItemClick(id: Int) {
        openNoteScreen(id)
    }


}