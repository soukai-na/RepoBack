package com.domospring.library.dao;

import com.domospring.library.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MatereilRepository extends JpaRepository<Materiel,Long> {
}
