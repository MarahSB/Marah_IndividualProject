package com.marah.test_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskRVAdapter(private val taskList: List<Task>):RecyclerView.Adapter<TaskAdapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent,false)
        return TaskAdapter(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter, position: Int) {
        val task = taskList[position]
        holder.titleTV.text = task.taskTitle
        holder.descTV.text = task.taskDescription
        holder.dueDateTV.text = task.dueDate.toString()
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}

class TaskAdapter(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
    val titleTV: TextView = itemView.findViewById(R.id.tvTaskTitle)
    val descTV: TextView = itemView.findViewById(R.id.tvDesc)
    val dueDateTV: TextView = itemView.findViewById(R.id.tvDueDate)
    init {
        itemView.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
      //  TODO("Not yet implemented")
    }

}