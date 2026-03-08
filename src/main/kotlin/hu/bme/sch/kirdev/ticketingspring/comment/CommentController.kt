package hu.bme.sch.kirdev.ticketingspring.comment

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
@ConditionalOnBooleanProperty(value = ["hu.bme.sch.kirdev.ticketingspring.load.comment"])
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping("/tickets/{ticketId}/comments")
    fun createComment(@RequestBody comment: CreateCommentDto, @PathVariable ticketId: Int): ResponseEntity<DetailedCommentDto> {
        val created = commentService.createComment(comment, ticketId)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @GetMapping("/comments/{id}")
    fun getComment(@PathVariable id: Int): ResponseEntity<DetailedCommentDto> {
        val comment = commentService.getComment(id)
        return ResponseEntity.status(HttpStatus.OK).body(comment)
    }

    @GetMapping("/tickets/{ticketId}/comments")
    fun getAllCommentsForTicket(@PathVariable ticketId: Int): ResponseEntity<List<DetailedCommentDto>> {
        val comments = commentService.getAllCommentsForTicket(ticketId)
        return ResponseEntity.status(HttpStatus.OK).body(comments)
    }

    @PatchMapping("/comments/{id}")
    fun updateComment(@PathVariable id: Int, @RequestBody comment: CreateCommentDto): ResponseEntity<DetailedCommentDto> {
        val updated = commentService.updateComment(id, comment)
        return ResponseEntity.status(HttpStatus.OK).body(updated)
    }

    @DeleteMapping("/comments/{id}")
    fun deleteComment(@PathVariable id: Int): ResponseEntity<Void> {
        commentService.deleteComment(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}