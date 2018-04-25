package br.com.liveo.mvp.base

import android.os.Bundle
import android.view.View

/**
 * Created by rudsonlima on 8/29/17.
 */
class BaseDialog : android.support.v7.app.AppCompatDialogFragment() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE,
                br.com.liveo.mvp.R.style.MyTheme_FloatingDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialog = dialog
        if (dialog != null) {
            dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
            val window = dialog.window
            if (window != null) {
                window.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

                window.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(
                        android.graphics.Color.TRANSPARENT))

                window.attributes.windowAnimations = br.com.liveo.mvp.R.style.DialogAnimation
            }
        }
    }
}
