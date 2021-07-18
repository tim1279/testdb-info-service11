package demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.dto.ClientDTO;
import demo.dto.QuizDTO;
import demo.entity.Client;
import demo.entity.Quiz;
import demo.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final ObjectMapper mapper;

    public QuizDTO get(Long id) {
        var quiz = quizRepository.getById(id);
        var quizDTO = mapper.convertValue(quiz, QuizDTO.class);
        return quizDTO;
    }

    public QuizDTO create(QuizDTO quizDTO) {
        var quiz = mapper.convertValue(quizDTO, Quiz.class);
        var quizSaved = quizRepository.save(quiz);
        return toDTO(quizSaved);
    }

    public QuizDTO toDTO(Quiz quiz) {
        var quizDTO = mapper.convertValue(quiz, QuizDTO.class);
        return quizDTO;
    }
}
