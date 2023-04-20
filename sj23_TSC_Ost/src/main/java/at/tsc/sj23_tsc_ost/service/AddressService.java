package at.tsc.sj23_tsc_ost.service;

import at.tsc.sj23_tsc_ost.persistence.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

}
