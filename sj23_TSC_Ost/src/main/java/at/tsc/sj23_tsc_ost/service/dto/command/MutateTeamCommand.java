package at.tsc.sj23_tsc_ost.service.dto.command;

import at.tsc.sj23_tsc_ost.domain.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Data
public class MutateTeamCommand {

    private Integer version;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime updateTimeStamp;
    private LocalDate creationDate;
    private String name;
    private List<Member> members;
    private String description;

}
