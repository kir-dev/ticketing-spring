package hu.bme.sch.kirdev.ticketingspring.ticket

import hu.bme.sch.kirdev.ticketingspring.board.BoardEntity
import hu.bme.sch.kirdev.ticketingspring.label.LabelEntity

data class CreateTicketDto(
    val name: String,
    val description: String?,
    var boardId: Int,
    var labelIds: List<Int>?
)

data class UpdateTicketDto(
    val name: String,
    val description: String?,
    var status: TicketStatus,
    var boardId: Int,
    var labelIds: List<Int>?
)