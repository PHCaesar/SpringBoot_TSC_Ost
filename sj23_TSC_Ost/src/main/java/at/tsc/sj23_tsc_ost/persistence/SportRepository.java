package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.SportsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SportRepository extends JpaRepository<Sports,Long> {

    Optional<Sports> findSportsByName(String name);
    List<Sports> findSportsBySportsType(SportsType type);

}
