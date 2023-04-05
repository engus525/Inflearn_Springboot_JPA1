package japbook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class JpashopApplication {


	public static void main(String[] args)
	{
		Hello hello = new Hello();
		hello.setData("Hello");
		String data = hello.getData();
		System.out.println("data = " + data); //단축키 : soutv

		SpringApplication.run(JpashopApplication.class, args);
	}

}
