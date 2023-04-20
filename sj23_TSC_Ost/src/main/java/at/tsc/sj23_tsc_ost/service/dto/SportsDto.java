package at.tsc.sj23_tsc_ost.service.dto;

import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.SportsType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record SportsDto(SportsType type,String name,String description) {
    public SportsDto(Sports sport){
        this(sport.getSportsType(),sport.getName(),sport.getDescription());
        log.info("SportsDto from SportsEntity {} has been created!",sport);
    }
}
