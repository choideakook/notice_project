package practice.notice.NoticeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.notice.domain.Category;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    //-- save --//
    public void save(Category category) {
        em.persist(category);
    }

    //-- find by id --//
    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    //-- find by name --//
    public List<Category> findByName(String name) {
        return em.createQuery("select c from Category c where c.name =: name", Category.class)
                .setParameter("name", name)
                .getResultList();
    }

    //-- find All --//
    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    //-- update name --//
}
