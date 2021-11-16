package com.marah.test_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

//Filter by done at the same date
class FilterRVAdapter(private var taskList: List<Task>, val viewModel: TaskViewModel)
:RecyclerView.Adapter<FilterAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_rv, parent, false)
        return FilterAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: FilterAdapterHolder, position: Int) {
        val task = taskList[position]
        holder.titleTV.text = task.taskTitle
        holder.descTV.text = task.taskDescription
        holder.dueDateTV.text = task.dueDate



    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}


class FilterAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val titleTV: TextView = itemView.findViewById(R.id.tvTaskTitle4)
    val descTV: TextView = itemView.findViewById(R.id.tvDesc4)
    val dueDateTV: TextView = itemView.findViewById(R.id.tvDueDate4)


    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(itemView.context, "${titleTV.text} clicked", Toast.LENGTH_SHORT).show()
    }

}