package japbook.jpashop.repository;

import jakarta.persistence.EntityManager;
import japbook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository
{
    private final EntityManager em;

    public void save(Item item)
    {
        if(item.getName() == null)
        {//DB에 등록이 되어야 name이 생긴다.
            em.persist(item);
        }
        else
        {
            em.merge(item);
        }
    }

    public Item findOne(Long id)
    {
        return em.find(Item.class,id);
    }

    public List<Item> findAll()
    {
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
