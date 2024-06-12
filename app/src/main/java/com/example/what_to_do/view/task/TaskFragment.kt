package com.example.what_to_do.view.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.what_to_do.R
import com.example.what_to_do.adapter.TaskListAdapter
import com.example.what_to_do.data.Task
import com.example.what_to_do.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
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

        setTaskAdapter()
        setUpNavigation()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
        }
    }

    private fun setTaskAdapter() {
        val viewModel = binding.taskViewModel
        if (viewModel != null){
            adapter = TaskListAdapter(viewModel){task->
                val action = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment(task.id)
                findNavController().navigate(action)
            }
            binding.recyclerView.adapter = adapter
        }
    }

}