package springboot.corestudy.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.corestudy.AutoAppConfig;
import springboot.corestudy.discount.DiscountPolicy;
import springboot.corestudy.member.Grade;
import springboot.corestudy.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {//AutoAppConfig.class를 넣어주면 자동으로 컴포넌트 스캔을하면서 빈을 등록해준다. 그냥 DiscountService.class만으로는 되지 않는다. 등록이?
       ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
       
       DiscountService discountService = ac.getBean(DiscountService.class);
       Member member = new Member(1L, "userA", Grade.VIP);
       int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired//생성자 하나라서 오토와이어드 생략가능
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = "  + policies);
        }
//맵을 이용해서 동적으로 빈을 설정할 수 있다.
        public int discount(Member member, int price, String discountCode) {//스프링 빈을 이용해서 다형성 코드를 개발할 수 있다.
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
