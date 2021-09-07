package io.github.studio22.probably.extensions

import android.view.View
import android.widget.TextView
import io.github.studio22.probably.R
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.round(): Double {
    return BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN).toDouble()
}

fun TextView.setStyle() {
    this.textSize = 36F
    this.textAlignment = View.TEXT_ALIGNMENT_CENTER
    this.setTextColor(resources.getColor(R.color.baltic_sea))
}
