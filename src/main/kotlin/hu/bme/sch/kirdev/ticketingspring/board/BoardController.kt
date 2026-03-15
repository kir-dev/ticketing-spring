package hu.bme.sch.kirdev.ticketingspring.board

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService
) {

    @PostMapping
    fun createBoard(@RequestBody board: CreateBoardDto): ResponseEntity<DetailedBoardDto> {
        val created = boardService.createBoard(board)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }


    @GetMapping
    fun getAllBoards(): ResponseEntity<List<DetailedBoardDto>> {
        val boards = boardService.getAllBoards()
        return ResponseEntity.ok(boards)
    }


    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Int): ResponseEntity<DetailedBoardDto> {
        val board = boardService.getBoard(id)
        return ResponseEntity.ok(board)
    }


    @PatchMapping("/{id}")
    fun updateBoard(@PathVariable id: Int, @RequestBody board: UpdateBoardDto): ResponseEntity<DetailedBoardDto> {
        val updated = boardService.updateBoard(id, board)
        return ResponseEntity.ok(updated)
    }


    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: Int): ResponseEntity<Void> {
        boardService.deleteBoard(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}