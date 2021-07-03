package com.domospring.library.dao;

import com.domospring.library.model.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AbonnementRepository extends JpaRepository<Abonnement,Long> {
}

