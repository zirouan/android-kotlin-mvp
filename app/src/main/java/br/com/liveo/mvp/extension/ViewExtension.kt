package br.com.liveo.mvp.extension

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * Created by rudsonlima on 25/04/18.
 */
inline fun View.snack(messageResourceId: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit = {}) {
    this.snack(context.getString(messageResourceId), length, f)
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit = {}) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(actionLabelResourceId: Int, color: Int? = null, listener: (View) -> Unit) {
    val actionLabel = context.getString(actionLabelResourceId)
    setAction(actionLabel, listener)
    color?.let { setActionTextColor(it) }
}

fun Context.toastShort(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Context.toastShort(resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

fun Context.toastLong(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Context.toastLong(resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_LONG).show()

fun View.hideSoftKeyboard(context: Context) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.visible(isVisible: Boolean) {
    this.visibility = if (isVisible) VISIBLE else GONE
}