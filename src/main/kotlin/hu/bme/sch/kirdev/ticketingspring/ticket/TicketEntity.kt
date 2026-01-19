package hu.bme.sch.kirdev.ticketingspring.ticket

import hu.bme.sch.kirdev.ticketingspring.board.BoardEntity
import hu.bme.sch.kirdev.ticketingspring.label.LabelEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.util.Date

enum class TicketStatus {
    CREATED,
    IN_PROGRESS,
    UNDER_REVIEW,
    CLOSED,
}


@Entity
@Table(name = "ticket")
data class TicketEntity(
    @Id
    @GeneratedValue
    @Column(nullable = false)
    val id: Int = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = true)
    var description: String = "",

    @Column(nullable = false)
    var status: TicketStatus = TicketStatus.CREATED,

    @Column(nullable = false)
    val createdAt: Date = Date(),

    @Column(nullable = false)
    var updatedAt: Date = Date(),

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    var board: BoardEntity = BoardEntity(),

    @ManyToMany
    @JoinTable(
        name = "ticket_label",
        joinColumns = arrayOf(JoinColumn(name = "ticket_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "label_id"))
    )
    var labels: MutableList<LabelEntity> = mutableListOf()

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TicketEntity) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = Date()
    }
}
