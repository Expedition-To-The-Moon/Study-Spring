package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L;

    // 저장
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member); // member.getId()로 찾아서 저장소에 저장
        return member;
    }

    // 고유번호로 조회
    public Member findById(Long id) {
        return store.get(id);
    }

    // 로그인 아이디로 조회
    public Optional<Member> findByLoginId(String loginId) {
        /* 방법 1 */
//        List<Member> all = findAll();
//        for (Member m : all) {
//            if (m.getLoginId().equals(loginId)) {
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty(); // 값이 null일 경우

        /* 방법 2 */
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId)) // 이 조건을 만족해야 다음단계로 넘어감
                .findFirst(); // 제일 먼저 나오는 것을 반환
    }

    // 전체 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 초기화
    public void clearStore() {
        store.clear();
    }

}
