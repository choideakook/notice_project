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

    //-- find all --//

    //-- delete --//

}
