package springboot.corestudy.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import springboot.corestudy.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final MyLogger myLogger;
    //동일하게 ObjectProvider를 해줘야한다 생성자 주입을 한다면 빈이 생성될때 바로 주입하기 때문에 에러가 발생한다 스코프가 달라서
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    public void logic(String id) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
