package springboot.corestudy.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.corestudy.AutoAppConfig;
import springboot.corestudy.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        //ClassPathBeanDefinitionScanner 이것이 작동해서 각컴포넌트의 후보들이 등록되면서
        //Creating shared instance of singleton bean 이라고 싱글톤 빈으로 생성됨이 나온다.
        //Autowiring by type from bean name 오토와이어드 된것도 알려준다. 이렇게하면 컴포넌트 스캔으로 자동으로 스프링빈이 등록된것이다.
    }
}
