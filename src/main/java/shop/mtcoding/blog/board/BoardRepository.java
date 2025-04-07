package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;


    public void write(Board board) {
        em.persist(board);
    }
}
