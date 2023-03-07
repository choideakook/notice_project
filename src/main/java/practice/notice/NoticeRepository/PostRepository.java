package practice.notice.NoticeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.notice.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    //-- save --//
    public void save(Post post) {
        em.persist(post);
    }

    //-- find by id --//
    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    //-- find by name --//
    public List<Post> findByName(String name) {
        return em.createQuery("select p from Post p where p.name =: name", Post.class)
                .setParameter("name", name)
                .getResultList();
    }

    //-- find by tag --//
    /**
     * 내가 생각하는 tag 기능은 String[] 으로 구현이 안되는것같다.
     * tag 기능을 구현하려면 별도의 tag Entity 를 만들어야 사용 가능할 듯 하다.
     */
    public List<Post> findByTag(String tag) {
        return em.createQuery("select p from Post p where p.tag =: tag", Post.class)
                .setParameter("tag", tag)
                .getResultList();
    }

    //-- find all --//
    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    //-- delete --//
    public void delete(Post post) {
        em.remove(post);
    }
}
