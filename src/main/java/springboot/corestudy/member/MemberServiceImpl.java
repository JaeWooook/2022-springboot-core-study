package springboot.corestudy.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();//구현한 객체를 넣어주지 않으면 실행할 수 없다.
    //실제 구현된 객체를 생성해야된다. 그렇지 않으면 null익셉션 발생한다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
