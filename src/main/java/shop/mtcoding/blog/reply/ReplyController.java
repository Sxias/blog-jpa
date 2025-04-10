package shop.mtcoding.blog.reply;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO saveDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다");
        replyService.댓글저장(saveDTO, sessionUser);

        return "redirect:/board/"+saveDTO.getBoardId();
    }

//    @PostMapping("/reply/{id}/delete")
//    public String delete(@PathVariable("id") int replyId, @RequestParam int boardId, HttpSession session) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다");
//        replyService.댓글삭제(replyId);
//        return "redirect:/board/"+boardId;
//    }

    @PostMapping("/reply/{id}/delete")
    public String deletev2(@PathVariable("id") int id, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다");

        int boardId = replyService.댓글삭제2(id, sessionUser.getId());
        return "redirect:/board/"+boardId;
    }
}
