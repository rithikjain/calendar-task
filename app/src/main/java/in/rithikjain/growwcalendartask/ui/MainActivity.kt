package `in`.rithikjain.growwcalendartask.ui

import `in`.rithikjain.calendar.Calendar
import `in`.rithikjain.growwcalendartask.R
import `in`.rithikjain.growwcalendartask.adapters.CalendarAdapter
import `in`.rithikjain.growwcalendartask.adapters.MonthAdapter
import `in`.rithikjain.growwcalendartask.adapters.YearAdapter
import `in`.rithikjain.growwcalendartask.data.CustomDate
import `in`.rithikjain.growwcalendartask.databinding.ActivityMainBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Changing the action bar title
        title = "Groww Calendar"

        initViews()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_reset_date) {
            resetDate()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        setUpYearView()
        setUpMonthView()
        setUpCalendarView()
    }

    private fun initObservers() {
        viewModel.dateLiveData.observe(this) {
            setUpSelectedDateView(it)
            setUpWeekInfoView(it)
        }
    }

    private fun setUpYearView() {
        // Handling the year recycler view
        val years = (1600..2600).toList()
        val initialYear = viewModel.dateLiveData.value!!.year
        val initialYearOffset = initialYear - years[0]
        val yearAdapter = YearAdapter(years, initialYearOffset, this) { newYear ->
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
            layoutManager?.scrollToPosition(initialYearOffset - 2)
        }
    }

    private fun setUpMonthView() {
        // Handling the month recycler view
        val initialMonth = viewModel.dateLiveData.value!!.month
        val monthAdapter = MonthAdapter(Calendar.months, initialMonth, this) { newMonth ->
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
            layoutManager?.scrollToPosition(if (initialMonth != 0) initialMonth - 1 else initialMonth)
        }
    }

    private fun setUpCalendarView() {
        val days = viewModel.getFormattedDaysInSelectedMonth()
        val dateWithOffset = viewModel.getSelectedDateWithOffset()

        val calendarAdapter = CalendarAdapter(days, dateWithOffset, this) { newDateWithOffset ->
            onDateChanged(newDateWithOffset)
        }

        binding.calendarRecyclerView.apply {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 7)
            setHasFixedSize(true)
        }
    }

    private fun setUpSelectedDateView(date: CustomDate) {
        binding.selectedDateTextView.text = viewModel.getReadableDate(date)
    }

    private fun setUpWeekInfoView(date: CustomDate) {
        binding.weekInfoTextView.text = viewModel.getWeekInfoString(date)
    }

    private fun resetDate() {
        viewModel.resetDateToToday()
        initViews()
    }

    private fun onYearChanged(year: Int) {
        viewModel.updateYear(year)
        setUpCalendarView()
    }

    private fun onMonthChanged(month: Int) {
        viewModel.updateMonth(month)
        setUpCalendarView()
    }

    private fun onDateChanged(dateWithOffset: Int) {
        viewModel.updateDateWithOffset(dateWithOffset)
    }
}