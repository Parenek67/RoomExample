package com.example.roomexample.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.R
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import com.example.roomexample.data.UserViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
        val adapter = ListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        userViewModel = ViewModelProvider(this, UserViewModelFactory(requireContext())).
            get(UserViewModel::class.java)
        userViewModel.getAllData().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return view
    }

}