package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public Board findByIdV1(int id) {
        Query query = em.createNativeQuery("select bt.id, bt.title, bt.content, bt.is_public, bt.created_at, ut.id user_id, ut.username, ut.password, ut.email, ut.created_at from board_tb bt inner join user_tb ut on bt.user_id = ut.id where bt.id = ?");
        query.setParameter(1, id);

        Object[] obs = (Object[]) query.getSingleResult();

        User user = User.builder()
                .id((int) obs[5])
                .username((String) obs[6])
                .password((String) obs[7])
                .email((String) obs[8])
                .createdAt((Timestamp) obs[9])
                .build();

        Board board = Board.builder()
                .id((int) obs[0])
                .title((String) obs[1])
                .content((String) obs[2])
                .isPublic((boolean) obs[3])
                .createdAt((Timestamp) obs[4])
                .user(user)
                .build();

        return board;
    }

    public Board findByIdV2(int id) {
        return em.find(Board.class, id);
    }
}