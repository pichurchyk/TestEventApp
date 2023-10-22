package com.pichurchyk.testeventapp.presentation.events.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.databinding.ItemEventBinding
import com.pichurchyk.testeventapp.domain.common.getEventDay
import com.pichurchyk.testeventapp.domain.common.getEventTime
import com.pichurchyk.testeventapp.domain.common.getTodayOrYesterday
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.utils.date.getStringResId
import com.pichurchyk.testeventapp.utils.visibleIf
import org.joda.time.DateTime

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

            event.date.let { date ->
                val todayOrYesterday = date.getTodayOrYesterday(DateTime.now())?.let {
                    root.context.getString(it.getStringResId())
                }
                tvDate.text = todayOrYesterday ?: date.getEventDay()

                tvTime.apply {
                    visibleIf(todayOrYesterday != null)
                    text = date.getEventTime()
                }
            }

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
