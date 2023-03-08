package practice.notice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String tag;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    //-- 연관관계 편의 method --//
    public void cascadePost(Post post) {
        this.post = post;
    }

    //-- create method --//
    public static Tag createTag(String tagging) {
        Tag tag = new Tag();
        tag.tag = tagging;
        return tag;
    }
}
