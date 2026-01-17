package hu.bme.sch.kirdev.ticketingspring.label


data class CreateLabelDto(
    val name: String,
    val color: String
)

data class UpdateLabelDto(
    val name: String,
    val color: String
)