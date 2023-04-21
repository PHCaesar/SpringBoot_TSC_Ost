package at.tsc.sj23_tsc_ost.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Slf4j
@Entity
@Table(name = "member")
public class Member extends AbstractPersistable<Long> {

    @Version
    private Integer version;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;

    @NotBlank
    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @NotEmpty
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank
    @NotEmpty
    @Column(length = 8)
    private String password;

    @NotNull
    private LocalDate birthDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Sports> sports;

    @NotNull
    @Embedded
    private Address address;

    @NotEmpty
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Team team;

    @Enumerated
    @NotEmpty
    private Role role;


}
