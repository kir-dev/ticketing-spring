package hu.bme.sch.kirdev.ticketingspring.board

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService
) {

    @PostMapping
    fun createBoard(@RequestBody board: CreateBoardDto): String {
        val created = boardService.createBoard(board)
        return created
    }


    @GetMapping
    fun getAllBoards(): String {
        val boards = boardService.getAllBoards()
        return boards
    }


    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Int): String {
        val board = boardService.getBoard(id)
        return board
    }


    @PatchMapping("/{id}")
    fun updateBoard(@PathVariable id: Int, @RequestBody board: UpdateBoardDto): String {
        val updated = boardService.updateBoard(id, board)
        return updated
    }


    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: Int): String {
        val res = boardService.deleteBoard(id)
        return res
    }

}