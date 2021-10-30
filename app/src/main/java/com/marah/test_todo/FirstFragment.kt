package com.marah.test_todo

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment(R.layout.fragment_first) {
//Main fragment

    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn: Button
    private lateinit var filterBtn: Button
    private lateinit var infoBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBtn = view.findViewById(R.id.add)
        addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainActivity_to_addTask_Fragment)
        }

        filterBtn = view.findViewById(R.id.filter)
        filterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainActivity_to_editTask_Fragment)
        }

        infoBtn = view.findViewById(R.id.info)
        infoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainActivity_to_infoFragment)
        }

        val viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        viewModel.getAllTasks().observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = TaskRVAdapter(it, viewModel)
        })



       }









}