package shop.mtcoding.blog.reply;

import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String content;

        public Reply toEntity(User sessionUser) {
            return Reply.builder()
                    .content(content)
                    .user(sessionUser)
                    .board(Board.builder().id(boardId).build())
                    .build();
        }
    }
}
