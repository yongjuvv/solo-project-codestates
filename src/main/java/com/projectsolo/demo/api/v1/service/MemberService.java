package com.projectsolo.demo.api.v1.service;

import com.projectsolo.demo.api.v1.entity.Member;
import com.projectsolo.demo.api.v1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입 로직
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    //1. 전체 회원 조회 로직
    public Page<Member> findMembers(int page, int size) {
        Page<Member> members = memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("id").descending()));
        return members;
    }

    //2. 지역에 따른 회원 조회 로직
    public Page<Member> findCompanyLocation(int page, int size, String companyLocation) {
        Page<Member> byCompanyLocation = memberRepository.findByCompanyLocation(PageRequest.of(page, size, Sort.by("id").descending()), companyLocation);
        return byCompanyLocation;
    }

    //3. 업종에 따른 회원 조회 조직
    public Page<Member> findCompanyType(int page, int size, String companyType) {
        Page<Member> byCompanyType = memberRepository.findByCompanyType(PageRequest.of(page, size, Sort.by("id").descending()), companyType);
        return byCompanyType;
    }
}
