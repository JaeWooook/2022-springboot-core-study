package springboot.corestudy.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();//구현한 객체를 넣어주지 않으면 실행할 수 없다.
    private final MemberRepository memberRepository; //이렇게하면 MemberServiceImpl에 memberRepository에 구현체는 없고, 오로지 인터페이스 뿐이다.

    //컴퍼넌트 스캔을 이용해서 빈을 등록하게되면 의존관계주입을 위해 @Autowired를 만들어줘야한다.
    @Autowired //스프링이 자동으로 의존관계를 주입해준다 생성자에 만들어주면
    public MemberServiceImpl(MemberRepository memberRepository) {//생성자를 통해서 구현체에 뭐가들어갈지 해준다.
        this.memberRepository = memberRepository;
    }

    //실제 구현된 객체를 생성해야된다. 그렇지 않으면 null익셉션 발생한다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
