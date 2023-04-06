package japbook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member
{
    @Id @GeneratedValue
    @Column(name = "member_id")//DB col name
    private Long id;

    private String name;

    @Embedded//내장 type을 포함했다.(Embeddable이나 Embedded중 하나만 해도 된다. 보통 둘 다 사용)
    private Address address;

    @OneToMany(mappedBy = "member")//연관관계에서 주인이 !아님!
    private List<Order> orders = new ArrayList<>();


}
