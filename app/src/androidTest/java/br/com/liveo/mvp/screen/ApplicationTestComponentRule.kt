package br.com.liveo.mvp.screen

import br.com.liveo.mvp.App
import br.com.liveo.mvp.di.components.DaggerApplicationTestComponent
import br.com.liveo.mvp.di.modules.HelperTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ApplicationTestComponentRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        val helperTestModule = HelperTestModule()

        module(helperTestModule)

        return base
    }

    companion object {

        internal fun module(helperTestModule: HelperTestModule?) {

            val baseComponent = DaggerApplicationTestComponent.builder()
                    .helperTestModule(helperTestModule)
                    .build()

            App.application = baseComponent
        }
    }
}
