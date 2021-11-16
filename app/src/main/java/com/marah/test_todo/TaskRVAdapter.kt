package com.marah.test_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class TaskRVAdapter(private var taskList: List<Task>, val viewModel: TaskViewModel) :
    RecyclerView.Adapter<TaskAdapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return TaskAdapter(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter, position: Int) {
        val task = taskList[position]
        holder.titleTV.text = task.taskTitle
        holder.descTV.text = task.taskDescription
        holder.dueDateTV.text = task.dueDate
        holder.checkBx.isChecked = task.completed
        holder.checkBx.setOnCheckedChangeListener { _, ischeckd ->
            if (holder.checkBx.isChecked) {
                task.completed = true
                viewModel.update(task)
            } else {
                task.completed = false
            }
        }

        holder.delBtn.setOnClickListener {
            viewModel.delete(task)
            taskList -= task
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }


        //Move it to first fragment
        holder.itemView.setOnClickListener { view ->
            val action = FirstFragmentDirections.actionMainActivityToUpdateDelFragment(task)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}

class TaskAdapter(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val titleTV: TextView = itemView.findViewById(R.id.tvTaskTitle)
    val descTV: TextView = itemView.findViewById(R.id.tvDesc)
    val dueDateTV: TextView = itemView.findViewById(R.id.tvDueDate)
    val checkBx: CheckBox = itemView.findViewById(R.id.checkbox)
    val delBtn: ImageButton = itemView.findViewById(R.id.deleteBtn)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(itemView.context, "${titleTV.text} clicked", Toast.LENGTH_SHORT).show()
    }

}