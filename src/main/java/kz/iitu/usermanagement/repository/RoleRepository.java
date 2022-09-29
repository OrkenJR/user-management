package kz.iitu.usermanagement.repository;

import kz.iitu.usermanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
