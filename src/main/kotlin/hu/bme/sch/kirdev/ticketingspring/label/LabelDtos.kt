package hu.bme.sch.kirdev.ticketingspring.label

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketDto


data class CreateLabelDto(
    val name: String,
    val color: String
)

data class UpdateLabelDto(
    val name: String,
    val color: String
)

data class LabelDto(
    val id: Int,
    val name: String,
    val color: String
){
    constructor(label: LabelEntity): this(
        id = label.id,
        name = label.name,
        color = label.color
    )
}

data class DetailedLabelDto(
    val id: Int,
    val name: String,
    val color: String,
    val tickets: List<TicketDto>
){
    constructor(label: LabelEntity): this(
        id = label.id,
        name = label.name,
        color = label.color,
        tickets = label.tickets.map { TicketDto(it) }
    )
}