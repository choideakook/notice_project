package practice.notice.NoticeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.notice.domain.Profile;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProfileRepository {

    private final EntityManager em;

    //-- save --//
    public void save(Profile profile) {
        em.persist(profile);
    }

    //-- find by id --//
    public Profile findOne(Long id) {
        return em.find(Profile.class, id);
    }
}
