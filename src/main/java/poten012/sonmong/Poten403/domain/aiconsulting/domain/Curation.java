package poten012.sonmong.Poten403.domain.aiconsulting.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poten012.sonmong.Poten403.common.entity.superclass.TimeStamped;
import poten012.sonmong.Poten403.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"Curation\"")
public class Curation extends TimeStamped {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Curation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "gender")
    private boolean gender; // true가 여자 false가 남자

    @Column(name = "job_hobby")
    private String jobOrHobby; // 귀하의 직업이나 취미활동은 무엇인가요?

    @Column(name = "where_does_it_hurt")
    private String whereDoesItHurt; // 어디가 아프신가요?

    @Column(name = "position")
    private String position; // 위의 영역 중 어느 위치가 아프신가요?

    @Column(name = "level_of_pain")
    private int levelOfPain; // 아픈 정도를 선택해 주세요.

    @Column(name = "how_log")
    private LocalDateTime howLong; // 언제부터 아팠나요?

    @Column(name = "how_sick")
    private String howSick; // 어떻게 아프신가요?

    @Column(name = "what_activities")
    private String whatActivities; // 어떤 활동을 할 때 더 심해지나요?

    @Column(name = "put_strain_on_wrist")
    private boolean putStrainOnWrist; // 귀하의 직업이나 취미 활동이 손목에 부담을 주나요?

    @Column(name = "past_medical_history")
    private boolean pastMedicalHistory; // 과거에 손목을 다치거나 수술한 경험이 있나요?

    @Column(name = "different_past_medical_history")
    private boolean differentPastMedicalHistory; // 다른 관절이나 근육 관련 질환을 앓은 적이 있나요?


    @Builder
    public Curation(User user, LocalDateTime birthday, boolean gender, String jobOrHobby, String whereDoesItHurt,
                    String position, int levelOfPain, LocalDateTime howLong, String howSick, String whatActivities,
                    boolean putStrainOnWrist, boolean pastMedicalHistory, boolean differentPastMedicalHistory) {
        setUser(user);
        this.birthday = birthday;
        this.gender = gender;
        this.jobOrHobby = jobOrHobby;
        this.whereDoesItHurt = whereDoesItHurt;
        this.position = position;
        this.levelOfPain = levelOfPain;
        this.howLong = howLong;
        this.howSick = howSick;
        this.whatActivities = whatActivities;
        this.putStrainOnWrist = putStrainOnWrist;
        this.pastMedicalHistory = pastMedicalHistory;
        this.differentPastMedicalHistory = differentPastMedicalHistory;
    }

    private void setUser(User user) {
        if (Objects.nonNull(this.user)) {
            this.user.getCurations().remove(this);
        }
        this.user = user;
        user.getCurations().add(this);
    }
}
