package practice.notice.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Post {

    @Id
    @GeneratedValue
    @Column(name ="post_id")
    private Long id;

    private String name;
    private String desc;
    private String tag;
    private LocalDateTime postDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "series_id")
    private Series series;
}
