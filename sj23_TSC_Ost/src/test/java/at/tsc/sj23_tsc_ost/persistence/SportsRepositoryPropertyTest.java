package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.SportsType;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class SportsRepositoryPropertyTest {

    @Autowired
    private SportRepository sportRepository;

    @AfterEach @BeforeEach
    void Refresh() {
        sportRepository.deleteAll();
    }

    @Test
    void findSportsByName(){
        Sports sports = Sports.builder()
                .name(RandomString.make(9))
                .description(RandomString.make(20))
                .sportsType(SportsType.CONTACT)
                .build();

        sportRepository.save(sports);

        Assertions.assertEquals(sports,sportRepository.findSportsByName(sports.getName()).get());
    }

    @Test
    void findSportsBySportsType(){
        List<SportsType> sportsTypes= Arrays.stream(SportsType.values()).toList();
        Random randomInstance = new Random();
        SportsType type = sportsTypes.get(randomInstance.nextInt(sportsTypes.size()));
        List<Sports> sportsList = IntStream.range(0,10).mapToObj(x ->
                Sports.builder()
                        .sportsType(type)
                        .description(RandomString.make(20))
                        .name(RandomString.make(9))
                        .build()).toList();


        sportRepository.saveAll(sportsList);

        Assertions.assertEquals(sportsList,sportRepository.findSportsBySportsType(type));
    }
}
