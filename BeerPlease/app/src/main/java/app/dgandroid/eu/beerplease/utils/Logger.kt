package app.dgandroid.eu.beerplease.utils

import android.util.Log

/**
 * Created by Duilio on 22/05/2017.
 */

object Logger {
    private val tag: String
        get() {
            val className = Exception().stackTrace[2].className
            return className.substring(1 + className.lastIndexOf('.'))
        }

    fun i(msg: String) {
        if (Config.DEBUG) {
            Log.println(Log.INFO, tag, msg)
        }
    }

    fun e(msg: String) {
        if (Config.DEBUG) {
            Log.println(Log.ERROR, tag, msg)
        }
    }
}