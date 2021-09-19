package com.enigma.demo.service;

import com.enigma.demo.entity.Member;

import java.util.List;

public interface MemberService {
    public Member addMember(Member member);

    public Member getMemberById(String id);

    public List<Member> getAllMember();

    public Member updateMember(Member member);

    public void deleteMemberById(String id);
}
