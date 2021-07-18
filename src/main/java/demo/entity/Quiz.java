package demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client")
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    private Instant dateStart;

    @Column
    private Instant dateEnd;

    @Column
    private String description;

    @Column
    private Boolean active;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Set<Question> questions;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Set<ClientQuizAnswer> clientQuizAnswers;

}
