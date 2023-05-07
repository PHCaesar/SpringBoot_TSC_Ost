package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Member;
import at.tsc.sj23_tsc_ost.domain.Team;
import at.tsc.sj23_tsc_ost.service.dto.TeamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    List<TeamDto> getTeamsByCreationDate(LocalDate creationDate);
    Optional<TeamDto> getTeamByName(String name);
    Optional<TeamDto> getTeamByMembersContaining(Member member);
    void deleteTeamByName(String name);
}
