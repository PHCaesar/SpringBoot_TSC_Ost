package at.tsc.sj23_tsc_ost.presentation.api;

import at.tsc.sj23_tsc_ost.service.TeamService;
import at.tsc.sj23_tsc_ost.service.dto.TeamDto;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(TeamController.basePath)
@Slf4j
public class TeamController {

    private final TeamService teamService;

    public static final String basePath = "/api/team";

    @GetMapping("/{name}")
    public HttpEntity<Optional<TeamDto>> getTeamByName(@Valid @RequestParam String name){
        log.info(String.format("returns Team by name : %s",name));
        return ResponseEntity.ok().body(teamService.getTeamByName(name));
    }

}
