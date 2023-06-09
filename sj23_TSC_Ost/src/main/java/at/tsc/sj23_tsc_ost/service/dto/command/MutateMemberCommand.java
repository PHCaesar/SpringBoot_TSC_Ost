package at.tsc.sj23_tsc_ost.service.dto.command;

import at.tsc.sj23_tsc_ost.domain.Address;
import at.tsc.sj23_tsc_ost.domain.Role;
import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.Team;
import at.tsc.sj23_tsc_ost.service.dto.MemberDto;
import at.tsc.sj23_tsc_ost.service.dto.TeamDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
public class MutateMemberCommand {

    private LocalDateTime updateTimeStamp;
    private String mutateFirstname;
    private String mutateLastname;
    private String mutateMiddleName;
    private String mutatePassword;
    private LocalDate mutateBirthDate;
    private List<Sports> sports;
    private Address address;
    private Team team;
    private Role role;

}
