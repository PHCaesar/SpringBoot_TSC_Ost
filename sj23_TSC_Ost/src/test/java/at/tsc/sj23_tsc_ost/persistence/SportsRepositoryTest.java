package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.SportsType;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SportsRepositoryTest {

    @Autowired
    private SportRepository sportRepository;

    @Test
    void findSportsByName(){
        Sports sports = Sports.builder()
                .name("Handball")
                .description("Playing with a ball in your hand")
                .sportsType(SportsType.CONTACT)
                .build();

        sportRepository.save(sports);

        Assertions.assertEquals(sports,sportRepository.findSportsByName(sports.getName()).get());
    }

    @Test
    void findSportsBySportsType(){
        Sports sports = Sports.builder()
                .name("Handball")
                .description("Playing with a ball in your hand")
                .sportsType(SportsType.CONTACT)
                .build();

        sportRepository.save(sports);

        Assertions.assertEquals(sports,sportRepository.findSportsBySportsType(sports.getSportsType()).get(0));
    }
}
