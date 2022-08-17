package com.projectsolo.demo.api.v1.controller;

import com.projectsolo.demo.api.v1.dto.MemberDto;
import com.projectsolo.demo.api.v1.entity.Member;
import com.projectsolo.demo.api.v1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberDto.Post requestBody) {
        Member member = new Member();
        member.setName(requestBody.getName());
        member.setPassword(requestBody.getPassword());
        member.setSex(requestBody.getSex());
        member.setCompanyName(requestBody.getCompanyName());
        member.setCompanyLocation(requestBody.getCompanyLocation());
        member.setCompanyType(requestBody.getCompanyType());

        Member createdMember = memberService.createMember(member);

        return new ResponseEntity(createdMember, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity getMembers(@RequestParam int page,
                                     @RequestParam int size) {
        Page<Member> memberPage = memberService.findMembers(page, size);
        List<Member> members = memberPage.getContent();

        return new ResponseEntity(members, HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity getCompanyLocationMembers(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam String companyLocation) {
        Page<Member> memberPage = memberService.findCompanyLocation(page, size, companyLocation);
        List<Member> members = memberPage.getContent();

        return new ResponseEntity(members, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity getCompanyTypeMembers(@RequestParam int page,
                                                @RequestParam int size,
                                                @RequestParam String companyType) {
        Page<Member> memberPage = memberService.findCompanyType(page, size, companyType);
        List<Member> members = memberPage.getContent();

        return new ResponseEntity(members, HttpStatus.OK);
    }


}

