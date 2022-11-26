package springboot.corestudy.lifecycle;

import org.springframework.beans.factory.DisposableBean; //destroy가 들어있다.
import org.springframework.beans.factory.InitializingBean; //프로퍼티 셋팅이 끝나면 호출해주는것이다. 의존관계 주입이 끝나면 알려준다.

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
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

//    @Override
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
