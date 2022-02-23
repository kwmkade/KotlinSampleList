package com.kwmkade.kotlinsamplelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwmkade.kotlinsamplelist.db.Todo
import com.kwmkade.kotlinsamplelist.db.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TodoRepository) : ViewModel() {
    val todoList = repository.allTodoList

    // NOTE: ViewModelScope は、アプリで ViewModel ごとに定義されます。
    //       このスコープ内で起動されたすべてのコルーチンは、ViewModel が消去されると自動的にキャンセルされます。
    //       ViewModel がアクティブな場合にのみ行う必要がある作業があるとき、コルーチンが役に立ちます。
    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        repository.delete(todo)
    }
}

//class MainViewModel() : ViewModel() {
//    // NOTE: LiveData は監視可能なデータホルダー クラスです。
//    //       通常の監視とは異なり、LiveData はライフサイクルに応じた監視が可能です。
//    //       つまり、アクティビティ、フラグメント、サービスなどの他のアプリ コンポーネントのライフサイクルが考慮されます。
//    //       このため、LiveData はライフサイクルの状態がアクティブなアプリ コンポーネント オブザーバーのみを更新します
//    val todo = MutableLiveData<String>()
//
//    fun addItem(item: String) {
//        todo.value = item
//    }
//}