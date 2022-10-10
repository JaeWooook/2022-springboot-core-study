package springboot.corestudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.corestudy.discount.DiscountPolicy;
import springboot.corestudy.discount.FixDiscountPolicy;
import springboot.corestudy.discount.RateDiscountPolicy;
import springboot.corestudy.member.MemberService;
import springboot.corestudy.member.MemberServiceImpl;
import springboot.corestudy.member.MemoryMemberRepository;
import springboot.corestudy.order.OrderService;
import springboot.corestudy.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    //AppConfig에서 생성한 객체 인스턴스를 생성자를 통해서 주입(연결)하는 과정
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 여기서 new MemoryMemberRepository 이런걸 안해준다.
    }

    @Bean
    public MemoryMemberRepository memberRepository() {//이런식으로 구조를 하나하나 다 나누어준다면, 객체생성을 안해준다.
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); 이부분만 변경하면 할인 정책을 바꿔줄수 있다.
        return new RateDiscountPolicy();
    }
}
