package practice.notice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Post {

    //-- field --//
    @Id
    @GeneratedValue
    @Column(name ="post_id")
    private Long id;

    private String name;
    private String desc;
    private LocalDateTime postDate;

    @OneToMany(mappedBy = "post", cascade = ALL)
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    //-- 연관관계 편의 method --//
    private void setCategory(Category category) {
        this.category = category;
        category.getPosts().add(this);
    }

    private void setSeries(Series series) {
        this.series = series;
        series.getPosts().add(this);
    }

    private void setTags(Tag tags) {
        this.tags.add(tags);
        tags.cascadePost(this);
    }

    //-- create method --//
    // to Category
    public static Post createPost(Category category, String name, String desc, String... tags) {
        Post post = new Post();
        post.name = name;
        post.desc = desc;
        post.postDate = LocalDateTime.now();
        post.setCategory(category);

        for (String tagging : tags) {
            Tag tag = Tag.createTag(tagging);
            post.setTags(tag);
        }
        return post;
    }

    // to Series
    public static Post createPost(Series series, String name, String desc, String... tags) {
        Post post = new Post();
        post.name = name;
        post.desc = desc;
        post.postDate = LocalDateTime.now();
        post.setSeries(series);

        for (String tagging : tags) {
            Tag tag = Tag.createTag(tagging);
            post.setTags(tag);
        }
        return post;
    }
}
