package com.example.contactappwithinternet.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactappwithinternet.databinding.ItemContactBinding
import com.example.contactappwithinternet.presentation.data.model.ContactData
import com.example.contactappwithinternet.presentation.data.remote.response.ContactResponse
import kotlin.math.abs

class ContactAdapter :
    ListAdapter<ContactResponse, ContactAdapter.ContactViewHolder>(ContactDiffUtil) {

    private var onLongClickListener: ((ContactResponse) -> Unit)? = null
    private var onClickListener: ((Int) -> Unit)? = null

    object ContactDiffUtil : DiffUtil.ItemCallback<ContactResponse>() {
        override fun areItemsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ContactResponse,
            newItem: ContactResponse,
        ): Boolean {
            return oldItem.firstName == newItem.firstName
        }
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                onLongClickListener?.invoke(getItem(absoluteAdapterPosition))
                return@setOnLongClickListener true
            }

            binding.root.setOnClickListener {
                onClickListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.contactName.text = getItem(absoluteAdapterPosition).firstName
            binding.contactLName.text = getItem(absoluteAdapterPosition).lastName
            binding.contactNumber.text = getItem(absoluteAdapterPosition).phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        ContactViewHolder(
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind()

    fun setOnLongClickListener(block: (ContactResponse) -> Unit) {
        this.onLongClickListener = block
    }

    fun setOnClickListener(block: (Int) -> Unit) {
        this.onClickListener = block
    }

}

