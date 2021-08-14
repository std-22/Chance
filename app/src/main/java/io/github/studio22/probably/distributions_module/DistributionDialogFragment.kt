package io.github.studio22.probably.distributions_module

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import io.github.studio22.probably.R

class DistributionDialogFragment : DialogFragment() {
    private lateinit var listener: DialogListener
    private lateinit var builder: AlertDialog.Builder
    private var eventQuantity = 0
    private var eventCharacteristic = 0.0

    interface DialogListener {
        fun onDialogNegativeClick(dialog: DialogFragment)
        fun onDialogPositiveClick(eventQuantity: Int, eventProbability: Double)
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
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.dialog, null))
                .setTitle("Ввод данных")
                .setPositiveButton("OK") { _, _ ->
                    eventQuantity = dialog?.findViewById<EditText>(R.id.input_event_number)
                        ?.text.toString().toInt()
                    eventCharacteristic =
                        dialog?.findViewById<EditText>(R.id.input_event_probability)
                            ?.text.toString().toDouble()
                    listener.onDialogPositiveClick(eventQuantity, eventCharacteristic)
                }
                .setNegativeButton("CANCEL") { _, _ ->
                    listener.onDialogNegativeClick(this)
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    
}