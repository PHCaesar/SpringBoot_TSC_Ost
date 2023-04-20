package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Member;
import at.tsc.sj23_tsc_ost.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    Team getTeamByCreationDate(LocalDate creationDate);
    Team getTeamByName(String name);
    List<Team> getTeamByMembersContaining(Member member);
    Team deleteTeamByName(String name);


}
