package com.pichurchyk.testeventapp.presentation.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pichurchyk.testeventapp.databinding.ItemEventBinding
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity

class EventsAdapter(private val clickListener: EventClickListener) :
    ListAdapter<EventEntity, EventViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return EventViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<EventEntity>() {

        override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity) =
            oldItem == newItem
    }

    interface EventClickListener {
        fun onEventClick(event: EventEntity)
    }
}
