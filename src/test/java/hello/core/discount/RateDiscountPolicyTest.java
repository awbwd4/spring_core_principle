package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();


    @Test
    @DisplayName("VIP는 10퍼 할인이 적용돼야 한다.")
    void vip_o(){
        //givne
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount_price = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount_price).isEqualTo(1000);


    }

    @Test
    @DisplayName("VIP가 아니면 할인 적용x")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberNotVIP", Grade.BASIC);
        //when
        int discount_price = discountPolicy.discount(member, 10000);

        //then
        Assertions.assertThat(discount_price).isEqualTo(0);
    }



}