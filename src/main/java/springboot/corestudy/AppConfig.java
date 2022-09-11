package springboot.corestudy;

import springboot.corestudy.discount.FixDiscountPolicy;
import springboot.corestudy.member.MemberService;
import springboot.corestudy.member.MemberServiceImpl;
import springboot.corestudy.member.MemoryMemberRepository;
import springboot.corestudy.order.OrderService;
import springboot.corestudy.order.OrderServiceImpl;

public class AppConfig {

    //AppConfig에서 생성한 객체 인스턴스를 생성자를 통해서 주입(연결)하는 과정
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
