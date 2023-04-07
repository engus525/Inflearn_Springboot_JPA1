package japbook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable//어딘가에 내장이 될 수 있다.
@Getter
public class Address
{
    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode)
    {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    //jpa에서 reflection proxy등을 사용하기 위해서는 기본 생성자가 필요하다.
    //protected로 호출하지 못하게 선언하고, jpa 스펙상 만든 것임을 명시한다.
    protected Address()
    {

    }
}
