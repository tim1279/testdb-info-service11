package demo.repository;

import demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository <Question, Long> {
}