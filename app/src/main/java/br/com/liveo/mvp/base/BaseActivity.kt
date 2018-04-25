package br.com.liveo.mvp.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import br.com.liveo.mvp.R

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

    //region Methods toolbar/appBar
    fun onInitToolbar(toolBar: Toolbar?) {
        onInitToolbar(toolBar, R.string.clear, -1, false)
    }

    fun onInitToolbar(toolBar: Toolbar?, title: Int) {
        onInitToolbar(toolBar, title, -1, false)
    }

    fun onInitToolbar(toolBar: Toolbar?, title: Int, displayHome: Boolean) {
        onInitToolbar(toolBar, title, -1, displayHome)
    }

    fun onInitToolbar(toolBar: Toolbar?, title: Int, icon: Int,
                      displayHome: Boolean = true) {

        toolBar?.let { toolbar ->
            setSupportActionBar(toolBar)

            supportActionBar?.let { actionBar ->
                if (title != -1) {
                    actionBar.title = getString(title)
                }

                actionBar.setDisplayShowHomeEnabled(displayHome)
                actionBar.setDisplayHomeAsUpEnabled(displayHome)
                if (icon != -1 && displayHome) {
                    toolbar.navigationIcon = ContextCompat.getDrawable(this, icon)
                }
            }
        }
    }

    fun showElevation(appBarLayout: AppBarLayout?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout?.elevation = 10f
        }
    }

    fun removeEvelation(appBarLayout: AppBarLayout?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout?.elevation = 0f
        }
    }

    fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    }

    //endregion

    //region Methods Abstract
    abstract fun onInitView()
    abstract fun onInitInject()
    abstract fun finishActivity()
    //endregion

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishActivity()
    }
}
