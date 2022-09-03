package springboot.corestudy.order;

import springboot.corestudy.discount.DiscountPolicy;
import springboot.corestudy.discount.FixDiscountPolicy;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemberRepository;
import springboot.corestudy.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);//회원정보 조회
        int discountPrice =  discountPolicy.discount(member, itemPrice);//할인 정책에 회원정보를 넘긴다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //단일 체계의 원칙을 잘 지킨것이다 할인에 대한것은 discount에서만 처리하기 때문에
    //할인에 관련된것은 할인만 고치면 된다.
}
