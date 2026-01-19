package hu.bme.sch.kirdev.ticketingspring.board

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService
) {

    @Operation(summary = "Create a new board")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Board created",
                content = [Content(schema = Schema(implementation = DetailedBoardDto::class))]
            ),
        ]
    )
    @PostMapping
    fun createBoard(@RequestBody board: CreateBoardDto): ResponseEntity<DetailedBoardDto> {
        val created = boardService.createBoard(board)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }


    @Operation(summary = "List all boards")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Boards found",
                content = [Content(schema = Schema(implementation = DetailedBoardDto::class))]
            ),
        ]
    )
    @GetMapping
    fun getAllBoards(): ResponseEntity<List<DetailedBoardDto>> {
        val boards = boardService.getAllBoards()
        return ResponseEntity.ok(boards)
    }


    @Operation(summary = "Get a board by ID")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Board found",
                content = [Content(schema = Schema(implementation = DetailedBoardDto::class))]
            ),
            ApiResponse(responseCode = "404", description = "Board not found"),
        ]
    )
    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Int): ResponseEntity<DetailedBoardDto> {
        val board = boardService.getBoard(id)
        return ResponseEntity.ok(board)
    }


    @Operation(summary = "Update a board")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Board updated",
                content = [Content(schema = Schema(implementation = DetailedBoardDto::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Board not found"
            ),
        ]
    )
    @PatchMapping("/{id}")
    fun updateBoard(@PathVariable id: Int, @RequestBody board: UpdateBoardDto): ResponseEntity<DetailedBoardDto> {
        val updated = boardService.updateBoard(id, board)
        return ResponseEntity.ok(updated)
    }


    @Operation(summary = "Delete a board")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Board deleted"
            ),
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: Int): ResponseEntity<Void> {
        boardService.deleteBoard(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}