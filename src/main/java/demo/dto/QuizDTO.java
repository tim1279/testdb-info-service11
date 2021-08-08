package demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Instant;
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class QuizDTO {

    private Long id;
    private String name;
    private Instant dateStart;
    private Instant dateEnd;
    private String description;
    private Boolean active;

}
