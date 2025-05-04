package com.annotateurproject.repository;

import com.annotateurproject.entity.utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface utilisateurRepo extends JpaRepository<utilisateur, Integer> {
utilisateur findByLogin(String login);
}
