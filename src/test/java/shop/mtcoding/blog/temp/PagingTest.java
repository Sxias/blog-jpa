package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PagingTest {

    @Test
    public void makeNumbers() {
        int current = 3;
        int totalPage = 7;

        List<Integer> numbers = new ArrayList<>();

        int start = (current / 5) * 5;
        int end = Math.min(start + 5, totalPage);

        for (int i = start; i < end; i++) {
            numbers.add(i);
        }

        numbers.forEach(System.out::println);
    }
}