package hu.bme.sch.kirdev.ticketingspring.board

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy
import java.util.Date


@Entity
@Table(name = "board")
data class BoardEntity(
    @Id
    @GeneratedValue
    @Column(nullable = false)
    val id: Int = 0,

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = false)
    var createdAt: Date = Date(),

    @OneToMany
    var tickets: MutableList<TicketEntity> = mutableListOf(),

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BoardEntity) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }
}
