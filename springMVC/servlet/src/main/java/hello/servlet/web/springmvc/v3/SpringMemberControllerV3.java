package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // GET으로 제한
//    @RequestMapping(value = "/new-form", method = RequestMethod.GET) // 이전 방법
    @GetMapping("/new-form") // 최신 방법
    public String newForm() {
        return "new-form";
    }

    // POST로 제한
//    @RequestMapping(value = "/save", method = RequestMethod.POST) // 이전 방법
    @PostMapping("/save") // 최신 방법
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    // GET으로 제한
//    @RequestMapping(method = RequestMethod.GET) // 이전 방법
    @GetMapping // 최신 방법
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}
