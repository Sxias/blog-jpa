package shop.mtcoding.blog.temp;

import shop.mtcoding.blog.user.User;

public class Member {
    public static void main(String[] args) {
        User u = User.builder().username("admin").password("123456").build();


    }
}
