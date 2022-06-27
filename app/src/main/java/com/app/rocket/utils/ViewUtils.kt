package com.app.rocket.utils

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * [visible] if condition is true, [gone] otherwise.
 */
fun View?.visibleIfTrue(condition: Boolean) {
    when {
        condition -> visible()
        else -> gone()
    }
}

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun visibleIfTrue(condition: Boolean): Int {
    return if (condition) View.VISIBLE else View.GONE
}

fun showToast(context: Context, toastMsg: String) = Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
