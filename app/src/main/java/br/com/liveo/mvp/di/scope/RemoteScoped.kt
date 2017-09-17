package br.com.liveo.mvp.di.scope

import javax.inject.Qualifier

/**
 * This is a Dagger scope to identify RemoteScoped repository
 *
 * Created by rudsonlima on 8/31/17.
 */

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteScoped
