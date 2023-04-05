package japbook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController
{

    @GetMapping("hello")//url 뒤에 붙는 내용(http://localhost:8080/hello)
    public String hello(Model model)
    {
        //data라는 key의 값을 hello!!!
        model.addAttribute("data","hello!!!");
        //resources/templates의 html과 mapping된다.
        //spring boot thymeleaf가 찾아준다.
        return "hello";
    }
}
