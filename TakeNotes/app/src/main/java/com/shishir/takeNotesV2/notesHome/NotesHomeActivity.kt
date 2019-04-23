package com.shishir.takeNotesV2.notesHome

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.shishir.takeNotesV2.NoteDetailsActivity
import com.shishir.takeNotesV2.R
import com.shishir.takeNotesV2.databinding.ActivityNotesHomeBinding
import com.shishir.takeNotesV2.pojo.NoteVO
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