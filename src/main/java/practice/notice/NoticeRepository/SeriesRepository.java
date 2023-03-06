package practice.notice.NoticeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.notice.domain.Series;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SeriesRepository {

    private final EntityManager em;

    public void save(Series series) {
        em.persist(series);
    }
}
