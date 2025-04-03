package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findByIdV2_test() {
        Board board = boardRepository.findByIdV2(1);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println("============");
        System.out.println(board.getUser().getUsername());
    }

    @Test
    public void findByIdV1_test() {
        Board board = boardRepository.findByIdV1(1);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getUser().getUsername());
        System.out.println(board.getUser().getPassword());
    }

}