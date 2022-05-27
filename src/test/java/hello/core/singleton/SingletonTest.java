package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출시마다 객체를 생성하는지
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

//        Assertions.assertThrows()
        //memberSErvice1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }


    @Test
    @DisplayName("싱글톤")
    void singletonService(){
//        new SingletonService();
        SingletonService ss1 = SingletonService.getInstance();
        SingletonService ss2 = SingletonService.getInstance();

        System.out.println("ss1 = " + ss1);
        System.out.println("ss2 = " + ss2);


        Assertions.assertThat(ss1).isSameAs(ss2);


    }



}
