package `in`.rithikjain.growwcalendartask

import `in`.rithikjain.calendar.Calendar
import `in`.rithikjain.growwcalendartask.adapters.CalendarAdapter
import `in`.rithikjain.growwcalendartask.adapters.MonthAdapter
import `in`.rithikjain.growwcalendartask.adapters.YearAdapter
import `in`.rithikjain.growwcalendartask.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var selectedYear = Calendar.getCurrentYear()
    private var selectedMonth = Calendar.getCurrentMonth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Changing the action bar title
        title = "Groww Calendar"

        setUpYearView()
        setUpMonthView()
        setUpCalendarView()
    }

    private fun setUpYearView() {
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
    }

    private fun setUpMonthView() {
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

    private fun setUpCalendarView() {
        val days = Calendar.getFormattedDaysInMonth(selectedMonth, selectedYear)
        val currDateWithOffset = Calendar.getCurrentDateWithOffset(selectedMonth, selectedYear)

        val calendarAdapter = CalendarAdapter(days, currDateWithOffset, this) {

        }

        binding.calendarRecyclerView.apply {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 7)
            setHasFixedSize(true)
        }
    }

    private fun onYearChanged(year: Int) {
        selectedYear = year
        setUpCalendarView()
    }

    private fun onMonthChanged(month: Int) {
        selectedMonth = month
        setUpCalendarView()
    }
}