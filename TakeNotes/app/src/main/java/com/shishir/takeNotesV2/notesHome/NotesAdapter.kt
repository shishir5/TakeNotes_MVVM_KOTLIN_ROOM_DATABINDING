package com.shishir.takeNotesV2.notesHome

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shishir.takeNotesV2.databinding.HomeNoteItemViewBinding
import com.shishir.takeNotesV2.pojo.NoteVO
import com.shishir.takeNotesV2.util.NoteVODiffUtil

class NotesAdapter(var mlistener: INoteItemClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    var mNotesList: List<NoteVO> = listOf()

    fun setNotes(newList: List<NoteVO>) {
        var diffResult = DiffUtil.calculateDiff(NoteVODiffUtil(mNotesList, newList))
        diffResult.dispatchUpdatesTo(this)
        mNotesList = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeNoteItemViewBinding.inflate(inflater)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mNotesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: NoteVO = mNotesList.get(position)
        holder.setListner(mlistener)
        holder.bind(note)

    }

    class NoteViewHolder(val binding: HomeNoteItemViewBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View?) {
            mListner?.let {
                mListner!!.onItemClick(v?.tag.toString().toInt())
            }
        }

        private var mListner: INoteItemClickListener? = null

        fun bind(note: NoteVO) {
            binding.root.setTag(note.id)
            binding.root.setOnClickListener(this)
            binding.tvNoteTitle.setText(note.title)
            binding.tvNoteDetails.setText(note.note)
        }

        fun setListner(listener: INoteItemClickListener) {
            mListner = listener
        }
    }

    interface INoteItemClickListener {
        fun onItemClick(id: Int)
    }

}