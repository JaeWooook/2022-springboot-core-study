package springboot.corestudy.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {//close를 쓰기위해서 ConfigurableApplicationContext를 사용한다 인터페이스를 사용하기 위함
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        //스프링 빈은 간단하게 객체 생성 -> 의존관계 주입이 일어나는 라이프사이클을 가진다.
        //초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.
        //스프링은 컨테이너가 종료되기 직전에 소멸 콜백을 준다.
        //스프링 빈의 이벤트 라이프사이클
        //스프링 컨테이너 생성 -> 스프링 빈 생성(생성자 주입은 여기서 주입) -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
        @Bean(initMethod = "init", destroyMethod = "close") //이렇게하면 스프링 빈이 스프링에만 의존하지 않는다. 코드를 고칠수 없는 외부라이브러리에도 초기화, 종료를 쓸 수 있다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");//당연하게 생성자에서 url을 넣어주지 않기 때문에 null이 나온다.
            return networkClient;
            //객체의 생성과 초기화를 분리하는게 좋다. 대부분의 메서드는 이렇게 하는게 좋다 객체를 할당하는부분까지만 생성자로 사용하는게 좋다.
            //객체 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는 무거운 동작을 수행한다.
            //따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분을 명확하게 나누는것이 좋다.
        }
    }
}
