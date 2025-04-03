package shop.mtcoding.blog.user;

import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUserTest() {
        User user = User.builder().username("admin").password("123456").email("abcd@efg.com").build();
        userRepository.insertUser(user);

    }

}
