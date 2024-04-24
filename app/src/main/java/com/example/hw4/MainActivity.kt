package com.example.hw4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import com.example.hw4.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        println("program started")
//        var  = false
        var phoneFlag = false
        var radioFlag = false
        var switchFlag = false

        var nameFlag: Boolean by Delegates.observable(false) { _, old, new ->
            if (new != old) {
                binding.saveButton.isEnabled = true

            } else {
                binding.saveButton.isEnabled = false

            }
        }


        val progressNum = (0..100).random()

        binding.nameInputLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
        binding.progressPoints.text = toStr(progressNum)
        binding.progressBar.progress = progressNum

        binding.receiveNotificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.checkboxAboutNews.isEnabled = isChecked
            binding.checkboxAboutAuthorization.isEnabled = isChecked
            if (!isChecked) {
                switchFlag = true

            } else if (binding.checkboxAboutNews.isActivated || binding.checkboxAboutAuthorization.isActivated) {
                switchFlag = true

            }
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.isNotEmpty()) {
                    nameFlag = true
                }
                else {
                    nameFlag = false
                }
            }
        })
        binding.editPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.isNotEmpty()) {
                    phoneFlag = true

                }
            }
        })
    }

    private fun toStr(str: Int): String {
        return "$str/100"
    }
}