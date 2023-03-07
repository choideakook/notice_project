package practice.notice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

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
    private String[] tag = new String[5];
    private LocalDateTime postDate;

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

    //-- create method --//
    // to Category
    public static Post createPost(Category category, String name, String desc, String... tag) {
        Post post = new Post();
        post.name = name;
        post.desc = desc;
        post.postDate = LocalDateTime.now();
        post.setCategory(category);
        for (int i = 0; i < tag.length; i++) {
            post.tag[i] = tag[i];
        }
        return post;
    }

    // to Series
    public static Post createPost(Series series, String name, String desc, String... tag) {
        Post post = new Post();
        post.name = name;
        post.desc = desc;
        post.postDate = LocalDateTime.now();
        post.setSeries(series);
        for (int i = 0; i < tag.length; i++) {
            post.tag[i] = tag[i];
        }
        return post;
    }
}
