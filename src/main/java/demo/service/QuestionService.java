package demo.service;

import demo.entity.Question;
import demo.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional
    public Question get(Long id) {
        return questionRepository.getById(id);
    }
    @Transactional
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Transactional
    public void delete(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Transactional
    public void update(Question question) {
        questionRepository.save(question);
    }

}
