package demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.data.QuizAll;
import demo.data.ResponseStatus;
import demo.dto.ClientQuizAnswerDTO;
import demo.dto.QuizDTO;
import demo.entity.Client;
import demo.entity.Question;
import demo.entity.Quiz;
import demo.service.ClientService;
import demo.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/quizes")
@Slf4j
@RestController
public class QuizController {

    private final QuizService quizService;
    private final ClientService clientService;

    @GetMapping("/get/{quizId}/")
    public ResponseEntity<QuizDTO> get(@PathVariable Long quizId) {
        var quiz = quizService.get(quizId);
        var quizDTO = mapper.convertValue(quiz, QuizDTO.class);
        return ResponseEntity.ok(quizDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<QuizDTO> create(@RequestBody QuizDTO quizDTO) {
        var quiz = mapper.convertValue(quizDTO, Quiz.class);
        var quizCreated = quizService.create(quiz);
        var quizConvertedDTO = mapper.convertValue(quizCreated, QuizDTO.class);
        return ResponseEntity.ok(quizConvertedDTO);
    }

    @DeleteMapping("/delete/{quizId}/")
    public ResponseEntity<ResponseStatus> delete(Long quizId) {
        quizService.delete(quizId);
        return ResponseEntity.ok(new ResponseStatus("ok", null));
    }

    @PatchMapping("/update/{quizId}/")
    public ResponseEntity<QuizDTO> update(@RequestParam Long quizId, @RequestBody QuizDTO quizDTO) throws Exception {
        var quiz = quizService.get(quizId);
        if (quiz == null) {
            throw new ObjectNotFoundException(null, "quiz");
        }
        try {
            var quizDTOJson = mapper.writeValueAsString(quizDTO);
            var quizConverted = mapper.readerForUpdating(quiz).readValue(quizDTOJson, Quiz.class);
            quizService.update(quizConverted);
            var quizDTOUpdated = mapper.convertValue(quizConverted, QuizDTO.class);
            return ResponseEntity.ok(quizDTOUpdated);
        } catch (Exception e) {
            throw new Exception("error during converting quiz");
        }
    }

    @PostMapping("/createAll")
    public ResponseEntity<ResponseStatus> create(@RequestBody QuizAll quizAll) {
        log.info("started method  {}", quizAll.getClientId());

        var quizDTO = quizAll.getQuizDTO();
        var clientQuizAnswerDTO = quizAll.getClientQuizAnswers();
        var quiz = mapper.convertValue(quizDTO, Quiz.class);
        var questions = quizAll.getQuestions();
        log.info("clientId  {}", quizAll.getClientId());
        var clientDTO = clientService.getById(quizAll.getClientId());
        var client = mapper.convertValue(clientDTO, Client.class);
        quiz.setClient(client);
        var quizCreated = quizService.create(quiz);
        var quizCreatedToDTO = mapper.convertValue(quizCreated, QuizDTO.class);
        return ResponseEntity.ok(new ResponseStatus("ok", "quiz with all created"));
    }

    private final ObjectMapper mapper;
}
