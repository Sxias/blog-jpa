package shop.mtcoding.blog.board;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, User user) {
        Board board = saveDTO.toEntity(user);
        boardRepository.write(board);
    }

    public List<Board> 글목록보기(Integer userId) {
        return boardRepository.findAll(userId);
    }
}
