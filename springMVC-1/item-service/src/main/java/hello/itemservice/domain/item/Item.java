package hello.itemservice.domain.item;

import lombok.Data;

@Data // 예제이므로 그냥 사용...
/*
    @Data는 주의해서 사용해야됨.(핵심 도메인에 사용하기에는 예측하지 못한 동작이 생길 수 있으므로 위험함)
    되도록이면 @Getter, @Setter 이렇게 사용하는게 나음.
    데이터를 이동시킬때 사용하는 DTO에는 괜찮음(확인은 해보고 사용해야함)
*/
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // int는 null이 들어갈 수 없으므로 Integer을 사용함
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
