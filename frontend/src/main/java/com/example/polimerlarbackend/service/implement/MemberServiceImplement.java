package com.example.polimerlarbackend.service.implement;

import com.example.polimerlarbackend.model.Member;
import com.example.polimerlarbackend.repository.MemberRepository;
import com.example.polimerlarbackend.service.MemberService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImplement implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImplement(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(String email) {
        if (email == null || !email.contains("@")) {
            throw new RuntimeException("Invalid email address!");
        }

        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered!");
        }

        Member member = Member.builder()
                .email(email)
                .build();
        memberRepository.save(member);
        return member;
    }


    @Override
    public List<Member> readAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member readById(long id) {
        return memberRepository.findById(id).orElseThrow(
                ()-> new RuntimeException(id+" of Member not found!")
        );
    }

    @Override
    public String deleteMember(long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("ID:"+id+" not found!")
        );
        memberRepository.deleteById(member.getId());
        return "Deleting successful!";
    }


}