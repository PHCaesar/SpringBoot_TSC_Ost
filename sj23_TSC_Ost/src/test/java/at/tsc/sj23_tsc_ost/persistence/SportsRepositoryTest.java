package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.SportsType;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SportsRepositoryTest {

    @Autowired
    private SportRepository sportRepository;


    @AfterEach @BeforeEach
    void Refresh() {
        sportRepository.deleteAll();
    }


    @Test
    void findSportsBySportsType(){
        Sports sports = Sports.builder()
                .name("Handball")
                .description("Playing with a ball in your hand")
                .sportsType(SportsType.WINTER)
                .build();

        sportRepository.save(sports);

        Assertions.assertEquals(sports,sportRepository.findSportsBySportsType(sports.getSportsType()).get(0));
    }

    @Test
    void findSportsByName(){
        Sports sports = Sports.builder()
                .name("Handball")
                .description("Playing with a ball in your hand")
                .sportsType(SportsType.WATER)
                .build();

        sportRepository.save(sports);

        Assertions.assertEquals(sports,sportRepository.findSportsByName(sports.getName()).get());
    }
}
