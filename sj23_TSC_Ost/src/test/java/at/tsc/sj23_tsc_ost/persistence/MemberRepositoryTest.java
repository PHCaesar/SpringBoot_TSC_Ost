package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.*;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.*;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    @AfterEach
    void Refresh(){
        memberRepository.deleteAll();
    }
    
    @Test
    void createMemberInsertsExactData(){
        Member m = MockMember();

        memberRepository.save(m);
        Assertions.assertEquals(m,memberRepository.findAll().get(0));
    }

    @Test
    @Transactional
    void deleteMemberByNamesDeletesCorrectly(){
        Member m = MockMember();

        memberRepository.save(m);
        Assertions.assertEquals(1,memberRepository.count());
        memberRepository.deleteMemberByFirstNameAndLastNameAndMiddleName(m.getFirstName(), m.getLastName(),m.getMiddleName() );
        Assertions.assertEquals(0,memberRepository.count());
    }

    @Test
    @Transactional
    void deleteMemberByAddressDeletesCorrectly(){
        Member m = MockMember();

        memberRepository.save(m);
        Assertions.assertEquals(1,memberRepository.count());
        memberRepository.deleteMemberByAddress(m.getAddress());
        Assertions.assertEquals(0,memberRepository.count());
    }

    public Member MockMember(){

        Random randomInstance = new Random();
        List<Role> roles = Arrays.stream(Role.values()).toList();

        return Member.builder()
                .firstName(RandomString.make(8))
                .middleName(RandomString.make(8))
                .lastName(RandomString.make(8))
                .sports(null)
                .role(roles.get(randomInstance.nextInt(roles.size())))
                .team(new Team())
                .birthDate(LocalDate.now().minusDays(100))
                .address(Address.builder()
                        .countryCode(RandomString.make(4))
                        .streetName(RandomString.make(10))
                        .zipCode(RandomString.make(4))
                        .build())
                .build();
    }
}
