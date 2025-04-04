package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public void insertUser(User user) {
        Query q = em.createNativeQuery("insert into user_tb(username, password, email, created_at) values(?, ?, ?, now());");
        q.setParameter(1, user.getUsername());
        q.setParameter(2, user.getPassword());
        q.setParameter(3, user.getEmail());
        q.executeUpdate();
    }

    // 2. User 영속
    public void insertUser2(User user) {
        em.persist(user);
    }
    // 3. User는 DB와 동기화

    /*
        1. createNativeQuery : 기본 쿼리
        2. createQuery : JPA가 제공하는 객체 지향 쿼리
        3. createNamedQuery : Query Method ( 함수 이름으로 쿼리 생성 )
        4. createEntityGraph
     */

    public User findByUsernameV1(User user) {
        Query q = em.createNativeQuery("select * from user_tb where username = ?;");
        q.setParameter(1, user.getUsername());
        Object[] obs = (Object[]) q.getSingleResult();

        User foundUser = User.builder()
                .id((int) obs[0])
                .username((String) obs[1])
                .password((String) obs[2])
                .email((String) obs[3])
                .createdAt((Timestamp) obs[4])
                .build();

        return foundUser;
    }

    public User findByUsernameV2(String username) {
        try {
            Query q = em.createQuery("select u from User u where u.username = :username", User.class);
            q.setParameter("username", username);
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
