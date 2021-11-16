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
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class SecondFragment : Fragment(R.layout.fragment_second) {
//Add
    private lateinit var pickDate: TextView
    private lateinit var dueDate: String
    private lateinit var calIcon: ImageButton



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskTitle: EditText = view.findViewById(R.id.etNewTask)
        val taskDescription: EditText = view.findViewById(R.id.etDescription)
        val taskDueDate: TextView = view.findViewById(R.id.tvDate)
        val currentDate: TextView = view.findViewById(R.id.currentDate)
        val addBtn: Button = view.findViewById(R.id.btnSave)
        addBtn.setOnClickListener {
            val mainVM = ViewModelProvider(this).get(TaskViewModel::class.java)
            val format = SimpleDateFormat("yyy-MM-dd HH:mm")
            val current = format.format(Date())


            val task = Task(
                taskTitle = taskTitle.text.toString(),
                taskDescription = taskDescription.text.toString(),
                dueDate = taskDueDate.text.toString(),
                creationDate = currentDate.setText(current).toString()
            )

            mainVM.insert(task)
            view.findNavController().navigate(R.id.action_addTask_Fragment_to_mainActivity)
        }

        calIcon = view.findViewById(R.id.cal)
        calIcon.setOnClickListener {
            val cal = Calendar.getInstance()
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener{ view,y,m,d ->
                dueDate = "$y/${m + 1}/$d"
                pickDate.setText(dueDate) },year,month,day)
            datePickerDialog.datePicker.minDate = cal.timeInMillis
            datePickerDialog.show()

        }

    }
    companion object{
        fun newInstance() = SecondFragment()
    }




}