package hello.itemservice.web.basic;

import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.Item;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        /* PathVariable로 넘어온 상품ID로 상품을 조회하여 model에 담아두고, 뷰 템플릿을 호출함
           스트링 부트 3.2 부터는 @PathVariable에 "itemId"를 추가해야 정상 작동함 */
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /** 상품 등록 처리 방식 1 - @RequestParam 을 사용하는 경우 */
//    @PostMapping("/add")  // POST 인 "/add" 가 한 개 이상이므로 오류 발생하므로 주석처리함
    public String addItemV1(@RequestParam("itemName") String itemName,
                       @RequestParam("price") int price, // int나 Integer 상관없음
                       @RequestParam("quantity") Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        // 저장하고 저장된 결과물을 model에 넣음
        model.addAttribute("item", item);

        return "basic/item";
    }

    /** 상품 등록 처리 방식 2 - @ModelAttribute 를 사용하는 경우
        @ModelAttribute("name") 역할
        1) item.set~ 들을 자동으로 생성해줌
        2) 결과물을 이름이 "name"인 model에 넣어줌
            => model.addAttribute("item", item); 자동 생성되므로 생략 가능
    */
//    @PostMapping("/add")  // POST 인 "/add" 가 한 개 이상이므로 오류 발생하므로 주석처리함
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
        return "basic/item";
    }

    /** 상품 등록 처리 방식 3 - @ModelAttribute의 이름을 생략하는 경우
        ModelAttribute 의 이름을 생략하면 모델에 저장될 때 클래스명의 첫글자만 소문자로 변경해서 등록됨
        ex. Item -> item
    */
//    @PostMapping("/add")  // POST 인 "/add" 가 한 개 이상이므로 오류 발생하므로 주석처리함
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /** 상품 등록 처리 방식 4 - @ModelAttribute를 생략하는 경우
        이 경우에도 모델에 저장될 때 클래스명의 첫글자만 소문자로 변경해서 등록됨
        ex. Item -> item
    */
    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /* 상품 수정 */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
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
