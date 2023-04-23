package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.*;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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


    @AfterEach @BeforeEach
    void Refresh() {
        memberRepository.deleteAll();
    }

    @Test
    void createMemberInsertsExactData() {
        Member m = MemberFactory.BuildMemberWithParts();

        memberRepository.save(m);
        Assertions.assertEquals(m, memberRepository.findAll().get(0));
    }

    @Test
    @Transactional
    void deleteMemberByNamesDeletesCorrectly() {
        Member m = MemberFactory.BuildMemberWithParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.count());
        memberRepository.deleteMemberByFirstNameAndLastNameAndMiddleName(m.getFirstName(), m.getLastName(), m.getMiddleName());
        Assertions.assertEquals(0, memberRepository.count());
    }

    @Test
    @Transactional
    void deleteMemberByAddressDeletesCorrectly() {
        Member m = MemberFactory.BuildMemberWithParts();

        memberRepository.save(m);
        Assertions.assertEquals(1, memberRepository.count());
        memberRepository.deleteMemberByAddress(m.getAddress());
        Assertions.assertEquals(0, memberRepository.count());
    }

    @Test
    void countBySportsContainingReturnsCorrectNumber() {
        Member m = MemberFactory.BuildMemberWithParts();

        memberRepository.save(m);
        Integer count = memberRepository.countBySportsContaining(m.getSports().get(0));
        Assertions.assertEquals(count, 1);
    }

    @Test
    void getMemberByTeamReturnsCorrectMember() {
        Member m = MemberFactory.BuildMemberWithParts();

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

        Member m =MemberFactory.MockMember(sports,null,null);
        memberRepository.save(m);
        Assertions.assertEquals(m, memberRepository.getMembersBySportsContaining(sports.get(0)).get(0));
    }

    @Test
    void getMembersByAddress_CountryCodeAndAddress_ZipCodeReturnsCorrectMembers(){

        List<Member> memberList = IntStream.range(0, 10).mapToObj(x -> MemberFactory.BuildMemberWithParts()).toList();

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


        List<Member> memberList = IntStream.range(0, 10).mapToObj(x -> MemberFactory.MockMember(sports,null,null)).toList();

        List<Member> nonAffectedMembers = IntStream.range(0, 10).mapToObj(x -> MemberFactory.MockMember(null,null,null)).toList();

        Assertions.assertEquals(0,memberRepository.findAll().size());
        memberRepository.saveAll(memberList);
        memberRepository.saveAll(nonAffectedMembers);
        Assertions.assertEquals(20,memberRepository.findAll().size());
        memberRepository.deleteMembersBySportsContaining(sports.get(0));
        Assertions.assertEquals(10,memberRepository.findAll().size());

    }

}
