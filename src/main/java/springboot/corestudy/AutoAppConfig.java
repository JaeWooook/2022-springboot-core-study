package springboot.corestudy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(//자동으로 컴포넌트 스캔을 통해서 빈들이 등록된다.
//        basePackages = "springboot.corestudy.member", //이렇게 하면 멤버만 컴퍼넌트 스캔의 중심이 된다. memberServiceImpl이랑 memoryMemberRepository만 빈이 등록된다.
//        basePackageClasses = AutoAppConfig.class, //이렇게하면 해당 클래스의 패키지 부터 찾기 시작한다.
        //지정하지 않으면, 디폴트 : @ComponentScan이 붙은 설정 정보 클래스의 패키지부터 하위 패키지까지 전부 찾는다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
//권장 방법 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 스프링 부트도 이방법을 기본으로 제공한다.
//예를 들어 com.hello , com.hello.service , 이런식으로 있다면 com.hello에 설정 클래스를 둔다.
//즉, @SpringBootApplication이 있는 곳에 컴포넌트 스캔 자체가 있기 때문에 해당 위치에 설정 클래스를 두는것이 좋다. 하지만 최근에는 스프링부트에서 알아서 컴포넌트 스캔을 해준다.
// @Service, @Controller, @Repository에는 @Component가 이미 포함되어 있어서 특별히 해주지 않아도, 자동으로 포함 시켜준다.
public class AutoAppConfig {
}
