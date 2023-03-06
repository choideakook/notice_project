package practice.notice.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Profile {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private String name;
    private String desc;
    private String sns;

    @OneToMany(mappedBy = "profile")
    private List<Category> categories = new ArrayList<>();
}
