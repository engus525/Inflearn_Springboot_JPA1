package japbook.jpashop.repository;

import jakarta.persistence.EntityManager;
import japbook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Spring Bean에 등록(Component Scan)
@RequiredArgsConstructor
public class MemberRepository
{
//@PersistenceContext//JPA 표준 annotation
//    em은 autowired로 injection이 안 되고 위의 annotation으로 해야하는데,
//    spring boot에서 가능케함(근데 바뀔 예정)
    private final EntityManager em;

    public void save(Member member)
    {
        em.persist(member);
    }

    public Member findOne(Long id)
    {
        return em.find(Member.class,id);
    }

    public List<Member> findAll()
    {
        //(JPQL,return type)  jpql에서 from의 대상은 entity
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name)
    {
        //(JPQL,return type)
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
