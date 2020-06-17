package com.akanbi.jetpacksample.application.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.akanbi.jetpacksample.R
import com.akanbi.jetpacksample.application.adapter.UserAdapter
import com.akanbi.jetpacksample.application.viewmodel.UserListViewModel
import com.akanbi.jetpacksample.domain.model.User
import kotlinx.android.synthetic.main.user_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserListActivity : AppCompatActivity() {

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list)

        initAdapterComponent()
        initViewModel()
        addUser.setOnClickListener(addNewUser())
    }

    private fun initAdapterComponent() {
        userList.adapter = UserAdapter()
        (userList.adapter as UserAdapter).removeUserListener = this::removeUser
        (userList.adapter as UserAdapter).editUserListener = this::editUser
    }

    private fun initViewModel() {
        viewModel.list().observe(this, Observer {
            (userList.adapter as UserAdapter).refreshList(it)
        })
    }

    private fun addNewUser(): View.OnClickListener? {
        return View.OnClickListener {
            startActivity(Intent(this, UserFormActivity::class.java))
        }
    }

    private fun removeUser(user: User) {
        viewModel.remove(user).observe(this, Observer {
            if (it == null)
                Toast.makeText(this, getString(R.string.successfullyDeleted), Toast.LENGTH_LONG).show()
        })
    }

    private fun editUser(user: User) {
        val intent = Intent(this, UserFormActivity::class.java)
        intent.putExtra(UserFormActivity.USER_SELECTED, user)
        startActivity(intent)
    }

}