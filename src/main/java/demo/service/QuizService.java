package demo.service;

import demo.entity.Quiz;
import demo.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Transactional
    public Quiz get(Long id) {
        var quiz = quizRepository.getById(id);
        return quiz;
    }

    @Transactional
    public Quiz create(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Transactional
    public void delete(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Transactional
    public void update(Quiz quiz) {
        quizRepository.save(quiz);
    }

}
