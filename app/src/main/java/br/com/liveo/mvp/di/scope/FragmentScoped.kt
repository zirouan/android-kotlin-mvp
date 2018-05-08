package br.com.liveo.mvp.di.scope

import javax.inject.Scope

/**
 * This is a Dagger scope for identify each fragment scope
 *
 * Created by rudsonlima on 8/31/17.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScoped
