package shop.mtcoding.blog.reply;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String save(@Valid ReplyRequest.SaveDTO saveDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글저장(saveDTO, sessionUser);
        return "redirect:/board/" + saveDTO.getBoardId();
    }

//    @PostMapping("/reply/{id}/delete")
//    public String delete(@PathVariable("id") int replyId, @RequestParam int boardId, HttpSession session) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다");
//        replyService.댓글삭제(replyId);
//        return "redirect:/board/"+boardId;
//    }

    @PostMapping("/reply/{id}/delete")
    public String deleteV2(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int boardId = replyService.댓글삭제2(id, sessionUser.getId());
        return "redirect:/board/" + boardId;
    }
}
