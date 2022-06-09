package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect : "+url);
    }

    public void call(String message) {
        System.out.println("call  "+url+"  message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close  "+url);
    }


    @PostConstruct
    public void init() throws Exception {//afterPropertiesSet : 의존 관계 주입이 끝난 이후에
        System.out.println("======init()======");
        connect();
        call("초기화 연결 메세지");
        System.out.println("================================");
    }

    @PreDestroy
    public void close() throws Exception { //이 빈이 종료될 때 호출됨.
        System.out.println("=========NetworkClient.close=========");
        disconnect();
    }
}
