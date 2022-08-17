package com.projectsolo.demo.api.v1.service;

import com.projectsolo.demo.api.v1.entity.Member;
import com.projectsolo.demo.api.v1.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {


    @Autowired
    private MemberService memberService;

    @Test
    public void 회원_전체_조회_테스트() {
        Member member1 = new Member();
        member1.setId(1L);
        member1.setName("test1");
        member1.setSex("m");
        member1.setCompanyName("01");
        member1.setCompanyLocation("11");
        member1.setPassword("aasd");
        memberService.createMember(member1);


        Member member2 = new Member();
        member2.setId(2L);
        member2.setName("test");
        member2.setSex("m");
        member2.setCompanyName("02");
        member2.setCompanyLocation("22");
        member2.setPassword("aasd");
        memberService.createMember(member2);

        Page<Member> members = memberService.findMembers(0, 2);
        List<Member> memberList = members.getContent();

        for (Member member : memberList) {
            System.out.println(member.getName());
        }

        Assertions.assertEquals(memberList.size(), 2);
    }

    @Test
    public void 회원_지역별_조회_테스트() {
        Member member = new Member();
        member.setId(1L);
        member.setName("test1");
        member.setSex("m");
        member.setCompanyName("01");
        member.setCompanyType("001");
        member.setCompanyLocation("11");
        member.setPassword("aasd");
        memberService.createMember(member);

        Member member2 = new Member();
        member2.setId(2L);
        member2.setName("test2");
        member2.setSex("m");
        member2.setCompanyName("02");
        member2.setCompanyLocation("11");
        member2.setPassword("aasd");
        memberService.createMember(member2);

        Page<Member> companyLocation = memberService.findCompanyLocation(0, 2, "11");
        List<Member> content = companyLocation.getContent();
        Assertions.assertEquals(content.size(), 2);
    }

    @Test
    public void 회원_업종별_조회_테스트() {
        //given
        Member member = new Member();
        member.setId(1L);
        member.setName("test1");
        member.setSex("m");
        member.setCompanyName("01");
        member.setCompanyType("001");
        member.setCompanyLocation("11");
        member.setPassword("aasd");
        memberService.createMember(member);

        //when
        Page<Member> companyType = memberService.findCompanyType(0, 1, "001");
        List<Member> content = companyType.getContent();
        //then
        Assertions.assertEquals(content.size(), 1);
    }
    /**
     * 업종별 조회, 지역별 조회 1건만 조회할 수 있음
     * 어떻게 리팩토링?
     */

}