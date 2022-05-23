package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

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
