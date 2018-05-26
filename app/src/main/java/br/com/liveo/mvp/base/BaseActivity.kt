package br.com.liveo.mvp.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
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

    private var toolBarIcon: Int = -1
    private var toolBar: Toolbar? = null
    private var displayHome: Boolean = true
    private var toolBarTitle: Int = R.string.clear

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    protected fun bindView(layout: Int): ViewDataBinding =
            DataBindingUtil.setContentView(this, layout)

    //region Methods toolbar/appBar
    fun toolbar(toolBar: Toolbar?): BaseActivity {
        this.toolBar = toolBar
        return this
    }

    fun title(title: Int): BaseActivity {
        this.toolBarTitle = title
        return this
    }

    fun icon(icon: Int): BaseActivity {
        this.toolBarIcon = icon
        return this
    }

    fun displayHome(displayHome: Boolean): BaseActivity {
        this.displayHome = displayHome
        return this
    }

    fun builder(){
        toolBar?.let { toolbar ->
            setSupportActionBar(toolBar)

            supportActionBar?.let { actionBar ->
                if (toolBarTitle != -1) {
                    actionBar.title = getString(toolBarTitle)
                }

                actionBar.setDisplayShowHomeEnabled(displayHome)
                actionBar.setDisplayHomeAsUpEnabled(displayHome)
                if (toolBarIcon != -1 && displayHome) {
                    toolbar.navigationIcon = ContextCompat.getDrawable(this, toolBarIcon)
                }
            }
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
