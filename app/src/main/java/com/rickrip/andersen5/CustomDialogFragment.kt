package com.rickrip.andersen5

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {

    private lateinit var positiveButtonOnClickListener: PositiveButtonOnClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // parentFragment == AFragment
        positiveButtonOnClickListener = parentFragment as PositiveButtonOnClickListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = AlertDialog.Builder(requireContext())
        .setTitle("Custom Dialog")
        .setMessage("FragDialog")
        .setNegativeButton(android.R.string.cancel) { dialog, id ->
            dialog.dismiss()
        }
        .setPositiveButton(android.R.string.ok) { dialog, id ->
            positiveButtonOnClickListener.onPositiveButtonClicked()
            dialog.dismiss()
        }
        .create()

    interface PositiveButtonOnClickListener {
        fun onPositiveButtonClicked()
    }

    companion object {
        fun newInstance() = CustomDialogFragment()
    }
}