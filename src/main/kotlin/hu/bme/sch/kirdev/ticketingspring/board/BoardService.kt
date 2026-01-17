package hu.bme.sch.kirdev.ticketingspring.board

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    fun createBoard(board: CreateBoardDto): BoardEntity {
        return boardRepository.save(BoardEntity(
            title = board.title
        ))
    }

    fun getBoard(id: Int): BoardEntity {
        return boardRepository.findById(id)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found") }
    }

    fun getAllBoards(): List<BoardEntity> {
        return boardRepository.findAll().toList()
    }

    fun updateBoard(id: Int, board: UpdateBoardDto): BoardEntity {
        return boardRepository.findById(id).map {
            val toUpdate = it
            toUpdate.title = board.title
            boardRepository.save(toUpdate)
        }.orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found") }
    }

    fun deleteBoard(id: Int) {
        boardRepository.deleteById(id)
    }

}