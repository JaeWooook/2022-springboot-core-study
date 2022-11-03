package springboot.corestudy.scan.filter;

import java.lang.annotation.*;

//이거는 aspect?인가 aop쪽 언어 사용해서한다.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
