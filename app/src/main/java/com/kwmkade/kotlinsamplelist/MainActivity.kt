package com.kwmkade.kotlinsamplelist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.kwmkade.kotlinsamplelist.db.Todo
import com.kwmkade.kotlinsamplelist.db.TodoDatabase
import com.kwmkade.kotlinsamplelist.db.TodoRepository

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = RecyclerAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        var submitText: EditText = findViewById(R.id.submit_text)
        val addItemButton: Button = findViewById(R.id.add_item_button)

        val db = Room.databaseBuilder(this, TodoDatabase::class.java, "database_name").build()
        val dao = db.todoDao()
        val repository = TodoRepository(dao)
        mainViewModel = MainViewModel(repository)

        addItemButton.setOnClickListener {
            mainViewModel.insert(Todo(0, submitText.text.toString()))
        }

        mainViewModel.todoList.observe(this, Observer {
            adapter.setItem(it)
        })

//        addItemButton.setOnClickListener {
//            mainViewModel.addItem(submitText.text.toString())
//        }
//
//        mainViewModel.todo.observe(this, Observer {
//            adapter.setItem(it)
//        })
    }
}