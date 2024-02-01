package com.example.contactappwithinternet.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactappwithinternet.R
import com.example.contactappwithinternet.databinding.ScreenAddContactBinding

class EditContactDialog : DialogFragment(R.layout.screen_add_contact) {

    private val binding by viewBinding(ScreenAddContactBinding::bind)
    private var listener: ((fName: String, lName: String, number: String) -> Unit)? = null

    private var currentFName: String = ""
    private var currentLName: String = ""
    private var currentPhone: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.editTextFirstName.setText(currentFName)
        binding.editTextLastName.setText(currentLName)
        binding.editTextAge.setText(currentPhone)

        binding.btnSave.setOnClickListener {
            listener?.invoke(
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.editTextAge.text.toString()
            )
            dismiss()
        }

    }

    fun setListener(block: (fName: String, lName: String, number: String) -> Unit) {
        listener = block
    }

    override fun onStart() {
        super.onStart()
        this.dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun setEditableData(fName: String, lName: String, phone: String) {
        currentFName = fName
        currentLName = lName
        currentPhone = phone
    }
}