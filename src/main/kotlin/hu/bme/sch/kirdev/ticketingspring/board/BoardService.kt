package hu.bme.sch.kirdev.ticketingspring.board

import org.springframework.stereotype.Service

@Service
class BoardService() {

    fun createBoard(board: CreateBoardDto): String {
        return "This action adds a new board"
    }

    fun getBoard(id: Int): String {
        return "This action returns a #${id} board"
    }

    fun getAllBoards(): String {
        return "This action returns all boards"
    }

    fun updateBoard(id: Int, board: UpdateBoardDto): String {
        return "This action updates a #${id} board"
    }

    fun deleteBoard(id: Int): String {
        return "This action removes a #${id} board"
    }

}