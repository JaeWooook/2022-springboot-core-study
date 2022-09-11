package springboot.corestudy.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.corestudy.AppConfig;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();

    MemberService memberService;

    @BeforeEach //테스트 실행하기전에 생성
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
