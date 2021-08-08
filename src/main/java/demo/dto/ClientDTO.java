package demo.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ClientDTO {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    private String passport;
    private String snils;
    @Email
    private String email;
    private String login;

}
