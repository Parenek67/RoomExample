package com.example.roomexample.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomexample.R
import com.example.roomexample.data.model.User
import com.example.roomexample.data.viewmodel.UserViewModel
import com.example.roomexample.data.viewmodel.UserViewModelFactory

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        val firstname: EditText = view.findViewById(R.id.updateFirstName_et)
        val lastname: EditText = view.findViewById(R.id.updateLastName_et)
        val age: EditText = view.findViewById(R.id.updateAge_et)
        val btn: Button = view.findViewById(R.id.update_btn)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(requireContext())).
            get(UserViewModel::class.java)

        firstname.setText(args.currentUser.firstname)
        lastname.setText(args.currentUser.lastname)
        age.setText(args.currentUser.age.toString())

        btn.setOnClickListener{
            updateItem(firstname.text.toString(), lastname.text.toString(), age.text.toString())
        }

        return view
    }

    private fun updateItem(firstname: String, lastname: String, age: String){
        userViewModel.updateUser(User(args.currentUser.id, firstname, lastname, Integer.parseInt(age)))
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
}