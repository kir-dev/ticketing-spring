package hu.bme.sch.kirdev.ticketingspring.comment

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty
import java.util.Date


@Entity
@Table(name = "comment")
@ConditionalOnBooleanProperty(value = ["hu.bme.sch.kirdev.ticketingspring.load.comment"])
data class CommentEntity(
    @Id
    @GeneratedValue
    @Column(nullable = false)
    val id: Int = 0,

    @Column(nullable = false)
    var postedBy: String = "",

    @Column(nullable = false)
    var content: String = "",

    @Column(nullable = false)
    val postedAt: Date = Date(),

    @Column(nullable = false)
    var updatedAt: Date = Date(),

    @Column(nullable = false)
    var ticketId: Int = 0,

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CommentEntity) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = Date()
    }
}
