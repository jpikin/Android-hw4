package com.example.hw4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hw4.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button

    private var nameFlag: Boolean by Delegates.observable(false) { _, _, _ ->
        updateButtonState()
    }
    private var phoneFlag: Boolean by Delegates.observable(false) { _, _, _ ->
        updateButtonState()
    }
    private var radioFlag: Boolean by Delegates.observable(false) { _, _, _ ->
        updateButtonState()
    }
    private var switchFlag: Boolean by Delegates.observable(false) { _, _, _ ->
        updateButtonState()
    }
    private fun updateButtonState(){
        button.isEnabled = nameFlag&&phoneFlag&&radioFlag&&switchFlag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        button = findViewById(R.id.saveButton)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                nameFlag = text.isNotEmpty()&&text.length<41
            }
        })

        binding.editPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                phoneFlag = text.isNotEmpty()
            }
        })

        updateButtonState()
    }

    private fun toStr(str: Int): String {
        return "$str/100"
    }
}