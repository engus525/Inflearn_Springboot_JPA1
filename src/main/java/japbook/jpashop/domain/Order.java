package japbook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order
{
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //foreign key에서 가까운 table이 일반적으로 연관관계의 주인이 된다.
    @ManyToOne
    @JoinColumn(name = "member_id")//foreign key
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;//주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문 상태 [ORDER,CANCLE]
}
