package at.tsc.sj23_tsc_ost.presentation.api;

import at.tsc.sj23_tsc_ost.domain.Address;
import at.tsc.sj23_tsc_ost.domain.Team;
import at.tsc.sj23_tsc_ost.presentation.AbstractRestController;
import at.tsc.sj23_tsc_ost.service.MemberService;
import at.tsc.sj23_tsc_ost.service.dto.MemberDto;
import at.tsc.sj23_tsc_ost.service.dto.TeamDto;
import at.tsc.sj23_tsc_ost.service.dto.command.MutateMemberCommand;
import at.tsc.sj23_tsc_ost.service.dto.command.MutateTeamCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(MemberController.BASEURL )
public class MemberController extends AbstractRestController {

    private final MemberService memberService;
    public static final String BASEURL = "/api/member";
    public static final String PATH_INDEX = "/";


    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<MemberDto>> getAllMembers(){

        Team t = Team.builder().description("nice Team").name("TSC OST").build();
        return ResponseEntity.ok(wrappedServiceException(memberService::getAllMembers));
    }

    @PostMapping({"",PATH_INDEX})
    public HttpEntity<MemberDto> createMember(@Valid @RequestBody MutateMemberCommand member){
        log.debug("Create Member - {}",member);
        Long id =wrappedServiceException(()->memberService.createMember(member));
        return ResponseEntity.created(URI.create(BASEURL+"/Member/"+id)).build();
    }

    @GetMapping({"/team",PATH_INDEX})
    public HttpEntity<List<MemberDto>> getAllMembersByTeam(@Valid @RequestParam MutateTeamCommand team){
        log.debug("Get All Members By Team - {}",team);
        return ResponseEntity.ok(wrappedServiceException(() -> memberService.getMembersByTeam(team)));
    }

    @DeleteMapping({"/remove",PATH_INDEX})
    public HttpEntity<MemberDto> deleteMemberByAddress(@Valid @RequestBody Address address){
        log.debug("Get All Member by Address - {}",address);
        return ResponseEntity.ok(wrappedServiceException(()->memberService.deleteMemberByAddress(address).get()));
    }

    @DeleteMapping({"/remove/{firstName}/{lastName}/{middleName}",PATH_INDEX})
    public HttpEntity<MemberDto> deleteMemberByName(@Valid @RequestParam String firstName,@Valid @RequestParam String lastName, @Valid @RequestParam String middleName){
        log.debug("Get All Member by Names - {} {} {}",firstName,middleName,lastName);
        return ResponseEntity.ok(wrappedServiceException(()->memberService.deleteMemberByName(firstName,lastName,middleName).get()));
    }

    @GetMapping({"/{id}",PATH_INDEX})
    public HttpEntity<MemberDto> getMemberById(@Valid @PathVariable Long id){
        log.debug("Get All Member by ID - {}",id);
        return ResponseEntity.ok(wrappedServiceException(()-> memberService.getMemberById(id).get()));
    }



}
