package poten012.sonmong.Poten403.domain.aiconsulting.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poten012.sonmong.Poten403.common.entity.superclass.TimeStamped;
import poten012.sonmong.Poten403.domain.user.domain.User;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"CurationAnswer\"")
public class CurationAnswer extends TimeStamped {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "curation_id")
    private Curation curation;

    @Column(name = "first_answer")
    private String firstAnswer;

    @Column(name = "second_answer")
    private String secondAnswer;

    @Column(name = "third_answer")
    private String thirdAnswer;

    @Column(name = "fourth_answer")
    private String fourthAnswer;

    @Builder
    public CurationAnswer(Curation curation,
                          String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer) {
        setCuration(curation);
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
    }
}
