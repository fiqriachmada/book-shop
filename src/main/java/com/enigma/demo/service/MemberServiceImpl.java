package com.enigma.demo.service;

import com.enigma.demo.constant.ResponseMessage;
import com.enigma.demo.entity.Member;
import com.enigma.demo.exception.DataNotFoundException;
import com.enigma.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(String id) {
        verifyMemberById(id);
        return memberRepository.findById(id).get();
    }

    @Override
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(Member member) {
        verifyMemberById(member.getId());
        return memberRepository.save(member);
    }

    @Override
    public void deleteMemberById(String id) {
        verifyMemberById(id);
        memberRepository.deleteById(id);
    }

    private void verifyMemberById(String id) {
        if (!memberRepository.findById(id).isPresent()) {
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "members", id);
            throw new DataNotFoundException(message);
        }
    }
}
