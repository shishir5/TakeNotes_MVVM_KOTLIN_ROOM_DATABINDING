package com.shishir.takeNotesV2.noteDetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shishir.takeNotesV2.R
import com.shishir.takeNotesV2.databinding.ActivityNoteDetailsBinding
import com.shishir.takeNotesV2.notesHome.NotesViewModel
import com.shishir.takeNotesV2.pojo.NoteVO
import com.shishir.takeNotesV2.util.IntentUtil
import com.shishir.takeNotesV2.util.ObjectUtils




class NoteDetailsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityNoteDetailsBinding
    private lateinit var mColorPickerAdapter: ColorPickerAdapter
    private lateinit var mBottomsheetBehaviour: BottomSheetBehavior<View>
    private var mNoteId: Int = 0

    //notes viewmodel
    private lateinit var mNotesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()
        initView()
    }

    private fun initVariables() {
        if (intent.hasExtra(IntentUtil.NOTE_ID))
            mNoteId = intent.getIntExtra(IntentUtil.NOTE_ID, 0)
    }

    private fun initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_details)
        mNotesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        if (mNoteId != 0) {
            fetchNote()
        }
        setColorPickerBottomSheet()
    }

    private fun setColorPickerBottomSheet() {
        setSupportActionBar(mBinding.bottomAppBar)
        mBinding.bottomAppBar.replaceMenu(R.menu.bottomappbar_note_details)
        setColorPickerAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_note_details, menu)
        return true
    }



    private fun setColorPickerAdapter() {
        mColorPickerAdapter = ColorPickerAdapter()
        mBottomsheetBehaviour = BottomSheetBehavior.from(mBinding.layoutColorPicker.bottomSheetColorPicker)
        mBinding.layoutColorPicker.recyclerColor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        mBinding.layoutColorPicker.recyclerColor.adapter = mColorPickerAdapter
        mBottomsheetBehaviour.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        mBinding.layoutColorPicker.recyclerColor.adapter = mColorPickerAdapter
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    private fun fetchNote() {
        val note: NoteVO = mNotesViewModel.getNote(mNoteId)
        mBinding.etNoteTitle.setText(note.title)
        mBinding.etNoteDetail.setText(note.note)
    }

    override fun onClick(v: View?) {

    }

    override fun onBackPressed() {
        saveNote()
        super.onBackPressed()
    }

    private fun saveNote() {
        val title: String = mBinding.etNoteTitle.text.toString()
        val note: String = mBinding.etNoteDetail.text.toString()

        //Check if title and description are not null or empty
        if (ObjectUtils.isNotNull(title) && ObjectUtils.isNotNull(note))
            mNotesViewModel.addNoteToDb(mNoteId, title, note)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.bottom_action_color -> {
                mBottomsheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED)
            }
        }
        return true
    }
}