package at.tsc.sj23_tsc_ost.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "sports")
public class Team extends AbstractPersistable<Long> {

    @Version
    private Integer version;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;

    @CreatedDate
    @NotNull
    private LocalDate creationDate; // Not sure if this is required if we can just use creationTimeStamp

    @NotEmpty
    @NotBlank
    @Column(name = "team_name", unique = true)
    private String name;

    @NotNull
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Member> members;

    @NotBlank
    @NotEmpty
    private String description;

}
