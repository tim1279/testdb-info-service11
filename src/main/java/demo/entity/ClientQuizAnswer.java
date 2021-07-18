package demo.entity;

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
@Table(name = "client_quiz_answer")
public class ClientQuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client_quiz_answer")
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client_quiz_answer")
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client_quiz_answer")
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}
