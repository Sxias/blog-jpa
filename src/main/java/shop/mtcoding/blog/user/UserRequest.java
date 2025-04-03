package shop.mtcoding.blog.user;

import lombok.AllArgsConstructor;
import lombok.Data;

public class UserRequest {

    @AllArgsConstructor
    @Data
    public class loginDTO {
        private String username;
        private String password;
        private String email;
    }
}
