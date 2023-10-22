package com.pichurchyk.testeventapp.data.mapper

import com.pichurchyk.testeventapp.data.io.ScheduleEventResponse
import com.pichurchyk.testeventapp.data.mapper.base.BaseMapper
import com.pichurchyk.testeventapp.domain.common.DateUtils
import com.pichurchyk.testeventapp.domain.entity.date.EventDate
import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity

object ScheduleEventMapper : BaseMapper<ScheduleEventResponse, ScheduleEventEntity> {
    override fun mapFrom(from: ScheduleEventResponse): ScheduleEventEntity = ScheduleEventEntity(
        id = from.id,
        title = from.title,
        subtitle = from.subtitle,
        date = from.date.let { date ->
            val formattedDate = DateUtils.parseDate(date)
            EventDate(formattedDate)
        },
        imageURL = from.imageUrl,
    )
}
