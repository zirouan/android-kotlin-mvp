package br.com.liveo.mvp.util.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.DatePicker
import br.com.liveo.mvp.base.BaseDialog
import br.com.liveo.mvp.util.DateUtil
import java.util.*

class DatePickerDialogFragment : BaseDialog(), DatePickerDialog.OnDateSetListener {

    companion object {

        private var listener: OnDateCallback? = null
        private var disableFutureDate: Boolean = false

        private var sCalendar: Calendar? = null

        val calendar: Calendar?
            get() {
                if (sCalendar == null) {
                    sCalendar = Calendar.getInstance()
                }

                return sCalendar
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var year = 0
        var month = 0
        var day = 0

        calendar?.let {
            year = it.get(Calendar.YEAR)
            month = it.get(Calendar.MONTH)
            day = it.get(Calendar.DAY_OF_MONTH)
        }

        val datePickerDialog = DatePickerDialog(activity, this,
                year, month, day)

        if (disableFutureDate) {
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        }

        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener?.let {
            sCalendar?.let {
                sCalendar?.set(year, month, day)
                listener?.onDate(DateUtil.formatDateToString(it.time))
            }
        }
    }

    interface OnDateCallback {
        fun onDate(dateFormatted: String)
    }

    class Builder {
        private var dateAsString: String? = null

        fun date(date: String): Builder {
            dateAsString = date
            return this
        }

        fun callback(OnDateCallback: OnDateCallback): Builder {
            listener = OnDateCallback
            return this
        }

        fun disableFutureDate(): Builder {
            disableFutureDate = true
            return this
        }

        fun create(): DatePickerDialogFragment {
            val fragment = DatePickerDialogFragment()

            if (!TextUtils.isEmpty(dateAsString)) {
                calendar?.time = DateUtil.date(dateAsString, DateUtil.DATE_FORMAT_LOCAL)
            }

            return fragment
        }
    }
}