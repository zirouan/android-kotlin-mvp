package br.com.liveo.mvp.base

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.View

/**
 * Created by rudsonlima on 8/29/17.
 */
open class BaseDialog : AppCompatDialogFragment() {

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
            }
        }
    }

    override fun onStart() {
        super.onStart()
        showAnimation()
    }

    private fun showAnimation() {
        if (dialog != null && dialog.window != null) {
            val decorView = dialog.window!!.decorView

            val scaleDown = ObjectAnimator.ofPropertyValuesHolder(decorView,
                    PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
                    PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f),
                    PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f))
            scaleDown.duration = 300
            scaleDown.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideAnimation()
    }

    protected fun hideAnimation() {
        if (activity != null) {
        }

        if (dialog != null && dialog.window != null) {

            val decorView = dialog
                    .window!!
                    .decorView

            val scaleDown = ObjectAnimator.ofPropertyValuesHolder(decorView,
                    PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f),
                    PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f),
                    PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f))
            scaleDown.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator) {
                    if (activity != null) {
                        dismiss()
                    }
                }

                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
            scaleDown.duration = 300
            scaleDown.start()
        }
    }
}
