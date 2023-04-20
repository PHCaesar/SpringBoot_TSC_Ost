package at.tsc.sj23_tsc_ost.service.dto.command;

import at.tsc.sj23_tsc_ost.domain.SportsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MutateSportCommand {
    private String sportName;
    private String sportDescription;
    private SportsType sportType;
}
