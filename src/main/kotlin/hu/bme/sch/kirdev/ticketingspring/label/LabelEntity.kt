package hu.bme.sch.kirdev.ticketingspring.label

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table


@Entity
@Table(name = "label")
data class LabelEntity(

    @Id
    @GeneratedValue
    @Column(nullable = false)
    val id: Int = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var color: String = "",

    @ManyToMany
    var tickets: MutableList<TicketEntity> = mutableListOf()

){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LabelEntity) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }
}
