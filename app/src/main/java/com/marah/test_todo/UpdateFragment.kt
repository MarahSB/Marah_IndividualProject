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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class UpdateFragment : Fragment() {

//    private val CHANNEL_ID = "channel_id_01"
//    private val notificatioId = 101
    private lateinit var calIcon: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

  //  Notification
//
//    private fun createNotChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Title"
//            val notDescriptionn = "Decr for notification"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = notDescriptionn
//            }
//            val notificationManager: NotificationManager =
//                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    private fun sendNotification(){
//        val builder = NotificationCompat.Builder(context.this,CHANNEL_ID)
//    }

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
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        currentDate.text = receivedTask.creationDate

//Compare date
        val current1 = LocalDate.now()
        val formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/d")
        val nowDate = current1.format(formatter1)
        val today = LocalDate.parse(nowDate, DateTimeFormatter.ofPattern("yyyy/MM/d"))
        var dueDateString = taskDueDate.text.toString()
        dueDateString = dueDateString.format(formatter1)
        val dueDate = LocalDate.parse(dueDateString, DateTimeFormatter.ofPattern("yyyy/MM/d"))

        if (dueDate.compareTo(today) < 0) {
            //Date1 is after Date2
            //Task is pass due date
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
