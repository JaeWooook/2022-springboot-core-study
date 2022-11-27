package springboot.corestudy.lifecycle;

import org.springframework.beans.factory.DisposableBean; //destroy가 들어있다.
import org.springframework.beans.factory.InitializingBean; //프로퍼티 셋팅이 끝나면 호출해주는것이다. 의존관계 주입이 끝나면 알려준다.

import javax.annotation.PostConstruct; //javax로 시작하면 java 공식적으로 지원하는것이라 스프링이 아니여도 사용할 수 있다.
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스를 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //스프링에 의존적인 인터페이스라는게 단점이다. 내가 코드를 고칠수 없는 외부라이브러리가 들어있다. 요즘은 거의 사용하지 않는다.
//    @Override
    @PostConstruct //코드를 고칠 수 없는 외부의 라이브러리를 초기화, 종료해야한다면 @Bean의 initMethod, destroyMethod를 사용하자
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

//    @Override
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
