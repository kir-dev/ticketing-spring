package hu.bme.sch.kirdev.ticketingspring.comment

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketDto
import hu.bme.sch.kirdev.ticketingspring.ticket.TicketEntity
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty
import java.util.Date

@ConditionalOnBooleanProperty(value = ["hu.bme.sch.kirdev.ticketingspring.load.comment"])
data class CreateCommentDto(
    val postedBy: String,
    val content: String
)

@ConditionalOnBooleanProperty(value = ["hu.bme.sch.kirdev.ticketingspring.load.comment"])
data class CommentDto(
    val id: Int,
    val postedBy: String,
    val content: String,
    val postedAt: Date,
    val updatedAt: Date,
    val ticketId: Int,
)
{
    constructor(comment: CommentEntity): this(
        id = comment.id,
        postedBy = comment.postedBy,
        content = comment.content,
        postedAt = comment.postedAt,
        updatedAt = comment.updatedAt,
        ticketId = comment.ticketId,
    )
}

@ConditionalOnBooleanProperty(value = ["hu.bme.sch.kirdev.ticketingspring.load.comment"])
data class DetailedCommentDto(
    val id: Int,
    val postedBy: String,
    val content: String,
    val postedAt: Date,
    val updatedAt: Date,
    val ticket: TicketDto
) {
    constructor(comment: CommentEntity, ticket: TicketEntity): this(
        id = comment.id,
        postedBy = comment.postedBy,
        content = comment.content,
        postedAt = comment.postedAt,
        updatedAt = comment.updatedAt,
        ticket = TicketDto(ticket)
    )
}