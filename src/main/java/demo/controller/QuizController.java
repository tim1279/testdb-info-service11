package demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.data.QuizAll;
import demo.data.ResponseStatus;
import demo.dto.QuizDTO;
import demo.entity.Quiz;
import demo.service.ClientService;
import demo.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/quizes")
@Slf4j
@RestController
public class QuizController {

    private final QuizService quizService;
    private final ClientService clientService;
    private final ObjectMapper mapper;


    @GetMapping("/get/{quizId}/")
    public ResponseEntity<QuizDTO> get(@PathVariable Long quizId) {
        var quiz = quizService.get(quizId);
        var quizDTO = mapper.convertValue(quiz, QuizDTO.class);
        return ResponseEntity.ok(quizDTO);
    }

    @DeleteMapping("/{quizId}/")
    public ResponseEntity<ResponseStatus> delete(Long quizId) {
        quizService.delete(quizId);
        return ResponseEntity.ok(new ResponseStatus("ok", null));
    }

    @PatchMapping("/{quizId}/")
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

    @PostMapping("/")
    public ResponseEntity<QuizDTO> create(@RequestParam Long clientId, @RequestBody QuizDTO quizDTO) {
        var client = clientService.getById(clientId);
        if (client == null) {
            throw new ObjectNotFoundException(null, "Client was not found");
        }
        var quiz = mapper.convertValue(quizDTO, Quiz.class);
        quiz.setClient(client);
        var quizSaved = quizService.create(quiz);
        var quizDTOConverted = mapper.convertValue(quizSaved, QuizDTO.class);
        return ResponseEntity.ok(quizDTOConverted);
    }
}

