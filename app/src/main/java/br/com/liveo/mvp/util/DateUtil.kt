package br.com.liveo.mvp.util

import android.content.Context
import br.com.liveo.mvp.R
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by rudsonlima on 8/29/17.
 */
object DateUtil {
    const val DATE_FORMAT_LOCAL = "dd/MM/yyyy"
    const val API_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    private val apiDateFormat = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
    private val shortDateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
    private val shortTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val birthdayFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val fullDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    private val weekFullDateFormat = SimpleDateFormat("EEE, d 'de' MMM yyyy", Locale.getDefault())

    fun date(dateAsString: String?, pattern: String): Date? {

        dateAsString?.let {
            try {
                val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
                return simpleDateFormat.parse(it)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun formatDateToString(date: Date): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
    }

    fun formatShortDate(dateAsString: String): String {
        try {
            val date = apiDateFormat.parse(dateAsString)
            return formatShortDate(date)
        } catch (e: Exception) {
            Timber.e(e, "Failed to parse string as date")
        }

        return dateAsString
    }

    fun formatShortTime(dateAsString: String): String {
        try {
            val date = apiDateFormat.parse(dateAsString)
            return formatShortTime(date)
        } catch (e: Exception) {
            Timber.e(e, "Failed to parse string as time")
        }

        return dateAsString
    }

    fun formatDateToApi(date: Date?): String {
        // TODO Do not accept optional date
        if (date == null) return ""
        return apiDateFormat.format(date)
    }

    fun getDateFromApi(dateAsString: String): Date {
        try {
            return apiDateFormat.parse(dateAsString)
        } catch (e: Exception) {
            Timber.e(e, "Failed to parse string as time")
        }

        return Date()
    }

    fun formatShortTime(date: Date): String {
        return shortTimeFormat.format(date)
    }

    fun formatShortDate(date: Date): String {
        return shortDateFormat.format(date)
    }

    fun formatWeekFullDate(date: Date): String {
        return weekFullDateFormat.format(date)
    }

    fun formatBirthday(date: Date): String {
        return birthdayFormat.format(date)
    }

    fun formatDateInFull(context: Context, date: Date): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.time = date

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val dayOfWeekString = when (dayOfWeek) {
            Calendar.SUNDAY -> "Dom"
            Calendar.MONDAY -> "Seg"
            Calendar.TUESDAY -> "Ter"
            Calendar.WEDNESDAY -> "Qua"
            Calendar.THURSDAY -> "Qui"
            Calendar.FRIDAY -> "Sex"
            Calendar.SATURDAY -> "Sáb"
            else -> ""
        }

        val monthString = when (month) {
            Calendar.JANUARY -> "jan"
            Calendar.FEBRUARY -> "fev"
            Calendar.MARCH -> "mar"
            Calendar.APRIL -> "abr"
            Calendar.MAY -> "mai"
            Calendar.JUNE -> "jun"
            Calendar.JULY -> "jul"
            Calendar.AUGUST -> "ago"
            Calendar.SEPTEMBER -> "set"
            Calendar.OCTOBER -> "out"
            Calendar.NOVEMBER -> "nov"
            Calendar.DECEMBER -> "dez"
            else -> ""
        }

        return context.getString(R.string.date_in_full, dayOfWeekString, day, monthString, year)
    }

    fun getMonthInFull(context: Context, month: Int): String {
        val months = context.resources.getStringArray(R.array.date_month_in_full)

        return if (months.size > month) months[month] else ""
    }

    fun addDaysToDate(date: Date, days: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date

        calendar.add(Calendar.DAY_OF_YEAR, days)

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        return Date(calendar.timeInMillis)
    }

    fun subtractYearsFromDate(date: Date, years: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date

        calendar.add(Calendar.YEAR, -years)

        return Date(calendar.timeInMillis)
    }

    /**
     * From 0 to 6, monday to sunday
     */
    fun getDayOfWeek(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date

        return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7
    }

    fun getHour(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date

        return calendar.get(Calendar.HOUR_OF_DAY)
    }

    fun getMinute(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date

        return calendar.get(Calendar.MINUTE)
    }

    fun isTimeInPeriod(chosenHour: Int, chosenMinute: Int,
                       startHour: Int, startMinute: Int,
                       endHour: Int, endMinute: Int): Boolean {
        if (((chosenHour > startHour) ||
                        (chosenHour == startHour && chosenMinute >= startMinute)) &&
                ((chosenHour < endHour) ||
                        (chosenHour == endHour && chosenMinute <= endMinute))) {
            return true
        }

        return false
    }
}