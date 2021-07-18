package demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.dto.QuestionDTO;
import demo.entity.Question;
import demo.entity.Quiz;
import demo.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ObjectMapper mapper;

    public QuestionDTO get(Long id) {
        var question = questionRepository.getById(id);
        var questionDTO = toDTO(question);
        return questionDTO;
    }

    public QuestionDTO create(QuestionDTO questionDTO) {
        var question = mapper.convertValue(questionDTO, Question.class);
        var questionSaved= questionRepository.save(question);
        return toDTO(questionSaved);
    }

    public QuestionDTO toDTO(Question question) {
        var questionDTO = mapper.convertValue(question, QuestionDTO.class);
        return questionDTO;
    }
}
