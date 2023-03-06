package practice.notice.NoticeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.notice.domain.Series;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeriesRepository {

    private final EntityManager em;

    //-- save --//
    public void save(Series series) {
        em.persist(series);
    }

    //-- find by id --//
    public Series findOne(Long id) {
        return em.find(Series.class, id);
    }

    //-- find by name --//
    public List<Series> findByName(String name) {
        return em.createQuery("select s from Series s where s.name =: name", Series.class)
                .setParameter("name", name)
                .getResultList();
    }

    //-- find all --//
    public List<Series> findAll() {
        return em.createQuery("select s from Series s", Series.class)
                .getResultList();
    }

    //-- delete --//
    public void delete(Series series) {
        em.remove(series);
    }


}
