package demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Getter
    @Setter
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client")
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    @Getter
    @Setter
    private Instant dateStart;

    @Column
    @Getter
    @Setter
    private Instant dateEnd;

    @Column
    private String description;

    @Column
    private Boolean active;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Set<Question> questions;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Set<ClientQuizAnswer> clientQuizAnswers;

}
