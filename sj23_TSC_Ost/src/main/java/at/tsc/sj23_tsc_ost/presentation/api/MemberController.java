package at.tsc.sj23_tsc_ost.presentation.api;

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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(MemberController.BASEURL )
public class MemberController extends AbstractRestController {

    private final MemberService memberService;
    public static final String BASEURL = "/api/memberController";
    public static final String PATH_INDEX = "/";

    @GetMapping({"/team",PATH_INDEX})
    public HttpEntity<List<MemberDto>> getAllMembersByTeam(@Valid @RequestParam MutateTeamCommand team){
        log.debug("Get All Members By Team - {}",team);
        return ResponseEntity.ok(wrappedServiceException(() -> memberService.getMembersByTeam(team)));
    }


    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<MemberDto>> getAllMembers(){

        Team t = Team.builder().description("nice Team").name("TSC OST").build();
        memberService.createMember(MutateMemberCommand.builder().firstName("BOII").team(t).build());

        memberService.createMember(MutateMemberCommand.builder().firstName("Nico").team(t).build());

        return ResponseEntity.ok(wrappedServiceException(memberService::getAllMembers));
    }

    @PostMapping({"",PATH_INDEX})
    public HttpEntity<MemberDto> createMember(@Valid MutateMemberCommand member){
        Long smth =wrappedServiceException(()->memberService.createMember(member));
        return null;
    }



}
