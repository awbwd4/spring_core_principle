package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("fixDiscountPolicy")
public class FixedDiscountPolicy implements DiscountPolicy{

    private int discountFixedAmt = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {//Enum은 == 쓰는게 맞음
            return discountFixedAmt;
        }
        return 0;
    }





}
