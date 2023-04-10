package japbook.jpashop.service;

import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service//spring bean 등록
@Transactional(readOnly = true)//조회 성능 향상
@RequiredArgsConstructor//lombok에서 final이 있는 field의 생성자를 만들어준다.
public class MemberService
{
    private final MemberRepository memberRepository;//변경할 일 없으므로 final

    //@Autowired 생성자가 하나이면 자동 autowired
//    public MemberService(MemberRepository memberRepository)
//    {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional
    public Long join(Member member)
    {
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member)
    {
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty())
        {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findAll()
    {
        return memberRepository.findAll();
    }

    public Member findById(Member member)
    {
        return memberRepository.findOne(member.getId());
//        return memberRepository.findOne(memberId);
    }
}
