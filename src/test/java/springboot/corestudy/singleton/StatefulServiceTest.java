package springboot.corestudy.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    //공유 필드는 항상 조심해야하고, 스프링 빈은 항상 무상태로 설계해야 한다.
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //문제있는 버전
//        //ThreadA : A사용자가 만원을 주문
//        statefulService1.order("userA", 10000);
//        //ThreadB : B사용자가 2만원을 주문
//        statefulService1.order("userA", 20000);

        //문제없는 버전
//        ThreadA : A사용자가 만원을 주문
        int userA = statefulService1.order("userA", 10000);
//        ThreadB : B사용자가 2만원을 주문
        int userB = statefulService1.order("userA", 20000);

        //ThreadA : 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice(); //? 여기는 얼마가 나와야할까? 만원이 아닌 2만원이 나온다.
//        System.out.println("price = " + price); // 같은 인스턴스를 쓰기 때문에 싱글톤의 문제점이다.
        System.out.println(" price = "  +userA); // 지역변수는 공유되는것이 아니기 때문에 만원이 나온다.
//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}