package com.example.what_to_do.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.what_to_do.data.Task
import com.example.what_to_do.databinding.TaskItemLayoutBinding

class TaskListAdapter(private val list: List<Task>) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        list[position].let {
            holder.binding.apply {
                tvTitle.text = it.title
            }
        }
    }

    inner class TaskViewHolder(val binding: TaskItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}