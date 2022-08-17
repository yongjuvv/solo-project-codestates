package com.projectsolo.demo.api.v1.controller;

import com.google.gson.Gson;
import com.projectsolo.demo.api.v1.entity.Member;
import com.projectsolo.demo.api.v1.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private Gson gson;

    @Test
    public void getMembersTest() throws Exception {
        //given
        Member member1 = new Member();
        member1.setId(1L);
        member1.setName("test1");
        member1.setSex("m");
        member1.setCompanyName("01");
        member1.setCompanyLocation("11");
        member1.setCompanyType("001");
        member1.setPassword("aasd");

        Member member2 = new Member();
        member2.setId(2L);
        member2.setName("test");
        member2.setSex("m");
        member2.setCompanyName("02");
        member2.setCompanyLocation("22");
        member2.setCompanyType("002");
        member2.setPassword("aasd");

        PageImpl<Member> members = new PageImpl<>(List.of(member1, member2),
                PageRequest.of(0, 10, Sort.by("id").descending()), 2);

        given(memberService.findMembers(0, 2)).willReturn(members);
        //when
        ResultActions actions = mockMvc.perform(get("/v1/members").param("page", "0").param("size", "2").accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andDo(document("get-members",
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("Page 번호"),
                                        parameterWithName("size").description("Page size")
                                )
                        ),
                        responseFields(
                                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("사용자 리스트"),
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("사용자 식별자"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("사용자 이름"),
                                fieldWithPath("[].sex").type(JsonFieldType.STRING).description("사용자 성별"),
                                fieldWithPath("[].companyName").type(JsonFieldType.STRING).description("회사명"),
                                fieldWithPath("[].companyType").type(JsonFieldType.STRING).description("업종"),
                                fieldWithPath("[].companyLocation").type(JsonFieldType.STRING).description("지역"),
                                fieldWithPath("[].password").type(JsonFieldType.STRING).description("사용자 패스워드")

                        )
                ));
        //테스트 완료, API 문서 만들기

    }

    @Test
    public void getLocationMembersTest() throws Exception {
        //given
        Member member1 = new Member();
        member1.setId(1L);
        member1.setName("test1");
        member1.setSex("m");
        member1.setCompanyName("01");
        member1.setCompanyLocation("001");
        member1.setCompanyType("001");
        member1.setPassword("aasd");


        Member member2 = new Member();
        member2.setId(2L);
        member2.setName("test");
        member2.setSex("m");
        member2.setCompanyName("02");
        member2.setCompanyLocation("001");
        member2.setCompanyType("002");
        member2.setPassword("aasd");

        PageImpl<Member> members = new PageImpl<>(List.of(member1, member2),
                PageRequest.of(0, 10, Sort.by("id").descending()), 2);

        given(memberService.findCompanyLocation(0, 2, "001")).willReturn(members);
        //when
        ResultActions actions = mockMvc.perform(get("/v1/members/location").param("page", "0").param("size", "2").param("companyLocation","001").accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andDo(document("get-members-location",
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("Page 번호"),
                                        parameterWithName("size").description("Page size"),
                                        parameterWithName("companyLocation").description("Location 번호")
                                )
                        ),
                        responseFields(
                                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("사용자 리스트"),
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("사용자 식별자"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("사용자 이름"),
                                fieldWithPath("[].sex").type(JsonFieldType.STRING).description("사용자 성별"),
                                fieldWithPath("[].companyName").type(JsonFieldType.STRING).description("회사명"),
                                fieldWithPath("[].companyType").type(JsonFieldType.STRING).description("업종"),
                                fieldWithPath("[].companyLocation").type(JsonFieldType.STRING).description("지역"),
                                fieldWithPath("[].password").type(JsonFieldType.STRING).description("사용자 패스워드")

                        )
                ));
        //테스트 완료, API 문서 만들기

    }

    @Test
    public void getTypeMembersTest() throws Exception {
        //given
        Member member1 = new Member();
        member1.setId(1L);
        member1.setName("test1");
        member1.setSex("m");
        member1.setCompanyName("01");
        member1.setCompanyLocation("002");
        member1.setCompanyType("001");
        member1.setPassword("aasd");


        Member member2 = new Member();
        member2.setId(2L);
        member2.setName("test");
        member2.setSex("m");
        member2.setCompanyName("02");
        member2.setCompanyLocation("001");
        member2.setCompanyType("001");
        member2.setPassword("aasd");

        PageImpl<Member> members = new PageImpl<>(List.of(member1, member2),
                PageRequest.of(0, 10, Sort.by("id").descending()), 2);

        given(memberService.findCompanyType(0, 2, "001")).willReturn(members);
        //when
        ResultActions actions = mockMvc.perform(get("/v1/members/type").param("page", "0").param("size", "2").param("companyType","001").accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andDo(document("get-members-type",
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("Page 번호"),
                                        parameterWithName("size").description("Page size"),
                                        parameterWithName("companyType").description("Type 번호")
                                )
                        ),
                        responseFields(
                                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("사용자 리스트"),
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("사용자 식별자"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("사용자 이름"),
                                fieldWithPath("[].sex").type(JsonFieldType.STRING).description("사용자 성별"),
                                fieldWithPath("[].companyName").type(JsonFieldType.STRING).description("회사명"),
                                fieldWithPath("[].companyType").type(JsonFieldType.STRING).description("업종"),
                                fieldWithPath("[].companyLocation").type(JsonFieldType.STRING).description("지역"),
                                fieldWithPath("[].password").type(JsonFieldType.STRING).description("사용자 패스워드")

                        )
                ));
        //테스트 완료, API 문서 만들기

    }

}