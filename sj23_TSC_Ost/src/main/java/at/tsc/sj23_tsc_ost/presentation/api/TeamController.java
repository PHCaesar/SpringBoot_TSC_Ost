package at.tsc.sj23_tsc_ost.presentation.api;

import at.tsc.sj23_tsc_ost.domain.Team;
import at.tsc.sj23_tsc_ost.service.TeamService;
import at.tsc.sj23_tsc_ost.service.dto.TeamDto;
import at.tsc.sj23_tsc_ost.presentation.AbstractRestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(TeamController.basePath)
@Slf4j
public class TeamController extends AbstractRestController{

    private final TeamService teamService;

    public static final String basePath = "/api/team";

    @GetMapping("/{name}")
    public HttpEntity<Optional<TeamDto>> getTeamByName(@Valid @RequestParam String name){
        log.info(String.format("returns Team by name : %s",name));
        return ResponseEntity.ok().body(wrappedServiceException(() -> teamService.getTeamByName(name)));
    }

    @GetMapping("/{creationDate}")
    public HttpEntity<List<TeamDto>> getTeamByCreationDate(@Valid @RequestParam LocalDate creationDate){
        log.info(String.format("returns Teams by creationDate : %s",creationDate));
        return ResponseEntity.ok().body(wrappedServiceException(() -> teamService.getTeamsByCreationDate(creationDate)));
    }
}
