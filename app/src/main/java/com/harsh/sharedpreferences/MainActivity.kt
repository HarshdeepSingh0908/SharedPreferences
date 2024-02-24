package com.harsh.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.sharedpreferences.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "com.harsh.sharedpreferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // Retrieve the saved item count and background colors from SharedPreferences
        val itemCount = sharedPreferences.getInt("itemCount", 0)
        val backgroundColors = getBackgroundColors(sharedPreferences, itemCount)
        binding.edtEnterCount.setText(itemCount.toString())

        val name: MutableList<String> = mutableListOf()
        repeat(itemCount) {
            name.add("Harsh")
        }

        binding.rvRecyclerView.adapter = MyAdapter(name, backgroundColors.toMutableList())
        binding.rvRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnSave.setOnClickListener {
            // Save the entered number to SharedPreferences
            val enteredCount = binding.edtEnterCount.text.toString().toIntOrNull() ?: 0
            with(sharedPreferences.edit()) {
                putInt("itemCount", enteredCount)
                apply()
            }

            // Update RecyclerView with the new data
            val updatedNameList: MutableList<String> = mutableListOf()
            repeat(enteredCount) {
                updatedNameList.add("Harsh")
            }
            (binding.rvRecyclerView.adapter as MyAdapter).updateData(updatedNameList)
        }
        binding.btnRed.setOnClickListener {
            // Change background color of all RecyclerView items to red
            val redColor = Color.RED
            val backgroundColors = IntArray(itemCount) { redColor }
            (binding.rvRecyclerView.adapter as MyAdapter).updateBackgroundColors(backgroundColors)
            // Save the background colors to SharedPreferences
            saveBackgroundColors(sharedPreferences, backgroundColors)
        }
        binding.btnGreen.setOnClickListener {
            // Change background color of all RecyclerView items to green
            val greenColor = Color.GREEN
            val backgroundColors = IntArray(itemCount) { greenColor }
            (binding.rvRecyclerView.adapter as MyAdapter).updateBackgroundColors(backgroundColors)
            // Save the background colors to SharedPreferences
            saveBackgroundColors(sharedPreferences, backgroundColors)
        }

        binding.btnBlue.setOnClickListener {
            // Change background color of all RecyclerView items to blue
            val blueColor = Color.BLUE
            val backgroundColors = IntArray(itemCount) { blueColor }
            (binding.rvRecyclerView.adapter as MyAdapter).updateBackgroundColors(backgroundColors)
            // Save the background colors to SharedPreferences
            saveBackgroundColors(sharedPreferences, backgroundColors)
        }
        binding.btnClear.setOnClickListener {
            // Clear all saved preferences
            sharedPreferences.edit().clear().apply()
            // Reset EditText and RecyclerView
            binding.edtEnterCount.setText("")
            (binding.rvRecyclerView.adapter as MyAdapter).updateData(emptyList())
        }

    }

    private fun saveBackgroundColors(sharedPreferences: SharedPreferences, colors: IntArray) {
        with(sharedPreferences.edit()) {
            putString("backgroundColors", colors.joinToString(","))
            apply()
        }
    }

    private fun getBackgroundColors(sharedPreferences: SharedPreferences, itemCount: Int): IntArray {
        val colorsString = sharedPreferences.getString("backgroundColors", null)
        return colorsString?.split(",")?.map { it.toInt() }?.toIntArray() ?: IntArray(itemCount) { Color.WHITE }
    }
}
