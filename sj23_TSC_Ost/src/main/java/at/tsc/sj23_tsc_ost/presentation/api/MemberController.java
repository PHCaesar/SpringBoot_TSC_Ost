package at.tsc.sj23_tsc_ost.presentation.api;

import at.tsc.sj23_tsc_ost.domain.Team;
import at.tsc.sj23_tsc_ost.service.MemberService;
import at.tsc.sj23_tsc_ost.service.dto.MemberDto;
import at.tsc.sj23_tsc_ost.service.dto.command.MutateTeamCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(MemberController.BASEURL )
public class MemberController {

    private final MemberService memberService;
    public static final String BASEURL = "/api/memberController";
    public static final String PATH_INDEX = "/";

    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<MemberDto>> getAllMembers(@Valid @RequestParam MutateTeamCommand team){
        List<String> names =memberService.getMembersByTeam(new Team()).stream().map(MemberDto::password).toList();
        //TeamDto dto = new TeamDto();
        return ResponseEntity.ok(memberService.getMembersByTeam(new Team()));
    }

}
