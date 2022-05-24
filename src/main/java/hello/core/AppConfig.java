package hello.core;

import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
    //생성한 객체의 인스턴스 참조를 ""생성자""를 통해서 주입해준다.

    // 생성자 주입
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixedDiscountPolicy());
    }



}
