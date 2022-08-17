package com.projectsolo.demo.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String name;

        private String password;

        private String sex;

        private String companyName;

        private String companyType;

        private String companyLocation;
    }
}
