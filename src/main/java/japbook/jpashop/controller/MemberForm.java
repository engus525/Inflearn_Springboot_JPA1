package japbook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm
{
    /*Member를 넘기지 않고 MemberForm을 만들어서 넘기는 이유
    *
    * controller에서 넘어올 때의 validation과
    * domain이 원하는 validation이 다를 수 있다.
    *
    * 서비스가 복잡해지면 코드가 매우 지저분해진다.
    * !!!Entity는 최대한!! 순수하게 유지하라. 안 그러면 나중에 피눈물을 흘릴 것이다..!!!!!
    *
    * + API를 만들 때는 절대 entity를 외부로 반환하지 말자.
    * 1. private가 노출됨
    * 2. api 스펙이 변해버림.
    * */

    @NotEmpty(message = "회원 이름은 필수입니다.")//build.gradle 수정
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
