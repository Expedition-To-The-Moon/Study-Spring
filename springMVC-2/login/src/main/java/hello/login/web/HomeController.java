package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    //    @GetMapping("/")
    public String home() {
        return "home";
    }

    // required = false 를 넣어줘야함
    // why? 로그인 안한 사용자도 접근가능해야 하므로
    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            return "home";
        }

        //로그인
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            // 없을 경우
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0); // 쿠키의 종료 날짜를 0으로 지정 -> 쿠키가 즉시 만료됨
        response.addCookie(cookie);
    }
}