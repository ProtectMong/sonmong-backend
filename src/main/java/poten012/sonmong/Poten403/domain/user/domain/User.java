package poten012.sonmong.Poten403.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Curation> curations = new ArrayList<>();

    @Builder
    public User(Long userId, String name, String phoneNumber) {
        this.id = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
