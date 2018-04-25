package br.com.liveo.mvp.util.view

import android.app.Dialog
import android.app.DialogFragment
import android.app.FragmentManager
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import java.util.*

/**
 * Created by rudsonlima on 25/04/18.
 */
class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private var componentTag: String? = null
    var onTimeListener: OnTimeListener? = null
    var selectedDate: Date? = null

    interface OnTimeListener {
        fun onTimeListener(hour: Int, minute: Int, tag: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        selectedDate?.let {
            calendar.timeInMillis = it.time
        }

        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val is24HView = true

        return TimePickerDialog(activity, this, hourOfDay, minute, is24HView)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        onTimeListener?.onTimeListener(hourOfDay, minute, componentTag.orEmpty())
    }

    override fun show(manager: FragmentManager, tag: String) {
        super.show(manager, tag)
        componentTag = tag
    }
}