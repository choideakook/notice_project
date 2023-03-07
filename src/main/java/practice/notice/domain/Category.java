package practice.notice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Category {

    //-- field --//
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "category")
    private List<Series> series = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    //-- 연관관계 편의 method --//
    private void setProfile(Profile profile) {
        this.profile = profile;
        profile.getCategories().add(this);
    }

    //-- create method --//
    public static Category createCategory(Profile profile, String name) {
        Category category = new Category();
        category.setProfile(profile);
        category.name = name;
        return category;
    }



}
