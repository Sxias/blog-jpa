package shop.mtcoding.blog.reply;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        @NotNull(message = "해당하는 글이 없습니다.")
        private Integer boardId;
        @NotEmpty(message = "내용을 입력해주세요.")
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
