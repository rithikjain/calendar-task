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
         * Takes [month] and [year] as inputs to get the days with the week start offset
         * @return the formatted list containing the days in the correct order
         */
        fun getFormattedDaysInMonth(month: Int, year: Int): List<String> {
            val daysInMonthList = mutableListOf<String>()
            val cal = GregorianCalendar(year, month, 1)

            val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

            for (i in 1..42) {
                if (i < firstDayOfWeek || i >= firstDayOfWeek + daysInMonth) daysInMonthList.add("")
                else daysInMonthList.add("${i - firstDayOfWeek + 1}")
            }

            return daysInMonthList
        }

        /**
         * @return the current year
         */
        fun getCurrentYear(): Int {
            val cal = Calendar.getInstance()
            return cal.get(Calendar.YEAR)
        }

        /**
         * @return the current month, index starting from 0
         */
        fun getCurrentMonth(): Int {
            val cal = Calendar.getInstance()
            return cal.get(Calendar.MONTH)
        }

        /**
         * @return the current date, index starting from 1
         */
        fun getCurrentDay(): Int {
            val cal = Calendar.getInstance()
            return cal.get(Calendar.DAY_OF_MONTH)
        }

        /**
         * @return the current date with the offset of the startingDayOfTheWeek
         */
        fun getCurrentDateWithOffset(month: Int, year: Int): Int {
            val currCal = Calendar.getInstance()
            val currDate = currCal.get(Calendar.DATE)

            val cal = GregorianCalendar(year, month, 1)
            val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

            return currDate + firstDayOfWeek - 2
        }

        /**
         * @return the date after removing the offset of the startingDayOfTheWeek
         */
        fun getDateWithoutOffset(offsetDate: Int, month: Int, year: Int): Int {
            val cal = GregorianCalendar(year, month, 1)
            val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

            return offsetDate - firstDayOfWeek + 2
        }

        val months = listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
    }
}