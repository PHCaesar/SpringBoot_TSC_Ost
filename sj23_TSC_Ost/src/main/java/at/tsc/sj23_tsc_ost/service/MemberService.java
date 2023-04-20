package at.tsc.sj23_tsc_ost.service;

import at.tsc.sj23_tsc_ost.domain.Member;
import at.tsc.sj23_tsc_ost.persistence.MemberRepository;
import at.tsc.sj23_tsc_ost.service.dto.MemberDto;
import at.tsc.sj23_tsc_ost.service.dto.command.MutateMemberCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void Test(Member me){
        MemberDto m = new MemberDto(me);
    }
}
