package com.projectsolo.demo.api.v1.repository;

import com.projectsolo.demo.api.v1.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByCompanyLocation(Pageable pageable, String companyLocation);
    Page<Member> findByCompanyType(Pageable pageable, String companyType);

}
