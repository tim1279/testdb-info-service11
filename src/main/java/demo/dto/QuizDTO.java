package demo.dto;

import demo.entity.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class QuizDTO {

    private String name;
    private String description;
    private Boolean active;
    private Set<QuestionDTO> questions;

}
