package `in`.rithikjain.growwcalendartask.ui

import `in`.rithikjain.calendar.Calendar
import `in`.rithikjain.growwcalendartask.data.CustomDate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _dateLiveData = MutableLiveData(
        CustomDate(
            Calendar.getCurrentDay(),
            Calendar.getCurrentMonth(),
            Calendar.getCurrentYear()
        )
    )
    val dateLiveData get() = _dateLiveData

    fun updateYear(year: Int) {
        _dateLiveData.value?.year = year
        _dateLiveData.postValue(_dateLiveData.value)
    }

    fun updateMonth(month: Int) {
        _dateLiveData.value?.month = month
        _dateLiveData.postValue(_dateLiveData.value)
    }

    fun updateDateWithOffset(dateWithOffset: Int) {
        val date = getDateWithoutOffset(dateWithOffset)
        _dateLiveData.value?.date = date
        _dateLiveData.postValue(_dateLiveData.value)
    }

    fun getFormattedDaysInSelectedMonth(): List<String> {
        return Calendar.getFormattedDaysInMonth(
            _dateLiveData.value!!.month,
            _dateLiveData.value!!.year
        )
    }

    fun getSelectedDateWithOffset(): Int {
        return Calendar.getDateWithOffset(
            _dateLiveData.value!!.date,
            _dateLiveData.value!!.month,
            _dateLiveData.value!!.year
        )
    }

    private fun getDateWithoutOffset(dateWithOffset: Int): Int {
        return Calendar.getDateWithoutOffset(
            dateWithOffset,
            _dateLiveData.value!!.month,
            _dateLiveData.value!!.year
        )
    }

    fun getReadableDate(date: CustomDate): String {
        return Calendar.getStringFormattedDate(date.date, date.month, date.year)
    }

    fun getWeekInfoString(date: CustomDate): String {
        val weekOfYear =
            Calendar.getWeekOfYear(date.date, date.month, date.year).toString()
        val weekOfMonth =
            Calendar.getWeekOfMonth(date.date, date.month, date.year).toString()
        val weekRange = Calendar.getWeekRange(date.date, date.month, date.year)

        return "$weekRange\n$weekOfYear weeks past the year start\n$weekOfMonth weeks past the month start"
    }

    fun resetDateToToday() {
        _dateLiveData.value = CustomDate(
            Calendar.getCurrentDay(),
            Calendar.getCurrentMonth(),
            Calendar.getCurrentYear()
        )
    }
}