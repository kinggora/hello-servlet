package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    /** 싱글 톤 패턴 */
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){}

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id); //이렇게 해서 찾은 객체를 수정하면 store에 있는 객체도 수정됨
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); //새로운 리스트를 생성했기 때문에 store와는 별개로 작동
    }

    public void clearStore(){
        store.clear();
    }

}
