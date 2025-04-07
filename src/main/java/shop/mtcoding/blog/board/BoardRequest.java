package shop.mtcoding.blog.board;

import shop.mtcoding.blog.user.User;

public class BoardRequest {

    public static class SaveDTO {
        private String title;
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user)
                    .build();
        }
    }
}
