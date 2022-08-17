package com.projectsolo.demo.api.v1.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String password;

    private String sex;

    private String companyName;

    private String companyType;

    private String companyLocation;


}
