package springboot.corestudy.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given 테스트를 하기 위해 주어진 값
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when 회원가입 테스트를 하기 위한 행동
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then 테스트를 확인하기 위한 결과 똑같은지 확인
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
