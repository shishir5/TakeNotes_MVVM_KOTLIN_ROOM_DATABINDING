package com.shishir.takeNotesV2.noteDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shishir.takeNotesV2.R
import com.shishir.takeNotesV2.databinding.ItemColorPickerBinding

class ColorPickerAdapter : RecyclerView.Adapter<ColorPickerAdapter.ColorViewHolder>() {
    private val mColors: List<Int> = arrayListOf(R.color.pink, R.color.blueLight, R.color.greenLight, R.color.yellowLight,
            R.color.purpleLight, R.color.brown)


    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = mColors[position]
        holder.bind(color)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemColorPickerBinding.inflate(inflater)
        return ColorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mColors.size
    }

    class ColorViewHolder(private var binding: ItemColorPickerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Int) {
            binding.imageColor.setBackgroundColor(ContextCompat.getColor(binding.root.context, color))
        }
    }

}