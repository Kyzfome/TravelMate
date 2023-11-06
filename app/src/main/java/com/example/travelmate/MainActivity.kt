package com.example.travelmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginTextInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout2)
        val passwordTextInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout3)
        val loginEditText = loginTextInputLayout.findViewById<EditText>(R.id.loginTextField)
        loginEditText.setTextColor(ContextCompat.getColor(this, R.color.white))
        val passwordEditText = passwordTextInputLayout.findViewById<EditText>(R.id.passwordTextField)
        passwordEditText.setTextColor(ContextCompat.getColor(this, R.color.white))
        val enterButton = findViewById<Button>(R.id.enterButton)

        enterButton.setOnClickListener {

            val enteredLogin = loginEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()
            if (enteredLogin == "test" && enteredPassword == "1234") {

                println("Введенный логин: $enteredLogin")
                println("Введенный пароль: $enteredPassword")
                setContentView(R.layout.item)
            }
        }
    }
}
