package com.example.what_to_do.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.what_to_do.data.Task
import com.example.what_to_do.databinding.TaskItemLayoutBinding
import com.example.what_to_do.view.task.TaskViewModel

class TaskListAdapter(
    val viewModel: TaskViewModel,
    private val taskClickListener: TaskClickListener
) :
    ListAdapter<Task, TaskListAdapter.TaskViewHolder>(COMARATOR) {

    fun interface TaskClickListener {
        fun onTaskClicked(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(viewModel, task, taskClickListener)
    }

    class TaskViewHolder private constructor(val binding: TaskItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: TaskViewModel, mTask: Task, taskClickListener: TaskClickListener) {
            binding.apply {
                root.setOnClickListener {
                    taskClickListener.onTaskClicked(mTask)
                }
                taskViewModel = viewModel
                task = mTask
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemLayoutBinding.inflate(layoutInflater, parent, false)

                return TaskViewHolder(binding)
            }
        }
    }

    companion object {
        val COMARATOR = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}