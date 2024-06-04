package com.example.what_to_do.view.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.what_to_do.R
import com.example.what_to_do.adapter.TaskListAdapter
import com.example.what_to_do.data.Task
import com.example.what_to_do.databinding.FragmentTaskBinding
import com.example.what_to_do.view.addtask.AddTaskViewModel

class TaskFragment : Fragment(), TaskListAdapter.HandleUserClick {
    private lateinit var binding: FragmentTaskBinding
    private val viewModel by viewModels<TaskViewModel>()
    private lateinit var adapter: TaskListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_task, container, false)
        // Inflate the layout for this fragment
        binding.taskViewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.allTask.observe(viewLifecycleOwner) {list ->
            adapter = TaskListAdapter(this, list)
            binding.recyclerView.adapter = adapter
        }

        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
        }

        return binding.root
    }

    override fun onEditClick(task: Task) {
        findNavController().navigate(
            TaskFragmentDirections.actionTaskFragmentToAddTaskFragment(
                task.id,
                task.title,
                task.description,
                task.isCompleted
            )
        )
    }
}