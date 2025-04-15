package shop.mtcoding.blog.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {

    @Data
    public static class UpdateDTO {
        private String username;
        private String password;
        private String email;
    }

    // insert 용도의 dto에는 toEntity 메서드를 만든다.
    @Data
    public static class JoinDTO {
        // DTO 유효성 검사 annotation
        // 1. Pattern
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "유저네임은 2-20자이며, 특수문자,한글이 포함될 수 없습니다")
        private String username;

        // 2. Size
        @Size(min = 4, max = 20, message = "크기가 4자에서 20자 사이여야 합니다.")
        private String password;

        @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 적어주세요")
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "유저네임은 2-20자이며, 특수문자,한글이 포함될 수 없습니다")
        private String username;
        @Size(min = 4, max = 20, message = "크기가 4자에서 20자 사이여야 합니다.")
        private String password;
        private String rememberMe; // check되면 on, 안되면 null
    }
}
