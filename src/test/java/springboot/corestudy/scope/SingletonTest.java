package springboot.corestudy.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletoneBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean1);

        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close(); //싱글톤은 스프링 컨테이너에서 관리를 하기 때문에 생성하고 종료메서드까지 실행 해준다.
    }

    @Scope("singleton")//디폴트가 싱글톤이라서 안해도되는데 한다.
    //prototype의 스코프는 빈 생성과 초기화까지만 담당하고 나머지는 클라이언트에게 반환하면서 관리하지 않는다.
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
