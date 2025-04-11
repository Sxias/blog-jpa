package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.error.ex.Exception403;
import shop.mtcoding.blog._core.error.ex.Exception404;
import shop.mtcoding.blog.love.Love;
import shop.mtcoding.blog.love.LoveRepository;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.reply.ReplyRepository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final LoveRepository loveRepository;
    private final ReplyRepository replyRepository;

    public List<Board> 글목록보기(Integer userId) {
        if (userId == null) {
            return boardRepository.findAll();
        } else {
            return boardRepository.findAll(userId);
        }
    }

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        Board board = saveDTO.toEntity(sessionUser);
        boardRepository.save(board);
    }

    public BoardResponse.DetailDTO 글상세보기(Integer id, Integer userId) {
        Board board = boardRepository.findByIdJoinUserAndReplies(id);

        Love love = loveRepository.findByUserIdAndBoardId(userId, id);
        Long loveCount = loveRepository.findByBoardId(id);

        Integer loveId = love == null ? null : love.getId();
        Boolean isLove = love == null ? false : true;

        BoardResponse.DetailDTO detailDTO = new BoardResponse.DetailDTO(board, userId, isLove, loveCount.intValue(), loveId);
        return detailDTO;
    }

    // TODO : 글 수정
    @Transactional
    public void 글수정(BoardRequest.UpdateDTO updateDTO, Integer userId, Integer boardId) {
        // 1. 글 불러오기 by boardId
        Board board = boardRepository.findById(boardId);
        // 1-1. 글이 없으면 Exception404
        if(board == null) throw new Exception404("해당하는 글이 없습니다.");
        // 2. 글 주인과 userId 비교 : 불일치 시 Exception403
        if(!(board.getUser().getId().equals(userId))) throw new Exception403("권한이 없습니다.");
        // 3. update (dirty checking)
        board.update(updateDTO.getTitle(), updateDTO.getContent(), updateDTO.isPublicChecked());
    }

    // TODO : 글 삭제
    @Transactional
    public void 글삭제(Integer id) {
        boardRepository.deleteById(id);
    }

    public Board 글수정화면(Integer boardId, Integer sessionUserId) {
        Board board = boardRepository.findByIdJoinUserAndReplies(boardId);
        if(board == null) throw new Exception404("해당하는 글이 없습니다.");
        if(!(board.getUser().getId().equals(sessionUserId))) throw new Exception403("권한이 없습니다.");
        return board;
    }
}
