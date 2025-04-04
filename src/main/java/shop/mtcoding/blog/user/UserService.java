package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        try{
            User user = joinDTO.toEntity();
            userRepository.insertUser2(user);
        } catch (Exception e) {
            throw new RuntimeException("비정상 접근이 감지되었습니다.");
        }
        // user 객체 : 영속 & 동기화됨
    }


    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = loginDTO.toEntity();
        User foundUser = userRepository.findByUsernameV2(user.getUsername());
        if (foundUser == null) throw new RuntimeException("일치하는 유저가 없습니다.");
        if(!(foundUser.getPassword().equals(loginDTO.getPassword()))) throw new RuntimeException("아이디 또는 비밀번호가 틀립니다.");
        return foundUser;
    }

    public Map<String, Object> 유저네임중복체크(String username) {
        User user = userRepository.findByUsernameV2(username);
        Map<String, Object> dto = new HashMap<>();

        if(user == null) dto.put("available", true);
        else dto.put("available", false);
        return dto;
    }
}
