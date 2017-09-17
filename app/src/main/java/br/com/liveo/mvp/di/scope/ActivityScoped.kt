package br.com.liveo.mvp.di.scope

import javax.inject.Scope

/**
 * This is a Dagger scope for identify each activity scope

 * Created by rudsonlima on 8/31/17.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped

