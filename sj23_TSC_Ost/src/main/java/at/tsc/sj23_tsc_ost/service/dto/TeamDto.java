package at.tsc.sj23_tsc_ost.service.dto;

import at.tsc.sj23_tsc_ost.domain.Member;
import at.tsc.sj23_tsc_ost.domain.Team;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public record TeamDto(String name, String description, LocalDate creationDate, List<Member> memberList) {
    public TeamDto(Team team){
        this(team.getName(),team.getDescription(),team.getCreationDate(),team.getMembers());
        log.debug("TeamDto from Team : {} has been built !", team);
    }
}
