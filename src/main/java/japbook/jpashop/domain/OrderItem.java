package japbook.jpashop.domain;

import jakarta.persistence.*;
import japbook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class OrderItem
{
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //XToOne은 default fetch가 EAGER인데 이는 n+1문제를 야기한다.
    //무조건 LAZY로 설정하자. (XToMany의 default fetch는 LAZY)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int price;//주문 가격
    private int count;//주문 수량
}
