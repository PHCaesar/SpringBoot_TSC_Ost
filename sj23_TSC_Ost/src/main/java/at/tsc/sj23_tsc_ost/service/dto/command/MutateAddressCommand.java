package at.tsc.sj23_tsc_ost.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MutateAddressCommand {
    private String zip;
    private String countryTag;
    private String street;
}
