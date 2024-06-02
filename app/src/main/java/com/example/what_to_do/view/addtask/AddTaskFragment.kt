package com.example.what_to_do.view.addtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.what_to_do.R
import com.example.what_to_do.databinding.FragmentAddTaskBinding
import com.example.what_to_do.utils.showChar
import com.example.what_to_do.utils.showSnackBar
import com.google.android.material.snackbar.Snackbar

class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel by viewModels<AddTaskViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_add_task,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        bindUiMessage()

        return binding.root
    }

    private fun bindUiMessage() {
        binding.warningTextTaskTitle.showChar(lifecycleOwner = viewLifecycleOwner, viewModel.title)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSnackbar(view)
    }

    private fun setSnackbar(view: View) {
        view.showSnackBar(
            lifecycleOwner = viewLifecycleOwner,
            viewModel.snackbarMsg,
            Snackbar.LENGTH_SHORT
        )
    }


}