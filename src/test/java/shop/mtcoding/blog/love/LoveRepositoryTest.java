package shop.mtcoding.blog.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(LoveRepository.class)
@DataJpaTest // EntityManager, PC
public class LoveRepositoryTest {

    @Autowired // DI
    private LoveRepository loveRepository;


}
