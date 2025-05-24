package com.annotateurproject.repository;

import com.annotateurproject.entity.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepo extends JpaRepository<role, Integer> {
}
