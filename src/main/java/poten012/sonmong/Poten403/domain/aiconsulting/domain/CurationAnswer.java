package poten012.sonmong.Poten403.domain.aiconsulting.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(max=500)
    private String firstAnswer;

    @Column(name = "second_answer")
    @Size(max=500)
    private String secondAnswer;

    @Column(name = "third_answer")
    @Size(max=500)
    private String thirdAnswer;

    @Column(name = "fourth_answer")
    @Size(max=500)
    private String fourthAnswer;

    @Column(name = "source")
    @Size(max=500)
    private String source;

    @Builder
    public CurationAnswer(Curation curation,
                          String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer, String source) {
        setCuration(curation);
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.source = source;
    }
}
