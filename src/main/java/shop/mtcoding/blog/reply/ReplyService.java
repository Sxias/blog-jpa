package shop.mtcoding.blog.reply;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void 댓글저장(ReplyRequest.SaveDTO saveDTO, User sessionUser) {
        Reply req = Reply.builder()
                .content(saveDTO.getContent())
                .user(sessionUser)
                .board(Board.builder().id(saveDTO.getBoardId()).build())
                .build();
        replyRepository.save(req);
    }

    @Transactional
    public void 댓글삭제(int id) {
        replyRepository.deleteById(id);
    }
}
