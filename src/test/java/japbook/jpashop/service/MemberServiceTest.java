package japbook.jpashop.service;

import jakarta.persistence.EntityManager;
import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.MemberRepository;
//import org.junit.jupiter.api.Test;  junit4,5 혼용 금지
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)//junit을 spring과 함께 실행할래!
@SpringBootTest//spring container 안에서 test 실행
@Transactional//rollback까지 해준다.
public class MemberServiceTest
{

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception
    {
        //given
        Member member = new Member();
        member.setName("kim");
        
        //when
        Long savedId = memberService.join(member);

        //then
        //em.flush();//insert 해준다.
        assertEquals(member,memberRepository.findOne(savedId));
    }
    
    @Test(expected = IllegalStateException.class)//try catch 생략 가능
    public void 중복_회원_예외() throws Exception
    {
        //given
        Member member1 = new Member();
        member1.setName("kim2");

        Member member2 = new Member();
        member2.setName("kim2");
        
        //when
        memberService.join(member1);
        memberService.join(member2);
        //try {memberService.join(member2);}
        //catch(IllegalStateException e) {return;}

        //then
        fail("예외가 발생해야 한다.");
    }
}