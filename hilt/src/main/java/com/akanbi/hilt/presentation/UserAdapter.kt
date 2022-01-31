package com.akanbi.hilt.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akanbi.hilt.databinding.ContentUserBinding
import com.akanbi.hilt.network.model.User

class UserAdapter(private var list: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ContentUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    class UserViewHolder(private val viewBinding: ContentUserBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(user: User) = with(viewBinding) {
            userContent.text = user.name
        }

    }
}