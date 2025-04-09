package shop.mtcoding.blog.reply;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
}
