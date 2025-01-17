package com.rcs.pwaapp.repository;

import com.rcs.pwaapp.model.Procedimento;
import com.rcs.pwaapp.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

}
