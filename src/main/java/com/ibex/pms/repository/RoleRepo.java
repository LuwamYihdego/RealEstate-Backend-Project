package com.ibex.pms.repository;

import com.ibex.pms.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role getByRoleEqualsIgnoreCase(String role);
}
