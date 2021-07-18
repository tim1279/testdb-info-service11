package demo.controller;

import demo.dto.ClientDTO;
import demo.dto.QuizDTO;
import demo.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/quizes")
@RestController
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/{clientId}")
    public ResponseEntity<QuizDTO> get(@PathVariable Long quizId) {
        var quiz = quizService.get(quizId);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/")
    public ResponseEntity<QuizDTO> create(@RequestBody QuizDTO quizDTO) {
        var quiz = quizService.create(quizDTO);
        return ResponseEntity.ok(quiz);
    }
}
