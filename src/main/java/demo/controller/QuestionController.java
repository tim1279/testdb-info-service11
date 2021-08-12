package demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.data.ResponseStatus;
import demo.dto.QuestionDTO;
import demo.entity.Question;
import demo.service.QuestionService;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RequestMapping("/questions")
@RestController
public class QuestionController {

    private final ObjectMapper mapper;

    private final QuestionService questionService;

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> get(@PathVariable Long questionId) {
        var question = questionService.get(questionId);
        var questionDTO = mapper.convertValue(question, QuestionDTO.class);
        return ResponseEntity.ok(questionDTO);
    }

    @PostMapping("/")
    public ResponseEntity<QuestionDTO> create(@RequestBody QuestionDTO questionDTO) {
        var questionConverted = mapper.convertValue(questionDTO, Question.class);
        var questionCreated = questionService.create(questionConverted);
        var questionDTOConverted = mapper.convertValue(questionCreated, QuestionDTO.class);
        return ResponseEntity.ok(questionDTOConverted);
    }

    @PatchMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> update(@RequestParam Long questionId, @RequestBody QuestionDTO questionDTO) throws IOException {
        var question = questionService.get(questionId);
        if (question == null) {
            throw new ObjectNotFoundException("fail", "not found");
        }
        if (questionDTO.getText() != null) {
            question.setText(questionDTO.getText());
        }
        if (questionDTO.getType() != null) {
            question.setType(questionDTO.getType());
        }
        var questionDTOUpdated = questionService.update(question);
        var questionUpdated = mapper.convertValue(questionDTOUpdated, QuestionDTO.class);
        return ResponseEntity.ok(questionUpdated);
    }

    @DeleteMapping
    public ResponseEntity<ResponseStatus> delete(Long id) {
        questionService.delete(id);
        return ResponseEntity.ok(new ResponseStatus("ok", "deleted"));
    }
}
