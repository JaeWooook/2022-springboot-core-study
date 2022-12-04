package springboot.corestudy.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//proxy모드의 적용 대상이 인터페이스가 아니면 TARGET_CLASS 를 선택하고, 인터페이스면 INTERFACES를 선택
//이렇게 프록시모드를 사용하면 MyLogger의 가짜 프록시 클래스를 만들어 두고 HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해둘수 있다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //이 빈은 스프링 빈에 HTTP요청당 하나씩 생성되고, 요청이 끝나는 시점에 소멸된다.
public class MyLogger {
//가짜 프록시를 만드는것이다. 해당 가짜프록시 객체는 생성되도라도 실제 빈을 호출할때 내부에서 빈을 요청하는 위임 로직이 들어있다.
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {//requestURL은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력 받는다.
        this.requestURL = requestURL;
    }

    //서로 다른 uuid를 통해서 로그에서 다른 uuid임을 가지고 같은 uuid는 같은 http 요청임을 알 수 있다.
    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
