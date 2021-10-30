package com.marah.test_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marah.test_todo.R

class ThirdFragment : Fragment(R.layout.fragment_third) {
    //Filter today tasks


    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backBtn:Button = view.findViewById(R.id.back)
        backBtn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_todayTask_Fragment_to_mainActivity)
        }

        val viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        recyclerView = view.findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        viewModel.getDoneTasks() .observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = FilterRVAdapter(it, viewModel) })

    }
}
