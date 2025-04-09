package shop.mtcoding.blog.reply;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name="reply_tb")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Reply(int id, User user, Board board, String content, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.content = content;
        this.createdAt = createdAt;
    }
}
