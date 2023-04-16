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
    * */

    @NotEmpty(message = "회원 이름은 필수입니다.")//build.gradle 수정
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
