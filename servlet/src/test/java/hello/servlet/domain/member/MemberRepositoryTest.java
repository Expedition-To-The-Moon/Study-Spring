package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    // 싱글톤이기 때문에 new MemberRepository(); 안됨 ->  MemberRepository.getInstance(); 을 사용
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트가 끝나면 초기화 해줌
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    // 저장
    @Test
    void save() {
        // given : 주어졌을 때
        Member member = new Member("hello", 20);

        // when : 실행했을 때
        Member savedMember = memberRepository.save(member);

        // then : 결과
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    // 모두 조회
    @Test
    void findAll() {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}