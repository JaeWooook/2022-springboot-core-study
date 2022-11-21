package springboot.corestudy.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springboot.corestudy.annotation.MainDiscountPolicy;
import springboot.corestudy.discount.DiscountPolicy;
import springboot.corestudy.discount.FixDiscountPolicy;
import springboot.corestudy.discount.RateDiscountPolicy;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemberRepository;
import springboot.corestudy.member.MemoryMemberRepository;

@Component
//@RequiredArgsConstructor //RequiredArgsConstructor는 파이널이 붙은 필드값을 이용해서 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    //DIP를 어긴 예시이다. 동작은 하지만 구체 클래스(구체 객체)를 직접 주입해서는 안된다.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정할인 금액이다. FixDiscountPolicy에 의존하고 있다.
    //추상과 구체 두개 모두에 의존하고 있다. 결론적으로는 인터페이스에만 의존해야만 한다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 이렇게 바꿔주면 끝이된다.
    //할인 정책의 객체를 바꿔주는것 이다.
    private final DiscountPolicy discountPolicy; //이렇게 하면 인터페이스에만 의존한다. 구체에 의존하지 않는다.
    // 이러한 문제를 해결하기 위해서는 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.
    // 스프링 컨테이너를 사용하는 이유
//생성자 주입방식에서만 final 키워드를 사용할 수가 없다. 생성자주입을 사용해야 final 키워드를 사용할 수 있고, 컴파일오류로 잡아낼 수 있다.
    //필드 주입을 하게되면 스프링컨테이너 없이는 테스트를 할 수 없고, 테스트에 용이하지 못하다.

    //여러개를 조회했을 때 빈이름을 조회하기 때문에 정확한 빈이름으로 설정해주면 2개이상인 경우에도 해준다.
    //타입이름으로 해놨기 때문에 2개가 조회되어 에러를 발생한다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
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

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
