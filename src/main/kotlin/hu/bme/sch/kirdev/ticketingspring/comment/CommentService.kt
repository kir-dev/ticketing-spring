package hu.bme.sch.kirdev.ticketingspring.comment

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
@ConditionalOnBooleanProperty(value = ["hu.bme.sch.kirdev.ticketingspring.load.comment"])
class CommentService(
    private val commentRepository: CommentRepository,
    private val ticketRepository: TicketRepository
) {

    fun createComment(comment: CreateCommentDto): DetailedCommentDto {
        val ticket = ticketRepository.findById(comment.ticketId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
        return commentRepository.save(CommentEntity(
            postedBy = comment.postedBy,
            content = comment.content,
            ticketId = comment.ticketId
        )).let { DetailedCommentDto(it, ticket) }
    }

    fun getComment(id: Int): DetailedCommentDto {
        return commentRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found") }
            .let { comment ->
                val ticket = ticketRepository.findById(comment.ticketId)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
                DetailedCommentDto(comment, ticket)
            }
    }

    fun getAllComments(): List<DetailedCommentDto> {
        return commentRepository.findAll().map { comment ->
            val ticket = ticketRepository.findById(comment.ticketId)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
            DetailedCommentDto(comment, ticket)
        }
    }

    fun updateComment(id: Int, comment: CreateCommentDto): DetailedCommentDto {
        val ticket = ticketRepository.findById(comment.ticketId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found") }
        return commentRepository.findById(id).map {
            it.postedBy = comment.postedBy
            it.content = comment.content
            it.ticketId = comment.ticketId
            commentRepository.save(it)
        }.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found") }
        .let { DetailedCommentDto(it, ticket) }
    }

    fun deleteComment(id: Int) {
        commentRepository.deleteById(id)
    }

}