package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Address;
import at.tsc.sj23_tsc_ost.domain.Member;
import at.tsc.sj23_tsc_ost.domain.Sports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Integer countBySportsContaining(Sports sport);
    Member getMemberByAddress(Address address);
    Member getMemberByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String MiddleName);
    List<Member> getMembersBySportsContaining(Sports sports);
    List<Member> getMembersByAddress_CountryCodeAndAddress_ZipCode(String countryCode, String zipCode);
    Member deleteMemberByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String MiddleName);
    Member deleteMembersBySportsContaining(Sports sport);

}
