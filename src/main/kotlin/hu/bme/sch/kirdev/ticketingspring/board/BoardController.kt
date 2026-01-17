package hu.bme.sch.kirdev.ticketingspring.board

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

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Board created",
                content = [Content(schema = Schema(implementation = BoardEntity::class))]
            ),
        ]
    )
    @PostMapping
    fun createBoard(@RequestBody board: CreateBoardDto): ResponseEntity<BoardEntity> {
        val created = boardService.createBoard(board)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Boards found",
                content = [Content(schema = Schema(implementation = BoardEntity::class))]
            ),
        ]
    )
    @GetMapping
    fun getAllBoards(): ResponseEntity<List<BoardEntity>> {
        val boards = boardService.getAllBoards()
        return ResponseEntity.ok(boards)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Board found",
                content = [Content(schema = Schema(implementation = BoardEntity::class))]
            ),
            ApiResponse(responseCode = "404", description = "Board not found"),
        ]
    )
    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Int): ResponseEntity<BoardEntity> {
        val board = boardService.getBoard(id)
        return ResponseEntity.ok(board)
    }


    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Board updated",
                content = [Content(schema = Schema(implementation = BoardEntity::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Board not found"
            ),
        ]
    )
    @PatchMapping("/{id}")
    fun updateBoard(@PathVariable id: Int, @RequestBody board: UpdateBoardDto): ResponseEntity<BoardEntity> {
        val updated = boardService.updateBoard(id, board)
        return ResponseEntity.ok(updated)
    }


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