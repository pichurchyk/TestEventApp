package com.pichurchyk.testeventapp.presentation.events.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.databinding.ItemEventBinding
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.utils.visibleIf

class EventViewHolder(
    private val binding: ItemEventBinding,
    private val clickListener: EventsAdapter.EventClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: EventEntity) {
        with(binding) {
            tvSubtitle.apply {
                text = event.subtitle
                visibleIf(!event.subtitle.isNullOrEmpty())
            }

            tvTitle.text = event.title

            tvDate.text = event.date

            ivEventImage.load(event.imageURL) {
                crossfade(true)
                error(R.drawable.ic_img_not_found)
            }

            root.setOnClickListener {
                clickListener.onEventClick(event)
            }
        }
    }
}
