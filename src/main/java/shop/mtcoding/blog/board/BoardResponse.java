package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.reply.Reply;

import java.sql.Timestamp;
import java.util.List;

public class BoardResponse {

    // 상세보기 화면에 필요한 데이터
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Boolean isPublic;
        private Boolean isOwner;
        private Boolean isLove;
        private Integer loveCount;
        private String username;
        private Timestamp createdAt;
        private Integer loveId;
        private List<Reply> replies;

        public DetailDTO(Board board, Integer sessionUserId, Boolean isLove, Integer loveCount, Integer loveId, List<Reply> replies) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isPublic = board.getIsPublic();
            this.isOwner = sessionUserId == board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt();
            this.isLove = isLove;
            this.loveCount = loveCount;
            this.loveId = loveId;
            this.replies = replies;
        }
    }

}
