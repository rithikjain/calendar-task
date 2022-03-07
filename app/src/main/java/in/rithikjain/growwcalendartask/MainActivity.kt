package `in`.rithikjain.growwcalendartask

import `in`.rithikjain.calendar.Calendar
import `in`.rithikjain.growwcalendartask.adapters.YearAdapter
import `in`.rithikjain.growwcalendartask.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Changing the action bar title
        title = "Groww Calendar"

        val years = (1600..2600).toList()
        val currentYear = Calendar.getCurrentYear()
        val currentYearOffset = currentYear - years[0]
        val yearAdapter = YearAdapter(years, currentYearOffset, this) { newYear ->
            onYearChanged(newYear)
        }

        binding.yearRecyclerView.apply {
            adapter = yearAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }

        binding.yearRecyclerView.layoutManager?.scrollToPosition(currentYearOffset - 2)
    }

    private fun onYearChanged(year: Int) {
        Log.d("BRR", year.toString())
    }
}