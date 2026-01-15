package hu.bme.sch.kirdev.ticketingspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TicketingSpringApplication

fun main(args: Array<String>) {
    runApplication<TicketingSpringApplication>(*args)
}
