package com.kwmkade.kotlinsamplelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kwmkade.kotlinsamplelist.db.Todo

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private val todoList = mutableListOf<Todo>()

    fun setItem(items: List<Todo>) {
        todoList.clear()
        todoList.addAll(items)
        notifyDataSetChanged()
    }

//    private val todoList = mutableListOf<String>()
//    fun setItem(item: String) {
//        todoList.add(item)
//        notifyDataSetChanged()
//    }

    class RecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val sampleImg: ImageView = view.findViewById(R.id.sampleImg)
        val sampleTxt: TextView = view.findViewById(R.id.sampleTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerViewHolder(item)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.let {
            it.sampleImg.setImageResource(R.mipmap.ic_launcher_round)
            it.sampleTxt.text = todoList[position].todoTitle
            //it.sampleTxt.text = todoList[position]
        }
    }
}