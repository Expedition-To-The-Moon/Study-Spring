package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository // @Component가 포함되어 있으므로 컴포넌트 스캔의 대상이 됨
public class ItemRepository {

    // item의 id가 Long 타입이므로 Long
    /* 실제로는 HashMap, long 사용하면 안됨(동시성 문제 발생 가능성으로 인한 사용금지)
        싱글톤 객체에 멤버 변수는 여러 쓰레드에서 공유되기 때문에 조심해서 사용해야 하므로, 이 경우 ConcurrentHashMap, AtomicLong 사용
        지역 변수는 쓰레드마다 각각 따로 전용 공간이 할당 되기 때문에 HashMap, long 사용 가능 */
    // 주의! static 사용
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    // 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    // 조희
    public Item findById(Long id) {
        return store.get(id);
    }
    // 전체 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // 이렇게 감싸서 조회하면 실제 저장소에는 영향이 없음
    }
    // 수정
    /* 정석으로 구현하면, updateParam로 넘어오는 값 전용 객체(ex. ItmeParamDto)를 만들어서
        itemName, price, quantity 만 넣어놓는게 맞음.
        (findItem.set 으로 가져오는 값에 id는 가져오지 않기 때문) */
    public void update(Long itemId, Item updateParam) { // 간단하게 구현한 것
        Item findItem = findById(itemId); // 아이템을 찾음
        // 찾은 파라미터 정보를 받음
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
