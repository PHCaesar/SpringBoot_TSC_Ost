package at.tsc.sj23_tsc_ost.service.dto;

import at.tsc.sj23_tsc_ost.domain.Sports;
import at.tsc.sj23_tsc_ost.domain.SportsType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record SportDto(String name, String description, SportsType type) {
    public SportDto(Sports sports){
        this(sports.getName(),
                sports.getDescription(),
                sports.getSportsType());
    }
}
