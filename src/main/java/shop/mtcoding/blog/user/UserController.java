package shop.mtcoding.blog.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog._core.error.ex.Exception400;
import shop.mtcoding.blog._core.util.Resp;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // ViewResolver -> prefix = /templates/ -> suffix = .mustache
    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // update user_tb set password = ?, email = ? where id = ?
        User userPS = userService.회원정보수정(updateDTO, sessionUser.getId());

        // 세션 동기화
        session.setAttribute("sessionUser", userPS);

        return "redirect:/";
    }

    @GetMapping("/check-username-available/{username}")
    public @ResponseBody Resp<?> checkUsernameAvailable(@PathVariable("username") String username) {
        Map<String, Object> dto = userService.유저네임중복체크(username);
        return Resp.ok(dto);
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    // @Valid : 매개변수 내 Validation (Pattern, Size 등) 검사
    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO joinDTO, Errors errors) {

        /*  // 유효성 검사
        boolean r1 = Pattern.matches("^[a-zA-Z0-9]{2,20}$", joinDTO.getUsername());
        boolean r2 = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()])[a-zA-Z\\d!@#$%^&*()]{6,20}$", joinDTO.getPassword());
        boolean r3 = Pattern.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$", joinDTO.getEmail());

        if (!r1) throw new Exception400("유저네임은 2-20자이며, 특수문자,한글이 포함될 수 없습니다");
        if (!r2) throw new Exception400("패스워드는 4-20자이며, 특수문자,영어 대문자,소문자, 숫자가 포함되어야 하며, 공백이 있을 수 없습니다");
        if (!r3) throw new Exception400("이메일 형식에 맞게 적어주세요");
        */

        // pass if no error exists
        if (errors.hasErrors()) {
            // gather errors
            List<FieldError> fErrors = errors.getFieldErrors();

            // alert users (
            for (FieldError fieldError : fErrors) {
                throw new Exception400(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            }
        }

        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO loginDTO, HttpServletResponse response, Errors errors) {
        //System.out.println(loginDTO);

        if (errors.hasErrors()) {
            // gather errors
            List<FieldError> fErrors = errors.getFieldErrors();

            // alert users
            for (FieldError fieldError : fErrors) {
                throw new Exception400(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            }
        }

        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);

        if (loginDTO.getRememberMe() == null) {
            Cookie cookie = new Cookie("username", null);
            cookie.setMaxAge(0); // 즉시 만료
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("username", loginDTO.getUsername());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/login-form";
    }
}
