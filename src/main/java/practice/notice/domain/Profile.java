package practice.notice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Profile {

    //-- field --//
    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private String name;
    private String desc;
    // GIT, VELOG, INSTAGRAM, YOUTUBE //
    private String[] sns = new String[4];

    @OneToMany(mappedBy = "profile")
    private List<Category> categories = new ArrayList<>();

    //-- create method --//
    public static Profile createProfile(String name, String desc, String... sns) {
        Profile profile = new Profile();
        profile.name = name;
        profile.desc = desc;
        for (int i = 0; i < sns.length; i++) {
            profile.sns[i] = sns[i];
        }
        return profile;
    }
}
