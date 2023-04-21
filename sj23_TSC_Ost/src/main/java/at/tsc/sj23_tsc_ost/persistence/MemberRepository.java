package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Address;
import at.tsc.sj23_tsc_ost.domain.Member;
import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Integer countBySportsContaining(Sports sport);
    Optional<Member> getMemberByAddress(Address address);
    List<Member> getMemberByTeam(Team team);
    Optional<Member> getMemberByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String MiddleName);
    List<Member> getMembersBySportsContaining(Sports sports);
    List<Member> getMembersByAddress_CountryCodeAndAddress_ZipCode(String countryCode, String zipCode);
    void deleteMemberByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String MiddleName);
    void deleteMembersBySportsContaining(Sports sport);
    void deleteMemberByAddress(Address address);
}
