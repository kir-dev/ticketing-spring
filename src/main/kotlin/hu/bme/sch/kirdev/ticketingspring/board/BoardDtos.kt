package hu.bme.sch.kirdev.ticketingspring.board

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketDto
import java.util.Date

data class CreateBoardDto(
    val title: String,
)

data class UpdateBoardDto(
    val title: String,
)

data class BoardDto(
    val id: Int,
    val title: String,
    val createdAt: Date,
){
    constructor(board: BoardEntity): this(
        id = board.id,
        title = board.title,
        createdAt = board.createdAt,
    )
}

data class DetailedBoardDto(
    val id: Int,
    val title: String,
    val createdAt: Date,
    val tickets: List<TicketDto>,
){
    constructor(board: BoardEntity): this(
        id = board.id,
        title = board.title,
        createdAt = board.createdAt,
        tickets = board.tickets.map { TicketDto(it) }
    )
}