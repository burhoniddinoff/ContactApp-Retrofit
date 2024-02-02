package com.example.contactappwithinternet.presentation.screen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactappwithinternet.databinding.ItemHistoryCallBinding
import com.example.contactappwithinternet.presentation.data.remote.data.CallHistoryResponse

class CallHistoryAdapter :
    ListAdapter<CallHistoryResponse, CallHistoryAdapter.ContactViewHolder>(ContactDiffUtil) {

    object ContactDiffUtil : DiffUtil.ItemCallback<CallHistoryResponse>() {
        override fun areItemsTheSame(
            oldItem: CallHistoryResponse,
            newItem: CallHistoryResponse,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CallHistoryResponse,
            newItem: CallHistoryResponse,
        ): Boolean {
            return oldItem.date == newItem.date
        }
    }

    inner class ContactViewHolder(private val binding: ItemHistoryCallBinding) :
        ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.day.text = getItem(absoluteAdapterPosition).date
            binding.time.text = getItem(absoluteAdapterPosition).time
            binding.outGoingCall.text = getItem(absoluteAdapterPosition).call
            binding.second.text = getItem(absoluteAdapterPosition).second
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        ContactViewHolder(
            ItemHistoryCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind()


}

