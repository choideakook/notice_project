package practice.notice.NoticeRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.notice.domain.Category;
import practice.notice.domain.Profile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    CategoryRepository categoryRepository;

    // profile 생성
    private Profile createProfile () {
        Profile profile = Profile.createProfile("notice", "desc", "facebook");
        profileRepository.save(profile);
        Profile findProfile = profileRepository.findOne(profile.getId());
        return findProfile;
    }

    // category 생성
    private Category createCategory(String name) {
        Profile profile = createProfile();
        Category category = Category.createCategory(profile, name);
        categoryRepository.save(category);

        return categoryRepository.findOne(category.getId());
    }

    @Test
    void save() {
        Profile profile = createProfile();
        Category category = Category.createCategory(profile, "java");
        categoryRepository.save(category);

        Category findCategory = categoryRepository.findOne(category.getId());

        assertThat(findCategory.getProfile()).isSameAs(profile);
        assertThat(findCategory.getName()).isEqualTo("java");
    }

    @Test
    void delete_findByName() {
        Category category1 = createCategory("java");
        Category category2 = createCategory("spring");

        List<Category> findAll = categoryRepository.findAll();
        assertThat(findAll.size()).isEqualTo(2);

        categoryRepository.delete(category1);

        findAll = categoryRepository.findAll();
        assertThat(findAll.size()).isEqualTo(1);

        List<Category> findCategory = categoryRepository.findByName("spring");
        Category category = findCategory.get(0);

        assertThat(findCategory.size()).isEqualTo(1);
        assertThat(category.getName()).isEqualTo("spring");
    }
}