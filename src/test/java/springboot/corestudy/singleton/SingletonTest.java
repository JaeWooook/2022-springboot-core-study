package springboot.corestudy.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.corestudy.AppConfig;
import springboot.corestudy.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
//        new SingletonService(); //private access라서 접근 불가라는 오류가 나온다 컴파일 에러다.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //참조값 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        //java가 뜰때 생성한 객체를 그대로 쓰는 것이다.

        //singletonService1 == singletonService2
        assertThat(singletonService1).isSameAs(singletonService2);
        //isEqualTo는 자바의 equals비교 , isSameAs 자바의 == 비교이다.
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        //스프링은 각기 다른 고객의 요청에 대해 호출된 객체들이 모두 동일한 인스턴스인 memberService를 반환하게 된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //스프링 컨테이너에서 생성한 것 이지만 싱글톤으로 알아서 해주기 때문에 2번 객체를 호출해도 같은 객체를 가진다.
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
//        assertThat(memberService1).isNotSameAs(memberService2);

        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
