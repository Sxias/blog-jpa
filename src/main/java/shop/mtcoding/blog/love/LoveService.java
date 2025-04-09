package shop.mtcoding.blog.love;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public LoveResponse.DeleteDTO 좋아요취소(Integer id) {
        Love lovePS = loveRepository.findById(id);
        if(lovePS == null) throw new RuntimeException("잘못된 접근이 감지되었습니다.");
        loveRepository.deleteById(id);
        Long loveCount = loveRepository.findByBoardId(lovePS.getBoard().getId());
        return new LoveResponse.DeleteDTO(loveCount.intValue());
    }
}
