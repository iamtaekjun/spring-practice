package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // Id 값 세팅
        store.put(member.getId(), member); //Id 값이랑 member store에 넣기

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null 방지
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // lambda 이용해서 map 쭉 돌려서 찾기.
                .filter(member -> member.getName().equals(name))
                .findAny(); // 값 없으면 null 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // values는 member들
    }

    public void clearStore() {
        store.clear();
    }
}
