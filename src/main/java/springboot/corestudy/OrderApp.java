package springboot.corestudy;

import springboot.corestudy.member.Grade;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemberService;
import springboot.corestudy.member.MemberServiceImpl;
import springboot.corestudy.order.Order;
import springboot.corestudy.order.OrderService;
import springboot.corestudy.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService(); // MemberServiceImpl 객체를 반환한다.
        OrderService orderService = appConfig.orderService(); // OrderServiceImpl 객체를 반환한다.
//        MemberService memberService = new MemberServiceImpl(null);
//        OrderService orderService = new OrderServiceImpl(null, null);

        Long memberId = 1L;
        Member member  = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
