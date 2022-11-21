package springboot.corestudy.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springboot.corestudy.annotation.MainDiscountPolicy;
import springboot.corestudy.member.Grade;
import springboot.corestudy.member.Member;

@Component
//@Qualifier("mainDiscountPolicy")
//@Primary //rate가 무조건 우선 선정 되도록한다.
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
