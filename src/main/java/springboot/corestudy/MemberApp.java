package springboot.corestudy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.corestudy.member.Grade;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//이게 스프링의 모든 빈을 생성해주는 것이다.
        //어노테이션을 기반으로 Config를 하고있다. AppConfig에 있는 환경설정 정보를 가지고 스프링이 내부에서 @Bean들을 컨테이너에 객체 생성한것을 넣고 관리해준다.
        MemberService memberService =  applicationContext.getBean("memberService", MemberService.class);//Config에서 메서드이름을 가진 객체를 찾는 메서드
        //이름은 memberService이고 , 타입은 MemberService이다.

        //회원 가입 로직
//        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);//Long타입은 뒤에 L을 붙여야한다.
        memberService.join(member);

        //정상 회원가입 조회 동일한지 확인 인스턴스 확인하려면 member, findMember로 확인 가능하다.
        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
