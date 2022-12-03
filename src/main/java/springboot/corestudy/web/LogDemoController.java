package springboot.corestudy.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.corestudy.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자에 오토와이어드 생겨서 자동으로 주입되는 어노테이션
public class LogDemoController {

    //이상태로만 된다면 스프링 컨테이너 뜰때 의존관계를 주입하기 때문에 mylogger를 불러낸다.
    // 하지만 mylogger가 scope가 request scope임으로 내놓으려고 하는데 request가 없다.
    // 이 빈의 생존 범위는 http request오고, 다시 받을때 까지 인데, scope의 생존 범위를 벗어난 행동이다.
    //'myLogger': Scope 'request' is not active for the current thread; 이러한 에러가 발생한다. 이럴때는 Provider를 사용해서 해결할 수 있다.
    private final LogDemoService logDemoService;
//    private final MyLogger myLogger;
    //스프링 컨테이너가 시작할때 생성자로 주입하지 않고 찾을 수 있도록만 look up만 해주고, 실질적인 주입을할때 주입한다.
    private final ObjectProvider<MyLogger> myLoggerProvider; //이렇게하면 마이로거를 찾지않고 DL 의존관계를 찾아준다.
    //주입 시점에 주입을 받을 수 있게 된다.

    @RequestMapping("log-demo")
    @ResponseBody//문자를 그대로 보낼수가있다 화면이없어도
    public String logDemo(HttpServletRequest request) {
        MyLogger myLogger = myLoggerProvider.getObject();//정말 필요한 시점에서 의존관계를 주입한다.
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
