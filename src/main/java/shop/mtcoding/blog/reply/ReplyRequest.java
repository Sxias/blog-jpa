package shop.mtcoding.blog.reply;

import lombok.Data;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String content;
    }
}
