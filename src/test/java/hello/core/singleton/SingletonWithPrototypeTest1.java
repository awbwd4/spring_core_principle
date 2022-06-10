package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);


        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);


        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);


    }
    @Scope("singleton")
    static class ClientBean{
//        private final PrototypeBean prototypeBean; // 생성 시점에 주입이 돼있음.

        //        private final ObjectProvider<PrototypeBean> prototypeProvider;
//        private final Provider<PrototypeBean> prototypeProvider;
//
//        public ClientBean(ObjectProvider<PrototypeBean> prototypeBeans) {
//            this.prototypeProvider = prototypeBeans;
//        }
//        }
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            //getObject()를 호출하면 그때서야 스프링 컨테이너에서 prototypeBean을 찾아서 생성해줌.
            // applicationContext 전체를 호출하지 않고 딱 해당 빈만 찾아야 할때 유용
            // ObjectProvider를 통해 항상 새로운 프로토타입 빈이 생성된다.
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

        //        @Autowired
//        ApplicationContext applicationContext;

//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            //이렇게 하면 logic 메서드 호출시마다 prototypeBean 객체를 새로 생성해서 리턴해줌.
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;

    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init "+this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");

        }

    }
}
