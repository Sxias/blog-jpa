package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.love.Love;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // ORM

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Reply> replies = new ArrayList<Reply>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Love> loves = new ArrayList<Love>();

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, Boolean isPublic, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
        this.user = user;
        this.createdAt = createdAt;
    }
}