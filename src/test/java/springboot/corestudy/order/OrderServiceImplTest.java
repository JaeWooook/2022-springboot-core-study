package springboot.corestudy.order;

import org.junit.jupiter.api.Test;
import springboot.corestudy.discount.FixDiscountPolicy;
import springboot.corestudy.member.Grade;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        //테스트 코드를 조힙하는 것이다. 생성자로해야 필요한 것들을 직접 넣어서 테스트할 수 있다.

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        //생성자 주입을 하게되면 컴파일 오류가난다. 필수값으로 memberRepository랑 discountRepository가 필요하다.
        Order order =  orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        //가짜라도 임의의 더미라도 넣어주지 않으면 테스트를 할수가 없다.
    }
}
