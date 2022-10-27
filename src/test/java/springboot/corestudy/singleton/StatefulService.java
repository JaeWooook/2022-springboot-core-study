package springboot.corestudy.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드  10000->20000

//    public void order(String name, int price) { //Statueful한 상태이다.
//        System.out.println("name = "+ name + " price = " + price);
//        this.price = price; //여기가 문제!
//        //여기에 만원들어와서 할당되고 2만원이 할당된다.
//        // StatefulService는 같은 객체 이기 때문이다.
//    }

    public int order(String name, int price) { //무상태로 설계한다.
        System.out.println("name = "+ name + " price = " + price);
        return price;//이렇게 해주면 price는 각각 선언된 값들은 지역변수라서 사용자값이 다르다.
    }

//    public int getPrice() {
//        return price;
//    }
}
