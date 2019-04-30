package com.kensplanet.familytreeapi.repository;

import com.kensplanet.familytreeapi.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

}
