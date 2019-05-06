package com.kensplanet.familytreeapi.repository;

import com.kensplanet.familytreeapi.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByName(String name);
}