package hu.bme.sch.kirdev.ticketingspring.label

import hu.bme.sch.kirdev.ticketingspring.board.BoardEntity
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/label")
class LabelController(
    private val labelService: LabelService
) {

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Label created",
                content = [Content(schema = Schema(implementation = DetailedLabelDto::class))]
            ),
        ]
    )
    @PostMapping
    fun createLabel(@RequestBody label: CreateLabelDto): ResponseEntity<DetailedLabelDto> {
        val created = labelService.createLabel(label)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Labels found",
                content = [Content(schema = Schema(implementation = DetailedLabelDto::class))]
            )
        ]
    )
    @GetMapping
    fun getAllLabels(): ResponseEntity<List<DetailedLabelDto>> {
        val labels = labelService.getAllLabels()
        return ResponseEntity.status(HttpStatus.OK).body(labels)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Label found",
                content = [Content(schema = Schema(implementation = DetailedLabelDto::class))]
            ),
            ApiResponse(responseCode = "404", description = "Label not found"),
        ]
    )
    @GetMapping("/{id}")
    fun getLabel(@PathVariable id: Int): ResponseEntity<DetailedLabelDto> {
        val label = labelService.getLabel(id)
        return ResponseEntity.status(HttpStatus.OK).body(label)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Label updated",
                content = [Content(schema = Schema(implementation = DetailedLabelDto::class))]
            ),
            ApiResponse(responseCode = "404", description = "Label not found"),
        ]
    )
    @PatchMapping("/{id}")
    fun updateLabel(@PathVariable id: Int, @RequestBody label: UpdateLabelDto): ResponseEntity<DetailedLabelDto> {
        val updated = labelService.updateLabel(id, label)
        return ResponseEntity.status(HttpStatus.OK).body(updated)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Label deleted"
            ),
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteLabel(@PathVariable id: Int): ResponseEntity<Void> {
        labelService.deleteLabel(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}