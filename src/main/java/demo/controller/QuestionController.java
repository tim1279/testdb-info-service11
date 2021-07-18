package demo.controller;

import demo.dto.QuestionDTO;
import demo.dto.QuizDTO;
import demo.service.QuestionService;
import demo.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/questions")
@RestController
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> get(@PathVariable Long questionId) {
        var question = questionService.get(questionId);
        return ResponseEntity.ok(question);
    }

    @PostMapping("/")
    public ResponseEntity<QuestionDTO> create(@RequestBody QuestionDTO questionDTO) {
        var question = questionService.create(questionDTO);
        return ResponseEntity.ok(question);
    }
}
