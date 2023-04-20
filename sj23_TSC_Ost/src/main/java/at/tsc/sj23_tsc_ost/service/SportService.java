package at.tsc.sj23_tsc_ost.service;

import at.tsc.sj23_tsc_ost.persistence.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SportService {
    private final SportRepository sportRepository;
}
