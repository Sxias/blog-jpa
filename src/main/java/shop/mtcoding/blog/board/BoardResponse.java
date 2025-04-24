package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.reply.Reply;

import java.sql.Timestamp;
import java.util.ArrayList;
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

        private List<ReplyDTO> replies;

        @Data
        public class ReplyDTO {
            private Integer id;
            private String content;
            private String username;
            private Boolean isOwner;

            public ReplyDTO(Reply reply, Integer sessionUserId) {
                this.id = reply.getId();
                this.content = reply.getContent();
                this.username = reply.getUser().getUsername();
                this.isOwner = reply.getUser().getId().equals(sessionUserId);
            }
        }

        public DetailDTO(Board board, Integer sessionUserId, Boolean isLove, Integer loveCount, Integer loveId) {
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

            List<ReplyDTO> repliesDTO = new ArrayList<>();

            for (Reply reply : board.getReplies()) {
                ReplyDTO replyDTO = new ReplyDTO(reply, sessionUserId);
                repliesDTO.add(replyDTO);
            }

            this.replies = repliesDTO;

        }
    }

    @Data
    public static class MainDTO {
        private Integer prev;
        private Integer next;
        private Integer current;
        private Integer totalCount;
        private Integer totalPage;
        private Integer size;
        private Boolean isFirst; // currentPage == 0
        private Boolean isLast; // totalCount, totalPage == currentPage
        private Integer pageSize;
        private String keyword;

        private List<Board> boards;

        private List<Integer> numbers; // 20개 [1, 2, 3, 4, 5, 6, 7] -> {{#model.numbers}} -> {{.}}

        public MainDTO(List<Board> boards, Integer current, Integer totalCount, String keyword) {
            this.boards = boards;
            this.prev = current - 1;
            this.next = current + 1;
            this.size = 3;
            this.totalCount = totalCount; // given
            this.totalPage = makeTotalPage(totalCount, size);
            this.isFirst = current == 0;
            this.isLast = current == totalPage - 1;
            this.pageSize = 5;
            this.numbers = makeNumbers(current, pageSize, totalPage);
            // keyword 값이 있거나 null
            this.keyword = keyword;
            // 1. 페이지 전체 넣기
            /*
            for (int i = 0; i < totalCount; i++) {
                this.numbers.add(i);
            }
            */
            // 2. 페이지 묶음
            /*for (int i = 0; i < pageSize; i++) {
                pageIndex = current / pageSize; // 현재 페이지 index : 페이지 목차
                if (i % pageSize == 0) numbers.clear(); // 페이지를 불러올 때마다 페이지 리스트 클리어
                int page = pageIndex * pageSize + i; // 페이지 변수 : 페이지 index * pageSize + i
                // 0 ~ 4 : 0 * 5 + 0~4, 5 ~ 9 : 1 * 5 + 0~4, ...
                if (page < totalPage) numbers.add(page); // 최대 페이지 전까지 추가
            }*/
        }

        private List<Integer> makeNumbers(int current, int pageSize, int totalPage) {
            List<Integer> numbers = new ArrayList<>();

            int start = (current / pageSize) * pageSize;
            int end = Math.min(start + pageSize, totalPage);

            for (int i = start; i < end; i++) {
                numbers.add(i);
            }

            return numbers;
        }

        private Integer makeTotalPage(int totalCount, int size) {
            return totalCount / size + (totalCount % size == 0 ? 0 : 1);
        }
    }
}
