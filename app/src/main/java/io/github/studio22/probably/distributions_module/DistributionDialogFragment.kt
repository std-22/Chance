package io.github.studio22.probably.distributions_module

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import io.github.studio22.probably.R

class DistributionDialogFragment(private var distributionType: String) : DialogFragment() {
    private lateinit var listener: DialogListener
    private lateinit var builder: AlertDialog.Builder
    private var eventQuantity = 0
    private var eventCharacteristic = 0.0

    interface DialogListener {
        fun onDialogNegativeClick(dialog: DialogFragment)
        fun onDialogPositiveClick(eventQuantity: Int = 0,
                                  eventProbability: Double = 0.0,
                                  lambda: Double = 0.0)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement NoticeDialogListener")
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            builder = AlertDialog.Builder(it)
            createDialog()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun createDialog(): Dialog {
        val inflater = requireActivity().layoutInflater
        builder
            .setTitle("Ввод данных")
            .setNegativeButton("CANCEL") { _, _ ->
                listener.onDialogNegativeClick(this)
            }
        return when (distributionType) {
            resources.getString(R.string.binomial) -> {
                builder
                    .setView(inflater.inflate(R.layout.dialog, null))
                    .setPositiveButton("OK") { _, _ ->
                        eventQuantity = dialog?.findViewById<EditText>(R.id.input_event_number)
                            ?.text.toString().toInt()
                        eventCharacteristic =
                            dialog?.findViewById<EditText>(R.id.input_event_probability)
                                ?.text.toString().toDouble()
                        listener.onDialogPositiveClick(eventQuantity, eventCharacteristic)
                    }
                    .create()
            }
            resources.getString(R.string.poisson) -> {
                val inputLambda = EditText(context)
                builder
                    .setMessage("Введите lambda")
                    .setView(inputLambda)
                    .setPositiveButton("OK") { _, _ ->
                        eventCharacteristic = inputLambda.text.toString().toDouble()
                        listener.onDialogPositiveClick(lambda = eventCharacteristic)
                    }
                    .create()
            }
            resources.getString(R.string.geometric) -> {
                val inputProbability = EditText(context)
                builder
                    .setMessage("Введите вероятность события")
                    .setView(inputProbability)
                    .setPositiveButton("OK") {_, _ ->
                        eventCharacteristic = inputProbability.text.toString().toDouble()
                        listener.onDialogPositiveClick(eventProbability = eventCharacteristic)
                    }
                    .create()
            }
            resources.getString(R.string.uniform) -> {
                val inputStart = EditText(context)
                val inputEnd = EditText(context)
                builder
                    .setView(inputStart)
                    .setView(inputEnd)
                    .setPositiveButton("OK") {_, _ ->

                    }
                    .create()
            }
            else -> {
                throw IllegalStateException("Activity cannot be null")
            }
        }
    }
}