package practice.notice.NoticeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.notice.domain.Profile;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository repository;

    @Test
    void sava() {
        Profile profile = Profile.createProfile("notice", "desc", "facebook", "instagram");
        repository.save(profile);

        Profile findProfile = repository.findOne(1L);

        assertThat(findProfile.getName()).isEqualTo("notice");
        assertThat(findProfile.getSns()[0]).isEqualTo("facebook");
        assertThat(findProfile.getSns()[1]).isEqualTo("instagram");
    }
}