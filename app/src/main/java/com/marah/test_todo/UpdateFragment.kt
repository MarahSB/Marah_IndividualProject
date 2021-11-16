package com.marah.test_todo

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class UpdateFragment : Fragment() {

    private lateinit var calIcon: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: UpdateFragmentArgs by navArgs()
        val receivedTask = args.myTask

        val update: Button = view.findViewById(R.id.editBtn)
        val taskTitle: EditText = view.findViewById(R.id.etNewTask)
        val taskDescription: EditText = view.findViewById(R.id.etDescription)
        val taskDueDate: TextView = view.findViewById(R.id.tvDate)
        val currentDate: TextView = view.findViewById(R.id.currentDate)
        taskTitle.setText(receivedTask.taskTitle)
        taskDescription.setText(receivedTask.taskDescription)
        taskDueDate.setText(receivedTask.dueDate)
        currentDate.setText(receivedTask.creationDate)

        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val formatted = current.format(formatter)
        currentDate.text = receivedTask.creationDate



        calIcon = view.findViewById(R.id.cal1)
        calIcon.setOnClickListener {
            val cal = Calendar.getInstance()
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener{ view, y, m, d ->
                var dueDate =   "$y/${m + 1}/$d"
                taskDueDate.setText(dueDate) },year,month,day)
            datePickerDialog.datePicker.minDate = cal.timeInMillis
            datePickerDialog.show()

        }

        val mainVM = ViewModelProvider(this).get(TaskViewModel::class.java)
        update.setOnClickListener {

            receivedTask.taskTitle = taskTitle.text.toString()
            receivedTask.taskDescription = taskDescription.text.toString()
            receivedTask.dueDate = taskDueDate.text.toString()

            mainVM.update(receivedTask)
            view.findNavController().navigate(R.id.action_updateDelFragment_to_mainActivity)
        }
    }
}
