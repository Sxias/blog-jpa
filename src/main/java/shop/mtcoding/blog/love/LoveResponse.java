package shop.mtcoding.blog.love;

import lombok.Data;

public class LoveResponse {

    @Data
    public static class SaveDTO {
        private Integer loveId;
        private Integer loveCount;

        public SaveDTO(Integer loveId, Integer loveCount) {
            this.loveId = loveId;
            this.loveCount = loveCount;
        }
    }

    @Data
    public static class DeleteDTO {
        private Integer loveCount;

        public DeleteDTO(Integer loveCount) {
            this.loveCount = loveCount;
        }
    }
}
