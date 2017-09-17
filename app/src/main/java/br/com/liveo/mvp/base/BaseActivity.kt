package br.com.liveo.mvp.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import br.com.liveo.mvp.R
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

/**
 * Created by rudsonlima on 8/29/17.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    protected fun bindView(layout: Int): ViewDataBinding =
            DataBindingUtil.setContentView(this, layout)

    fun onInitToolbar(toolBar: Toolbar) {
        onInitToolbar(toolBar, getString(R.string.clear), -1, false)
    }

    fun onInitToolbar(toolBar: Toolbar, title: String) {
        onInitToolbar(toolBar, title, -1, false)
    }

    fun onInitToolbar(toolBar: Toolbar, title: Int) {
        onInitToolbar(toolBar, title, -1, false)
    }

    fun onInitToolbar(toolBar: Toolbar, title: Int, icon: Int) {
        onInitToolbar(toolBar, getString(title), icon, true)
    }

    fun onInitToolbar(toolBar: Toolbar, title: String, displayHome: Boolean) {
        onInitToolbar(toolBar, title, -1, displayHome)
    }

    fun onInitToolbar(toolBar: Toolbar, title: Int, displayHome: Boolean) {
        onInitToolbar(toolBar, title, -1, displayHome)
    }

    fun onInitToolbar(toolBar: Toolbar, title: Int, icon: Int, displayHome: Boolean) {
        onInitToolbar(toolBar, getString(title), icon, displayHome)
    }

    fun onInitToolbar(toolBar: Toolbar?, title: String, icon: Int, displayHome: Boolean) {

        if (toolBar != null) {
            setSupportActionBar(toolBar)
            val actionBar = supportActionBar

            if (actionBar != null) {
                actionBar.title = title
                actionBar.setDisplayShowHomeEnabled(displayHome)
                actionBar.setDisplayHomeAsUpEnabled(displayHome)
                if (icon != -1) {
                    toolBar.setNavigationIcon(icon)
                }
            }
        }
    }

    fun showElevation(appBarLayout: AppBarLayout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.elevation = 10f
        }
    }

    fun removeEvelation(appBarLayout: AppBarLayout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.elevation = 0f
        }
    }

    fun onEventKeyboard(listener: KeyboardVisibilityEventListener?) {
        KeyboardVisibilityEvent.setEventListener(this
        ) { isOpen -> listener?.onVisibilityChanged(isOpen) }
    }

    fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    }
}
