package com.example.what_to_do.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.what_to_do.adapter.TaskListAdapter
import com.example.what_to_do.data.Task

@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView,item: List<Task>?){
    item?.let {
        (recyclerView.adapter as TaskListAdapter).submitList(it)
    }
}