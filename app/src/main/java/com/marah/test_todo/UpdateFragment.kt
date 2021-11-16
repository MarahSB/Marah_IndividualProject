package com.marah.test_todo

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
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
        calIcon = view.findViewById(R.id.cal1)

        taskTitle.setText(receivedTask.taskTitle)
        taskDescription.setText(receivedTask.taskDescription)
        taskDueDate.setText(receivedTask.dueDate)
        currentDate.setText(receivedTask.creationDate)
        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/M/dd")
        currentDate.text = receivedTask.creationDate

//Compare date
        val current1 = LocalDate.now()
        val formatter1 = DateTimeFormatter.ofPattern("yyyy/M/d")
        val nowDate = current1.format(formatter1)
        val today = LocalDate.parse(nowDate, DateTimeFormatter.ofPattern("yyyy/M/d"))
        var dueDateString = taskDueDate.text.toString()
        dueDateString = dueDateString.format(formatter1)
        val dueDate = LocalDate.parse(dueDateString, DateTimeFormatter.ofPattern("yyyy/M/d"))

        if (dueDate.compareTo(today) < 0) {
            //Date1 is after Date2
            //Task is pass due date
            Toast.makeText(context, "This task is pass due date", Toast.LENGTH_LONG)
            taskTitle.isEnabled = false
            calIcon.isClickable = false
            calIcon.isEnabled = false
        }

        calIcon.setOnClickListener {
            val cal = Calendar.getInstance()
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { view, y, m, d ->
                    var dueDate = "$y/${m + 1}/$d"
                    taskDueDate.setText(dueDate)
                }, year, month, day)
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
