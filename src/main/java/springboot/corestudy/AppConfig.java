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

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //위와같이 객체를 두번 호출하게되면 싱글톤이 깨질까 안깨질까?

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    
    //이 순서로 호출될 것이다. 아마도 순서는 다를 수 있지만 3번 호출 된다.

    //우리의 의도와는 다르게
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    //이렇게 호출됨을 확인할 수 있다. 스프링이 어떠한 방법을 쓰더라도 싱글톤을 보장해준다.

    //AppConfig에서 생성한 객체 인스턴스를 생성자를 통해서 주입(연결)하는 과정
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 여기서 new MemoryMemberRepository 이런걸 안해준다.
    }

    @Bean
    public MemoryMemberRepository memberRepository() {//이런식으로 구조를 하나하나 다 나누어준다면, 객체생성을 안해준다.
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); 이부분만 변경하면 할인 정책을 바꿔줄수 있다.
        return new RateDiscountPolicy();
    }
}
