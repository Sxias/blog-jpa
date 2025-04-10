package shop.mtcoding.blog.reply;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

    public List<Reply> findAllByBoardId(int boardId) {
        Query q = em.createQuery("select r from Reply r join fetch r.user where r.board.id = :boardId order by r.id desc");
        q.setParameter("boardId", boardId);
        List<Reply> replies = q.getResultList();
        return replies;
    }

    public Reply save(Reply reply) {
        em.persist(reply);
        return reply;
    }

    public void deleteById(int id) {
        em.createQuery("delete from Reply r where r.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Reply findById(int id) {
        return em.find(Reply.class, id);
    }
}
