package demo.data;

import demo.dto.ClientDTO;
import demo.dto.ClientQuizAnswerDTO;
import demo.dto.QuestionDTO;
import demo.dto.QuizDTO;
import demo.entity.ClientQuizAnswer;
import demo.entity.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class QuizAll {

    Long clientId;
    QuizDTO quizDTO;
    private Set<QuestionDTO> questions;
    private Set<ClientQuizAnswerDTO> clientQuizAnswers;

}
