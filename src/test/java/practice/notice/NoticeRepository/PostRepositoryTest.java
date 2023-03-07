package practice.notice.NoticeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice.notice.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired ProfileRepository profileRepository;
    @Autowired CategoryRepository categoryRepository;
    @Autowired SeriesRepository seriesRepository;
    @Autowired PostRepository postRepository;

    private Profile createProfile() {
        Profile profile = Profile.createProfile("notice", "desc", "");
        profileRepository.save(profile);
        Profile findProfile = profileRepository.findOne(profile.getId());
        return findProfile;
    }

    private Category createCategory() {
        Profile profile = createProfile();
        Category category = Category.createCategory(profile, "category");
        categoryRepository.save(category);
        Category findCategory = categoryRepository.findOne(category.getId());
        return findCategory;
    }

    private Series createSeries() {
        Category category = createCategory();
        Series series = Series.createSeries(category, "series");
        seriesRepository.save(series);
        Series findSeries = seriesRepository.findOne(series.getId());
        return findSeries;
    }


    @Test
    void save_to_series() {
        Series series = createSeries();
        Post post = Post.createPost(series, "post", "de", "tag1", "tag2");
        postRepository.save(post);

        Post findPost = postRepository.findOne(post.getId());

        assertThat(findPost.getSeries()).isSameAs(series);
        assertThat(findPost.getName()).isEqualTo("post");
        assertThat(findPost.getTags().size()).isEqualTo(2);
    }

    @Test
    void save_to_category() {
        Category category = createCategory();
        Post post = Post.createPost(category, "post", "de", "tag1", "tag2");
        postRepository.save(post);

        Post findPost = postRepository.findOne(post.getId());

        assertThat(findPost.getCategory()).isSameAs(category);
        assertThat(findPost.getName()).isEqualTo("post");
//        assertThat(findPost.getTag()).contains("tag1");
    }

    /**
     * tag 기능은 String[] 으로 구현이 안될 것 같다.
     * 별도의 Entity 를 만들어야 될듯 하다.
     */
    @Test
    void find_by_name() {
        Category category = createCategory();
        Post post1 = Post.createPost(category, "post1", "de", "tag1", "tag2");
        Post post2 = Post.createPost(category, "post2", "de", "tag3", "tag4");
        postRepository.save(post1);
        postRepository.save(post2);

        List<Post> findByName = postRepository.findByName("post1");
        Post findPost1 = findByName.get(0);

        assertThat(findPost1.getName()).isEqualTo("post1");


    }

    @Test
    void delete_findAll_tag() {
        Category category = createCategory();
        Post post1 = Post.createPost(category, "post1", "de", "tag1", "tag2");
        Post post2 = Post.createPost(category, "post2", "de", "tag3", "tag4");
        Post post3 = Post.createPost(category, "post3", "de", "tag5", "tag6");
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        List<Post> findAll = postRepository.findAll();
        assertThat(findAll.size()).isEqualTo(3);


        Post findByTag = findByTag(findAll, "tag3");
        assertThat(findByTag.getName()).isEqualTo("post2");

//        postRepository.delete(post1);
//
//        findAll = postRepository.findAll();
//        assertThat(findAll.size()).isEqualTo(2);
    }

    private Post findByTag(List<Post> findAll, String tag) {
        for (Post post : findAll) {
            for (Tag postTag : post.getTags()) {
                if (postTag.getTag().contains(tag))
                    return post;
            }
        }
        return null;
    }

    @Test
    void name() {
        List<String> list = new ArrayList<>();
        list.add("ss");

        System.out.println(list.contains("ss"));
    }
}