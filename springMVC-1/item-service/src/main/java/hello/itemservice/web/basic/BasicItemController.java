package hello.itemservice.web.basic;

import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.Item;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
/* @RequiredArgsConstructor는 final이 붙은 것을 가지고
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    를 생성해주며, 생성자가 하나일 경우에는 @Autowired 생략 가능
*/
public class BasicItemController {

    private final ItemRepository itemRepository;

    // 아이템 목록 출력
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

}
