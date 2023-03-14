package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Role;

/**
 * 登入角色repository
 * @author sam
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
