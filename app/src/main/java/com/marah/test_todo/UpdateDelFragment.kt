package com.marah.test_todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class UpdateDelFragment : Fragment() {

private val args :UpdateDelFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_del, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task1 =args.myTask
       val update: Button = view.findViewById(R.id.editBtn)
        val delete: Button = view.findViewById(R.id.delBtn)

        val taskTitle: EditText = view.findViewById(R.id.etNewTask1)
        val taskDescription: EditText = view.findViewById(R.id.etDescription1)
        val taskDueDate: TextView = view.findViewById(R.id.tvDate1)
        val currentDate: TextView = view.findViewById(R.id.currentDate1)
        taskTitle.setText(task1.taskTitle)
        taskDescription.setText(task1.taskDescription)
        taskDueDate.setText(task1.dueDate)
        currentDate.setText(task1.creationDate)

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val formatted = current.format(formatter)
        currentDate.text = currentDate.text.toString() + formatted



//btn

        val mainVM = ViewModelProvider(this).get(TaskViewModel::class.java)
        //taskTitle.text.toString()
        update.setOnClickListener {


            val task = Task(
                taskTitle = taskTitle.text.toString(),
                taskDescription = taskDescription.text.toString(),
                dueDate = taskDueDate.text.toString(),
                creationDate = currentDate.setText(formatted).toString()
            )

            mainVM.update(task)
            view.findNavController().navigate(R.id.action_updateDelFragment_to_mainActivity)
        }

        delete.setOnClickListener {

        }


    }
}
