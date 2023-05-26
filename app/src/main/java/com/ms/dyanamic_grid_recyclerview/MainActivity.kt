package com.ms.dyanamic_grid_recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter
    private val itemList = mutableListOf<Item>()
    private var selectedItemPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create 5 default items
        val n= 5
        for (i in 1..n) {
            val item = Item(i)
            itemList.add(item)
        }


        itemAdapter = ItemAdapter(itemList, selectedItemPosition) { item ->
//            showToast("Clicked on ${item.name}")
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = GridLayoutManager(this,6)

//        Next Button
        /**val nextButton: ImageView = findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            val newPosition = selectedItemPosition!! + 1
            if (newPosition < itemList.size) {
                selectItem(newPosition)
            }
        }
        **/

//        Previous Button
/**
        val prevButton: ImageView = findViewById(R.id.prevButton)
        prevButton.setOnClickListener {
            val newPosition = selectedItemPosition!! - 1
            if (newPosition >= 0) {
                selectItem(newPosition)
            }
        }
        **/

        val addButton: ImageView = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val newItemId = itemList.size + 1
            val newItem = Item(newItemId)
            itemList.add(newItem)
            itemAdapter.notifyDataSetChanged()
        }

        val removeButton: TextView = findViewById(R.id.removeButton)
        removeButton.setOnClickListener {
            if (itemList.isNotEmpty()) {
                itemList.removeAt(itemList.size - 1)
                itemAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun selectItem(position: Int) {
        if (selectedItemPosition != RecyclerView.NO_POSITION) {
            itemList[selectedItemPosition!!].isSelected = false
            itemAdapter.notifyItemChanged(selectedItemPosition!!)
        }

        itemList[position].isSelected = true
        itemAdapter.notifyItemChanged(position)
        selectedItemPosition = position
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
