package at.tsc.sj23_tsc_ost.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Table(name = "member")
public class Member extends AbstractPersistable<Long> {

    @Version
    private Integer Version;

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
    @Column(name = "middle_name",unique = true)
    private String middleName;

    @NotBlank
    @NotEmpty
    @Column(length = 8)
    private String password;

    @NotNull
    private LocalDate birthDate;
    private ArrayList<Sports> sports;

    @NotNull
    private Address address;

    private Team team;

    @Enumerated
    @NotEmpty
    private Role role;


}
