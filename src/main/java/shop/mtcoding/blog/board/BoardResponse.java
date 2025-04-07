package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpSession;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Boolean isPublic;
        private Boolean isOwner;
        private Integer userId;
        private String username;
        private Timestamp createdAt;


        public DetailDTO(Board board, Integer sessionUserId) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isPublic = board.getIsPublic();
            this.isOwner = board.getUser().getId() == sessionUserId;
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt();
        }
    }
}
