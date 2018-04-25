package br.com.liveo.mvp.util.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.app.FragmentManager
import android.os.Bundle
import android.widget.DatePicker
import java.util.*

/**
 * Created by rudsonlima on 23/03/18.
 */
class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var componentTag: String? = null
    var onDateListener: OnDateListener? = null
    var selectedDate: Date? = null

    var minimumDate: Date? = null
    var maximumDate: Date? = null

    var openInYearPicker: Boolean = false

    interface OnDateListener {
        fun onDateListener(date: Date, tag: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        selectedDate?.let {
            calendar.timeInMillis = it.time
        }

        val year = calendar.get(Calendar.YEAR)
        val monthOfYear = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(activity, this, year, monthOfYear, dayOfMonth)

        val datePicker = datePickerDialog.datePicker

        minimumDate?.let {
            datePicker.minDate = it.time
        }

        maximumDate?.let {
            datePicker.maxDate = it.time
        }

        if (openInYearPicker) {
            openYearPicker(datePicker)
        }

        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)

        onDateListener?.onDateListener(calendar.time, componentTag.orEmpty())
    }

    fun disableFutureDate() {
        if (this.dialog != null) {
            (this.dialog as DatePickerDialog).datePicker.maxDate = System.currentTimeMillis()
        }
    }

    override fun show(manager: FragmentManager, tag: String) {
        super.show(manager, tag)
        componentTag = tag
    }

    private fun openYearPicker(datePicker: DatePicker) {
        datePicker.touchables?.get(0)?.performClick()
    }
}