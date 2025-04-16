package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @GetMapping("/v2/board/{id}")
    public @ResponseBody BoardResponse.DetailDTO v2detail(@PathVariable("id") Integer id) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = 1;
//        (sessionUser == null ? null : sessionUser.getId());
        BoardResponse.DetailDTO detailDTO = boardService.글상세보기(id, sessionUserId);

        return detailDTO;
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = (sessionUser == null ? null : sessionUser.getId());
        BoardResponse.DetailDTO detailDTO = boardService.글상세보기(id, sessionUserId);
        request.setAttribute("model", detailDTO);
        return "board/detail";
    }

    @GetMapping("/")
    public String list(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            request.setAttribute("models", boardService.글목록보기(null));
        } else {
            request.setAttribute("models", boardService.글목록보기(sessionUser.getId()));
        }

        return "board/list";
    }

    @PostMapping("/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(saveDTO, sessionUser);

        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    // TODO : 글 수정 및 삭제
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board boardPS = boardService.글수정화면(id, sessionUser.getId());
        request.setAttribute("model", boardPS);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") Integer id, @Valid BoardRequest.UpdateDTO updateDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정(updateDTO, sessionUser.getId(), id);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

}
