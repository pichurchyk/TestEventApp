package com.pichurchyk.testeventapp.presentation.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pichurchyk.testeventapp.databinding.ItemEventBinding
import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity

class ScheduleAdapter() :
    ListAdapter<ScheduleEventEntity, ScheduleEventViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleEventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return ScheduleEventViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ScheduleEventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<ScheduleEventEntity>() {

        override fun areItemsTheSame(oldItem: ScheduleEventEntity, newItem: ScheduleEventEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ScheduleEventEntity,
            newItem: ScheduleEventEntity,
        ) =
            oldItem == newItem
    }
}
