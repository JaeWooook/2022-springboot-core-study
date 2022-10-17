package springboot.corestudy.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.corestudy.AppConfig;
import springboot.corestudy.member.MemberService;
import springboot.corestudy.member.MemberServiceImpl;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService = "+ memberService);
//        System.out.println("memberServcie = "+ memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);//멤버 서비스가 멤버서비스임플의 인스턴스면 성공이다.
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);//이름이 없이 타입으로만 조회 해도 성공한다.
//        System.out.println("memberService = "+ memberService);
//        System.out.println("memberServcie = "+ memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);//멤버 서비스가 멤버서비스임플의 인스턴스면 성공이다.
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        //반환되는 타입은 MemberServiceImpl이다.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);//스프링에 등록된 반환되는 인스턴스 타입으로 하기 때문이다.
//        System.out.println("memberService = "+ memberService);
//        System.out.println("memberServcie = "+ memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);//멤버 서비스가 멤버서비스임플의 인스턴스면 성공이다.
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
//        ac.getBean("xxxx", MemberService.class);
//        ac.getBean("xxxx", MemberService.class); //등록한적 없는 빈에 대한 테스트를 하면 틀린다. 이것을 검증을 해야된다.
        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("xxxx", MemberService.class));
        //오른쪽에 있는 로직이 실패했을 때 NoSuchBeanDefinitionException 에러가 발생해야 된다.
    }
}
