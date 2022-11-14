package springboot.corestudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springboot.corestudy.member.MemberRepository;
import springboot.corestudy.member.MemoryMemberRepository;

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
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
    //Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing [Generic bean: class [springboot.corestudy.member.MemoryMemberRepository];
    //수동빈으로 등록했을시 오버라이딩 해준다. 자동보다 수동이 우선권을 가진다.
    //하지만 스프링부트는 이제 수동빈과 자동빈 등록이 충돌이되면 에러를 낸다. 이 2가지의 충돌은 엄청난 버그를 초래할수 있기 때문이다.
    //The bean 'memoryMemberRepository', defined in class path resource [springboot/corestudy/AutoAppConfig.class], could not be registered. A bean with that name has already been defined in file
    // 위와 같이 2개가 중복되서 정의되고있다고 메시지를 보여준다. 빈의 기본옵션이 오버라이딩이 disable 되어있어서 충돌에 대한 에러를 발생한다.
    // properties에 spring.main.allow-bean-definition-overriding=true 옵션을 추가하면 오버라이딩이 되도록 해준다.
}
