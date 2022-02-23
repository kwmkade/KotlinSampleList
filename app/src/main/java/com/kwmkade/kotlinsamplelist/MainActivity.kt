package com.kwmkade.kotlinsamplelist

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.kwmkade.kotlinsamplelist.db.Todo
import com.kwmkade.kotlinsamplelist.db.TodoDatabase
import com.kwmkade.kotlinsamplelist.db.TodoRepository

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: RecyclerAdapter

    private val swipeToDismissTouchHelper by lazy {
        getSwipeToDismissTouchHelper(adapter)
    }

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

        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)

//        addItemButton.setOnClickListener {
//            mainViewModel.addItem(submitText.text.toString())
//        }
//
//        mainViewModel.todo.observe(this, Observer {
//            adapter.setItem(it)
//        })
    }


    private fun getSwipeToDismissTouchHelper(adapter: RecyclerAdapter) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.ACTION_STATE_IDLE,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val timeTasks = adapter.getItems()
                mainViewModel.delete(timeTasks[viewHolder.bindingAdapterPosition])
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                val itemView = viewHolder.itemView
                val background = ColorDrawable(Color.RED)
                val deleteIcon = AppCompatResources.getDrawable(
                    this@MainActivity,
                    R.drawable.ic_baseline_delete_24
                )
                val iconMarginVertical =
                    (viewHolder.itemView.height - deleteIcon!!.intrinsicHeight) / 2

                deleteIcon.setBounds(
                    itemView.left + iconMarginVertical,
                    itemView.top + iconMarginVertical,
                    itemView.left + iconMarginVertical + deleteIcon.intrinsicWidth,
                    itemView.bottom - iconMarginVertical
                )
                background.setBounds(
                    itemView.left,
                    itemView.top,
                    itemView.right + dX.toInt(),
                    itemView.bottom
                )
                background.draw(c)
                deleteIcon.draw(c)
            }
        })
}