package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home(HttpServletRequest request, HttpSession session) {
        User validatedUser = (User) session.getAttribute("validatedUser");
        if (validatedUser == null) {
            request.setAttribute("models", boardService.글목록보기(null));
        } else {
            request.setAttribute("models", boardService.글목록보기(validatedUser.getId()));
        }
        return "board/list";
    }

    @GetMapping("/board/save-form")
    public String saveForm(HttpSession session) {
        User validatedUser = (User) session.getAttribute("validatedUser");
        if (validatedUser == null) throw new RuntimeException("인증이 필요합니다.");
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO, HttpSession session) {
        User validatedUser = (User) session.getAttribute("validatedUser");
        if (validatedUser == null) throw new RuntimeException("인증이 필요합니다.");
        boardService.글쓰기(saveDTO, validatedUser);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String updateForm(@PathVariable("id") int id, HttpSession session) {
        User validatedUser = (User) session.getAttribute("validatedUser");
        if (validatedUser == null) throw new RuntimeException("인증이 필요합니다.");
        return "board/update-form";
    }

}
