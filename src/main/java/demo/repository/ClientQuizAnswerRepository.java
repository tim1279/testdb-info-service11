package demo.repository;

import demo.entity.ClientQuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientQuizAnswerRepository extends JpaRepository<ClientQuizAnswer,Long> {
}
