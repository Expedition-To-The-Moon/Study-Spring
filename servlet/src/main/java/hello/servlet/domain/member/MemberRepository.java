package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // static으로 했으므로 new가 많아도 하나만 생성됨(싱글톤으로 만들었기 때문에 static이 없어도 됨)
    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L;

    // 싱글톤으로 생성(톰켓 띄우는거 제외하고 스프링 사용을 안하고 진행할 것이기 때문)
    private static final MemberRepository instance = new MemberRepository();

    // 조회할 수 있게 생성
    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤으로 생성할 경우, private로 생성자를 막아서 아무나 생성하지 못하도록 해야 함
    private MemberRepository() {
    }

    // 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // 밖에서 새 리스트를 건드려도 store에 있는 value는 문제가 없도록 모든 값들을 꺼내서 새 리스트 생성
        return new ArrayList<>(store.values());
    }

    // 테스트할 때 사용 -> 저장소 전체 삭제함
    public void clearStore() {
        store.clear();
    }
}
