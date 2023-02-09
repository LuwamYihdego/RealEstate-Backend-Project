package com.ibex.pms.service;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getById(long id);
    void deleteById(long id);
    void  save(Role role);
    void  update(long id, Role role);
}
