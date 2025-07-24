package com.joaovictor.commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joaovictor.commerce.entities.User;
import com.joaovictor.commerce.projections.UserDetailsProjection;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(nativeQuery = true, value = "SELECT  u.EMAIL AS username , u.PASSWORD , role.ID  AS roleId, role.AUTHORITY  "
			+ "FROM  tb_user AS u "
			+ "INNER JOIN tb_user_role  AS  js ON  js.ID_USER   = u.ID "
			+ "INNER JOIN tb_role  AS role ON js.ID_ROLE = role.ID "
			+ "WHERE u.email = :email")
	List<UserDetailsProjection> loadUserByUsername(String email);
}
