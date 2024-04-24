package com.example.hw4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hw4.databinding.ActivityMainBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameInputLayout: TextInputLayout = findViewById(R.id.name_input_layout)
        nameInputLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT

        val progressNum = (0..100).random().toInt()
        val progressBar = findViewById<LinearProgressIndicator>(R.id.progress_bar)
        val switch = findViewById<Switch>(R.id.receive_notifications_switch)
//        val checkNews = findViewById<MaterialCheckBox>(R.id.checkbox_about_news)
//        val checkAuthorization = findViewById<MaterialCheckBox>(R.id.checkbox_about_authorization)

        binding.progressPoints.text = toStr(progressNum)
        progressBar.progress = progressNum

        switch.setOnCheckedChangeListener { _,
                                            isChecked ->
            binding.checkboxAboutNews.isEnabled = isChecked
            binding.checkboxAboutAuthorization.isEnabled = isChecked

        }
    }


    private fun toStr(str: Int): String {
        return "$str/100"
    }


}