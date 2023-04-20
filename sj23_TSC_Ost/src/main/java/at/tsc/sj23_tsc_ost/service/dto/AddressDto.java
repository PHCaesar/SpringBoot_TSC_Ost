package at.tsc.sj23_tsc_ost.service.dto;

import at.tsc.sj23_tsc_ost.domain.Address;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record AddressDto(String zipCode, String countryCode, String streetName) {
    public AddressDto(Address address){
        this(address.getZipCode(),
                address.getCountryCode(),
                address.getStreetName());
        log.debug("AddressDto from {} has been built!", address);
    }
}
