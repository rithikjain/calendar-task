package `in`.rithikjain.calendar

import java.util.*
import java.util.Calendar

class Calendar {

    companion object {
        /**
         * Calculates the number of days in the current month
         * @return the list containing the days
         */
        fun getNumOfDaysInTheCurrentMonth(): List<Int> {
            val cal = Calendar.getInstance()
            val currYear = cal.get(Calendar.YEAR)
            val currMonth = cal.get(Calendar.MONTH)

            return getNumOfDaysInMonth(currMonth, currYear)
        }

        /**
         * Takes [month] and [year] as inputs to calculate number of days
         * @return the list containing the days
         */
        fun getNumOfDaysInMonth(month: Int, year: Int): List<Int> {
            val cal = GregorianCalendar(year, month, 1)
            val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

            return (1..daysInMonth).toList()
        }

        /**
         * @return the current year
         */
        fun getCurrentYear(): Int {
            val cal = Calendar.getInstance()
            return cal.get(Calendar.YEAR)
        }
    }
}