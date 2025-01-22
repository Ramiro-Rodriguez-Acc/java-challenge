package com.javachallenge.repository;

import com.javachallenge.model.Accreditation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditationRepository extends JpaRepository<Accreditation, Integer> {
}
