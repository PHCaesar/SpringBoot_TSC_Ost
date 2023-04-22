package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.*;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    @AfterEach
    void Refresh() {
        memberRepository.deleteAll();
    }

    @Test
    void createMemberInsertsExactData() {
        Member m = BuildMemberWithParts();

        memberRepository.save(m);
        Assertions.assertEquals(m, memberRepository.findAll().get(0));
    }

    @Test
    @Transactional
    void deleteMemberByNamesDeletesCorrectly() {
        Member m = BuildMemberWithParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.count());
        memberRepository.deleteMemberByFirstNameAndLastNameAndMiddleName(m.getFirstName(), m.getLastName(), m.getMiddleName());
        Assertions.assertEquals(0, memberRepository.count());
    }

    @Test
    @Transactional
    void deleteMemberByAddressDeletesCorrectly() {
        Member m = BuildMemberWithParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.count());
        memberRepository.deleteMemberByAddress(m.getAddress());
        Assertions.assertEquals(0, memberRepository.count());
    }

    @Test
    void countBySportsContainingReturnsCorrectNumber() {
        Member m = BuildMemberWithParts();

        memberRepository.save(m);
        Integer count = memberRepository.countBySportsContaining(m.getSports().get(0));
        Assertions.assertEquals(count, 1);
    }

    @Test
    void getMemberByTeamReturnsCorrectMember() {
        Member m = BuildMemberWithParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.getMembersByTeam(m.getTeam()).stream().count());
        Assertions.assertEquals(m, memberRepository.getMembersByTeam(m.getTeam()).get(0));
    }

    @Test
    void getMembersBySportsContainingReturnsCorrectMembers() {
        List<Sports> sports = new ArrayList<Sports>();
        sports.add(Sports.builder()
                .name("Handball")
                .sportsType(SportsType.CONTACT)
                .description("Sports with ball in hand")
                .build());

        Member m =MockMember(sports,null,null);
        memberRepository.save(m);
        Assertions.assertEquals(m, memberRepository.getMembersBySportsContaining(sports.get(0)).get(0));
    }

    @Test
    void getMembersByAddress_CountryCodeAndAddress_ZipCodeReturnsCorrectMembers(){

        List<Member> memberList = IntStream.range(0, 10).mapToObj(x -> BuildMemberWithParts()).toList();

        memberRepository.getMembersByAddress_CountryCodeAndAddress_ZipCodeAndAddress_StreetName(
                memberList.get(0).getAddress().getCountryCode()
                ,memberList.get(0).getAddress().getZipCode(),
                memberList.get(0).getAddress().getStreetName());

    }

    @Test
    @Transactional
    void deleteMembersBySportsContainingDeletesCorrectly(){
        List<Sports> sports = new ArrayList<Sports>();
        sports.add(Sports.builder()
                .name("Handball")
                .sportsType(SportsType.CONTACT)
                .description("Sports with ball in hand")
                .build());


        List<Member> memberList = IntStream.range(0, 10).mapToObj(x -> MockMember(sports,null,null)).toList();

        List<Member> nonAffectedMembers = IntStream.range(0, 10).mapToObj(x -> MockMember(null,null,null)).toList();

        Assertions.assertEquals(0,memberRepository.findAll().size());
        memberRepository.saveAll(memberList);
        memberRepository.saveAll(nonAffectedMembers);
        Assertions.assertEquals(20,memberRepository.findAll().size());
        memberRepository.deleteMembersBySportsContaining(sports.get(0));
        Assertions.assertEquals(10,memberRepository.findAll().size());

    }

    public Member BuildMemberWithParts() {
        List<Sports> sports = new ArrayList<Sports>();
        sports.add(Sports.builder()
                .name("Handball")
                .sportsType(SportsType.CONTACT)
                .description("Sports with ball in hand")
                .build());

        Team team = Team.builder()
                .name("A Team")
                .description("This team is insanely good")
                .creationDate(LocalDate.now().minusDays(100))
                .build();

        return MockMember(sports, Role.MEMBER, team);
    }

    public Member MockMember(List<Sports> sports, Role role, Team team){

        return Member.builder()
                .firstName("Philipp")
                .middleName("Smth")
                .lastName("Cserich")
                .sports(sports)
                .role(role)
                .team(team)
                .birthDate(LocalDate.now().minusDays(100))
                .address(Address.builder()
                        .countryCode("AT")
                        .streetName("Meißauergasse")
                        .zipCode("1220")
                        .build())
                .build();
    }
}
