package com.android.sekarya_mobile_app.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.android.sekarya_mobile_app.databinding.ActivityInfoPersonalBinding
import com.android.sekarya_mobile_app.utils.DateConverter
import com.android.sekarya_mobile_app.utils.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class InfoPersonalActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener {

    private var dueDateMillis: Long = System.currentTimeMillis()
    private var selectedGender: String? = null

    private lateinit var binding: ActivityInfoPersonalBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)




        back()
        spinnerGender()
        skip()
        next()

    }


//    private fun spinnerGender(){
//        //        value spinner
//        val gender = arrayOf("Male","Female")
////        adapter untuk spinner
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gender)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        val spinner: Spinner = binding.spGender
//        spinner.adapter = adapter
//    }

    private fun spinnerGender() {
        // Nilai spinner
        val gender = arrayOf("Male", "Female")

        // Adapter untuk spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gender)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = binding.spGender
        spinner.adapter = adapter

        // Menambahkan listener untuk spinner
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Dapatkan nilai yang dipilih dari array berdasarkan posisi
                 selectedGender = gender[position]

                // Lakukan sesuatu dengan nilai yang dipilih

                Log.d("Spinner", "Selected Gender: $selectedGender")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tidak ada item yang dipilih
            }
        })
    }

    private fun back(){

        binding.ivBack.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun skip(){
        binding.btnSkip.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.tvDateFormat.text = dateFormat.format(calendar.time)

        dueDateMillis = calendar.timeInMillis
    }

    private fun next(){
        binding.btnContinue.setOnClickListener(){
            val fullName = binding.edFullName.text.toString()
            val phoneNumber = binding.edPhone.text.toString()
            val dateOfBirth = DateConverter.convertMillisToString(dueDateMillis)
            val gender = selectedGender
            val email =intent.getStringExtra("email")
            val password =intent.getStringExtra("password")
            val intent = Intent(this, PublicProfileActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("fullName", fullName)
            intent.putExtra("phoneNumber", phoneNumber)
            intent.putExtra("dateOfBirth",dateOfBirth)
            intent.putExtra("gender",gender)
            startActivity(intent)
        }

    }

}