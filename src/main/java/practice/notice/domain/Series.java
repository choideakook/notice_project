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
public class Series {

    //-- field --//
    @Id
    @GeneratedValue
    @Column(name ="series_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "series")
    private List<Post> posts = new ArrayList<>();

    //-- 연관관계 편의 method --//
    private void setCategory(Category category) {
        this.category = category;
        category.getSeries().add(this);
    }

    //-- create method --//
    public static Series createSeries(Category category, String name) {
        Series series = new Series();
        series.name = name;
        series.setCategory(category);
        return series;
    }
}
