package at.tsc.sj23_tsc_ost.service;

import at.tsc.sj23_tsc_ost.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

}
