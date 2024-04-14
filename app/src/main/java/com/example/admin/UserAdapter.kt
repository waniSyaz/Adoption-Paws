package com.example.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val context: Context, private val userList: ArrayList<Model>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userId: TextView = itemView.findViewById(R.id.tvID)
        private val userEmail: TextView = itemView.findViewById(R.id.tvEmail)
        private val userName: TextView = itemView.findViewById(R.id.tvName)
        private val userPassword: TextView = itemView.findViewById(R.id.tvPassword)
        private val userPhone: TextView = itemView.findViewById(R.id.tvPhone)

        fun bind(user: Model) {
            userId.text = user.userId
            userEmail.text = user.userEmail
            userName.text = user.userName
            userPassword.text = user.userPassword
            userPhone.text = user.userPhone
        }
    }
}
