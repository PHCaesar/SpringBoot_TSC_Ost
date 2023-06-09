package at.tsc.sj23_tsc_ost.service;

import at.tsc.sj23_tsc_ost.domain.Address;
import at.tsc.sj23_tsc_ost.domain.Member;
import at.tsc.sj23_tsc_ost.domain.Team;
import at.tsc.sj23_tsc_ost.persistence.MemberRepository;
import at.tsc.sj23_tsc_ost.service.dto.MemberDto;
import at.tsc.sj23_tsc_ost.service.dto.command.MutateMemberCommand;
import at.tsc.sj23_tsc_ost.service.dto.command.MutateTeamCommand;
import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberDto> getAllMembers(){
        return memberRepository.findAll().stream().map(MemberDto::new).toList();
    }

    public Optional<MemberDto> getMemberById(Long id){
        return memberRepository.findById(id).map(MemberDto::new);
    }

    public Optional<MemberDto> getMembersByName(String firstName,String middleName,String lastName){
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(middleName);
        memberRepository.getMemberByAddress(new Address()).map(MemberDto::new);



        Optional<MemberDto> dto = memberRepository.getMemberByFirstNameAndLastNameAndMiddleName(firstName, middleName, lastName).map(MemberDto::new);
        return validateMember(dto,MessageFormat.format("The Member with the name {0} {1} {2} could not be found",firstName,middleName,lastName));
    }

    public List<MemberDto> getMembersByTeam(MutateTeamCommand team){
        return memberRepository.getMembersByTeam(Team.builder()
                .members(team.getMembers())
                .name(team.getName())
                .description(team.getDescription())
                .creationDate(team.getCreationDate()).build()).stream().map(MemberDto::new).toList();
    }

    public Optional<MemberDto> getMemberByAddress(Address address){
        Objects.requireNonNull(address);

        Optional<MemberDto> dto = memberRepository.getMemberByAddress(address).map(MemberDto::new);
        return validateMember(dto, MessageFormat.format("The Member with the address {0} is not present in the database!",address));
    }

    public Optional<MemberDto> deleteMemberByName(String firstName,String middleName,String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(middleName);
        Objects.requireNonNull(lastName);

        Optional<MemberDto> m = memberRepository.getMemberByFirstNameAndLastNameAndMiddleName(firstName, lastName, middleName).map(MemberDto::new);
        memberRepository.deleteMemberByFirstNameAndLastNameAndMiddleName(firstName, lastName, middleName);
        return m;
    }
    public Optional<MemberDto> deleteMemberByAddress(Address address){
        Objects.requireNonNull(address);
        Optional<MemberDto> m = memberRepository.getMemberByAddress(address).map(MemberDto::new);
        memberRepository.deleteMemberByAddress(address);
        return m;
    }

    public Long createMember(MutateMemberCommand member){
        return memberRepository.save(Member.builder()
                .birthDate(member.getMutateBirthDate())
                .role(member.getRole())
                .sports(member.getSports())
                .team(member.getTeam())
                .address(member.getAddress())
                .creationTimeStamp(member.getUpdateTimeStamp())
                .password(member.getMutatePassword())
                .build()).getId();

    }

    // Automatic emptiness validation
    public Optional<MemberDto> validateMember(Optional<MemberDto> dto,String errMsg){
        if(dto.isEmpty()) {
            log.error(errMsg);
            throw new NoSuchElementException(errMsg);
        }else return dto;
    }
}

