package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;


    public void write(Board board) {
        em.persist(board);
    }

    public List<Board> findAll(Integer userId) {
        String s1 = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
        String s2 = "select b from Board b where b.isPublic = true order by b.id desc";

        Query q = em.createQuery(userId == null ? s2 : s1, Board.class);
        if(userId != null) q.setParameter("userId", userId);
        return q.getResultList();
    }

    public Board findById(int id) {
        return em.find(Board.class, id);
    }

    public Board findByIdJoinUser(int id) {
        // inner join은 on 절 생략 가능
        Query q = em.createQuery("select b from Board b join b.user where b.id = :id", Board.class);
        q.setParameter("id", id);
        return (Board) q.getSingleResult();
    }
}
