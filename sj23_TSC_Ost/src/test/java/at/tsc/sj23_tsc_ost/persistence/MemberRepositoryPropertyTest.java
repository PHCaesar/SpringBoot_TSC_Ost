package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.*;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryPropertyTest {

    @Autowired
    private MemberRepository memberRepository;


    @AfterEach @BeforeEach
    void Refresh() {
        memberRepository.deleteAll();
    }

    @Test
    void createMemberInsertsExactData() {
        Member m = MemberFactory.BuildMemberWithRandomParts();

        memberRepository.save(m);
        Assertions.assertEquals(m, memberRepository.findAll().get(0));
    }

    @Test
    @Transactional
    void deleteMemberByNamesDeletesCorrectly() {
        Member m = MemberFactory.BuildMemberWithRandomParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.count());
        memberRepository.deleteMemberByFirstNameAndLastNameAndMiddleName(m.getFirstName(), m.getLastName(), m.getMiddleName());
        Assertions.assertEquals(0, memberRepository.count());
    }

    @Test
    @Transactional
    void deleteMemberByAddressDeletesCorrectly() {
        Member m = MemberFactory.BuildMemberWithRandomParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.count());
        memberRepository.deleteMemberByAddress(m.getAddress());
        Assertions.assertEquals(0, memberRepository.count());
    }

    @Test
    void countBySportsContainingReturnsCorrectNumber() {
        Member m = MemberFactory.BuildMemberWithRandomParts();

        memberRepository.save(m);
        Integer count = memberRepository.countBySportsContaining(m.getSports().get(0));
        Assertions.assertEquals(count, 1);
    }

    @Test
    void getMemberByTeamReturnsCorrectMember() {
        Member m = MemberFactory.BuildMemberWithRandomParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.getMembersByTeam(m.getTeam()).stream().count());
        Assertions.assertEquals(m, memberRepository.getMembersByTeam(m.getTeam()).get(0));
    }

    @Test
    void getMembersBySportsContainingReturnsCorrectMembers() {
        List<SportsType> sportsOptions = Arrays.stream(SportsType.values()).toList();
        List<Sports> sports = sportsOptions.stream().map(x -> Sports.builder()
                .sportsType(x)
                .description(RandomString.make(20))
                .name(RandomString.make(8))
                .build()).toList();

        List<Member> memberList = IntStream.range(0, 10).mapToObj(x -> MemberFactory.MockMemberRandomized(sports,null,null,new Random())).toList();

        memberRepository.saveAll(memberList);
        Assertions.assertEquals(memberList, memberRepository.getMembersBySportsContaining(sports.get(0)));
    }

    @Test
    void getMembersByAddress_CountryCodeAndAddress_ZipCodeReturnsCorrectMembers(){

        List<Member> memberList = IntStream.range(0, 10).mapToObj(x -> MemberFactory.BuildMemberWithRandomParts()).toList();

        memberRepository.getMembersByAddress_CountryCodeAndAddress_ZipCodeAndAddress_StreetName(
                memberList.get(0).getAddress().getCountryCode()
                ,memberList.get(0).getAddress().getZipCode(),
                memberList.get(0).getAddress().getStreetName());

    }

    @Test
    @Transactional
    void deleteMembersBySportsContainingDeletesCorrectly(){
        List<SportsType> sportsOptions = Arrays.stream(SportsType.values()).toList();
        List<Sports> sports = sportsOptions.stream().map(x -> Sports.builder()
                .sportsType(x)
                .description(RandomString.make(20))
                .name(RandomString.make(8))
                .build()).toList();

        List<Member> memberList = IntStream.range(0, 10).mapToObj(x -> MemberFactory.MockMemberRandomized(sports,null,null,new Random())).toList();

        List<Member> nonAffectedMembers = IntStream.range(0, 10).mapToObj(x -> MemberFactory.MockMemberRandomized(null,null,null,new Random())).toList();

        Assertions.assertEquals(0,memberRepository.findAll().size());
        memberRepository.saveAll(memberList);
        memberRepository.saveAll(nonAffectedMembers);
        Assertions.assertEquals(20,memberRepository.findAll().size());
        memberRepository.deleteMembersBySportsContaining(sports.get(0));
        Assertions.assertEquals(10,memberRepository.findAll().size());

    }


}
