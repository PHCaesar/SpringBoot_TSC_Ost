package at.tsc.sj23_tsc_ost.service;

import at.tsc.sj23_tsc_ost.domain.Team;
import at.tsc.sj23_tsc_ost.persistence.TeamRepository;
import at.tsc.sj23_tsc_ost.service.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Optional<TeamDto> getTeamByName(String name){
        Objects.requireNonNull(name);

        Optional<TeamDto> dto = teamRepository.getTeamByName(name).map(TeamDto::new);
        String errorMessage = MessageFormat.format("TeamDto:{0} is empty", name);
        return validateTeam(dto, errorMessage);

    }

    private Optional<TeamDto> validateTeam(Optional<TeamDto> teamDto, String errorMessage){
        if(teamDto.isEmpty()){
            log.warn(errorMessage);
            throw new NoSuchElementException(errorMessage);
        }
        else{
            return teamDto;
        }
    }
}
