package br.com.liveo.mvp.base

/**
 * Created by rudsonlima on 10/10/17.
 */

abstract class BaseException : Exception {
    var code = DEFAULT_CODE

    companion object {
        val DEFAULT_CODE = -1
    }

    abstract val stringRes: Int
    constructor(message: String) : super(message) {}

    constructor() {
    }
}
