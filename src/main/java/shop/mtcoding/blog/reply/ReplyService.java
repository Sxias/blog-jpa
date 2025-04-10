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
        Reply req = saveDTO.toEntity(sessionUser);
        replyRepository.save(req);
    }

    @Transactional
    public void 댓글삭제(int id) {
        replyRepository.deleteById(id);
    }

    @Transactional
    public Integer 댓글삭제2(int id, Integer sessionUserId) {
        // 1. 댓글 존재 확인
        Reply reply = replyRepository.findById(id);
        if(reply == null) throw new RuntimeException("댓글이 존재하지 않습니다.");
        // 2. 로그인 한 유저와 일치하는 지 확인
        if (reply.getUser().getId() != sessionUserId) throw new RuntimeException("권한이 없습니다.");
        // 3. 삭제
        replyRepository.deleteById(id);

        return reply.getBoard().getId();
    }
}
