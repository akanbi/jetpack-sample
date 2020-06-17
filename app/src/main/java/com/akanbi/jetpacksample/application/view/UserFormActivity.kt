package com.akanbi.jetpacksample.application.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.akanbi.jetpacksample.R
import com.akanbi.jetpacksample.application.viewmodel.UserFormViewModel
import com.akanbi.jetpacksample.domain.model.User
import kotlinx.android.synthetic.main.user_form.*
import org.joda.time.LocalDateTime
import org.koin.android.viewmodel.ext.android.viewModel

class UserFormActivity : AppCompatActivity() {

    private val viewModel: UserFormViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_form)

        saveUser.setOnClickListener(saveNewUser())
    }

    private fun saveNewUser(): View.OnClickListener? {
        return View.OnClickListener {
            viewModel.save(buildUser()).observe(this, Observer {
                if (it == null)
                    finish()
            })
        }
    }

    private fun buildUser(): User {
        return User(name = nameText.text.toString(), surname = surnameText.text.toString(), birthDate = LocalDateTime.parse(birthdayText.text.toString()))
    }

    companion object {
        const val USER_SELECTED = "USER_SELECTED"
    }
}