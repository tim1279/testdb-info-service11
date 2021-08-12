package demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import demo.dto.AnswerOptionDTO;
import demo.entity.AnswerOption;
import demo.repository.AnswerOptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AnswerOptionService {

    private final ObjectMapper mapper;
    private final AnswerOptionRepository answerOptionRepository;

    @Transactional
    public AnswerOptionDTO get(Long id) {
        var answerOption = answerOptionRepository.getById(id);
        var answerOptionDTO = updateMapper().convertValue(answerOption, AnswerOptionDTO.class);
        return answerOptionDTO;
    }

    @Transactional
    public AnswerOptionDTO create(AnswerOptionDTO answerOptionDTO) {
        var answerOption = updateMapper().convertValue(answerOptionDTO, AnswerOption.class);
        var answerOptionSaved = answerOptionRepository.save(answerOption);
        return updateMapper().convertValue(answerOptionSaved, AnswerOptionDTO.class);
    }

    public AnswerOptionDTO toDTO(AnswerOption answerOption) {
        return updateMapper().convertValue(answerOption, AnswerOptionDTO.class);
    }

    public ObjectMapper updateMapper() {
        return mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
