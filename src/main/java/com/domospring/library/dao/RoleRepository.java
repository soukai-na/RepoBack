package com.domospring.library.dao;

import com.domospring.library.model.Role;
import com.domospring.library.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
   Optional<Role> findByName(RoleName roleName);
}
