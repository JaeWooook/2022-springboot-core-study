package springboot.corestudy.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototyeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);//이때마다 init이 실행된다. 프로토타입은
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = "  + prototypeBean1);
        System.out.println("prototypeBean2 = "  + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        prototypeBean1.destroy();
        prototypeBean2.destroy();//굳이 종료를 호출할 필요가 있다면 이런식으로 빈을 직접 종료메서드를 호출해야한다.
        ac.close();//프로토타입이기 때문에 close를 해도 만들고 버리기 때문에 destory를 해주지 않는다.
    }

    //Component가 없다 ac에 지정해주면 해당 클래스가 컴포넌트 스캔처럼 빈으로 등록해준다 그래서 생략해도된다.
    @Scope("prototype") //prototype의 스코프는 빈 생성과 초기화까지만 담당하고 나머지는 클라이언트에게 반환하면서 관리하지 않는다.
    static class PrototypeBean {
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
