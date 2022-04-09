package com.example.roomexample.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.R
import com.example.roomexample.data.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        val txt: TextView = holder.itemView.findViewById(R.id.id_txt)
        val firstName: TextView = holder.itemView.findViewById(R.id.firstName_txt)
        val lastName: TextView = holder.itemView.findViewById(R.id.lastName_txt)
        val age: TextView = holder.itemView.findViewById(R.id.age_txt)
        val layout: ConstraintLayout = holder.itemView.findViewById(R.id.recyclerItem)

        txt.text = currentItem.id.toString()
        firstName.text = currentItem.firstname
        lastName.text = currentItem.lastname
        age.text = currentItem.age.toString()

        layout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(userList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}