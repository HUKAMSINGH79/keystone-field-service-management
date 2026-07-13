package com.zidio.keystone.repository;

import com.zidio.keystone.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    //find role by role name
    Optional<Role> findByRoleName(String roleName);
}
