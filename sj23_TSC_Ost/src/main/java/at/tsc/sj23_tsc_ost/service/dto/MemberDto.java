package at.tsc.sj23_tsc_ost.service.dto;

import at.tsc.sj23_tsc_ost.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public record MemberDto(LocalDateTime timeStamp, LocalDate birthDate,Address address, Role role, Team team, List<Sports> sports, String firstName, String middleName, String lastName, String password) {
    public MemberDto(Member member){
        this(member.getUpdateTimeStamp(),
                member.getBirthDate(),
                member.getAddress()
                ,member.getRole()
                ,member.getTeam()
                ,member.getSports()
                ,member.getFirstName()
                ,member.getMiddleName()
                ,member.getLastName()
                ,member.getPassword());
        log.debug("MemberDto from Member {} has been built!",member);
    }
}
