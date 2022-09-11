package springboot.corestudy.order;

import springboot.corestudy.discount.DiscountPolicy;
import springboot.corestudy.discount.FixDiscountPolicy;
import springboot.corestudy.discount.RateDiscountPolicy;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemberRepository;
import springboot.corestudy.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    //DIP를 어긴 예시이다. 동작은 하지만 구체 클래스(구체 객체)를 직접 주입해서는 안된다.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정할인 금액이다. FixDiscountPolicy에 의존하고 있다.
    //추상과 구체 두개 모두에 의존하고 있다. 결론적으로는 인터페이스에만 의존해야만 한다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 이렇게 바꿔주면 끝이된다.
    //할인 정책의 객체를 바꿔주는것 이다.
    private DiscountPolicy discountPolicy; //이렇게 하면 인터페이스에만 의존한다. 구체에 의존하지 않는다.
    // 이러한 문제를 해결하기 위해서는 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.
    // 스프링 컨테이너를 사용하는 이유


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);//회원정보 조회
        int discountPrice =  discountPolicy.discount(member, itemPrice);//할인 정책에 회원정보를 넘긴다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //단일 체계의 원칙을 잘 지킨것이다 할인에 대한것은 discount에서만 처리하기 때문에
    //할인에 관련된것은 할인만 고치면 된다.
}
