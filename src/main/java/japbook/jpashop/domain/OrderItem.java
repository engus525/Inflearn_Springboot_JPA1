package japbook.jpashop.domain;

import jakarta.persistence.*;
import japbook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)//생성 메서드가 있으므로 다른 곳에서 생성하지 않도록
public class  OrderItem
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

    private int orderPrice;//주문 가격
    private int count;//주문 수량

    /****생성 메서드****/
    public static OrderItem createOrderItem(Item item,int orderPrice,int count)
    {
        //왜 다시 생성하는가? -> 할인 등을 받을 수 있기때문에 따로 생성하는게 맞다.
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    /***비즈니스 로직 ***/
    public void cancle()
    {
        getItem().addStock(count);
    }

    /****조회 로직***/
    public int getTotalPrice()
    {
        return getOrderPrice() * getCount();
    }

}
