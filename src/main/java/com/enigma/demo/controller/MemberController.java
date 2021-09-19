package com.enigma.demo.controller;

import com.enigma.demo.entity.Member;
import com.enigma.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return memberService.getMemberById(id);
    }

    @GetMapping
    public List<Member> getAllBook() {
        return memberService.getAllMember();
    }

    @PutMapping
    public Member updateMember(@RequestBody Member member) {
        return memberService.updateMember(member);
    }

    @DeleteMapping
    public void deleteMemberById(@RequestParam String id) {
        memberService.deleteMemberById(id);
    }
}
