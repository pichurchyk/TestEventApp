package com.pichurchyk.testeventapp.data.mapper

import com.pichurchyk.testeventapp.data.io.EventResponse
import com.pichurchyk.testeventapp.data.mapper.base.BaseMapper
import com.pichurchyk.testeventapp.domain.common.DateUtils
import com.pichurchyk.testeventapp.domain.entity.date.EventDate
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity

object EventMapper : BaseMapper<EventResponse, EventEntity> {
    override fun mapFrom(from: EventResponse): EventEntity = EventEntity(
        id = from.id,
        title = from.title,
        subtitle = from.subtitle,
        date = from.date.let { date ->
            val formattedDate = DateUtils.parseDate(date)
            EventDate(formattedDate)
        },
        imageURL = from.imageUrl,
        videoURL = from.videoUrl,
    )
}
