package com.marah.test_todo

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationView

class FirstFragment : Fragment(R.layout.fragment_first) {
//Main fragment

    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: TaskRVAdapter

    private lateinit var addBtn: Button
    private lateinit var finishBtn: Button
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

        finishBtn = view.findViewById(R.id.finished)
        finishBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainActivity_to_editTask_Fragment )
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