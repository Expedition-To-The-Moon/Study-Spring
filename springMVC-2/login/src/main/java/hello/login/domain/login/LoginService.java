package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password) {
        /* 방식 1 */
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get(); // 없으면 예외가 터짐
//        if (member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }

        /* 방식 2 */
//        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
//        byLoginId.filter(m -> m.getPassword().equals(password))
//                .orElse(null);

        /* 방식 3 */
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
