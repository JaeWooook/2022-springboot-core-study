package springboot.corestudy.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import springboot.corestudy.member.Member;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {//Member는 스프링 빈이 아니다.

        @Autowired(required = false)//기본값은 true라서 스프링에서 찾을수없다고 에러가 터진다.
        public void setNoBean1(Member noBean1) {//member는 스프링이관리하는 빈이 아니다.
            System.out.println("noBean1 = " + noBean1);
        }//자동 주입할 대상이없으면 수정자 호출이 안된다.

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }// Nullable인 경우는 호출은 된다. 하지만 찾지못했기 때문에 null로 들어간다.

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }// Optional은 들어가면 스프링 빈이 없으면 Optional.empty로 들어간다.
    }
}
