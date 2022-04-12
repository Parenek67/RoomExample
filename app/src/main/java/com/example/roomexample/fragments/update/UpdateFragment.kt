package com.example.roomexample.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
    private lateinit var firstname: EditText
    private lateinit var lastname: EditText
    private lateinit var age: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        firstname = view.findViewById(R.id.updateFirstName_et)
        lastname = view.findViewById(R.id.updateLastName_et)
        age = view.findViewById(R.id.updateAge_et)
        val btn: Button = view.findViewById(R.id.update_btn)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(requireContext())).
            get(UserViewModel::class.java)

        firstname.setText(args.currentUser.firstname)
        lastname.setText(args.currentUser.lastname)
        age.setText(args.currentUser.age.toString())

        btn.setOnClickListener{
            updateItem(firstname.text.toString(), lastname.text.toString(), age.text.toString())
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem(firstname: String, lastname: String, age: String){
        userViewModel.updateUser(User(args.currentUser.id, firstname, lastname, Integer.parseInt(age)))
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            userViewModel.deleteUser(args.currentUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}