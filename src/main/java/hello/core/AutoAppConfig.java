package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = {"hello.core.member","hello.service"},//이 패키지 하위에서만 탐색한다.
//        basePackageClasses = AutoAppConfig.class, // 이 클래스가 위치한 패키지에서 탐색한다.
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes=Configuration.class)
        // Configuration 애노테이션이 붙은 클래스는 스캐닝에서 배제한다.
        // 디폴트 탐색위치는? ComponentScan 어노테이션이 붙은 클래스가 위치한 패키지에서 부터 탐색 시작.
        // hello.core 를 전부 뒤진다.
)
public class AutoAppConfig {



//    @Bean
//    OrderService orderService() {
////        return new OrderServiceImpl();
//    }

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

}
