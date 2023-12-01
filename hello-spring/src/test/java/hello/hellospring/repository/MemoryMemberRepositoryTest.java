package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // Test 케이스 하나 끝날 때마다 지워서 문제없게 해줌.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        // member을 저장
        repository.save(member);
        // 제대로 들어갔는지 확인 (get()으로 꺼내는게 좋은 방법은 아니지만 test같은 경우에는 괜찮음)
        Member result = repository.findById(member.getId()).get();
        /* 검증
        * 방법 1
        System.out.println("result = " + (result == member));
        * 방법 2 [ org.junit.jupiter 가 제공하는 Assertions]
        Assertions.assertEquals(member, result);
            // result 가 아니라 null로 했을 경우 빨간 불 뜸.
        * 방법 3 [ org.assertj 가 제공하는 Assertions ]
        Assertions.assertThat(member).isEqualTo(result);
          -> assertThat(member).isEqualTo(result); 로 변경 가능 [ static ]
         **/
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
