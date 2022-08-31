package springboot.corestudy;

import springboot.corestudy.member.Grade;
import springboot.corestudy.member.Member;
import springboot.corestudy.member.MemberService;
import springboot.corestudy.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        //회원 가입 로직
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);//Long타입은 뒤에 L을 붙여야한다.
        memberService.join(member);

        //정상 회원가입 조회 동일한지 확인 인스턴스 확인하려면 member, findMember로 확인 가능하다.
        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
