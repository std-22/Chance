package io.github.studio22.probably.extensions

import android.text.Editable
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

class Extension {
    fun EditText.validate(message: String, validator: (String) -> Boolean) {
        this.doAfterTextChanged {
            this.error = if (validator(it.toString())) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }

    private fun String.isNotEmpty(): Boolean {
        return this.isNotEmpty()
    }
}