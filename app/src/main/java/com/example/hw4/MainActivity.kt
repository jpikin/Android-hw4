package com.example.hw4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hw4.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    // Слушаем флаги, вызываем  проверку флагов
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

    /**
     * Проверка, что все флаги true
     */
    private fun updateButtonState() {
        button.isEnabled = nameFlag && phoneFlag && switchFlag && radioFlag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressNum = (0..100).random()
        var newsFlag = false
        var authorFlag = false
        var switchCheck = true
        button = findViewById(R.id.saveButton)
        binding.nameInputLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
        binding.progressPoints.text = toStr(progressNum)
        binding.progressBar.progress = progressNum

        // Слушаем Чекбоксы и вызываем проверку флага
        binding.checkboxAboutNews.setOnCheckedChangeListener { _, isChecked ->
            newsFlag = isChecked
            switchFlag = setUpSwitchFlag(newsFlag, authorFlag, switchCheck)
        }
        binding.checkboxAboutAuthorization.setOnCheckedChangeListener { _, isChecked ->
            authorFlag = isChecked
            switchFlag = setUpSwitchFlag(newsFlag, authorFlag, switchCheck)

        }
        // Слушаем переключатель Switch и вызываем проверку флага
        binding.receiveNotificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            switchCheck = isChecked
            binding.checkboxAboutNews.isEnabled = isChecked
            binding.checkboxAboutAuthorization.isEnabled = isChecked
            switchFlag = setUpSwitchFlag(newsFlag, authorFlag, switchCheck)
        }
        // Слушаем поле ввода имени
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                nameFlag = text.isNotEmpty() && text.length < 41
            }
        })
        // Слушаем поле ввода телефона
        binding.editPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                phoneFlag = text.isNotEmpty()
            }
        })
        binding.radioGroup.setOnCheckedChangeListener {_, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)
            radioFlag = radioButton.isChecked
        }
        updateButtonState()
        binding.saveButton.setOnClickListener {
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
        }
    }

    /**
      Метод возвращает статус флага Switch
    */
    private fun setUpSwitchFlag(newsFlag: Boolean,
                          authorFlag: Boolean,
                          switchCheck: Boolean
                          ) : Boolean {
        return if(!switchCheck)
            true
        else if (authorFlag || newsFlag)
            true
        else
            false
    }

    private fun toStr(str: Int): String {
        return "$str/100"
    }
}