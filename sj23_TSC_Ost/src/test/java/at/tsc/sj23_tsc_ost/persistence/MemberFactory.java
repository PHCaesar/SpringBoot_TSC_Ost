package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.domain.*;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MemberFactory {

    public static Member BuildMemberWithRandomParts() {
        Random randomInstance = new Random();
        List<Role> roles = Arrays.stream(Role.values()).toList();

        List<SportsType> sportsOptions = Arrays.stream(SportsType.values()).toList();
        List<Sports> sports = sportsOptions.stream().map(x -> Sports.builder()
                .sportsType(x)
                .description(RandomString.make(20))
                .name(RandomString.make(8))
                .build()).toList();


        Team team = Team.builder()
                .name(RandomString.make(8))
                .description(RandomString.make(20))
                .creationDate(LocalDate.now().minusDays(randomInstance.nextInt(200)))
                .build();

        return MockMemberRandomized(sports, roles.get(randomInstance.nextInt(roles.size())), team, randomInstance);
    }

    public static Member MockMemberRandomized(List<Sports> sports, Role role, Team team, Random randomInstance){

        return Member.builder()
                .firstName(RandomString.make(8))
                .middleName(RandomString.make(8))
                .lastName(RandomString.make(8))
                .sports(sports)
                .role(role)
                .team(team)
                .birthDate(LocalDate.now().minusDays(100))
                .address(Address.builder()
                        .countryCode(RandomString.make(4))
                        .streetName(RandomString.make(10))
                        .zipCode(RandomString.make(4))
                        .build())
                .build();
    }


    public static Member BuildMemberWithParts() {
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

    public static Member MockMember(List<Sports> sports, Role role, Team team){

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
