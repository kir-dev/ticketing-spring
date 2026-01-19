package hu.bme.sch.kirdev.ticketingspring.board

import hu.bme.sch.kirdev.ticketingspring.ticket.TicketDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    fun createBoard(board: CreateBoardDto): DetailedBoardDto {
        return boardRepository.save(BoardEntity(
            title = board.title
        )).let { DetailedBoardDto(it) }
    }

    fun getBoard(id: Int): DetailedBoardDto {
        return boardRepository.findById(id)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found") }
            .let { DetailedBoardDto(it) }
    }

    fun getAllBoards(): List<DetailedBoardDto> {
        return boardRepository.findAll().map { DetailedBoardDto(it) }
    }

    fun updateBoard(id: Int, board: UpdateBoardDto): DetailedBoardDto {
        return boardRepository.findById(id).map {
            val toUpdate = it
            toUpdate.title = board.title
            boardRepository.save(toUpdate)
        }.orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found") }
        .let { DetailedBoardDto(it) }
    }

    fun deleteBoard(id: Int) {
        boardRepository.deleteById(id)
    }

}