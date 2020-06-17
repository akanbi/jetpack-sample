package com.akanbi.jetpacksample.application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akanbi.jetpacksample.R
import com.akanbi.jetpacksample.domain.model.User
import kotlinx.android.synthetic.main.user_element.view.*
import org.joda.time.LocalDateTime

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<User> = mutableListOf()

    var removeUserListener: (user: User) -> Unit = {}
    var editUserListener: (user: User) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_element, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.buildViewHolder(users[position])
    }

    override fun getItemCount() = users.size

    fun refreshList(list: List<User>) {
        this.users = list
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        fun buildViewHolder(user: User) {
            setFullname(user)
            setBirhday(user.birthDate)
            setEditUser(user)
            setRemoveUser(user)
        }

        private fun setFullname(user: User) {
            view.fullnameText.text = "${user.name} ${user.surname}"
        }

        private fun setBirhday(birthDate: LocalDateTime) {
            view.birthdayText.text = "${birthDate.toString()}"
        }

        private fun setEditUser(user: User) {
            view.setOnClickListener {
                editUserListener(user)
            }
        }

        private fun setRemoveUser(user: User) {
            view.removeUser.setOnClickListener {
                removeUserListener(user)
            }
        }
    }


}