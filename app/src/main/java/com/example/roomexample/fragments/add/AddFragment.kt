package com.example.roomexample.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomexample.R
import com.example.roomexample.data.model.User
import com.example.roomexample.data.viewmodel.UserViewModel
import com.example.roomexample.data.viewmodel.UserViewModelFactory

class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val btn: Button = view.findViewById(R.id.add_btn)
        val firstName: EditText = view.findViewById(R.id.addFirstName_et)
        val lastName: EditText = view.findViewById(R.id.addLastName_et)
        val age: EditText = view.findViewById(R.id.addAge_et)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(requireContext())).
            get(UserViewModel::class.java)

        btn.setOnClickListener{
            insertUser(firstName.text.toString(), lastName.text.toString(), age.text.toString())
        }
        return view
    }

    private fun insertUser(firstName: String, lastName: String, age: String){
        userViewModel.insertUser(User(0, firstName, lastName, Integer.parseInt(age)))
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}