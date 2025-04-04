package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User user = joinDTO.toEntity();
        userRepository.insertUser2(user);
        // user 객체 : 영속 & 동기화됨
    }


    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = loginDTO.toEntity();
        User foundUser = userRepository.findByUsernameV2(user);
        if (foundUser == null) throw new RuntimeException("일치하는 유저가 없습니다.");
        if(!(foundUser.getPassword().equals(loginDTO.getPassword()))) throw new RuntimeException("아이디 또는 비밀번호가 틀립니다.");
        return foundUser;
    }
}
