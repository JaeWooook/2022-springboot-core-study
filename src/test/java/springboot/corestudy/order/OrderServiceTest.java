package springboot.corestudy.order;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springboot.corestudy.member.Grade;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemberService;
import springboot.corestudy.member.MemberServiceImpl;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order  = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
