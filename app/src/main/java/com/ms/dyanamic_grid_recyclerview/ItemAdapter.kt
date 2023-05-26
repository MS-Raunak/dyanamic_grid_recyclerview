package com.ms.dyanamic_grid_recyclerview

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val itemList: MutableList<Item>,
    private var selectedItemPosition: Int?,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = itemList[position]
                    handleItemClick(position, item)
                }
            }
        }

        fun bind(item: Item) {
//            nameTextView.text = item.name
            setItemBackground(item.isSelected)
        }

        private fun setItemBackground(isSelected: Boolean) {
            val drawable: Drawable? = if (isSelected) {
                ContextCompat.getDrawable(itemView.context, R.drawable.item_border_selected)
            } else {
                ContextCompat.getDrawable(itemView.context, R.drawable.item_border_default)
            }
            itemView.background = drawable
        }
    }

    private fun handleItemClick(position: Int, item: Item) {
        val previousSelectedPosition = selectedItemPosition
        if (previousSelectedPosition != null && previousSelectedPosition != position) {
            itemList[previousSelectedPosition].isSelected = false
            notifyItemChanged(previousSelectedPosition)
        }

        item.isSelected = !item.isSelected
        selectedItemPosition = if (item.isSelected) position else null
        notifyItemChanged(position)

        onItemClick(item)
    }
}
