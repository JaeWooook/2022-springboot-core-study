package springboot.corestudy.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);//싱글톤 안에서 프로토타입 빈을 사용하면
        //Provider를 사용하면 2개의 빈이 서로 다르다. 중복되지않는다.
        // 스프링이 싱글톤일 경우 호출할때마다 새로 생성하지만, 프로토타입 빈을 사용하게 되면 그대로 기존에 있는 빈을 사용해서
        // 이렇게 기존의 1에 중첩되는 현상이 생긴다.
    }

    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean; //생성 시점에 이미 주입되어있음
        //생성 시점에 주입되면서 할당되기 때문에 계속 같은 빈을 사용한다.

        //ObjectFactory는 스프링에 의존적이지만 기능이 단순, 별도의 라이브러리 필요 없음
        //ObjectProvider는 ObjectFactory에 상속, 옵션, 스트림 처리등의 편의 기능이 많고, 별도의 라이브러리 필요가 없다. 스프링에 의존적
        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;//ObjectFactory로 해도 똑같이 동작한다.
        //ObjectProvider는 getObject를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.(Dependency Lookup 방식)
        private Provider<PrototypeBean> prototypeBeanProvider; //Java 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있는 장점이 있다.
        //provider.get()을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.

//        @Autowired
//        ApplicationContext applicationContext;

        //싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과 함께 계속 유지가 되는것이 문제이다.
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {//생성자를 호출되면서 프로토타입 빈이 이미 만들어진 애를 지속적으로 사용한다.
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); //이런식으로 로직을 호출할 때마다 프로토타입 빈을 생성한다면
            //할당되는 빈이 logic을 호출할 때마다 할당되기 때문에 중첩되지 않는다.
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();//이렇게 하면 프로토타입빈이 2개가 생성됨을 알 수 있다.
            PrototypeBean prototypeBean = prototypeBeanProvider.get();//javax.inject에서 사용하는 Provider이다.
            //이것을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
