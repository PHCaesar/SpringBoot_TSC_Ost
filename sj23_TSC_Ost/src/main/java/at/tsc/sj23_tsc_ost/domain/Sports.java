package at.tsc.sj23_tsc_ost.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "sports")
public class Sports extends AbstractPersistable<Long> {

    @Version
    private Integer version;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;

    @NotEmpty
    @NotBlank
    @Column(name = "sports_name")
    private String name;

    @NotEmpty
    @NotBlank
    @Column(name = "sports_description")
    private String description;

    @Enumerated
    @NotNull
    private SportsType sportsType;


}
