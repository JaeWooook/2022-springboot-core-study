package springboot.corestudy.singleton;

public class SingletonService {
    //하지만 스프링컨테이너는 알아서 싱글톤 패턴으로 디자인 해준다.

    //아래의 방법은 객체를 미리 생성해두는 가장 단순하고 안전한 방법이다. 이외에도 여러 방법이 있다.
    private static final SingletonService instance = new SingletonService();
    //자기 자신을 호출해서 인스턴스에 1개의 참조값을 생성한다.
    //자바가 뜨면서 static영역에서 1개의 인스턴스를 생성하고, 인스턴스를 꺼낼수있는 방법은 getInstance뿐이다.
    //이것을 생성할 수 있는곳은 아무곳도 없다. private으로 생성자를 만들었기 때문이다. new로 생성할 수 있는 곳은 없다.

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {//private으로 생성자를 만들면 다른 클래스에서는 호출할 수 없다.
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
