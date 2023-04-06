package japbook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery
{
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private String id;

    @OneToOne(mappedBy = "delivery")//1대1 관계에서 주인은 누가해도 상관없으나, 보통 외래 키 기준으로 설정
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)//default인 ORDINAL은 숫자로 표현된다. 쓰면 큰일!
    private DeliveryStatus status;//READY,COMP
}
