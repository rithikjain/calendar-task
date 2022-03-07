package `in`.rithikjain.growwcalendartask

import `in`.rithikjain.calendar.Calendar
import `in`.rithikjain.growwcalendartask.adapters.MonthAdapter
import `in`.rithikjain.growwcalendartask.adapters.YearAdapter
import `in`.rithikjain.growwcalendartask.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Changing the action bar title
        title = "Groww Calendar"

        // Handling the year recycler view
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
            layoutManager?.scrollToPosition(currentYearOffset - 2)
        }

        // Handling the month recycler view
        val currentMonth = Calendar.getCurrentMonth()
        val monthAdapter = MonthAdapter(Calendar.months, currentMonth, this) { newMonth ->
            onMonthChanged(newMonth)
        }

        binding.monthRecyclerView.apply {
            adapter = monthAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            layoutManager?.scrollToPosition(if (currentMonth != 0) currentMonth - 1 else currentMonth)
        }
    }

    private fun onYearChanged(year: Int) {
        Log.d("BRR", year.toString())
    }

    private fun onMonthChanged(month: Int) {
        Log.d("BRR", month.toString())
    }
}