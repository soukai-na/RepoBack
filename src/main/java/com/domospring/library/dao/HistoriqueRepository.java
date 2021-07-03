package com.domospring.library.dao;

import com.domospring.library.model.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
}
