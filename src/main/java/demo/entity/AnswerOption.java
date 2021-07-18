package demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer_option")
public class AnswerOption {
    @Id
    Long id;

    @Column
    String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("question")
    @JoinColumn(name = "question_id")
    private Question question;

}
