package springboot.corestudy.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.corestudy.AppConfig;
import springboot.corestudy.member.MemberRepository;
import springboot.corestudy.member.MemberServiceImpl;
import springboot.corestudy.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //3가지가 다 똑같다.
        // new를 3번 호출하는데도 어떻게 3개가 전부 같은 객체(인스턴스) 인가?
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository -> memberRepository = " + memberRepository);

        //전부 같은 인스턴스를 사용하고 있다.
        assertThat(memberRepository1).isEqualTo(memberRepository2);
        assertThat(memberRepository).isEqualTo(memberRepository1);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = "  +bean.getClass()); // class springboot.corestudy.AppConfig$$EnhancerBySpringCGLIB$$5cba855a
        //순수한 클래스라면 class springboot.corestudy.Appconfig이렇게 출력되야한다.
        //xxxCGLIB가 붙는것은 내가 만든 클래스가 아니다. 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속 받는 임의의 다른 클래스를 만들고, 그다른클래스를 스프링 빈으로 등록한 것이다.

        // @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링빈으로 등록하고 반환하는 코드가 동적으로 만들어짐
        // 덕분에 싱글톤이 보장된다.

        //@Configuration을 빼면 CGLIB를 사용하지 않는다.
        //하지만 memberRepository가 3번 호출된다. 빈은 등록되지만 싱글톤은 깨지게 된다.
    }
}
