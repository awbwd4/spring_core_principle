package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, "rateDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1100);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;


        // 생성자가 하나일때는 알아서 autowired된다.
        // 스프링 빈으로 등록이 돼있는 DiscountPolicy의 구현 객체들(rateDiscountPolicy, fixedDiscountPolicy)이 : 위에서 AutoAppConfig.class를 등록했기 때문
        // 이 객체가 생성될때 자동으로 의존 주입이 된다.
        // Map으로 받게되면 key값은 스프링빈의 이름(rateDiscountPolicy, fixedDiscountPolicy), value값은 객체들이다.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            // 맵안에 DiscountPolicy의 구현체인 RateDiscount~와 FixedDiscount~ 객체가 담겨있음.
            // 키값은 각각 "RateDiscount~"와 "FixedDiscount~" 로 되어있음

            this.policies = policies;


            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            // .get() : 키값을 받으면 해당하는 밸류값을 리턴해줌
            // 키값으로 받는 파라미터는 String discountCode이고
            // 맵의 키값도 스프링 빈의 이름으로 되어있음.
            return discountPolicy.discount(member, price);
        }
    }




}
