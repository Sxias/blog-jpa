package shop.mtcoding.blog.love;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.error.ex.ExceptionApi403;
import shop.mtcoding.blog._core.error.ex.ExceptionApi404;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class LoveService {
    private final LoveRepository loveRepository;

    @Transactional
    public LoveResponse.SaveDTO 좋아요(LoveRequest.SaveDTO reqDTO, Integer sessionUserId) {
        Love lovePS = loveRepository.save(reqDTO.toEntity(sessionUserId));
        Long loveCount = loveRepository.findByBoardId(lovePS.getBoard().getId());
        return new LoveResponse.SaveDTO(lovePS.getId(), loveCount.intValue());
    }

    @Transactional
    public LoveResponse.DeleteDTO 좋아요취소(Integer id, Integer sessionUserId) {
        Love lovePS = loveRepository.findById(id);

        // ExceptionApi404
        if(lovePS == null) throw new ExceptionApi404("잘못된 접근이 감지되었습니다.");

        // ExceptionApi403
        if(!(lovePS.getBoard().getUser().getId().equals(sessionUserId))) throw new ExceptionApi403("권한이 없습니다.");
        loveRepository.deleteById(id);
        Long loveCount = loveRepository.findByBoardId(lovePS.getBoard().getId());
        return new LoveResponse.DeleteDTO(loveCount.intValue());
    }
}
