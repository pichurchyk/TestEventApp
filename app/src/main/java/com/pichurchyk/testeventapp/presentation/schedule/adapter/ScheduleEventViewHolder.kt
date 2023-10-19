package com.pichurchyk.testeventapp.presentation.schedule.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.databinding.ItemEventBinding
import com.pichurchyk.testeventapp.domain.common.getEventDay
import com.pichurchyk.testeventapp.domain.common.getEventTime
import com.pichurchyk.testeventapp.domain.common.getTodayOrYesterday
import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity
import com.pichurchyk.testeventapp.utils.date.getStringResId
import com.pichurchyk.testeventapp.utils.visibleIf
import org.joda.time.DateTime

class ScheduleEventViewHolder(
    private val binding: ItemEventBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: ScheduleEventEntity) {
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

                tvTime.text = date.getEventTime()
            }

            ivEventImage.load(event.imageURL) {
                crossfade(true)
                error(R.drawable.ic_img_not_found)
            }
        }
    }
}
