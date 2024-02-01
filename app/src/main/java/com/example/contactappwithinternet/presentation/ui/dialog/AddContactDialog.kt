package com.example.contactappwithinternet.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactappwithinternet.R
import com.example.contactappwithinternet.databinding.ScreenAddContactBinding
import com.example.contactappwithinternet.presentation.data.remote.request.CreateContactRequest

class AddContactDialog : DialogFragment(R.layout.screen_add_contact) {

    private val binding by viewBinding(ScreenAddContactBinding::bind)
    private var listener: ((CreateContactRequest) -> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSave.setOnClickListener {
            listener?.invoke(
                CreateContactRequest(
                    binding.editTextFirstName.text.toString(),
                    binding.editTextLastName.text.toString(),
                    binding.editTextAge.text.toString()
                )
            )
            dismiss()
        }

    }

    fun setListener(block: (CreateContactRequest) -> Unit) {
        listener = block
    }

    override fun onStart() {
        super.onStart()
        this.dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}