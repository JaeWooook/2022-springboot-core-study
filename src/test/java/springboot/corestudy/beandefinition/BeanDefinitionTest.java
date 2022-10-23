package springboot.corestudy.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springboot.corestudy.AppConfig;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // Java코드로 AppConfig로 넣는것은 앱컨피그의 팩토리메서드를 통해서 빈을 넣어주는 방식
    //이걸로 하면 팩토리빈네임이랑 팩토리메서드네임도 나온다. 팩토리빈을 통해서 등록이되는 방식이기 때문
    //일반적인 방식은 팩토리빈을 통해서 빈을 등록한다.
    
//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml"); //xml 형식으로 Bean 정의를 보는 방법

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = "  + beanDefinition);
            }
        }
    }
}
