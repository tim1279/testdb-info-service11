package demo.data;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
@AllArgsConstructor
public class ResponseStatus {

    private String status;
    private String message;

}
