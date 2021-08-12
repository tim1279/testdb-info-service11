package demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String passport;
    private String snils;
    private String email;
    private String login;

}
