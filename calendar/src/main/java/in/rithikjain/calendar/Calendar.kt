package `in`.rithikjain.calendar

import java.text.SimpleDateFormat
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
         * @param month starting from index 0
         * @param year
         * Takes [month] and [year] as inputs to calculate number of days
         * @return the list containing the days
         */
        fun getNumOfDaysInMonth(month: Int, year: Int): List<Int> {
            val cal = GregorianCalendar(year, month, 1)
            val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

            return (1..daysInMonth).toList()
        }

        /**
         * @param month starting from index 0
         * @param year
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
         * @param date day of the month
         * @param month starting from index 0
         * @param year
         * @return the date with the offset of the startingDayOfTheWeek
         */
        fun getDateWithOffset(date: Int, month: Int, year: Int): Int {
            val cal = GregorianCalendar(year, month, 1)
            val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

            return date + firstDayOfWeek - 2
        }

        /**
         * @param offsetDate date containing the offset due to the startingDayOfTheWeek
         * @param month starting from index 0
         * @param year
         * @return the date after removing the offset of the startingDayOfTheWeek
         */
        fun getDateWithoutOffset(offsetDate: Int, month: Int, year: Int): Int {
            val cal = GregorianCalendar(year, month, 1)
            val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

            return offsetDate - firstDayOfWeek + 2
        }


        /**
         * @param date day of the month
         * @param month starting from index 0
         * @param year
         * @return human readable date string
         */
        fun getStringFormattedDate(date: Int, month: Int, year: Int): String {
            val inFormatter = SimpleDateFormat("d M y", Locale.US)
            val inDate = inFormatter.parse("$date ${month + 1} $year")

            val outFormatter = SimpleDateFormat("EEEE, dd MMMM, y", Locale.US)
            return outFormatter.format(inDate)
        }


        /**
         * @param date day of the month
         * @param month starting from index 0
         * @param year
         * @return the number of weeks elapsed from the start of the year
         */
        fun getWeekOfYear(date: Int, month: Int, year: Int): Int {
            val cal = GregorianCalendar(year, month, date)
            return cal.get(Calendar.WEEK_OF_YEAR)
        }


        /**
         * @param date day of the month
         * @param month starting from index 0
         * @param year
         * @return the number of weeks elapsed from the start of the month
         */
        fun getWeekOfMonth(date: Int, month: Int, year: Int): Int {
            val cal = GregorianCalendar(year, month, date)
            return cal.get(Calendar.WEEK_OF_MONTH)
        }

        /**
         * @param date day of the month
         * @param month starting from index 0
         * @param year
         * @return the week range in which the particular given date is present in
         */
        fun getWeekRange(date: Int, month: Int, year: Int): String {
            val cal = GregorianCalendar(year, month, date)
            val offset = cal.get(Calendar.DAY_OF_WEEK) - cal.firstDayOfWeek

            cal.add(Calendar.DATE, -offset)
            val start = cal.time

            cal.add(Calendar.DATE, 6)
            val end = cal.time

            val formatter = SimpleDateFormat("dd/MM/yy", Locale.US)

            return "${formatter.format(start)} - ${formatter.format(end)}"
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