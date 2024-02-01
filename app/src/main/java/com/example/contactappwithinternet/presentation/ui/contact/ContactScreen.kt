package com.example.contactappwithinternet.presentation.ui.contact

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactappwithinternet.R
import com.example.contactappwithinternet.databinding.ScreenContactBinding
import com.example.contactappwithinternet.presentation.data.remote.request.EditContactRequest
import com.example.contactappwithinternet.presentation.data.remote.response.ContactResponse
import com.example.contactappwithinternet.presentation.ui.adapter.ContactAdapter
import com.example.contactappwithinternet.presentation.ui.dialog.AddContactDialog
import com.example.contactappwithinternet.presentation.ui.dialog.EditContactDialog
import com.example.contactappwithinternet.presentation.utils.myApply

class ContactScreen : Fragment(R.layout.screen_contact) {
    private val binding by viewBinding(ScreenContactBinding::bind)
    private val viewModel by viewModels<ContactViewModel>()
    private val adapter by lazy { ContactAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        recycler.adapter = adapter

        addBtn.setOnClickListener { viewModel.openAddScreen() }

        adapter.setOnLongClickListener {
            showDialog(it)
        }

        viewModel.loadAllContact()
        viewModel.contact.observe(viewLifecycleOwner, contactObserver)
        viewModel.progressBar.observe(viewLifecycleOwner, progressObserver)
        viewModel.isEmpty.observe(viewLifecycleOwner, isEmptyObserver)
        viewModel.error.observe(viewLifecycleOwner, errorObserver)
        viewModel.openAddScreen.observe(viewLifecycleOwner, openAddScreenObserver)
    }

    private val contactObserver = Observer<List<ContactResponse>> {
        adapter.submitList(it)
    }

    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }

    private val isEmptyObserver = Observer<Boolean> {
        if (it) binding.empty.visibility = View.VISIBLE
        else binding.empty.visibility = View.GONE
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        Log.d("TTT", it)
    }

    private val openAddScreenObserver = Observer<Unit> {
        val addContact = AddContactDialog()

        addContact.setListener {
            viewModel.addContact(it.firstName, it.lastName, it.phone)
        }

        addContact.show(childFragmentManager, "ADD_CONTACT")
    }

    private fun showDialog(data: ContactResponse) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet)

        dialog.findViewById<ImageView>(R.id.dialog_delete).setOnClickListener {
            viewModel.deleteContact(data.id)
            dialog.dismiss()
        }

        dialog.findViewById<ImageView>(R.id.dialog_edit).setOnClickListener {
            val editDialog = EditContactDialog()

            editDialog.setEditableData(data.firstName, data.lastName, data.phone)
            editDialog.setListener { fName, lName, number ->
                viewModel.updateContact(EditContactRequest(data.id, fName, lName, number))
            }


            dialog.dismiss()
            editDialog.show(childFragmentManager, "EDIT_CONTACT")

        }

        dialog.show()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }


}